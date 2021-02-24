package top.bruned.kaiheila.framework.event;

import java.util.Comparator;

public class PluginsComparator implements Comparator<PluginMethod> {

    @Override
    public int compare(PluginMethod plugin1, PluginMethod plugin2) {
        if (plugin1.getPriority()> plugin2.getPriority()){
            return -1;
        }
        else if(plugin1.getPriority()< plugin2.getPriority()){
            return 1;
        }
        return 0;
    }
}
