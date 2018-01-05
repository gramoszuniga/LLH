/*
    MaterialsListActivity.java
    Assignment 2

    Revision History:
        Gonzalo Ramos Zúñiga, 2017.11.26: Created
 */

package ca.on.einfari.llh.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.ExecutionException;

import ca.on.einfari.llh.R;
import ca.on.einfari.llh.adapters.MaterialsRecyclerViewAdapter;
import ca.on.einfari.llh.data.LLHDatabase;
import ca.on.einfari.llh.data.MaterialsListWithProduct;
import ca.on.einfari.llh.data.Quote;

public class MaterialsListActivity extends AppCompatActivity {

    private RecyclerView rvMaterialsList;
    private RecyclerView.Adapter adapter;
    private List<MaterialsListWithProduct> materialsList;
    private Quote quote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        rvMaterialsList = findViewById(R.id.rvMaterialsList);
        rvMaterialsList.setLayoutManager(new LinearLayoutManager(this));
        rvMaterialsList.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.llh_divider));
        rvMaterialsList.addItemDecoration(dividerItemDecoration);
        try {
            materialsList = new AsyncTask<Void, Void, List<MaterialsListWithProduct>>() {

                @Override
                protected List<MaterialsListWithProduct> doInBackground(Void... voids) {
                    return LLHDatabase.getDatabase(getApplicationContext()).
                            materialsListWithProductDao().readAll(getIntent().getIntExtra("id", 0));
                }

            }.execute().get();
            adapter = new MaterialsRecyclerViewAdapter(materialsList);
            rvMaterialsList.setAdapter(adapter);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SendEmail().execute();
            }
        });
    }

    private class SendEmail extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {
            quote = LLHDatabase.getDatabase(getApplicationContext()).quoteDao().read(
                    getIntent().getIntExtra("id", 0));
            return quote != null;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                String to[] = {quote.getEmail()};
                intent.putExtra(android.content.Intent.EXTRA_EMAIL, to);
                intent.putExtra(android.content.Intent.EXTRA_SUBJECT, quote.getDescription());
                intent.setType("plain/text");
                intent.putExtra(android.content.Intent.EXTRA_TEXT, writeBody(materialsList));
                startActivity(intent);
            } else {
                Toast.makeText(MaterialsListActivity.this, "Something went wrong. Try again later.",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    private String writeBody(List<MaterialsListWithProduct> materialsList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (MaterialsListWithProduct material : materialsList) {
            stringBuilder.append(material.product.get(0).getDescription());
            stringBuilder.append(" Qty: ");
            stringBuilder.append(material.materialsList.getQuantity());
            stringBuilder.append(" ");
            stringBuilder.append(material.product.get(0).getUnit());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
	
}