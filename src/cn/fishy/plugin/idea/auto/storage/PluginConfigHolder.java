package cn.fishy.plugin.idea.auto.storage;

import cn.fishy.plugin.idea.auto.storage.domain.PluginConfig;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.StoragePathMacros;
import com.intellij.openapi.components.StorageScheme;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.Nullable;

/**
 * User: duxing
 * Date: 2015.8.11
 */

@com.intellij.openapi.components.State(
        name = "PluginConfig",
        storages = {
                @Storage(
                        file = StoragePathMacros.APP_CONFIG + "/autoGenerator.xml"
                )}
)
public class PluginConfigHolder implements PersistentStateComponent<PluginConfig> {
    public PluginConfig pluginConfig = new PluginConfig();
    @Nullable
    @Override
    public PluginConfig getState() {
        return pluginConfig;
    }

    @Override
    public void loadState(PluginConfig state) {
        XmlSerializerUtil.copyBean(state, pluginConfig);
    }

    @Nullable
    public static PluginConfig getPluginConfig() {
        try {
            return ServiceManager.getService(PluginConfigHolder.class).getState();
        }catch (Exception e){
            return null;
        }
    }

}
