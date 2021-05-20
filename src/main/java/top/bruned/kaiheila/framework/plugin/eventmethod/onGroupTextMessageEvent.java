package top.bruned.kaiheila.framework.plugin.eventmethod;

import top.bruned.kaiheila.sdk.wsclient.result.event.GroupTextMessageEvent.GroupTextMessageEvent;

public interface onGroupTextMessageEvent {
    void onEvent(GroupTextMessageEvent event);
}
