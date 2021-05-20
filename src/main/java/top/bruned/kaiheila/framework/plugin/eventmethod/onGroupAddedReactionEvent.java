package top.bruned.kaiheila.framework.plugin.eventmethod;

import top.bruned.kaiheila.sdk.wsclient.result.event.GroupAddedReactionEvent.GroupAddedReactionEvent;

public interface onGroupAddedReactionEvent {
    void onEvent(GroupAddedReactionEvent event);
}
