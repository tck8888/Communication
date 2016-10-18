package tck.cn.communication.app;

import android.app.ActivityManager;
import android.app.Application;
import android.content.pm.PackageManager;
import android.util.Log;

import com.hyphenate.EMContactListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;

import org.greenrobot.eventbus.EventBus;

import java.util.Iterator;
import java.util.List;

import cn.bmob.v3.Bmob;
import tck.cn.communication.model.OnContactUpdate;
import tck.cn.communication.utils.DBUtils;

/**
 * Description :
 * <p>
 * Created by tck on 2016/10/15.
 */

public class App extends Application {

    private static App INSTANCE;

    public synchronized static App getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        initHyphenate();

        //默认初始化:后端云服务器
        Bmob.initialize(this, "c529a9978542db5f09f932c4a7bc966a");

        initDB();
    }

    public void initDB() {
        DBUtils.initDB(this);
    }

    private void initHyphenate() {
        EMOptions options = new EMOptions();

        // 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false);
        int pid = android.os.Process.myPid();
        String processAppName = getAppName(pid);
        // 如果APP启用了远程的service，此application:onCreate会被调用2次
        // 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
        // 默认的APP会在以包名为默认的process name下运行，如果查到的process name不是APP的process name就立即返回

        if (processAppName == null || !processAppName.equalsIgnoreCase(getPackageName())) {
            Log.e("App", "enter the service process!");
            // 则此application::onCreate 是被service 调用的，直接返回
            return;
        }

//初始化
        EMClient.getInstance().init(this, options);
//在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(true);

        initEMCListener();
    }

    private String getAppName(int pID) {
        String processName = null;
        ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        PackageManager pm = this.getPackageManager();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pID) {
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
                // Log.d("Process", "Error>> :"+ e.toString());
            }
        }
        return processName;
    }


    public void initEMCListener() {
        EMClient.getInstance().contactManager().setContactListener(new EMContactListener() {
            @Override
            public void onContactAdded(String s) {
                /**
                 * 好友请求被tong意
                 * 发出通知联系人界面更新ui
                 */
                EventBus.getDefault().post(new OnContactUpdate(s, true));
            }

            @Override
            public void onContactDeleted(String s) {
                //被删除时回调此方法
                EventBus.getDefault().post(new OnContactUpdate(s, false));

            }

            @Override
            public void onContactInvited(String s, String s1) {
            //收到好友邀请
            }

            @Override
            public void onContactAgreed(String s) {
                //增加了联系人时回调此方法
            }

            @Override
            public void onContactRefused(String s) {
                //好友请求被拒绝
            }
        });
    }
}
