package com.droidboys.goodtrips.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.droidboys.goodtrips.Activities.DetailActivity;
import com.droidboys.goodtrips.Network.VolleySingleton;
import com.droidboys.goodtrips.Pojo.Feed;
import com.droidboys.goodtrips.R;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;

import java.util.ArrayList;

/**
 * Created by Sandeep on 23-Apr-16.
 */
public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedViewHolder> {
    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private ArrayList<Feed> mItems=new ArrayList<>();
    private ImageLoader mImageLoader;

    public FeedAdapter(Context context) {
        mContext=context;
        mLayoutInflater= LayoutInflater.from(context);
        VolleySingleton mVolley= VolleySingleton.getInstance(mContext);
        mImageLoader=mVolley.getImageLoader();
    }

    public void addItems(ArrayList<Feed> items){
        int count=mItems.size();
        mItems.addAll(items);
        notifyItemRangeChanged(count,items.size());
    }

    @Override
    public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView=mLayoutInflater.inflate(R.layout.feed_item_layout,parent,false);
        return new FeedViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final FeedViewHolder holder, final int position) {
        holder.userName.setText(mItems.get(position).getUserName());
        holder.desc.setText(mItems.get(position).getDesc());
        holder.cityName.setText(mItems.get(position).getPname());
        mImageLoader.get(mItems.get(position).getProf_pic(), new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                holder.prof.setImageBitmap(response.getBitmap());
            }

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mImageLoader.get(mItems.get(position).getImg(), new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                holder.feedImage.setImageBitmap(response.getBitmap());
            }

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        if(mItems.get(position).isMood()) {
            holder.mfb.setFavorite(true);
            holder.mCv.setCardBackgroundColor(ContextCompat.getColor(mContext,R.color.colorLightBlue));
        }else{
            holder.mfb.setFavorite(false);
            holder.mCv.setCardBackgroundColor(ContextCompat.getColor(mContext,R.color.colorLightRed));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent=new Intent(mContext, DetailActivity.class);
                mIntent.putExtra("extraDetail",mItems.get(position));
                mContext.startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public static class FeedViewHolder extends RecyclerView.ViewHolder{
        TextView userName;
        ImageView prof;
        TextView desc;
        ImageView feedImage;
        TextView cityName;
        MaterialFavoriteButton mfb;
        CardView mCv;
        public FeedViewHolder(View itemView) {
            super(itemView);
            mCv=(CardView)itemView.findViewById(R.id.cv_fil);
            userName=(TextView)itemView.findViewById(R.id.user_tv_fil);
            prof=(ImageView)itemView.findViewById(R.id.prof_iv_fil);
            desc=(TextView)itemView.findViewById(R.id.desc_tv_fil);
            feedImage=(ImageView)itemView.findViewById(R.id.feed_iv_fil);
            cityName=(TextView)itemView.findViewById(R.id.city_name_tv_fil);
            mfb=(MaterialFavoriteButton)itemView.findViewById(R.id.mfb_fil);
        }
    }
}
