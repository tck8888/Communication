package tck.cn.communication.ui.activity.adpater;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.util.DateUtils;

import java.util.Date;
import java.util.List;

import tck.cn.communication.R;
import tck.cn.communication.ui.activity.viewholder.ConversationViewHolder;

/**
 * Description :会话的适配器
 * <p>
 * Created by tck on 2016/10/19.
 */

public class CoversationAdapter extends RecyclerView.Adapter<ConversationViewHolder> {

    private List<EMConversation> mEMConversations;

    public CoversationAdapter(List<EMConversation> EMConversations) {
        mEMConversations = EMConversations;
    }

    @Override
    public ConversationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_conversation, parent, false);
        return new ConversationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ConversationViewHolder holder, int position) {
        //聊天的对方的名称
        final EMConversation emConversation = mEMConversations.get(position);

        final String userName = emConversation.getUserName();
        //没有读的信息条数
        int unreadMsgCount = emConversation.getUnreadMsgCount();

        //最后一条消息
        EMMessage lastMessage = emConversation.getLastMessage();

        //最后一条消息的时间
        long msgTime = lastMessage.getMsgTime();

        EMTextMessageBody lastMessageBody = (EMTextMessageBody) lastMessage.getBody();

        String message = lastMessageBody.getMessage();

        holder.tvMsg.setText(message);

        holder.tvTime.setText(DateUtils.getTimestampString(new Date(msgTime)));

        holder.tvUsername.setText(userName);

        if (unreadMsgCount > 99) {
            holder.tvUnread.setVisibility(View.VISIBLE);
            holder.tvUnread.setText("99+");
        } else if (unreadMsgCount > 0) {
            holder.tvUnread.setVisibility(View.VISIBLE);
            holder.tvUnread.setText(unreadMsgCount + "");
        } else {
            holder.tvUnread.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(emConversation);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mEMConversations == null ? 0 : mEMConversations.size();
    }

    public interface OnItemClickListener {
        void onItemClick(EMConversation conversation);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
