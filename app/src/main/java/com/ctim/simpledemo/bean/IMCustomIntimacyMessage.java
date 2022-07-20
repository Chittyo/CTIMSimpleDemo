package com.ctim.simpledemo.bean;


import android.os.Parcel;
import android.text.TextUtils;
import android.util.Log;

import com.ctim.simpledemo.utils.JsonHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import io.rong.common.ParcelUtils;
import io.rong.common.RLog;
import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MentionedInfo;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.model.UserInfo;

@MessageTag(value = "Mchat:Intimacy", flag = MessageTag.NONE)
public class IMCustomIntimacyMessage extends MessageContent {
    private final String TAG = this.getClass().getName();
    private String content;
    protected String extra;

    private MessageIntimacyBean bean;
    public static final Creator<IMCustomIntimacyMessage> CREATOR = new Creator<IMCustomIntimacyMessage>() {
        public IMCustomIntimacyMessage createFromParcel(Parcel source) {
            return new IMCustomIntimacyMessage(source);
        }

        public IMCustomIntimacyMessage[] newArray(int size) {
            return new IMCustomIntimacyMessage[size];
        }
    };

    public String getExtra() {
        return this.extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public byte[] encode() {
        JSONObject jsonObj = new JSONObject();

        try {
            if (!TextUtils.isEmpty(this.getContent())) {
                jsonObj.put("content", this.getContent());
            }

            if (!TextUtils.isEmpty(this.getExtra())) {
                jsonObj.put("extra", this.getExtra());
            }

            if (this.getJSONUserInfo() != null) {
                jsonObj.putOpt("user", this.getJSONUserInfo());
            }

            if (this.getJsonMentionInfo() != null) {
                jsonObj.putOpt("mentionedInfo", this.getJsonMentionInfo());
            }

            jsonObj.put("isBurnAfterRead", this.isDestruct());
            jsonObj.put("burnDuration", this.getDestructTime());
        } catch (JSONException var4) {
            Log.e(TAG, "JSONException " + var4.getMessage());
        }

        try {
            return jsonObj.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException var3) {
            Log.e(TAG, "UnsupportedEncodingException ", var3);
            return null;
        }
    }

    protected IMCustomIntimacyMessage() {
    }

    public static IMCustomIntimacyMessage obtain(String text) {
        Log.e("发送自定义消息", "--> IMCustomIntimacyMessage obtain text="+text);
        IMCustomIntimacyMessage model = new IMCustomIntimacyMessage();
        model.setContent(text);
        Log.e("发送自定义消息", "--> IMCustomIntimacyMessage obtain model="+model);
        return model;
    }

    public IMCustomIntimacyMessage(byte[] data) {
        if (data == null) {
            Log.e(TAG, "data is null ");
        } else {
            String jsonStr = null;

            try {
                if (data.length >= 40960) {
                    Log.e(TAG, "TextMessage length is larger than 40KB, length :" + data.length);
                }

                jsonStr = new String(data, "UTF-8");
            } catch (UnsupportedEncodingException var5) {
                RLog.e("TextMessage", "UnsupportedEncodingException ", var5);
            }

            if (jsonStr == null) {
                Log.e(TAG, "jsonStr is null ");
            } else {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    if (jsonObj.has("content")) {
                        this.setContent(jsonObj.optString("content"));
                    }

                    if (jsonObj.has("extra")) {
                        this.setExtra(jsonObj.optString("extra"));
                    }

                    if (jsonObj.has("user")) {
                        this.setUserInfo(this.parseJsonToUserInfo(jsonObj.getJSONObject("user")));
                    }

                    if (jsonObj.has("mentionedInfo")) {
                        this.setMentionedInfo(this.parseJsonToMentionInfo(jsonObj.getJSONObject("mentionedInfo")));
                    }

                    if (jsonObj.has("isBurnAfterRead")) {
                        this.setDestruct(jsonObj.getBoolean("isBurnAfterRead"));
                    }

                    if (jsonObj.has("burnDuration")) {
                        this.setDestructTime(jsonObj.getLong("burnDuration"));
                    }
                } catch (JSONException var4) {
                    Log.e(TAG, "JSONException " + var4.getMessage());
                }

            }
        }
    }

    public void setContent(String content) {
        this.content = content;
        Log.e("发送自定义消息", "--> setContent content="+content);
//        bean = GsonUtilsKt.fromJson(this.content, MessageIntimacyBean.class);
        try {
            bean = JsonHelper.fromJson(this.content, MessageIntimacyBean.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        LogUtils.INSTANCE.d("接收到的消息  " + callInviteBean.toString());
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        ParcelUtils.writeToParcel(dest, this.getExtra());
        ParcelUtils.writeToParcel(dest, this.content);
        ParcelUtils.writeToParcel(dest, this.getUserInfo());
        ParcelUtils.writeToParcel(dest, this.getMentionedInfo());
        ParcelUtils.writeToParcel(dest, this.isDestruct() ? 1 : 0);
        ParcelUtils.writeToParcel(dest, this.getDestructTime());
    }

    public IMCustomIntimacyMessage(Parcel in) {
        this.setExtra(ParcelUtils.readFromParcel(in));
        this.setContent(ParcelUtils.readFromParcel(in));
        this.setUserInfo((UserInfo) ParcelUtils.readFromParcel(in, UserInfo.class));
        this.setMentionedInfo((MentionedInfo) ParcelUtils.readFromParcel(in, MentionedInfo.class));
        this.setDestruct(ParcelUtils.readIntFromParcel(in) == 1);
        this.setDestructTime(ParcelUtils.readLongFromParcel(in));
    }

    @Override
    public String toString() {
        return "IMCustomIntimacyMessage{" +
                "TAG='" + TAG + '\'' +
                ", content='" + content + '\'' +
                ", extra='" + extra + '\'' +
                ", bean=" + bean +
                '}';
    }

    public IMCustomIntimacyMessage(String content) {
        this.setContent(content);
    }

    public String getContent() {
        return this.content;
    }


    public MessageIntimacyBean getBean() {
        return bean;
    }

}

