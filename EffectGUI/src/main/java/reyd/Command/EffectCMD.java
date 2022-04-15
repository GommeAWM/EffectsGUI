package reyd.Command;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.form.element.ElementDropdown;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.element.ElementSlider;
import cn.nukkit.form.element.ElementStepSlider;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.form.window.FormWindowSimple;
import reyd.EffectMain;

import java.util.ArrayList;
import java.util.List;

public class EffectCMD extends Command {

    public EffectCMD(String cmd, String description){
        super(cmd, description);
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {

        if (commandSender instanceof Player){
            Player player = (Player) commandSender;
            List<String> players = new ArrayList<>();

            for (Player all : Server.getInstance().getOnlinePlayers().values()){
                players.add(all.getName());
            }

            if (!player.hasPermission("reyd.effectsgui")){
                player.sendMessage(EffectMain.getAuctionConfig().permission());
                return true;
            }

            FormWindowCustom formWindowCustom = new FormWindowCustom(EffectMain.getAuctionConfig().titel());
            formWindowCustom.addElement(new ElementSlider("Effect (ID)", 1, 26, 1, 1));
            formWindowCustom.addElement(new ElementSlider("Duration (Seconds)", 1, 60, 1, 1));
            formWindowCustom.addElement(new ElementSlider("Effect Strength", 1, 5, 1, 0));
            if (player.hasPermission("reyd.effectgive")){
                formWindowCustom.addElement(new ElementDropdown("Players", players));
            }

            player.showFormWindow(formWindowCustom);

        }

        return true;
    }
}
