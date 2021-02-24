package top.bruned.kaiheila.framework.plugin.loader;

import top.bruned.kaiheila.framework.event.EventChannel;
import top.bruned.kaiheila.framework.event.EventParse;
import top.bruned.kaiheila.framework.plugin.JavaPlugin;
import top.bruned.kaiheila.sdk.bot.Bot;

import java.util.List;

public class PluginManger {
    public List<JavaPlugin> plugins;
    public EventChannel eventChannel= new EventChannel();
    public EventParse eventParse = new EventParse(eventChannel);
    public PluginManger() {
        PluginLoader loader = new PluginLoader();
        this.plugins = loader.init();

    }

    public void onLoad(Bot bot) {

        for(JavaPlugin plugin:plugins){
            plugin.setEventChannel(eventChannel);
            plugin.setBot(bot);
            plugin.onLoad();
        }
        this.eventChannel.init();
    }

    public void onEnable() {
        for(JavaPlugin plugin:plugins){

            plugin.onEnable();
        }

    }

    public void onDisable(){
        for(JavaPlugin plugin:plugins){
            plugin.onDisable();
        }
    }
}
