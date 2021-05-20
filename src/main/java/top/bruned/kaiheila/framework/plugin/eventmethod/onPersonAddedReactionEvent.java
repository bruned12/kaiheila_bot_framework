package top.bruned.kaiheila.framework.plugin.eventmethod;

import top.bruned.kaiheila.sdk.wsclient.result.event.PersonAddedReactionEvent.PersonAddedReactionEvent;

public interface onPersonAddedReactionEvent {
    void onEvent(PersonAddedReactionEvent event);
}
