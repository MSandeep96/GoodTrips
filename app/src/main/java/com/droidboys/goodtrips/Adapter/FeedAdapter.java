package com.droidboys.goodtrips.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Sandeep on 23-Apr-16.
 */
public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedViewHolder> {
    @Override
    public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(FeedViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class FeedViewHolder extends RecyclerView.ViewHolder{

        public FeedViewHolder(View itemView) {
            super(itemView);
        }
    }
}
