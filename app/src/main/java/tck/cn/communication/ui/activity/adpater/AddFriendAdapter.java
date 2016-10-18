package tck.cn.communication.ui.activity.adpater;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import tck.cn.communication.R;
import tck.cn.communication.model.User;
import tck.cn.communication.ui.activity.viewholder.AddFriendViewHolder;

/**
 * Description :添加好友界面的适配器
 * <p>
 * Created by tck on 2016/10/18.
 */

public class AddFriendAdapter extends RecyclerView.Adapter<AddFriendViewHolder> {

    private List<User> mUserList;
    private List<String> mContactsList;

    public AddFriendAdapter(List<User> list, List<String> contactsList) {
        this.mUserList = list;
        this.mContactsList = contactsList;
    }

    @Override
    public AddFriendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_search, parent, false);
        return new AddFriendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AddFriendViewHolder holder, int position) {
        User user = mUserList.get(position);
        final String username = user.getUsername();
        holder.tvUsername.setText(username);
        holder.tvTime.setText(user.getCreatedAt());

        //判断当前username是不是已经在好友列表中了

        if (mContactsList.contains(username)) {
            holder.btnAdd.setText("已经是好友");
            holder.btnAdd.setEnabled(false);
        } else {
            holder.btnAdd.setText("添加");
            holder.btnAdd.setEnabled(true);
        }
        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnAddFriendClickListener != null) {
                    mOnAddFriendClickListener.onAddClick(username);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mUserList == null ? 0 : mUserList.size();
    }

    /**
     * 添加好友按钮的回调事件的处理
     */
    private OnAddFriendClickListener mOnAddFriendClickListener;

    public interface OnAddFriendClickListener {
        void onAddClick(String username);
    }

    public void setOnAddFriendClickListener(OnAddFriendClickListener onAddFriendClickListener) {
        this.mOnAddFriendClickListener = onAddFriendClickListener;
    }

}
