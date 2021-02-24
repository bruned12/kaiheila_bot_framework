package top.bruned.kaiheila.framework.plugin.loader;

import top.bruned.kaiheila.framework.plugin.JavaPlugin;
import top.bruned.kaiheila.framework.plugin.annotation.PluginINFO;
import top.bruned.kaiheila.sdk.util.Log;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class PluginLoader {
    private final File PluginPath = new File("plugins");
    private Log log = new Log("PLUGIN");

    public List<JavaPlugin> init(){
        List<JavaPlugin> plugins= new ArrayList<>();;

        for(File pluginFile : PluginPath.listFiles()){
            if (pluginFile.isFile() &&  pluginFile.getName().endsWith(".jar")){
                try {
                    plugins.add(createPluginObject(getMainClass(pluginFile),pluginFile.getPath()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return plugins;
    }
    private JavaPlugin createPluginObject(String classpath,String pluginFile){
        String PluginName = null;
        String Version = null;
        String Author = null;
        Class pluginClass = null;
        URL url = null;
        try {
            url = new URL("file:"+pluginFile);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            log.info(classpath);
            URLClassLoader loader = new URLClassLoader(new URL[] {url});
            pluginClass = Class.forName(classpath,true,loader);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (pluginClass.isAnnotationPresent(PluginINFO.class)){
            PluginINFO annotation = (PluginINFO) pluginClass.getAnnotation(PluginINFO.class);
            PluginName = annotation.PluginName();
            Version = annotation.Version();
            Author = annotation.Author();
            log.info("加载: "+PluginName+" 作者: "+Author +" 版本： "+Version);
        }
        Constructor<JavaPlugin> constructor = null;
        try {
            constructor = pluginClass.getConstructor();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        JavaPlugin plugin = null;
        try {
            plugin = constructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        plugin.setLog(new Log(PluginName));
        return plugin;
    }
    private String getMainClass(File file) throws IOException {
        JarFile jar = null;
        InputStream input = null;
        jar = new JarFile(file);
        JarEntry entry = jar.getJarEntry("plugin.properties");
        input = jar.getInputStream(entry);
        InputStreamReader isr = new InputStreamReader(input);
        BufferedReader reader = new BufferedReader(isr);
        String class_path = reader.readLine().split("=")[1];
        reader.close();
        return class_path.trim();

    }

}
