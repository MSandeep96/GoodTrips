package com.droidboys.goodtrips.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.droidboys.goodtrips.Network.VolleySingleton;
import com.droidboys.goodtrips.Pojo.InstaFeed;
import com.droidboys.goodtrips.R;

import java.util.ArrayList;

/**
 * Created by Sandeep on 24-Apr-16.
 */

public class InstaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context mContext;
    ArrayList<String> mFeed = new ArrayList<>();
    LayoutInflater mInflater;
    VolleySingleton mVolley;
    ImageLoader mImageLoader;
    final int INSTA_ITEM = 1;
    final int PROGRESS_ITEM = 2;

    public InstaAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mVolley = VolleySingleton.getInstance(context);
        mImageLoader = mVolley.getImageLoader();
    }

    @Override
    public int getItemViewType(int position) {
        if (position < mFeed.size()) {
            return INSTA_ITEM;
        } else if (position == mFeed.size()) {
            return PROGRESS_ITEM;
        }
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == INSTA_ITEM) {
            View thisView = mInflater.inflate(R.layout.insta_feed_layout, parent, false);
            return new InstaViewHolder(thisView);
        } else if (viewType == PROGRESS_ITEM) {
            LinearLayout mLinearLayout = new LinearLayout(mContext);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            mLinearLayout.setLayoutParams(params);
            mLinearLayout.setGravity(Gravity.CENTER_HORIZONTAL);
            ProgressBar mProgre = new ProgressBar(mContext);
            mLinearLayout.addView(mProgre);
            return new RecyclerView.ViewHolder(mLinearLayout) {
            };
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof InstaViewHolder) {
            mImageLoader.get(mFeed.get(position), new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                    ((InstaViewHolder) holder).mImage.setImageBitmap(response.getBitmap());
                }

                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (mFeed == null || mFeed.size() == 0) {
            return 0;
        }
        return mFeed.size() + 1;
    }

    public void addmFeed(ArrayList<String> feed) {
        int k = mFeed.size();
        mFeed.addAll(feed);
        notifyItemRangeChanged(k, feed.size());
    }

    static class InstaViewHolder extends RecyclerView.ViewHolder {
        ImageView mImage;

        public InstaViewHolder(View itemView) {
            super(itemView);
            mImage = (ImageView) itemView.findViewById(R.id.image_iv_in);
        }
    }
}