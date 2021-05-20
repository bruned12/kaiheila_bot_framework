package top.bruned.kaiheila.framework.plugin.eventmethod;

import top.bruned.kaiheila.sdk.wsclient.result.event.GroupExitedChannelEvent.GroupExitedChannelEvent;

public interface onGroupExitedChannelEvent {
    void onEvent(GroupExitedChannelEvent event);
}
