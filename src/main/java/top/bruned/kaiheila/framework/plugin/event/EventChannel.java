package top.bruned.kaiheila.framework.plugin.event;

import top.bruned.kaiheila.framework.plugin.EventClass;
import top.bruned.kaiheila.sdk.util.Log;
import top.bruned.kaiheila.sdk.wsclient.base.EVENT;
import top.bruned.kaiheila.sdk.wsclient.result.REVENT;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EventChannel {
    private final List<EventClass> PluginEventList = new ArrayList<>();
    private final Log log = new Log("事件管道");
    private HashMap<String,HashMap<Integer,Integer>> dict;
    public void init() {
        log.debug("事件方法注册完毕");
    }

    public void registerEventClass(EventClass eventObject) {
        PluginEventList.add(eventObject);
    }

    public void start(EVENT event) {
        REVENT revent = event.getD().toJavaObject(REVENT.class);
        switch (revent.getChannel_type()) {
            case "GROUP": {
                switch (revent.getType()) {
                    case 1:
                        for(EventClass obj:PluginEventList)
                            obj.onEvent(event.getD().toJavaObject(GroupTextMessageEvent.class));
                        break;
                    case 2:for(EventClass obj:PluginEventList)
                        obj.onEvent(
                        event.getD().toJavaObject(GroupImageMessageEvent.class));
                        break;
                    case 255:
                        switch (revent.extra.getString("type")) {
                            case "joined_channel":
                                for(EventClass obj:PluginEventList)
                                    obj.onEvent(
                                event.getD().toJavaObject(GroupJoinedChannelEvent.class));
                                break;
                            case "exited_channel":
                                for(EventClass obj:PluginEventList)
                                    obj.onEvent(
                                event.getD().toJavaObject(GroupExitedChannelEvent.class));
                                break;
                            case "deleted_message":
                                for(EventClass obj:PluginEventList)
                                    obj.onEvent(
                                event.getD().toJavaObject(GroupDeletedMessageEvent.class));
                                break;
                            case "added_reaction":
                                for(EventClass obj:PluginEventList)
                                    obj.onEvent(
                                event.getD().toJavaObject(GroupAddedReactionEvent.class));
                                break;
                            case "deleted_reaction":
                                for(EventClass obj:PluginEventList)
                                    obj.onEvent(
                                event.getD().toJavaObject(GroupDeletedReactionEvent.class));
                                break;
                        }
                        break;

                }
                break;
            }
            case "PERSON": {
                switch (revent.getType()) {
                    case 1:
                        for(EventClass obj:PluginEventList)
                            obj.onEvent(
                        event.getD().toJavaObject(PersonTextMessageEvent.class));
                        break;
                    case 2:
                        for(EventClass obj:PluginEventList)
                            obj.onEvent(
                        event.getD().toJavaObject(PersonImageMessageEvent.class));
                        break;
                    case 255:
                        switch (revent.extra.getString("type")) {
                            case "private_added_reaction":
                                for(EventClass obj:PluginEventList)
                                    obj.onEvent(
                                event.getD().toJavaObject(PersonAddedReactionEvent.class));
                                break;
                            case "deleted_private_message":
                                for(EventClass obj:PluginEventList)
                                    obj.onEvent(
                                event.getD().toJavaObject(PersonDeletedMessageEvent.class));
                                break;
                            case "guild_member_offline":
                                for(EventClass obj:PluginEventList)
                                    obj.onEvent(
                                event.getD().toJavaObject(PersonGuildMemberOfflineEvent.class));
                                break;
                            case "guild_member_online":
                                for(EventClass obj:PluginEventList)
                                    obj.onEvent(
                                event.getD().toJavaObject(PersonGuildMemberOnlineEvent.class));
                                break;
                            case "private_deleted_reaction":
                                for(EventClass obj:PluginEventList)
                                    obj.onEvent(
                                event.getD().toJavaObject(PersonDeletedReactionEvent.class));
                                break;
                            case "message_btn_click":
                                log.debug("=========" + event);
                                break;
                        }
                }
                break;
            }
        }
    }

}
