package top.bruned.kaiheila.framework.event;


import top.bruned.kaiheila.sdk.util.Log;
import top.bruned.kaiheila.sdk.wsclient.base.EVENT;
import top.bruned.kaiheila.sdk.wsclient.result.REVENT;
import top.bruned.kaiheila.sdk.wsclient.result.event.GroupMessageEvent.GroupMessageEvent;


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
                        eventChannel.eventBroadCast(event.getD().toJavaObject(GroupMessageEvent.class));
                }
            }
        }

    }
}
