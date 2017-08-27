package lawisAddonDqr1.event.rooms.room1;

import java.util.Random;

import dqr.api.Blocks.DQDecorates;
import lawisAddonDqr1.achievement.LadAchievementCore;
import lawisAddonDqr1.config.LadConfigCore;
import lawisAddonDqr1.config.LadDebug;
import lawisAddonDqr1.event.entities.LadSpawnEnemyCore;
import lawisAddonDqr1.event.rooms.LadRoomID;
import lawisAddonDqr1.event.rooms.decoration.LadDecorationCross;
import lawisAddonDqr1.event.rooms.decoration.LadDecorationFloor;
import lawisAddonDqr1.event.rooms.decoration.LadDecorationPillar;
import lawisAddonDqr1.event.rooms.decoration.LadDecorationReward;
import lawisAddonDqr1.event.rooms.decoration.LadDecorationTorch;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class LadRoomDesertWell {
	/*
	 * バニラの砂漠の井戸をモチーフとした戦闘部屋（村の井戸ではない）
	 */
	public static void setRoom(World world, EntityPlayer player) {
		Random rand = new Random();
		int roomDirection = LadRoomID.getDirectionRoom(player, 0);

		int roomX = (int)player.posX;			// 部屋の起点となるX座標
		int roomZ = (int)player.posZ;			// 部屋の起点となるZ座標
		int roomY = (int)player.posY -1;		// 部屋の起点となるY座標（-1）

		int roomHeight = 6;					// 部屋の高さ
		int roomWidth = 10;					// 部屋の幅（-1）
		int roomCenter = roomWidth /2;		// 部屋の中心
		int roomType = rand.nextInt(8);		// 部屋の種類
		int roomDepth = -7;					// 地下室の深さ

		// [Debug] 戦闘部屋固定時に生成方向がチャット表示される（デバッグ用）
		if (LadDebug.getDebugRoom() >=0) {
			player.addChatMessage(new ChatComponentTranslation("roomDirection == " + roomDirection));
			player.addChatMessage(new ChatComponentTranslation("roomType == " + roomType));
			player.addChatMessage(new ChatComponentTranslation("difOfRoom == " + LadRoomID.getDifOfRoom()));
		}

		// 実績の取得
		player.triggerAchievement(LadAchievementCore.roomDesertWell);

		// マイナス座標の時に、部屋の位置がズレることの修正
		if (player.posX < 0) roomX -=1;
		if (player.posZ < 0) roomZ -=1;

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

		// 井戸を生成する
		setDesertWell(world, roomX +roomCenter, roomZ +roomCenter, roomY);

		// 干ばつパターン
		if ((roomType == 2) || (roomType == 3)) {
			// 井戸の中に「砂」を敷く
			LadDecorationFloor.setBlockAndAirCross(world, Blocks.sand, roomX +roomCenter, roomY -1, roomZ +roomCenter);
		}

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
		LadDecorationTorch.setTorchCenterSlanting(world, roomX +roomCenter, roomZ +roomCenter, roomY +1, 2);

		/* 地下室 */
		if (roomType == 3) {
			// 井戸の底の中央のブロックをなくす
			LadDecorationPillar.setPillar(world, roomX +roomCenter, roomY -2, roomZ +roomCenter, 2);

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

			// 装飾
			world.setBlock(roomX +roomCenter, roomY +roomDepth +1, roomZ +roomWidth -1, Blocks.sand);
			world.setBlock(roomX +roomCenter, roomY +roomDepth +2, roomZ +roomWidth -1, DQDecorates.DqmBlockSwordMob);

		}


		/* - - - - - - - - - -
		 * 以下、敵のスポーン
		 * - - - - - - - - - */

		// 屋根上スタート時・「旱魃」井戸中スタート時
		if (roomType <= 2) {
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +1, roomZ +1, LadRoomID.DESERT_WELL + LadRoomID.getDifOfRoom());
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +1, roomZ +roomWidth -1, LadRoomID.DESERT_WELL + LadRoomID.getDifOfRoom());
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +1, roomY +1, roomZ +roomCenter, LadRoomID.DESERT_WELL + LadRoomID.getDifOfRoom());
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -1, roomY +1, roomZ +roomCenter, LadRoomID.DESERT_WELL + LadRoomID.getDifOfRoom());

		// 通常時・「旱魃」地下室
		} else {
			// 確定スポーン
			switch (roomDirection) {
			case 0:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -1, roomY +1, roomZ +roomCenter, LadRoomID.DESERT_WELL + LadRoomID.getDifOfRoom());
				break;
			case 1:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +1, roomZ +roomWidth -1, LadRoomID.DESERT_WELL + LadRoomID.getDifOfRoom());
				break;
			case 2:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +1, roomY +1, roomZ +roomCenter, LadRoomID.DESERT_WELL + LadRoomID.getDifOfRoom());
				break;
			case 3:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +1, roomZ +1, LadRoomID.DESERT_WELL + LadRoomID.getDifOfRoom());
				break;
			}

			// 確率スポーン
			if (LadRoomID.getDifOfRoom() >= rand.nextInt(4)) {
				switch (roomDirection) {
				case 0:
				case 2:
					LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +1, roomZ +1, LadRoomID.DESERT_WELL + LadRoomID.getDifOfRoom());
					LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +1, roomZ +roomWidth -1, LadRoomID.DESERT_WELL + LadRoomID.getDifOfRoom());
					break;
				case 1:
				case 3:
					LadSpawnEnemyCore.spawnEnemy(world, player, roomX +1, roomY +1, roomZ +roomCenter, LadRoomID.DESERT_WELL + LadRoomID.getDifOfRoom());
					LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -1, roomY +1, roomZ +roomCenter, LadRoomID.DESERT_WELL + LadRoomID.getDifOfRoom());
					break;
				}
			}
		}

		/* - - - - - - - - - -
		 * 以下、報酬
		 * - - - - - - - - - */

		switch (roomDirection) {
		case 0:
			LadDecorationReward.setChest(world, roomX +roomWidth +1, roomY, roomZ +roomCenter);
			break;
		case 1:
			LadDecorationReward.setChest(world, roomX +roomCenter, roomY, roomZ +roomWidth +1);
			break;
		case 2:
			LadDecorationReward.setChest(world, roomX -1, roomY, roomZ +roomCenter);
			break;
		case 3:
			LadDecorationReward.setChest(world, roomX +roomCenter, roomY, roomZ -1);
			break;
		}
	}

	/*
	 * 砂漠の井戸を生成するメソッド
	 */
	public static void setDesertWell(World world, int centerX, int centerZ, int floorY){
		// 井戸の底に「砂岩ブロック」を敷く
		for (int x = centerX -2; x <= centerX +2; x++) {
			for (int z = centerZ -2; z <= centerZ +2; z++) {
				for (int y = -1; y <= 0; y++) {
					world.setBlock(x, floorY +y, z, Blocks.sandstone);
				}
			}
		}

		// 井戸の中に「水」を敷く
		LadDecorationFloor.setBlockAndAirCross(world, Blocks.water, centerX, floorY -1, centerZ);

		// 井戸の周りに「砂岩ハーフブロック」を敷く
		LadDecorationCross.setFourBlockCross(world, Blocks.stone_slab, 1, centerX, centerZ, floorY, 2);

		// 柱となる「砂岩」を設置
		LadDecorationPillar.setPillarSlanting(world, Blocks.sandstone, centerX, centerZ, floorY +1, 2, 1);

		// 屋根となる「砂岩ハーフブロック」を設置
		for (int x = centerX -1; x <= centerX +1; x++) {
			for (int z = centerZ -1; z <= centerZ +1; z++) {
				world.setBlock(x, floorY +3, z, Blocks.stone_slab, 1, 2);
			}
		}

		// 頂点の「砂岩」を設置
		world.setBlock(centerX, floorY +3, centerZ, Blocks.sandstone);
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