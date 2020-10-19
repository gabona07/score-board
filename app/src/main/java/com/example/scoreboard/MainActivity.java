package com.example.scoreboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.scoreboard.view.LastEventsFragment;
import com.example.scoreboard.view.NextEventsFragment;
import com.example.scoreboard.view.adapters.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // remove shadow from actionbar
        Objects.requireNonNull(getSupportActionBar()).setElevation(0);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager viewPager = findViewById(R.id.pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(LastEventsFragment.newInstance(), getString(R.string.first_tab_text));
        adapter.addFragment(NextEventsFragment.newInstance(), getString(R.string.second_tab_text));
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}