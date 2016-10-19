package tck.cn.communication.ui.activity.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import tck.cn.communication.R;

/**
 * Description :
 * <p>
 * Created by tck on 2016/10/19.
 */

public class SenderViewHolder extends ReceiverViewHold {

    public TextView tvTime;
    public ImageView ivAvatar;
    public TextView tvMsg;
    public ImageView ivState;

    public SenderViewHolder(View itemView) {
        super(itemView);
        tvTime = (TextView) itemView.findViewById(R.id.tv_time);
        ivAvatar = (ImageView) itemView.findViewById(R.id.iv_avatar);
        tvMsg = (TextView) itemView.findViewById(R.id.tv_msg);
        ivState = (ImageView) itemView.findViewById(R.id.iv_state);
    }
}
