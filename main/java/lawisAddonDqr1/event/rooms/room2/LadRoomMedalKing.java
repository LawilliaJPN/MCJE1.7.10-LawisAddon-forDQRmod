package lawisAddonDqr1.event.rooms.room2;

import java.util.Random;

import dqr.api.Blocks.DQBlocks;
import dqr.api.Blocks.DQDecorates;
import lawisAddonDqr1.achievement.LadAchievementCore;
import lawisAddonDqr1.api.blocks.LadBlocks;
import lawisAddonDqr1.config.LadDebug;
import lawisAddonDqr1.event.entities.LadSpawnEnemyCore;
import lawisAddonDqr1.event.rooms.LadRoomID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class LadRoomMedalKing {
	/*
	 * DQRの「メダル王の部屋」をモチーフにした戦闘部屋
	 */
	public static void setRoom(World world, EntityPlayer player) {
		Random rand = new Random();
		int roomDirection = LadRoomID.getDirectionRoom(player, 0);

		int roomX = (int)player.posX;			// 部屋の起点となるX座標
		int roomZ = (int)player.posZ -1;		// 部屋の起点となるZ座標（-1）
		int roomY = (int)player.posY -1;		// 部屋の起点となるY座標（-1）

		int roomHeight = 5;					// 部屋の高さ
		int roomWidth = 12;					// 部屋の幅（-1）
		int roomCenter = roomWidth /2;		// 部屋の中心
		int roomType = rand.nextInt(8);		// 部屋の種類

		// [Debug] 戦闘部屋固定時に生成方向がチャット表示される（デバッグ用）
		if (LadDebug.getDebugRoom() >=0) {
			player.addChatMessage(new ChatComponentTranslation("roomDirection == " + roomDirection));
			player.addChatMessage(new ChatComponentTranslation("roomType == " + roomType));
		}

		// 実績の取得
		player.triggerAchievement(LadAchievementCore.roomMedalKing);

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

		/* - - - - - - - - - -
		 * 以下、部屋の生成
		 * - - - - - - - - - */

		// 空気ブロックの設置
		for (int x = 0; x <= roomWidth; x++) {
			for (int z = 0; z <= roomWidth; z++) {
				for (int y = 0; y <= roomHeight +3; y++) {
					world.setBlockToAir(roomX +x, roomY +y, roomZ +z);
				}
			}
		}


		// 床・壁・天井の設置
		for (int x = 0; x <= roomWidth; x++) {
			for (int z = 0; z <= roomWidth; z++) {
				// 地面の下に「ラピスの装飾石」
				world.setBlock(roomX +x, roomY -2, roomZ +z, DQBlocks.DqmBlockKowareru5);
				// 地面に「エメラルドの装飾石」
				world.setBlock(roomX +x, roomY -1, roomZ +z, DQBlocks.DqmBlockKowareru9);
				// 壁に「ダイヤの装飾石」
				for (int y = 0; y <= roomHeight; y += 2) {
					world.setBlock(roomX +x, roomY +y, roomZ +z, DQBlocks.DqmBlockKowareru7);
				}
				// 壁に「金の装飾石」
				for (int y = 1; y <= roomHeight; y += 2) {
					world.setBlock(roomX +x, roomY +y, roomZ +z, DQBlocks.DqmBlockKowareru6);
				}
			}
		}

		// 空気ブロックの設置
		for (int x = 2; x <= roomWidth -2; x++) {
			for (int z = 2; z <= roomWidth -2; z++) {
				for (int y = 0; y <= roomHeight -1; y++) {
					world.setBlockToAir(roomX +x, roomY +y, roomZ +z);
				}
			}
		}

		// 水ブロックの設置
		for (int x = roomCenter -2; x <= roomCenter +2; x++) {
			for (int z = roomCenter -2; z <= roomCenter +2; z++) {
				world.setBlockToAir(roomX +x, roomY -1, roomZ +z);
			}
		}
		world.setBlock(roomX +roomCenter, roomY -1, roomZ +roomCenter, Blocks.flowing_water);

		// 「キャンドルスタンド」の設置
		world.setBlock(roomX +roomCenter +4, roomY, roomZ +roomCenter +4, DQDecorates.DqmBlockTaimatu2);
		world.setBlock(roomX +roomCenter +4, roomY, roomZ +roomCenter -4, DQDecorates.DqmBlockTaimatu2);
		world.setBlock(roomX +roomCenter -4, roomY, roomZ +roomCenter +4, DQDecorates.DqmBlockTaimatu2);
		world.setBlock(roomX +roomCenter -4, roomY, roomZ +roomCenter -4, DQDecorates.DqmBlockTaimatu2);

		/* プレイヤー初期位置・出口整備 */
		switch (roomDirection) {
		case 0:
			// プレイヤー初期位置に「空気ブロック」設置
			for (int z = roomCenter -1; z <= roomCenter +1; z++) {
				for (int y = 0; y <= 3; y++) {
					world.setBlockToAir(roomX +1, roomY +y, roomZ +z);
				}
			}
			// 「キャンドルスタンド」の設置
			world.setBlock(roomX +1, roomY, roomZ +roomCenter +1, DQDecorates.DqmBlockTaimatu2);
			world.setBlock(roomX +1, roomY, roomZ +roomCenter -1, DQDecorates.DqmBlockTaimatu2);
			// 「グロウストーン」の設置
			world.setBlock(roomX +1, roomY -1, roomZ +roomCenter, Blocks.glowstone);
			break;

		case 1:
			// プレイヤー初期位置に「空気ブロック」設置
			for (int x = roomCenter -1; x <= roomCenter +1; x++) {
				for (int y = 0; y <= 3; y++) {
					world.setBlockToAir(roomX +x, roomY +y, roomZ +1);
				}
			}
			// 「キャンドルスタンド」の設置
			world.setBlock(roomX +roomCenter +1, roomY, roomZ +1, DQDecorates.DqmBlockTaimatu2);
			world.setBlock(roomX +roomCenter -1, roomY, roomZ +1, DQDecorates.DqmBlockTaimatu2);
			// 「グロウストーン」の設置
			world.setBlock(roomX +roomCenter, roomY -1, roomZ +1, Blocks.glowstone);
			break;

		case 2:
			// プレイヤー初期位置に「空気ブロック」設置
			for (int z = roomCenter -1; z <= roomCenter +1; z++) {
				for (int y = 0; y <= 3; y++) {
					world.setBlockToAir(roomX +roomWidth -1, roomY +y, roomZ +z);
				}
			}
			// 「キャンドルスタンド」の設置
			world.setBlock(roomX +roomWidth -1, roomY, roomZ +roomCenter +1, DQDecorates.DqmBlockTaimatu2);
			world.setBlock(roomX +roomWidth -1, roomY, roomZ +roomCenter -1, DQDecorates.DqmBlockTaimatu2);
			// 「グロウストーン」の設置
			world.setBlock(roomX +roomWidth -1, roomY -1, roomZ +roomCenter, Blocks.glowstone);
			break;

		case 3:
			// プレイヤー初期位置に「空気ブロック」設置
			for (int x = roomCenter -1; x <= roomCenter +1; x++) {
				for (int y = 0; y <= 3; y++) {
					world.setBlockToAir(roomX +x, roomY +y, roomZ +roomWidth -1);
				}
			}
			// 「キャンドルスタンド」の設置
			world.setBlock(roomX +roomCenter +1, roomY, roomZ +roomWidth -1, DQDecorates.DqmBlockTaimatu2);
			world.setBlock(roomX +roomCenter -1, roomY, roomZ +roomWidth -1, DQDecorates.DqmBlockTaimatu2);
			// 「グロウストーン」の設置
			world.setBlock(roomX +roomCenter, roomY -1, roomZ +roomWidth -1, Blocks.glowstone);
			break;
		}

		// 出口が奥の「ジャンプブロック(LAD)」のパターン
		if (roomType%2 == 0) {
			switch (roomDirection) {
			case 0:
				// 出口位置に「空気ブロック」設置
				for (int z = roomCenter -1; z <= roomCenter +1; z++) {
					for (int y = 0; y <= 3; y++) {
						world.setBlockToAir(roomX +roomWidth -1, roomY +y, roomZ +z);
					}
				}
				world.setBlockToAir(roomX +roomWidth -1, roomY +roomHeight, roomZ +roomCenter);
				world.setBlockToAir(roomX +roomWidth -1, roomY +roomHeight -1, roomZ +roomCenter);
				// 「キャンドルスタンド」の設置
				world.setBlock(roomX +roomWidth -1, roomY, roomZ +roomCenter +1, DQDecorates.DqmBlockTaimatu2);
				world.setBlock(roomX +roomWidth -1, roomY, roomZ +roomCenter -1, DQDecorates.DqmBlockTaimatu2);
				// 「ジャンプブロック(LAD)」の設置
				world.setBlock(roomX +roomWidth -1, roomY -1, roomZ +roomCenter, LadBlocks.ladJumpBlock2);
				break;

			case 1:
				// 出口位置に「空気ブロック」設置
				for (int x = roomCenter -1; x <= roomCenter +1; x++) {
					for (int y = 0; y <= 3; y++) {
						world.setBlockToAir(roomX +x, roomY +y, roomZ +roomWidth -1);
					}
				}
				world.setBlockToAir(roomX +roomCenter, roomY +roomHeight, roomZ +roomWidth -1);
				world.setBlockToAir(roomX +roomCenter, roomY +roomHeight -1, roomZ +roomWidth -1);
				// 「キャンドルスタンド」の設置
				world.setBlock(roomX +roomCenter +1, roomY, roomZ +roomWidth -1, DQDecorates.DqmBlockTaimatu2);
				world.setBlock(roomX +roomCenter -1, roomY, roomZ +roomWidth -1, DQDecorates.DqmBlockTaimatu2);
				// 「ジャンプブロック(LAD)」の設置
				world.setBlock(roomX +roomCenter, roomY -1, roomZ +roomWidth -1, LadBlocks.ladJumpBlock2);
				break;

			case 2:
				// 出口位置に「空気ブロック」設置
				for (int z = roomCenter -1; z <= roomCenter +1; z++) {
					for (int y = 0; y <= 3; y++) {
						world.setBlockToAir(roomX +1, roomY +y, roomZ +z);
					}
				}
				world.setBlockToAir(roomX +1, roomY +roomHeight, roomZ +roomCenter);
				world.setBlockToAir(roomX +1, roomY +roomHeight -1, roomZ +roomCenter);
				// 「キャンドルスタンド」の設置
				world.setBlock(roomX +1, roomY, roomZ +roomCenter +1, DQDecorates.DqmBlockTaimatu2);
				world.setBlock(roomX +1, roomY, roomZ +roomCenter -1, DQDecorates.DqmBlockTaimatu2);
				// 「ジャンプブロック(LAD)」の設置
				world.setBlock(roomX +1, roomY -1, roomZ +roomCenter, LadBlocks.ladJumpBlock2);
				break;

			case 3:
				// 出口位置に「空気ブロック」設置
				for (int x = roomCenter -1; x <= roomCenter +1; x++) {
					for (int y = 0; y <= 3; y++) {
						world.setBlockToAir(roomX +x, roomY +y, roomZ +1);
					}
				}
				world.setBlockToAir(roomX +roomCenter, roomY +roomHeight, roomZ +1);
				world.setBlockToAir(roomX +roomCenter, roomY +roomHeight -1, roomZ +1);
				// 「キャンドルスタンド」の設置
				world.setBlock(roomX +roomCenter +1, roomY, roomZ +1, DQDecorates.DqmBlockTaimatu2);
				world.setBlock(roomX +roomCenter -1, roomY, roomZ +1, DQDecorates.DqmBlockTaimatu2);
				// 「ジャンプブロック(LAD)」の設置
				world.setBlock(roomX +roomCenter, roomY -1, roomZ +1, LadBlocks.ladJumpBlock2);
				break;
			}

		// 出口が水を登るパターン
		} else {
			// 「水ブロック」の設置
			world.setBlock(roomX +roomCenter, roomY +roomHeight, roomZ +roomCenter, Blocks.flowing_water);
			// 「井戸」の設置
			world.setBlock(roomX +roomCenter -1, roomY +roomHeight +1, roomZ +roomCenter, DQDecorates.DqmBlockIdo);
		}

		// 屋根の上に「松明」の設置
		world.setBlock(roomX +roomCenter +4, roomY +roomHeight +1, roomZ +roomCenter +4, Blocks.torch, 5, 3);
		world.setBlock(roomX +roomCenter +4, roomY +roomHeight +1, roomZ +roomCenter -4, Blocks.torch, 5, 3);
		world.setBlock(roomX +roomCenter -4, roomY +roomHeight +1, roomZ +roomCenter +4, Blocks.torch, 5, 3);
		world.setBlock(roomX +roomCenter -4, roomY +roomHeight +1, roomZ +roomCenter -4, Blocks.torch, 5, 3);

		/* - - - - - - - - - -
		 * 以下、敵のスポーン
		 * - - - - - - - - - */

		// 正面に2体
		if (roomType%2 == 0) {
			switch (roomDirection) {
			case 0:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -2, roomY +1, roomZ +3, LadRoomID.MEDAL_KING + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -2, roomY +1, roomZ +roomWidth -3, LadRoomID.MEDAL_KING + LadRoomID.getDifOfRoom());
				break;
			case 1:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +3, roomY +1, roomZ +roomWidth -2, LadRoomID.MEDAL_KING + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -3, roomY +1, roomZ +roomWidth -2, LadRoomID.MEDAL_KING + LadRoomID.getDifOfRoom());
				break;
			case 2:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +2, roomY +1, roomZ +3, LadRoomID.MEDAL_KING + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +2, roomY +1, roomZ +roomWidth -3, LadRoomID.MEDAL_KING + LadRoomID.getDifOfRoom());
				break;
			case 3:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +3, roomY +1, roomZ +2, LadRoomID.MEDAL_KING + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -3, roomY +1, roomZ +2, LadRoomID.MEDAL_KING + LadRoomID.getDifOfRoom());
				break;
			}
		// 正面に3体
		} else if (roomType%2 == 1) {
			switch (roomDirection) {
			case 0:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -2, roomY +1, roomZ +roomCenter, LadRoomID.MEDAL_KING + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -2, roomY +1, roomZ +3, LadRoomID.MEDAL_KING + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -2, roomY +1, roomZ +roomWidth -3, LadRoomID.MEDAL_KING + LadRoomID.getDifOfRoom());
				break;
			case 1:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +1, roomZ +roomWidth -2, LadRoomID.MEDAL_KING + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +3, roomY +1, roomZ +roomWidth -2, LadRoomID.MEDAL_KING + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -3, roomY +1, roomZ +roomWidth -2, LadRoomID.MEDAL_KING + LadRoomID.getDifOfRoom());
				break;
			case 2:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +2, roomY +1, roomZ +roomCenter, LadRoomID.MEDAL_KING + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +2, roomY +1, roomZ +3, LadRoomID.MEDAL_KING + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +2, roomY +1, roomZ +roomWidth -3, LadRoomID.MEDAL_KING + LadRoomID.getDifOfRoom());
				break;
			case 3:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +1, roomZ +2, LadRoomID.MEDAL_KING + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +3, roomY +1, roomZ +2, LadRoomID.MEDAL_KING + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -3, roomY +1, roomZ +2, LadRoomID.MEDAL_KING + LadRoomID.getDifOfRoom());
				break;
			}
		// 正面に1体、部屋の上に2体
		} else if (roomType%2 == 2) {
			switch (roomDirection) {
			case 0:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -2, roomY +1, roomZ +roomCenter, LadRoomID.MEDAL_KING + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +2, roomY +roomHeight +1, roomZ +3, LadRoomID.MEDAL_KING + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +2, roomY +roomHeight +1, roomZ +roomWidth -3, LadRoomID.MEDAL_KING + LadRoomID.getDifOfRoom());
				break;
			case 1:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +1, roomZ +roomWidth -2, LadRoomID.MEDAL_KING + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +3, roomY +roomHeight +1, roomZ +2, LadRoomID.MEDAL_KING + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -3, roomY +roomHeight +1, roomZ +2, LadRoomID.MEDAL_KING + LadRoomID.getDifOfRoom());
				break;
			case 2:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +2, roomY +1, roomZ +roomCenter, LadRoomID.MEDAL_KING + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -2, roomY +roomHeight +1, roomZ +3, LadRoomID.MEDAL_KING + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -2, roomY +roomHeight +1, roomZ +roomWidth -3, LadRoomID.MEDAL_KING + LadRoomID.getDifOfRoom());
				break;
			case 3:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +1, roomZ +2, LadRoomID.MEDAL_KING + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +3, roomY +roomHeight +1, roomZ +roomWidth -2, LadRoomID.MEDAL_KING + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -3, roomY +roomHeight +1, roomZ +roomWidth -2, LadRoomID.MEDAL_KING + LadRoomID.getDifOfRoom());
				break;
			}
		// メタル系が1体
		} else {
			switch (roomDirection) {
			case 0:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -2, roomY +1, roomZ +roomCenter, LadRoomID.Metal_Slime_With_Log);
				break;
			case 1:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +1, roomZ +roomWidth -2, LadRoomID.Metal_Slime_With_Log);
				break;
			case 2:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +2, roomY +1, roomZ +roomCenter, LadRoomID.Metal_Slime_With_Log);
				break;
			case 3:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +1, roomZ +2, LadRoomID.Metal_Slime_With_Log);
				break;
			}
		}
	}
}
