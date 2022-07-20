package com.ctim.simpledemo.zhangrong;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ctim.simpledemo.R;

import java.util.List;

import io.rong.imkit.IMCenter;
import io.rong.imkit.RongIM;
import io.rong.imkit.userinfo.RongUserInfoManager;
import io.rong.imkit.userinfo.UserDataProvider;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.UserInfo;
import io.rong.message.TextMessage;

public class ZRMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainzr);
       TextView connect= findViewById(R.id.connect);
       TextView unconnect= findViewById(R.id.unconnect);
       TextView joinone= findViewById(R.id.joinone);
       TextView jointwo= findViewById(R.id.jointwo);
       TextView sendone= findViewById(R.id.sendone);
       TextView sendtwo= findViewById(R.id.sendtwo);
       TextView getone= findViewById(R.id.getone);
       TextView gettwo= findViewById(R.id.gettwo);



        joinone.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               RongIMClient.getInstance().joinChatRoom("globalroom", 0, new RongIMClient.OperationCallback() {
                   @Override
                   public void onSuccess() {
                       Log.e("join","加入聊天室1成功");
                   }

                   @Override
                   public void onError(RongIMClient.ErrorCode errorCode) {
                       Log.e("join","加入聊天室1失败");
                   }
               });
           }
       }); jointwo.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               RongIMClient.getInstance().joinChatRoom("globalchat", 0, new RongIMClient.OperationCallback() {
                   @Override
                   public void onSuccess() {
                       Log.e("join","加入聊天室2成功");
                   }

                   @Override
                   public void onError(RongIMClient.ErrorCode errorCode) {
                       Log.e("join","加入聊天室2失败");
                   }
               });
           }
       }); sendone.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               TextMessage textMessage=TextMessage.obtain("聊天室消息");
               Message message= Message.obtain("globalroom", Conversation.ConversationType.CHATROOM,textMessage);
               RongIMClient.getInstance().sendMessage(message, "", "", new IRongCallback.ISendMessageCallback() {
                   @Override
                   public void onAttached(Message message) {

                   }

                   @Override
                   public void onSuccess(Message message) {
                       Log.e("join","发消息成功");
                   }

                   @Override
                   public void onError(Message message, RongIMClient.ErrorCode errorCode) {
                       Log.e("join","发消息失败");
                   }
               });
           }
       }); sendtwo.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               TextMessage textMessage=TextMessage.obtain("聊天室消息");
               Message message= Message.obtain("globalchat", Conversation.ConversationType.CHATROOM,textMessage);
               RongIMClient.getInstance().sendMessage(message, "", "", new IRongCallback.ISendMessageCallback() {
                   @Override
                   public void onAttached(Message message) {

                   }

                   @Override
                   public void onSuccess(Message message) {
                       Log.e("join","发消息成功");
                   }

                   @Override
                   public void onError(Message message, RongIMClient.ErrorCode errorCode) {
                       Log.e("join","发消息失败");
                   }
               });
           }
       }); getone.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               RongIMClient.getInstance().getChatroomHistoryMessages("globalroom", 0L, 50, RongIMClient.TimestampOrder.RC_TIMESTAMP_DESC, new IRongCallback.IChatRoomHistoryMessageCallback() {
                   @Override
                   public void onSuccess(List<Message> messages, long syncTime) {
                       Log.e("join","获取聊天室1消息成功"+messages.size());
                   }

                   @Override
                   public void onError(RongIMClient.ErrorCode code) {
                       Log.e("join","获取聊天室1消息失败");
                   }
               });
           }
       }); gettwo.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               RongIMClient.getInstance().getChatroomHistoryMessages("globalchat", 0L, 50, RongIMClient.TimestampOrder.RC_TIMESTAMP_DESC, new IRongCallback.IChatRoomHistoryMessageCallback() {
                   @Override
                   public void onSuccess(List<Message> messages, long syncTime) {
                       Log.e("join","获取聊天室2消息成功"+messages.size());
                   }

                   @Override
                   public void onError(RongIMClient.ErrorCode code) {
                       Log.e("join","获取聊天室2消息失败");
                   }
               });
           }
       });  connect.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               connect();
           }
       });
       unconnect.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               unconnect();

           }
       });

        RongIMClient.setConnectionStatusListener(new RongIMClient.ConnectionStatusListener() {
            @Override
            public void onChanged(ConnectionStatus status) {
                Log.e("rc:ConnectionStatus", "status: "+status);
            }
        });

        RongUserInfoManager.getInstance().setUserInfoProvider(new UserDataProvider.UserInfoProvider() {
            @Override
            public UserInfo getUserInfo(String userId) {
                Uri uri =  Uri.parse("");
                UserInfo userInfo=new UserInfo(userId,userId+"名字",uri);
                return userInfo;
            }
        },true);


    }

    private void unconnect() {
        RongIM.getInstance().logout();
        IMCenter.getInstance().removeOnReceiveMessageListener(new RongIMClient.OnReceiveMessageWrapperListener() {
            @Override
            public boolean onReceived(Message message, int left, boolean hasPackage, boolean offline) {
                return false;
            }
        });
    }

    private void connect() {
        IMCenter.getInstance().connect("prJNiGSDVEB4ky/mUAdGxCiJ8r+GW5sxNosZaZoP0TE=@akl1.cn.rongnav.com;akl1.cn.rongcfg.com", new RongIMClient.ConnectCallback() {
            //        IMCenter.getInstance().connect("7tFyttugY+AMeluZozDLitTIyHoR4d2qZdekFDTsDYY=@9tsf.cn.rongnav.com;9tsf.cn.rongcfg.com", new RongIMClient.ConnectCallback() {
            @Override
            public void onSuccess(String s) {
                Log.e("join", "onSuccess: ");


            }

            @Override
            public void onError(RongIMClient.ConnectionErrorCode connectionErrorCode) {
                Log.e("join", "onError: "+connectionErrorCode);
            }

            @Override
            public void onDatabaseOpened(RongIMClient.DatabaseOpenStatus databaseOpenStatus) {
                Log.e("join", "onDatabaseOpened: ");
            }
        });

    }

}