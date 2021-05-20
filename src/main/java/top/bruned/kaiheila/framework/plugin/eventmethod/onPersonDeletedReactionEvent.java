package top.bruned.kaiheila.framework.plugin.eventmethod;

import top.bruned.kaiheila.sdk.wsclient.result.event.PersonDeletedReactionEvent.PersonDeletedReactionEvent;

public interface onPersonDeletedReactionEvent {
    void onEvent(PersonDeletedReactionEvent event);
}
