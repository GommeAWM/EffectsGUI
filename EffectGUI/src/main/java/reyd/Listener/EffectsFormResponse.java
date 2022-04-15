package reyd.Listener;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.response.FormResponseCustom;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.potion.Effect;
import reyd.EffectMain;

public class EffectsFormResponse implements Listener {

    @EventHandler
    public void onResponseForm(PlayerFormRespondedEvent event){
        Player player = event.getPlayer();

        if (event.getWindow() instanceof FormWindowCustom){
            FormWindowCustom formWindowCustom = (FormWindowCustom) event.getWindow();
            FormResponseCustom formResponseCustom = (FormResponseCustom) event.getResponse();

            if (formWindowCustom.getTitle().equals(EffectMain.getAuctionConfig().titel())){
                if (player.hasPermission("reyd.effectgive")){
                    Effect effect = Effect.getEffect((int) formResponseCustom.getSliderResponse(0)).setDuration((20 * (int) formResponseCustom.getSliderResponse(1))).setAmplifier( (int) formResponseCustom.getSliderResponse(2));
                    String who = String.valueOf(formResponseCustom.getResponse(3));

                    if (Server.getInstance().getPlayer(who) == null){
                        player.sendMessage(EffectMain.getAuctionConfig().except());
                        return;
                    }
                    Player getwho = Server.getInstance().getPlayer(who);

                    getwho.addEffect(effect);

                    // taret

                    String whomsg = EffectMain.getAuctionConfig().target().replace("{effect}", String.valueOf(formResponseCustom.getSliderResponse(0)));
                    String whomsg1 = whomsg.replace("{seconds}", String.valueOf(formResponseCustom.getSliderResponse(1)));
                    String whomsg2 = whomsg1.replace("{amplifier}", String.valueOf(formResponseCustom.getSliderResponse(2)));
                    String whomsgresult = whomsg2.replace("{target}", player.getName());

                    // player who give effect

                    String plmsg = EffectMain.getAuctionConfig().giveplayer().replace("{effect}", String.valueOf(formResponseCustom.getSliderResponse(0)));
                    String plmsg1 = plmsg.replace("{seconds}", String.valueOf(formResponseCustom.getSliderResponse(1)));
                    String plmsg2 = plmsg1.replace("{amplifier}", String.valueOf(formResponseCustom.getSliderResponse(2)));
                    String plmsgresult = plmsg2.replace("{target}", String.valueOf(formResponseCustom.getResponse(3)));

                    getwho.sendMessage(whomsgresult);
                    player.sendMessage(plmsgresult);

                } else {

                    // player

                    String msg = EffectMain.getAuctionConfig().give().replace("{effect}", String.valueOf(formResponseCustom.getSliderResponse(0)));
                    String msg1 = msg.replace("{seconds}", String.valueOf(formResponseCustom.getSliderResponse(1)));
                    String result = msg1.replace("{amplifier}", String.valueOf(formResponseCustom.getSliderResponse(2)));

                    Effect effect = Effect.getEffect((int) formResponseCustom.getSliderResponse(0)).setDuration((int) formResponseCustom.getSliderResponse(1)).setAmplifier((int) formResponseCustom.getSliderResponse(2));
                    player.addEffect(effect);
                    player.sendMessage(result);
                }
            }

        }
    }

}
