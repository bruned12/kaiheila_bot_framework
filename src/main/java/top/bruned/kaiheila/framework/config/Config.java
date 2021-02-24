package top.bruned.kaiheila.framework.config;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private File configPath =  new File("./config");
    private Properties properties = new Properties();
    public File configFile;
    private FileWriter writer;

    public Config(String PluginName){
        this.configFile = new File(configPath, PluginName+".properties");
        if (!this.configFile.exists()){
            try {
                configFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            this.properties.load(new FileInputStream(configFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            this.writer = new FileWriter(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(){
        try {
            writer.write(properties.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key){
        return properties.getProperty(key);
    }
    public void setProperty(String key,String value){
        properties.setProperty(key,value);
    }
}
