package tck.cn.communication.ui.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sdsmdg.tastytoast.TastyToast;
import com.zhl.cbdialog.CBDialogBuilder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import tck.cn.communication.R;
import tck.cn.communication.base.BaseFragment;
import tck.cn.communication.model.OnContactUpdate;
import tck.cn.communication.present.ContactPresenter;
import tck.cn.communication.present.contact.ContactContract;
import tck.cn.communication.ui.activity.ChatActivity;
import tck.cn.communication.ui.activity.MainActivity;
import tck.cn.communication.ui.activity.adpater.ContactAdpater;
import tck.cn.communication.widget.ContactLayout;

/**
 * Description :联系人界面
 * <p>
 * Created by tck on 2016/10/17.
 */

public class ContractFragment extends BaseFragment implements ContactContract.ContactView, SwipeRefreshLayout.OnRefreshListener, ContactAdpater.OnItemLongClickListener, ContactAdpater.OnItemShortClickListener {

    private ContactLayout mContactLayout;
    private ContactPresenter mContactPresenter;
    private ContactAdpater mContactAdpater;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contact, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContactLayout = (ContactLayout) view.findViewById(R.id.contactlayout);
        mContactPresenter = new ContactPresenter(this);

        mContactPresenter.initContacts();
        mContactLayout.setOnRefreshListener(this);

        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(OnContactUpdate onContactUpdate) {
        mContactPresenter.updateContacts();
    }

    @Override
    public void onInitContacts(List<String> contacts) {
        mContactAdpater = new ContactAdpater(contacts);
        mContactLayout.setAdapter(mContactAdpater);
        mContactAdpater.setOnItemLongClickListener(this);
        mContactAdpater.setOnItemShortClickListener(this);
    }

    @Override
    public void updateContacts(boolean success, String msg) {
        mContactAdpater.notifyDataSetChanged();
        //隐藏下拉刷新
        mContactLayout.setRefreshing(false);
    }

    @Override
    public void onDelete(String contact, boolean success, String msg) {
        if (success) {
            TastyToast.makeText(getActivity(), "友尽", TastyToast.LENGTH_LONG, TastyToast.INFO);
        } else {
            TastyToast.makeText(getActivity(), "删除失败，要不再续前缘？", TastyToast.LENGTH_LONG, TastyToast.WARNING);
        }
    }

    @Override
    public void onRefresh() {
        /**
         * 1.访问网络
         * 2.拿到数据，更新数据库
         * 3.更新UI
         * 4.隐藏下拉刷新
         */
        mContactPresenter.updateContacts();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 长按事件的处理----删除好友功能
     *
     * @param contact
     * @param position
     */
    @Override
    public void onItemLongClick(final String contact, int position) {

        new CBDialogBuilder(getContext(), CBDialogBuilder.DIALOG_STYLE_NORMAL)
                .setTouchOutSideCancelable(true)
                .showCancelButton(true)
                .setTitle("断绝关系")
                .setMessage("您和" + contact + "确定友尽了吗？")
                .setConfirmButtonText("恩断义绝")
                .setCancelButtonText("容我想想")
                .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_SLID_BOTTOM)
                .setDialoglocation(CBDialogBuilder.DIALOG_LOCATION_CENTER)
                .setButtonClickListener(true, new CBDialogBuilder.onDialogbtnClickListener() {
                    @Override
                    public void onDialogbtnClick(Context context, Dialog dialog, int whichBtn) {
                        switch (whichBtn) {
                            case BUTTON_CONFIRM:
                                mContactPresenter.deleteContact(contact);
                                break;
                            case BUTTON_CANCEL:
                                break;
                            default:
                                break;
                        }
                    }
                })
                .create().show();
    }

    /**
     * 点击事件的处理----进入聊天界面
     *
     * @param contact
     * @param position
     */
    @Override
    public void onItemShortClick(String contact, int position) {
        MainActivity activity = (MainActivity) getActivity();
        activity.startActivity(ChatActivity.class,false,contact);
    }
}
