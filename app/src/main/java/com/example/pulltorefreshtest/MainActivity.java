package com.example.pulltorefreshtest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.pulltorefreshtest.RefreshableView.PullToRefreshListener;

/**
 * https://blog.csdn.net/u010142437/article/details/52982265
 */
public class MainActivity extends Activity {
	Button btn_auto_pull;
	RefreshableView refreshableView;
	ListView listView;
	ArrayAdapter<String> adapter;
	String[] items = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		refreshableView = (RefreshableView) findViewById(R.id.refreshable_view);
		btn_auto_pull = (Button) findViewById(R.id.btn_auto_pull);

		btn_auto_pull.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				refreshableView.setAutoRefresh();
			}
		});

		listView = (ListView) findViewById(R.id.list_view);
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
		listView.setAdapter(adapter);
		refreshableView.setOnRefreshListener(new PullToRefreshListener() {
			@Override
			public void onRefresh() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				refreshableView.finishRefreshing();
			}
		}, 0);
	}

}
