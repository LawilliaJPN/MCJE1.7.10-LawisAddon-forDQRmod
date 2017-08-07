package lawisAddonDqr1.event.rooms;

import dqr.api.Blocks.DQBlocks;
import lawisAddonDqr1.blocks.LADBlocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class Room41Rare1 {
	/*
	 * DQRのブロックを利用した特殊な戦闘部屋
	 */
	public static void setRoomRoomRare1(World world, EntityPlayer player, int direction) {
		// player.addChatMessage(new ChatComponentTranslation("direction == " + direction));

		int playerX = (int)player.posX;		// プレイヤーのX座標
		int playerY = (int)player.posY;		// プレイヤーのY座標
		int playerZ = (int)player.posZ;		// プレイヤーのZ座標

		int roomX = playerX;					// 部屋の起点となるX座標
		int roomZ = playerZ -1;				// 部屋の起点となるZ座標（-1）
		int roomHeight = 9;					// 部屋の高さ
		int roomWidth = 20;					// 部屋の幅（-1）
		int roomFloor2Y = 5;					// 2階の高さ

		roomX -= 10;
		roomZ -= 10;

		/* 空間 */
		// 「レッドストーンの装飾石」を設置
		for (int x = -1; x <= roomWidth +1; x++) {
			for (int z = -1; z <= roomWidth +1; z++) {
				for (int y = -2; y <= roomHeight; y++) {
					world.setBlock(roomX +x, playerY +y, roomZ +z, DQBlocks.DqmBlockKowareru8);
				}
			}
		}

		// 「空気」を設置
		for (int x = 0; x <= roomWidth; x++) {
			for (int z = 0; z <= roomWidth; z++) {
				for (int y = 0; y <= roomHeight; y++) {
					world.setBlockToAir(roomX +x, playerY +y, roomZ +z);
				}
			}
		}

		/* 床・天井 */
		// 地面に「ラピスの装飾石」を設置
		for (int x = 0; x <= roomWidth; x++) {
			for (int z = 0; z <= roomWidth; z++) {
				world.setBlock(roomX +x, playerY -2, roomZ +z, DQBlocks.DqmBlockKowareru5);
				world.setBlock(roomX +x, playerY -1, roomZ +z, DQBlocks.DqmBlockKowareru5);
				world.setBlock(roomX +x, playerY +roomFloor2Y, roomZ +z, DQBlocks.DqmBlockKowareru5);
			}
		}
		// 階段となる「ラピスの装飾石」を設置
		world.setBlock(roomX +2, playerY, roomZ +roomWidth, DQBlocks.DqmBlockKowareru5);
		world.setBlock(roomX +1, playerY +1, roomZ +roomWidth, DQBlocks.DqmBlockKowareru5);
		world.setBlock(roomX, playerY +2, roomZ +roomWidth, DQBlocks.DqmBlockKowareru5);
		world.setBlock(roomX, playerY +3, roomZ +roomWidth -1, DQBlocks.DqmBlockKowareru5);
		world.setBlock(roomX, playerY +4, roomZ +roomWidth -2, DQBlocks.DqmBlockKowareru5);
		world.setBlock(roomX +roomWidth -2, playerY, roomZ, DQBlocks.DqmBlockKowareru5);
		world.setBlock(roomX +roomWidth -1, playerY +1, roomZ, DQBlocks.DqmBlockKowareru5);
		world.setBlock(roomX +roomWidth, playerY +2, roomZ, DQBlocks.DqmBlockKowareru5);
		world.setBlock(roomX +roomWidth, playerY +3, roomZ +1, DQBlocks.DqmBlockKowareru5);
		world.setBlock(roomX +roomWidth, playerY +4, roomZ +2, DQBlocks.DqmBlockKowareru5);

		// 2階の床の吹き抜け部分に「空気ブロック」を設置
		for (int x = 9; x <= roomWidth -9; x++) {
			for (int z = 9; z <= roomWidth -9; z++) {
				world.setBlockToAir(roomX +x, playerY +roomFloor2Y, roomZ +z);
				world.setBlockToAir(roomX +x -5, playerY +roomFloor2Y, roomZ +z -5);
				world.setBlockToAir(roomX +x -5, playerY +roomFloor2Y, roomZ +z +5);
				world.setBlockToAir(roomX +x +5, playerY +roomFloor2Y, roomZ +z -5);
				world.setBlockToAir(roomX +x +5, playerY +roomFloor2Y, roomZ +z +5);
			}
		}
		for (int x = 0; x <= 2; x++) {
			for (int z = 4; z <= 6; z++) {
				world.setBlockToAir(roomX +x, playerY +roomFloor2Y, roomZ +z);
				world.setBlockToAir(roomX +roomWidth -x, playerY +roomFloor2Y, roomZ +roomWidth -z);
			}
		}
		for (int z = 0; z <=2; z++) {
			world.setBlockToAir(roomX +roomWidth, playerY +roomFloor2Y, roomZ +z);
			world.setBlockToAir(roomX, playerY +roomFloor2Y, roomZ +roomWidth -z);
		}
		// 天井に「ダメージ床」を設置
		for (int x = -1; x <= roomWidth +1; x++) {
			for (int z = -1; z <= roomWidth +1; z++) {
				world.setBlock(roomX +x, playerY +roomHeight +1, roomZ +z, DQBlocks.DqmBlockToramanaYuka);
			}
		}

		/* 壁・柱 */
		// 壁となる「レッドストーンの装飾石」を設置
		for (int y = 0; y <= roomHeight; y++) {
			for (int x = 3; x <= 7; x++) {
				world.setBlock(roomX +x, playerY +y, roomZ +3, DQBlocks.DqmBlockKowareru8);
				world.setBlock(roomX +x, playerY +y, roomZ +roomWidth -3, DQBlocks.DqmBlockKowareru8);
				world.setBlock(roomX +roomWidth -x, playerY +y, roomZ +3, DQBlocks.DqmBlockKowareru8);
				world.setBlock(roomX +roomWidth -x, playerY +y, roomZ +roomWidth -3, DQBlocks.DqmBlockKowareru8);
			}

			for (int z = 4; z <= 7; z++) {
				world.setBlock(roomX +3, playerY +y, roomZ +z, DQBlocks.DqmBlockKowareru8);
				world.setBlock(roomX +3, playerY +y, roomZ +roomWidth -z, DQBlocks.DqmBlockKowareru8);
				world.setBlock(roomX +roomWidth -3, playerY +y, roomZ +z, DQBlocks.DqmBlockKowareru8);
				world.setBlock(roomX +roomWidth -3, playerY +y, roomZ +roomWidth -z, DQBlocks.DqmBlockKowareru8);
			}

		}
		// 柱となる「レッドストーンの装飾石」を設置
		for (int x = 8; x <= roomWidth -8; x += 4) {
			for (int z = 8; z <= roomWidth -8; z += 4) {
				for (int y = 0; y <= roomHeight; y++) {
					world.setBlock(roomX +x, playerY +y, roomZ +z, DQBlocks.DqmBlockKowareru8);
				}
			}
		}

		/* 「ジャンプブロック(LAD)」※プレイヤー以外のEntityもジャンプする等の仕様変更したブロック */
		// 中央
		for (int x = 9; x <= roomWidth -9; x++) {
			for (int z = 9; z <= roomWidth -9; z++) {
				world.setBlock(roomX +x, playerY -1, roomZ +z, LADBlocks.ladJumpBlock2);
			}
		}
		// 通路
		for (int x = 0; x <= 2; x++) {
			for (int z = 4; z <= 6; z++) {
				world.setBlock(roomX +x, playerY -1, roomZ +z, LADBlocks.ladJumpBlock2);
				world.setBlock(roomX +roomWidth -x, playerY -1, roomZ +roomWidth -z, LADBlocks.ladJumpBlock2);
			}
		}
		/* 「強制移動床・改」 */
		// 中央十字
		for (int i = 9; i <= roomWidth -9; i++) {
			for (int j = 3; j <= 8; j++) {
				world.setBlock(roomX +i, playerY -1, roomZ +j, DQBlocks.DqmBlockS2);
				world.setBlock(roomX +i, playerY -1, roomZ +roomWidth -j, DQBlocks.DqmBlockN2);
				world.setBlock(roomX +j, playerY -1, roomZ +i, DQBlocks.DqmBlockW2);
				world.setBlock(roomX +roomWidth -j, playerY -1, roomZ +i, DQBlocks.DqmBlockE2);
			}
		}
		for (int x = 3; x <= 7; x++) {
			world.setBlock(roomX +x, playerY -1, roomZ +8, DQBlocks.DqmBlockW2);
			world.setBlock(roomX +x, playerY -1, roomZ +roomWidth -8, DQBlocks.DqmBlockW2);
			world.setBlock(roomX +roomWidth -x, playerY -1, roomZ +8, DQBlocks.DqmBlockE2);
			world.setBlock(roomX +roomWidth -x, playerY -1, roomZ +roomWidth -8, DQBlocks.DqmBlockE2);
		}
		for (int z = 3; z <= 7; z++) {
			world.setBlock(roomX +8, playerY -1, roomZ +z, DQBlocks.DqmBlockE2);
			world.setBlock(roomX +8, playerY -1, roomZ +roomWidth -z, DQBlocks.DqmBlockE2);
			world.setBlock(roomX +roomWidth -8, playerY -1, roomZ +z, DQBlocks.DqmBlockW2);
			world.setBlock(roomX +roomWidth -8, playerY -1, roomZ +roomWidth -z, DQBlocks.DqmBlockW2);
		}

		// 中央四隅
		for (int i = 4; i <= 7; i++) {
			for (int j = 4; j <= 7; j++) {
				world.setBlock(roomX +i, playerY -1, roomZ +j, DQBlocks.DqmBlockE2);
				world.setBlock(roomX +roomWidth -j, playerY -1, roomZ +i, DQBlocks.DqmBlockS2);
				world.setBlock(roomX +i, playerY -1, roomZ +roomWidth -j, DQBlocks.DqmBlockN2);
				world.setBlock(roomX +roomWidth -j, playerY -1, roomZ +roomWidth -i, DQBlocks.DqmBlockW2);
			}
		}

		// 通路（ジャンプブロックへ）
		for (int z = 7; z <= 12; z++) {
			for (int x = 0; x <= 2; x++) {
				world.setBlock(roomX +x, playerY -1, roomZ +z, DQBlocks.DqmBlockN2);
				world.setBlock(roomX +roomWidth -x, playerY -1, roomZ +roomWidth -z, DQBlocks.DqmBlockS2);
			}
		}

		/* 光源 */
		// 明るさ確保のための「松明」の設置（仮）
		world.setBlock(roomX, playerY +2, roomZ +roomWidth, Blocks.torch, 5, 3);
		world.setBlock(roomX +roomWidth, playerY, roomZ, Blocks.torch, 5, 3);

	}
}
