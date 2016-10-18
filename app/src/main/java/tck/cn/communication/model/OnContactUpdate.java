package tck.cn.communication.model;

/**
 * Description :
 * <p>
 * Created by tck on 2016/10/18.
 */

public class OnContactUpdate {

    public String contact;
    public boolean isAdded;

    public OnContactUpdate(String contact, boolean isAdded) {
        this.contact = contact;
        this.isAdded = isAdded;
    }

    public OnContactUpdate() {
    }
}
