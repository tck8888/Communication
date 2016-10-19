package tck.cn.communication.ui.activity.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import tck.cn.communication.R;

/**
 * Description :
 * <p>
 * Created by tck on 2016/10/19.
 */

public class ConversationViewHolder extends RecyclerView.ViewHolder {

    public ImageView ivAvatar;
    public TextView tvUsername;
    public TextView tvMsg;
    public TextView tvTime;
    public TextView tvUnread;


    public ConversationViewHolder(View itemView) {
        super(itemView);


        ivAvatar = (ImageView) itemView.findViewById(R.id.iv_avatar);
        tvUsername = (TextView) itemView.findViewById(R.id.tv_username);
        tvMsg = (TextView) itemView.findViewById(R.id.tv_msg);
        tvTime = (TextView) itemView.findViewById(R.id.tv_time);
        tvUnread = (TextView) itemView.findViewById(R.id.tv_unread);
    }
}
