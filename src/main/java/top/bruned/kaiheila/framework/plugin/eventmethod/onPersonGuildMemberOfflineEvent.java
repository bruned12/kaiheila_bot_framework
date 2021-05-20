package top.bruned.kaiheila.framework.plugin.eventmethod;

import top.bruned.kaiheila.sdk.wsclient.result.event.PersonGuildMemberOfflineEvent.PersonGuildMemberOfflineEvent;

public interface onPersonGuildMemberOfflineEvent {
    void onEvent(PersonGuildMemberOfflineEvent event);
}
