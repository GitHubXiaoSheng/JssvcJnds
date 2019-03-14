package com.app.trafficclient.login;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.app.trafficclient.MainActivity;
import com.app.trafficclient.R;
import com.app.trafficclient.util.HttpRequest;
import com.app.trafficclient.util.Shared;
import com.app.trafficclient.util.UrlBean;
import com.app.trafficclient.util.Util;

import org.json.JSONException;
import org.json.JSONObject;


public class LoginActivity extends Activity {

	private UrlBean urlBean;
	private String urlHttp;
	private String urlPort = "8088";
	
	EditText accountET, pwdET;
	Button loginBtn, regBtn,settingBtn;
	TextView tv_login;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();
		initLiserter();
	}

	private void initView() {
		// TODO Auto-generated method stub
		accountET = (EditText) findViewById(R.id.accountET);
		pwdET = (EditText) findViewById(R.id.pwdET);
		accountET.setText("user1");
		pwdET.setText("123456");
		loginBtn = (Button) findViewById(R.id.loginBtn);
		regBtn = (Button) findViewById(R.id.regBtn);
		settingBtn = (Button) findViewById(R.id.setting);
		tv_login = findViewById(R.id.tv_login);
		tv_login.requestFocus();
        urlBean = Util.loadSetting( LoginActivity.this );

	}
	
	private void initLiserter() {
		// TODO Auto-generated method stub
		regBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(LoginActivity.this,
						RegActivity.class);
				startActivity(intent);
			}
		});

		loginBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final String account = accountET.getText().toString();
				String pwd = pwdET.getText().toString();
//				startActivity(new Intent(LoginActivity.this, MainActivity.class));
//				finish();
				HttpRequest.post("user_login", "{\"UserName\":\"" + account + "\",\"UserPwd\": \"" + pwd + "\"}",
					new Response.Listener<JSONObject>() {
						@Override
						public void onResponse(JSONObject jsonObject) {
							String role = null;
							try {
							   role =  jsonObject.getString("UserRole");
							} catch (JSONException e) {
								e.printStackTrace();
							}
//                                //R01:普通用户，R02：一般管理员，R03：超级管理员
							Shared.edit(LoginActivity.this).putString("role",role).putString("username",account).apply();
							startActivity(new Intent(LoginActivity.this, MainActivity.class));
							finish();

						}
					},
					new Response.ErrorListener() {
						@Override
						public void onErrorResponse(VolleyError volleyError) {
							Toast.makeText(LoginActivity.this, "登录失败，用户名或密码错误", Toast.LENGTH_SHORT).show();
						}
					});

			}
		});

		settingBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final Dialog urlSettingDialog = new Dialog(LoginActivity.this);
				urlSettingDialog.show();
				urlSettingDialog.setTitle("Setting");
				urlSettingDialog.getWindow().setContentView(R.layout.login_setting);
				final EditText edit_urlHttp = (EditText) urlSettingDialog.getWindow().findViewById(R.id.edit_setting_url);
				final EditText edit_urlPort = (EditText) urlSettingDialog.getWindow().findViewById(R.id.edit_setting_port);

				edit_urlHttp.setText( urlBean.getUrl() );
				edit_urlPort.setText( urlBean.getPort());
				Button save = (Button) urlSettingDialog.getWindow().findViewById(R.id.save);
				Button cancel = (Button) urlSettingDialog.getWindow().findViewById(R.id.cancel);
				save.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						urlHttp = edit_urlHttp.getText().toString();
						urlPort = edit_urlPort.getText().toString();

						if ( urlHttp == null || urlHttp.equals("")   ) {
							Toast.makeText(LoginActivity.this,R.string.error_ip, Toast.LENGTH_LONG).show();
						} else {
							Util.saveSetting(urlHttp,urlPort,LoginActivity.this);
                            urlBean = Util.loadSetting( LoginActivity.this );
							urlSettingDialog.dismiss();
						}
					}
				});
				cancel.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						urlSettingDialog.dismiss();
					}
				});
				urlSettingDialog.show();
	
			}
		});
	
	}

}
