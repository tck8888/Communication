package tck.cn.communication.ui.activity;


import android.content.Intent;
import android.provider.ContactsContract;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sdsmdg.tastytoast.TastyToast;

import butterknife.BindView;
import butterknife.OnClick;
import tck.cn.communication.R;
import tck.cn.communication.base.BaseActivity;
import tck.cn.communication.present.LoginPresenter;
import tck.cn.communication.present.contact.LoginContract;
import tck.cn.communication.utils.SharedPreferencesUtil;

public class LoginActivity extends BaseActivity implements LoginContract.View {

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
        mLoginPresenter = new LoginPresenter(this);
        /**
         * 数据回显
         */
        mEdUsername.setText(SharedPreferencesUtil.getUsername(this));
        mEdPassword.setText(SharedPreferencesUtil.getPassword(this));
    }

    @OnClick({R.id.login, R.id.tv_newuser})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                login();
                //startActivity(new Intent(this, MainActivity.class));
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

        mLoginPresenter.login(username, password);
    }

    @Override
    public void loginSuccess(String username, String password, boolean isSuccess) {
        if (isSuccess) {
            TastyToast.makeText(this, username +"登录成功" + password, TastyToast.LENGTH_LONG, TastyToast.INFO);
        } else {
            TastyToast.makeText(this, "登录失败", TastyToast.LENGTH_LONG, TastyToast.ERROR);

        }
    }
}
