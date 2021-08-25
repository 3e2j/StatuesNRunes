package net.runedar.snr.screenhandler;

import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class StatueScreen extends CottonInventoryScreen<StatueScreenGUI> {
    public StatueScreen(StatueScreenGUI gui, PlayerEntity player, Text title) {
        super(gui, player, title);
    }
}
