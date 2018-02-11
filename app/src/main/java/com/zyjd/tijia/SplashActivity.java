package com.zyjd.tijia;

import android.content.Intent;

import com.zyjd.tijia.activity.main.MainActivity;
import com.zyjd.tijia.api.ApiClient;
import com.zyjd.tijia.base.BaseActivity;
import com.zyjd.tijia.entity.Token;
import com.zyjd.tijia.util.SPUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class SplashActivity extends BaseActivity {

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
//        verifyUserToken();
//        getBannerResource();
    }

    private void verifyUserToken() {
        Token token = new Token(SPUtil.getString(this, "token", null));
        ApiClient.getApiService()
                .verifyToken(token)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new DisposableObserver<ResponseBody>() {
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        finish();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void getBannerResource() {

    }
}
