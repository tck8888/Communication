package tck.cn.communication.utils;

import android.content.Context;
import android.content.SharedPreferences;

import tck.cn.communication.app.Constant;

/**
 * Description :SharedPreferences工具类
 * <p>
 * Created by tck on 2016/10/17.
 */

public class SharedPreferencesUtil {

    private static final String KEY_CONFIG = "config";

    public static void saveUser(Context context, String username, String password) {
        getSharedPreferences(context).edit()
                .putString(Constant.SP_KEY_USERNAME, username)
                .putString(Constant.SP_KEY_PWD, password).commit();
    }

    public static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(KEY_CONFIG, Context.MODE_PRIVATE);
    }

    public static String getUsername(Context context) {
        return getSharedPreferences(context).getString(Constant.SP_KEY_USERNAME, "");
    }

    public static String getPassword(Context context) {
        return getSharedPreferences(context).getString(Constant.SP_KEY_PWD, "");
    }
}
