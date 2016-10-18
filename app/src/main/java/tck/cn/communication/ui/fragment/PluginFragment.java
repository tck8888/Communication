package tck.cn.communication.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import tck.cn.communication.R;
import tck.cn.communication.base.BaseFragment;
import tck.cn.communication.present.contact.PluginContract;
import tck.cn.communication.ui.activity.adpater.PluginAdapter;
import tck.cn.communication.widget.DividerItemDecoration;

/**
 * Description :动态界面的简单实现
 * <p>
 * Created by tck on 2016/10/17.
 */

public class PluginFragment extends BaseFragment implements PluginContract.PluginView {

    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_plugin, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.plugin_recyclerview);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false));
        PluginAdapter adapter = new PluginAdapter();
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
    }

    @Override
    public void onLogout(String username, boolean success, String msg) {

    }
}
