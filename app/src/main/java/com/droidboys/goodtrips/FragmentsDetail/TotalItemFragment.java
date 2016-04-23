package com.droidboys.goodtrips.FragmentsDetail;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.droidboys.goodtrips.MeInterface;
import com.droidboys.goodtrips.Network.VolleySingleton;
import com.droidboys.goodtrips.Pojo.Feed;
import com.droidboys.goodtrips.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TotalItemFragment extends Fragment {


    private Context mContext;
    private Feed thisItem;
    private ImageLoader mImageLoader;

    public TotalItemFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=getContext();
        thisItem=((MeInterface)mContext).getItem();
        VolleySingleton volley=VolleySingleton.getInstance(mContext);
        mImageLoader=volley.getImageLoader();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View mView= inflater.inflate(R.layout.fragment_total_item, container, false);
        ((TextView)mView.findViewById(R.id.user_tv_fti)).setText(thisItem.getUserName());
        ((TextView)mView.findViewById(R.id.desc_tv_fti)).setText(thisItem.getDesc());
        ((TextView)mView.findViewById(R.id.city_name_tv_fti)).setText(thisItem.getPname());
        ((TextView)mView.findViewById(R.id.date_tv_fti)).setText("");
        mImageLoader.get(thisItem.getProf_pic(), new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                ((ImageView)mView.findViewById(R.id.prof_iv_fti)).setImageBitmap(response.getBitmap());
            }

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mImageLoader.get(thisItem.getImg(), new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                ((ImageView)mView.findViewById(R.id.feed_iv_fti)).setImageBitmap(response.getBitmap());
            }

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        return mView;

    }

}
