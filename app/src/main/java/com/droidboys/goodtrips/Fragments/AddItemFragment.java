package com.droidboys.goodtrips.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.droidboys.goodtrips.Network.VolleySingleton;
import com.droidboys.goodtrips.R;
import com.seatgeek.placesautocomplete.OnPlaceSelectedListener;
import com.seatgeek.placesautocomplete.PlacesApi;
import com.seatgeek.placesautocomplete.PlacesAutocompleteTextView;
import com.seatgeek.placesautocomplete.model.AutocompleteResultType;
import com.seatgeek.placesautocomplete.model.Place;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddItemFragment extends Fragment {

    Place present;
    VolleySingleton mVolley;
    RequestQueue mReqQue;
    int count;
    private ArrayAdapter<String> mAdapter;

    public AddItemFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mVolley=VolleySingleton.getInstance(getContext());
        mReqQue=mVolley.getRequestQueue();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView= inflater.inflate(R.layout.fragment_add_item, container, false);
        PlacesAutocompleteTextView mPlaceText=(PlacesAutocompleteTextView)mView.findViewById(R.id.places_fai);
        mPlaceText.setOnPlaceSelectedListener(new OnPlaceSelectedListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
            }
        });
        AutoCompleteTextView mEdit=(AutoCompleteTextView) mView.findViewById(R.id.autocomp_fai);
        mAdapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_expandable_list_item_1);
        mEdit.setAdapter(mAdapter);
        mEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count>=3 && (count-3)%2==0){
                    getWords(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        Button mSubmit=(Button)mView.findViewById(R.id.sumbit_btn_fai);
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Request mReq;
                // TODO: 24-Apr-16  
            }
        });
        return mView;
    }


    void getWords(String word){
        final ArrayList<String> mStrings=new ArrayList<>();
        String url="https://api.havenondemand.com/1/api/sync/autocomplete/v1?text="+word+"&apikey=37d4d689-f27c-4564-89b7-16b759ebf90a";
        JsonObjectRequest mReq=new JsonObjectRequest(JsonRequest.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray mArr = response.getJSONArray("words");
                    for (int x = 0; x < mArr.length(); x++) {
                        mStrings.add(mArr.getString(x));
                    }
                    mAdapter.addAll(mStrings);
                }catch (Exception e){

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mReqQue.add(mReq);
    }



}
