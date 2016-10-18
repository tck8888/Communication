package tck.cn.communication.present.contact;

import java.util.List;

/**
 * Description :联系人界面
 * <p>
 * Created by tck on 2016/10/17.
 */

public interface ContactContract {

    public interface ContactView {

        void onInitContacts(List<String> contacts);

        void updateContacts(boolean success,String msg);

        void onDelete(String contact,boolean success,String msg);
    }

    public interface Presenter {
        void initContacts();

        void updateContacts();

        void deleteContact(String contact);
    }

}
