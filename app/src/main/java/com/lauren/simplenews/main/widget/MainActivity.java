package com.lauren.simplenews.main.widget;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.lauren.simplenews.R;
import com.lauren.simplenews.about.widget.AboutFragment;
import com.lauren.simplenews.images.widget.ImageFragment;
import com.lauren.simplenews.main.presenter.MainPresenter;
import com.lauren.simplenews.main.presenter.MainPresenterImpl;
import com.lauren.simplenews.main.view.MainView;
import com.lauren.simplenews.news.widget.NewsFragment;
import com.lauren.simplenews.weather.widget.WeatherFragment;

/**
 * Author : lauren
 * Email  : lauren.liuling@gmail.com
 * Blog   : http://www.liuling123.com
 * Date   : 15/12/13
 */
public class MainActivity extends AppCompatActivity implements MainView {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    private MainPresenter mMainPresenter;

    private Fragment mCurrentFragment;
    private NewsFragment mNewsFragment;
    private ImageFragment mImageFragment;
    private WeatherFragment mWeatherFragment;
    private AboutFragment mAboutFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open,
                R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        setupDrawerContent(mNavigationView);

        mMainPresenter = new MainPresenterImpl(this);
        switch2News();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        mMainPresenter.switchNavigation(menuItem.getItemId());
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    @Override
    public void switch2News() {
        if (mNewsFragment == null) {
            mNewsFragment = new NewsFragment();
        }
        showFragment(mNewsFragment);
        mToolbar.setTitle(R.string.navigation_news);
    }

    @Override
    public void switch2Images() {
        if (mImageFragment == null) {
            mImageFragment = new ImageFragment();
        }
        showFragment(mImageFragment);
        mToolbar.setTitle(R.string.navigation_images);
    }

    @Override
    public void switch2Weather() {
        if (mWeatherFragment == null) {
            mWeatherFragment = new WeatherFragment();
        }
        showFragment(mWeatherFragment);
        mToolbar.setTitle(R.string.navigation_weather);
    }

    @Override
    public void switch2About() {
        if (mAboutFragment == null) {
            mAboutFragment = new AboutFragment();
        }
        showFragment(mAboutFragment);
        mToolbar.setTitle(R.string.navigation_about);
    }

    private void showFragment(@NonNull Fragment fragment) {
        if (fragment == mCurrentFragment) {
            return;
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (mCurrentFragment != null) {
            transaction.hide(mCurrentFragment);
        }
        if (!fragment.isAdded()) {
            transaction.add(R.id.frame_content, fragment);
        } else {
            transaction.show(fragment);
        }
        transaction.commit();
        mCurrentFragment = fragment;
    }
}
