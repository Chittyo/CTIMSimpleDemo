package com.ctim.simpledemo.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ctim.simpledemo.R;

import io.rong.imkit.conversationlist.ConversationListFragment;

public class MyConversationListActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_conversation_list);

        ConversationListFragment conversationListFragment = new ConversationListFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, conversationListFragment);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {

    }
}
