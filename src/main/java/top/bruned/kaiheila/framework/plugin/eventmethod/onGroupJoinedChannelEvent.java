package top.bruned.kaiheila.framework.plugin.eventmethod;

import top.bruned.kaiheila.sdk.wsclient.result.event.GroupJoinedChannelEvent.GroupJoinedChannelEvent;

public interface onGroupJoinedChannelEvent {
    void onEvent(GroupJoinedChannelEvent event);
}
