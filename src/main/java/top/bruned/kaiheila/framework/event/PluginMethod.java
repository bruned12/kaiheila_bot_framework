package top.bruned.kaiheila.framework.event;

import java.lang.reflect.Method;

public class PluginMethod{
    private int priority;
    private Boolean ignoreCancelled;
    private Method method;
    private Class eventtype;
    private Object eventObject;
    public PluginMethod(int priority, Boolean ignoreCancelled, Method method,Object eventObject){
        this.priority = priority;
        this.ignoreCancelled = ignoreCancelled;
        this.method = method;
        this.eventtype = method.getParameters()[0].getType();
        this.eventObject =eventObject;
    }

    public int getPriority() {
        return priority;
    }

    public Boolean getIgnoreCancelled() {
        return ignoreCancelled;
    }

    public Method getMethod() {
        return method;
    }

    public Class getEventtype() {
        return eventtype;
    }

    public Object getEventObject() {
        return eventObject;
    }
}

