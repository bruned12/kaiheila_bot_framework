package top.bruned.kaiheila.framework.plugin.eventmethod;

import top.bruned.kaiheila.sdk.wsclient.result.event.GroupDeletedMessageEvent.GroupDeletedMessageEvent;

public interface onGroupDeletedMessageEvent {
    void onEvent(GroupDeletedMessageEvent event);
}
