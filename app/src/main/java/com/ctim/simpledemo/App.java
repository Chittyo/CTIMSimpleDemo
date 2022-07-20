package com.ctim.simpledemo;

import android.app.Application;
import android.content.Context;
import androidx.multidex.MultiDex;

import com.ctim.simpledemo.activity.MyConversationListActivity;
import com.ctim.simpledemo.bean.CustomMessage;
import com.ctim.simpledemo.bean.CustomMessageProvider;
import com.ctim.simpledemo.bean.IMCustomIntimacyMessage;
import com.ctim.simpledemo.plugins.MyExtensionConfig;

import java.util.ArrayList;

import io.rong.imkit.RongIM;
import io.rong.imkit.config.RongConfigCenter;
import io.rong.imkit.conversation.extension.RongExtensionManager;
import io.rong.imkit.utils.RouteUtils;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.MessageContent;
import io.rong.push.RongPushClient;
import io.rong.push.pushconfig.PushConfig;

public class App extends Application {
    public static final String APP_KEY = "lmxuhwagl6ddd";
    public static final String APP_SECRET = "X73cq2WP9keSot";
    private static Context context;
    private static final String XIAOMI_APPID = "2882303761520109364";
    private static final String XIAOMI_APPKEY = "5442010912364";

    public static Context getAppContext(){
        return context;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        //推送配置
        PushConfig config = new PushConfig.Builder()
                .enableMiPush(XIAOMI_APPID, XIAOMI_APPKEY) //小米推送配置
                //.enableHWPush(...)
                //...
//                .enableOppoPush()
                .build();
        RongPushClient.setPushConfig(config); //将推送相关配置设置到 SDK
        //初始化IM
        RongIM.init(this, APP_KEY);
        //注册自定义消息
        ArrayList<Class<? extends MessageContent>> myMessages = new ArrayList<>();
        myMessages.add(CustomMessage.class);
        myMessages.add(IMCustomIntimacyMessage.class);
        RongIMClient.registerMessageType(myMessages);
        //注册自定义消息展示模板
        RongConfigCenter.conversationConfig().addMessageProvider(new CustomMessageProvider());
        //设置自定义的扩展面板配置
        RongExtensionManager.getInstance().setExtensionConfig(new MyExtensionConfig());
        //注册自定义的会话列表
        RouteUtils.registerActivity(RouteUtils.RongActivityType.ConversationListActivity, MyConversationListActivity.class);
    }
}
