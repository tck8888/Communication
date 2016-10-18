package tck.cn.communication.model;

/**
 * Description :动态界面的条目的bean
 * <p>
 * Created by tck on 2016/10/18.
 */

public class ItemInfo {

    public int titleId;
    public int ImageId;

    public ItemInfo(int titleId, int imageId) {
        this.titleId = titleId;
        ImageId = imageId;
    }

    public ItemInfo() {
    }
}
