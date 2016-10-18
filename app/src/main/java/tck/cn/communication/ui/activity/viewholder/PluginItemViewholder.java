package tck.cn.communication.ui.activity.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import tck.cn.communication.R;

/**
 * Description :
 * <p>
 * Created by tck on 2016/10/18.
 */

public class PluginItemViewholder extends RecyclerView.ViewHolder {
    public TextView tvInfomation;



    public PluginItemViewholder(View itemView) {
        super(itemView);
        tvInfomation = (TextView) itemView.findViewById(R.id.tv_infomation);
    }
}
