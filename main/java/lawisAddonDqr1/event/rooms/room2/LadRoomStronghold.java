package lawisAddonDqr1.event.rooms.room2;

import java.util.Random;

import lawisAddonDqr1.achievement.LadAchievementCore;
import lawisAddonDqr1.config.LadDebug;
import lawisAddonDqr1.event.entities.LadSpawnEnemyCore;
import lawisAddonDqr1.event.rooms.LadRoomID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemDoor;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class LadRoomStronghold {
	/*
	 * バニラの「要塞」をモチーフとした戦闘部屋
	 */
	public static void setRoom(World world, EntityPlayer player) {
		Random rand = new Random();
		int roomDirection = LadRoomID.getDirectionRoom(player, 0);

		int roomX = (int)player.posX;			// 部屋の起点となるX座標
		int roomZ = (int)player.posZ -1;		// 部屋の起点となるZ座標（-1）
		int roomY = (int)player.posY -1;		// 部屋の起点となるY座標（-1）

		int roomHeight = 5;					// 部屋の高さ
		int roomFloorY = -1;					// 1階の高さ
		int roomWidth = 10;					// 部屋の幅（-1）
		int roomCenter = roomWidth /2;		// 部屋の中心
		int roomType = rand.nextInt(4);		// 部屋の種類

		// [Debug] 戦闘部屋固定時に生成方向がチャット表示される（デバッグ用）
		if (LadDebug.getDebugRoom() >=0) {
			player.addChatMessage(new ChatComponentTranslation("roomDirection == " + roomDirection));
			player.addChatMessage(new ChatComponentTranslation("roomType == " + roomType));
		}

		// 実績の取得
		player.triggerAchievement(LadAchievementCore.roomStronghold);

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

		/* 空間 */
		// 「空気」の設置
		for (int x = 0; x <= roomWidth; x++) {
			for (int z = 0; z <= roomWidth; z++) {
				for (int y = 0; y <= roomHeight; y++) {
					world.setBlockToAir(roomX +x, roomY +y, roomZ +z);
				}
			}
		}

		// 「石ブロック」等の設置
		for (int x = 0; x <= roomWidth; x++) {
			for (int z = 0; z <= roomWidth; z++) {
				setStoneBrick(world, roomX +x, roomY +roomFloorY, roomZ +z);
				setStoneBrick(world, roomX +x, roomY +roomHeight, roomZ +z);
			}
		}

		// 「石ブロック」等の設置
		for (int y = 0; y < roomHeight; y++) {
			for (int x = 0; x <= roomWidth; x++) {
				setStoneBrick(world, roomX +x, roomY +y, roomZ);
				setStoneBrick(world, roomX +x, roomY +y, roomZ +roomWidth);
			}

			for (int z = 0; z <= roomWidth; z++) {
				setStoneBrick(world, roomX, roomY +y, roomZ +z);
				setStoneBrick(world, roomX +roomWidth, roomY +y, roomZ +z);
			}
		}

		// 出入口部分の「鉄格子」の設置
		for (int y = 0; y <= 2; y++) {
			for (int x = roomCenter -1; x <= roomCenter +1; x++) {
				world.setBlock(roomX +x, roomY +y, roomZ, Blocks.iron_bars);
				world.setBlock(roomX +x, roomY +y, roomZ +roomWidth, Blocks.iron_bars);
			}

			for (int z = roomCenter -1; z <= roomCenter +1; z++) {
				world.setBlock(roomX, roomY +y, roomZ +z, Blocks.iron_bars);
				world.setBlock(roomX +roomWidth, roomY +y, roomZ +z, Blocks.iron_bars);
			}
		}

		// 出入口部分の「空気」の設置
		for (int y = 0; y <= 1; y++) {
			world.setBlockToAir(roomX +roomCenter, roomY +y, roomZ);
			world.setBlockToAir(roomX +roomCenter, roomY +y, roomZ +roomWidth);
			world.setBlockToAir(roomX, roomY +y, roomZ +roomCenter);
			world.setBlockToAir(roomX +roomWidth, roomY +y, roomZ +roomCenter);
		}

		/* 装飾 */
		if (roomType == 0) {
			// 「石ハーフブロック」の設置
			for (int x = roomCenter -1; x <= roomCenter +1; x++) {
				for (int z = roomCenter -1; z <= roomCenter +1; z++) {
					world.setBlock(roomX +x, roomY, roomZ +z, Blocks.stone_slab);
				}
			}

			// 「石レンガ」の設置
			for (int y = 0; y <= 2; y++) {
				world.setBlock(roomX +roomCenter, roomY +y, roomZ +roomCenter, Blocks.stonebrick);
			}

		} else if (roomType == 1) {
			// 「石レンガ」の設置
			for (int x = roomCenter -2; x <= roomCenter +2; x++) {
				for (int z = roomCenter -2; z <= roomCenter +2; z++) {
					world.setBlock(roomX +x, roomY, roomZ +z, Blocks.stonebrick);
				}
			}

			// 「空気」の設置
			for (int x = roomCenter -1; x <= roomCenter +1; x++) {
				for (int z = roomCenter -1; z <= roomCenter +1; z++) {
					world.setBlockToAir(roomX +x, roomY, roomZ +z);
				}
			}

			// 「石レンガ」の設置
			for (int y = 0; y <= 2; y++) {
				world.setBlock(roomX +roomCenter, roomY +y, roomZ +roomCenter, Blocks.stonebrick);
			}

			// 「水」の設置
			world.setBlock(roomX +roomCenter, roomY +3, roomZ +roomCenter, Blocks.flowing_water);

		} else if (roomType == 2) {
			// 「石ブロック」等の設置
			for (int x = roomCenter -1; x <= roomCenter +1; x++) {
				for (int z = roomCenter -1; z <= roomCenter +1; z++) {
					for (int y = 0; y < roomHeight; y++) {
						setStoneBrick(world, roomX +x, roomY +y, roomZ +z);
					}
				}
			}

		} else {
			// 「石ブロック」等の設置
			for (int y = 0; y < roomHeight; y++) {
				setStoneBrick(world, roomX +roomCenter +2, roomY +y, roomZ +roomCenter +2);
				setStoneBrick(world, roomX +roomCenter +2, roomY +y, roomZ +roomCenter -2);
				setStoneBrick(world, roomX +roomCenter -2, roomY +y, roomZ +roomCenter +2);
				setStoneBrick(world, roomX +roomCenter -2, roomY +y, roomZ +roomCenter -2);
			}

			switch (roomDirection) {
			case 0:
			case 2:
				for (int z = roomCenter -1; z <= roomCenter +1; z++) {
					// 「鉄格子」の設置
					for (int y = 0; y <= 2; y++) {
						world.setBlock(roomX +3, roomY +y, roomZ +z, Blocks.iron_bars);
						world.setBlock(roomX +roomWidth -3, roomY +y, roomZ +z, Blocks.iron_bars);
					}
					// 「石ブロック」等の設置
					for (int y = 3; y < roomHeight; y++) {
						setStoneBrick(world, roomX +3, roomY +y, roomZ +z);
						setStoneBrick(world, roomX +roomWidth -3, roomY +y, roomZ +z);
					}
				}

				for (int x = roomCenter -1; x <= roomCenter +1; x++) {
					// 「鉄格子」の設置
					for (int y = 3; y < roomHeight; y++) {
						world.setBlock(roomX +x, roomY +y, roomZ +3, Blocks.iron_bars);
						world.setBlock(roomX +x, roomY +y, roomZ +roomWidth -3, Blocks.iron_bars);
					}
					// 「石ブロック」等の設置
					for (int y = 0; y <= 2; y++) {
						setStoneBrick(world, roomX +x, roomY +y, roomZ +3);
						setStoneBrick(world, roomX +x, roomY +y, roomZ +roomWidth -3);
					}
				}

				// 「空気」の設置
				world.setBlockToAir(roomX +roomCenter, roomY, roomZ +3);
				world.setBlockToAir(roomX +roomCenter, roomY +1, roomZ +3);
				world.setBlockToAir(roomX +roomCenter, roomY, roomZ +roomWidth -3);
				world.setBlockToAir(roomX +roomCenter, roomY +1, roomZ +roomWidth -3);
				// 「鉄のドア」の設置
				ItemDoor.placeDoorBlock(world, roomX +roomCenter, roomY, roomZ +3, 1, Blocks.iron_door);
				ItemDoor.placeDoorBlock(world, roomX +roomCenter, roomY, roomZ +roomWidth -3, 3, Blocks.iron_door);
				// 「石の感圧板」の設置
				world.setBlock(roomX +roomCenter, roomY, roomZ +2, Blocks.stone_pressure_plate);
				world.setBlock(roomX +roomCenter, roomY, roomZ +roomWidth -2, Blocks.stone_pressure_plate);
				break;
			case 1:
			case 3:
				for (int x = roomCenter -1; x <= roomCenter +1; x++) {
					// 「鉄格子」の設置
					for (int y = 0; y <= 2; y++) {
						world.setBlock(roomX +x, roomY +y, roomZ +3, Blocks.iron_bars);
						world.setBlock(roomX +x, roomY +y, roomZ +roomWidth -3, Blocks.iron_bars);
					}
					// 「石ブロック」等の設置
					for (int y = 3; y < roomHeight; y++) {
						setStoneBrick(world, roomX +x, roomY +y, roomZ +3);
						setStoneBrick(world, roomX +x, roomY +y, roomZ +roomWidth -3);
					}
				}

				for (int z = roomCenter -1; z <= roomCenter +1; z++) {
					// 「鉄格子」の設置
					for (int y = 3; y < roomHeight; y++) {
						world.setBlock(roomX +3, roomY +y, roomZ +z, Blocks.iron_bars);
						world.setBlock(roomX +roomWidth -3, roomY +y, roomZ +z, Blocks.iron_bars);
					}
					// 「石ブロック」等の設置
					for (int y = 0; y <= 2; y++) {
						setStoneBrick(world, roomX +3, roomY +y, roomZ +z);
						setStoneBrick(world, roomX +roomWidth -3, roomY +y, roomZ +z);
					}
				}

				// 「空気」の設置
				world.setBlockToAir(roomX +3, roomY, roomZ +roomCenter);
				world.setBlockToAir(roomX +3, roomY +1, roomZ +roomCenter);
				world.setBlockToAir(roomX +roomWidth -3, roomY, roomZ +roomCenter);
				world.setBlockToAir(roomX +roomWidth -3, roomY +1, roomZ +roomCenter);
				// 「鉄のドア」の設置
				ItemDoor.placeDoorBlock(world, roomX +3, roomY, roomZ +roomCenter, 0, Blocks.iron_door);
				ItemDoor.placeDoorBlock(world, roomX +roomWidth -3, roomY, roomZ +roomCenter, 2, Blocks.iron_door);
				// 「石の感圧板」の設置
				world.setBlock(roomX +2, roomY, roomZ +roomCenter, Blocks.stone_pressure_plate);
				world.setBlock(roomX +roomWidth -2, roomY, roomZ +roomCenter, Blocks.stone_pressure_plate);
				break;
			}
		}

		// 「松明」の設置
		if (roomType == 0) {
			world.setBlock(roomX +roomCenter, roomY +2, roomZ +roomCenter +1, Blocks.torch, 3, 3);
			world.setBlock(roomX +roomCenter, roomY +2, roomZ +roomCenter -1, Blocks.torch, 4, 3);
			world.setBlock(roomX +roomCenter +1, roomY +2, roomZ +roomCenter, Blocks.torch, 1, 3);
			world.setBlock(roomX +roomCenter -1, roomY +2, roomZ +roomCenter, Blocks.torch, 2, 3);
		} else {
			world.setBlock(roomX +1, roomY, roomZ +1, Blocks.torch, 5, 3);
			world.setBlock(roomX +1, roomY, roomZ +roomWidth -1, Blocks.torch, 5, 3);
			world.setBlock(roomX +roomWidth -1, roomY, roomZ +1, Blocks.torch, 5, 3);
			world.setBlock(roomX +roomWidth -1, roomY, roomZ +roomWidth -1, Blocks.torch, 5, 3);
		}


		/* - - - - - - - - - -
		 * 以下、敵のスポーン
		 * - - - - - - - - - */
		if (roomType == 3) {
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter +1, roomY +1, roomZ +roomCenter +1, LadRoomID.STRONGHOLD + LadRoomID.getDifOfRoom());
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter +1, roomY +1, roomZ +roomCenter -1, LadRoomID.STRONGHOLD + LadRoomID.getDifOfRoom());
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter -1, roomY +1, roomZ +roomCenter +1, LadRoomID.STRONGHOLD + LadRoomID.getDifOfRoom());
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter -1, roomY +1, roomZ +roomCenter -1, LadRoomID.STRONGHOLD + LadRoomID.getDifOfRoom());
		} else {
			switch (roomDirection) {
			case 0:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -1, roomY +1, roomZ +roomCenter, LadRoomID.STRONGHOLD + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -1, roomY +1, roomZ +roomCenter +3, LadRoomID.STRONGHOLD + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -1, roomY +1, roomZ +roomCenter -3, LadRoomID.STRONGHOLD + LadRoomID.getDifOfRoom());
				break;
			case 1:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +1, roomZ +roomWidth -1, LadRoomID.STRONGHOLD + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter +3, roomY +1, roomZ +roomWidth -1, LadRoomID.STRONGHOLD + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter -3, roomY +1, roomZ +roomWidth -1, LadRoomID.STRONGHOLD + LadRoomID.getDifOfRoom());
				break;
			case 2:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +1, roomY +1, roomZ +roomCenter, LadRoomID.STRONGHOLD + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +1, roomY +1, roomZ +roomCenter +3, LadRoomID.STRONGHOLD + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +1, roomY +1, roomZ +roomCenter -3, LadRoomID.STRONGHOLD + LadRoomID.getDifOfRoom());
				break;
			case 3:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +1, roomZ +1, LadRoomID.STRONGHOLD + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter +3, roomY +1, roomZ +1, LadRoomID.STRONGHOLD + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter -3, roomY +1, roomZ +1, LadRoomID.STRONGHOLD + LadRoomID.getDifOfRoom());
				break;
			}
		}
	}

	public static void setStoneBrick(World world, int x, int y, int z) {
		Random rand = new Random();
		switch (rand.nextInt(4)) {
		case 0:
			world.setBlock(x, y, z, Blocks.stonebrick, 1, 2);
			break;
		case 1:
			world.setBlock(x, y, z, Blocks.stonebrick, 2, 2);
			break;
		default:
			world.setBlock(x, y, z, Blocks.stonebrick);
			break;
		}
	}
}
