/*
    MaterialsListActivity.java
    Assignment 2

    Revision History:
        Gonzalo Ramos Zúñiga, 2017.11.26: Created
 */

package ca.on.einfari.llh.activities;

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
import ca.on.einfari.llh.adapters.MaterialsRecyclerViewAdapter;
import ca.on.einfari.llh.data.LLHDatabase;
import ca.on.einfari.llh.data.MaterialsListWithProduct;

public class MaterialsListActivity extends AppCompatActivity {

    private RecyclerView rvMaterialsList;
    private RecyclerView.Adapter adapter;

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
            List<MaterialsListWithProduct> materialsList = new AsyncTask<Void, Void, List<MaterialsListWithProduct>>() {

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
    }

}