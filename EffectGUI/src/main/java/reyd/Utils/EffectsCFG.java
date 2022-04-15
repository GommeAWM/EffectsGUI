package reyd.Utils;

import cn.nukkit.utils.Config;
import reyd.EffectMain;

import java.io.File;

public class EffectsCFG {

    private EffectMain effectMain;
    private File file;
    private Config config;

    public EffectsCFG(EffectMain effectMain){
        this.effectMain = EffectMain.getInstance();
        this.file = new File(effectMain.getDataFolder(), "/config.yml");
        this.config = new Config(this.file, Config.YAML);
    }

    public void createDefaultConfig(){
        this.addDefault("options.messages.giveplayer", "§aYou gave the player §7{target}, §eEffect-id: §b{effect}, §eseconds: §b{seconds}, §eAmplifier: §b{amplifier}");
        this.addDefault("options.messages.give", "§aYou gave Effect, §eEffect-id: §b{effect}, §eseconds: §b{seconds}, §eAmplifier: §b{amplifier}");
        this.addDefault("options.messages.target", "§aThe Player §7{target} §agave you, §eEffect-id: §b{effect}, §eseconds: §b{seconds}, §eAmplifier: §b{amplifier}");
        this.addDefault("options.messages.exception", "§cPlayer cannot be found");
        this.addDefault("options.messages.permission", "§cYou need Permission");
        this.addDefault("options.command.description", "to give effects");
        this.addDefault("options.titel", "EffectsGUI");
    }


    public String giveplayer() {
        return this.config.getString("options.messages.giveplayer");
    }

    public String give() {
        return this.config.getString("options.messages.give");
    }

    public String target() {
        return this.config.getString("options.messages.target");
    }

    public String except() {
        return this.config.getString("options.messages.exception");
    }

    public String permission() {
        return this.config.getString("options.messages.permission");
    }

    public String descr() {
        return this.config.getString("options.command.description");
    }

    public String titel() {
        return this.config.getString("options.titel");
    }

    public void addDefault(String path, Object object){
        if(!this.config.exists(path)){
            this.config.set(path, object);
            this.config.save(this.file);
        }
    }

}
