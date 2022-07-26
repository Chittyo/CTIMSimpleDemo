package com.ctim.simpledemo.plugins;

import java.util.List;
import java.util.ListIterator;

import io.rong.imkit.conversation.extension.DefaultExtensionConfig;
import io.rong.imkit.conversation.extension.component.plugin.FilePlugin;
import io.rong.imkit.conversation.extension.component.plugin.IPluginModule;
import io.rong.imlib.model.Conversation;

public class MyExtensionConfig extends DefaultExtensionConfig {

    @Override
    public List<IPluginModule> getPluginModules(Conversation.ConversationType conversationType, String targetId) {
        List<IPluginModule> pluginModules = super.getPluginModules(conversationType,targetId);
        ListIterator<IPluginModule> iterator = pluginModules.listIterator();

        // 删除扩展项
        while (iterator.hasNext()) {
            IPluginModule integer = iterator.next();
            // 以删除 FilePlugin 为例
            if (integer instanceof FilePlugin) {
                iterator.remove();
            }
        }

        // 增加扩展项, 以 ConectionPlugin 为例
        pluginModules.add(new MyPlugin());
        return pluginModules;
    }
}
