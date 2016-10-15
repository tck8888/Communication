package tck.cn.communication.ui.activity;


import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import tck.cn.communication.R;
import tck.cn.communication.base.BaseActivity;

public class LoginActivity extends BaseActivity {

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

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {
        String username = mEdUsername.getText().toString().trim();
        String password = mEdPassword.getText().toString().toString().toString();
    }

    @OnClick({R.id.login, R.id.tv_newuser})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.tv_newuser:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }
    }
}
