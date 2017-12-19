/*
    FeedsRecyclerViewAdapter.java
    Final Project

    Revision History:
        Gonzalo Ramos Zúñiga, 2017.12.18: Created
 */

package ca.on.einfari.llh.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ca.on.einfari.llh.R;
import ca.on.einfari.llh.data.Feed;

public class FeedsRecyclerViewAdapter extends RecyclerView.Adapter<FeedsRecyclerViewAdapter.
        ViewHolder> {

    private List<Feed> feeds;
    private FeedsAdapterListener feedsAdapterListener;

    public FeedsRecyclerViewAdapter(List<Feed> feeds, FeedsAdapterListener feedsAdapterListener) {
        this.feeds = feeds;
        this.feedsAdapterListener = feedsAdapterListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.
                listitem_feed, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.title.setText(feeds.get(i).getTitle());
        viewHolder.pubDate.setText(feeds.get(i).getPubDate());
    }

    @Override
    public int getItemCount() {
        return feeds.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView pubDate;

        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvTitle);
            pubDate = itemView.findViewById(R.id.tvPubDate);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    feedsAdapterListener.onFeedSelected(feeds.get(getAdapterPosition()));
                }
            });
        }
    }

    public interface FeedsAdapterListener {
        void onFeedSelected(Feed feed);
    }

}