package top.bruned.kaiheila.framework.plugin.eventmethod;

import top.bruned.kaiheila.sdk.wsclient.result.event.PersonDeletedMessageEvent.PersonDeletedMessageEvent;

public interface onPersonDeletedMessageEvent {
    void onEvent(PersonDeletedMessageEvent event);
}
