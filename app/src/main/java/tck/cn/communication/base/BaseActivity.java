package tck.cn.communication.base;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.jaeger.library.StatusBarUtil;
import com.zhl.cbdialog.CBDialogBuilder;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Description :基础Activity
 * <p>
 * Created by tck on 2016/10/15.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected Unbinder mBind;

    protected Dialog mDialog;

    protected abstract int getLayout();

    protected abstract void initData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayout());
        mBind = ButterKnife.bind(this);
        /**
         * 设置状态栏的透明度
         */
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
           window.setNavigationBarColor(Color.TRANSPARENT);
        }
        mDialog = LoadDialog();
        initData();

    }

    /**
     * 显示进度加载框
     */
    public void showLoadDialog() {
        mDialog.show();
    }

    /**
     * 隐藏进度加载框
     */
    public void hideLoadDialog() {
        mDialog.dismiss();
    }

    public Dialog LoadDialog() {
        return new CBDialogBuilder(this, CBDialogBuilder.DIALOG_STYLE_PROGRESS_AVLOADING)
                .setTouchOutSideCancelable(false)
                .showCancelButton(true)
                .setMessage("正在加载请稍后...")
                .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_SLID_BOTTOM)
                .setProgressIndicatorColor(0xaa198675)
                .setProgressIndicator(CBDialogBuilder.INDICATOR_BallPulseRise)
                .create();
    }


    /**
     * 隐藏键盘
     */
    public void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
    }
}
