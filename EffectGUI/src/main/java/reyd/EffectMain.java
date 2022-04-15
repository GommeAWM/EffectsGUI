package reyd;

import cn.nukkit.command.SimpleCommandMap;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginManager;
import reyd.Command.EffectCMD;
import reyd.Listener.EffectsFormResponse;
import reyd.Utils.EffectsCFG;

public class EffectMain extends PluginBase {

    private static EffectsCFG effectsCFG;

    private static EffectMain instance;

    @Override
    public void onEnable() {
        effectsCFG = new EffectsCFG(this);
        effectsCFG.createDefaultConfig();
        instance = this;
        register();
        this.getLogger().info("§fEnable: §aEffectGUI");
    }

    @Override
    public void onDisable() {
        this.getLogger().info("§fDisable: §cEffectGUI");
    }

    private void register(){
        SimpleCommandMap simpleCommandMap = getServer().getCommandMap();
        simpleCommandMap.register("help", new EffectCMD("effects", EffectMain.getAuctionConfig().descr()));

        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new EffectsFormResponse(), this);
    }

    public static EffectMain getInstance(){
        return instance;
    }

    public static EffectsCFG getAuctionConfig(){
        return effectsCFG;
    }

}
