package top.bruned.kaiheila.framework.plugin;

import top.bruned.kaiheila.framework.plugin.eventmethod.*;
import top.bruned.kaiheila.sdk.bot.Bot;
import top.bruned.kaiheila.sdk.util.Log;
import top.bruned.kaiheila.sdk.wsclient.result.event.GroupAddedReactionEvent.GroupAddedReactionEvent;
import top.bruned.kaiheila.sdk.wsclient.result.event.GroupDeletedMessageEvent.GroupDeletedMessageEvent;
import top.bruned.kaiheila.sdk.wsclient.result.event.GroupDeletedReactionEvent.GroupDeletedReactionEvent;
import top.bruned.kaiheila.sdk.wsclient.result.event.GroupExitedChannelEvent.GroupExitedChannelEvent;
import top.bruned.kaiheila.sdk.wsclient.result.event.GroupImageMessageEvent.GroupImageMessageEvent;
import top.bruned.kaiheila.sdk.wsclient.result.event.GroupJoinedChannelEvent.GroupJoinedChannelEvent;
import top.bruned.kaiheila.sdk.wsclient.result.event.GroupTextMessageEvent.GroupTextMessageEvent;
import top.bruned.kaiheila.sdk.wsclient.result.event.PersonAddedReactionEvent.PersonAddedReactionEvent;
import top.bruned.kaiheila.sdk.wsclient.result.event.PersonDeletedMessageEvent.PersonDeletedMessageEvent;
import top.bruned.kaiheila.sdk.wsclient.result.event.PersonDeletedReactionEvent.PersonDeletedReactionEvent;
import top.bruned.kaiheila.sdk.wsclient.result.event.PersonGuildMemberOfflineEvent.PersonGuildMemberOfflineEvent;
import top.bruned.kaiheila.sdk.wsclient.result.event.PersonGuildMemberOnlineEvent.PersonGuildMemberOnlineEvent;
import top.bruned.kaiheila.sdk.wsclient.result.event.PersonImgaeMessageEvent.PersonImageMessageEvent;
import top.bruned.kaiheila.sdk.wsclient.result.event.PersonTextMessageEvent.PersonTextMessageEvent;

public class EventClass implements onGroupAddedReactionEvent, onGroupDeletedMessageEvent, onGroupDeletedReactionEvent, onGroupExitedChannelEvent, onGroupImageMessageEvent, onGroupJoinedChannelEvent, onGroupTextMessageEvent, onPersonAddedReactionEvent, onPersonDeletedMessageEvent, onPersonDeletedReactionEvent, onPersonGuildMemberOfflineEvent, onPersonGuildMemberOnlineEvent, onPersonTextMessageEvent ,onPersonImageMessageEvent{
    public Bot bot;
    public Log log;

    public void init(Bot bot, Log log) {
        this.bot = bot;
        this.log = log;
    }

    @Override
    public void onEvent(GroupAddedReactionEvent event) {

    }

    @Override
    public void onEvent(GroupDeletedMessageEvent event) {

    }

    @Override
    public void onEvent(GroupDeletedReactionEvent event) {

    }

    @Override
    public void onEvent(GroupExitedChannelEvent event) {

    }

    @Override
    public void onEvent(GroupImageMessageEvent event) {

    }

    @Override
    public void onEvent(GroupJoinedChannelEvent event) {

    }

    @Override
    public void onEvent(GroupTextMessageEvent event) {

    }

    @Override
    public void onEvent(PersonAddedReactionEvent event) {

    }

    @Override
    public void onEvent(PersonDeletedMessageEvent event) {

    }

    @Override
    public void onEvent(PersonDeletedReactionEvent event) {

    }

    @Override
    public void onEvent(PersonGuildMemberOfflineEvent event) {

    }

    @Override
    public void onEvent(PersonGuildMemberOnlineEvent event) {

    }

    @Override
    public void onEvent(PersonTextMessageEvent event) {

    }

    @Override
    public void onEvent(PersonImageMessageEvent event) {

    }
}
