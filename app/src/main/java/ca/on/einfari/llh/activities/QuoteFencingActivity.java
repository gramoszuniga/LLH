/*
    QuoteFencingActivity.java
    Final Project

    Revision History:
        Gonzalo Ramos Zúñiga, 2017.12.27: Created
 */

package ca.on.einfari.llh.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import ca.on.einfari.llh.R;
import ca.on.einfari.llh.data.LLHDatabase;
import ca.on.einfari.llh.data.MaterialsList;
import ca.on.einfari.llh.data.Quote;
import ca.on.einfari.llh.preferences.LLHSharedPreferences;
import ca.on.einfari.llh.utils.LLHConstants;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class QuoteFencingActivity extends AppCompatActivity {

    EditText txtDescription;
    EditText txtLength;
    RadioButton rad4x4;
    RadioButton rad6x6;
    CheckBox chkPostCaps;
    CheckBox chkLattice;
    EditText txtEmail;
    Button btnCreate;
    Quote quote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_fencing);
        txtDescription = findViewById(R.id.txtDescription);
        txtLength = findViewById(R.id.txtLength);
        rad4x4 = findViewById(R.id.rad4x4);
        rad6x6 = findViewById(R.id.rad6x6);
        chkPostCaps = findViewById(R.id.chkPostCaps);
        chkLattice = findViewById(R.id.chkLattice);
        txtEmail = findViewById(R.id.txtEmail);
        btnCreate = findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                create();
            }
        });
        rad4x4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rad4x4.setFocusableInTouchMode(false);
                rad4x4.setError(null);
            }
        });
        rad6x6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rad4x4.setFocusableInTouchMode(false);
                rad4x4.setError(null);
            }
        });
    }

    private void create() {
        txtDescription.setError(null);
        txtEmail.setError(null);
        txtLength.setError(null);
        rad4x4.setError(null);
        View focusedView;

        if (TextUtils.isEmpty(txtDescription.getText().toString().trim())) {
            txtDescription.setError("Quote description is required.");
            focusedView = txtDescription;
            focusedView.requestFocus();
            return;
        } else if (TextUtils.isEmpty(txtLength.getText().toString().trim())) {
            txtLength.setError("Fence length in feet is required.");
            focusedView = txtLength;
            focusedView.requestFocus();
            return;
        } else if (!rad4x4.isChecked() && !rad6x6.isChecked()) {
            rad4x4.setFocusableInTouchMode(true);
            rad4x4.setError("Posts size is required.");
            focusedView = rad4x4;
            focusedView.requestFocus();
            return;
        } else if (TextUtils.isEmpty(txtEmail.getText().toString().trim())) {
            txtEmail.setError("E-mail is required.");
            focusedView = txtEmail;
            focusedView.requestFocus();
            return;
        }

        quote = new Quote(txtEmail.getText().toString(), txtDescription.getText().toString());
        new quoteFence().execute();
    }

    private class quoteFence extends AsyncTask<Void, Void, Long> {

        @Override
        protected Long doInBackground(Void... voids) {
            long id = LLHDatabase.getDatabase(getApplicationContext()).quoteDao().create(quote);
            int sections = (int) Math.ceil((Double.parseDouble(txtLength.getText().toString()) /
                    LLHConstants.FENCE_SECTION_DIVISOR));
            int posts = sections + 1;
            if (chkLattice.isChecked()) {
                LLHDatabase.getDatabase(getApplicationContext()).materialsListDao().create(new
                        MaterialsList((int) id, 1, sections * LLHConstants.
                        FENCE_BOARDS_FACTOR));
            } else {
                LLHDatabase.getDatabase(getApplicationContext()).materialsListDao().create(new
                        MaterialsList((int) id, 2, sections * LLHConstants.
                        FENCE_BOARDS_FACTOR));
            }
            if (rad4x4.isChecked()) {
                LLHDatabase.getDatabase(getApplicationContext()).materialsListDao().create(new
                        MaterialsList((int) id, 3, sections * LLHConstants.TWO_BY_FOURS_FACTOR));
                if (chkLattice.isChecked()) {
                    LLHDatabase.getDatabase(getApplicationContext()).materialsListDao().create(new
                            MaterialsList((int) id, 4, sections * LLHConstants.
                            TWO_BY_FOURS_FACTOR));
                }
                LLHDatabase.getDatabase(getApplicationContext()).materialsListDao().create(new
                        MaterialsList((int) id, 6, posts));
                if (chkPostCaps.isChecked()) {
                    LLHDatabase.getDatabase(getApplicationContext()).materialsListDao().create(new
                            MaterialsList((int) id, 8, posts));
                }
            } else {
                if (chkLattice.isChecked()) {
                    LLHDatabase.getDatabase(getApplicationContext()).materialsListDao().create(new
                            MaterialsList((int) id, 5, sections * LLHConstants.
                            TWO_BY_SIXES_LATTICE_FACTOR));
                } else {
                    LLHDatabase.getDatabase(getApplicationContext()).materialsListDao().create(new
                            MaterialsList((int) id, 5, sections * LLHConstants.
                            TWO_BY_SIXES_FACTOR));
                }
                LLHDatabase.getDatabase(getApplicationContext()).materialsListDao().create(new
                        MaterialsList((int) id, 7, posts));
                if (chkPostCaps.isChecked()) {
                    LLHDatabase.getDatabase(getApplicationContext()).materialsListDao().create(new
                            MaterialsList((int) id, 9, posts));
                }
            }
            if (chkLattice.isChecked()) {
                LLHDatabase.getDatabase(getApplicationContext()).materialsListDao().create(new
                        MaterialsList((int) id, 10, sections));
            }
            if (rad4x4.isChecked()) {
                LLHDatabase.getDatabase(getApplicationContext()).materialsListDao().create(new
                        MaterialsList((int) id, 11, sections * LLHConstants.FENCE_BRACKETS_FACTOR));
                LLHDatabase.getDatabase(getApplicationContext()).materialsListDao().create(new
                        MaterialsList((int) id, 12, (int) Math.ceil(posts / LLHConstants.
                        BUILDING_FORMS_DIVISOR)));
            } else {
                LLHDatabase.getDatabase(getApplicationContext()).materialsListDao().create(new
                        MaterialsList((int) id, 13, (int) Math.ceil(posts / LLHConstants.
                        BUILDING_FORMS_DIVISOR)));
            }
            LLHDatabase.getDatabase(getApplicationContext()).materialsListDao().create(new
                    MaterialsList((int) id, 14, posts * LLHConstants.CONCRETE_FACTOR));
            LLHDatabase.getDatabase(getApplicationContext()).materialsListDao().create(new
                    MaterialsList((int) id, 15, 1));
            return id;
        }

        @Override
        protected void onPostExecute(Long id) {
            new triggerNotification().execute();
            startActivity(new Intent(getApplicationContext(), MaterialsListActivity.class).
                    putExtra("id", id.intValue()));
        }
    }

    private class triggerNotification extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                OkHttpClient client = new OkHttpClient();
                JSONObject dataJson = new JSONObject();
                dataJson.put("title", "LLH");
                dataJson.put("body", "Kudos! You have just created a quote.");
                JSONObject json = new JSONObject();
                json.put("notification", dataJson);
                json.put("to", LLHSharedPreferences.getSharedPreferences(
                        getApplicationContext()).getRegistrationToken());
                RequestBody requestBody = RequestBody.create(LLHConstants.JSON, json.toString());
                Request request = new Request.Builder()
                        .header("Authorization", "key=" + LLHConstants.
                                FCN_LEGACY_SERVER_KEY).url(LLHConstants.FCM_API).post(requestBody).
                                build();
                client.newCall(request).execute();
            } catch (JSONException err) {
                err.printStackTrace();
            } catch (IOException err) {
                err.printStackTrace();
            }
            return null;
        }

    }

}