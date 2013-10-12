package com.chamika_kasun.proweather;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.app.ActionBar.TabListener;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

/**
 * This Class is Used to Hold Fragments Home , 7_Day , Favouriets , Tsunami 
 * @author Chamika
 * E-mail : kasun.chamika@gmail.com
 */
public class FragmentsActivity extends SherlockFragmentActivity implements OnPageChangeListener ,TabListener{

	//Create Variables to Hold ViewPager,ActionBar and Constant ACTIVE_TAB
	private ViewPager viewPager;
	private ActionBar actionBar;
	//private final static String ACTIVE_TAB = "active_tab";

	public TabsAdapter tabsAdapter;
	
	@Override
	protected void onCreate(Bundle arg0) {
		
		super.onCreate(arg0);
		setContentView(R.layout.main);
		initialize();
		
	}

	/**
	 * This methos is Used to initializ viewPager
	 */
	private void initialize() {
		
		viewPager = (ViewPager) findViewById(R.id.vpTest);
		actionBar = getSupportActionBar();
		viewPager.setOnPageChangeListener(this);		
		viewPager.setOffscreenPageLimit(4);
		setupPager();
		setUpTabs();
		
	}

	/**
	 * This method is Used to create Tabs for each Fragments
	 */
	private void setUpTabs() {
		
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		//Add Home Fragment to the ActionBar as a Tab
		Tab firstTab = actionBar.newTab();
		firstTab.setText("Home");
		firstTab.setTabListener(this);
		actionBar.addTab(firstTab);
		
		//Add 7_day Fragment to the ActionBar as a Tab
		Tab secondTab = actionBar.newTab();
		secondTab.setText("7 Days");
		secondTab.setTabListener(this);
		actionBar.addTab(secondTab);
		
		//Add Favourits Fragment to the ActionBar as a Tab
		Tab thirdTab = actionBar.newTab();
		thirdTab.setText("Favourits");
		thirdTab.setTabListener(this);
		actionBar.addTab(thirdTab);
		
		//Add Tsunami Fragment to the ActionBar as a Tab
		Tab fourthTab = actionBar.newTab();
		fourthTab.setText("Tsunami");
		fourthTab.setTabListener(this);
		actionBar.addTab(fourthTab);
		
	}

	/**
	 * This method is used to Set the tabsAdapter to the viewPager
	 */
	private void setupPager() {
		tabsAdapter = new TabsAdapter(getSupportFragmentManager());
		viewPager.setAdapter(tabsAdapter);
		
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int arg0) {
		actionBar.setSelectedNavigationItem(arg0);
		
	}
	
	

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		//True use for smooth control
		viewPager.setCurrentItem(tab.getPosition(), true);
		
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//Add a Stting Icon on the Action Bar
		super.onCreateOptionsMenu(menu);
		getSupportMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		//To get Selected Item From the Menu
		switch (item.getItemId()) {
		
		case R.id.about_proweather:
			Intent intent = new Intent("com.chamika_kasun.proweather.ABOUT");
			startActivity(intent);			
			break;

		case R.id.action_settings:
			Intent intent2 = new Intent("com.chamika_kasun.proweather.PREFS"); 
			startActivity(intent2);
			break;
			
		case R.id.proweather_guide:
			Intent intent3 = new Intent("com.chamika_kasun.proweather.PROWEATHERGUIDE"); 
			startActivity(intent3);
			break;
			
		}
		
		return false;
	}
	


}
