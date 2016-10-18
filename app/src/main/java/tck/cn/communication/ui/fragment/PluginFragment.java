package tck.cn.communication.ui.fragment;

import android.app.Dialog;
import android.content.Context;
import android.media.DrmInitData;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.zhl.cbdialog.CBDialogBuilder;

import java.util.ArrayList;
import java.util.List;

import tck.cn.communication.R;
import tck.cn.communication.base.BaseFragment;
import tck.cn.communication.model.ItemInfo;
import tck.cn.communication.present.PluginPresenter;
import tck.cn.communication.present.contact.PluginContract;
import tck.cn.communication.ui.activity.adpater.PluginAdapter;
import tck.cn.communication.widget.DividerItemDecoration;

/**
 * Description :动态界面的简单实现
 * <p>
 * Created by tck on 2016/10/17.
 */

public class PluginFragment extends BaseFragment implements PluginContract.PluginView, PluginAdapter.OnItemClickListener {

    private RecyclerView mRecyclerView;
    private PluginPresenter mPluginPresenter;

    private static int[] ICONID = {R.mipmap.weather, R.mipmap.news, R.mipmap.avatar1, R.mipmap.avatar3};
    private static int[] TITLE = {R.string.weather, R.string.phonenumberlocation, R.string.joke, R.string.news};
    private List<ItemInfo> mItemInfoData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_plugin, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.plugin_recyclerview);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false));
        PluginAdapter adapter = new PluginAdapter(mItemInfoData);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));

        mPluginPresenter = new PluginPresenter(this);
    }

    @Override
    public void onLogout(String username, boolean success, String msg) {

    }

    /**
     * 退出按钮的点击事件的处理
     *
     * @param postion
     */
    @Override
    public void onItemClick(int postion) {
        switch (postion) {
            case 0:
                Toast.makeText(getContext(), "0", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(getContext(), "1", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(getContext(), "2", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(getContext(), "3", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                new CBDialogBuilder(getContext(), CBDialogBuilder.DIALOG_STYLE_NORMAL)
                        .setTouchOutSideCancelable(true)
                        .showCancelButton(true)
                        .setTitle("我  的  家")
                        .setMessage("亲爱的" + EMClient.getInstance().getCurrentUser() + ",你确定要离开我吗？")
                        .setConfirmButtonText("毫不犹豫")
                        .setCancelButtonText("稍后再说")
                        .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_SLID_BOTTOM)
                        .setDialoglocation(CBDialogBuilder.DIALOG_LOCATION_CENTER)
                        .setButtonClickListener(true, new CBDialogBuilder.onDialogbtnClickListener() {
                            @Override
                            public void onDialogbtnClick(Context context, Dialog dialog, int whichBtn) {
                                switch (whichBtn) {
                                    case BUTTON_CONFIRM:
                                        mPluginPresenter.logout();
                                        break;
                                    case BUTTON_CANCEL:
                                        break;
                                    default:
                                        break;
                                }
                            }
                        })
                        .create().show();
                break;
        }
    }


    /**
     * 初始话条目的基本信息
     */
    private void initData() {
        mItemInfoData = new ArrayList<>();
        for (int i = 0; i < ICONID.length; i++) {
            mItemInfoData.add(new ItemInfo(TITLE[i], ICONID[i]));
        }
    }


}
