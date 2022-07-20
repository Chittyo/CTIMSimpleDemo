package com.ctim.simpledemo.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.ctim.simpledemo.R;
import com.ctim.simpledemo.bean.CustomMessage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.rong.callkit.RongCallKit;
import io.rong.imkit.IMCenter;
import io.rong.imkit.MessageInterceptor;
import io.rong.imkit.RongIM;
import io.rong.imkit.userinfo.RongUserInfoManager;
import io.rong.imkit.userinfo.UserDataProvider;
import io.rong.imkit.userinfo.model.GroupUserInfo;
import io.rong.imkit.utils.RouteUtils;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Group;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.model.UserInfo;
import io.rong.message.ImageMessage;
import io.rong.message.TextMessage;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "HomeActivity";

    private Button btnConversationList, btnConversation, btnUserInfo, btnSendCustomMsg, btnUpdateMessageExpansion,
            btnRemoveMessageExpansion, btnSendImage, btnJoinChatRoom1, btnJoinChatRoom2,btnSendMsg2ChatRoom1, btnSendMsg2ChatRoom2,
            btnGetHistoryMsgChatRoom1, btnGetHistoryMsgChatRoom2;
    private TextView tvCustomMsg;
    private HashMap expansionKey = new HashMap();
    private Message msg;

    String userId001 = "001";
    String userId002 = "002";
    String userId003 = "003";
    String userId004 = "004";
    String userId005 = "005";
    String userName001 = "昵称001";
    String userName002 = "昵称002";
    String userName003 = "昵称003";
    String userName004 = "昵称004";
    String userName005 = "昵称005";
    Uri user001Portrait = Uri.parse("https://www.splitshire.com/wp-content/uploads/2014/05/SplitShire_IMG_4661-1800x1200.jpg");
    Uri user002Portrait = Uri.parse("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic2.zhimg.com%2F50%2Fv2-cdb4ce0c39da667bd862958f2308f9e4_hd.jpg&refer=http%3A%2F%2Fpic2.zhimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1636617488&t=0ba68b5196b531e0f872f744fa9ccc02");
    Uri user003Portrait = Uri.parse("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F0119d95c482e8fa801213f26847b0f.jpg%402o.jpg&refer=http%3A%2F%2Fimg.zcool.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1636617488&t=cb00de8cfaa50d198352377aa5d72dae");
    Uri user004Portrait = Uri.parse("https://img1.baidu.com/it/u=1870015119,1736627843&fm=26&fmt=auto");
    Uri user005Portrait = Uri.parse("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201809%2F06%2F20180906101405_tozwg.jpg&refer=http%3A%2F%2Fb-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1639732891&t=c712c7f17da7d2f0caf627199c29226c");
    String groupId = "1001";
    String groupName = "group1001";
    Uri groupPortrait = Uri.parse("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fitem%2F201506%2F15%2F20150615010338_8wC2u.thumb.700_0.jpeg&refer=http%3A%2F%2Fimg3.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1639722076&t=702b9e073cfa343b5a19f02b54871fd7");


    public static void start(Context context) {
        context.startActivity(new Intent(context, HomeActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnConversationList = findViewById(R.id.btnConversationList);
        btnConversation = findViewById(R.id.btnConversation);
        btnUserInfo = findViewById(R.id.btnUserInfo);
        btnSendCustomMsg = findViewById(R.id.btnSendCustomMsg);
        tvCustomMsg = findViewById(R.id.tvCustomMsg);
        btnUpdateMessageExpansion = findViewById(R.id.btnUpdateMessageExpansion);
        btnRemoveMessageExpansion = findViewById(R.id.btnRemoveMessageExpansion);
        btnSendImage = findViewById(R.id.btnSendImage);
        btnJoinChatRoom1 = findViewById(R.id.btnJoinChatRoom1);
        btnJoinChatRoom2 = findViewById(R.id.btnJoinChatRoom2);
        btnSendMsg2ChatRoom1 = findViewById(R.id.btnSendMsg2ChatRoom1);
        btnSendMsg2ChatRoom2 = findViewById(R.id.btnSendMsg2ChatRoom2);
        btnGetHistoryMsgChatRoom1 = findViewById(R.id.btnGetHistoryMsgChatRoom1);
        btnGetHistoryMsgChatRoom2 = findViewById(R.id.btnGetHistoryMsgChatRoom2);

        btnConversationList.setOnClickListener(this);
        btnConversation.setOnClickListener(this);
        btnUserInfo.setOnClickListener(this);
        btnSendCustomMsg.setOnClickListener(this);
        btnUpdateMessageExpansion.setOnClickListener(this);
        btnRemoveMessageExpansion.setOnClickListener(this);
        btnSendImage.setOnClickListener(this);
        btnJoinChatRoom1.setOnClickListener(this);
        btnJoinChatRoom2.setOnClickListener(this);
        btnSendMsg2ChatRoom1.setOnClickListener(this);
        btnSendMsg2ChatRoom2.setOnClickListener(this);
        btnGetHistoryMsgChatRoom1.setOnClickListener(this);
        btnGetHistoryMsgChatRoom2.setOnClickListener(this);

        //消息扩展监听器
        RongIMClient.getInstance().setMessageExpansionListener(new RongIMClient.MessageExpansionListener() {
            @Override
            public void onMessageExpansionUpdate(Map<String, String> map, Message message) {
                Log.e(TAG, "--> 消息扩展监听器 onMessageExpansionUpdate map=" + map.toString() + " , message=" + message);
                Toast.makeText(HomeActivity.this, "扩展消息更新了", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMessageExpansionRemove(List<String> list, Message message) {
                Log.e(TAG, "--> 消息扩展监听器 onMessageExpansionRemove list=" + list.toString() + " , message=" + message);
                Toast.makeText(HomeActivity.this, "扩展消息移除了", Toast.LENGTH_SHORT).show();
            }
        });

        //群组信息
        RongUserInfoManager.getInstance().setGroupInfoProvider(new UserDataProvider.GroupInfoProvider() {
            @Override
            public Group getGroupInfo(String groupId) {
                Group group = new Group(groupId, groupName, groupPortrait);
                //刷新群组信息
                RongUserInfoManager.getInstance().refreshGroupInfoCache(group);
                return group;
            }
        }, true);

        //群成员信息
        RongUserInfoManager.getInstance().setGroupUserInfoProvider(new UserDataProvider.GroupUserInfoProvider() {
            @Override
            public GroupUserInfo getGroupUserInfo(String groupId, String userId) {
                GroupUserInfo groupUserInfo = new GroupUserInfo(groupId, userId, "昵称" + userId);
                //刷新群成员信息
                RongUserInfoManager.getInstance().refreshGroupUserInfoCache(groupUserInfo);
                return groupUserInfo; //同步返回群成员昵称。
            }
        }, true);

        /**
         * 群组-音视频通话-选择联系人列表
         *
         * 第一种：可单独使用：RongIM.getInstance().setGroupMembersProvider
         *      也可配合 RongIM.getInstance().setGroupUserInfoProvider
         *      （但是如果没使用 RongIM.getInstance().setUserInfoProvider，在会话聊天窗口就不显示头像）
         *
         * 第二种 ：组合使用
         * 先使用 RongCallKit.setGroupMemberProvider （这个会展示用户列表但是没有name）
         * 再使用 RongIM.getInstance().setUserInfoProvider   展示name （如果只使用setUserInfoProvider 不会展示联系人列表）
         */
        //方案一
//        RongIM.getInstance().setGroupMembersProvider(new RongMentionManager.IGroupMembersProvider() {
//            @Override
//            public void getGroupMembers(String groupId, RongMentionManager.IGroupMemberCallback iGroupMemberCallback) {
//                //groupId 是群组id 可以根据群组id 获取群组内用户id，根据id获取用户信息并返回
//                //代码如下
//                List<UserInfo> list = new ArrayList();
//                UserInfo userInfo = new UserInfo(userId001,userName001, user001Portrait);
//                list.add(userInfo);
//                UserInfo userInfo2 = new UserInfo(userId002, userName002, user002Portrait);
//                list.add(userInfo2);
//                UserInfo userInfo3 = new UserInfo(userId003, userName003, user003Portrait);
//                list.add(userInfo3);
//                UserInfo userInfo4 = new UserInfo(userId004, userName004, user004Portrait);
//                list.add(userInfo4);
//                UserInfo userInfo5 = new UserInfo(userId005, userName005, user005Portrait);
//                list.add(userInfo5);
//                iGroupMemberCallback.onGetGroupMembersResult(list);
//            }
//        });
//
//        //此段代码加不加都可
//        RongIM.getInstance().setGroupUserInfoProvider(new UserDataProvider.GroupUserInfoProvider() {//RongUserInfoManager
//            @Override
//            public GroupUserInfo getGroupUserInfo(String groupId, String userId) {
//                // groupId 为群组id userId 为群成员id
//                GroupUserInfo groupUserInfo = new GroupUserInfo(groupId, userId, "昵称"+userId);
//
//                return groupUserInfo;
//            }
//        },true);


        //方案二（推荐）
        //使用这个方法会展示选择联系人页面，但是不会展示昵称和头像
        RongCallKit.setGroupMemberProvider(new RongCallKit.GroupMembersProvider() {
            @Override
            public ArrayList<String> getMemberList(String groupId, RongCallKit.OnGroupMembersResult result) {
                //可以根据groupId 获取群组内成员id 并返回
                ArrayList<String> list = new ArrayList();
                list.add(userId001);
                list.add(userId002);
                list.add(userId003);
                list.add(userId004);
                list.add(userId005);
                result.onGotMemberList(list);
                return list;
            }
        });
        //配合设置用户信息提供者使用，即可展示用户名
        toSetUserInfoProvider();


        //发送消息
        RongIMClient.getInstance().setOnReceiveMessageListener(new RongIMClient.OnReceiveMessageListener() {
            @Override
            public boolean onReceived(Message message, int left) {
                MessageContent content = message.getContent();
                if (content instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) content;
                    String content1 = textMessage.getContent();// flagship
                }else if(content instanceof ImageMessage){
                    ImageMessage imageMessage = (ImageMessage) content;
                    Uri thumUri = imageMessage.getThumUri();
                }
                return false;
            }
        });

        //消息拦截
        messageInterceptor();

//        RongConfigCenter.featureConfig().enableReference(true);

    }

    private void messageInterceptor() {
        IMCenter.getInstance().setMessageInterceptor(new MessageInterceptor() {
            @Override
            public boolean interceptReceivedMessage(Message message, int left, boolean hasPackage, boolean offline) {
                Log.e("TAG", "interceptReceivedMessage: " + message.getObjectName() );
                return false;
            }

            @Override
            public boolean interceptOnSendMessage(Message message) {
                Log.e("TAG", "interceptOnSendMessage: " );
                return false;
            }

            @Override
            public boolean interceptOnSentMessage(Message message) {

                Log.e("TAG", "interceptOnSentMessage: --" );
                return false;
            }

            @Override
            public boolean interceptOnInsertOutgoingMessage(Conversation.ConversationType type, String targetId, Message.SentStatus sentStatus, MessageContent content, long time) {
                return false;
            }

            @Override
            public boolean interceptOnInsertIncomingMessage(Conversation.ConversationType type, String targetId, String senderId, Message.ReceivedStatus receivedStatus, MessageContent content, long time) {
                return false;
            }
        });
    }

    private void toSetUserInfoProvider() {
        RongIM.getInstance().setUserInfoProvider(new UserDataProvider.UserInfoProvider() {//RongUserInfoManager
            /**
             * 获取设置用户信息. 通过返回的 userId 来封装生产用户信息.
             * @param userId 用户 ID
             */

            @Override
            public UserInfo getUserInfo(String userId) {
                UserInfo userInfo;
                if (userId.equals(userId001)) {
                    userInfo = new UserInfo(userId, "昵称" + userId, user001Portrait);
                } else if (userId.equals(userId002)) {
                    userInfo = new UserInfo(userId, "昵称" + userId, user002Portrait);
                } else if (userId.equals(userId003)) {
                    userInfo = new UserInfo(userId, "昵称" + userId, user003Portrait);
                } else if (userId.equals(userId004)) {
                    userInfo = new UserInfo(userId, "昵称" + userId, user004Portrait);
                } else {
                    userInfo = new UserInfo(userId, "昵称" + userId, user005Portrait);
                }
                RongUserInfoManager.getInstance().refreshUserInfoCache(userInfo);
                return userInfo;
            }
        }, true);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnConversationList:
                //sdk 默认会话列表
//                RouteUtils.routeToConversationListActivity(HomeActivity.this, "");

                //自定义会话列表
                RouteUtils.routeToConversationListActivity(HomeActivity.this, "自定义会话列表");
                break;
            case R.id.btnConversation:
                //跳转到指定会话界面
                RouteUtils.routeToConversationActivity(HomeActivity.this, Conversation.ConversationType.PRIVATE, userId002);
                break;
            case R.id.btnUserInfo:
                //用户信息
                toSetUserInfoProvider();
                break;
            case R.id.btnSendCustomMsg:
                //发送自定义消息
                Conversation.ConversationType conversationType = Conversation.ConversationType.PRIVATE;
                String targetId = userId002;
                // PushContent 内容, 不可为 null
                String pushContent = "我是自定义消息pushContent";
                String pushData = "我是自定义消息pushData";

                //消息扩展
                expansionKey.put("isRead", "YES");
                expansionKey.put("isOpen", "YES");

                CustomMessage messageContent = CustomMessage.obtain(userId001, userName001, "我是自定义消息", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//                IMCustomIntimacyMessage messageContent = IMCustomIntimacyMessage.obtain("我是IMCustomIntimacyMessage-text自定义消息");
//                TextMessage textMessage=TextMessage.obtain("1111");
                messageContent.setExtra("message 携带消息");// message 携带消息
                Message message = Message.obtain(targetId, conversationType, messageContent);
                message.setCanIncludeExpansion(true);//设置消息可扩展
                message.setExpansion(expansionKey);//消息扩展内容

                //TODO 如下是关闭消息的推送通知（自定义消息默认不走推送）
//                message.setMessageConfig(new MessageConfig.Builder().setDisableNotification(true).build());

                RongIMClient.getInstance().sendMessage(message, pushContent, pushData, new IRongCallback.ISendMessageCallback() {
                    /**
                     * 消息发送前回调, 回调时消息已存储数据库
                     * @param message 已存库的消息体
                     */
                    @Override
                    public void onAttached(Message message) {
                        Log.e(TAG, "--> 发送自定义消息 onAttached message=" + message);
                        tvCustomMsg.setText("onAttached：" + message.toString());
                    }

                    /**
                     * 消息发送成功。
                     * @param message 发送成功后的消息体
                     */
                    @Override
                    public void onSuccess(Message message) {
                        msg = message;
                        Log.e(TAG, "--> 发送自定义消息 onSuccess message=" + message);
                        tvCustomMsg.setText("发送成功：" + message.toString());
                    }

                    /**
                     * 消息发送失败
                     * @param message   发送失败的消息体
                     * @param errorCode 具体的错误
                     */
                    @Override
                    public void onError(Message message, RongIMClient.ErrorCode errorCode) {
                        Log.e(TAG, "--> 发送自定义消息 onError errorCode=" + errorCode + " ， message=" + message);
                        tvCustomMsg.setText("发送失败：" + message.toString());
                    }
                });
                break;
            case R.id.btnUpdateMessageExpansion:
                expansionKey.put("isRead", "NO");
                expansionKey.put("isOpen", "NO");
                RongIMClient.getInstance().updateMessageExpansion(expansionKey, msg.getUId(), new RongIMClient.OperationCallback() {
                    @Override
                    public void onSuccess() {
                        Log.e(TAG, "--> 更新消息扩展信息 成功 ");
                        tvCustomMsg.setText("更新成功：" + msg.toString());
                    }

                    @Override
                    public void onError(RongIMClient.ErrorCode errorCode) {
                        Log.e(TAG, "--> 更新消息扩展信息 失败 errorCode=" + errorCode);
                        tvCustomMsg.setText("更新失败：" + msg.toString());
                    }
                });

                break;
            case R.id.btnRemoveMessageExpansion:
                List keyArray = new ArrayList(2);
                keyArray.add("isOpen");
                RongIMClient.getInstance().removeMessageExpansion(keyArray, msg.getUId(), new RongIMClient.OperationCallback() {
                    @Override
                    public void onSuccess() {
                        Log.e(TAG, "--> 删除消息扩展信息 成功 ");
                        tvCustomMsg.setText("删除成功：" + msg.toString());
                    }

                    @Override
                    public void onError(RongIMClient.ErrorCode errorCode) {
                        Log.e(TAG, "--> 删除消息扩展信息 失败 errorCode=" + errorCode);
                        tvCustomMsg.setText("删除失败：" + msg.toString());
                    }
                });
                break;
            case R.id.btnSendImage:
                sendImageMessage();
                break;
            case R.id.btnJoinChatRoom1:
                RongIMClient.getInstance().joinExistChatRoom("globalroom", 0, new RongIMClient.OperationCallback() {
                    @Override
                    public void onSuccess() {
                        Log.e("join","加入聊天室1成功");
                    }

                    @Override
                    public void onError(RongIMClient.ErrorCode errorCode) {
                        Log.e("join","加入聊天室1失败 errorCode ="+errorCode);
                    }
                });
                break;
            case R.id.btnJoinChatRoom2:
                RongIMClient.getInstance().joinExistChatRoom("globalchat", 0, new RongIMClient.OperationCallback() {
                    @Override
                    public void onSuccess() {
                        Log.e("join","加入聊天室2成功");
                    }

                    @Override
                    public void onError(RongIMClient.ErrorCode errorCode) {
                        Log.e("join","加入聊天室2失败 errorCode ="+errorCode);
                    }
                });
                break;

            case R.id.btnSendMsg2ChatRoom1://向聊天室1发送文本消息
                TextMessage textMessage=TextMessage.obtain("聊天室消息");
                message = Message.obtain("globalroom", Conversation.ConversationType.CHATROOM,textMessage);
                RongIMClient.getInstance().sendMessage(message, "", "", new IRongCallback.ISendMessageCallback() {
                    @Override
                    public void onAttached(Message message) {

                    }

                    @Override
                    public void onSuccess(Message message) {
                        Log.e("join","向聊天室1发消息成功");
                    }

                    @Override
                    public void onError(Message message, RongIMClient.ErrorCode errorCode) {
                        Log.e("join","向聊天室1发消息失败 message ="+message+" ， errorCode ="+errorCode);
                    }
                });
                break;

            case R.id.btnSendMsg2ChatRoom2://向聊天室2发送文本消息
                textMessage = TextMessage.obtain("聊天室消息");
                message = Message.obtain("globalchat", Conversation.ConversationType.CHATROOM,textMessage);
                RongIMClient.getInstance().sendMessage(message, "", "", new IRongCallback.ISendMessageCallback() {
                    @Override
                    public void onAttached(Message message) {

                    }

                    @Override
                    public void onSuccess(Message message) {
                        Log.e("join","向聊天室2发消息成功");
                    }

                    @Override
                    public void onError(Message message, RongIMClient.ErrorCode errorCode) {
                        Log.e("join","向聊天室2发消息失败 message ="+message+" ， errorCode ="+errorCode);
                    }
                });
                break;

            case R.id.btnGetHistoryMsgChatRoom1:// 拉取聊天室1历史消息
                RongIMClient.getInstance().getChatroomHistoryMessages("globalroom", 0L, 50, RongIMClient.TimestampOrder.RC_TIMESTAMP_DESC, new IRongCallback.IChatRoomHistoryMessageCallback() {
                    @Override
                    public void onSuccess(List<Message> messages, long syncTime) {
                        Log.e("join","获取聊天室1消息成功");
                    }

                    @Override
                    public void onError(RongIMClient.ErrorCode code) {
                        Log.e("join","获取聊天室1消息失败 code ="+code);
                    }
                });
                break;
            case R.id.btnGetHistoryMsgChatRoom2:// 拉取聊天室2历史消息
                RongIMClient.getInstance().getChatroomHistoryMessages("globalchat", 0L, 50, RongIMClient.TimestampOrder.RC_TIMESTAMP_DESC, new IRongCallback.IChatRoomHistoryMessageCallback() {
                    @Override
                    public void onSuccess(List<Message> messages, long syncTime) {
                        Log.e("join","获取聊天室2消息成功");
                    }

                    @Override
                    public void onError(RongIMClient.ErrorCode code) {
                        Log.e("join","获取聊天室2消息失败 code ="+code);
                    }
                });
                break;
        }

    }

    /**
     * {"appId":"8luwapkv8689l","fromUserId":"23","targetId":"24","targetType":1,"GroupId":"","classname":"RC:ImgMsg","content":{"id":7,"content":"/9j/4AAQSBAAD/2wBDDBAYEBAQEB....","imageUri":"https://780g.oss-cn-zhangjiakou.aliyuncs.com/uploads/20220121/8f330e3258a4b7fe809ee9c72a1744e1.png"},"dateTime":"2022-01-21 15:40:28.920","timestamp":1642750828920,"msgUID":"BUFE-A62U-32C4-01I2","source":"Websocket","isSensitiveWord":"false"}
     *
     */
    private void sendImageMessage() {
        Uri localUri = Uri.parse("https://780g.oss-cn-zhangjiakou.aliyuncs.com/uploads/20220121/8f330e3258a4b7fe809ee9c72a1744e1.png");
        ImageMessage imageMessage = ImageMessage.obtain(localUri, localUri);
        String targetId = "002";
        Conversation.ConversationType conversationType = Conversation.ConversationType.PRIVATE;
        Message message = Message.obtain(targetId, conversationType, imageMessage);

        IMCenter.getInstance().sendMediaMessage(message, null, null, new IRongCallback.ISendMediaMessageCallback() {
            @Override
            public void onProgress(Message message, int i) {
                Log.e(TAG,"--> 发送图片 onProgress - message="+message+" ， i="+i);
            }

            @Override
            public void onCanceled(Message message) {
                Log.e(TAG,"--> 发送图片 onCanceled - message="+message);
            }

            @Override
            public void onAttached(Message message) {
                Log.e(TAG,"--> 发送图片 onAttached - message="+message);
            }

            @Override
            public void onSuccess(Message message) {
                Log.e(TAG,"--> 发送图片 onSuccess - message="+message);
            }

            @Override
            public void onError(final Message message, final RongIMClient.ErrorCode errorCode) {
                Log.e(TAG,"--> 发送图片 onError - message="+message+" ， errorCode="+errorCode);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RongIM.getInstance().disconnect();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        RongIM.getInstance().disconnect();
    }
}
