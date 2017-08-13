package lawisAddonDqr1.event.rooms.room1;

import java.util.Random;

import dqr.api.Blocks.DQDecorates;
import lawisAddonDqr1.config.LadConfigCore;
import lawisAddonDqr1.config.LadDebug;
import lawisAddonDqr1.event.enemies.SpawnEnemyCore;
import lawisAddonDqr1.event.rooms.RoomID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class Room13DesertWell {
	/*
	 * バニラの砂漠の井戸をモチーフとした戦闘部屋（村の井戸ではない）
	 */
	public static void setRoom(World world, EntityPlayer player, int direction) {
		Random rand = new Random();

		int roomX = (int)player.posX;			// 部屋の起点となるX座標
		int roomZ = (int)player.posZ -1;		// 部屋の起点となるZ座標（-1）
		int roomY = (int)player.posY -1;		// 部屋の起点となるY座標（-1）

		int roomHeight = 6;					// 部屋の高さ
		int roomWidth = 10;					// 部屋の幅（-1）
		int roomCenter = roomWidth /2;		// 部屋の中心
		int roomType = rand.nextInt(8);		// 部屋の種類
		int roomDepth = -7;					// 地下室の深さ

		// [Debug] 戦闘部屋固定時に生成方向がチャット表示される（デバッグ用）
		if (LadDebug.getDebugRoom() >=0) {
			player.addChatMessage(new ChatComponentTranslation("direction == " + direction));
		}

		if (roomType == 3) {
			// 「旱魃」中央スタート（コンフィグ：負荷軽減オンの時）
			if (LadConfigCore.isRoomReduction) roomType = 2;

			// 「旱魃」地下室（コンフィグ：負荷軽減オフのみ）
			else roomType =3;
		}

		// 屋根上スタート
		if (roomType/2 == 0) {
			roomX -= roomCenter;
			roomZ -= roomCenter;
			roomY -= 3;

		// 「旱魃」中央スタート
		} else if (roomType == 2) {
			roomX -= roomCenter;
			roomZ -= roomCenter;

		// 通常時・「旱魃」地下室
		} else {
			// プレイヤーの向きから部屋の起点となる座標を決める
			switch (direction) {
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
		}

		/* - - - - - - - - - -
		 * 以下、部屋の生成
		 * - - - - - - - - - */

		/* 地面 */
		// 地面の下に「砂岩ブロック」を敷く（地面となる「砂ブロック」の落下防止のため）
		for (int x = 0; x <= roomWidth; x++) {
			for (int z = 0; z <= roomWidth; z++) {
				world.setBlock(roomX +x, roomY -2, roomZ +z, Blocks.sandstone);
			}
		}

		// 地面に「砂ブロック」を敷く
		for (int x = 0; x <= roomWidth; x++) {
			for (int z = 0; z <= roomWidth; z++) {
				world.setBlock(roomX +x, roomY -1, roomZ +z, Blocks.sand);
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

		/* 井戸の底 */
		// 井戸の底に「砂岩ブロック」を敷く
		for (int x = roomCenter -2; x <= roomCenter +2; x++) {
			for (int z = roomCenter -2; z <= roomCenter +2; z++) {
				for (int y = -1; y <= 0; y++) {
					world.setBlock(roomX +x, roomY +y, roomZ +z, Blocks.sandstone);
				}
			}
		}

		if (roomType == 2) {
			// 井戸の中に「砂」を敷く
			world.setBlock(roomX +roomCenter, roomY -1, roomZ +roomCenter, Blocks.sand);
			world.setBlock(roomX +roomCenter, roomY -1, roomZ +roomCenter -1, Blocks.sand);
			world.setBlock(roomX +roomCenter, roomY -1, roomZ +roomCenter +1, Blocks.sand);
			world.setBlock(roomX +roomCenter -1, roomY -1, roomZ +roomCenter, Blocks.sand);
			world.setBlock(roomX +roomCenter +1, roomY -1, roomZ +roomCenter, Blocks.sand);
		} else if (roomType != 3) {
			// 井戸の中に「水」を敷く
			world.setBlock(roomX +roomCenter, roomY -1, roomZ +roomCenter, Blocks.water);
			world.setBlock(roomX +roomCenter, roomY -1, roomZ +roomCenter -1, Blocks.water);
			world.setBlock(roomX +roomCenter, roomY -1, roomZ +roomCenter +1, Blocks.water);
			world.setBlock(roomX +roomCenter -1, roomY -1, roomZ +roomCenter, Blocks.water);
			world.setBlock(roomX +roomCenter +1, roomY -1, roomZ +roomCenter, Blocks.water);
		}

		// 井戸の中に「空気」を設置
		world.setBlockToAir(roomX +roomCenter, roomY, roomZ +roomCenter);
		world.setBlockToAir(roomX +roomCenter, roomY, roomZ +roomCenter -1);
		world.setBlockToAir(roomX +roomCenter, roomY, roomZ +roomCenter +1);
		world.setBlockToAir(roomX +roomCenter -1, roomY, roomZ +roomCenter);
		world.setBlockToAir(roomX +roomCenter +1, roomY, roomZ +roomCenter);

		// 井戸の周りに「砂岩ハーフブロック」を敷く
		world.setBlock(roomX +roomCenter, roomY, roomZ +roomCenter -2, Blocks.stone_slab, 1, 2);
		world.setBlock(roomX +roomCenter, roomY, roomZ +roomCenter +2, Blocks.stone_slab, 1, 2);
		world.setBlock(roomX +roomCenter -2, roomY, roomZ +roomCenter, Blocks.stone_slab, 1, 2);
		world.setBlock(roomX +roomCenter +2, roomY, roomZ +roomCenter, Blocks.stone_slab, 1, 2);


		/* 井戸の屋根 */
		// 柱となる「砂岩」を設置
		for (int y = 1; y <= 2; y++) {
			world.setBlock(roomX +roomCenter -1, roomY +y, roomZ +roomCenter -1, Blocks.sandstone);
			world.setBlock(roomX +roomCenter -1, roomY +y, roomZ +roomCenter +1, Blocks.sandstone);
			world.setBlock(roomX +roomCenter +1, roomY +y, roomZ +roomCenter -1, Blocks.sandstone);
			world.setBlock(roomX +roomCenter +1, roomY +y, roomZ +roomCenter +1, Blocks.sandstone);
		}

		// 屋根となる「砂岩ハーフブロック」を設置
		for (int x = roomCenter -1; x <= roomCenter +1; x++) {
			for (int z = roomCenter -1; z <= roomCenter +1; z++) {
				world.setBlock(roomX +x, roomY +3, roomZ +z, Blocks.stone_slab, 1, 2);
			}
		}

		// 頂点の「砂岩」を設置
		world.setBlock(roomX +roomCenter, roomY +3, roomZ +roomCenter, Blocks.sandstone);


		/* 装飾 */
		// 枯れ木・サボテン
		for (int i = 0; i < 4; i++) {
			int x = 0, z = 0;

			switch (i) {
			case 0:
				x = 1;
				z = 1;
				break;
			case 1:
				x = 1;
				z = roomWidth -1;
				break;
			case 2:
				x = roomWidth -1;
				z = 1;
				break;
			case 3:
				x = roomWidth -1;
				z = roomWidth -1;
				break;
			}

			int r = rand.nextInt(8);

			if (r/4 == 1) {
				world.setBlock(roomX +x, roomY, roomZ +z, Blocks.deadbush);
			} else {
				for (int y = 0; y < r; y++) {
					world.setBlock(roomX +x, roomY +y, roomZ +z, Blocks.cactus);
				}
			}
		}

		/* 光源 */
		// 明るさ確保のための「松明」の設置
		world.setBlock(roomX +roomCenter -2, roomY +1, roomZ +roomCenter -2, Blocks.torch, 5, 3);
		world.setBlock(roomX +roomCenter -2, roomY +1, roomZ +roomCenter +2, Blocks.torch, 5, 3);
		world.setBlock(roomX +roomCenter +2, roomY +1, roomZ +roomCenter -2, Blocks.torch, 5, 3);
		world.setBlock(roomX +roomCenter +2, roomY +1, roomZ +roomCenter +2, Blocks.torch, 5, 3);


		/* 地下室 */
		if (roomType == 3) {
			// 井戸の底の中央のブロックをなくす
			world.setBlockToAir(roomX +roomCenter, roomY -2, roomZ +roomCenter);
			world.setBlockToAir(roomX +roomCenter, roomY -1, roomZ +roomCenter);

			// 「砂岩ブロック」で埋め尽くす
			for (int x = 0; x <= roomWidth; x++) {
				for (int z = 0; z <= roomWidth; z++) {
					for (int y = roomDepth; y <= -3; y++) {
						world.setBlock(roomX +x, roomY +y, roomZ +z, Blocks.sandstone);
					}
				}
			}

			// 「空気」の設置
			for (int x = 1; x <= roomWidth -1; x++) {
				for (int z = 1; z <= roomWidth -1; z++) {
					for (int y = roomDepth +2; y <= -3; y++) {
						world.setBlockToAir(roomX +x, roomY +y, roomZ +z);
					}
				}
			}

			// 底の「水」の設置
			for (int x = 1; x <= roomWidth -1; x++) {
				for (int z = 1; z <= roomWidth -1; z++) {
					world.setBlock(roomX +x, roomY +roomDepth +1, roomZ +z, Blocks.water);
				}
			}

			// 足場となる「砂ブロック」の設置
			for (int x = roomCenter -1; x <= roomCenter +1; x++) {
				for (int z = roomCenter -1; z <= roomCenter +1; z++) {
					world.setBlock(roomX +x, roomY +roomDepth +1, roomZ +z, Blocks.sand);
				}
			}

			// 明るさ確保のための「松明」の設置
			world.setBlock(roomX +roomCenter +1, roomY +roomDepth +2, roomZ +roomWidth -1, Blocks.torch, 4, 3);
			world.setBlock(roomX +roomCenter -1, roomY +roomDepth +2, roomZ +roomWidth -1, Blocks.torch, 4, 3);
			/*
			world.setBlock(roomX +roomCenter, roomY +roomDepth +2, roomZ +1, Blocks.torch, 3, 3);
			world.setBlock(roomX +roomWidth -1, roomY +roomDepth +2, roomZ +roomCenter, Blocks.torch, 2, 3);
			world.setBlock(roomX +1, roomY +roomDepth +2, roomZ +roomCenter, Blocks.torch, 1, 3);
			*/

			// 装飾
			world.setBlock(roomX +roomCenter, roomY +roomDepth +1, roomZ +roomWidth -1, Blocks.sand);
			world.setBlock(roomX +roomCenter, roomY +roomDepth +2, roomZ +roomWidth -1, DQDecorates.DqmBlockSwordMob);

		}


		/* - - - - - - - - - -
		 * 以下、敵のスポーン
		 * - - - - - - - - - */

		// 屋根上スタート時・「旱魃」井戸中スタート時
		if (roomType <= 2) {
			SpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +1, roomZ +1, RoomID.roomBeach + RoomID.getDifOfRoom());
			SpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +1, roomZ +roomWidth -1, RoomID.roomBeach + RoomID.getDifOfRoom());
			SpawnEnemyCore.spawnEnemy(world, player, roomX +1, roomY +1, roomZ +roomCenter, RoomID.roomBeach + RoomID.getDifOfRoom());
			SpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -1, roomY +1, roomZ +roomCenter, RoomID.roomBeach + RoomID.getDifOfRoom());

		// 通常時・「旱魃」地下室
		} else {
			// 確定スポーン
			switch (direction) {
			case 0:
				SpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -1, roomY, roomZ +roomCenter, RoomID.roomForest + RoomID.getDifOfRoom());
				break;
			case 1:
				SpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY, roomZ +roomWidth -1, RoomID.roomForest + RoomID.getDifOfRoom());
				break;
			case 2:
				SpawnEnemyCore.spawnEnemy(world, player, roomX +1, roomY, roomZ +roomCenter, RoomID.roomForest + RoomID.getDifOfRoom());
				break;
			case 3:
				SpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY, roomZ +1, RoomID.roomForest + RoomID.getDifOfRoom());
				break;
			}

			// 確率スポーン
			if (RoomID.getDifOfRoom() >= rand.nextInt(4)) {
				switch (direction) {
				case 0:
				case 2:
					SpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +1, roomZ +1, RoomID.roomBeach + RoomID.getDifOfRoom());
					SpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +1, roomZ +roomWidth -1, RoomID.roomBeach + RoomID.getDifOfRoom());
					break;
				case 1:
				case 3:
					SpawnEnemyCore.spawnEnemy(world, player, roomX +1, roomY +1, roomZ +roomCenter, RoomID.roomBeach + RoomID.getDifOfRoom());
					SpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -1, roomY +1, roomZ +roomCenter, RoomID.roomBeach + RoomID.getDifOfRoom());
					break;
				}
			}
		}

	}
}
/* 設計図
o,0,1,2,3,4,5,4,3,2,1,0,x
0,_,_,_,_,_,_,_,_,_,_,_,
1,_,_,_,_,_,1,_,_,_,_,_,
2,_,_,_,_,_,_,_,_,_,_,_,
3,_,_,_,b,b,h,b,b,_,_,_,
4,_,_,_,b,b,w,b,b,_,_,_,
5,_,0,_,h,w,w,w,h,_,2,_,
4,_,_,_,b,b,w,b,b,_,_,_,
3,_,_,_,b,b,h,b,b,_,_,_,
2,_,_,_,_,_,_,_,_,_,_,_,
1,_,_,_,_,_,3,_,_,_,_,_,
0,_,_,_,_,_,_,_,_,_,_,_,
z,
*/