package tck.cn.communication.ui.activity.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import tck.cn.communication.R;

/**
 * Description :联系人界面的viewholder
 * <p>
 * Created by tck on 2016/10/17.
 */

public class ContactViewHolder extends RecyclerView.ViewHolder {

    public TextView tvSection;
    public TextView tvUsername;


    public ContactViewHolder(View itemView) {
        super(itemView);

        tvSection = (TextView) itemView.findViewById(R.id.tv_section);
        tvUsername = (TextView) itemView.findViewById(R.id.tv_username);
    }
}
