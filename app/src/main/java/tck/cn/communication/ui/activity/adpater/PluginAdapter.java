package tck.cn.communication.ui.activity.adpater;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tck.cn.communication.R;
import tck.cn.communication.ui.activity.viewholder.FootViewHolder;
import tck.cn.communication.ui.activity.viewholder.HeadViewHolder;
import tck.cn.communication.ui.activity.viewholder.PluginItemViewholder;

/**
 * Description :
 * <p>
 * Created by tck on 2016/10/18.
 */

public class PluginAdapter extends RecyclerView.Adapter {

    private final static int HEAD = 1000;
    private final static int FOOT = 1001;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == HEAD) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_head, parent, false);
            return new HeadViewHolder(view);
        }

        if (viewType == FOOT) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_foot, parent, false);
            return new FootViewHolder(view);
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_plugin, parent, false);

        return new PluginItemViewholder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeadViewHolder) {
            HeadViewHolder headViewHolder = (HeadViewHolder) holder;
        } else if (holder instanceof FootViewHolder) {
            FootViewHolder footViewHolder = (FootViewHolder) holder;
        } else {
            PluginItemViewholder pluginItemViewholder = (PluginItemViewholder) holder;
        }


    }

    @Override
    public int getItemCount() {
        return 15;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEAD;
        }
        if (position == getItemCount()-1) {
            return FOOT;
        }
        return super.getItemViewType(position);
    }
}
