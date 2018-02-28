package com.rzn.commonbaselib.views;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.style.Circle;
import com.rzn.commonbaselib.R;


/**
 * @Description:自定义对话框
 * @author
 */
public class CustomProgressDialog extends ProgressDialog {

	private  SpinKitView spin_kit;

	/*带动效的加载*/
	Circle circle;

	public CustomProgressDialog(Context context, String content, boolean flag) {
		super(context);
		setCanceledOnTouchOutside(flag);
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();

	}


	public void setContent(String str) {
		//mLoadingTv.setText(str);
	}

	private void initView() {
		setContentView(R.layout.frameprogress_dialog);
		spin_kit = (SpinKitView) findViewById(R.id.spin_kit);

		Window dialogWindow = this.getWindow();
		dialogWindow.setBackgroundDrawable(null);
		WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		dialogWindow.setGravity(Gravity.CENTER);
		/*lp.x = 100; // 新位置X坐标
		lp.y = 100; // 新位置Y坐标*/
		/*lp.width = SizeUtils.dp2px(100); // 宽度
		lp.height = SizeUtils.dp2px(100); // 高度*/
		//lp.alpha = 0.4f; // 透明度
		dialogWindow.setAttributes(lp);
		startProgressLoading();
	}

	@Override
	public void dismiss() {
		super.dismiss();
		stopProgressLoading();
	}

	private void stopProgressLoading() {
		if(circle!=null){
			circle.stop();
		}

	}

	private void startProgressLoading() {
		circle = new Circle();
		spin_kit.setIndeterminateDrawable(circle);
		circle.start();
	}

}
