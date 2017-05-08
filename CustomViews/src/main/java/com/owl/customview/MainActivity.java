package com.owl.customview;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.owl.customview.ArrowWaveDownload.ArrowWaveDownloadActivity;
import com.owl.customview.DownloadingView.DownloadingViewActivity;
import com.owl.customview.PanelView.PanelViewActivity;
import com.owl.customview.PopUpView.PopUpActivity;
import com.owl.customview.ProgressBarDownload.ProgressBarDownloadActivity;
import com.owl.customview.QQListView.QQListViewActivity;
import com.owl.customview.Vector.VectorActivity;
import com.owl.customview.ViewPager.ViewPagerActivity;
import com.owl.customview.WaveView.WaveViewActivity;

/**
 * Created by Alamusi on 2016/9/21.
 */
public class MainActivity extends ListActivity {

    private String[] mActivityNames = {"DownloadingViewActivity", "PanelViewActivity", "PopUpActivity",
            "QQListViewActivity", "ViewPagerActivity", "WaveViewActivity", "ArrowWaveDownloadActivity",
            "ProgressBarDownloadActivity", "VectorActivity"};
    private Class[] mActivityClasses = {DownloadingViewActivity.class, PanelViewActivity.class,
            PopUpActivity.class, QQListViewActivity.class, ViewPagerActivity.class,
            WaveViewActivity.class, ArrowWaveDownloadActivity.class, ProgressBarDownloadActivity.class,
            VectorActivity.class};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayAdapter<String> mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mActivityNames);

        setListAdapter(mAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(this, mActivityClasses[position]);
        startActivity(intent);
    }

}
