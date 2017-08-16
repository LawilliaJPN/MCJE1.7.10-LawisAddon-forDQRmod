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

		int roomHeight = 8;					// 部屋の高さ
		int roomWidth = 18;					// 部屋の幅（-1）
		int roomCenter = roomWidth /2;		// 部屋の中心

		int roomFloor1Y = -1;
		int roomFloor2Y = 3;

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

		// 「砂岩」を設置
		for (int i = roomFloor1Y -1; i <= roomHeight; i++) {
			for (int x = i; x <= roomWidth -i; x++) {
				for (int z = i; z <= roomWidth -i; z++) {
					world.setBlock(roomX +x, roomY +i, roomZ +z, Blocks.sandstone);
				}
			}
		}

		// 「空気」を設置
		for (int i = roomFloor1Y +1; i <= roomHeight; i++) {
			for (int x = i +1; x <= roomWidth -i -1; x++) {
				for (int z = i +1; z <= roomWidth -i -1; z++) {
					world.setBlockToAir(roomX +x, roomY +i, roomZ +z);
				}
			}
		}

		// 1階の柱の「滑らかな砂岩」を設置
		for (int y = 0; y <= roomFloor2Y -1; y++) {
			world.setBlock(roomX +roomCenter +2, roomY +y, roomZ +roomCenter +2, Blocks.sandstone, 2, 2);
			world.setBlock(roomX +roomCenter +2, roomY +y, roomZ +roomCenter -2, Blocks.sandstone, 2, 2);
			world.setBlock(roomX +roomCenter -2, roomY +y, roomZ +roomCenter +2, Blocks.sandstone, 2, 2);
			world.setBlock(roomX +roomCenter -2, roomY +y, roomZ +roomCenter -2, Blocks.sandstone, 2, 2);
		}

		// 2階部分の床となる「砂岩」を設置
		for (int x = roomFloor2Y; x <= roomWidth -roomFloor2Y; x++) {
			for (int z = roomFloor2Y; z <= roomWidth -roomFloor2Y; z++) {
				world.setBlock(roomX +x, roomY +roomFloor2Y, roomZ +z, Blocks.sandstone);
			}
		}

		// 2階の床の吹き抜け部分となる「空気」を設置
		for (int x = roomCenter -1; x <= roomCenter +1; x++) {
			for (int z = roomCenter -1; z <= roomCenter +1; z++) {
				world.setBlockToAir(roomX +x, roomY +roomFloor2Y, roomZ +z);
			}
		}

		// 2階の出入口となる「滑らかな砂岩」を設置
		for (int y = roomFloor2Y +1; y <= roomFloor2Y +3; y++) {
			for (int x = roomCenter -1; x <= roomCenter +1; x++) {
				world.setBlock(roomX +x, roomY +y, roomZ +roomCenter +5, Blocks.sandstone, 2, 2);
				world.setBlock(roomX +x, roomY +y, roomZ +roomCenter -5, Blocks.sandstone, 2, 2);
			}

			for (int z = roomCenter -1; z <= roomCenter +1; z++) {
				world.setBlock(roomX +roomCenter +5, roomY +y, roomZ +z, Blocks.sandstone, 2, 2);
				world.setBlock(roomX +roomCenter -5, roomY +y, roomZ +z, Blocks.sandstone, 2, 2);
			}
		}

		// 2階の出入口となる「空気」を設置
		for (int y = roomFloor2Y +1; y <= roomFloor2Y +2; y++) {
			for (int i = 4; i <= 5; i++) {
				world.setBlockToAir(roomX +roomCenter, roomY +y, roomZ +roomCenter +i);
				world.setBlockToAir(roomX +roomCenter, roomY +y, roomZ +roomCenter -i);
				world.setBlockToAir(roomX +roomCenter +i, roomY +y, roomZ +roomCenter);
				world.setBlockToAir(roomX +roomCenter -i, roomY +y, roomZ +roomCenter);
			}
		}

		// 2階の出入口となる「砂岩」を設置
		for (int x = roomCenter -1; x <= roomCenter +1; x++) {
			world.setBlock(roomX +x, roomY +roomFloor2Y +3, roomZ +roomCenter +4, Blocks.sandstone);
			world.setBlock(roomX +x, roomY +roomFloor2Y +3, roomZ +roomCenter -4, Blocks.sandstone);
		}
		for (int z = roomCenter -1; z <= roomCenter +1; z++) {
			world.setBlock(roomX +roomCenter +4, roomY +roomFloor2Y +3, roomZ +z, Blocks.sandstone);
			world.setBlock(roomX +roomCenter -4, roomY +roomFloor2Y +3, roomZ +z, Blocks.sandstone);
		}

		// 2階の外の地面となる「砂岩」を設置
		for (int x = roomCenter -2; x <= roomCenter +2; x++) {
			world.setBlock(roomX +x, roomY +roomFloor2Y, roomZ +roomCenter +7, Blocks.sandstone);
			world.setBlock(roomX +x, roomY +roomFloor2Y, roomZ +roomCenter -7, Blocks.sandstone);
			world.setBlock(roomX +x, roomY +roomFloor2Y, roomZ +roomCenter +8, Blocks.sandstone);
			world.setBlock(roomX +x, roomY +roomFloor2Y, roomZ +roomCenter -8, Blocks.sandstone);
			world.setBlock(roomX +x, roomY +roomFloor2Y -1, roomZ +roomCenter +8, Blocks.sandstone);
			world.setBlock(roomX +x, roomY +roomFloor2Y -1, roomZ +roomCenter -8, Blocks.sandstone);
		}
		for (int z = roomCenter -2; z <= roomCenter +2; z++) {
			world.setBlock(roomX +roomCenter +7, roomY +roomFloor2Y, roomZ +z, Blocks.sandstone);
			world.setBlock(roomX +roomCenter -7, roomY +roomFloor2Y, roomZ +z, Blocks.sandstone);
			world.setBlock(roomX +roomCenter +8, roomY +roomFloor2Y, roomZ +z, Blocks.sandstone);
			world.setBlock(roomX +roomCenter -8, roomY +roomFloor2Y, roomZ +z, Blocks.sandstone);
			world.setBlock(roomX +roomCenter +8, roomY +roomFloor2Y -1, roomZ +z, Blocks.sandstone);
			world.setBlock(roomX +roomCenter -8, roomY +roomFloor2Y -1, roomZ +z, Blocks.sandstone);
		}

		/* 装飾 */
		// 1階の床に「青色の羊毛」を設置
		world.setBlock(roomX +roomCenter, roomY +roomFloor1Y, roomZ +roomCenter, Blocks.wool, 11, 2);

		// 1階の床に「橙色の羊毛」を設置
		world.setBlock(roomX +roomCenter, roomY +roomFloor1Y, roomZ +roomCenter +2, Blocks.wool, 1, 2);
		world.setBlock(roomX +roomCenter, roomY +roomFloor1Y, roomZ +roomCenter +3, Blocks.wool, 1, 2);
		world.setBlock(roomX +roomCenter, roomY +roomFloor1Y, roomZ +roomCenter -2, Blocks.wool, 1, 2);
		world.setBlock(roomX +roomCenter, roomY +roomFloor1Y, roomZ +roomCenter -3, Blocks.wool, 1, 2);

		world.setBlock(roomX +roomCenter +2, roomY +roomFloor1Y, roomZ +roomCenter, Blocks.wool, 1, 2);
		world.setBlock(roomX +roomCenter +3, roomY +roomFloor1Y, roomZ +roomCenter, Blocks.wool, 1, 2);
		world.setBlock(roomX +roomCenter -2, roomY +roomFloor1Y, roomZ +roomCenter, Blocks.wool, 1, 2);
		world.setBlock(roomX +roomCenter -3, roomY +roomFloor1Y, roomZ +roomCenter, Blocks.wool, 1, 2);

		world.setBlock(roomX +roomCenter +1, roomY +roomFloor1Y, roomZ +roomCenter +1, Blocks.wool, 1, 2);
		world.setBlock(roomX +roomCenter +1, roomY +roomFloor1Y, roomZ +roomCenter -1, Blocks.wool, 1, 2);
		world.setBlock(roomX +roomCenter -1, roomY +roomFloor1Y, roomZ +roomCenter +1, Blocks.wool, 1, 2);
		world.setBlock(roomX +roomCenter -1, roomY +roomFloor1Y, roomZ +roomCenter -1, Blocks.wool, 1, 2);

		// 1階の外側の柱に「模様入りの砂岩」と「滑らかな砂岩」を設置
		for (int x = roomCenter -5; x <= roomCenter +5; x += 2) {
			world.setBlock(roomX +x, roomY +roomFloor1Y +1, roomZ +roomCenter +6, Blocks.sandstone, 2, 2);
			world.setBlock(roomX +x, roomY +roomFloor1Y +1, roomZ +roomCenter -6, Blocks.sandstone, 2, 2);
			world.setBlock(roomX +x, roomY +roomFloor1Y +2, roomZ +roomCenter +6, Blocks.sandstone, 1, 2);
			world.setBlock(roomX +x, roomY +roomFloor1Y +2, roomZ +roomCenter -6, Blocks.sandstone, 1, 2);
		}

		for (int z = roomCenter -5; z <= roomCenter +5; z += 2) {
			world.setBlock(roomX +roomCenter +6, roomY +roomFloor1Y +1, roomZ +z, Blocks.sandstone, 2, 2);
			world.setBlock(roomX +roomCenter -6, roomY +roomFloor1Y +1, roomZ +z, Blocks.sandstone, 2, 2);
			world.setBlock(roomX +roomCenter +6, roomY +roomFloor1Y +2, roomZ +z, Blocks.sandstone, 1, 2);
			world.setBlock(roomX +roomCenter -6, roomY +roomFloor1Y +2, roomZ +z, Blocks.sandstone, 1, 2);
		}

		// 1階の外側の天井に「砂岩」を設置
		for (int x = roomCenter -6; x <= roomCenter +6; x++) {
			world.setBlock(roomX +x, roomY +roomFloor1Y +3, roomZ +roomCenter +6, Blocks.sandstone);
			world.setBlock(roomX +x, roomY +roomFloor1Y +3, roomZ +roomCenter -6, Blocks.sandstone);
		}

		for (int z = roomCenter -5; z <= roomCenter +5; z++) {
			world.setBlock(roomX +roomCenter +6, roomY +roomFloor1Y +3, roomZ +z, Blocks.sandstone);
			world.setBlock(roomX +roomCenter -6, roomY +roomFloor1Y +3, roomZ +z, Blocks.sandstone);
		}

		/* 光源 */
		// 明るさ確保のための「松明」の設置
		// 橙色の羊毛の上
		/*
		world.setBlock(roomX +roomCenter, roomY +roomFloor1Y +1, roomZ +roomCenter +3, Blocks.torch, 5, 3);
		world.setBlock(roomX +roomCenter, roomY +roomFloor1Y +1, roomZ +roomCenter -3, Blocks.torch, 5, 3);
		world.setBlock(roomX +roomCenter +3, roomY +roomFloor1Y +1, roomZ +roomCenter, Blocks.torch, 5, 3);
		world.setBlock(roomX +roomCenter -3, roomY +roomFloor1Y +1, roomZ +roomCenter, Blocks.torch, 5, 3);
		*/

		// 1階の四隅
		world.setBlock(roomX +roomCenter +6, roomY +roomFloor1Y +1, roomZ +roomCenter +6, Blocks.torch, 5, 3);
		world.setBlock(roomX +roomCenter +6, roomY +roomFloor1Y +1, roomZ +roomCenter -6, Blocks.torch, 5, 3);
		world.setBlock(roomX +roomCenter -6, roomY +roomFloor1Y +1, roomZ +roomCenter +6, Blocks.torch, 5, 3);
		world.setBlock(roomX +roomCenter -6, roomY +roomFloor1Y +1, roomZ +roomCenter -6, Blocks.torch, 5, 3);

		// 2階の四隅
		world.setBlock(roomX +roomCenter +4, roomY +roomFloor2Y +1, roomZ +roomCenter +4, Blocks.torch, 5, 3);
		world.setBlock(roomX +roomCenter +4, roomY +roomFloor2Y +1, roomZ +roomCenter -4, Blocks.torch, 5, 3);
		world.setBlock(roomX +roomCenter -4, roomY +roomFloor2Y +1, roomZ +roomCenter +4, Blocks.torch, 5, 3);
		world.setBlock(roomX +roomCenter -4, roomY +roomFloor2Y +1, roomZ +roomCenter -4, Blocks.torch, 5, 3);

		/* - - - - - - - - - -
		 * 以下、敵のスポーン
		 * - - - - - - - - - */
	}
}
