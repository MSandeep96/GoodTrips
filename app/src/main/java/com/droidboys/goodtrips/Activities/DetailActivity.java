package com.droidboys.goodtrips.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.droidboys.goodtrips.FragmentsDetail.InstagramFeed;
import com.droidboys.goodtrips.FragmentsDetail.RelatedFeeds;
import com.droidboys.goodtrips.FragmentsDetail.TotalItemFragment;
import com.droidboys.goodtrips.FragmentsDetail.WikipediaFragment;
import com.droidboys.goodtrips.MeInterface;
import com.droidboys.goodtrips.Pojo.Feed;
import com.droidboys.goodtrips.R;
import com.gigamole.library.NavigationTabBar;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity implements MeInterface{

    Feed thisItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        thisItem=getIntent().getParcelableExtra("extraDetail");
        NavigationTabBar ntb=(NavigationTabBar)findViewById(R.id.ntb_cd);
        int actColor= ContextCompat.getColor(this,R.color.colorFourth);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(new NavigationTabBar.Model(
                ContextCompat.getDrawable(this,R.drawable.home), actColor,"Feed"));
        models.add(new NavigationTabBar.Model(
                ContextCompat.getDrawable(this,R.drawable.search), actColor,"Search"));
        models.add(new NavigationTabBar.Model(
                ContextCompat.getDrawable(this,R.drawable.add_item), actColor,"Add"));
        models.add(new NavigationTabBar.Model(
                ContextCompat.getDrawable(this,R.drawable.wishlist),actColor,"Wishlist"));
        ntb.setModels(models);
        ntb.setActiveColor(ContextCompat.getColor(this,R.color.colorActiveBut));
        ViewPager mViewPager=(ViewPager)findViewById(R.id.vp_cd);
        mViewPager.setAdapter(new FragmentPager(getSupportFragmentManager()));
        mViewPager.setOffscreenPageLimit(3);
        ntb.setViewPager(mViewPager);
    }

    @Override
    public Feed getItem() {
        return thisItem;
    }

    public class FragmentPager extends FragmentPagerAdapter{

        public FragmentPager(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:return new TotalItemFragment();
                case 1:return new RelatedFeeds();
                case 2:return new InstagramFeed();
                case 3:return new WikipediaFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}
