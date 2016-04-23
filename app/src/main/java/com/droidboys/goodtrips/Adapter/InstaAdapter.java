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

public class InstaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Context mContext;
    ArrayList<InstaFeed> mFeed;
    LayoutInflater mInflater;
    VolleySingleton mVolley;
    ImageLoader mImageLoader;
    final int INSTA_ITEM=1;
    final int PROGRESS_ITEM=2;

    public InstaAdapter(Context context) {
        mContext=context;
        mInflater=LayoutInflater.from(context);
        mVolley=VolleySingleton.getInstance(context);
        mImageLoader=mVolley.getImageLoader();
    }

    public void setmFeed(ArrayList<InstaFeed> mFeed) {
        this.mFeed=mFeed;
        notifyItemRangeChanged(0,mFeed.size());
    }

    @Override
    public int getItemViewType(int position) {
        if(position<mFeed.size()){
            return INSTA_ITEM;
        }else if(position==mFeed.size()){
            return PROGRESS_ITEM;
        }
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==INSTA_ITEM) {
            View thisView = mInflater.inflate(R.layout.insta_feed_layout, parent, false);
            return new InstaViewHolder(thisView);
        }else if(viewType==PROGRESS_ITEM){
            LinearLayout mLinearLayout=new LinearLayout(mContext);
            ViewGroup.LayoutParams params=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            mLinearLayout.setLayoutParams(params);
            mLinearLayout.setGravity(Gravity.CENTER_HORIZONTAL);
            ProgressBar mProgre=new ProgressBar(mContext);
            mLinearLayout.addView(mProgre);
            return new RecyclerView.ViewHolder(mLinearLayout) {};
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof InstaViewHolder) {
            ((InstaViewHolder)holder).mUser.setText(mFeed.get(position).getUserName());
            ((InstaViewHolder)holder).mCaption.setText(mFeed.get(position).getCaption());
            String urlProfPic = mFeed.get(position).getProfPic();
            String urlImage = mFeed.get(position).getImageStandard();
            mImageLoader.get(urlProfPic, new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                    ((InstaViewHolder)holder).mProf.setImageBitmap(response.getBitmap());
                }

                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            mImageLoader.get(urlImage, new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                    ((InstaViewHolder)holder).mImage.setImageBitmap(response.getBitmap());
                }

                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(mFeed==null){
            return 0;
        }
        return mFeed.size()+1;
    }

    public void addmFeed(ArrayList<InstaFeed> feed) {
        if(mFeed!=null) {
            int k = mFeed.size();
            mFeed.addAll(feed);
            notifyItemRangeChanged(k, feed.size());
        }else {
            mFeed=feed;
            notifyItemRangeChanged(0,feed.size());
        }
    }

    static class InstaViewHolder extends RecyclerView.ViewHolder{
        ImageView mProf;
        TextView mUser;
        ImageView mImage;
        TextView mCaption;

        public InstaViewHolder(View itemView) {
            super(itemView);
            mProf=(ImageView)itemView.findViewById(R.id.prof_iv_in);
            mUser=(TextView)itemView.findViewById(R.id.username_tv_in);
            mImage=(ImageView)itemView.findViewById(R.id.image_iv_in);
            mCaption=(TextView)itemView.findViewById(R.id.caption_tv_in);
        }
    }
}