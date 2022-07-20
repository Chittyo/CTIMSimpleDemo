package com.ctim.simpledemo.zhangrong;//package cn.rongcloud.um;
//
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.lifecycle.ViewModelProvider;
//
//import java.util.List;
//
////import io.rong.callkit.AudioPlugin;
//import io.rong.imkit.RongIM;
//import io.rong.imkit.conversation.ConversationFragment;
//import io.rong.imkit.conversation.MessageListAdapter;
//import io.rong.imkit.conversation.extension.component.inputpanel.InputPanel;
//import io.rong.imkit.conversation.extension.component.plugin.FilePlugin;
//import io.rong.imkit.conversation.extension.component.plugin.IPluginModule;
//import io.rong.imkit.conversation.messgelist.viewmodel.MessageViewModel;
//import io.rong.imkit.event.uievent.ScrollToEndEvent;
//import io.rong.imkit.utils.RouteUtils;
//import io.rong.imlib.RongIMClient;
//import io.rong.imlib.model.Conversation;
//import io.rong.imlib.model.Message;
//
//public class ConversationFragmentNew extends ConversationFragment {
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = super.onCreateView(inflater, container, savedInstanceState);
//        TextView tv = view.findViewById(R.id.textmdmsg);
//        tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.e("tag", "view----" + view);
//                RouteUtils.routeToConversationActivity(getActivity(), Conversation.ConversationType.PRIVATE, "userid1");
//            }
//        });
//
//        Conversation.ConversationType conversationType1;
//        Bundle arguments = getArguments();
//        String targetId = arguments.getString("targetId");
//        String conversationType = arguments.getString("ConversationType");
//        Bundle bundle = new Bundle();
//        bundle.putString("targetId", targetId);
//        if (conversationType.equals("group")) {
//             conversationType1 = Conversation.ConversationType.setValue(getActivity().getIntent().getIntExtra("conversationType", 3));
//            initConversation(targetId, conversationType1,bundle);
//        } else if (conversationType.equals("private")){
//           conversationType1 = Conversation.ConversationType.setValue(getActivity().getIntent().getIntExtra("conversationType", 1));
//            initConversation(targetId, conversationType1,bundle);
//    }
//        return view;
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        if (mMessageViewModel.getUiMessageLiveData().getValue()!=null){
//            mMessageViewModel.getUiMessageLiveData().getValue().clear();
//        }
//
//    }
//}
