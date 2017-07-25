package com.example.android.itwasnews;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ImageView;

import com.example.android.itwasnews.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding binding;
    private CategoryAdapter categoryAdapter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(binding.toolbarMain.toolbar);
        context = binding.getRoot().getContext();
        categoryAdapter = new CategoryAdapter(getSupportFragmentManager());
        binding.viewpager.setAdapter(categoryAdapter);
        binding.viewpager.addOnPageChangeListener(new PageChangeListener());
        binding.tabs.setupWithViewPager(binding.viewpager);
        decorateTabStyle();
        binding.toolbarMain.toolbarSubtitle.setText(QueryUtils.getSubtitle());
    }

    private class PageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            changeColorOfTabDivider(context, position);

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    /**
     * Paints the tabs of the viewpager and inserts icons
     */
    private void decorateTabStyle() {
        ImageView tab1 = (ImageView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tab1.setBackgroundColor(ContextCompat.getColor(context, R.color.color_google));
        tab1.setImageResource(R.drawable.google);
        binding.tabs.getTabAt(CategoryAdapter.CATEGORY_GOOGLE_INDEX).setCustomView(tab1);

        ImageView tab2 = (ImageView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tab2.setBackgroundColor(ContextCompat.getColor(context, R.color.color_apple));
        tab2.setImageResource(R.drawable.apple);
        binding.tabs.getTabAt(CategoryAdapter.CATEGORY_APPLE_INDEX).setCustomView(tab2);

        ImageView tab3 = (ImageView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tab3.setBackgroundColor(ContextCompat.getColor(context, R.color.color_microsoft));
        tab3.setImageResource(R.drawable.microsoft);
        binding.tabs.getTabAt(CategoryAdapter.CATEGORY_MICROSOFT_INDEX).setCustomView(tab3);

        ImageView tab4 = (ImageView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tab4.setBackgroundColor(ContextCompat.getColor(context, R.color.color_facebook));
        tab4.setImageResource(R.drawable.facebook);
        binding.tabs.getTabAt(CategoryAdapter.CATEGORY_FACEBOOK_INDEX).setCustomView(tab4);

        ImageView tab5 = (ImageView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tab5.setBackgroundColor(ContextCompat.getColor(context, R.color.color_oracle));
        tab5.setImageResource(R.drawable.oracle);
        binding.tabs.getTabAt(CategoryAdapter.CATEGORY_ORACLE_INDEX).setCustomView(tab5);
    }

    /**
     * When the tab is changed, change the color of the bar under the tab
     * @param context
     * @param position of the tab
     */
    private void changeColorOfTabDivider(Context context, int position) {
        ImageView tabDivider = binding.tabDivider;
        switch (position) {
            case CategoryAdapter.CATEGORY_GOOGLE_INDEX:
                tabDivider.setBackgroundColor(ContextCompat.getColor(context, R.color.color_google));
                break;
            case CategoryAdapter.CATEGORY_APPLE_INDEX:
                tabDivider.setBackgroundColor(ContextCompat.getColor(context, R.color.color_apple));
                break;
            case CategoryAdapter.CATEGORY_MICROSOFT_INDEX:
                tabDivider.setBackgroundColor(ContextCompat.getColor(context, R.color.color_microsoft));
                break;
            case CategoryAdapter.CATEGORY_FACEBOOK_INDEX:
                tabDivider.setBackgroundColor(ContextCompat.getColor(context, R.color.color_facebook));
                break;
            case CategoryAdapter.CATEGORY_ORACLE_INDEX:
                tabDivider.setBackgroundColor(ContextCompat.getColor(context, R.color.color_oracle));
                break;
        }
    }
}
