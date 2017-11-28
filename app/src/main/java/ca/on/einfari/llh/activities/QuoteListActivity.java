/*
    QuoteListActivity.java
    Assignment 2

    Revision History:
        Gonzalo Ramos Zúñiga, 2017.11.24: Created
 */

package ca.on.einfari.llh.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.List;
import java.util.concurrent.ExecutionException;

import ca.on.einfari.llh.R;
import ca.on.einfari.llh.adapters.QuotesRecyclerViewAdapter;
import ca.on.einfari.llh.data.LLHDatabase;
import ca.on.einfari.llh.data.Quote;

public class QuoteListActivity extends AppCompatActivity implements QuotesRecyclerViewAdapter.
        QuotesAdapterListener {

    private RecyclerView rvQuoteList;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        rvQuoteList = findViewById(R.id.rvQuoteList);
        rvQuoteList.setLayoutManager(new LinearLayoutManager(this));
        rvQuoteList.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.llh_divider));
        rvQuoteList.addItemDecoration(dividerItemDecoration);
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
        startActivity(new Intent(this, MaterialsListActivity.class).putExtra("id", quote.getId()));
    }

}