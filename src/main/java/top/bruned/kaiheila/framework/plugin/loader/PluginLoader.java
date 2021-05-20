package top.bruned.kaiheila.framework.plugin.loader;

import top.bruned.kaiheila.framework.plugin.JavaPlugin;
import top.bruned.kaiheila.framework.plugin.annotation.PluginINFO;
import top.bruned.kaiheila.sdk.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class PluginLoader {
    private final Log log = new Log("PLUGIN");
    private final File PluginPath;
    private URLClassLoader loader;

    public PluginLoader(File pluginPath) {
        this.PluginPath = pluginPath;
    }

    public List<JavaPlugin> init() {
        List<JavaPlugin> plugins = new ArrayList<>();
        //插件主类
        List<String> pluginClassList = new ArrayList<>();

        //插件URL 喂给URLClassloader
        List<URL> pluginUrlList = new ArrayList<>();

        //遍历plugin目录 粗判断
        try {
            for (File pluginFile : Objects.requireNonNull(PluginPath.listFiles())) {
                if (pluginFile.isFile() && pluginFile.getName().endsWith(".jar")) {
                    pluginClassList.add(getMainClass(new JarFile(pluginFile)));
                    pluginUrlList.add(pluginFile.toURI().toURL());
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

        //获取loader
        loader = new URLClassLoader(pluginUrlList.toArray(new URL[pluginUrlList.size()]));
        //获取JavaPlugin对象
        for (String realPluginFile : pluginClassList) {
            try {
                plugins.add(createPluginObject(realPluginFile));
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        return plugins;
    }

    private JavaPlugin createPluginObject(String classpath) throws MalformedURLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String PluginName;
        String Version;
        String Author;
        Class<JavaPlugin> pluginClass;

        pluginClass = (Class<JavaPlugin>) Class.forName(classpath, true, this.loader);
        JavaPlugin plugin = pluginClass.getConstructor().newInstance();
        //解析注解
        PluginINFO annotation = pluginClass.getAnnotation(PluginINFO.class);

        PluginName = annotation.PluginName();
        plugin.setPluginName(PluginName);
        Version = annotation.Version();
        plugin.setVersion(Version);
        Author = annotation.Author();
        plugin.setAuthor(Author);
        log.info("插件: " + PluginName + " 作者: " + Author + " 版本： " + Version);
        //返回对象
        return plugin;
    }

    private String getMainClass(JarFile jar) {
        String class_path = "";
        try {
            JarEntry entry = jar.getJarEntry("plugin.properties");
            BufferedReader reader = new BufferedReader(new InputStreamReader(jar.getInputStream(entry)));
            class_path = reader.readLine().split("=")[1].trim();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return class_path;

    }

}
