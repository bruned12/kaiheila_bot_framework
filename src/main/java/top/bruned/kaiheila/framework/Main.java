package top.bruned.kaiheila.framework;

import top.bruned.kaiheila.framework.wsclient.Client;
import top.bruned.kaiheila.framework.config.Config;
import top.bruned.kaiheila.framework.plugin.loader.PluginManger;
import top.bruned.kaiheila.sdk.bot.Bot;
import top.bruned.kaiheila.sdk.util.Log;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

public class Main {
    private static Config settingObject;
    private static final File settingFile = new File("setting.json");
    private static final File pluginPath = new File("plugins");
    private static final File configPath = new File("config");
    private static final File librariesPath = new File("libraries");
    private static final Log log = new Log("MAIN");
    private static Client client = null;

    public static void main(String[] args) {
        init();
        PluginManger manger = new PluginManger(pluginPath, configPath);

        //初始化结束
        String authorization = settingObject.getJsonObject().getString("Authorization");

        Bot bot = new Bot(authorization);
        manger.setBot(bot);
        log.info("[CONFIG]Authorization: " + authorization);
        String wsUrl = bot.gateway_index(true);
        log.info("[WSS]" + wsUrl);
        try {
            client = new Client(new URI(wsUrl), log, manger);
            client.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        manger.onEnable();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String str = scanner.next();
            log.info(str);
            if (str.equals("stop")) {
                client.stopAll();
                manger.onDisable();
                return;
            }
        }
    }

    public static void init() {
        settingObject = new Config(settingFile);
        settingObject.init("{\"Authorization\":\"ChangeMe!!!\"}");
        if (!librariesPath.exists()) {
            librariesPath.mkdir();
        }
        if (!pluginPath.exists()) {
            pluginPath.mkdir();
        }
        if (!configPath.exists()) {
            configPath.mkdir();
        }
    }


}
