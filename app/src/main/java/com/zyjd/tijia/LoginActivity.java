package com.zyjd.tijia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.zyjd.tijia.activity.main.MainActivity;
import com.zyjd.tijia.api.ApiClient;
import com.zyjd.tijia.base.BaseActivity;
import com.zyjd.tijia.entity.Login;
import com.zyjd.tijia.entity.Token;
import com.zyjd.tijia.util.SPUtil;
import com.zyjd.tijia.util.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.et_username)
    EditText et_username;
    @BindView(R.id.et_password)
    EditText et_password;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        // 自动填充用户名
        String username = SPUtil.getString(LoginActivity.this, "username", null);
        if (username != null) {
            et_username.setText(username);
            et_password.requestFocus();
        }

    }

    @OnClick(R.id.tv_login)
    void onLoginClick() {
        final Login loginInfo = new Login();
        if(et_username.getText().toString().equals("") || et_password.getText().toString().equals("")){
            new ToastUtil().show(LoginActivity.this, "请输入用户名或密码");
            return;
        }
        loginInfo.setUsername(et_username.getText().toString());
        loginInfo.setPassword(et_password.getText().toString());
        ApiClient.getApiService(this)
                .login(loginInfo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Token>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Token token) {
                        // 登陆成功，存储用户名 token，下次登陆记住用户名
                        SPUtil.putString(LoginActivity.this, "username", loginInfo.getUsername());
                        SPUtil.putString(LoginActivity.this, "token", token.getToken());
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        new ToastUtil().show(LoginActivity.this, "用户名或密码错误");

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
