package com.sanilk.harmonia;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.sanilk.harmonia.home_activity_fragments.ActivityFragment;
import com.sanilk.harmonia.home_activity_fragments.HomeFragment;
import com.sanilk.harmonia.home_activity_fragments.LeaderboardFragment;
import com.sanilk.harmonia.home_activity_fragments.PlaylistFragment;
import com.sanilk.harmonia.home_activity_fragments.ProfileFragment;

public class HomeActivity extends FragmentActivity {
    private static final int NUM_PAGES=5;
    private ViewPager viewPager;
    private PagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        viewPager=(ViewPager)findViewById(R.id.pager);
        adapter=new ScreenSlideAdapter(this.getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
    }

    @Override
    public void onBackPressed() {
        if(viewPager.getCurrentItem()==2) {
            super.onBackPressed();
        }else{
            viewPager.setCurrentItem(2);
        }
    }

    private class ScreenSlideAdapter extends FragmentStatePagerAdapter{

        public ScreenSlideAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:return new ProfileFragment();
                case 1:return new PlaylistFragment();
                case 2:return new HomeFragment();
                case 3:return new LeaderboardFragment();
                case 4:return new ActivityFragment();
                default:throw new RuntimeException("Yeh kya ho gya??? Nagar palika ko bulao!");
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}

class ZoomOutPageTransformer implements ViewPager.PageTransformer{
    private static final float MIN_SCALE=0.85f;
    private static final float MIN_ALPHA=0.5f;

    @Override
    public void transformPage(@NonNull View page, float position) {
        int pageWidth = page.getWidth();
        int pageHeight = page.getHeight();

        if (position < -1) {
            page.setAlpha(0);

        } else if (position <= 1) {
            float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
            float vertMargin = pageHeight * (1 - scaleFactor) / 2;
            float horzMargin = pageWidth * (1 - scaleFactor) / 2;
            if (position < 0) {
                page.setTranslationX(horzMargin - vertMargin / 2);
            } else {
                page.setTranslationX(-horzMargin + vertMargin / 2);
            }

            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);

            page.setAlpha(MIN_ALPHA +
                    (scaleFactor - MIN_SCALE) /
                            (1 - MIN_SCALE) * (1 - MIN_ALPHA));

        } else {
            page.setAlpha(0);
        }
    }
}