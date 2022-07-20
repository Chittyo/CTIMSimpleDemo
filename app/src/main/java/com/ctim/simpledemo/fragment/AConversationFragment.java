package com.ctim.simpledemo.fragment;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import io.rong.imkit.conversation.ConversationFragment;
import io.rong.imkit.conversation.MessageListAdapter;
import io.rong.imkit.widget.refresh.api.RefreshLayout;

public class AConversationFragment extends ConversationFragment {

    @Override
    protected MessageListAdapter onResolveAdapter() {
        return super.onResolveAdapter();

    }

    @Override
    public void onRefresh(@NonNull @NotNull RefreshLayout refreshLayout) {
        super.onRefresh(refreshLayout);
    }
}
