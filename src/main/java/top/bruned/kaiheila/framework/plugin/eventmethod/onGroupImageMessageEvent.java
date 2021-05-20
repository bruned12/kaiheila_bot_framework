package top.bruned.kaiheila.framework.plugin.eventmethod;

import top.bruned.kaiheila.sdk.wsclient.result.event.GroupImageMessageEvent.GroupImageMessageEvent;

public interface onGroupImageMessageEvent {
    void onEvent(GroupImageMessageEvent event);
}
