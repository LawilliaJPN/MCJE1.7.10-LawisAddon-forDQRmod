package lawisAddonDqr1.event.rooms.room2;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class LadRoomPyramid {
	/*
	 * ピラミッドの戦闘部屋
	 *
	 * [Unimplemented] 実装途中。
	 */
	public static void setRoom(World world, EntityPlayer player) {
		Random rand = new Random();

		int roomX = (int)player.posX;			// 部屋の起点となるX座標
		int roomZ = (int)player.posZ -1;		// 部屋の起点となるZ座標（-1）
		int roomY = (int)player.posY -1;		// 部屋の起点となるY座標（-1）

		int roomHeight = 6;					// 部屋の高さ
		int roomWidth = 16;					// 部屋の幅（-1）
		int roomCenter = roomWidth /2;		// 部屋の中心

		// プレイヤーが中心になるように生成(仮)
		roomX -= roomCenter;
		roomZ -= roomCenter;

		/* - - - - - - - - - -
		 * 以下、部屋の生成
		 * - - - - - - - - - */

		/* 空間 */
		// 「砂岩ブロック」を設置
		for (int x = 0; x <= roomWidth; x++) {
			for (int z = 0; z <= roomWidth; z++) {
				for (int y = -1; y <= roomHeight; y++) {
					world.setBlock(roomX +x, roomY +y, roomZ +z, Blocks.sandstone);
				}
			}
		}

		// 「空気」を設置
		for (int x = 1; x <= roomWidth +1; x++) {
			for (int z = 1; z <= roomWidth +1; z++) {
				for (int y = 0; y <= roomHeight; y++) {
					world.setBlockToAir(roomX +x, roomY +y, roomZ +z);
				}
			}
		}

		/* - - - - - - - - - -
		 * 以下、敵のスポーン
		 * - - - - - - - - - */
	}
}
