package top.bruned.kaiheila.framework.config;

import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.io.IOException;

import static top.bruned.kaiheila.framework.Util.readFileWithString;
import static top.bruned.kaiheila.framework.Util.writerFileWithString;

public class Config {
    private File file;
    private JSONObject jsonObject;

    public Config(File file) {
        this.file = file;
    }

    public void init(String basedata) {
        try {
            if (!file.exists()) {
                file.createNewFile();
                writerFileWithString(file, basedata);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        readConfig();
    }

    public void readConfig() {
        try {
            jsonObject = JSONObject.parseObject(readFileWithString(file));
        } catch (IOException e) {
            init("{}");
        }
    }

    public JSONObject getJsonObject() {
        readConfig();
        return jsonObject;
    }

    public void saveConfig() {
        if (jsonObject != null) {
            writerFileWithString(file, jsonObject.toJSONString());
        }
    }

}
