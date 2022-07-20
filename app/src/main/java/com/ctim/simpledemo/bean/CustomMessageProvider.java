package com.ctim.simpledemo.bean;

import android.content.Context;
import android.text.Spannable;
import android.view.ViewGroup;

import java.util.List;

import io.rong.imkit.conversation.messgelist.provider.BaseMessageItemProvider;
import io.rong.imkit.model.UiMessage;
import io.rong.imkit.widget.adapter.IViewProviderListener;
import io.rong.imkit.widget.adapter.ViewHolder;
import io.rong.imlib.model.MessageContent;

public class CustomMessageProvider extends BaseMessageItemProvider<CustomMessage> {

    public CustomMessageProvider(){
        //修改模板属性
        mConfig.showReadState = true;//单聊会话中是否在消息旁边显示已读回执状态，默认 false
        mConfig.showPortrait = true;//是否显示头像，默认 true
        mConfig.centerInHorizontal = true;//消息内容是否横向居中，默认 false
        mConfig.showWarning = true;//是否显示未发送成功警告，默认 true
        mConfig.showProgress = true;//是否显示发送进度，默认 true
        mConfig.showSummaryWithName = true;//是否在会话的内容体里显示发送者名字，默认 true
        mConfig.showContentBubble = true;//是否需要展示气泡，默认 true
    }

    /**
     * 创建 ViewHolder
     * @param parent 父 ViewGroup
     * @param viewType 视图类型
     * @return ViewHolder
     */
    @Override
    protected ViewHolder onCreateMessageContentViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    /**
     * 设置消息视图里各 view 的值
     * @param holder ViewHolder
     * @param parentHolder 父布局的 ViewHolder
     * @param customMessage 此展示模板对应的消息
     * @param uiMessage {@link UiMessage}
     * @param position 消息位置
     * @param list 列表
     * @param listener ViewModel 的点击事件监听器。如果某个子 view 的点击事件需要 ViewModel 处理，可通过此监听器回调。
     */
    @Override
    protected void bindMessageContentViewHolder(ViewHolder holder, ViewHolder parentHolder, CustomMessage customMessage, UiMessage uiMessage, int position, List<UiMessage> list, IViewProviderListener<UiMessage> listener) {
        //TODO 演示 customMessage 替换成您自定义消息类的对象
//        Context context = holder.getContext();
//        String changeType = customMessage.getChangeType();
//        String changeResult = customMessage.getChangeResult();
//        String contact = customMessage.getContact();
//        String userName = customMessage.getUserName();

    }

    /**
     * @param holder ViewHolder
     * @param customMessage 自定义消息
     * @param uiMessage {@link UiMessage}
     * @param position 位置
     * @param list 列表数据
     * @param listener ViewModel 的点击事件监听器。如果某个子 view 的点击事件需要 ViewModel 处理，可通过此监听器回调。
     * @return 点击事件是否被消费
     */
    @Override
    protected boolean onItemClick(ViewHolder holder, CustomMessage customMessage, UiMessage uiMessage, int position, List<UiMessage> list, IViewProviderListener<UiMessage> listener) {
        return false;
    }

    /**
     * 根据消息内容，判断是否为本模板需要展示的消息类型
     *
     * @param messageContent 消息内容
     * @return 本模板是否处理。
     */
    @Override
    protected boolean isMessageViewType(MessageContent messageContent) {
        return messageContent instanceof CustomMessage;
    }

    /**
     * 在会话列表页某条会话最后一条消息为该类型消息时，会话里需要展示的内容。
     * 比如: 图片消息在会话里需要展示为"图片"，那返回对应的字符串资源即可。
     * @param context 上下文
     * @param customMessage 消息内容
     * @return 会话里需要展示的字符串资源
     */
    @Override
    public Spannable getSummarySpannable(Context context, CustomMessage customMessage) {
        return null;
    }
}
