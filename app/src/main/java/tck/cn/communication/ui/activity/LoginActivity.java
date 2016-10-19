package tck.cn.communication.ui.activity;


import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.sdsmdg.tastytoast.TastyToast;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;
import tck.cn.communication.R;
import tck.cn.communication.base.BaseActivity;
import tck.cn.communication.present.LoginPresenter;
import tck.cn.communication.present.contact.LoginContract;
import tck.cn.communication.utils.SharedPreferencesUtil;

/**
 * 登录界面简单实现
 */
public class LoginActivity extends BaseActivity implements LoginContract.View, TextView.OnEditorActionListener {

    private static final int REQUEST_SDCARD = 1;

    @BindView(R.id.ed_username)
    EditText mEdUsername;
    @BindView(R.id.login_username)
    TextInputLayout mLoginUsername;
    @BindView(R.id.ed_password)
    EditText mEdPassword;
    @BindView(R.id.login_password)
    TextInputLayout mLoginPassword;
    @BindView(R.id.login)
    Button mLogin;
    @BindView(R.id.tv_newuser)
    TextView mTvNewuser;
    @BindView(R.id.activity_login)
    LinearLayout mActivityLogin;
    private LoginPresenter mLoginPresenter;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {
        //StatusBarUtil.setTranslucent(this, 0);
        mLoginPresenter = new LoginPresenter(this);
        /**
         * 数据回显
         */
        mEdUsername.setText(SharedPreferencesUtil.getUsername(this));
        mEdPassword.setText(SharedPreferencesUtil.getPassword(this));
        mEdPassword.setOnEditorActionListener(this);
    }

    @OnClick({R.id.login, R.id.tv_newuser})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                login();
                break;
            case R.id.tv_newuser:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }
    }

    /**
     * 登录事件的处理
     */
    public void login() {
        String username = mEdUsername.getText().toString().trim();
        String password = mEdPassword.getText().toString().trim();

        if (!Pattern.compile("[a-zA-Z]\\w{2,19}").matcher(username).matches()) {

            mLoginUsername.setErrorEnabled(true);
            mLoginUsername.setError("用户名不合法");
            mEdUsername.requestFocus(View.FOCUS_RIGHT);

            return;
        }
        if (!Pattern.compile("[0-9]{3,19}").matcher(password).matches()) {

            mLoginPassword.setErrorEnabled(true);
            mLoginPassword.setError("用户名不合法");
            mEdPassword.requestFocus(View.FOCUS_RIGHT);
            return;
        }

        /**
         * 1. 动态申请权限
         */
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PermissionChecker.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_SDCARD);
            return;
        }
        showLoadDialog();

        mLoginPresenter.login(username, password);
    }

    /**
     * 登录成功的逻辑事件的处理
     *
     * @param username
     * @param password
     * @param isSuccess
     */
    @Override
    public void loginSuccess(String username, String password, boolean isSuccess) {
        if (isSuccess) {
            // TastyToast.makeText(this, username + "登录成功", TastyToast.LENGTH_SHORT, TastyToast.INFO);
            SharedPreferencesUtil.saveUser(this, username, password);
            startActivity(new Intent(this, MainActivity.class));
        } else {
            TastyToast.makeText(this, "登录失败", TastyToast.LENGTH_LONG, TastyToast.ERROR);
        }
        hideLoadDialog();
    }

    /**
     * 键盘回车事件的处理
     *
     * @param v
     * @param actionId
     * @param event
     * @return
     */
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEND
                || actionId == EditorInfo.IME_ACTION_DONE
                || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
            login();
        }
        return false;
    }

    /**
     * 动态权限申请
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_SDCARD) {
            if (grantResults[0] == PermissionChecker.PERMISSION_GRANTED) {
                //被授权了
                login();
            } else {
                TastyToast.makeText(this, "没有给予该应用权限，不让你用了", TastyToast.LENGTH_SHORT, TastyToast.ERROR);

            }
        }
    }

    /**
     * 当再次startActivity的时候，接收新的Intent对象
     * 调用的前提是该启动模式是singleTask，或者singleTop但是他得在最上面才有效
     *
     * @param intent
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        mEdUsername.setText(SharedPreferencesUtil.getUsername(this));
        mEdPassword.setText(SharedPreferencesUtil.getPassword(this));
    }
}
