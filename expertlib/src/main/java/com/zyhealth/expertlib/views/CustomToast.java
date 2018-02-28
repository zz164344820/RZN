package com.zyhealth.expertlib.views;


import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zyhealth.expertlib.R;


public class CustomToast extends Toast {

	private TextView textView;
	private String text;
	private View view;
	private int layout;
	private int time;
	private Context context;

	
	public CustomToast(Context context, String text, int time) {
		super(context);
		this.context = context;
		this.text = text;
		this.time = time;
		init();
	}

	public CustomToast(Context context, int layout, int time) {
		super(context);
		this.context = context;
		this.layout = layout;
		this.time = time;
		init2();
	}
	
	private void init() {
		view = View.inflate(context, R.layout.view_custom_toast, null);
		setView(view);
		textView = (TextView) view.findViewById(R.id.textView);
		textView.setText(text);
		setGravity(Gravity.CENTER, 0, 0);
		setDuration(time); 
	}

	private void init2() {
		setView(View.inflate(context, layout, null));
		setGravity(Gravity.CENTER, 0, 0);
		setDuration(time);
	}

}
