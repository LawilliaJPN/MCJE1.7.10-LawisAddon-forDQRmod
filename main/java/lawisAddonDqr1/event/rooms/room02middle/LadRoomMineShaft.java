package lawisAddonDqr1.event.rooms.room02middle;

import java.util.Random;

import lawisAddonDqr1.achievement.LadAchievementCore;
import lawisAddonDqr1.config.LadDebug;
import lawisAddonDqr1.event.entities.LadMeasuresAgainstPlayerSuffocation;
import lawisAddonDqr1.event.entities.LadSpawnEnemyCore;
import lawisAddonDqr1.event.rooms.LadRoomID;
import lawisAddonDqr1.event.rooms.decoration.LadDecorationCrops;
import lawisAddonDqr1.event.rooms.decoration.LadDecorationDeteriorated;
import lawisAddonDqr1.event.rooms.decoration.LadDecorationPillar;
import lawisAddonDqr1.event.rooms.decoration.LadDecorationReward;
import lawisAddonDqr1.event.rooms.decoration.LadFillBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class LadRoomMineShaft {
	/*
	 * バニラの「廃坑」をモチーフにした戦闘部屋
	 *
	 * TODO パーツをいくつか作り、それらの配置でパターンを作成する
	 */
	public static void setRoom(World world, EntityPlayer player) {
		Random rand = new Random();
		int roomDirection = LadRoomID.getDirectionRoom(player, 0);

		int roomX = (int)player.posX;			// 部屋の起点となるX座標
		int roomZ = (int)player.posZ;			// 部屋の起点となるZ座標
		int roomY = (int)player.posY -1;		// 部屋の起点となるY座標（-1）

		int roomHeight = 6;					// 部屋の高さ
		int roomWidth = 12;					// 部屋の幅（-1）
		int roomCenter = roomWidth /2;		// 部屋の中心

		int roomFloor1Y = -1;					// 1階の床の高さ
		int roomFloor2Y = 3;					// 2階の床の高さ
		int roomType = rand.nextInt(8);		// 部屋の種類

		// [Debug] 戦闘部屋固定時に生成方向がチャット表示される（デバッグ用）
		if (LadDebug.getDebugRoom() >=0) {
			player.addChatMessage(new ChatComponentTranslation("roomDirection == " + roomDirection));
			player.addChatMessage(new ChatComponentTranslation("roomType == " + roomType));
			player.addChatMessage(new ChatComponentTranslation("difOfRoom == " + LadRoomID.getDifOfRoom()));
		}

		// 実績の取得
		player.triggerAchievement(LadAchievementCore.roomMineShaft);

		// マイナス座標の時に、部屋の位置がズレることの修正
		if (player.posX < 0) roomX -=1;
		if (player.posZ < 0) roomZ -=1;

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

		// 落下物対策
		LadMeasuresAgainstPlayerSuffocation.measuresAgainstFallingObject(world, roomX, roomZ, roomWidth, roomWidth, roomY +roomHeight +1);

		/* 空間 */
		// 「オークの木材」の設置
		LadFillBlock.fillBlock(world, Blocks.planks, roomX, roomX +roomWidth, roomZ, roomZ +roomWidth, roomY +roomFloor1Y, roomY +roomHeight);

		// 「空気」を設置
		for (int x = 4; x <= roomWidth -4; x++) {
			for (int z = 1; z <= 3; z++) {
				for (int y = 0; y <= roomHeight; y++) {
					if ((roomType%2 == 0) && (y < roomFloor2Y)) {
						LadDecorationDeteriorated.setBlockToAirOrWeb(world, rand, roomX +x, roomY +y, roomZ +z, roomType);
						LadDecorationDeteriorated.setBlockToAirOrWeb(world, rand, roomX +x, roomY +y, roomZ +roomWidth -z, roomType);
					} else if (y != roomFloor2Y) {
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
					if ((roomType%2 == 0) && (y < roomFloor2Y)) {
						LadDecorationDeteriorated.setBlockToAirOrWeb(world, rand, roomX +x, roomY +y, roomZ +z, roomType);
						LadDecorationDeteriorated.setBlockToAirOrWeb(world, rand, roomX +roomWidth -x, roomY +y, roomZ +z, roomType);
					} else if (y != roomFloor2Y) {
						world.setBlockToAir(roomX +x, roomY +y, roomZ +z);
						world.setBlockToAir(roomX +roomWidth -x, roomY +y, roomZ +z);
					}
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

		// 「空気」を設置
		LadDecorationPillar.setFourPillarCrossToAir(world, roomX +2, roomZ +2, roomY, roomHeight +1, 1);
		LadDecorationPillar.setFourPillarCrossToAir(world, roomX +roomWidth -2, roomZ +2, roomY, roomHeight +1, 1);
		LadDecorationPillar.setFourPillarCrossToAir(world, roomX +2, roomZ +roomWidth -2, roomY, roomHeight +1, 1);
		LadDecorationPillar.setFourPillarCrossToAir(world, roomX +roomWidth -2, roomZ +roomWidth -2, roomY, roomHeight +1, 1);

		LadDecorationPillar.setPillarToAir(world, roomX +2, roomZ, roomY, roomHeight +1);
		LadDecorationPillar.setPillarToAir(world, roomX, roomZ +2, roomY, roomHeight +1);
		LadDecorationPillar.setPillarToAir(world, roomX +2, roomZ +2, roomY, roomHeight +1);

		LadDecorationPillar.setPillarToAir(world, roomX +roomWidth -2, roomZ, roomY, roomHeight +1);
		LadDecorationPillar.setPillarToAir(world, roomX +roomWidth, roomZ +2, roomY, roomHeight +1);
		LadDecorationPillar.setPillarToAir(world, roomX +roomWidth -2, roomZ +2, roomY, roomHeight +1);

		LadDecorationPillar.setPillarToAir(world, roomX +2, roomZ +roomWidth, roomY, roomHeight +1);
		LadDecorationPillar.setPillarToAir(world, roomX, roomZ +roomWidth -2, roomY, roomHeight +1);
		LadDecorationPillar.setPillarToAir(world, roomX +2, roomZ +roomWidth -2, roomY, roomHeight +1);

		LadDecorationPillar.setPillarToAir(world, roomX +roomWidth -2, roomZ +roomWidth, roomY, roomHeight +1);
		LadDecorationPillar.setPillarToAir(world, roomX +roomWidth, roomZ +roomWidth -2, roomY, roomHeight +1);
		LadDecorationPillar.setPillarToAir(world, roomX +roomWidth -2, roomZ +roomWidth -2, roomY, roomHeight +1);

		LadFillBlock.fillBlockToAir(world, roomX +roomCenter -1, roomX +roomCenter +1, roomZ +roomCenter -1, roomZ +roomCenter +1, roomY, roomY +roomHeight);


		/* 装飾 */
		// 「土ブロック」の設置
		LadFillBlock.fillBlockXZ(world, Blocks.dirt, roomX +roomCenter -2, roomX +roomCenter +2, roomZ +roomCenter -2, roomZ +roomCenter +2, roomY +roomFloor1Y);

		// 「キノコ」の設置
		for (int x = roomCenter -1; x <= roomCenter +1; x++) {
			for (int z = roomCenter -1; z <= roomCenter +1; z++) {
				LadDecorationCrops.setMushroom(world, rand, roomX +x, roomY +roomFloor1Y +1, roomZ +z);
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

		/* - - - - - - - - - -
		 * 以下、敵のスポーン
		 * - - - - - - - - - */

		// 「洞窟グモ大量発生」時のスポーン
		if (roomType == 0) {
			// 中央の土ブロック上
			for (int x = roomCenter -1; x <= roomCenter +1; x++) {
				for (int z = roomCenter -1; z <= roomCenter +1; z++) {
					LadSpawnEnemyCore.spawnEnemy(world, player, roomX +x, roomY +roomFloor1Y +2, roomZ +z, LadRoomID.MINE_SHAFT);
				}
			}
			// 四隅(Y=25以下)
			if (LadRoomID.getDifOfRoom() >= 5) {
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +2, roomY +roomFloor1Y +2, roomZ +2, LadRoomID.MINE_SHAFT);
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +2, roomY +roomFloor1Y +2, roomZ +roomWidth -2, LadRoomID.MINE_SHAFT);
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -2, roomY +roomFloor1Y +2, roomZ +2, LadRoomID.MINE_SHAFT);
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -2, roomY +roomFloor1Y +2, roomZ +roomWidth -2, LadRoomID.MINE_SHAFT);
			}
			// 実績の取得
			player.triggerAchievement(LadAchievementCore.eventSpider);

		} else {
			// 1階中央(共通50%)
			if (rand.nextInt(2) == 0) {
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +roomFloor1Y +2, roomZ +roomCenter, LadRoomID.MINE_SHAFT +LadRoomID.getDifOfRoom());
			}

			if (roomType/2 == 1) {
				// 四隅
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +2, roomY +roomFloor1Y +2, roomZ +2, LadRoomID.MINE_SHAFT +LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +2, roomY +roomFloor1Y +2, roomZ +roomWidth -2, LadRoomID.MINE_SHAFT +LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -2, roomY +roomFloor1Y +2, roomZ +2, LadRoomID.MINE_SHAFT +LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -2, roomY +roomFloor1Y +2, roomZ +roomWidth -2, LadRoomID.MINE_SHAFT +LadRoomID.getDifOfRoom());
			} else if(roomType/2 == 2) {
				// 2階プレイヤーのいない方向
				switch (roomDirection) {
				case 0:
					LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -3, roomY +roomFloor2Y +2, roomZ +roomCenter, LadRoomID.MINE_SHAFT + LadRoomID.getDifOfRoom());
					LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +roomFloor2Y +2, roomZ +3, LadRoomID.MINE_SHAFT + LadRoomID.getDifOfRoom());
					LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +roomFloor2Y +2, roomZ +roomWidth -3, LadRoomID.MINE_SHAFT + LadRoomID.getDifOfRoom());
					break;
				case 1:
					LadSpawnEnemyCore.spawnEnemy(world, player, roomX +3, roomY +roomFloor2Y +2, roomZ +roomCenter, LadRoomID.MINE_SHAFT + LadRoomID.getDifOfRoom());
					LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -3, roomY +roomFloor2Y +2, roomZ +roomCenter, LadRoomID.MINE_SHAFT + LadRoomID.getDifOfRoom());
					LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +roomFloor2Y +2, roomZ +roomWidth -3, LadRoomID.MINE_SHAFT + LadRoomID.getDifOfRoom());
					break;
				case 2:
					LadSpawnEnemyCore.spawnEnemy(world, player, roomX +3, roomY +roomFloor2Y +2, roomZ +roomCenter, LadRoomID.MINE_SHAFT + LadRoomID.getDifOfRoom());
					LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +roomFloor2Y +2, roomZ +3, LadRoomID.MINE_SHAFT + LadRoomID.getDifOfRoom());
					LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +roomFloor2Y +2, roomZ +roomWidth -3, LadRoomID.MINE_SHAFT + LadRoomID.getDifOfRoom());
					break;
				case 3:
					LadSpawnEnemyCore.spawnEnemy(world, player, roomX +3, roomY +roomFloor2Y +2, roomZ +roomCenter, LadRoomID.MINE_SHAFT + LadRoomID.getDifOfRoom());
					LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -3, roomY +roomFloor2Y +2, roomZ +roomCenter, LadRoomID.MINE_SHAFT + LadRoomID.getDifOfRoom());
					LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +roomFloor2Y +2, roomZ +3, LadRoomID.MINE_SHAFT + LadRoomID.getDifOfRoom());
					break;
				}
			} else {
				//プレイヤーのいない方向（1階2体＋2階1体）
				switch (roomDirection) {
				case 0:
					LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -3, roomY +roomFloor2Y +2, roomZ +roomCenter, LadRoomID.MINE_SHAFT + LadRoomID.getDifOfRoom());
					LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -2, roomY +roomFloor1Y +2, roomZ +2, LadRoomID.MINE_SHAFT +LadRoomID.getDifOfRoom());
					LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -2, roomY +roomFloor1Y +2, roomZ +roomWidth -2, LadRoomID.MINE_SHAFT +LadRoomID.getDifOfRoom());
					break;
				case 1:
					LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +roomFloor2Y +2, roomZ +roomWidth -3, LadRoomID.MINE_SHAFT + LadRoomID.getDifOfRoom());
					LadSpawnEnemyCore.spawnEnemy(world, player, roomX +2, roomY +roomFloor1Y +2, roomZ +roomWidth -2, LadRoomID.MINE_SHAFT +LadRoomID.getDifOfRoom());
					LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -2, roomY +roomFloor1Y +2, roomZ +roomWidth -2, LadRoomID.MINE_SHAFT +LadRoomID.getDifOfRoom());
					break;
				case 2:
					LadSpawnEnemyCore.spawnEnemy(world, player, roomX +3, roomY +roomFloor2Y +2, roomZ +roomCenter, LadRoomID.MINE_SHAFT + LadRoomID.getDifOfRoom());
					LadSpawnEnemyCore.spawnEnemy(world, player, roomX +2, roomY +roomFloor1Y +2, roomZ +2, LadRoomID.MINE_SHAFT +LadRoomID.getDifOfRoom());
					LadSpawnEnemyCore.spawnEnemy(world, player, roomX +2, roomY +roomFloor1Y +2, roomZ +roomWidth -2, LadRoomID.MINE_SHAFT +LadRoomID.getDifOfRoom());
					break;
				case 3:
					LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +roomFloor2Y +2, roomZ +3, LadRoomID.MINE_SHAFT + LadRoomID.getDifOfRoom());
					LadSpawnEnemyCore.spawnEnemy(world, player, roomX +2, roomY +roomFloor1Y +2, roomZ +2, LadRoomID.MINE_SHAFT +LadRoomID.getDifOfRoom());
					LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -2, roomY +roomFloor1Y +2, roomZ +2, LadRoomID.MINE_SHAFT +LadRoomID.getDifOfRoom());
					break;
				}
			}

			/* - - - - - - - - - -
			 * 以下、報酬
			 * - - - - - - - - - */

			switch (roomDirection) {
			case 0:
				LadDecorationReward.setChest(world, roomX +roomWidth, roomZ +roomCenter, roomY);
				break;
			case 1:
				LadDecorationReward.setChest(world, roomX +roomCenter, roomZ +roomWidth, roomY);
				break;
			case 2:
				LadDecorationReward.setChest(world, roomX, roomZ +roomCenter, roomY);
				break;
			case 3:
				LadDecorationReward.setChest(world, roomX +roomCenter, roomZ, roomY);
				break;
			}
		}
	}
}