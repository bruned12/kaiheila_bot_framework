package top.bruned.kaiheila.framework.plugin.eventmethod;

import top.bruned.kaiheila.sdk.wsclient.result.event.PersonImgaeMessageEvent.PersonImageMessageEvent;

public interface onPersonImageMessageEvent {
    void onEvent(PersonImageMessageEvent event);
}
