package lawisAddonDqr1.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import lawisAddonDqr1.api.event.LadEnemySpawnEvent;
import lawisAddonDqr1.event.rooms.RoomID;
import net.minecraft.util.ChatComponentTranslation;

public class TestEventHundler {
	@SubscribeEvent
	public void TestEvent(LadEnemySpawnEvent event) {
		event.player.addChatMessage(new ChatComponentTranslation(event.enemy.getCommandSenderName() + "が あらわれるよ！"));
		event.player.addChatMessage(new ChatComponentTranslation("戦闘部屋は" + RoomID.getNameRoom(event.roomID) + "だよ！" ));
	}
}
