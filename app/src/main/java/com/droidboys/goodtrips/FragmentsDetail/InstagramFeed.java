package com.droidboys.goodtrips.FragmentsDetail;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.droidboys.goodtrips.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InstagramFeed extends Fragment {


    public InstagramFeed() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_instagram_feed, container, false);
    }

}
