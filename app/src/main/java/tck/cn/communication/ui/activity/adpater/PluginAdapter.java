package tck.cn.communication.ui.activity.adpater;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import tck.cn.communication.R;
import tck.cn.communication.model.ItemInfo;
import tck.cn.communication.ui.activity.viewholder.FootViewHolder;

import tck.cn.communication.ui.activity.viewholder.PluginItemViewholder;

/**
 * Description :动态界面的适配器
 * <p>
 * Created by tck on 2016/10/18.
 */

public class PluginAdapter extends RecyclerView.Adapter {

    private final static int FOOT = 1001;

    private List<ItemInfo> mItemInfo;

    public PluginAdapter(List<ItemInfo> itemInfo) {
        mItemInfo = itemInfo;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == FOOT) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_foot, parent, false);
            return new FootViewHolder(view);
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_plugin, parent, false);

        return new PluginItemViewholder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof FootViewHolder) {
            final FootViewHolder footViewHolder = (FootViewHolder) holder;

/*
            footViewHolder.exitLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(position);
                    }
                }
            });*/
        } else {

            ItemInfo itemInfo = mItemInfo.get(position);
            PluginItemViewholder pluginItemViewholder = (PluginItemViewholder) holder;
            pluginItemViewholder.tvInfomation.setText(itemInfo.titleId);
            pluginItemViewholder.imageView.setBackgroundResource(itemInfo.ImageId);
           /* pluginItemViewholder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(position);
                    }
                }
            });*/
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mItemInfo.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return FOOT;
        }
        return super.getItemViewType(position);
    }

    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int postion);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
