package top.bruned.kaiheila.framework.event;


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


public class EventParse {
    private final EventChannel eventChannel;
    private final Log log = new Log("EVENT处理");

    public EventParse(EventChannel eventChannel) {
        this.eventChannel = eventChannel;
    }

    public void start(EVENT event) {
        REVENT revent = event.getD().toJavaObject(REVENT.class);
        switch (revent.getChannel_type()) {
            case "GROUP": {
                switch (revent.getType()) {
                    case 1:
                        eventChannel.eventBroadCast(event.getD().toJavaObject(GroupTextMessageEvent.class));
                        break;
                    case 2:
                        eventChannel.eventBroadCast(event.getD().toJavaObject(GroupImageMessageEvent.class));
                        break;
                    case 255:
                        switch (revent.extra.getString("type")){
                            case "joined_channel":
                                eventChannel.eventBroadCast(event.getD().toJavaObject(GroupJoinedChannelEvent.class));
                                break;
                            case "exited_channel":
                                eventChannel.eventBroadCast(event.getD().toJavaObject(GroupExitedChannelEvent.class));
                                break;
                            case "deleted_message":
                                eventChannel.eventBroadCast(event.getD().toJavaObject(GroupDeletedMessageEvent.class));
                                break;
                            case "added_reaction":
                                eventChannel.eventBroadCast(event.getD().toJavaObject(GroupAddedReactionEvent.class));
                                break;
                            case "deleted_reaction":
                                eventChannel.eventBroadCast(event.getD().toJavaObject(GroupDeletedReactionEvent.class));
                                break;
                        }
                        break;

                }
                break;
            }
            case "PERSON":{
                switch (revent.getType()){
                    case 1:
                        eventChannel.eventBroadCast(event.getD().toJavaObject(PersonTextMessageEvent.class));
                        break;
                    case 2:
                        eventChannel.eventBroadCast(event.getD().toJavaObject(PersonImageMessageEvent.class));
                        break;
                    case 255:
                        switch (revent.extra.getString("type")){
                            case "private_added_reaction":
                                eventChannel.eventBroadCast(event.getD().toJavaObject(PersonAddedReactionEvent.class));
                                break;
                            case "deleted_private_message":
                                eventChannel.eventBroadCast(event.getD().toJavaObject(PersonDeletedMessageEvent.class));
                                break;
                            case "guild_member_offline":
                                eventChannel.eventBroadCast(event.getD().toJavaObject(PersonGuildMemberOfflineEvent.class));
                                break;
                            case "guild_member_online":
                                eventChannel.eventBroadCast(event.getD().toJavaObject(PersonGuildMemberOnlineEvent.class));
                                break;
                            case "private_deleted_reaction":
                                eventChannel.eventBroadCast(event.getD().toJavaObject(PersonDeletedReactionEvent.class));
                                break;
                        }
                }
                break;
            }

        }

    }
}
