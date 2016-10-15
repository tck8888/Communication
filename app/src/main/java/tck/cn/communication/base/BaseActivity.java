package tck.cn.communication.base;


import android.media.DrmInitData;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.bmob.v3.Bmob;

/**
 * Description :
 * <p>
 * Created by tck on 2016/10/15.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder mBind;

    protected abstract int getLayout();
    protected abstract void initData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //默认初始化:后端云服务器

        setContentView(getLayout());
        mBind = ButterKnife.bind(this);
        initData();
        Bmob.initialize(this, "c529a9978542db5f09f932c4a7bc966a");

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
    }
}
