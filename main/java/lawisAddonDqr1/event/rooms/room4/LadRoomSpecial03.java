package lawisAddonDqr1.event.rooms.room4;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class LadRoomSpecial03 {
	/*
	 * 動画の番外編で製作した特殊な戦闘部屋
	 *
	 * [Unimplemented]下層の部屋にすることにしたため、実装途中で中断
	 */
	public static void setRoom(World world, EntityPlayer player) {
		int roomX = (int)player.posX;			// 部屋の起点となるX座標
		int roomZ = (int)player.posZ -1;		// 部屋の起点となるZ座標（-1）
		int roomY = (int)player.posY -2;		// 部屋の起点となるY座標（-2）

		int roomHeight = 6;					// 部屋の高さ
		int roomWidth = 20;					// 部屋の幅（-1）
		int roomCenter = roomWidth /2;		// 部屋の中心

		// プレイヤーが中心になるように生成(仮)
		roomX -= roomCenter;
		roomZ -= roomCenter;

		/* - - - - - - - - - -
		 * 以下、部屋の生成
		 * - - - - - - - - - */

		/* 空間 */
		// 「空気」を設置
		for (int x = 0; x <= roomWidth; x++) {
			for (int z = 0; z <= roomWidth; z++) {
				for (int y = 0; y <= roomHeight +2; y++) {
					world.setBlockToAir(roomX +x, roomY +y, roomZ +z);
				}
			}
		}

		// 地面の「黒曜石」の設置
		for (int x = 0; x <= roomWidth; x++) {
			for (int z = 0; z <= roomWidth; z++) {
				for (int y = -2; y <= -1; y++) {
					world.setBlock(roomX +x, roomY +y, roomZ +z, Blocks.obsidian);
				}
			}
		}

		/* - - - - - - - - - -
		 * 以下、敵のスポーン
		 * - - - - - - - - - */
	}
}
