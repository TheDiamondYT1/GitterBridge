/*
 *    _____ _ _   _            ____       _     _
 * / ____(_) | | |          |  _ \     (_)   | |
 * | |  __ _| |_| |_ ___ _ __| |_) |_ __ _  __| | __ _  ___
 * | | |_ | | __| __/ _ \ '__|  _ <| '__| |/ _` |/ _` |/ _ \
 * | |__| | | |_| ||  __/ |  | |_) | |  | | (_| | (_| |  __/
 * \_____|_|\__|\__\___|_|  |____/|_|  |_|\__,_|\__, |\___|
 *                                               __/ |
 *                                              |___/
 *
 * By TheDiamondYT
 */
package tk.thediamondyt.gitterbridge;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.plugin.PluginBase;

import static cn.nukkit.utils.TextFormat.*;

import tk.thediamondyt.gitter4j.api.Gitter4J;

public class Main extends PluginBase implements Listener {

    private Gitter4J gitter;
    private String consolePrefix;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(this, this);
        this.consolePrefix = getConfig().getString("consolePrefix");

        getServer().getLogger().info(consolePrefix + "Setting access token...");

        if(getConfig().getString("access_token") != "") {
            gitter.setAccessToken(getConfig().getString("access_token")); }
        else {
            getServer().getLogger().error(consolePrefix + RED + "No access token defined. Disabling...");
            getPluginLoader().disablePlugin(this);
        }

        getServer().getLogger().info(consolePrefix + "GitterBridge by TheDiamondYT Loaded!");
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onChat(PlayerChatEvent e) {
        gitter.getRoom(getConfig().getString("room_id")).sendMessage(e.getMessage());
    }
}
