package com.droidboys.goodtrips.FragmentsDetail;


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
import com.android.volley.toolbox.JsonObjectRequest;
import com.droidboys.goodtrips.Adapter.FeedDetailedAdapter;
import com.droidboys.goodtrips.MeInterface;
import com.droidboys.goodtrips.Network.VolleySingleton;
import com.droidboys.goodtrips.Pojo.Feed;
import com.droidboys.goodtrips.Pojo.PlacesFeed;
import com.droidboys.goodtrips.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 */
public class RelatedFeeds extends Fragment {


    private FeedDetailedAdapter mAdapter;
    private Feed thisItem;
    RequestQueue mReq;
    public RelatedFeeds() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        VolleySingleton singleton=VolleySingleton.getInstance(getContext());
        mReq=singleton.getRequestQueue();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView=inflater.inflate(R.layout.fragment_related_feeds, container, false);
        RecyclerView mRecy=(RecyclerView)mView.findViewById(R.id.rv_frf);
        mRecy.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter=new FeedDetailedAdapter(getContext());
        thisItem=((MeInterface)getContext()).getItem();
        getItems(thisItem.getPlace_ID());
        return mView;
    }

    private void getItems(long place_id) {
        String places="http://goodtrips.herokuapp.com/api/v1/places/"+place_id+"/";
        JsonObjectRequest req=new JsonObjectRequest(Request.Method.GET, places, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response){
                try {
                    JSONArray mjson = response.getJSONArray("feedbacks");
                    Gson gson = new GsonBuilder().create();
                    PlacesFeed[] mFeed = gson.fromJson(mjson.toString(), PlacesFeed[].class);
                    mAdapter.addItems(new ArrayList<PlacesFeed>(Arrays.asList(mFeed)));
                }catch(JSONException e){

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mReq.add(req);
    }


}
