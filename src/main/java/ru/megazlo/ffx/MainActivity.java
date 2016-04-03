package ru.megazlo.ffx;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringArrayRes;

/**
 * @author iv - 27.03.2016
 */
@EActivity(R.layout.main)
public class MainActivity extends AppCompatActivity {

	@ViewById(R.id.my_toolbar)
	Toolbar toolbar;
	@StringArrayRes(R.array.cats_array_ru)
	String[] mCatTitles;
	@ViewById(R.id.main_drawer)
	DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;
	@ViewById(R.id.swipe_menu)
	ListView swipeMenu;
	@ViewById(R.id.refresh)
	SwipeRefreshLayout refreshLayout;
	@Bean
	LeftMenuClickListener leftMenuClickListener;

	@AfterViews
	void afterView() {
		setSupportActionBar(toolbar);
		refreshLayout.setColorSchemeResources(R.color.title_top_color);
		refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
											   @Override
											   public void onRefresh() {
												   refreshLayout.setRefreshing(false);
											   }
										   }
		);
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);
			actionBar.setHomeButtonEnabled(true);
		}
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		swipeMenu.setOnItemClickListener(leftMenuClickListener);
		swipeMenu.setAdapter(new ArrayAdapter<>(this, R.layout.drawer_list_item, mCatTitles));
		swipeMenu.addHeaderView(getLayoutInflater().inflate(R.layout.swipe_list_header, null));
		swipeMenu.setAdapter(new ArrayAdapter<>(this, R.layout.drawer_list_item, mCatTitles));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_top_right, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onBackPressed() {
		if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
			mDrawerLayout.closeDrawer(Gravity.START);
		} else {
			super.onBackPressed();
		}
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
}
