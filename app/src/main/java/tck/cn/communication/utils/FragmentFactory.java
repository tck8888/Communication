package tck.cn.communication.utils;

import tck.cn.communication.base.BaseFragment;
import tck.cn.communication.ui.fragment.ContractFragment;
import tck.cn.communication.ui.fragment.MessageFragment;
import tck.cn.communication.ui.fragment.PluginFragment;

/**
 * Description :
 * <p>
 * Created by tck on 2016/10/17.
 */

public class FragmentFactory {
    private static MessageFragment sConversationFragment;
    private static ContractFragment sContactFragment;
    private static PluginFragment sPluginFragment;

    public static BaseFragment getFragment(int position){
        BaseFragment baseFragment = null;
        switch (position) {
            case 0:
                if (sConversationFragment==null){
                    sConversationFragment = new MessageFragment();
                }
                baseFragment = sConversationFragment;
                break;
            case 1:
                if (sContactFragment==null){
                    sContactFragment = new ContractFragment();
                }
                baseFragment = sContactFragment;
                break;
            case 2:
                if (sPluginFragment==null){
                    sPluginFragment = new PluginFragment();
                }
                baseFragment = sPluginFragment;
                break;
        }
        return baseFragment;

    }
}
