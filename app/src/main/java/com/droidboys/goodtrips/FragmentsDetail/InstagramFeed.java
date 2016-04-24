package com.droidboys.goodtrips.FragmentsDetail;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.droidboys.goodtrips.Adapter.InstaAdapter;
import com.droidboys.goodtrips.MeInterface;
import com.droidboys.goodtrips.Network.VolleySingleton;
import com.droidboys.goodtrips.Pojo.Feed;
import com.droidboys.goodtrips.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class InstagramFeed extends Fragment {

    Feed thisItem;
    private Context mContext;
    private InstaAdapter mAdapter;
    private String nextUrl;
    private RequestQueue mReqQue;
    private int visibleItemCount;
    private LinearLayoutManager mLineMan;
    private int totalItemCount;
    private int pastVisiblesItems;
    private boolean loading;
    private ProgressBar mProgressBar;

    public InstagramFeed() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=getContext();
        thisItem=((MeInterface)mContext).getItem();
        VolleySingleton volley=VolleySingleton.getInstance(mContext);
        mReqQue=volley.getRequestQueue();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView= inflater.inflate(R.layout.fragment_instagram_feed, container, false);
        RecyclerView mRecyclerView=(RecyclerView)mView.findViewById(R.id.rv_fif);
        mProgressBar=(ProgressBar)mView.findViewById(R.id.prog_bar_fif);
        mLineMan=new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLineMan);
        mAdapter=new InstaAdapter(mContext);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy>0){
                    //scrolled down
                    visibleItemCount=mLineMan.getChildCount();
                    totalItemCount=mLineMan.getItemCount();
                    pastVisiblesItems=mLineMan.findFirstVisibleItemPosition();
                    if((visibleItemCount+pastVisiblesItems)>=totalItemCount){
                        if(!loading) {
                            makeRequest(nextUrl);
                            loading = true;
                        }
                    }
                }
            }
        });
        loadUrl(thisItem.getPlace_ID());
        return mView;
    }

    private void makeRequest(String nxt) {
        JsonObjectRequest mJsonObj=new JsonObjectRequest(Request.Method.GET, nxt, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    nextUrl = response.getJSONObject("pagination").getString("next_url");
                    JSONArray mImgs = response.getJSONArray("data");
                    ArrayList<String> mImgUrls = new ArrayList<>();
                    for (int i = 0; i < mImgs.length(); i++) {
                        mImgUrls.add(mImgs.getJSONObject(i).getJSONObject("images").getJSONObject("standard_resolution").getString("url"));
                    }
                    mAdapter.addmFeed(mImgUrls);
                }catch (Exception e){

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mReqQue.add(mJsonObj);
    }

    private void loadUrl(long place_id) {
        String base_url="https://goodtrips.herokuapp.com/insta/?place_id="+place_id;
        JsonObjectRequest mJsonObj=new JsonObjectRequest(Request.Method.GET, base_url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if(response.length()==0)
                    return;
                mProgressBar.setVisibility(View.GONE);
                try {
                    nextUrl = response.getJSONObject("pagination").getString("next_url");
                    JSONArray mImgs = response.getJSONArray("" +
                            "data");
                    ArrayList<String> mImgUrls = new ArrayList<>();
                    for (int i = 0; i < mImgs.length(); i++) {
                        mImgUrls.add(mImgs.getJSONObject(i).getJSONObject("images").getJSONObject("standard_resolution").getString("url"));
                    }
                    mAdapter.addmFeed(mImgUrls);
                }catch (Exception e){

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mReqQue.add(mJsonObj);
    }


}
