package lawisAddonDqr1.event.rooms.room4;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class LadRoomDebug {
	public static void setRoom(World world, EntityPlayer player) {
		Random rand = new Random();

		int roomX = (int)player.posX;			// 部屋の起点となるX座標
		int roomZ = (int)player.posZ;			// 部屋の起点となるZ座標
		int roomY = (int)player.posY;			// 部屋の起点となるY座標
	}
}
