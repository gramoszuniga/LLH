/*
    MainActivity.java
    Assignment 1

    Revision History:
        Gonzalo Ramos Zúñiga, 2017.09.22: Created
 */

package ca.on.einfari.llh.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import ca.on.einfari.llh.R;
import ca.on.einfari.llh.adapters.ProjectsFragmentPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private ViewPager vpProjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vpProjects = findViewById(R.id.vpProjects);
        vpProjects.setAdapter(new ProjectsFragmentPagerAdapter(getSupportFragmentManager()));
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(vpProjects);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (vpProjects.getCurrentItem()) {
                    case 0:
                        Snackbar.make(view, "TODO: Open activity to build fencing quote...",
                                Snackbar.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Snackbar.make(view, "TODO: Open activity to build siding quote...",
                                Snackbar.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_search) {
            startActivity(new Intent(this, QuoteListActivity.class));
            return true;
        } else if (item.getItemId() == R.id.action_feed) {
            startActivity(new Intent(this, FeedListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}