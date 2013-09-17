package com.chamika_kasun.proweather;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * This Class is Used as an Adapter for ViewPager
 * @author Chamika
 * 		   E-mail :  kasun.chamika@gmail.com
 */

public class TabsAdapter extends FragmentPagerAdapter {

	public TabsAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int arg0) {
		switch (arg0) {
		case 0:
			return new Home();

		case 1:
			return new Day_7();
			
		case 2:
			return new Favourites();
			
		case 3:
			return new Tsunami();
			
		}
		return new Fragment();
	}

	@Override
	public int getCount() {
		return 4;
	}

}
