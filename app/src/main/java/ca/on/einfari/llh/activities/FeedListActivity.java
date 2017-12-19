/*
    FeedActivity.java
    Final Project

    Revision History:
        Gonzalo Ramos Zúñiga, 2017.12.17: Created
 */

package ca.on.einfari.llh.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Xml;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import ca.on.einfari.llh.R;
import ca.on.einfari.llh.adapters.FeedsRecyclerViewAdapter;
import ca.on.einfari.llh.data.Feed;

public class FeedListActivity extends AppCompatActivity implements FeedsRecyclerViewAdapter.
        FeedsAdapterListener {

    private SwipeRefreshLayout srlFeedList;
    private RecyclerView rvFeedList;
    private List<Feed> feeds;
    private final String FEED_URL = "http://blog.homedepot.com/feed/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        srlFeedList = findViewById(R.id.srlFeedList);
        srlFeedList.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new FetchFeed().execute();
            }
        });
        rvFeedList = findViewById(R.id.rvFeedList);
        rvFeedList.setLayoutManager(new LinearLayoutManager(this));
        rvFeedList.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.llh_divider));
        rvFeedList.addItemDecoration(dividerItemDecoration);
        new FetchFeed().execute();
    }

    @Override
    public void onFeedSelected(Feed feed) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(feed.getLink())));
    }

    private class FetchFeed extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            srlFeedList.setRefreshing(true);
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            try {
                URL url = new URL(FEED_URL);
                InputStream inputStream = url.openConnection().getInputStream();
                feeds = parseFeed(inputStream);
                return true;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            srlFeedList.setRefreshing(false);
            if (result) {
                rvFeedList.setAdapter(new FeedsRecyclerViewAdapter(feeds, FeedListActivity.this));
            } else {
                Toast.makeText(FeedListActivity.this, "Something went wrong. Request took too " +
                        "long.", Toast.LENGTH_LONG).show();
            }
        }

        private List<Feed> parseFeed(InputStream inputStream) throws XmlPullParserException,
                IOException {
            boolean isItem = false;
            List<Feed> feeds = new ArrayList<>();
            String title = null;
            String link = null;
            String pubDate = null;

            XmlPullParser xmlPullParser = Xml.newPullParser();
            xmlPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            xmlPullParser.setInput(inputStream, null);
            xmlPullParser.nextTag();

            while (xmlPullParser.next() != XmlPullParser.END_DOCUMENT) {
                String name = xmlPullParser.getName();
                int eventType = xmlPullParser.getEventType();

                if (name == null) {
                    continue;
                }

                if (eventType == XmlPullParser.END_TAG) {
                    if (name.equalsIgnoreCase("item")) {
                        isItem = false;
                    }
                    continue;
                }

                if (eventType == XmlPullParser.START_TAG) {
                    if (name.equalsIgnoreCase("item")) {
                        isItem = true;
                        continue;
                    }
                }

                String result = "";
                if (xmlPullParser.next() == XmlPullParser.TEXT) {
                    result = xmlPullParser.getText();
                    xmlPullParser.nextTag();
                }

                if (name.equalsIgnoreCase("title")) {
                    title = result;
                } else if (name.equalsIgnoreCase("link")) {
                    link = result;
                } else if (name.equalsIgnoreCase("pubDate")) {
                    pubDate = result;
                }

                if (title != null && link != null && pubDate != null) {
                    if (isItem) {
                        Feed feed = new Feed(title, link, pubDate);
                        feeds.add(feed);
                    }

                    title = null;
                    link = null;
                    pubDate = null;
                    isItem = false;
                }
            }

            inputStream.close();
            return feeds;
        }

    }

}