package top.bruned.kaiheila.framework.plugin.loader;

import top.bruned.kaiheila.framework.config.Config;
import top.bruned.kaiheila.framework.plugin.JavaPlugin;
import top.bruned.kaiheila.framework.plugin.event.EventChannel;
import top.bruned.kaiheila.sdk.bot.Bot;
import top.bruned.kaiheila.sdk.util.Log;

import java.io.File;
import java.util.List;

public class PluginManger {
    public List<JavaPlugin> plugins;
    public EventChannel eventChannel = new EventChannel();
    public String configDir;

    private Bot bot;

    public PluginManger(File pluginPath, File configPath) {
        PluginLoader loader = new PluginLoader(pluginPath);
        this.plugins = loader.init();
        configDir = configPath.getPath();
    }

    public void onEnable() {
        for (JavaPlugin plugin : plugins) {
            Log log = new Log(plugin.PluginName);
            plugin.init(this.bot, log, new Config(new File(configDir + "/" + plugin.PluginName + ".json")));
            plugin.onEnable();
            plugin.EventObject.init(this.bot, log);
            eventChannel.registerEventClass(plugin.EventObject);
        }
        this.eventChannel.init();
    }

    public void onDisable() {
        for (JavaPlugin plugin : plugins) {
            plugin.onDisable();
        }
    }

    public void setBot(Bot bot) {
        this.bot = bot;
    }
}
