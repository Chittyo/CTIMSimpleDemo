package com.ctim.simpledemo.zhangrong;//package cn.rongcloud.um;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.FragmentManager;
//import androidx.fragment.app.FragmentTransaction;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//
//import io.rong.imkit.conversation.ConversationFragment;
//import io.rong.imkit.utils.RouteUtils;
//import io.rong.imlib.model.Conversation;
//
//public class ConversationActivity extends AppCompatActivity {
//    FragmentManager manager = getSupportFragmentManager();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_conversation_activity);
//        // 添加会话界面
//
//        FragmentTransaction transaction = manager.beginTransaction();
//        Bundle bundle = new Bundle();
//        bundle.putString("targetId",getIntent().getStringExtra("targetId"));
//        bundle.putString("ConversationType",getIntent().getStringExtra("ConversationType"));
//
//        ConversationFragmentNew conversationFragment = new ConversationFragmentNew();
//        conversationFragment.setArguments(bundle);
//        transaction.replace(R.id.container, conversationFragment);
//        transaction.commit();
//    }
//    @Override
//    protected void onNewIntent(Intent intent) {
//
//        super.onNewIntent(intent);
////        setIntent(intent);
//        FragmentTransaction fragmentTransaction = manager.beginTransaction();
//        ConversationFragmentNew conversationFragment = new ConversationFragmentNew();
//        Bundle bundle = new Bundle();
//        bundle.putString("targetId",intent.getStringExtra("targetId"));
//        bundle.putString("ConversationType",getIntent().getStringExtra("ConversationType"));
//        conversationFragment.setArguments(bundle);
//        fragmentTransaction.replace(R.id.container, conversationFragment);
//        fragmentTransaction.commit();
//    }
//}