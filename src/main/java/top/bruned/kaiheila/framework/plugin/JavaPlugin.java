package top.bruned.kaiheila.framework.plugin;

import top.bruned.kaiheila.framework.config.Config;
import top.bruned.kaiheila.sdk.bot.Bot;
import top.bruned.kaiheila.sdk.util.Log;

public class JavaPlugin {
    public Bot bot;
    public Log log;
    public String PluginName;
    public String Version;
    public String Author;
    public Config pluginConfig;
    public EventClass EventObject;

    public void init(Bot bot, Log log, Config config) {
        this.bot = bot;
        this.log = log;
        this.pluginConfig = config;
    }

    public void onEnable() {
    }

    public void onDisable() {
    }

    public void setPluginName(String pluginName) {
        this.PluginName = pluginName;
    }

    public void setVersion(String version) {
        this.Version = version;
    }

    public void setAuthor(String author) {
        this.Author = author;
    }

    public void registerEvent(Object object) {
        this.EventObject = (EventClass) object;
    }
}
