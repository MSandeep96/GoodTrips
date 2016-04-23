package com.droidboys.goodtrips.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.droidboys.goodtrips.Adapter.FeedAdapter;
import com.droidboys.goodtrips.Network.VolleySingleton;
import com.droidboys.goodtrips.Pojo.Feed;
import com.droidboys.goodtrips.R;
import com.droidboys.goodtrips.Utils.ConstantsProj;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedFragment extends Fragment {


    private Context mContext;
    private FeedAdapter mAdapter;
    private VolleySingleton mVolley;
    private RequestQueue mQueue;

    public FeedFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mVolley=VolleySingleton.getInstance(getContext());
        mQueue=mVolley.getRequestQueue();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mContext=getContext();
        View mView= inflater.inflate(R.layout.fragment_feed, container, false);
        RecyclerView mRecy=(RecyclerView)mView.findViewById(R.id.rv_ff);
        mRecy.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter=new FeedAdapter(mContext);
        mRecy.setAdapter(mAdapter);
        getFeed(ConstantsProj.BASE_URL);
        return mView;
    }

    private void getFeed(String url) {
        JsonArrayRequest mReq=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Gson gson=new GsonBuilder().create();
                Feed[] items=gson.fromJson(response.toString(),Feed[].class);
                mAdapter.addItems(new ArrayList<>(Arrays.asList(items)));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: 23-Apr-16 Handle error codes
            }
        });
        mQueue.add(mReq);
    }

}
