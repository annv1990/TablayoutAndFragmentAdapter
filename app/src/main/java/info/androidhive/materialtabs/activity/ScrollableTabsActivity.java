package info.androidhive.materialtabs.activity;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import info.androidhive.materialtabs.R;
import info.androidhive.materialtabs.fragments.EightFragment;
import info.androidhive.materialtabs.fragments.FiveFragment;
import info.androidhive.materialtabs.fragments.FourFragment;
import info.androidhive.materialtabs.fragments.NineFragment;
import info.androidhive.materialtabs.fragments.OneFragment;
import info.androidhive.materialtabs.fragments.SevenFragment;
import info.androidhive.materialtabs.fragments.SixFragment;
import info.androidhive.materialtabs.fragments.TenFragment;
import info.androidhive.materialtabs.fragments.ThreeFragment;
import info.androidhive.materialtabs.fragments.TwoFragment;

public class ScrollableTabsActivity extends AppCompatActivity {

//    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager mViewPager;
//    private int[] layouts;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int mCouponTotal;
    ViewPagerAdapter mViewPagerAdapter;
    ViewStatePagerAdapter mViewStatePagerAdapter;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrollable_tabs);
        dotsLayout = findViewById(R.id.layoutDots);

        btnDelete = findViewById(R.id.btnDelete);
        mViewPager = findViewById(R.id.viewpager);
//        setupViewPager(mViewPager);
        setupViewPager1();
        mCouponTotal = 10;
//        addBottomDots(0);

        addDots();
        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        LinearLayout linearLayout = (LinearLayout) tabLayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(Color.parseColor("#edeeef"));
        drawable.setSize(10, 1);
        linearLayout.setDividerPadding(10);
        linearLayout.setDividerDrawable(drawable);
        btnDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int position  = tabLayout.getSelectedTabPosition();
                mViewPager.removeAllViews();
                mViewStatePagerAdapter.deletePage(position);
            }
        });
    }

    private void setupViewPager1() {
        mViewStatePagerAdapter = new ViewStatePagerAdapter(getSupportFragmentManager());
        mViewStatePagerAdapter.addFrag(new OneFragment(), "ONE");
        mViewStatePagerAdapter.addFrag(new TwoFragment(), "TWO");
        mViewStatePagerAdapter.addFrag(new ThreeFragment(), "THREE HREE HREE");
        mViewStatePagerAdapter.addFrag(new FourFragment(), "FOUR");
        mViewStatePagerAdapter.addFrag(new FiveFragment(), "FIVE");
        mViewStatePagerAdapter.addFrag(new SixFragment(), "SIX");
        mViewStatePagerAdapter.addFrag(new SevenFragment(), "SEVEN");
        mViewStatePagerAdapter.addFrag(new EightFragment(), "EIGHT");
        mViewStatePagerAdapter.addFrag(new NineFragment(), "NINE");
        mViewStatePagerAdapter.addFrag(new TenFragment(), "TEN");
        mViewPager.setAdapter(mViewStatePagerAdapter);
        mViewPager.addOnPageChangeListener(mOnPageChangeListener);
    }


    ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            addBottomDots(position);
            highlightDots(position);
        }


        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

        // Delete a page at a `position`
        public void deletePage(int position)
        {
            // Remove the corresponding item in the data set
            mFragmentList.remove(position);
            mFragmentTitleList.remove(position);
            // Notify the adapter that the data set is changed
            notifyDataSetChanged();
        }

    }

    class ViewStatePagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewStatePagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

        // Delete a page at a `position`
        public void deletePage(int position)
        {
            // Remove the corresponding item in the data set
            mFragmentList.remove(position);
            mFragmentTitleList.remove(position);
            // Notify the adapter that the data set is changed
            notifyDataSetChanged();
        }

        @Override
        public int getItemPosition(@NonNull Object object) {
            return PagerAdapter.POSITION_NONE;
        }
    }

    private void addBottomDots(int currentPage) {
        mCouponTotal = 10;
        dots = new TextView[mCouponTotal];

//        int[] colorsActive = new int[mCouponTotal];
//        int[] colorsInactive = new int[mCouponTotal];
        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);
        /*for(int i = 0 ; i < mCouponTotal; i ++){
            colorsActive[i] = R.color.dot_light_screen;
            colorsInactive[i] = R.color.dot_dark_screen;
        }*/

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            //add 。。。
            dots[i].setText(Html.fromHtml("&#8226;", Html.FROM_HTML_MODE_LEGACY));
            dots[i].setTextSize(25);
            dots[i].setTextColor(colorsInactive[i]);
            dots[i].setPadding(3,0,3,0);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    private void addDots(){
        dots = new TextView[mCouponTotal];
        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);
        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            //add 。。。
            dots[i].setText(Html.fromHtml("&#8226;", Html.FROM_HTML_MODE_LEGACY));
            dots[i].setTextSize(25);
            dots[i].setTextColor(colorsInactive[i]);
            dots[i].setPadding(3,0,3,0);
            dotsLayout.addView(dots[i]);
        }
    }

    private void highlightDots(int currentPage){
        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);
        dots[currentPage].setTextColor(colorsActive[currentPage]);
        for (int i = 0; i < dots.length; i++) {
            if(i != currentPage)
                dots[i].setTextColor(colorsInactive[i]);
        }
    }
}
