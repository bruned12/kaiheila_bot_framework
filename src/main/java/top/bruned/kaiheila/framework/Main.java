package top.bruned.kaiheila.framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;
import java.util.Scanner;

import top.bruned.kaiheila.framework.plugin.loader.PluginLoader;
import top.bruned.kaiheila.framework.plugin.loader.PluginManger;
import top.bruned.kaiheila.framework.server.Client;
import top.bruned.kaiheila.sdk.bot.Bot;
import top.bruned.kaiheila.sdk.util.Log;

public class Main {
    public static void main(String[] args) {
        Log log = new Log("MAIN");
        File settingFile = new File("setting.properties");
        File pluginPath = new File("plugins");
        File configPath = new File("config");
        Client client = null;
        PluginManger manger = new PluginManger();
        if(!settingFile.exists()) {
            try {
                settingFile.createNewFile();
                FileWriter settingWrite = new FileWriter(settingFile.getName());
                settingWrite.write("Authorization=Authorization");
                settingWrite.flush();
                settingWrite.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(!pluginPath.exists()){
            pluginPath.mkdir();
        }
        if(!configPath.exists()){
            configPath.mkdir();
        }
        Properties properties = new Properties();
        try {
           properties.load(new FileInputStream(settingFile));
        } catch (IOException e) {
            e.printStackTrace();
        }


        //初始化结束

        PluginLoader loader = new PluginLoader();
        String authorization = properties.getProperty("Authorization");

        Bot bot = new Bot(authorization);
        manger.onLoad(bot);
        log.info("[CONFIG]Authorization: "+authorization);
        String wsUrl = bot.api.getGateway_index(0).getUrl();
        log.info("[WSS]"+wsUrl);

        try {
            client = new Client(new URI(wsUrl),log,manger);
            manger.onEnable();
            client.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        while (true){
            String str = scanner.next();
            log.info(str);
            if (str.equals("stop")) {
                client.stopAll();
                manger.onDisable();
                return;
            }
        }
    }


}
