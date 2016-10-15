package tck.cn.communication.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import tck.cn.communication.R;
import tck.cn.communication.utils.AnimationListenerAdpater;


/**
 * 闪屏界面的简单实现
 */
public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.splash)
    ImageView mSplash;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mUnbinder = ButterKnife.bind(this);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha_anim);
        animation.setDuration(2000);
        mSplash.startAnimation(animation);
        animation.setAnimationListener(new AnimationListenerAdpater() {
            @Override
            public void onAnimationEnd(Animation animation) {
                super.onAnimationEnd(animation);
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
