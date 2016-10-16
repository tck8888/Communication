package tck.cn.communication.ui.activity;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sdsmdg.tastytoast.TastyToast;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;
import tck.cn.communication.R;
import tck.cn.communication.base.BaseActivity;
import tck.cn.communication.present.RegisterPresenter;
import tck.cn.communication.present.contact.RegisterContract;
import tck.cn.communication.utils.ListenerAdpater;
import tck.cn.communication.utils.SharedPreferencesUtil;

/**
 * 注册界面
 */
public class RegisterActivity extends BaseActivity implements RegisterContract.View {


    @BindView(R.id.ed_register_username)
    EditText mEdRegisterUsername;

    @BindView(R.id.ed_register_password)
    EditText mEdRegisterPassword;

    @BindView(R.id.register)
    Button mRegister;
    @BindView(R.id.register_input_username)
    TextInputLayout mRegisterInputUsername;
    @BindView(R.id.register_input_password)
    TextInputLayout mRegisterInputPassword;
    @BindView(R.id.activity_register)
    LinearLayout mActivityRegister;
    private RegisterPresenter mRegisterPresenter;

    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initData() {

        mRegisterPresenter = new RegisterPresenter(this);

        mRegisterInputUsername.getEditText().addTextChangedListener(new ListenerAdpater() {
            @Override
            public void afterTextChanged(Editable s) {

                if (Pattern.compile("[0-9]+").matcher(s.toString()).matches()) {
                    mRegisterInputUsername.setErrorEnabled(true);
                    mRegisterInputUsername.setError("用户名必须以字母开头");
                } else if (s.length() < 3 || s.length() > 19) {
                    mRegisterInputUsername.setErrorEnabled(true);
                    mRegisterInputUsername.setError("用户名必须为3~19位");
                } else {
                    mRegisterInputUsername.setErrorEnabled(false);
                }
            }

        });

        mRegisterInputUsername.getEditText().setOnEditorActionListener(new TextView.OnEditorActionListener() {//
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {//
                //当actionId == XX_SEND 或者 XX_DONE时都触发
                //或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发
                //注意，这是一定要判断event != null。因为在某些输入法上会返回null。
                if (actionId == EditorInfo.IME_ACTION_SEND
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                    register();
                }
                return false;
            }
        });

        mRegisterInputPassword.getEditText().addTextChangedListener(new ListenerAdpater() {
            @Override
            public void afterTextChanged(Editable s) {
                if (!Pattern.compile("[0-9]+").matcher(s).matches()) {
                    mRegisterInputPassword.setErrorEnabled(true);
                    mRegisterInputPassword.setError("必须是数字组合");

                } else if (s.length() < 3 || s.length() > 19) {
                    mRegisterInputPassword.setErrorEnabled(true);
                    mRegisterInputPassword.setError("密码必须为3~19位");
                } else {
                    mRegisterInputPassword.setErrorEnabled(false);
                }
            }
        });

        mRegisterInputPassword.getEditText().setOnEditorActionListener(new TextView.OnEditorActionListener() {//
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {//
                if (actionId == EditorInfo.IME_ACTION_SEND
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                    register();
                }
                return false;
            }
        });
    }

    /**
     * 注册事件的处理
     */
    public void register() {
        hideKeyboard();

        String username = mRegisterInputUsername.getEditText().getText().toString().trim();
        String password = mRegisterInputPassword.getEditText().getText().toString().trim();

        if (!Pattern.compile("[a-zA-Z]\\w{2,19}").matcher(username).matches()) {
            return;
        }
        if (!Pattern.compile("[0-9]{3,19}").matcher(password).matches()) {
            return;
        }

        showLoadDialog();
        mRegisterPresenter.register(username, password);
    }


    @Override
    public void registerSuccess(String username, String password, boolean isSuccess) {
        if (isSuccess) {
            TastyToast.makeText(this, "注册成功", TastyToast.LENGTH_LONG, TastyToast.INFO);

            SharedPreferencesUtil.saveUser(this, username, password);
            startActivity(new Intent(this, LoginActivity.class));
        } else {
            TastyToast.makeText(this, "注册失败", TastyToast.LENGTH_LONG, TastyToast.ERROR);
        }
        hideLoadDialog();
    }

    @Override
    public void showProgress() {
        showLoadDialog();
    }

    @Override
    public void hideProgress() {
        hideLoadDialog();
    }

    /**
     * 点击事件的处理
     *
     * @param view
     */
    @OnClick({R.id.register, R.id.activity_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register:
                register();
                break;
            case R.id.activity_register:
                hideKeyboard();
                break;
        }
    }
}


