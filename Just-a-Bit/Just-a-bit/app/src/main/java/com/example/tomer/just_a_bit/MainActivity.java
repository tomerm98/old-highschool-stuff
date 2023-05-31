package com.example.tomer.just_a_bit;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;
    final int DEFAULT_TAB_INDEX = 1;
    final int TAB_COUNT = 3;
    final String TAB0_NAME = "Wallets";
    final String TAB1_NAME = "Main";
    final String TAB2_NAME = "History";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeTabs();
    }

    private void initializeTabs() {

        // Create the adapter that will return a fragment for each of the
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(DEFAULT_TAB_INDEX);
        //connects the tabLayout with the fragments(viewPager)
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position)
            {
                case 0:
                    return new Tab0Fragment();
                case 1:
                    return new Tab1Fragment();
                case 2:
                    return new Tab2Fragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return TAB_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return TAB0_NAME;
                case 1:
                    return TAB1_NAME;
                case 2:
                    return TAB2_NAME;
            }
            return null;
        }
    }
}
