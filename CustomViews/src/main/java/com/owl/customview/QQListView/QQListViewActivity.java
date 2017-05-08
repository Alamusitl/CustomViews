package com.owl.customview.QQListView;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.owl.customview.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QQListViewActivity extends ListActivity {

    private QQListView mListView;
    private ArrayAdapter<String> mAdapter;
    private List<String> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qq_listview);

        mListView = (QQListView) findViewById(android.R.id.list);
        mData = new ArrayList<>(Arrays.asList("HelloWorld", "Welcome", "Java", "Android", "Servlet", "Struts",
                "Hibernate", "Spring", "HTML5", "Javascript", "Lucene"));
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mData);
        mListView.setAdapter(mAdapter);

        mListView.setDelButtonClickListener(new QQListView.DelButtonClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(QQListViewActivity.this, position + " : " + mAdapter.getItem(position), Toast.LENGTH_SHORT).show();
                mAdapter.remove(mAdapter.getItem(position));
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(QQListViewActivity.this, i + " : " + mAdapter.getItem(i), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
