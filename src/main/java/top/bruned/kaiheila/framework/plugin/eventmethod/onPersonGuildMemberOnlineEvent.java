package top.bruned.kaiheila.framework.plugin.eventmethod;

import top.bruned.kaiheila.sdk.wsclient.result.event.PersonGuildMemberOnlineEvent.PersonGuildMemberOnlineEvent;

public interface onPersonGuildMemberOnlineEvent {
    void onEvent(PersonGuildMemberOnlineEvent event);
}
