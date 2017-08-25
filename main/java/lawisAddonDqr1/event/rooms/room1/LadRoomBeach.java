package lawisAddonDqr1.event.rooms.room1;

import java.util.Random;

import lawisAddonDqr1.achievement.LadAchievementCore;
import lawisAddonDqr1.config.LadDebug;
import lawisAddonDqr1.event.entities.LadSpawnEnemyCore;
import lawisAddonDqr1.event.rooms.LadRoomID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class LadRoomBeach {
	/*
	 * 砂浜の戦闘部屋
	 */
	public static void setRoom(World world, EntityPlayer player) {
		Random rand = new Random();
		int roomDirection = LadRoomID.getDirectionRoom(player, 0);

		int roomX = (int)player.posX;			// 部屋の起点となるX座標
		int roomZ = (int)player.posZ;			// 部屋の起点となるZ座標
		int roomY = (int)player.posY -1;		// 部屋の起点となるY座標

		int roomHeight = 3;					// 部屋の高さ
		int roomDepth = -4;					// 部屋の深さ
		int roomWidth = 12;					// 部屋の幅（-1）
		int roomCenter = roomWidth /2;		// 部屋の中心

		int roomType = rand.nextInt(6);		// 部屋の種類

		// [Debug] 戦闘部屋固定時に生成方向がチャット表示される（デバッグ用）
		if (LadDebug.getDebugRoom() >=0) {
			player.addChatMessage(new ChatComponentTranslation("roomDirection == " + roomDirection));
			player.addChatMessage(new ChatComponentTranslation("roomType == " + roomType));
		}

		// 実績の取得
		player.triggerAchievement(LadAchievementCore.roomBeach);

		// マイナス座標の時に、部屋の位置がズレることの修正
		if (roomX < 0) roomX -=1;
		if (roomZ < 0) roomZ -=1;

		// プレイヤーの向きから部屋の起点となる座標を決める
		switch (roomDirection) {
		case 0:
			roomX -= 2;
			roomZ -= roomCenter;
			break;
		case 1:
			roomX -= roomCenter;
			roomZ -= 2;
			break;
		case 2:
			roomX -= roomWidth -2;
			roomZ -= roomCenter;
			break;
		case 3:
			roomX -= roomCenter;
			roomZ -= roomWidth -2;
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

		/* 地面 */
		// 地面よりも下に「土ブロック」を敷く
		for (int x = 0; x <= roomWidth; x++) {
			for (int z = 0; z <= roomWidth; z++) {
				world.setBlock(roomX +x, roomY +roomDepth -1, roomZ +z, Blocks.dirt);
			}
		}

		// 地面に「砂ブロック」を敷く
		for (int x = 0; x <= roomWidth; x++) {
			for (int z = 0; z <= roomWidth; z++) {
				for (int y = roomDepth; y <= 0; y++) {
					world.setBlock(roomX +x, roomY +y, roomZ +z, Blocks.sand);
				}
			}
		}

		// 手前と奥で差別化する必要がない部分を2方向に分けて生成
		switch (roomDirection) {
		case 0:
		case 2:
			// 「空気」を設置する
			for (int x = 2; x <= roomWidth -2; x++) {
				for (int z = 1; z <= roomWidth -1; z++) {
					for (int y = 0; y <= 1; y++) {
						world.setBlockToAir(roomX +x, roomY +y, roomZ +z);
					}
				}
			}
			for (int z = 4; z <= roomWidth -4; z++) {
				for (int y = 0; y <= 1; y++) {
					world.setBlockToAir(roomX +1, roomY +y, roomZ +z);
					world.setBlockToAir(roomX +roomWidth -1, roomY +y, roomZ +z);
				}
			}

			// 「水」を設置する
			for (int x = 2; x <= roomWidth -2; x++) {
				for (int z = 1; z <= roomWidth -1; z++) {
					for (int y = roomDepth; y <= -1; y++) {
						world.setBlock(roomX +x, roomY +y, roomZ +z, Blocks.water);
					}
				}
			}

			// 固定の足場となる「砂ブロック」を設置する
			for (int x = 2; x <= 4; x++) {
				for (int z = 4; z <= roomWidth -4; z++) {
					for (int y = roomDepth; y <= -1; y++) {
						world.setBlock(roomX +x, roomY +y, roomZ +z, Blocks.sand);
						world.setBlock(roomX +roomWidth -x, roomY +y, roomZ +z, Blocks.sand);
					}
				}
			}

			// 変動の足場となる、中央の両端の「砂ブロック」の設置
			if (roomType%2 == 0) {
				for (int x = 5; x <= roomWidth -5; x++) {
					for (int z = 1; z <= 3; z++) {
						for (int y = roomDepth; y <= -1; y++) {
							world.setBlock(roomX +x, roomY +y, roomZ +z, Blocks.sand);
							world.setBlock(roomX +x, roomY +y, roomZ +roomWidth -z, Blocks.sand);
						}
					}
				}
			}

			// 明るさ確保のための「松明」の設置
			world.setBlock(roomX +1, roomY, roomZ +4, Blocks.torch, 5, 3);
			world.setBlock(roomX +1, roomY, roomZ +roomWidth -4, Blocks.torch, 5, 3);
			world.setBlock(roomX +roomWidth -1, roomY, roomZ +4, Blocks.torch, 5, 3);
			world.setBlock(roomX +roomWidth -1, roomY, roomZ +roomWidth -4, Blocks.torch, 5, 3);
			break;

		case 1:
		case 3:
			// 「空気」を設置する
			for (int x = 1; x <= roomWidth -1; x++) {
				for (int z = 2; z <= roomWidth -2; z++) {
					for (int y = 0; y <= 1; y++) {
						world.setBlockToAir(roomX +x, roomY +y, roomZ +z);
					}
				}
			}
			for (int x = 4; x <= roomWidth -4; x++) {
				for (int y = 0; y <= 1; y++) {
					world.setBlockToAir(roomX +x, roomY +y, roomZ +1);
					world.setBlockToAir(roomX +x, roomY +y, roomZ +roomWidth -1);
				}
			}

			// 「水」を設置する
			for (int x = 1; x <= roomWidth -1; x++) {
				for (int z = 2; z <= roomWidth -2; z++) {
					for (int y = roomDepth; y <= -1; y++) {
						world.setBlock(roomX +x, roomY +y, roomZ +z, Blocks.water);
					}
				}
			}

			// 固定の足場となる「砂ブロック」を設置する
			for (int x = 4; x <= roomWidth -4; x++) {
				for (int z = 2; z <= 4; z++) {
					for (int y = roomDepth; y <= -1; y++) {
						world.setBlock(roomX +x, roomY +y, roomZ +z, Blocks.sand);
						world.setBlock(roomX +x, roomY +y, roomZ +roomWidth -z, Blocks.sand);
					}
				}
			}

			// 変動の足場となる、中央の両端の「砂ブロック」の設置
			if (roomType%2 == 0) {
				for (int x = 1; x <= 3; x++) {
					for (int z = 5; z <= roomWidth -5; z++) {
						for (int y = roomDepth; y <= -1; y++) {
							world.setBlock(roomX +x, roomY +y, roomZ +z, Blocks.sand);
							world.setBlock(roomX +roomWidth -x, roomY +y, roomZ +z, Blocks.sand);
						}
					}
				}
			}

			// 明るさ確保のための「松明」の設置
			world.setBlock(roomX +4, roomY, roomZ +1, Blocks.torch, 5, 3);
			world.setBlock(roomX +4, roomY, roomZ +roomWidth -1, Blocks.torch, 5, 3);
			world.setBlock(roomX +roomWidth -4, roomY, roomZ +1, Blocks.torch, 5, 3);
			world.setBlock(roomX +roomWidth -4, roomY, roomZ +roomWidth -1, Blocks.torch, 5, 3);
			break;
		}

		// 変動の足場となる、中央の「砂ブロック」の設置
		if (roomType%2 == 1) {
			for (int x = 5; x <= roomWidth -5; x++) {
				for (int z = 5; z <= roomWidth -5; z++) {
					for (int y = roomDepth; y <= -1; y++) {
						world.setBlock(roomX +x, roomY +y, roomZ +z, Blocks.sand);
					}
				}
			}
		}

		// 「手前」と「奥」を差別化しなければならない部分を、4方向に分けて生成
		switch (roomDirection) {
		case 0:
			// 変動の足場となる、手前の両端の「砂ブロック」の設置
			if (roomType%3 == 0) {
				for (int x = 2; x <= 4; x++) {
					for (int z = 1; z <= 3; z++) {
						for (int y = roomDepth; y <= -1; y++) {
							world.setBlock(roomX +x, roomY +y, roomZ +z, Blocks.sand);
							world.setBlock(roomX +x, roomY +y, roomZ +roomWidth -z, Blocks.sand);
						}
					}
				}
			}

			// 変動の足場となる、奥の両端の「砂ブロック」の設置
			if (roomType%3 == 1) {
				for (int x = roomWidth -4; x <= roomWidth -2; x++) {
					for (int z = 1; z <= 3; z++) {
						for (int y = roomDepth; y <= -1; y++) {
							world.setBlock(roomX +x, roomY +y, roomZ +z, Blocks.sand);
							world.setBlock(roomX +x, roomY +y, roomZ +roomWidth -z, Blocks.sand);
						}
					}
				}
			}
			break;

		case 1:
			// 変動の足場となる、手前の両端の「砂ブロック」の設置
			if (roomType%3 == 0) {
				for (int x = 1; x <= 3; x++) {
					for (int z = 2; z <= 4; z++) {
						for (int y = roomDepth; y <= -1; y++) {
							world.setBlock(roomX +x, roomY +y, roomZ +z, Blocks.sand);
							world.setBlock(roomX +roomWidth -x, roomY +y, roomZ +z, Blocks.sand);
						}
					}
				}
			}
			// 変動の足場となる、奥の両端の「砂ブロック」の設置
			if (roomType%3 == 1) {
				for (int x = 1; x <= 3; x++) {
					for (int z = roomWidth -4; z <= roomWidth -2; z++) {
						for (int y = roomDepth; y <= -1; y++) {
							world.setBlock(roomX +x, roomY +y, roomZ +z, Blocks.sand);
							world.setBlock(roomX +roomWidth -x, roomY +y, roomZ +z, Blocks.sand);
						}
					}
				}
			}

			break;

		case 2:
			// 変動の足場となる、手前の両端の「砂ブロック」の設置
			if (roomType%3 == 0) {
				for (int x = roomWidth -4; x <= roomWidth -2; x++) {
					for (int z = 1; z <= 3; z++) {
						for (int y = roomDepth; y <= -1; y++) {
							world.setBlock(roomX +x, roomY +y, roomZ +z, Blocks.sand);
							world.setBlock(roomX +x, roomY +y, roomZ +roomWidth -z, Blocks.sand);
						}
					}
				}
			}
			// 変動の足場となる、奥の両端の「砂ブロック」の設置
			if (roomType%3 == 1) {
				for (int x = 2; x <= 4; x++) {
					for (int z = 1; z <= 3; z++) {
						for (int y = roomDepth; y <= -1; y++) {
							world.setBlock(roomX +x, roomY +y, roomZ +z, Blocks.sand);
							world.setBlock(roomX +x, roomY +y, roomZ +roomWidth -z, Blocks.sand);
						}
					}
				}
			}
			break;

		case 3:
			// 変動の足場となる、手前の両端の「砂ブロック」の設置
			if (roomType%3 == 0) {
				for (int x = 1; x <= 3; x++) {
					for (int z = roomWidth -4; z <= roomWidth -2; z++) {
						for (int y = roomDepth; y <= -1; y++) {
							world.setBlock(roomX +x, roomY +y, roomZ +z, Blocks.sand);
							world.setBlock(roomX +roomWidth -x, roomY +y, roomZ +z, Blocks.sand);
						}
					}
				}
			}
			// 変動の足場となる、奥の両端の「砂ブロック」の設置
			if (roomType%3 == 1) {
				for (int x = 1; x <= 3; x++) {
					for (int z = 2; z <= 4; z++) {
						for (int y = roomDepth; y <= -1; y++) {
							world.setBlock(roomX +x, roomY +y, roomZ +z, Blocks.sand);
							world.setBlock(roomX +roomWidth -x, roomY +y, roomZ +z, Blocks.sand);
						}
					}
				}
			}
			break;
		}


		/* - - - - - - - - - -
		 * 以下、敵のスポーン
		 * - - - - - - - - - */

		// 確定スポーン
		switch (roomDirection) {
		case 0:
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -2, roomY +1, roomZ +roomCenter, LadRoomID.BEACH + LadRoomID.getDifOfRoom());
			break;
		case 1:
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +1, roomZ +roomWidth -2, LadRoomID.BEACH + LadRoomID.getDifOfRoom());
			break;
		case 2:
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +2, roomY +1, roomZ +roomCenter, LadRoomID.BEACH + LadRoomID.getDifOfRoom());
			break;
		case 3:
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +1, roomZ +2, LadRoomID.BEACH + LadRoomID.getDifOfRoom());
			break;
		}

		// 変動スポーン
		// 奥の両端が陸の時
		if (roomType%3 == 1) {
			switch (roomDirection) {
			case 0:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -2, roomY +1, roomZ +2, LadRoomID.BEACH + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -2, roomY +1, roomZ +roomWidth -2, LadRoomID.BEACH + LadRoomID.getDifOfRoom());
				break;
			case 1:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +2, roomY +1, roomZ +roomWidth -2, LadRoomID.BEACH + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -2, roomY +1, roomZ +roomWidth -2, LadRoomID.BEACH + LadRoomID.getDifOfRoom());
				break;
			case 2:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +2, roomY +1, roomZ +2, LadRoomID.BEACH + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +2, roomY +1, roomZ +roomWidth -2, LadRoomID.BEACH + LadRoomID.getDifOfRoom());
				break;
			case 3:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +2, roomY +1, roomZ +2, LadRoomID.BEACH + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -2, roomY +1, roomZ +2, LadRoomID.BEACH + LadRoomID.getDifOfRoom());
				break;
			}
		// 中央の両端が陸の時
		} else if (roomType%2 == 0) {
			switch (roomDirection) {
			case 0:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +1, roomZ +2, LadRoomID.BEACH + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +1, roomZ +roomWidth -2, LadRoomID.BEACH + LadRoomID.getDifOfRoom());
				break;
			case 1:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +2, roomY +1, roomZ +roomCenter, LadRoomID.BEACH + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -2, roomY +1, roomZ +roomCenter, LadRoomID.BEACH + LadRoomID.getDifOfRoom());
				break;
			case 2:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +1, roomZ +2, LadRoomID.BEACH + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +1, roomZ +roomWidth -2, LadRoomID.BEACH + LadRoomID.getDifOfRoom());
				break;
			case 3:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +2, roomY +1, roomZ +roomCenter, LadRoomID.BEACH + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -2, roomY +1, roomZ +roomCenter, LadRoomID.BEACH + LadRoomID.getDifOfRoom());
				break;
			}
		}
	}
}
