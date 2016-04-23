package com.droidboys.goodtrips.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.droidboys.goodtrips.Network.VolleySingleton;
import com.droidboys.goodtrips.Pojo.Feed;
import com.droidboys.goodtrips.R;

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
    public void onBindViewHolder(final FeedViewHolder holder, int position) {
        holder.userName.setText(mItems.get(position).getUserName());
        holder.desc.setText(mItems.get(position).getDesc());
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
       /* holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 23-Apr-16
            }
        });*/

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
        public FeedViewHolder(View itemView) {
            super(itemView);
            userName=(TextView)itemView.findViewById(R.id.user_tv_fil);
            prof=(ImageView)itemView.findViewById(R.id.prof_iv_fil);
            desc=(TextView)itemView.findViewById(R.id.desc_tv_fil);
            feedImage=(ImageView)itemView.findViewById(R.id.feed_iv_fil);
        }
    }
}
