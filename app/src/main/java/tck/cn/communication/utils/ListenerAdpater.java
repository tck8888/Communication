package tck.cn.communication.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.animation.Animation;


/**
 * Description : 各种监听事件的适配器
 * <p>
 * Created by tck on 2016/10/15.
 */

public class ListenerAdpater implements Animation.AnimationListener, TextWatcher {
    /**
     * 以下三个方法是对于动画监听事件
     */
    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }


    /**
     * 以下三个方法是对于TextInputLayout的监听事件的处理
     */
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
