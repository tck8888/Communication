package tck.cn.communication.present;

import android.util.Log;

import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import tck.cn.communication.present.contact.ContactContract;
import tck.cn.communication.utils.DBUtils;
import tck.cn.communication.utils.ThreadUtils;

/**
 * Description :
 * <p>
 * Created by tck on 2016/10/17.
 */

public class ContactPresenter implements ContactContract.Presenter {

    public ContactContract.ContactView mContactView;

    public List<String> contactList = new ArrayList<>();

    public ContactPresenter(ContactContract.ContactView contactView) {
        this.mContactView = contactView;
    }

    @Override
    public void initContacts() {
        /**
         * 1. 首先访问本地的缓存联系人
         * 2. 然后开辟子线程去环信后台获取当前用户的联系人
         * 3. 更新本地的缓存，刷新UI
         */
        final String currentUser = EMClient.getInstance().getCurrentUser();

        final List<String> contacts = DBUtils.getContacts(currentUser);

        contactList.clear();

        contacts.addAll(contacts);

        mContactView.onInitContacts(contactList);

        updateContactsFromServer(currentUser);


    }

    private void updateContactsFromServer(final String currentUser) {
        //然后开辟子线程去环信后台获取当前用户的联系人
        ThreadUtils.runOnSubThread(new Runnable() {
            @Override
            public void run() {
                try {
                    List<String> contactsFromServer = EMClient.getInstance().contactManager().getAllContactsFromServer();
                    Log.d("==1233===", contactsFromServer.size() + "");
                    /**
                     * 升序排列
                     */
                    Collections.sort(contactsFromServer, new Comparator<String>() {
                        @Override
                        public int compare(String o1, String o2) {
                            return o1.compareTo(o2);
                        }
                    });

                    //更新本地缓存
                    DBUtils.updateContacts(currentUser, contactsFromServer);
                    contactList.clear();
                    contactList.addAll(contactsFromServer);
                    //通知View刷新UI
                    Log.d("===================", contactList.size() + "");
                    ThreadUtils.runOnMainThread(new Runnable() {
                        @Override
                        public void run() {
                            mContactView.updateContacts(true, null);
                        }
                    });
                } catch (final HyphenateException e) {
                    e.printStackTrace();
                    ThreadUtils.runOnMainThread(new Runnable() {
                        @Override
                        public void run() {
                            mContactView.updateContacts(false, e.getMessage());
                        }
                    });
                }
            }
        });
    }

    @Override
    public void updateContacts() {
        updateContactsFromServer(EMClient.getInstance().getCurrentUser());
    }

    @Override
    public void deleteContact(final String contact) {
        ThreadUtils.runOnSubThread(new Runnable() {
            @Override
            public void run() {
                try {
                    EMClient.getInstance().contactManager().deleteContact(contact);
                    afterDelete(contact, true, null);
                } catch (final HyphenateException e) {
                    e.printStackTrace();
                    afterDelete(contact, false, e.toString());
                }

            }
        });
    }

    private void afterDelete(final String contact, final boolean success, final String msg) {
        ThreadUtils.runOnMainThread(new Runnable() {
            @Override
            public void run() {
                mContactView.onDelete(contact, success, msg);
            }
        });
    }
}
