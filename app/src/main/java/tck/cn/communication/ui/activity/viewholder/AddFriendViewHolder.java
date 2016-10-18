package tck.cn.communication.ui.activity.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import tck.cn.communication.R;

/**
 * Description :
 * <p>
 * Created by tck on 2016/10/18.
 */

public class AddFriendViewHolder extends RecyclerView.ViewHolder {


    public ImageView ivAvatar;
    public TextView tvUsername;
    public TextView tvTime;
    public Button btnAdd;


    public AddFriendViewHolder(View itemView) {
        super(itemView);


        ivAvatar = (ImageView) itemView.findViewById(R.id.iv_avatar);
        tvUsername = (TextView) itemView.findViewById(R.id.tv_username);
        tvTime = (TextView) itemView.findViewById(R.id.tv_time);
        btnAdd = (Button) itemView.findViewById(R.id.btn_add);
    }
}
