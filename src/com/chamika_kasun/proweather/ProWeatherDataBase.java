package com.chamika_kasun.proweather;

import java.sql.SQLException;
import java.util.ArrayList;

import com.chamika_kasun.proweather.objects.Location;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ProWeatherDataBase {

	public static final String KEY_ROWID = "_id";
	public static final String KEY_LONGITUDE = "longitude";
	public static final String KEY_LATTITUDE = "lattitude";
	public static final String KEY_CITY = "city";
	public static final String KEY_COUNTRY = "country";

	private static final String DATABASE_NAME = "Favorites";
	private static final String DATABASE_TABLE = "favorites_Table";
	private static final int DATABASE_VERSION = 1;

	private DbHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;

	private static class DbHelper extends SQLiteOpenHelper {

		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {

			db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" + KEY_ROWID
					+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_CITY
					+ " TEXT NOT NULL, " + KEY_COUNTRY + " TEXT NOT NULL, "
					+ KEY_LONGITUDE + " REAL, " + KEY_LATTITUDE + " REAL);");

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

			db.execSQL("DROP TABLE IF EXISTS" + DATABASE_TABLE);
			onCreate(db);

		}

	}

	public ProWeatherDataBase(Context c) {
		ourContext = c;
	}

	public ProWeatherDataBase open() throws SQLException {
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;

	}

	public void close() {
		ourHelper.close();
	}

	public void createEntrey(Location locaitonLast) {

		ContentValues cv = new ContentValues();
		cv.put(KEY_CITY, locaitonLast.getCity());
		cv.put(KEY_COUNTRY, locaitonLast.getCountry());
		cv.put(KEY_LONGITUDE, locaitonLast.getLatitude());
		cv.put(KEY_LATTITUDE, locaitonLast.getLongitude());


	}

	public ArrayList<Location> getData() {

		String[] columns = new String[] { KEY_ROWID, KEY_CITY, KEY_COUNTRY,
				KEY_LONGITUDE, KEY_LATTITUDE };
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null,
				null, null);

		ArrayList<Location> locationArray = new ArrayList<Location>();

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {

			int id = Integer.parseInt(c.getString(c.getColumnIndex(KEY_ROWID)));
			String city = c.getString(c.getColumnIndex(KEY_CITY));
			String country = c.getString(c.getColumnIndex(KEY_COUNTRY));
			float lattitude = Float.parseFloat(c.getString(c
					.getColumnIndex(KEY_LONGITUDE)));
			float longitude = Float.parseFloat(c.getString(c
					.getColumnIndex(KEY_LATTITUDE)));

			Location location = new Location(lattitude, longitude, country,
					city);
			location.setId(id);

			locationArray.add(location);

		}

		c.close();

		return locationArray;
	}

	public void updateEntry(long lRow, String mCity, String mCountry)
			throws SQLException {

		ContentValues cvUpdate = new ContentValues();
		cvUpdate.put(KEY_CITY, mCity);
		cvUpdate.put(KEY_COUNTRY, mCountry);
		ourDatabase.update(DATABASE_TABLE, cvUpdate, KEY_ROWID + "=" + lRow,
				null);

	}

	public void deleteEntry(long lRow1, String city, String country)
			throws SQLException {

		ourDatabase.delete(DATABASE_TABLE, KEY_ROWID + "=" + lRow1 + " AND " + KEY_CITY + " = " + city+ " AND " + KEY_COUNTRY + " = " + country, null);

	}

	public boolean isExisting(String cityAdded, String countryAdded) {
		
		boolean ret;

		String[] data_Columns = new String[] { KEY_CITY, KEY_COUNTRY };
		Cursor cursor = ourDatabase.query(DATABASE_TABLE, data_Columns, KEY_CITY + " = ? AND " + KEY_COUNTRY + " = ?",new String[]{cityAdded,countryAdded}, null, null, null);

		if (cursor.moveToFirst()) {
			ret = true;
		} else {
			ret = false;
		}
		
		cursor.close();
		return ret;
	}

}
