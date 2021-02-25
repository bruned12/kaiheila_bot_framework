package top.bruned.kaiheila.framework.plugin;

import top.bruned.kaiheila.framework.event.EventChannel;
import top.bruned.kaiheila.sdk.bot.Bot;
import top.bruned.kaiheila.sdk.util.Log;

public class JavaPlugin {
    protected Bot bot;
    protected Log log;
    protected EventChannel eventChannel;

    public void onLoad() {
    }

    public void onEnable() {
    }

    public void onDisable() {
    }

    public void setEventChannel(EventChannel eventChannel) {
        this.eventChannel = eventChannel;
    }

    public Bot getBot() {
        return bot;
    }

    public void setBot(Bot bot) {
        this.bot = bot;
    }

    public Log getLog() {
        return log;
    }

    public void setLog(Log log) {
        this.log = log;
    }
}
