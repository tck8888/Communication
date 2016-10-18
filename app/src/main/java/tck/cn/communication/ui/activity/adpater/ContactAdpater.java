package tck.cn.communication.ui.activity.adpater;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import tck.cn.communication.R;
import tck.cn.communication.listener.IContactAdapter;
import tck.cn.communication.ui.activity.viewholder.ContactViewHolder;
import tck.cn.communication.utils.StringUtils;

/**
 * Description :联系人界面的adpater
 * <p>
 * Created by tck on 2016/10/17.
 */

public class ContactAdpater extends RecyclerView.Adapter<ContactViewHolder> implements IContactAdapter {

    private List<String> mContacts;

    public ContactAdpater(List<String> contacts) {
        this.mContacts = contacts;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_contact, parent, false);
        ContactViewHolder contactViewHolder = new ContactViewHolder(view);
        return contactViewHolder;
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, final int position) {
        final String contact = mContacts.get(position);
        holder.tvUsername.setText(contact);
        String initial = StringUtils.getInitial(contact);
        holder.tvSection.setText(initial);
        if (position == 0) {
            holder.tvSection.setVisibility(View.VISIBLE);
        } else {
            //获取上一个首字母
            String preContact = mContacts.get(position - 1);
            String preInitial = StringUtils.getInitial(preContact);
            if (preInitial.equals(initial)) {
                holder.tvSection.setVisibility(View.GONE);
            } else {
                holder.tvSection.setVisibility(View.VISIBLE);
            }
        }
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemLongClickListener != null) {
                    mOnItemLongClickListener.onItemLongClick(contact, position);
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mContacts == null ? 0 : mContacts.size();
    }

    public List<String> getData() {
        return mContacts;
    }

    private OnItemLongClickListener mOnItemLongClickListener;

    public interface OnItemLongClickListener {
        void onItemLongClick(String contact, int position);
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.mOnItemLongClickListener = onItemLongClickListener;
    }

}
