package com.ctim.simpledemo.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ctim.simpledemo.App;
import com.ctim.simpledemo.R;
import com.ctim.simpledemo.utils.HttpUtil;
import com.ctim.simpledemo.utils.UiUtils;

import io.rong.imkit.RongIM;
import io.rong.imkit.manager.UnReadMessageManager;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private Button btnGetTokenConnectIMServer;
    private EditText etUserId;

//    private String token001 = "Enzbxdr7hdO6WSr+DZFARkaUNlc6QSw8FXTjaWyaqiE=@poxt.cn.rongnav.com;poxt.cn.rongcfg.com";
//    private String userId = "001", token = token001;
    private String userId = "";
    private Conversation.ConversationType[] conversationTypes;

    private RongIMClient.ConnectionStatusListener connectionStatusListener = new RongIMClient.ConnectionStatusListener() {
        @Override
        public void onChanged(ConnectionStatus status) {
            //开发者需要根据连接状态码，进行不同业务处理
            // CONNECTING CONNECTED SIGN_OUT
            Log.e(TAG,"--> connectionStatusListener - onChanged - status = "+status);
        }
    };

    public void setIMStatusListener() {
        RongIM.setConnectionStatusListener(connectionStatusListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGetTokenConnectIMServer = findViewById(R.id.btnGetTokenConnectIMServer);
        etUserId = findViewById(R.id.etUserId);

        btnGetTokenConnectIMServer.setOnClickListener(this);

        setIMStatusListener();
        //监听未读数变化
        conversationTypes =
                new Conversation.ConversationType[] {
                        Conversation.ConversationType.PRIVATE,
                        Conversation.ConversationType.GROUP,
                        Conversation.ConversationType.SYSTEM,
                        Conversation.ConversationType.PUBLIC_SERVICE,
                        Conversation.ConversationType.APP_PUBLIC_SERVICE
                };
        UnReadMessageManager.getInstance().addObserver(conversationTypes, new UnReadMessageManager.IUnReadMessageObserver() {

            @Override
            public void onCountChanged(int count) {
                Toast.makeText(MainActivity.this, "未读消息数"+count, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnGetTokenConnectIMServer:
                getToken();
                break;
        }

    }

    private void getToken() {
        userId = etUserId.getText().toString().trim();
        if (TextUtils.isEmpty(userId)) {
            Toast.makeText(this, "请输入 userId", Toast.LENGTH_SHORT).show();
            return;
        }
        UiUtils.showWaitingDialog(this);
        HttpUtil.getToken(App.APP_KEY, App.APP_SECRET, userId, new HttpUtil.GetTokenCallback() {
            @Override
            public void onGetTokenSuccess(String token) {
                Log.e(TAG, "onGetTokenSuccess() token = " + token);
                connectIMServer(token);
            }

            @Override
            public void onGetTokenFailed(String code) {
                UiUtils.hideWaitingDialog();
                Toast.makeText(MainActivity.this, "获取 Token 失败，code = " + code, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void connectIMServer(String token) {
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onSuccess(String userId) {
                //连接成功，如果 onDatabaseOpened() 时没有页面跳转，也可在此时进行跳转。
                // 登录成功，跳转到默认会话列表页。
                UiUtils.hideWaitingDialog();
                HomeActivity.start(MainActivity.this);
                MainActivity.this.finish();
            }

            @Override
            public void onError(RongIMClient.ConnectionErrorCode connectionErrorCode) {
                Log.e(TAG,"--> connectIMServer onError connectionErrorCode = "+connectionErrorCode);
                if(connectionErrorCode.equals(RongIMClient.ConnectionErrorCode.RC_CONN_TOKEN_EXPIRE)) {
                    //从 APP 服务请求新 token，获取到新 token 后重新 connect()
                } else if (connectionErrorCode.equals(RongIMClient.ConnectionErrorCode.RC_CONNECT_TIMEOUT)) {
                    //连接超时，弹出提示，可以引导用户等待网络正常的时候再次点击进行连接
                }else if (connectionErrorCode.equals(RongIMClient.ConnectionErrorCode.RC_CONNECTION_EXIST)){
                    // 连接已存在，不需要重复连接
                    //TODO 进行连接后逻辑

                } else {
                    //其它业务错误码，请根据相应的错误码作出对应处理。
                }
            }

            @Override
            public void onDatabaseOpened(RongIMClient.DatabaseOpenStatus databaseOpenStatus) {
                if(RongIMClient.DatabaseOpenStatus.DATABASE_OPEN_SUCCESS.equals(databaseOpenStatus)) {
                    //本地数据库打开，跳转到会话列表页面
                } else {
                    //数据库打开失败，可以弹出 toast 提示。
                    Toast.makeText(MainActivity.this, "数据库打开失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}