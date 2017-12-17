package lawisAddonDqr1.event.rooms.room03lower;

import java.util.Random;

import lawisAddonDqr1.achievement.LadAchievementCore;
import lawisAddonDqr1.config.LadDebug;
import lawisAddonDqr1.event.entities.LadMeasuresAgainstPlayerSuffocation;
import lawisAddonDqr1.event.entities.LadSpawnEnemyCore;
import lawisAddonDqr1.event.rooms.LadRoomID;
import lawisAddonDqr1.event.rooms.decoration.LadDecorationCross;
import lawisAddonDqr1.event.rooms.decoration.LadDecorationPillar;
import lawisAddonDqr1.event.rooms.decoration.LadDecorationReward;
import lawisAddonDqr1.event.rooms.decoration.LadFillBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class LadRoomBottomOfOverWorld {
	/*
	 * 地底の戦闘部屋
	 */
	public static void setRoom(World world, EntityPlayer player) {
		Random rand = new Random();
		int roomDirection = LadRoomID.getDirectionRoom(player, 0);

		int roomX = (int)player.posX;			// 部屋の起点となるX座標
		int roomZ = (int)player.posZ;			// 部屋の起点となるZ座標
		int roomY = (int)player.posY -1;		// 部屋の起点となるY座標（-1）

		int roomHeight = 8;					// 部屋の高さ
		int roomWidth = 14;					// 部屋の幅（-1）
		int roomCenter = roomWidth /2;		// 部屋の中心

		int roomFloor1Y = -1;					// 1階の床の高さ

		// 床のパターン
		int floorType[][];
		floorType = new int[5][5];
		for (int xFT = 0; xFT < 5; xFT++) {
			for (int zFT = 0; zFT < 5; zFT++) {
				floorType[xFT][zFT] = rand.nextInt(5);
				if (rand.nextInt(2) == 0) floorType[xFT][zFT] = -1;
			}
		}
		floorType[0][2] = floorType[4][2] = floorType[2][0] = floorType[2][4] = 1;


		/* 部屋の生成位置などの調整 */
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
		roomY -= 1;

		/* デバッグ用 */
		// [Debug] 戦闘部屋固定時に生成方向がチャット表示される（デバッグ用）
		if (LadDebug.getDebugRoom() >=0) {
			player.addChatMessage(new ChatComponentTranslation("roomDirection == " + roomDirection));
			player.addChatMessage(new ChatComponentTranslation("difOfRoom == " + LadRoomID.getDifOfRoom()));
		}

		// 実績の取得
		player.triggerAchievement(LadAchievementCore.roomBottom);

		/* - - - - - - - - - -
		 * 以下、部屋の生成
		 * - - - - - - - - - */

		/* 空間 */
		// 落下物対策
		LadMeasuresAgainstPlayerSuffocation.measuresAgainstFallingObject(world, roomX, roomZ, roomWidth, roomWidth, roomY +roomHeight);
		// 床下
		LadFillBlock.fillBlockXZ(world, Blocks.obsidian, roomX, roomZ, roomWidth, roomY -2);
		LadDecorationPillar.setWall(world, Blocks.obsidian, roomX -1, roomX +roomWidth +1, roomZ -1, roomZ +roomWidth +1, roomY -2, 2);
		// 空気
		LadFillBlock.fillBlockToAir(world, roomX, roomZ, roomWidth, roomY, roomHeight -1);
		// 床
		for (int xFT = 0; xFT < 5; xFT++) {
			for (int zFT = 0; zFT < 5; zFT++) {
				setFloorObsidian(world, rand, roomX, roomZ, roomY -1, floorType, xFT, zFT);
			}
		}

		/* - - - - - - - - - -
		 * 以下、敵のスポーン
		 * - - - - - - - - - */
		switch (roomDirection) {
		case 0:
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -1, roomY +2, roomZ +roomCenter, LadRoomID.BOTTOM_OF_OVERWORLD + LadRoomID.getDifOfRoom());
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +2, roomZ +1, LadRoomID.BOTTOM_OF_OVERWORLD + LadRoomID.getDifOfRoom());
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +2, roomZ +roomWidth -1, LadRoomID.BOTTOM_OF_OVERWORLD + LadRoomID.getDifOfRoom());
			break;
		case 1:
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +1, roomY +2, roomZ +roomCenter, LadRoomID.BOTTOM_OF_OVERWORLD + LadRoomID.getDifOfRoom());
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -1, roomY +2, roomZ +roomCenter, LadRoomID.BOTTOM_OF_OVERWORLD + LadRoomID.getDifOfRoom());
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +2, roomZ +roomWidth -1, LadRoomID.BOTTOM_OF_OVERWORLD + LadRoomID.getDifOfRoom());
			break;
		case 2:
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +1, roomY +2, roomZ +roomCenter, LadRoomID.BOTTOM_OF_OVERWORLD + LadRoomID.getDifOfRoom());
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +2, roomZ +1, LadRoomID.BOTTOM_OF_OVERWORLD + LadRoomID.getDifOfRoom());
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +2, roomZ +roomWidth -1, LadRoomID.BOTTOM_OF_OVERWORLD + LadRoomID.getDifOfRoom());
			break;
		case 3:
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +1, roomY +2, roomZ +roomCenter, LadRoomID.BOTTOM_OF_OVERWORLD + LadRoomID.getDifOfRoom());
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -1, roomY +2, roomZ +roomCenter, LadRoomID.BOTTOM_OF_OVERWORLD + LadRoomID.getDifOfRoom());
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +2, roomZ +1, LadRoomID.BOTTOM_OF_OVERWORLD + LadRoomID.getDifOfRoom());
			break;
		}

		/* - - - - - - - - - -
		 * 以下、報酬
		 * - - - - - - - - - */

		switch (roomDirection) {
		case 0:
			LadDecorationReward.setChest(world, roomX +roomWidth +1, roomZ +roomCenter, roomY);
			break;
		case 1:
			LadDecorationReward.setChest(world, roomX +roomCenter, roomZ +roomWidth +1, roomY);
			break;
		case 2:
			LadDecorationReward.setChest(world, roomX -1, roomZ +roomCenter, roomY);
			break;
		case 3:
			LadDecorationReward.setChest(world, roomX +roomCenter, roomZ -1, roomY);
			break;
		}
	}

	/*
	 * 黒曜石の床や溶岩と装飾を設置するメソッド
	 */
	public static void setFloorObsidian(World world, Random rand, int x, int z, int y, int floorType[][], int xFT, int zFT) {
		int xF = xFT *3;
		int zF = zFT *3;
		int r = rand.nextInt(16);

		if (floorType[xFT][zFT] == -1) {
			// 溶岩
			LadFillBlock.fillBlockXZ(world, Blocks.lava, x +xF, x +xF +2, z + zF, z +zF +2, y);

			if (r <= 2) {
				// 柱と高床
				LadDecorationPillar.setPillar(world, Blocks.cobblestone, x +xF +1, z +zF +1, y, 4 +r);
				LadFillBlock.fillBlockXZ(world, Blocks.cobblestone, x +xF, x +xF +2, z + zF, z +zF +2, y +4 +r);
				if (r == 0) {
					world.setBlock(x +xF +1, y +4 +r, z +zF +1, Blocks.flowing_lava);
				}
			} else if (r == 3) {
				// 少しの足場
				LadFillBlock.fillBlockX(world, Blocks.cobblestone, x +xF, x +xF +2, z +zF +1, y);
			} else if (r == 4) {
				// 少しの足場
				LadFillBlock.fillBlockZ(world, Blocks.cobblestone, x +xF +1, z +zF, z +zF +2, y);
			}
		} else if (floorType[xFT][zFT] == 0) {
			// 高さ1の黒曜石
			LadFillBlock.fillBlockXZ(world, Blocks.obsidian, x +xF, x +xF +2, z + zF, z +zF +2, y);

			if (r == 0) {
				// 水を流す
				LadDecorationCross.setFourBlock(world, Blocks.cobblestone, x +xF, x +xF +2, z + zF, z +zF +2, y +floorType[xFT][zFT] +1);
				LadDecorationCross.setBlockCross(world, Blocks.flowing_water, x +xF +1, z +zF +1, y +1, 1);
			} else if (r == 1) {
				// 溶岩を流す
				LadDecorationCross.setFourBlock(world, Blocks.cobblestone, x +xF, x +xF +2, z + zF, z +zF +2, y +floorType[xFT][zFT] +1);
				LadDecorationCross.setBlockCross(world, Blocks.flowing_lava, x +xF +1, z +zF +1, y +1, 1);
			} else if (r == 2) {
				// 火(9つ)
				LadFillBlock.fillBlockXZ(world, Blocks.fire, x +xF, x +xF +2, z + zF, z +zF +2, y +1);
			} else if (r == 3) {
				// 火(1つ)
				world.setBlock(x +xF +1, y +1, z +zF +1, Blocks.fire);
			} else if (r == 4) {
				// 流れない溶岩
				world.setBlock(x +xF +1, y, z +zF +1, Blocks.flowing_lava);
			}
		} else {
			// 高さ2以上の黒曜石
			LadFillBlock.fillBlock(world, Blocks.obsidian, x +xF, x +xF +2, z + zF, z +zF +2, y, y +floorType[xFT][zFT]);

			if ((floorType[xFT][zFT] >= 2) && (r /4 == 1)) {
				// 火(1つ)
				world.setBlock(x +xF +1, y +floorType[xFT][zFT] +1, z +zF +1, Blocks.fire);

			} else if ((floorType[xFT][zFT] >= 4) && (r /8 == 1)) {
				// 黒曜石を削って床を作る
				setObsidianToAir(world, rand, x +xF +1, z +zF +1, y +floorType[xFT][zFT], r);
				// グロウストーンを設置
				world.setBlock(x +xF +1, y +floorType[xFT][zFT] -1, z +zF +1, Blocks.glowstone);
			} else if ((floorType[xFT][zFT] == 5) && (r /4 == 0)) {
				// 四隅にネザーラック＋火
				LadDecorationCross.setFourBlock(world, Blocks.netherrack, x +xF, x +xF +2, z + zF, z +zF +2, y +floorType[xFT][zFT] +1);
				LadDecorationCross.setFourBlock(world, Blocks.fire, x +xF, x +xF +2, z + zF, z +zF +2, y +floorType[xFT][zFT] +2);
			}
		}
	}

	/*
	 * 黒曜石を削って階段を作るメソッド
	 */
	public static void setObsidianToAir(World world, Random rand, int x, int z, int y, int r) {
		switch (r) {
		case 8:
			world.setBlockToAir(x -1, y, z -1);
			world.setBlockToAir(x -1, y, z);
			world.setBlockToAir(x -1, y -1, z);
			world.setBlockToAir(x -1, y, z +1);
			world.setBlockToAir(x -1, y -1, z +1);
			world.setBlockToAir(x -1, y -2, z +1);
			break;
		case 9:
			world.setBlockToAir(x -1, y, z +1);
			world.setBlockToAir(x -1, y, z);
			world.setBlockToAir(x -1, y -1, z);
			world.setBlockToAir(x -1, y, z -1);
			world.setBlockToAir(x -1, y -1, z -1);
			world.setBlockToAir(x -1, y -2, z -1);
			break;
		case 10:
			world.setBlockToAir(x +1, y, z -1);
			world.setBlockToAir(x +1, y, z);
			world.setBlockToAir(x +1, y -1, z);
			world.setBlockToAir(x +1, y, z +1);
			world.setBlockToAir(x +1, y -1, z +1);
			world.setBlockToAir(x +1, y -2, z +1);
			break;
		case 11:
			world.setBlockToAir(x +1, y, z +1);
			world.setBlockToAir(x +1, y, z);
			world.setBlockToAir(x +1, y -1, z);
			world.setBlockToAir(x +1, y, z -1);
			world.setBlockToAir(x +1, y -1, z -1);
			world.setBlockToAir(x +1, y -2, z -1);
			break;

		case 12:
			world.setBlockToAir(x -1, y, z -1);
			world.setBlockToAir(x, y, z -1);
			world.setBlockToAir(x, y -1, z -1);
			world.setBlockToAir(x +1, y, z -1);
			world.setBlockToAir(x +1, y -1, z -1);
			world.setBlockToAir(x +1, y -2, z -1);
			break;
		case 13:
			world.setBlockToAir(x +1, y, z -1);
			world.setBlockToAir(x, y, z -1);
			world.setBlockToAir(x, y -1, z -1);
			world.setBlockToAir(x -1, y, z -1);
			world.setBlockToAir(x -1, y -1, z -1);
			world.setBlockToAir(x -1, y -2, z -1);
			break;
		case 14:
			world.setBlockToAir(x -1, y, z +1);
			world.setBlockToAir(x, y, z +1);
			world.setBlockToAir(x, y -1, z +1);
			world.setBlockToAir(x +1, y, z +1);
			world.setBlockToAir(x +1, y -1, z +1);
			world.setBlockToAir(x +1, y -2, z +1);
			break;
		case 15:
			world.setBlockToAir(x +1, y, z +1);
			world.setBlockToAir(x, y, z +1);
			world.setBlockToAir(x, y -1, z +1);
			world.setBlockToAir(x -1, y, z +1);
			world.setBlockToAir(x -1, y -1, z +1);
			world.setBlockToAir(x -1, y -2, z +1);
			break;
		}
	}
}