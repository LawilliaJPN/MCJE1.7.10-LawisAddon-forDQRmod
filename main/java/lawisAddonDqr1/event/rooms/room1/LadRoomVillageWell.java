package lawisAddonDqr1.event.rooms.room1;

import java.util.Random;

import lawisAddonDqr1.config.LadDebug;
import lawisAddonDqr1.event.enemies.SpawnEnemyCore;
import lawisAddonDqr1.event.rooms.LadRoomCore;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class LadRoomVillageWell {
	/*
	 * バニラの村の井戸をモチーフとした戦闘部屋
	 *
	 * [Unimplemented] 負荷軽減オフ時に「呪われた井戸」の丸石の一部を苔石や空気に
	 */
	public static void setRoom(World world, EntityPlayer player){
		Random rand = new Random();

		int playerX = (int)player.posX;		// プレイヤーのX座標
		int playerZ = (int)player.posZ -1;	// プレイヤーのZ座標

		int roomX = playerX;					// 部屋の起点となるX座標
		int roomZ = playerZ;					// 部屋の起点となるZ座標（-1）
		int roomY = (int)player.posY -1;		// 部屋の起点となるY座標（-1）

		int roomHeight = 6;					// 部屋の高さ
		int roomWidth = 11;					// 部屋の幅（-1）
		int roomCenter = roomWidth /2;		// 部屋の中央

		int roomType = 0;						// 部屋の種類
		int roomDirection = 0;				// 部屋の生成方向
		boolean hasCursed = false;			// 「呪われた井戸」かどうか

		int enemyX = roomX;					// 敵をスポーンさせるX座標決定に使用
		int enemyZ = roomZ;					// 敵をスポーンさせるZ座標決定に使用

		// 「呪われた井戸」かどうかの判定
		if (LadRoomCore.getDifOfRoom() == 2) hasCursed = true;
		else if ((LadRoomCore.getDifOfRoom() == 3) && (rand.nextInt(2) == 0)) hasCursed = true;
		if (LadDebug.getDebugRoom() == LadRoomCore.VILLAGE_WELL_HAS_CURSED) hasCursed = true;
		else if (LadDebug.getDebugRoom() == LadRoomCore.VILLAGE_WELL) hasCursed = false;

		// 井戸の生成方向を決定
		if (hasCursed) LadRoomCore.getDirectionRoom(player, 0);
		else LadRoomCore.getDirectionRoom(player, 1);

		// 「呪われた井戸」では、井戸が大きくなる
		if (hasCursed) {
			roomWidth++;
			roomCenter = roomWidth /2;
		}

		// [Debug] 戦闘部屋固定時に生成方向がチャット表示される（デバッグ用）
		if (LadDebug.getDebugRoom() >=0) {
			player.addChatMessage(new ChatComponentTranslation("roomDirection == " + roomDirection));
		}


		/* 部屋の種類 */
		// 「呪われた井戸」の部屋の種類
		if (hasCursed) {
			int r = rand.nextInt(10);
			// 10%の確率で、「ゾンビ大量発生」
			if (r == 0) roomType = 1;
			// 30%の確率で、「屋根上スタート」
			else if (r <= 3) roomType = 2;
		}

		/* 生成位置の調整 */
		if (roomType == 2) {
			// 「屋根上スタート」時の生成位置
			roomX -= roomCenter;
			roomZ -= roomCenter;
			roomY -= 4;
			roomHeight++;

		} else if (hasCursed) {
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

		} else {
			// プレイヤーの向きから部屋の起点となる座標を決める
			switch (roomDirection) {
			case 0:
				roomX -= 1;
				roomZ -= roomWidth -1;
				enemyX += roomWidth -1;
				enemyZ += 1;
				break;
			case 1:
				roomX -= 1;
				roomZ -= 1;
				enemyX += roomWidth -1;
				enemyZ += roomWidth -1;
				break;
			case 2:
				roomX -= roomWidth -1;
				roomZ -= 1;
				enemyX += 1;
				enemyZ += roomWidth -1;
				break;
			case 3:
				roomX -= roomWidth -1;
				roomZ -= roomWidth -1;
				enemyX += 1;
				enemyZ += 1;
				break;
			}
		}

		/* - - - - - - - - - -
		 * 以下、部屋の生成
		 * - - - - - - - - - */

		/* 地面 */
		// 地面の下に「土ブロック」を敷く（地面となる「砂利ブロック」の落下防止のため）
		for (int x = 0; x <= roomWidth; x++) {
			for (int z = 0; z <= roomWidth; z++) {
				world.setBlock(roomX +x, roomY -2, roomZ +z, Blocks.dirt);
			}
		}

		// 地面に「草ブロック」を敷く
		for (int x = 0; x <= roomWidth; x++) {
			for (int z = 0; z <= roomWidth; z++) {
				world.setBlock(roomX +x, roomY -1, roomZ +z, Blocks.grass);
			}
		}

		// 地面に「砂利ブロック」を敷く
		for (int x = 0; x <= roomWidth; x++) {
			for (int z = 5; z <= roomWidth -5; z++) {
				world.setBlock(roomX +x, roomY -1, roomZ +z, Blocks.gravel);
			}
		}
		for (int z = 0; z <= roomWidth; z++) {
			for (int x = 5; x <= roomWidth -5; x++) {
				world.setBlock(roomX +x, roomY -1, roomZ +z, Blocks.gravel);
			}
		}

		/* 空間 */
		// 「空気」の設置
		for (int x = 0; x <= roomWidth; x++) {
			for (int z = 0; z <= roomWidth; z++) {
				for (int y = 0; y <= roomHeight; y++) {
					world.setBlockToAir(roomX +x, roomY +y, roomZ +z);
				}
			}
		}

		/* 井戸 */
		// 井戸の周りの「砂利ブロック」の設置
		for (int x = 3; x <= roomWidth -3; x++) {
			for (int z = 3; z <= roomWidth -3; z++) {
				world.setBlock(roomX +x, roomY, roomZ +z, Blocks.gravel);
			}
		}

		// 井戸の周りの「丸石」の設置
		for (int x = 4; x <= roomWidth -4; x++) {
			for (int y = 0; y <= 1; y++) {
				for (int z = 4; z <= roomWidth -4; z++) {
					world.setBlock(roomX +x, roomY +y, roomZ +z, Blocks.cobblestone);
				}
			}
		}

		// 井戸の中の「水」の設置
		for (int x = 5; x <= roomWidth -5; x++) {
			for (int z = 5; z <= roomWidth -5; z++) {
				world.setBlock(roomX +x, roomY, roomZ +z, Blocks.water);
				world.setBlock(roomX +x, roomY +1, roomZ +z, Blocks.water);
			}
		}

		// 「呪われた井戸」ではない時（「呪われた井戸」では水位が上がる）
		if (!hasCursed) {
			// 井戸の中の「空気」の設置
			for (int x = 5; x <= roomWidth -5; x++) {
				for (int z = 5; z <= roomWidth -5; z++) {
					world.setBlockToAir(roomX +x, roomY +1, roomZ +z);
				}
			}
		}

		// 「フェンス」の設置
		world.setBlock(roomX +4, roomY +2, roomZ +4, Blocks.fence);
		world.setBlock(roomX +4, roomY +3, roomZ +4, Blocks.fence);
		world.setBlock(roomX +4, roomY +2, roomZ +roomWidth -4, Blocks.fence);
		world.setBlock(roomX +4, roomY +3, roomZ +roomWidth -4, Blocks.fence);
		world.setBlock(roomX +roomWidth -4, roomY +2, roomZ +4, Blocks.fence);
		world.setBlock(roomX +roomWidth -4, roomY +3, roomZ +4, Blocks.fence);
		world.setBlock(roomX +roomWidth -4, roomY +2, roomZ +roomWidth -4, Blocks.fence);
		world.setBlock(roomX +roomWidth -4, roomY +3, roomZ +roomWidth -4, Blocks.fence);

		// 屋根の「丸石」の設置
		for (int x = 4; x <= roomWidth -4; x++) {
			for (int z = 4; z <= roomWidth -4; z++) {
				world.setBlock(roomX +x, roomY +4, roomZ +z, Blocks.cobblestone);
			}
		}

		/* 光源 */
		// 明るさ確保のための「松明」の設置
		switch (roomType) {
		case 1:
			// 「ゾンビ大量発生」では、井戸の水部分に「松明」
			world.setBlock(roomX +roomCenter, roomY +2, roomZ +4, Blocks.torch, 5, 3);
			world.setBlock(roomX +roomCenter, roomY +2, roomZ +roomWidth -4, Blocks.torch, 5, 3);
			world.setBlock(roomX +4, roomY +2, roomZ +roomCenter, Blocks.torch, 5, 3);
			world.setBlock(roomX +roomWidth -4, roomY +2, roomZ +roomCenter, Blocks.torch, 5, 3);
			break;
		case 2:
			// 「屋根上スタート」では、屋根の上に「松明」
			world.setBlock(roomX +4, roomY +5, roomZ +4, Blocks.torch, 5, 3);
			world.setBlock(roomX +roomWidth -4, roomY +5, roomZ +4, Blocks.torch, 5, 3);
			world.setBlock(roomX +4, roomY +5, roomZ +roomWidth -4, Blocks.torch, 5, 3);
			world.setBlock(roomX +roomWidth -4, roomY +5, roomZ +roomWidth -4, Blocks.torch, 5, 3);
			break;
		default:
			// 通常時は部屋の四隅に「松明」
			world.setBlock(roomX +2, roomY, roomZ +2, Blocks.torch, 5, 3);
			world.setBlock(roomX +roomWidth -2, roomY, roomZ +2, Blocks.torch, 5, 3);
			world.setBlock(roomX +2, roomY, roomZ +roomWidth -2, Blocks.torch, 5, 3);
			world.setBlock(roomX +roomWidth -2, roomY, roomZ +roomWidth -2, Blocks.torch, 5, 3);
		}



		/* - - - - - - - - - -
		 * 以下、敵のスポーン
		 * - - - - - - - - - */

		// 「ゾンビ大量発生」時のスポーン
		if (roomType == 1) {
			// 井戸の中に「ゾンビ」が大量発生(全難易度共通)
			for (int x = 5; x <= roomWidth -5; x++) {
				for (int z = 5; z <= roomWidth -5; z++) {
					SpawnEnemyCore.spawnEnemy(world, player, roomX +x, roomY, roomZ +z, LadRoomCore.VILLAGE_WELL_HAS_CURSED);
				}
			}

			if (LadRoomCore.getDifOfRoom() >= 3) {
				// 屋根の上に「ゾンビ」が大量発生(Y=35以下)
				for (int x = 5; x <= roomWidth -5; x++) {
					for (int z = 5; z <= roomWidth -5; z++) {
						SpawnEnemyCore.spawnEnemy(world, player, roomX +x, roomY +6, roomZ +z, LadRoomCore.VILLAGE_WELL_HAS_CURSED);
					}
				}
			}


		// 「屋根上スタート」時のスポーン
		} else if (roomType == 2) {
			// 確定スポーン
			SpawnEnemyCore.spawnEnemy(world, player, roomX +1, roomY, roomZ +1, LadRoomCore.VILLAGE_WELL_HAS_CURSED + LadRoomCore.getDifOfRoom());
			SpawnEnemyCore.spawnEnemy(world, player, roomX +1, roomY, roomZ +roomWidth -1, LadRoomCore.VILLAGE_WELL_HAS_CURSED + LadRoomCore.getDifOfRoom());
			SpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -1, roomY, roomZ +1, LadRoomCore.VILLAGE_WELL_HAS_CURSED + LadRoomCore.getDifOfRoom());
			SpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -1, roomY, roomZ +roomWidth -1, LadRoomCore.VILLAGE_WELL_HAS_CURSED + LadRoomCore.getDifOfRoom());


		// 「呪われた井戸」の通常スポーン
		} else if (hasCursed) {
			int x = 0, z = 0;

			// 確定スポーン
			SpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY, roomZ +roomCenter, LadRoomCore.VILLAGE_WELL_HAS_CURSED_ON_WATER + LadRoomCore.getDifOfRoom());

			// 確率スポーン
			for (int i = 0; i < 4; i++) {
				if (LadRoomCore.getDifOfRoom() >= rand.nextInt(4)) {
					switch (i) {
					case 0:
						x = roomX +1;
						z = roomZ +roomCenter;
						break;
					case 1:
						x = roomX +roomWidth -1;
						z = roomZ +roomCenter;
						break;
					case 2:
						x = roomX +roomCenter;
						z = roomZ +1;
						break;
					case 3:
						x = roomX +roomCenter;
						z = roomZ +roomWidth -1;
						break;
					}

					if (!( (playerX == x) && (playerZ == z) )) {
						SpawnEnemyCore.spawnEnemy(world, player, x, roomY, z, LadRoomCore.VILLAGE_WELL_HAS_CURSED + LadRoomCore.getDifOfRoom());
					}
				}
			}


		// 井戸（通常）の通常スポーン
		} else {
			int x = 0, z = 0;

			// 確定スポーン
			SpawnEnemyCore.spawnEnemy(world, player, enemyX, roomY, enemyZ, LadRoomCore.VILLAGE_WELL + LadRoomCore.getDifOfRoom());

			// 確率スポーン
			for (int i = 0; i < 4; i++) {
				if (LadRoomCore.getDifOfRoom() >= rand.nextInt(4)) {
					switch (i) {
					case 0:
						x = roomX +1;
						z = roomZ +1;
						break;
					case 1:
						x = roomX +1;
						z = roomZ +roomWidth -1;
						break;
					case 2:
						x = roomX +roomWidth -1;
						z = roomZ +1;
						break;
					case 3:
						x = roomX +roomWidth -1;
						z = roomZ +roomWidth -1;
						break;
					}

					if (!((playerX == x) && (playerZ == z)) && !((enemyX == x) && (enemyZ == z))) {
						SpawnEnemyCore.spawnEnemy(world, player, x, roomY, z, LadRoomCore.VILLAGE_WELL + LadRoomCore.getDifOfRoom());
					}
				}
			}
		}

	}
	/* 設計図
	o,0,1,2,3,4,5,6,7,8,9,0,1,x
	0,_,_,_,_,_,g,g,_,_,_,_,_
	1,_,3,_,_,_,g,g,_,_,_,2,_
	2,_,_,t,_,_,g,g,_,_,t,_,_
	3,_,_,_,g,g,g,g,g,g,_,_,_
	4,_,_,_,g,c,c,c,c,g,_,_,_
	5,g,g,g,g,c,w,w,c,g,g,g,g
	6,g,g,g,g,c,w,w,c,g,g,g,g
	7,_,_,_,g,c,c,c,c,g,_,_,_
	8,_,_,_,g,g,g,g,g,g,_,_,_
	9,_,_,t,_,_,g,g,_,_,t,_,_
	0,_,0,_,_,_,g,g,_,_,_,1,_
	1,_,_,_,_,_,g,g,_,_,_,_,_
	z
	*/
}

