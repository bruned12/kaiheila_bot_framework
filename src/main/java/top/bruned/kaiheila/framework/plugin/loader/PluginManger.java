package top.bruned.kaiheila.framework.plugin.loader;

import top.bruned.kaiheila.framework.event.EventChannel;
import top.bruned.kaiheila.framework.event.EventParse;
import top.bruned.kaiheila.framework.plugin.JavaPlugin;
import top.bruned.kaiheila.sdk.bot.Bot;

import java.util.List;

public class PluginManger {
    public List<JavaPlugin> plugins;
    public EventChannel eventChannel = new EventChannel();
    public EventParse eventParse = new EventParse(eventChannel);

    public PluginManger() {
        PluginLoader loader = new PluginLoader();
        this.plugins = loader.init();
    }

    public void onLoad() {
        for (JavaPlugin plugin : plugins) {
            plugin.onLoad();
        }

    }

    public void onEnable(Bot bot) {
        for (JavaPlugin plugin : plugins) {
            plugin.setEventChannel(eventChannel);
            plugin.setBot(bot);
            plugin.onEnable();
        }
        this.eventChannel.init();

    }

    public void onDisable() {
        for (JavaPlugin plugin : plugins) {
            plugin.onDisable();
        }
    }
}
