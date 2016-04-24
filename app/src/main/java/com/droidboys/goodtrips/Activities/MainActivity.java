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
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.droidboys.goodtrips.Fragments.AddItemFragment;
import com.droidboys.goodtrips.Fragments.FeedFragment;
import com.droidboys.goodtrips.Fragments.ProfileFragment;
import com.droidboys.goodtrips.Fragments.SearchFragment;
import com.droidboys.goodtrips.Fragments.WishPlacesFrag;
import com.droidboys.goodtrips.R;
import com.droidboys.goodtrips.Utils.PrefsManager;
import com.gigamole.library.NavigationTabBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private MyAdapter mPagerAdap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(PrefsManager.getLoggedIn(this)){
            Intent inte=new Intent(this,LoginActivity.class);
            startActivityForResult(inte,0012);
        }
        setSupportActionBar(toolbar);
        NavigationTabBar ntb=(NavigationTabBar)findViewById(R.id.ntb_cm);
        mViewPager=(ViewPager)findViewById(R.id.vp_cm);
        mViewPager.setOffscreenPageLimit(4);
        mPagerAdap=new MyAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdap);
        ntb.setViewPager(mViewPager);
        int arr[]=getResources().getIntArray(R.array.nav_color);
        int actColor=ContextCompat.getColor(this,R.color.colorFourth);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(new NavigationTabBar.Model(
                ContextCompat.getDrawable(this,R.drawable.home),arr[0],"Home"));
        models.add(new NavigationTabBar.Model(
                ContextCompat.getDrawable(this,R.drawable.search), arr[1],"Search"));
        models.add(new NavigationTabBar.Model(
                ContextCompat.getDrawable(this,R.drawable.add_item), arr[2],"Add"));
        models.add(new NavigationTabBar.Model(
                ContextCompat.getDrawable(this,R.drawable.wishlist),arr[3],"Wishes"));
        models.add(new NavigationTabBar.Model(
                ContextCompat.getDrawable(this,R.drawable.profile),arr[4],"Profile"));
        ntb.setModels(models);
        ntb.setActiveColor(ContextCompat.getColor(this,R.color.colorActiveBut));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class MyAdapter extends FragmentPagerAdapter{

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0: return new FeedFragment();
                case 1: return new SearchFragment();
                case 2: return new AddItemFragment();
                case 3: return new WishPlacesFrag();
                case 4: return new ProfileFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 5;
        }
    }
}
