package com.yunlong.samples.intent;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yunlong.samples.R;
import com.yunlong.samples.base.BaseActivity;
import com.yunlong.samples.utils.ToastUtils;

import butterknife.Bind;

/**
 * Created by shiyunlong on 2016/10/12.
 * 登录
 */
public class IntentLoginActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 该界面的隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.IntentLogin";

    /**
     * 账号
     */
    @Bind(R.id.et_account)
    public EditText etAccount;
    /**
     * 密码
     */
    @Bind(R.id.et_password)
    EditText etPassword;
    /**
     * 登录
     */
    @Bind(R.id.btn_login)
    Button btnLogin;

    /**
     * 默认账号
     */
    public static final String ACCOUNT = "admin";
    /**
     * 默认密码
     */
    public static final String PASSWORD = "password";
    /**
     * 意图的Action
     */
    public String intentAction;
    /**
     * 意图Bundle
     */
    public Bundle intentBundle;

    @Override
    protected int getResourceId() {
        return R.layout.a_intent_login;
    }

    @Override
    protected void initView() {
        if (btnLogin != null)
            btnLogin.setOnClickListener(this);
    }

    @Override
    protected void initTitleBar() {
        titleBar.setTitle(R.string.nav_title_intent_login);
        super.initTitleBar();
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            intentAction = intent.getStringExtra(BundleConfig.BUNDLE_ACTION_KEY);
            intentBundle = intent.getExtras();
        }
        etAccount.setText(ACCOUNT);
        etPassword.setText(PASSWORD);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                checkLogin();
                break;
        }
    }

    /**
     * 检查登录
     */
    private void checkLogin() {
        String account = String.valueOf(etAccount.getText());
        String password = String.valueOf(etPassword.getText());
        if (account.equals(ACCOUNT) && password.equals(PASSWORD)) {
            ToastUtils.show(mContext, R.string.a_login_success);
            checkIntentData();
            finish();
        } else {
            ToastUtils.show(mContext, R.string.a_login_fail);
        }
    }

    /**
     * 登录成功
     */
    private void checkIntentData() {
        if (TextUtils.isEmpty(intentAction))
            return;
        Intent intent = new Intent();
        intent.setAction(intentAction);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        if (intentBundle != null)
            intent.putExtras(intentBundle);
        startActivity(intent);
    }
}
