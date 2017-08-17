package lawisAddonDqr1.event.rooms.room2;

import java.util.Random;

import lawisAddonDqr1.config.LadDebug;
import lawisAddonDqr1.event.rooms.LadRoomCore;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class LadRoomMineShaft {
	/*
	 * バニラの「廃坑」をモチーフにした戦闘部屋
	 *
	 * [Unimplemented] 大枠実装完了、細かい部分やスポーン設定は未実装。
	 */
	public static void setRoom(World world, EntityPlayer player) {
		Random rand = new Random();
		int roomDirection = LadRoomCore.getDirectionRoom(player, 0);

		int roomX = (int)player.posX;			// 部屋の起点となるX座標
		int roomZ = (int)player.posZ -1;		// 部屋の起点となるZ座標（-1）
		int roomY = (int)player.posY -1;		// 部屋の起点となるY座標（-1）

		int roomHeight = 6;					// 部屋の高さ
		int roomWidth = 12;					// 部屋の幅（-1）
		int roomCenter = roomWidth /2;		// 部屋の中心

		int roomFloor1Y = -1;					// 1階の床の高さ
		int roomFloor2Y = 3;					// 2階の床の高さ

		// [Debug] 戦闘部屋固定時に生成方向がチャット表示される（デバッグ用）
		if (LadDebug.getDebugRoom() >=0) {
			player.addChatMessage(new ChatComponentTranslation("roomDirection == " + roomDirection));
		}

		// プレイヤーの向きから部屋の起点となる座標を決める
		switch (roomDirection) {
		case 0:
			roomX -= 1;
			roomZ -= roomCenter;
			break;
		case 1:
			roomX -= roomCenter;
			roomZ -= 1;
			break;
		case 2:
			roomX -= roomWidth -1;
			roomZ -= roomCenter;
			break;
		case 3:
			roomX -= roomCenter;
			roomZ -= roomWidth -1;
			break;
		}
		roomY -= roomFloor2Y;

		/* - - - - - - - - - -
		 * 以下、部屋の生成
		 * - - - - - - - - - */

		/* 空間 */
		// 「オークの木材」の設置
		for (int x = 0; x <= roomWidth; x++) {
			for (int z = 0; z <= roomWidth; z++) {
				for (int y = roomFloor1Y; y <= roomHeight; y++) {
					world.setBlock(roomX +x, roomY +y, roomZ +z, Blocks.planks);
				}
			}
		}

		// 「空気」を設置
		for (int x = 4; x <= roomWidth -4; x++) {
			for (int z = 1; z <= 3; z++) {
				for (int y = 0; y <= roomHeight; y++) {
					if (y != roomFloor2Y) {
						world.setBlockToAir(roomX +x, roomY +y, roomZ +z);
						world.setBlockToAir(roomX +x, roomY +y, roomZ +roomWidth -z);
					}
				}
			}
		}

		// 「空気」を設置
		for (int x = 1; x <= 3; x++) {
			for (int z = 4; z <= roomWidth -4; z++) {
				for (int y = 0; y <= roomHeight; y++) {
					if (y != roomFloor2Y) {
						world.setBlockToAir(roomX +x, roomY +y, roomZ +z);
						world.setBlockToAir(roomX +roomWidth -x, roomY +y, roomZ +z);
					}
				}
			}
		}

		// 「空気」を設置
		for (int y = 0; y <= roomHeight; y++) {
			world.setBlockToAir(roomX +2, roomY +y, roomZ +2);
			world.setBlockToAir(roomX +2, roomY +y, roomZ);
			world.setBlockToAir(roomX +2, roomY +y, roomZ +1);
			world.setBlockToAir(roomX +2, roomY +y, roomZ +3);
			world.setBlockToAir(roomX, roomY +y, roomZ +2);
			world.setBlockToAir(roomX +1, roomY +y, roomZ +2);
			world.setBlockToAir(roomX +3, roomY +y, roomZ +2);

			world.setBlockToAir(roomX +2, roomY +y, roomZ +roomWidth -2);
			world.setBlockToAir(roomX +2, roomY +y, roomZ +roomWidth);
			world.setBlockToAir(roomX +2, roomY +y, roomZ +roomWidth -1);
			world.setBlockToAir(roomX +2, roomY +y, roomZ +roomWidth -3);
			world.setBlockToAir(roomX, roomY +y, roomZ +roomWidth -2);
			world.setBlockToAir(roomX +1, roomY +y, roomZ +roomWidth -2);
			world.setBlockToAir(roomX +3, roomY +y, roomZ +roomWidth -2);

			world.setBlockToAir(roomX +roomWidth -2, roomY +y, roomZ +2);
			world.setBlockToAir(roomX +roomWidth -2, roomY +y, roomZ);
			world.setBlockToAir(roomX +roomWidth -2, roomY +y, roomZ +1);
			world.setBlockToAir(roomX +roomWidth -2, roomY +y, roomZ +3);
			world.setBlockToAir(roomX +roomWidth, roomY +y, roomZ +2);
			world.setBlockToAir(roomX +roomWidth -1, roomY +y, roomZ +2);
			world.setBlockToAir(roomX +roomWidth -3, roomY +y, roomZ +2);

			world.setBlockToAir(roomX +roomWidth -2, roomY +y, roomZ +roomWidth -2);
			world.setBlockToAir(roomX +roomWidth -2, roomY +y, roomZ +roomWidth);
			world.setBlockToAir(roomX +roomWidth -2, roomY +y, roomZ +roomWidth -1);
			world.setBlockToAir(roomX +roomWidth -2, roomY +y, roomZ +roomWidth -3);
			world.setBlockToAir(roomX +roomWidth, roomY +y, roomZ +roomWidth -2);
			world.setBlockToAir(roomX +roomWidth -1, roomY +y, roomZ +roomWidth -2);
			world.setBlockToAir(roomX +roomWidth -3, roomY +y, roomZ +roomWidth -2);

			for (int x = roomCenter -1; x <= roomCenter +1; x++) {
				for (int z = roomCenter -1; z <= roomCenter +1; z++) {
					world.setBlockToAir(roomX +x, roomY +y, roomZ +z);
				}
			}
		}

		// 「空気」を設置
		for (int y = 0; y <= roomHeight -1; y++) {
			if (y != roomFloor2Y) {
				world.setBlockToAir(roomX +roomCenter, roomY +y, roomZ +roomCenter +2);
				world.setBlockToAir(roomX +roomCenter, roomY +y, roomZ +roomCenter -2);
				world.setBlockToAir(roomX +roomCenter +2, roomY +y, roomZ +roomCenter);
				world.setBlockToAir(roomX +roomCenter -2, roomY +y, roomZ +roomCenter);
			}
		}

		/* 装飾 */
		// 「土ブロック」の設置
		for (int x = roomCenter -2; x <= roomCenter +2; x++) {
			for (int z = roomCenter -2; z <= roomCenter +2; z++) {
				world.setBlock(roomX +x, roomY +roomFloor1Y, roomZ +z, Blocks.dirt);
			}
		}

		// 「フェンス」の設置
		for (int y = 1; y <= 2; y++) {
			// 1階
			world.setBlock(roomX +roomCenter +1, roomY +roomFloor1Y +y, roomZ +1, Blocks.fence);
			world.setBlock(roomX +roomCenter -1, roomY +roomFloor1Y +y, roomZ +1, Blocks.fence);
			world.setBlock(roomX +roomCenter +1, roomY +roomFloor1Y +y, roomZ +3, Blocks.fence);
			world.setBlock(roomX +roomCenter -1, roomY +roomFloor1Y +y, roomZ +3, Blocks.fence);

			world.setBlock(roomX +roomCenter +1, roomY +roomFloor1Y +y, roomZ +roomWidth -1, Blocks.fence);
			world.setBlock(roomX +roomCenter -1, roomY +roomFloor1Y +y, roomZ +roomWidth -1, Blocks.fence);
			world.setBlock(roomX +roomCenter +1, roomY +roomFloor1Y +y, roomZ +roomWidth -3, Blocks.fence);
			world.setBlock(roomX +roomCenter -1, roomY +roomFloor1Y +y, roomZ +roomWidth -3, Blocks.fence);

			world.setBlock(roomX +1, roomY +roomFloor1Y +y, roomZ +roomCenter +1, Blocks.fence);
			world.setBlock(roomX +1, roomY +roomFloor1Y +y, roomZ +roomCenter -1, Blocks.fence);
			world.setBlock(roomX +3, roomY +roomFloor1Y +y, roomZ +roomCenter +1, Blocks.fence);
			world.setBlock(roomX +3, roomY +roomFloor1Y +y, roomZ +roomCenter -1, Blocks.fence);

			world.setBlock(roomX +roomWidth -1, roomY +roomFloor1Y +y, roomZ +roomCenter +1, Blocks.fence);
			world.setBlock(roomX +roomWidth -1, roomY +roomFloor1Y +y, roomZ +roomCenter -1, Blocks.fence);
			world.setBlock(roomX +roomWidth -3, roomY +roomFloor1Y +y, roomZ +roomCenter +1, Blocks.fence);
			world.setBlock(roomX +roomWidth -3, roomY +roomFloor1Y +y, roomZ +roomCenter -1, Blocks.fence);

			// 2階
			world.setBlock(roomX +roomCenter +1, roomY +roomFloor2Y +y, roomZ +roomCenter +2, Blocks.fence);
			world.setBlock(roomX +roomCenter -1, roomY +roomFloor2Y +y, roomZ +roomCenter +2, Blocks.fence);
			world.setBlock(roomX +roomCenter +1, roomY +roomFloor2Y +y, roomZ +roomCenter -2, Blocks.fence);
			world.setBlock(roomX +roomCenter -1, roomY +roomFloor2Y +y, roomZ +roomCenter -2, Blocks.fence);

			world.setBlock(roomX +roomCenter +2, roomY +roomFloor2Y +y, roomZ +roomCenter +1, Blocks.fence);
			world.setBlock(roomX +roomCenter +2, roomY +roomFloor2Y +y, roomZ +roomCenter -1, Blocks.fence);
			world.setBlock(roomX +roomCenter -2, roomY +roomFloor2Y +y, roomZ +roomCenter +1, Blocks.fence);
			world.setBlock(roomX +roomCenter -2, roomY +roomFloor2Y +y, roomZ +roomCenter -1, Blocks.fence);
		}

		// 「オークの木材」の設置
		for (int x = 1; x <= 3; x++) {
			world.setBlock(roomX +x, roomY +roomFloor1Y +3, roomZ +roomCenter +1, Blocks.planks);
			world.setBlock(roomX +x, roomY +roomFloor1Y +3, roomZ +roomCenter -1, Blocks.planks);
			world.setBlock(roomX +roomWidth -x, roomY +roomFloor1Y +3, roomZ +roomCenter +1, Blocks.planks);
			world.setBlock(roomX +roomWidth -x, roomY +roomFloor1Y +3, roomZ +roomCenter -1, Blocks.planks);
		}

		// 「オークの木材」の設置
		for (int z = 1; z <= 3; z++) {
			world.setBlock(roomX +roomCenter +1, roomY +roomFloor1Y +3, roomZ +z, Blocks.planks);
			world.setBlock(roomX +roomCenter -1, roomY +roomFloor1Y +3, roomZ +z, Blocks.planks);
			world.setBlock(roomX +roomCenter +1, roomY +roomFloor1Y +3, roomZ +roomWidth -z, Blocks.planks);
			world.setBlock(roomX +roomCenter -1, roomY +roomFloor1Y +3, roomZ +roomWidth -z, Blocks.planks);
		}

		// 「レール」の設置
		for (int x = roomCenter -2; x <= roomCenter +2; x++) {
			world.setBlock(roomX +x, roomY +roomFloor1Y +1, roomZ +2, Blocks.rail);
			world.setBlock(roomX +x, roomY +roomFloor1Y +1, roomZ +roomWidth -2, Blocks.rail);
		}

		// 「レール」の設置
		for (int z = roomCenter -2; z <= roomCenter +2; z++) {
			world.setBlock(roomX +2, roomY +roomFloor1Y +1, roomZ +z, Blocks.rail);
			world.setBlock(roomX +roomWidth -2, roomY +roomFloor1Y +1, roomZ +z, Blocks.rail);
		}

		// 「蜘蛛の巣」の設置
		world.setBlock(roomX +roomCenter +2, roomY +roomFloor1Y +3, roomZ +3, Blocks.web);
		world.setBlock(roomX +roomCenter -2, roomY +roomFloor1Y +3, roomZ +3, Blocks.web);
		world.setBlock(roomX +roomCenter +2, roomY +roomFloor1Y +3, roomZ +roomWidth -3, Blocks.web);
		world.setBlock(roomX +roomCenter -2, roomY +roomFloor1Y +3, roomZ +roomWidth -3, Blocks.web);
		world.setBlock(roomX +3, roomY +roomFloor1Y +3, roomZ +roomCenter +2, Blocks.web);
		world.setBlock(roomX +3, roomY +roomFloor1Y +3, roomZ +roomCenter -2, Blocks.web);
		world.setBlock(roomX +roomWidth -3, roomY +roomFloor1Y +3, roomZ +roomCenter +2, Blocks.web);
		world.setBlock(roomX +roomWidth -3, roomY +roomFloor1Y +3, roomZ +roomCenter -2, Blocks.web);

		// 明るさ確保のための「松明」の設置(1階)
		world.setBlock(roomX +roomCenter +2, roomY +roomFloor1Y +3, roomZ +2, Blocks.torch, 1, 3);
		world.setBlock(roomX +roomCenter -2, roomY +roomFloor1Y +3, roomZ +2, Blocks.torch, 2, 3);
		world.setBlock(roomX +roomCenter +2, roomY +roomFloor1Y +3, roomZ +roomWidth -2, Blocks.torch, 1, 3);
		world.setBlock(roomX +roomCenter -2, roomY +roomFloor1Y +3, roomZ +roomWidth -2, Blocks.torch, 2, 3);
		world.setBlock(roomX +2, roomY +roomFloor1Y +3, roomZ +roomCenter +2, Blocks.torch, 3, 3);
		world.setBlock(roomX +2, roomY +roomFloor1Y +3, roomZ +roomCenter -2, Blocks.torch, 4, 3);
		world.setBlock(roomX +roomWidth -2, roomY +roomFloor1Y +3, roomZ +roomCenter +2, Blocks.torch, 3, 3);
		world.setBlock(roomX +roomWidth -2, roomY +roomFloor1Y +3, roomZ +roomCenter -2, Blocks.torch, 4, 3);

		// 明るさ確保のための「松明」の設置(2階)
		world.setBlock(roomX +roomCenter, roomY +roomFloor2Y +3, roomZ +roomCenter +3, Blocks.torch, 3, 3);
		world.setBlock(roomX +roomCenter, roomY +roomFloor2Y +3, roomZ +roomCenter -3, Blocks.torch, 4, 3);
		world.setBlock(roomX +roomCenter +3, roomY +roomFloor2Y +3, roomZ +roomCenter, Blocks.torch, 1, 3);
		world.setBlock(roomX +roomCenter -3, roomY +roomFloor2Y +3, roomZ +roomCenter, Blocks.torch, 2, 3);

		/*
		world.setBlock(x, y +4, z +1, Blocks.torch, 3, 3);
		world.setBlock(x, y +4, z -1, Blocks.torch, 4, 3);
		world.setBlock(x +1, y +4, z, Blocks.torch, 1, 3);
		world.setBlock(x -1, y +4, z, Blocks.torch, 2, 3);
		*/

		/* - - - - - - - - - -
		 * 以下、敵のスポーン
		 * - - - - - - - - - */
	}
}