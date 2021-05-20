package top.bruned.kaiheila.framework.plugin.eventmethod;

import top.bruned.kaiheila.sdk.wsclient.result.event.PersonTextMessageEvent.PersonTextMessageEvent;

public interface onPersonTextMessageEvent {
    void onEvent(PersonTextMessageEvent event);
}
