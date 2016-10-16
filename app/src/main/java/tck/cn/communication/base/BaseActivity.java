package tck.cn.communication.base;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
        StatusBarUtil.setTranslucent(this, 0);
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
