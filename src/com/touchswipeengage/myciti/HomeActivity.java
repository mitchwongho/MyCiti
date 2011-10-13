/**
 * 
 */
package com.touchswipeengage.myciti;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.TabContentFactory;

import com.touchswipeengage.myciti.fragments.NotificationsFragment;
import com.touchswipeengage.myciti.fragments.RoutesFragment;
import com.touchswipeengage.myciti.fragments.TwitterFragment;
import com.touchswipeengage.myciti.services.twitter.MyCitiUpdateTwitterService;
import com.touchswipeengage.myciti.ui.PagerAdapter;

/**
 * @author mwho
 *
 */
/**
 * @author mwho
 *
 */
public class HomeActivity extends BaseActivity implements TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener {

	private TabHost mTabHost;
	private ViewPager mViewPager;
	private HashMap<String, TabInfo> mapTabInfo = new HashMap<String, HomeActivity.TabInfo>();
	private PagerAdapter mPagerAdapter;
	/**
	 * 
	 * @author mwho
	 * Maintains extrinsic info of a tab's construct 
	 */
	private class TabInfo {
		 private String tag;
         private Bundle args;
         TabInfo(String tag, Bundle args) {
        	 this.tag = tag;
        	 this.args = args;
         }
	}
	/**
	 * A simple factory that returns dummy views to the Tabhost
	 * @author mwho
	 */
	class TabFactory implements TabContentFactory {

		private final Context mContext;

	    /**
	     * @param context
	     */
	    public TabFactory(Context context) {
	        mContext = context;
	    }

	    /** (non-Javadoc)
	     * @see android.widget.TabHost.TabContentFactory#createTabContent(java.lang.String)
	     */
	    public View createTabContent(String tag) {
	        View v = new View(mContext);
	        v.setMinimumWidth(0);
	        v.setMinimumHeight(0);
	        return v;
	    }

	}
	/** (non-Javadoc)
	 * @see android.support.v4.app.FragmentActivity#onCreate(android.os.Bundle)
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Inflate the layout
		setContentView(R.layout.home_tabs_viewpager_layout);
		// Initialise the TabHost
		this.initialiseTabHost(savedInstanceState);
		if (savedInstanceState != null) {
            mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab")); //set the tab as per the saved state
        }
		// Intialise ViewPager
		this.intialiseViewPager();
		
		// Do some background tasks
		Intent twitterUpdateIntent = new Intent(this, MyCitiUpdateTwitterService.class);
		super.startService(twitterUpdateIntent);
		
	}

	/** (non-Javadoc)
     * @see android.support.v4.app.FragmentActivity#onSaveInstanceState(android.os.Bundle)
     */
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("tab", mTabHost.getCurrentTabTag()); //save the tab selected
        super.onSaveInstanceState(outState);
    }
    
    /**
     * Initialise ViewPager
     */
    private void intialiseViewPager() {
		
		List<Fragment> fragments = new Vector<Fragment>();
		fragments.add(Fragment.instantiate(this, RoutesFragment.class.getName()));
		fragments.add(Fragment.instantiate(this, TwitterFragment.class.getName()));
		fragments.add(Fragment.instantiate(this, NotificationsFragment.class.getName()));
		this.mPagerAdapter  = new PagerAdapter(super.getSupportFragmentManager(), fragments);
		//
		this.mViewPager = (ViewPager)super.findViewById(R.id.viewpager);
		this.mViewPager.setAdapter(this.mPagerAdapter);
		this.mViewPager.setOnPageChangeListener(this);
    }
	
	/**
	 * Initialise the Tab Host
	 */
	private void initialiseTabHost(Bundle args) {
		Resources res = getResources();
		mTabHost = (TabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup();
        TabInfo tabInfo = null;
        this.addTab(this, this.mTabHost, this.mTabHost.newTabSpec("Routes").setIndicator(getString(R.string.tabs_routes),res.getDrawable(R.drawable.tab_ic_routes)), ( tabInfo = new TabInfo("routes", args)));
        this.mapTabInfo.put(tabInfo.tag, tabInfo);
        this.addTab(this, this.mTabHost, this.mTabHost.newTabSpec("Twitter").setIndicator(getString(R.string.tabs_twitter),res.getDrawable(R.drawable.tab_ic_twitter)), ( tabInfo = new TabInfo("twitter", args)));
        this.mapTabInfo.put(tabInfo.tag, tabInfo);
        this.addTab(this, this.mTabHost, this.mTabHost.newTabSpec("Notifications").setIndicator(getString(R.string.tabs_alerts),res.getDrawable(R.drawable.tab_ic_info)), ( tabInfo = new TabInfo("notifications", args)));
        this.mapTabInfo.put(tabInfo.tag, tabInfo);
        // Default to first tab
        //this.onTabChanged("Tab1");
        //
        mTabHost.setOnTabChangedListener(this);
	}
	
	/**
	 * Add Tab content to the Tabhost
	 * @param activity
	 * @param tabHost
	 * @param tabSpec
	 * @param clss
	 * @param args
	 */
	private void addTab(HomeActivity activity, TabHost tabHost, TabHost.TabSpec tabSpec, TabInfo tabInfo) {
		// Attach a Tab view factory to the spec
		tabSpec.setContent(activity.new TabFactory(activity));
        tabHost.addTab(tabSpec);
	}

	/** (non-Javadoc)
	 * @see android.widget.TabHost.OnTabChangeListener#onTabChanged(java.lang.String)
	 */
	public void onTabChanged(String tag) {
		//TabInfo newTab = this.mapTabInfo.get(tag);
		int pos = this.mTabHost.getCurrentTab();
		this.mViewPager.setCurrentItem(pos);
    }

	/* (non-Javadoc)
	 * @see android.support.v4.view.ViewPager.OnPageChangeListener#onPageScrolled(int, float, int)
	 */
	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {
		
	}

	/* (non-Javadoc)
	 * @see android.support.v4.view.ViewPager.OnPageChangeListener#onPageSelected(int)
	 */
	@Override
	public void onPageSelected(int position) {
		this.mTabHost.setCurrentTab(position);
	}

	/* (non-Javadoc)
	 * @see android.support.v4.view.ViewPager.OnPageChangeListener#onPageScrollStateChanged(int)
	 */
	@Override
	public void onPageScrollStateChanged(int state) {
		// TODO Auto-generated method stub
		
	}

}
