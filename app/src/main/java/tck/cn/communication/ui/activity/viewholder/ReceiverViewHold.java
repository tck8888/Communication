package tck.cn.communication.ui.activity.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import tck.cn.communication.R;

/**
 * Description :发送消息hold
 * <p>
 * Created by tck on 2016/10/19.
 */

public class ReceiverViewHold extends RecyclerView.ViewHolder {

    public TextView tvTime;
    public TextView tvMsg;


    public ReceiverViewHold(View itemView) {
        super(itemView);
        tvTime = (TextView) itemView.findViewById(R.id.tv_time);
        tvMsg = (TextView) itemView.findViewById(R.id.tv_msg);
    }
}
