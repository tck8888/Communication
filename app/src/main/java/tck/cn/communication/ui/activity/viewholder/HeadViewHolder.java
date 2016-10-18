package tck.cn.communication.ui.activity.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import tck.cn.communication.R;

/**
 * Description :
 * <p>
 * Created by tck on 2016/10/18.
 */

public class HeadViewHolder extends RecyclerView.ViewHolder {

    public ImageView qqzone;
    public ImageView qqbrowser;
    public ImageView wangyi;
    public LinearLayout mLinearLayout;

    public HeadViewHolder(View itemView) {
        super(itemView);
        qqzone = (ImageView) itemView.findViewById(R.id.qqzone);
        qqbrowser = (ImageView) itemView.findViewById(R.id.qqbrowser);
        wangyi = (ImageView) itemView.findViewById(R.id.wangyi);
       mLinearLayout = (LinearLayout) itemView.findViewById(R.id.item_head);

    }
}
