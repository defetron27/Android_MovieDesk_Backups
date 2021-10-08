package com.def.max.moviedesk;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private MaterialSearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mainToolbar =findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);

        searchView = findViewById(R.id.search_view);
        searchView.setVoiceSearch(true);

        int[] icons = {R.drawable.top_rated, R.drawable.popular,};

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager viewPager = findViewById(R.id.main_tab_content);

        setupViewPager(viewPager);

        tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < icons.length; i++)
        {
            tabLayout.getTabAt(i).setIcon(icons[i]);
        }

        tabLayout.getTabAt(0).select();

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener()
        {
            @Override
            public void onSearchViewShown()
            {

            }

            @Override
            public void onSearchViewClosed()
            {

            }
        });

    }

    private void setupViewPager(ViewPager viewPager)
    {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.insertNewFragment(new TopRatedMoviesFragment());
        adapter.insertNewFragment(new PopularMoviesFragment());
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentStatePagerAdapter
    {
        private final List<Fragment> mFragmentList = new ArrayList<>();

        ViewPagerAdapter(FragmentManager manager)
        {
            super(manager);
        }

        @Override
        public Fragment getItem(int position)
        {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount()
        {
            return mFragmentList.size();
        }

        void insertNewFragment(Fragment fragment)
        {
            mFragmentList.add(fragment);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

        return true;
    }
}
