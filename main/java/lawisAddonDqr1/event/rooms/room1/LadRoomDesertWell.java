package lawisAddonDqr1.event.rooms.room1;

import java.util.Random;

import biomesoplenty.api.content.BOPCBlocks;
import dqr.api.Blocks.DQDecorates;
import lawisAddonDqr1.LawisAddonDQR01;
import lawisAddonDqr1.achievement.LadAchievementCore;
import lawisAddonDqr1.addon.LadAddons;
import lawisAddonDqr1.config.LadConfigCore;
import lawisAddonDqr1.config.LadDebug;
import lawisAddonDqr1.event.entities.LadMeasuresAgainstPlayerSuffocation;
import lawisAddonDqr1.event.entities.LadSpawnEnemyCore;
import lawisAddonDqr1.event.rooms.LadRoomID;
import lawisAddonDqr1.event.rooms.decoration.LadDecorationCross;
import lawisAddonDqr1.event.rooms.decoration.LadDecorationFloor;
import lawisAddonDqr1.event.rooms.decoration.LadDecorationPillar;
import lawisAddonDqr1.event.rooms.decoration.LadDecorationReward;
import lawisAddonDqr1.event.rooms.decoration.LadDecorationTorch;
import lawisAddonDqr1.event.rooms.decoration.LadFillBlock;
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
		int roomHeightB1 = -3;				// 地下室の高さ


		/* 部屋の生成の前に */
		// マイナス座標の時に、部屋の位置がズレることの修正
		if (player.posX < 0) roomX -=1;
		if (player.posZ < 0) roomZ -=1;

		// コンフィグ：負荷軽減オンの時
		if (LadConfigCore.isRoomReduction) {
			// 「旱魃」地下室→「旱魃」中央スタート
			if (roomType == 3) roomType = 2;
			// 「井戸2つパターン」→通常
			if (roomType == 4) roomType = 7;
		}
		roomType = 7; //TODO debug


		// 屋根上スタート
		if (roomType/2 == 0) {
			roomX -= roomCenter;
			roomZ -= roomCenter;
			roomY -= 3;

		// 「旱魃」中央スタート
		} else if (roomType == 2) {
			roomX -= roomCenter;
			roomZ -= roomCenter;

		// 「井戸2つパターン」
		} else if (roomType == 4) {
			roomDirection = LadRoomID.getDirectionRoom(player, 1);
			roomWidth = 14;
			roomCenter = roomWidth /2;

			// プレイヤーの向きから部屋の起点となる座標を決める
			switch (roomDirection) {
			case 0:
				roomX -= 1;
				roomZ -= roomWidth -1;
				break;
			case 1:
				roomX -= 1;
				roomZ -= 1;
				break;
			case 2:
				roomX -= roomWidth -1;
				roomZ -= 1;
				break;
			case 3:
				roomX -= roomWidth -1;
				roomZ -= roomWidth -1;
				break;
			}

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

		// [Debug] 戦闘部屋固定時に生成方向がチャット表示される（デバッグ用）
		if (LadDebug.getDebugRoom() >=0) {
			player.addChatMessage(new ChatComponentTranslation("roomDirection == " + roomDirection));
			player.addChatMessage(new ChatComponentTranslation("roomType == " + roomType));
			player.addChatMessage(new ChatComponentTranslation("difOfRoom == " + LadRoomID.getDifOfRoom()));
		}

		// 実績の取得
		player.triggerAchievement(LadAchievementCore.roomDesertWell);

		/* - - - - - - - - - -
		 * 以下、部屋の生成
		 * - - - - - - - - - */

		// 落下物対策
		LadMeasuresAgainstPlayerSuffocation.measuresAgainstFallingObject(world, roomX, roomZ, roomWidth, roomWidth, roomY +roomHeight +1);

		/* 地面 */
		// 地面の下に「砂岩ブロック」を敷く（地面となる「砂ブロック」の落下防止のため）
		LadFillBlock.fillBlockXZ(world, Blocks.sandstone, roomX, roomZ, roomWidth, roomY -2);

		// 地面に「砂ブロック」を敷く
		LadFillBlock.fillBlockXZ(world, Blocks.sand, roomX, roomZ, roomWidth, roomY -1);

		/* 空間 */
		// 「空気」の設置
		LadFillBlock.fillBlockToAir(world, roomX, roomX +roomWidth, roomZ, roomZ +roomWidth, roomY, roomY +roomHeight);

		// 井戸2つパターン
		if (roomType == 4) {
			int type = rand.nextInt(12);

			// 水路の設置
			LadDecorationPillar.setBlockEnclosure(world, Blocks.sandstone, roomX +2, roomX +roomWidth-2, roomZ +2, roomZ +roomWidth -2, roomY -1);
			LadDecorationPillar.setBlockEnclosure(world, Blocks.water, roomX +3, roomX +roomWidth-3, roomZ +3, roomZ +roomWidth -3, roomY -1);
			LadDecorationPillar.setBlockEnclosure(world, Blocks.sandstone, roomX +4, roomX +roomWidth-4, roomZ +4, roomZ +roomWidth -4, roomY -1);

			switch (roomDirection) {
			case 0:
			case 2:
				// 井戸を2つ設置
				setDesertWell(world, roomX +3, roomZ +3, roomY);
				setDesertWell(world, roomX +roomWidth -3, roomZ +roomWidth -3, roomY);

				if (type/2 == 0) {
					// 明るさ確保のために「村の街灯」を設置
					LadDecorationTorch.setVillageLight(world, roomX +roomCenter +2, roomZ +roomCenter -2, roomY);
					LadDecorationTorch.setVillageLight(world, roomX +roomCenter -2, roomZ +roomCenter +2, roomY);
				}
				break;
			case 1:
			case 3:
				// 井戸を2つ設置
				setDesertWell(world, roomX +3, roomZ +roomWidth -3, roomY);
				setDesertWell(world, roomX +roomWidth -3, roomZ +3, roomY);

				if (type/2 == 0) {
					// 明るさ確保のために「村の街灯」を設置
					LadDecorationTorch.setVillageLight(world, roomX +roomCenter +2, roomZ +roomCenter +2, roomY);
					LadDecorationTorch.setVillageLight(world, roomX +roomCenter -2, roomZ +roomCenter -2, roomY);
				}
				break;
			}

			if (type/2 != 0) {
				// 明るさ確保のために「村の街灯」を設置
				LadDecorationTorch.setVillageLight(world, roomX +roomCenter, roomZ +roomCenter, roomY);
			}

			/* 装飾 */
			if (type%3 == 0) {
				// 枯れ木・サボテン
				setCactus(world, rand, roomX +roomCenter, roomZ +roomCenter +2, roomY);
				setCactus(world, rand, roomX +roomCenter, roomZ +roomCenter -2, roomY);
				setCactus(world, rand, roomX +roomCenter +2, roomZ +roomCenter, roomY);
				setCactus(world, rand, roomX +roomCenter -2, roomZ +roomCenter, roomY);

				if (type%6 == 0) {
					// 枯れ木・サボテン
					setCactus(world, rand, roomX +roomCenter, roomZ +roomCenter +6, roomY);
					setCactus(world, rand, roomX +roomCenter, roomZ +roomCenter -6, roomY);
					setCactus(world, rand, roomX +roomCenter +6, roomZ +roomCenter, roomY);
					setCactus(world, rand, roomX +roomCenter -6, roomZ +roomCenter, roomY);
				}
			} else if (type%3 == 1) {
				// 砂岩の柱
				setSandstonePillar(world, rand, roomX +roomCenter, roomZ +roomCenter +2, roomY);
				setSandstonePillar(world, rand, roomX +roomCenter, roomZ +roomCenter -2, roomY);
				setSandstonePillar(world, rand, roomX +roomCenter +2, roomZ +roomCenter, roomY);
				setSandstonePillar(world, rand, roomX +roomCenter -2, roomZ +roomCenter, roomY);

				if (type%6 == 1) {
					// 砂岩の柱
					setSandstonePillar(world, rand, roomX +roomCenter, roomZ +roomCenter +6, roomY);
					setSandstonePillar(world, rand, roomX +roomCenter, roomZ +roomCenter -6, roomY);
					setSandstonePillar(world, rand, roomX +roomCenter +6, roomZ +roomCenter, roomY);
					setSandstonePillar(world, rand, roomX +roomCenter -6, roomZ +roomCenter, roomY);
				}
			}

		// 井戸2つパターン以外
		} else {
			// 井戸を生成する
			setDesertWell(world, roomX +roomCenter, roomZ +roomCenter, roomY);

			// 干ばつパターン
			if ((roomType == 2) || (roomType == 3)) {
				if ((rand.nextInt(8) == 0) && (LadAddons.isBopLoaded())) {
					try {
						// 井戸の中に「流砂」を敷く
						LadDecorationFloor.setBlockAndAirCross(world, BOPCBlocks.mud, roomX +roomCenter, roomY -1, roomZ +roomCenter);
					} catch (Throwable t) {
						LawisAddonDQR01.logger.warn("Failed to load BoP");
					}

				} else {
					// 井戸の中に「砂」を敷く
					LadDecorationFloor.setBlockAndAirCross(world, Blocks.sand, roomX +roomCenter, roomY -1, roomZ +roomCenter);
				}


			}

			/* 装飾 */
			// 流砂(BoPと併用している場合)
			if ((rand.nextInt(8) == 0) && (LadAddons.isBopLoaded())) {
				try {
					LadFillBlock.fillBlock(world, BOPCBlocks.mud, 1, roomX, roomX +2, roomZ, roomZ +2, roomY -2, roomY -1);
					LadFillBlock.fillBlock(world, BOPCBlocks.mud, 1, roomX, roomX +2, roomZ +roomWidth -2, roomZ +roomWidth, roomY -2, roomY -1);
					LadFillBlock.fillBlock(world, BOPCBlocks.mud, 1, roomX +roomWidth -2, roomX +roomWidth, roomZ, roomZ +2, roomY -2, roomY -1);
					LadFillBlock.fillBlock(world, BOPCBlocks.mud, 1, roomX +roomWidth -2, roomX +roomWidth, roomZ +roomWidth -2, roomZ +roomWidth, roomY -2, roomY -1);
				} catch (Throwable t) {
					LawisAddonDQR01.logger.warn("Failed to load BoP");
				}

			// 枯れ木・サボテン
			} else {
				setCactus(world, rand, roomX +1, roomZ +1, roomY);
				setCactus(world, rand, roomX +1, roomZ +roomWidth -1, roomY);
				setCactus(world, rand, roomX +roomWidth -1, roomZ +1, roomY);
				setCactus(world, rand, roomX +roomWidth -1, roomZ +roomWidth -1, roomY);
			}

			/* 光源 */
			// 明るさ確保のための「松明」の設置
			LadDecorationTorch.setFourTorchCenterSlanting(world, roomX +roomCenter, roomZ +roomCenter, roomY +1, 2);

			/* 地下室 */
			if (roomType == 3) {
				// 井戸の底の中央のブロックをなくす
				LadDecorationPillar.setPillarToAir(world, roomX +roomCenter, roomY -2, roomZ +roomCenter, 2);

				// 「砂岩ブロック」の地面を設置
				LadFillBlock.fillBlockXZ(world, Blocks.sandstone, roomX, roomZ, roomWidth, roomY +roomDepth);

				// 「砂岩ブロック」の壁を設置
				LadDecorationPillar.setWall(world, Blocks.sandstone, roomX, roomZ, roomWidth, roomY +roomDepth +1, roomHeightB1 -roomDepth);

				// 「空気」の設置
				LadFillBlock.fillBlockToAir(world, roomX +1, roomX +roomWidth -1, roomZ +1, roomZ +roomWidth -1, roomY +roomDepth +2, roomY +roomHeightB1);

				// 底の「水」の設置
				LadFillBlock.fillBlockXZ(world, Blocks.water, roomX +1, roomZ +1, roomWidth -2, roomY +roomDepth +1);

				// 足場となる「砂ブロック」の設置
				LadFillBlock.fillBlockXZ(world, Blocks.sand, roomX +roomCenter -1, roomZ +roomCenter -1, 2, roomY +roomDepth +1);

				// 明るさ確保のための「松明」の設置
				world.setBlock(roomX +roomCenter +1, roomY +roomDepth +2, roomZ +roomWidth -1, Blocks.torch, 4, 3);
				world.setBlock(roomX +roomCenter -1, roomY +roomDepth +2, roomZ +roomWidth -1, Blocks.torch, 4, 3);

				// 装飾
				world.setBlock(roomX +roomCenter, roomY +roomDepth +1, roomZ +roomWidth -1, Blocks.sand);
				world.setBlock(roomX +roomCenter, roomY +roomDepth +2, roomZ +roomWidth -1, DQDecorates.DqmBlockSwordMob);

			}
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

		// 井戸2つパターン
		} else if (roomType == 4) {
			// 確定スポーン
			switch (roomDirection) {
			case 0:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -1, roomY, roomZ +4, LadRoomID.DESERT_WELL + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -4, roomY, roomZ +1, LadRoomID.DESERT_WELL + LadRoomID.getDifOfRoom());
				break;
			case 1:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -1, roomY, roomZ +roomWidth -4, LadRoomID.DESERT_WELL + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -4, roomY, roomZ +roomWidth -1, LadRoomID.DESERT_WELL + LadRoomID.getDifOfRoom());
				break;
			case 2:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +1, roomY, roomZ +roomWidth -4, LadRoomID.DESERT_WELL + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +4, roomY, roomZ +roomWidth -1, LadRoomID.DESERT_WELL + LadRoomID.getDifOfRoom());
				break;
			case 3:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +1, roomY, roomZ +4, LadRoomID.DESERT_WELL + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +4, roomY, roomZ +1, LadRoomID.DESERT_WELL + LadRoomID.getDifOfRoom());
				break;
			}

			// 確率スポーン
			if (LadRoomID.getDifOfRoom() >= rand.nextInt(4)) {
				switch (roomDirection) {
				case 0:
				case 2:
					LadSpawnEnemyCore.spawnEnemy(world, player, roomX +3, roomY +5, roomZ +3, LadRoomID.DESERT_WELL + LadRoomID.getDifOfRoom());
					LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -3, roomY +5, roomZ +roomWidth -3, LadRoomID.DESERT_WELL + LadRoomID.getDifOfRoom());
					break;
				case 1:
				case 3:
					LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -3, roomY +5, roomZ +3, LadRoomID.DESERT_WELL + LadRoomID.getDifOfRoom());
					LadSpawnEnemyCore.spawnEnemy(world, player, roomX +3, roomY +5, roomZ +roomWidth -3, LadRoomID.DESERT_WELL + LadRoomID.getDifOfRoom());
					break;
				}
			}

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

		// 井戸2つパターン
		if (roomType == 4) {
			switch (roomDirection) {
			case 0:
				LadDecorationReward.setChest(world, roomX +roomWidth, roomY, roomZ);
				break;
			case 1:
				LadDecorationReward.setChest(world, roomX +roomWidth, roomY, roomZ +roomWidth);
				break;
			case 2:
				LadDecorationReward.setChest(world, roomX, roomY, roomZ +roomWidth);
				break;
			case 3:
				LadDecorationReward.setChest(world, roomX, roomY, roomZ);
				break;
			}

		// 井戸2つパターン以外
		} else {
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
		LadDecorationPillar.setFourPillarSlanting(world, Blocks.sandstone, centerX, centerZ, floorY +1, 2, 1);

		// 屋根となる「砂岩ハーフブロック」を設置
		for (int x = centerX -1; x <= centerX +1; x++) {
			for (int z = centerZ -1; z <= centerZ +1; z++) {
				world.setBlock(x, floorY +3, z, Blocks.stone_slab, 1, 2);
			}
		}

		// 頂点の「砂岩」を設置
		world.setBlock(centerX, floorY +3, centerZ, Blocks.sandstone);
	}

	/*
	 * サボテンや枯れ木を設置するメソッド
	 */
	public static void setCactus(World world, Random rand, int x, int z, int roomY) {
		int r = rand.nextInt(8);

		if (r/4 == 1) {
			world.setBlock(x, roomY, z, Blocks.deadbush);
		} else {
			for (int y = 0; y < r; y++) {
				world.setBlock(x, roomY +y, z, Blocks.cactus);
			}
		}
	}

	/*
	 * 砂岩の柱を設置するメソッド
	 */
	public static void setSandstonePillar(World world, Random rand, int x, int z, int roomY) {
		world.setBlock(x, roomY, z, Blocks.sandstone, 2, 2);
		world.setBlock(x, roomY +1, z, Blocks.sandstone, 2, 2);
		world.setBlock(x, roomY +2, z, Blocks.sandstone, 1, 2);
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