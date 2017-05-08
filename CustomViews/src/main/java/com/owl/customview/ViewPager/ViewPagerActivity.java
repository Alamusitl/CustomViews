package com.owl.customview.ViewPager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.owl.customview.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends Activity {

    private List<View> mViewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        NonScrollViewPaper mViewPager = (NonScrollViewPaper) findViewById(R.id.id_non_scroll_view_paper);
        final ImageView mView0 = new ImageView(this);
        WebView mView1 = new WebView(this);
        setSettings(mView1);
        mView1.loadUrl("http://192.168.158.114:3001/views/video.html");
        WebView mView2 = new WebView(this);
        setSettings(mView2);
        mView2.loadUrl("http://192.168.158.114:3001/views/index.html");
        mView0.setImageResource(R.mipmap.ic_launcher);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        mView0.setLayoutParams(lp);
        mView1.setLayoutParams(lp);
        mView2.setLayoutParams(lp);
        mViewList = new ArrayList<>();
        mViewList.add(mView0);
        mViewList.add(mView1);
        mViewList.add(mView2);
        PagerAdapter mAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return mViewList.size();
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                if (position == 1) {
                    ((WebView) mViewList.get(position)).loadUrl("javascript:videoPlay()");
                }
                container.addView(mViewList.get(position));
                return position;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mViewList.get(position));
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == mViewList.get(Integer.parseInt(object.toString()));
            }
        };
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);
    }

    private void setSettings(WebView view) {
        view.setWebViewClient(new WebViewClient() {
            @SuppressWarnings("deprecation")
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d("ViewPager", "LandingView shouldOverrideUrlLoading: " + url);
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.d("ViewPager", "LandingView onPageFinished: " + url);
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
                Log.d("ViewPager", url);
            }

        });
        view.setWebChromeClient(new WebChromeClient() {
        });
        WebSettings settings = view.getSettings();
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setAppCacheEnabled(true);
        settings.setJavaScriptEnabled(true);
        settings.setDefaultTextEncodingName("UTF-8");
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setAllowFileAccess(true);
        view.setHorizontalScrollBarEnabled(false);//水平不显示
        view.setVerticalScrollBarEnabled(false); //垂直不显示
    }
}
