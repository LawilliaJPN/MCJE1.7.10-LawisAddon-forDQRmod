package lawisAddonDqr1.event.rooms.room3;

import java.util.Random;

import lawisAddonDqr1.config.LadDebug;
import lawisAddonDqr1.event.rooms.LadRoomID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class LadRoomNether {
	/*
	 * ネザーラック基調の戦闘部屋
	 */
	public static void setRoom(World world, EntityPlayer player) {
		Random rand = new Random();
		int roomDirection = LadRoomID.getDirectionRoom(player, 0);

		int roomX = (int)player.posX;			// 部屋の起点となるX座標
		int roomZ = (int)player.posZ;			// 部屋の起点となるZ座標
		int roomY = (int)player.posY -1;		// 部屋の起点となるY座標（-1）

		int roomHeight = 6;					// 部屋の高さ
		int roomWidth = 12;					// 部屋の幅（-1）
		int roomCenter = roomWidth /2;		// 部屋の中心

		int roomFloor1Y = -1;					// 1階の床の高さ
		
		int floorType = rand.nextInt(0);		// 部屋の種類(床配置)
		
		// [Debug] 戦闘部屋固定時に生成方向がチャット表示される（デバッグ用）
		if (LadDebug.getDebugRoom() >=0) {
			player.addChatMessage(new ChatComponentTranslation("roomDirection == " + roomDirection));
			player.addChatMessage(new ChatComponentTranslation("floorType == " + floorType));
			player.addChatMessage(new ChatComponentTranslation("difOfRoom == " + LadRoomID.getDifOfRoom()));
		}
		
		/* - - - - - - - - - -
		 * 以下、TODO 部屋の生成
		 * - - - - - - - - - */
		
		/* - - - - - - - - - -
		 * 以下、TODO 敵のスポーン
		 * - - - - - - - - - */
		
		/* - - - - - - - - - -
		 * 以下、TODO 報酬
		 * - - - - - - - - - */
	}
}
