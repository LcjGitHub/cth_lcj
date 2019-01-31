package com.chetuhui.lcj.chezhubao_x.wxapi;








import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.activity.ConfirmActivity;
import com.chetuhui.lcj.chezhubao_x.activity.PaySuccessActivity;
import com.chetuhui.lcj.chezhubao_x.tool.Constants;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler{

	private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";

	private IWXAPI api;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.pay_result);

		api = WXAPIFactory.createWXAPI(this, Constants.WECHAT_APPID);
		api.handleIntent(getIntent(), this);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
		api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {
	}

	@Override
	public void onResp(BaseResp resp) {
		Log.d(">>>>>>>>>>>>>", "onPayFinish, errCode = " + resp.errCode);
		if(resp.errCode==0){
			Toast.makeText(this,"充值成功!",Toast.LENGTH_SHORT).show();
			startActivity(new Intent(WXPayEntryActivity.this,PaySuccessActivity.class));


		}else if(resp.errCode==-1){
			Toast.makeText(this,"充值失败!",Toast.LENGTH_SHORT).show();

		}else if(resp.errCode==-2){
			Toast.makeText(this,"取消充值!",Toast.LENGTH_SHORT).show();

		}
		finish();
//		if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
//			AlertDialog.Builder builder = new AlertDialog.Builder(this);
//			builder.setTitle("支付结果");
//			builder.setMessage(getString(R.string.pay_result_callback_msg, String.valueOf(resp.errCode)));
//			builder.show();
//		}
	}
}


