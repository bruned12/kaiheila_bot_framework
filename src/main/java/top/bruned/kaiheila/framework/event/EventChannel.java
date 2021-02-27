package top.bruned.kaiheila.framework.event;

import top.bruned.kaiheila.framework.plugin.annotation.EventHandler;
import top.bruned.kaiheila.sdk.util.Log;
import top.bruned.kaiheila.sdk.wsclient.result.event.GroupTextMessageEvent.GroupTextMessageEvent;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class EventChannel {
    private HashMap<Object,Object> eventRunList= new HashMap<>();
    private final List<PluginMethod> GroupTextMessageEventList = new ArrayList<>();
    private final List<PluginMethod> PluginMethodList = new ArrayList<>();
    private final Log log = new Log("事件管道");

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
                } else {
                    continue;
                }
            }
        }

    }

    public void eventBroadCast(Object event) {
        if (event.getClass() == GroupTextMessageEvent.class) {
            for (PluginMethod method : GroupTextMessageEventList) {

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
    }

    public void init() {
        List<PluginMethod> GroupMessageEventPluginTemp = new ArrayList<>();
        for (PluginMethod pluginMethod : PluginMethodList) {
            Class event = pluginMethod.getEventtype();

            if (GroupTextMessageEvent.class.equals(event)) {
                GroupMessageEventPluginTemp.add(pluginMethod);
            }
        }
        Collections.sort(GroupMessageEventPluginTemp, new PluginsComparator());
        for (PluginMethod pluginMethod : GroupMessageEventPluginTemp) {
            GroupTextMessageEventList.add(pluginMethod);
        }
        log.debug("注册完毕");
    }

}
