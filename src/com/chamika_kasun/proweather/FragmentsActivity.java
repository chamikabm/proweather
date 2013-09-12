package com.chamika_kasun.proweather;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;

import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.app.ActionBar.TabListener;

public class FragmentsActivity extends SherlockFragmentActivity implements OnPageChangeListener ,TabListener{

	private ViewPager viewPager;
	private ActionBar actionBar;
	private final static String ACTIVE_TAB = "active_tab";

	public TabsAdapter tabsAdapter;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.main);
		initialize();
		
	}

	private void initialize() {
		// TODO Auto-generated method stub
		viewPager = (ViewPager) findViewById(R.id.vpTest);
		actionBar = getSupportActionBar();
		viewPager.setOnPageChangeListener(this);		
		setupPager();
		setUpTabs();
	}

	private void setUpTabs() {
		// TODO Auto-generated method stub
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		Tab firstTab = actionBar.newTab();
		firstTab.setText("Home");
		firstTab.setTabListener(this);
		actionBar.addTab(firstTab);
		
		Tab secondTab = actionBar.newTab();
		secondTab.setText("7 Days");
		secondTab.setTabListener(this);
		actionBar.addTab(secondTab);
		
		Tab thirdTab = actionBar.newTab();
		thirdTab.setText("Favourits");
		thirdTab.setTabListener(this);
		actionBar.addTab(thirdTab);
		
		Tab fourthTab = actionBar.newTab();
		fourthTab.setText("Detailed");
		fourthTab.setTabListener(this);
		actionBar.addTab(fourthTab);
		
	}

	private void setupPager() {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		actionBar.setSelectedNavigationItem(arg0);
		
	}
	
	

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		//true use for smooth control
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


}
