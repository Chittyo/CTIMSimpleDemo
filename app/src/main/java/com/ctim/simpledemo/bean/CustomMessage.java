package com.ctim.simpledemo.bean;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import io.rong.common.ParcelUtils;
import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MentionedInfo;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.model.UserInfo;


@SuppressLint("ParcelCreator")
@MessageTag(value = "IMDemo:customMessage", flag = MessageTag.ISCOUNTED | MessageTag.ISPERSISTED)
public class CustomMessage extends MessageContent {
    private static final String TAG = "CustomMessage";

    private String userId;
    private String userName;
    private String customMsg;
    private String timeStamp;

    // 快速构建消息对象方法
    public static CustomMessage obtain(String userId, String userName,String customMsg, String timeStamp) {
        CustomMessage model = new CustomMessage();
        model.userId = userId;
        model.userName = userName;
        model.customMsg = customMsg;
        model.timeStamp = timeStamp;
        return model;
    }

    public CustomMessage() {
    }


    /**
     * 创建 CustomMessage(byte[] data) 带有 byte[] 的构造方法用于解析消息内容.
     */
    public CustomMessage(byte[] data) {
        if (data == null) {
            Log.e(TAG, "data is null");
            return;
        }

        String jsonStr = null;
        try {
            jsonStr = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, "UnsupportedEncodingException", e);
        }

        if (jsonStr == null) {
            Log.e(TAG, "jsonStr is null");
            return;
        }

        try {
            JSONObject jsonObj = new JSONObject(jsonStr);

            // 消息携带用户信息时, 自定义消息需添加下面代码
            if (jsonObj.has("user")) {
                setUserInfo(parseJsonToUserInfo(jsonObj.getJSONObject("user")));
            }

            // 用于群组聊天, 消息携带 @ 人信息时, 自定义消息需添加下面代码
            if (jsonObj.has("mentionedInfo")) {
                setMentionedInfo(parseJsonToMentionInfo(jsonObj.getJSONObject("mentionedInfo")));
            }

            // ...
            // 自定义消息, 定义的字段
            // ...

        } catch (JSONException e) {
            Log.e(TAG, "JSONException " + e.getMessage());
        }

    }

    /**
     * 将本地消息对象序列化为消息数据。
     *
     * @return 消息数据。
     */
    @Override
    public byte[] encode() {
        JSONObject jsonObj = new JSONObject();
        try {

            // 消息携带用户信息时, 自定义消息需添加下面代码
            if (getJSONUserInfo() != null) {
                jsonObj.putOpt("user", getJSONUserInfo());
            }

            // 用于群组聊天, 消息携带 @ 人信息时, 自定义消息需添加下面代码
            if (getJsonMentionInfo() != null) {
                jsonObj.putOpt("mentionedInfo", getJsonMentionInfo());
            }

            // ...
            // 自定义消息, 定义的字段.
            jsonObj.putOpt("userId", this.userId);
            jsonObj.putOpt("userName", this.userName);
            jsonObj.putOpt("customMsg", this.customMsg);
            jsonObj.putOpt("timeStamp", this.timeStamp);

            // ...

        } catch (JSONException e) {
            Log.e(TAG, "JSONException " + e.getMessage());
        }

        try {
            return jsonObj.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, "UnsupportedEncodingException ", e);
        }
        return null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public CustomMessage(Parcel parcel) {
        this.userId = parcel.readString();
        this.userName = parcel.readString();
        this.customMsg = parcel.readString();
        this.timeStamp = parcel.readString();
        this.setExtra(ParcelUtils.readFromParcel(parcel));
        this.setUserInfo((UserInfo)ParcelUtils.readFromParcel(parcel, UserInfo.class));
        this.setMentionedInfo((MentionedInfo)ParcelUtils.readFromParcel(parcel, MentionedInfo.class));
        this.setDestruct(ParcelUtils.readIntFromParcel(parcel) == 1);
        this.setDestructTime(ParcelUtils.readLongFromParcel(parcel));
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        ParcelUtils.writeToParcel(dest, this.getUserId());
        ParcelUtils.writeToParcel(dest, this.getUserName());
        ParcelUtils.writeToParcel(dest, this.getCustomMsg());
        ParcelUtils.writeToParcel(dest, this.getTimeStamp());
        ParcelUtils.writeToParcel(dest, this.getExtra());
        ParcelUtils.writeToParcel(dest, this.getUserInfo());
        ParcelUtils.writeToParcel(dest, this.getMentionedInfo());
        ParcelUtils.writeToParcel(dest, this.isDestruct() ? 1 : 0);
        ParcelUtils.writeToParcel(dest, this.getDestructTime());
    }

    public static final Creator<CustomMessage> CREATOR = new Creator<CustomMessage>() {
        @Override
        public CustomMessage createFromParcel(Parcel source) {
            return new CustomMessage(source);
        }

        @Override
        public CustomMessage[] newArray(int size) {
            return new CustomMessage[size];
        }
    };

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCustomMsg() {
        return customMsg;
    }

    public void setCustomMsg(String customMsg) {
        this.customMsg = customMsg;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
