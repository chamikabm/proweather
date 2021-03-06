package com.chamika_kasun.proweather;

import java.sql.SQLException;
import java.util.ArrayList;

import com.chamika_kasun.proweather.objects.Location;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * This class is used to create Database for the Application. Used to store Favouriets Locations Data.
 * @author Chamika
 * E-mail : kasun.chamika@gmail.com
 */
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

	/**
	 * This method is used to create a database
	 * @return
	 * @throws SQLException
	 */
	public ProWeatherDataBase open() throws SQLException {
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;

	}

	/**
	 * This method is used to close the database conection
	 */
	public void close() {
		ourHelper.close();
	}

	/**
	 * This methos is used to create an Entry for a Location
	 * @param locaitonLast - It takes an Location Object
	 */
	public void createEntrey(Location locaitonLast) {

		ContentValues cv = new ContentValues();
		cv.put(KEY_CITY, locaitonLast.getCity());
		cv.put(KEY_COUNTRY, locaitonLast.getCountry());
		cv.put(KEY_LONGITUDE, locaitonLast.getLatitude());
		cv.put(KEY_LATTITUDE, locaitonLast.getLongitude());

		ourDatabase.insert(DATABASE_TABLE, null, cv);

	}

	/**
	 * This method is used to returen the saved data in the Database
	 * @return - IT returns the saved data in ArrayList<Location>  format
	 */
	public ArrayList<Location> getData() {

		String[] columns = new String[] { KEY_ROWID, KEY_CITY, KEY_COUNTRY,KEY_LONGITUDE, KEY_LATTITUDE };
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null,null, null);
		
		Log.v("Data Base Table Size", "Data Base Table Size : "+c.getCount());

		ArrayList<Location> locationArray = new ArrayList<Location>();

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {

			int id = Integer.parseInt(c.getString(c.getColumnIndex(KEY_ROWID)));
			String city = c.getString(c.getColumnIndex(KEY_CITY));
			String country = c.getString(c.getColumnIndex(KEY_COUNTRY));
			float lattitude = Float.parseFloat(c.getString(c.getColumnIndex(KEY_LONGITUDE)));
			float longitude = Float.parseFloat(c.getString(c.getColumnIndex(KEY_LATTITUDE)));

			Location location = new Location(lattitude, longitude, country,city);
			location.setId(id);

			locationArray.add(location);

		}

		c.close();

		return locationArray;
	}

	
	/**
	 * This methos is used to Update an entry
	 * @param lRow - It takes the Row number in long format
	 * @param mCity - It takes the City name in String format
	 * @param mCountry - It takes the Country name in String format 
	 * @throws SQLException - It throws an SQLException if an Error occureed while accessing the database
	 */
	public void updateEntry(long lRow, String mCity, String mCountry)throws SQLException {

		ContentValues cvUpdate = new ContentValues();
		cvUpdate.put(KEY_CITY, mCity);
		cvUpdate.put(KEY_COUNTRY, mCountry);
		ourDatabase.update(DATABASE_TABLE, cvUpdate, KEY_ROWID + "=" + lRow,
				null);

	}

	/**
	 * This method is used to delete a pertocular entry from the databases
	 * @param lRow1 - It takes the Row number in long format
	 * @param city - It takes the City name in String format
	 * @param country - It takes the Country name in String format 
	 * @throws SQLException - It throws an SQLException if an Error occureed while accessing the database
	 */
	public void deleteEntry(long lRow1, String city, String country)throws SQLException {

		ourDatabase.delete(DATABASE_TABLE, KEY_ROWID + "=" + lRow1 + " AND " + KEY_CITY + " = " + city+ " AND " + KEY_COUNTRY + " = " + country, null);

	}

	
	/**
	 * This method is used to check the existing items in the database
	 * @param cityAdded - It takes the City Added in String format
	 * @param countryAdded - It takes the Country in the String format
	 * @return
	 */
	public boolean isExisting(String cityAdded, String countryAdded) {
		
		boolean ret;

		String[] data_Columns = new String[] { KEY_CITY, KEY_COUNTRY };
		Cursor cursor = ourDatabase.query(DATABASE_TABLE, data_Columns, KEY_CITY + " = ? AND " + KEY_COUNTRY + " = ?",new String[]{cityAdded,countryAdded}, null, null, null);

		if (cursor.moveToFirst()) {
			Toast.makeText (ourContext, "Existing Location.",Toast.LENGTH_SHORT).show();
			ret = true;
		} else {
			ret = false;
		}
		
		cursor.close();
		return ret;
	}

}
