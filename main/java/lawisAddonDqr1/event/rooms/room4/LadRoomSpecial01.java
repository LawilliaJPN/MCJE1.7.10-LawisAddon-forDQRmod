package lawisAddonDqr1.event.rooms.room4;

import dqr.api.Blocks.DQBlocks;
import lawisAddonDqr1.achievement.LadAchievementCore;
import lawisAddonDqr1.api.blocks.LadBlocks;
import lawisAddonDqr1.config.LadConfigCore;
import lawisAddonDqr1.event.entities.LadSpawnEnemyCore;
import lawisAddonDqr1.event.rooms.LadRoomID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class LadRoomSpecial01 {
	/*
	 * DQRのブロックを利用した特殊な戦闘部屋
	 */
	public static void setRoom(World world, EntityPlayer player) {
		int roomX = (int)player.posX;			// 部屋の起点となるX座標
		int roomZ = (int)player.posZ -1;		// 部屋の起点となるZ座標（-1）
		int roomY = (int)player.posY -2;		// 部屋の起点となるY座標（-2）

		int roomHeight = 9;					// 部屋の高さ
		int roomWidth = 20;					// 部屋の幅（-1）

		int roomFloor1Y = 0;					// 1階の高さ (部屋の高さはroomHeight - roomFloor1Y)
		int roomFloor2Y = 5;					// 2階の高さ

		// 実績の取得
		player.triggerAchievement(LadAchievementCore.roomSpecial01);

		// プレイヤーの位置から部屋の起点となる座標を決める (※2階の中央にプレイヤーとなるように生成される部屋なので向きは関係ない)
		roomX -= 10;
		roomZ -= 10;
		roomY -= roomFloor2Y;

		// コンフィグ：負荷軽減オンの時は、2階のみ生成 → 1階の高さを2階の高さにする
		if (LadConfigCore.isRoomReduction) roomFloor1Y = roomFloor2Y +1;



		/* - - - - - - - - - -
		 * 以下、部屋の生成
		 * - - - - - - - - - */

		/* 空間 */
		// 「レッドストーンの装飾石」を設置
		for (int x = -1; x <= roomWidth +1; x++) {
			for (int z = -1; z <= roomWidth +1; z++) {
				for (int y = roomFloor1Y -2; y <= roomHeight; y++) {
					world.setBlock(roomX +x, roomY +y, roomZ +z, DQBlocks.DqmBlockKowareru8);
				}
			}
		}

		// 「空気」を設置
		for (int x = 0; x <= roomWidth; x++) {
			for (int z = 0; z <= roomWidth; z++) {
				for (int y = roomFloor1Y; y <= roomHeight; y++) {
					world.setBlockToAir(roomX +x, roomY +y, roomZ +z);
				}
			}
		}

		// 天井に「ダメージ床」を設置
		for (int x = -1; x <= roomWidth +1; x++) {
			for (int z = -1; z <= roomWidth +1; z++) {
				world.setBlock(roomX +x, roomY +roomHeight +1, roomZ +z, DQBlocks.DqmBlockToramanaYuka2);
			}
		}


		/* 床 */
		// コンフィグ：負荷軽減オンの時は、2階のみ生成
		if (LadConfigCore.isRoomReduction) {
			// 地面に「ラピスの装飾石」を設置
			for (int x = 0; x <= roomWidth; x++) {
				for (int z = 0; z <= roomWidth; z++) {
					world.setBlock(roomX +x, roomY +roomFloor2Y, roomZ +z, DQBlocks.DqmBlockKowareru5);
				}
			}

		// コンフィグ：負荷軽減オフの時は、1階～2階を生成
		} else {
			// 地面に「ラピスの装飾石」を設置
			for (int x = 0; x <= roomWidth; x++) {
				for (int z = 0; z <= roomWidth; z++) {
					world.setBlock(roomX +x, roomY -2, roomZ +z, DQBlocks.DqmBlockKowareru5);
					world.setBlock(roomX +x, roomY -1, roomZ +z, DQBlocks.DqmBlockKowareru5);
					world.setBlock(roomX +x, roomY +roomFloor2Y, roomZ +z, DQBlocks.DqmBlockKowareru5);
				}
			}
			// 階段となる「ラピスの装飾石」を設置
			world.setBlock(roomX +2, roomY, roomZ +roomWidth, DQBlocks.DqmBlockKowareru5);
			world.setBlock(roomX +1, roomY +1, roomZ +roomWidth, DQBlocks.DqmBlockKowareru5);
			world.setBlock(roomX, roomY +2, roomZ +roomWidth, DQBlocks.DqmBlockKowareru5);
			world.setBlock(roomX, roomY +3, roomZ +roomWidth -1, DQBlocks.DqmBlockKowareru5);
			world.setBlock(roomX, roomY +4, roomZ +roomWidth -2, DQBlocks.DqmBlockKowareru5);
			world.setBlock(roomX +roomWidth -2, roomY, roomZ, DQBlocks.DqmBlockKowareru5);
			world.setBlock(roomX +roomWidth -1, roomY +1, roomZ, DQBlocks.DqmBlockKowareru5);
			world.setBlock(roomX +roomWidth, roomY +2, roomZ, DQBlocks.DqmBlockKowareru5);
			world.setBlock(roomX +roomWidth, roomY +3, roomZ +1, DQBlocks.DqmBlockKowareru5);
			world.setBlock(roomX +roomWidth, roomY +4, roomZ +2, DQBlocks.DqmBlockKowareru5);

			// 2階の床の吹き抜け部分に「空気ブロック」を設置
			for (int x = 9; x <= roomWidth -9; x++) {
				for (int z = 9; z <= roomWidth -9; z++) {
					world.setBlockToAir(roomX +x, roomY +roomFloor2Y, roomZ +z);
					world.setBlockToAir(roomX +x -5, roomY +roomFloor2Y, roomZ +z -5);
					world.setBlockToAir(roomX +x -5, roomY +roomFloor2Y, roomZ +z +5);
					world.setBlockToAir(roomX +x +5, roomY +roomFloor2Y, roomZ +z -5);
					world.setBlockToAir(roomX +x +5, roomY +roomFloor2Y, roomZ +z +5);
				}
			}
			for (int x = 0; x <= 2; x++) {
				for (int z = 4; z <= 6; z++) {
					world.setBlockToAir(roomX +x, roomY +roomFloor2Y, roomZ +z);
					world.setBlockToAir(roomX +roomWidth -x, roomY +roomFloor2Y, roomZ +roomWidth -z);
				}
			}
			for (int z = 0; z <=2; z++) {
				world.setBlockToAir(roomX +roomWidth, roomY +roomFloor2Y, roomZ +z);
				world.setBlockToAir(roomX, roomY +roomFloor2Y, roomZ +roomWidth -z);
			}

			/* 「ジャンプブロック」※プレイヤー以外のEntityもジャンプする等の仕様変更したブロック */
			// 中央
			for (int x = 9; x <= roomWidth -9; x++) {
				for (int z = 9; z <= roomWidth -9; z++) {
					world.setBlock(roomX +x, roomY -1, roomZ +z, LadBlocks.ladJumpBlock2);
				}
			}
			// 通路
			for (int x = 0; x <= 2; x++) {
				for (int z = 4; z <= 6; z++) {
					world.setBlock(roomX +x, roomY -1, roomZ +z, LadBlocks.ladJumpBlock2);
					world.setBlock(roomX +roomWidth -x, roomY -1, roomZ +roomWidth -z, LadBlocks.ladJumpBlock2);
				}
			}
			/* 「強制移動床・改」 */
			// 中央十字
			for (int i = 9; i <= roomWidth -9; i++) {
				for (int j = 3; j <= 8; j++) {
					world.setBlock(roomX +i, roomY -1, roomZ +j, DQBlocks.DqmBlockS2);
					world.setBlock(roomX +i, roomY -1, roomZ +roomWidth -j, DQBlocks.DqmBlockN2);
					world.setBlock(roomX +j, roomY -1, roomZ +i, DQBlocks.DqmBlockW2);
					world.setBlock(roomX +roomWidth -j, roomY -1, roomZ +i, DQBlocks.DqmBlockE2);
				}
			}
			for (int x = 3; x <= 7; x++) {
				world.setBlock(roomX +x, roomY -1, roomZ +8, DQBlocks.DqmBlockW2);
				world.setBlock(roomX +x, roomY -1, roomZ +roomWidth -8, DQBlocks.DqmBlockW2);
				world.setBlock(roomX +roomWidth -x, roomY -1, roomZ +8, DQBlocks.DqmBlockE2);
				world.setBlock(roomX +roomWidth -x, roomY -1, roomZ +roomWidth -8, DQBlocks.DqmBlockE2);
			}
			for (int z = 3; z <= 7; z++) {
				world.setBlock(roomX +8, roomY -1, roomZ +z, DQBlocks.DqmBlockE2);
				world.setBlock(roomX +8, roomY -1, roomZ +roomWidth -z, DQBlocks.DqmBlockE2);
				world.setBlock(roomX +roomWidth -8, roomY -1, roomZ +z, DQBlocks.DqmBlockW2);
				world.setBlock(roomX +roomWidth -8, roomY -1, roomZ +roomWidth -z, DQBlocks.DqmBlockW2);
			}

			// 中央四隅
			for (int i = 4; i <= 7; i++) {
				for (int j = 4; j <= 7; j++) {
					world.setBlock(roomX +i, roomY -1, roomZ +j, DQBlocks.DqmBlockE2);
					world.setBlock(roomX +roomWidth -j, roomY -1, roomZ +i, DQBlocks.DqmBlockS2);
					world.setBlock(roomX +i, roomY -1, roomZ +roomWidth -j, DQBlocks.DqmBlockN2);
					world.setBlock(roomX +roomWidth -j, roomY -1, roomZ +roomWidth -i, DQBlocks.DqmBlockW2);
				}
			}

			// 通路（ジャンプブロックへ）
			for (int z = 7; z <= 12; z++) {
				for (int x = 0; x <= 2; x++) {
					world.setBlock(roomX +x, roomY -1, roomZ +z, DQBlocks.DqmBlockN2);
					world.setBlock(roomX +roomWidth -x, roomY -1, roomZ +roomWidth -z, DQBlocks.DqmBlockS2);
				}
			}

			/* 光源 */
			// 明るさ確保のための「松明」の設置
			world.setBlock(roomX, roomY, roomZ +roomWidth, Blocks.torch, 5, 3);
			world.setBlock(roomX +roomWidth, roomY, roomZ, Blocks.torch, 5, 3);
		}


		/* 壁・柱 */
		// 壁となる「レッドストーンの装飾石」を設置
		for (int y = roomFloor1Y; y <= roomHeight; y++) {
			for (int x = 3; x <= 7; x++) {
				world.setBlock(roomX +x, roomY +y, roomZ +3, DQBlocks.DqmBlockKowareru8);
				world.setBlock(roomX +x, roomY +y, roomZ +roomWidth -3, DQBlocks.DqmBlockKowareru8);
				world.setBlock(roomX +roomWidth -x, roomY +y, roomZ +3, DQBlocks.DqmBlockKowareru8);
				world.setBlock(roomX +roomWidth -x, roomY +y, roomZ +roomWidth -3, DQBlocks.DqmBlockKowareru8);
			}

			for (int z = 4; z <= 7; z++) {
				world.setBlock(roomX +3, roomY +y, roomZ +z, DQBlocks.DqmBlockKowareru8);
				world.setBlock(roomX +3, roomY +y, roomZ +roomWidth -z, DQBlocks.DqmBlockKowareru8);
				world.setBlock(roomX +roomWidth -3, roomY +y, roomZ +z, DQBlocks.DqmBlockKowareru8);
				world.setBlock(roomX +roomWidth -3, roomY +y, roomZ +roomWidth -z, DQBlocks.DqmBlockKowareru8);
			}
		}
		// 柱となる「レッドストーンの装飾石」を設置
		for (int x = 8; x <= roomWidth -8; x += 4) {
			for (int z = 8; z <= roomWidth -8; z += 4) {
				for (int y = roomFloor1Y; y <= roomHeight; y++) {
					world.setBlock(roomX +x, roomY +y, roomZ +z, DQBlocks.DqmBlockKowareru8);
				}
			}
		}

		// 出入口部分に空気ブロックを設置（壁の「レッドストーンの装飾石」が硬いため）
		for (int y = roomFloor1Y; y <= roomFloor1Y +2; y++) {
			world.setBlockToAir(roomX -1, roomY +y, roomZ +1);
			world.setBlockToAir(roomX -1, roomY +y, roomZ +2);
			world.setBlockToAir(roomX +roomWidth +1, roomY +y, roomZ +roomWidth -1);
			world.setBlockToAir(roomX +roomWidth +1, roomY +y, roomZ +roomWidth -2);
		}

		// コンフィグ：負荷軽減オンの時に、出入口部分を2方向から4方向に増やす
		if (LadConfigCore.isRoomReduction) {
			for (int y = roomFloor1Y; y <= roomFloor1Y +2; y++) {
				world.setBlockToAir(roomX +1, roomY +y, roomZ +roomWidth +1);
				world.setBlockToAir(roomX +2, roomY +y, roomZ +roomWidth +1);
				world.setBlockToAir(roomX +roomWidth -1, roomY +y, roomZ -1);
				world.setBlockToAir(roomX +roomWidth -2, roomY +y, roomZ -1);
			}
		}

		/* - - - - - - - - - -
		 * 以下、敵のスポーン
		 * - - - - - - - - - */

		// 確定スポーン
		LadSpawnEnemyCore.spawnEnemy(world, player, roomX +1, roomY +roomFloor2Y +2, roomZ +1, LadRoomID.SPECIAL_01 + LadRoomID.getDifOfRoom());
		LadSpawnEnemyCore.spawnEnemy(world, player, roomX +1, roomY +roomFloor2Y +2, roomZ +roomWidth -1, LadRoomID.SPECIAL_01 + LadRoomID.getDifOfRoom());
		LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -1, roomY +roomFloor2Y +2, roomZ +1, LadRoomID.SPECIAL_01 + LadRoomID.getDifOfRoom());
		LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -1, roomY +roomFloor2Y +2, roomZ +roomWidth -1, LadRoomID.SPECIAL_01 + LadRoomID.getDifOfRoom());

		// コンフィグ：負荷軽減オフの時に追加スポーン
		if (!(LadConfigCore.isRoomReduction)) {
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +1, roomY +2, roomZ +1, LadRoomID.SPECIAL_01 + LadRoomID.getDifOfRoom());
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +1, roomY +2, roomZ +roomWidth -1, LadRoomID.SPECIAL_01 + LadRoomID.getDifOfRoom());
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -1, roomY +2, roomZ +1, LadRoomID.SPECIAL_01 + LadRoomID.getDifOfRoom());
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -1, roomY +2, roomZ +roomWidth -1, LadRoomID.SPECIAL_01 + LadRoomID.getDifOfRoom());
		}

	}
}
