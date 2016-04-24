package com.droidboys.goodtrips.Adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.droidboys.goodtrips.Network.VolleySingleton;
import com.droidboys.goodtrips.Pojo.PlacesFeed;
import com.droidboys.goodtrips.R;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Sandeep on 24-Apr-16.
 */
public class FeedDetailedAdapter extends RecyclerView.Adapter<FeedDetailedAdapter.FeedDetailViewHolder> {
    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private ArrayList<PlacesFeed> mItems=new ArrayList<>();
    private ImageLoader imgLoader;

    public FeedDetailedAdapter(Context context) {
        mContext=context;
        mLayoutInflater= LayoutInflater.from(context);
        imgLoader= VolleySingleton.getInstance(context).getImageLoader();
    }

    @Override
    public FeedDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView=mLayoutInflater.inflate(R.layout.detail_places_layout,parent,false);
        return new FeedDetailViewHolder(mView);
    }

    public void addItems(ArrayList<PlacesFeed> items){
        int i=mItems.size();
        mItems.addAll(items);
        notifyItemRangeChanged(i,items.size());
    }

    @Override
    public void onBindViewHolder(final FeedDetailViewHolder holder, int position) {
        holder.mUser.setText(mItems.get(position).getName());
        holder.mDesc.setText(mItems.get(position).getDesc());
        holder.date.setText("");
        imgLoader.get(mItems.get(position).getProf(), new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                holder.mProf.setImageBitmap(response.getBitmap());
            }

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        if(mItems.get(position).isMood()){
            holder.mCardView.setCardBackgroundColor(ContextCompat.getColor(mContext,R.color.colorLightBlue));
            holder.mfb.setFavorite(true);
        }else{
            holder.mCardView.setCardBackgroundColor(ContextCompat.getColor(mContext,R.color.colorLightRed));
            holder.mfb.setFavorite(false);
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class FeedDetailViewHolder extends RecyclerView.ViewHolder{
        TextView mUser;
        TextView mDesc;
        TextView date;
        CircleImageView mProf;
        CardView mCardView;
        MaterialFavoriteButton mfb;
        public FeedDetailViewHolder(View itemView) {
            super(itemView);
            mUser=(TextView)itemView.findViewById(R.id.tv_dpl);
            mDesc=(TextView)itemView.findViewById(R.id.desc_tv_dpl);
            date=(TextView)itemView.findViewById(R.id.time_tv_dpl);
            mProf=(CircleImageView)itemView.findViewById(R.id.civ_dpl);
            mfb=(MaterialFavoriteButton)itemView.findViewById(R.id.mfb_dpl);
            mCardView=(CardView)itemView.findViewById(R.id.cv_dpl);
        }
    }
}
