/*
    ListActivity.java
    Assignment 2

    Revision History:
        Gonzalo Ramos Zúñiga, 2017.11.24: Created
 */

package ca.on.einfari.llh.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.ExecutionException;

import ca.on.einfari.llh.R;
import ca.on.einfari.llh.adapters.QuotesRecyclerViewAdapter;
import ca.on.einfari.llh.data.LLHDatabase;
import ca.on.einfari.llh.data.Quote;

public class ListActivity extends AppCompatActivity implements QuotesRecyclerViewAdapter.
        QuotesAdapterListener {

    RecyclerView rvQuoteList;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        rvQuoteList = findViewById(R.id.rvQuoteList);
        rvQuoteList.setLayoutManager(new LinearLayoutManager(this));
        rvQuoteList.setItemAnimator(new DefaultItemAnimator());
        rvQuoteList.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.
                VERTICAL));
        try {
            List<Quote> quotes = new AsyncTask<Void, Void, List<Quote>>() {

                @Override
                protected List<Quote> doInBackground(Void... voids) {
                    return LLHDatabase.getDatabase(getApplicationContext()).quoteDao().readAll();
                }

            }.execute().get();
            adapter = new QuotesRecyclerViewAdapter(quotes, this);
            rvQuoteList.setAdapter(adapter);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onQuoteSelected(Quote quote) {
        Toast.makeText(getApplicationContext(), "Selected: " + quote.getId() + ", " + quote.
                getDescription() + ", " + quote.getEmail(), Toast.LENGTH_LONG).show();
    }

}