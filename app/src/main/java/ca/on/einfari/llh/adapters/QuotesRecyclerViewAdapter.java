/*
    QuotesRecyclerViewAdapter.java
    Assignment 2

    Revision History:
        Gonzalo Ramos Zúñiga, 2017.11.25: Created
 */

package ca.on.einfari.llh.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ca.on.einfari.llh.R;
import ca.on.einfari.llh.data.Quote;

public class QuotesRecyclerViewAdapter extends RecyclerView.Adapter<QuotesRecyclerViewAdapter.
        ViewHolder> {

    private List<Quote> quotes;
    private QuotesAdapterListener quotesAdapterListener;

    public QuotesRecyclerViewAdapter(List<Quote> quotes, QuotesAdapterListener
            quotesAdapterListener) {
        this.quotes = quotes;
        this.quotesAdapterListener = quotesAdapterListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.
                listitem_quote, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.description.setText(quotes.get(i).getDescription());
    }

    @Override
    public int getItemCount() {
        return quotes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView description;

        ViewHolder(View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.tvDescription);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    quotesAdapterListener.onQuoteSelected(quotes.get(getAdapterPosition()));
                }
            });
        }
    }

    public interface QuotesAdapterListener {
        void onQuoteSelected(Quote quote);
    }

}