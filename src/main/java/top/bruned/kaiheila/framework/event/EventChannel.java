package top.bruned.kaiheila.framework.event;

import top.bruned.kaiheila.framework.plugin.annotation.EventHandler;
import top.bruned.kaiheila.sdk.util.Log;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

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

public class EventChannel {
    private HashMap<Class,List<PluginMethod>> eventRunList= new HashMap<Class,List<PluginMethod>>();
    private final List<PluginMethod> PluginMethodList = new ArrayList<PluginMethod>();
    private final Log log = new Log("事件管道");

    public EventChannel(){
        List<Class> registerEvent = Arrays.asList(
                GroupAddedReactionEvent.class, GroupDeletedReactionEvent.class,
                GroupDeletedMessageEvent.class, GroupExitedChannelEvent.class,
                GroupImageMessageEvent.class, GroupJoinedChannelEvent.class,
                GroupTextMessageEvent.class, PersonAddedReactionEvent.class,
                PersonDeletedMessageEvent.class, PersonDeletedReactionEvent.class,
                PersonGuildMemberOfflineEvent.class, PersonGuildMemberOnlineEvent.class,
                PersonImageMessageEvent.class, PersonTextMessageEvent.class
        );
        for (Class clz : registerEvent){
            eventRunList.put(clz,new ArrayList<PluginMethod>());
        }
    }
    public void registerEventClass(Object eventObject) {
        Class clz = eventObject.getClass();
        Method[] methods = clz.getDeclaredMethods();
        for (Method method : methods) {
            log.debug(method.getName());
            Annotation[] annotations = method.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof EventHandler) {
                    EventHandler eventHandler = (EventHandler) annotation;
                    PluginMethodList.add(new PluginMethod(eventHandler.priority(), eventHandler.ignoreCancelled(), method, eventObject));
                }
            }
        }

    }

    public void eventBroadCast(Object event) {
        for (PluginMethod method : eventRunList.get(event.getClass())) {
            try {
                method.getMethod().invoke(method.getEventObject(), event);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                log.debug(e.getCause().getMessage());
                e.printStackTrace();
            }
        }
    }

    public void init() {
        for (PluginMethod pluginMethod : PluginMethodList) {
            eventRunList.get(pluginMethod.getEventtype()).add(pluginMethod);
        }
        for (Map.Entry<Class,List<PluginMethod>> map : eventRunList.entrySet()){
            eventRunList.get(map.getKey()).sort(new PluginsComparator());
        }
        log.debug(eventRunList.toString());
        log.debug("注册完毕");
    }

}
