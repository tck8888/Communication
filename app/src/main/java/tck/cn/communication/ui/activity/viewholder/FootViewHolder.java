package tck.cn.communication.ui.activity.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import tck.cn.communication.R;

/**
 * Description :
 * <p>
 * Created by tck on 2016/10/18.
 */

public class FootViewHolder extends RecyclerView.ViewHolder{
    public Button exitLogin;


    public FootViewHolder(View itemView) {
        super(itemView);
        exitLogin = (Button) itemView.findViewById(R.id.exit_login);
    }

}
