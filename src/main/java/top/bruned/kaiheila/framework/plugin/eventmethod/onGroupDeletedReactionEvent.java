package top.bruned.kaiheila.framework.plugin.eventmethod;

import top.bruned.kaiheila.sdk.wsclient.result.event.GroupDeletedReactionEvent.GroupDeletedReactionEvent;

public interface onGroupDeletedReactionEvent {
    void onEvent(GroupDeletedReactionEvent event);
}
