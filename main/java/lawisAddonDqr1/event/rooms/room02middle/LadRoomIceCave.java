package lawisAddonDqr1.event.rooms.room02middle;

import java.util.Random;

import lawisAddonDqr1.achievement.LadAchievementCore;
import lawisAddonDqr1.config.LadDebug;
import lawisAddonDqr1.event.entities.LadSpawnEnemyCore;
import lawisAddonDqr1.event.rooms.LadRoomID;
import lawisAddonDqr1.event.rooms.decoration.LadDecorationIce;
import lawisAddonDqr1.event.rooms.decoration.LadDecorationReward;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class LadRoomIceCave {
	/*
	 * 氷の洞窟の戦闘部屋
	 */
	public static void setRoom(World world, EntityPlayer player) {
		Random rand = new Random();
		int roomDirection = LadRoomID.getDirectionRoom(player, 0);

		int roomX = (int)player.posX;			// 部屋の起点となるX座標
		int roomZ = (int)player.posZ;			// 部屋の起点となるZ座標
		int roomY = (int)player.posY -2;		// 部屋の起点となるY座標（-2）

		int roomHeight = 9;					// 部屋の高さ
		int roomWidth = 20;					// 部屋の幅（-1）
		int roomCenter = roomWidth /2;		// 部屋の中心
		int roomFloorY = -1;					// 地面の高さ

		// [Debug] 戦闘部屋固定時に生成方向がチャット表示される（デバッグ用）
		if (LadDebug.getDebugRoom() >=0) {
			player.addChatMessage(new ChatComponentTranslation("roomDirection == " + roomDirection));
			player.addChatMessage(new ChatComponentTranslation("difOfRoom == " + LadRoomID.getDifOfRoom()));
		}

		// 実績の取得
		player.triggerAchievement(LadAchievementCore.roomIceCave);

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

		/* - - - - - - - - - -
		 * 以下、部屋の生成
		 * - - - - - - - - - */

		/* 空間 */
		// 「氷塊」の設置
		for (int x = -1; x <= roomWidth +1; x++) {
			for (int z = -1; z <= roomWidth +1; z++) {
				for (int y = -2; y <= roomHeight +1; y++) {
					world.setBlock(roomX +x, roomY +y, roomZ +z, Blocks.packed_ice);
				}
			}
		}

		// 「空気」を設置
		for (int x = 0; x <= roomWidth; x++) {
			for (int z = 0; z <= roomWidth; z++) {
				for (int y = 0; y <= roomHeight; y++) {
					world.setBlockToAir(roomX +x, roomY +y, roomZ +z);
				}
			}
		}

		/* 装飾 */
		// 柱となる「氷塊」の設置
		LadDecorationIce.setIcePillar(world, roomX +roomCenter, roomZ +roomCenter, roomY +roomFloorY, roomHeight, false);
		LadDecorationIce.setIcePillar(world, roomX +roomCenter +6, roomZ +roomCenter +6, roomY +roomFloorY, roomHeight, true);
		LadDecorationIce.setIcePillar(world, roomX +roomCenter +6, roomZ +roomCenter -6, roomY +roomFloorY, roomHeight, true);
		LadDecorationIce.setIcePillar(world, roomX +roomCenter -6, roomZ +roomCenter +6, roomY +roomFloorY, roomHeight, true);
		LadDecorationIce.setIcePillar(world, roomX +roomCenter -6, roomZ +roomCenter -6, roomY +roomFloorY, roomHeight, true);

		if (rand.nextInt(2) == 0) {
			// 斜め方向の逆三角柱の設置
			LadDecorationIce.setIceTriangularPrism(world, roomX +roomCenter +4, roomZ +roomCenter +4, roomY +roomFloorY, true);
			LadDecorationIce.setIceTriangularPrism(world, roomX +roomCenter +4, roomZ +roomCenter -4, roomY +roomFloorY, true);
			LadDecorationIce.setIceTriangularPrism(world, roomX +roomCenter -4, roomZ +roomCenter +4, roomY +roomFloorY, true);
			LadDecorationIce.setIceTriangularPrism(world, roomX +roomCenter -4, roomZ +roomCenter -4, roomY +roomFloorY, true);
		}

		if (rand.nextInt(2) == 0) {
			// 水の設置
			LadDecorationIce.setWater(world, roomX +roomCenter, roomZ +roomCenter, roomY +roomFloorY, roomHeight);
		}

		if (rand.nextInt(2) == 0) {
			// 四隅の三角柱の設置
			LadDecorationIce.setIceTriangularPrism(world, roomX +1, roomZ +1, roomY +roomFloorY, false);
			LadDecorationIce.setIceTriangularPrism(world, roomX +1, roomZ +roomWidth -1, roomY +roomFloorY, false);
			LadDecorationIce.setIceTriangularPrism(world, roomX +roomWidth -1, roomZ +1, roomY +roomFloorY, false);
			LadDecorationIce.setIceTriangularPrism(world, roomX +roomWidth -1, roomZ +roomWidth -1, roomY +roomFloorY, false);
		}

		if (rand.nextInt(2) == 0) {
			boolean hasInverted = true;
			if (rand.nextInt(2) == 0) hasInverted = false;

			// 左右の逆三角柱の設置
			switch (roomDirection) {
			case 0:
			case 2:
				LadDecorationIce.setIceTriangularPrism(world, roomX +roomCenter, roomZ +2, roomY +roomFloorY, hasInverted);
				LadDecorationIce.setIceTriangularPrism(world, roomX +roomCenter, roomZ +roomWidth -2, roomY +roomFloorY, hasInverted);
				break;
			case 1:
			case 3:
				LadDecorationIce.setIceTriangularPrism(world, roomX +2, roomZ +roomCenter, roomY +roomFloorY, hasInverted);
				LadDecorationIce.setIceTriangularPrism(world, roomX +roomWidth -2, roomZ +roomCenter, roomY +roomFloorY, hasInverted);
				break;
			}
		}

		if (rand.nextInt(2) == 0) {
			boolean hasInverted = true;
			if (rand.nextInt(2) == 0) hasInverted = false;

			// 左右の三角柱の設置
			switch (roomDirection) {
			case 0:
			case 2:
				LadDecorationIce.setIceTriangularPrism(world, roomX +roomCenter +2, roomZ +4, roomY +roomFloorY, hasInverted);
				LadDecorationIce.setIceTriangularPrism(world, roomX +roomCenter -2, roomZ +4, roomY +roomFloorY, hasInverted);
				LadDecorationIce.setIceTriangularPrism(world, roomX +roomCenter +2, roomZ +roomWidth -4, roomY +roomFloorY, hasInverted);
				LadDecorationIce.setIceTriangularPrism(world, roomX +roomCenter -2, roomZ +roomWidth -4, roomY +roomFloorY, hasInverted);
				break;
			case 1:
			case 3:
				LadDecorationIce.setIceTriangularPrism(world, roomX +4, roomZ +roomCenter +2, roomY +roomFloorY, hasInverted);
				LadDecorationIce.setIceTriangularPrism(world, roomX +4, roomZ +roomCenter -2, roomY +roomFloorY, hasInverted);
				LadDecorationIce.setIceTriangularPrism(world, roomX +roomWidth -4, roomZ +roomCenter +2, roomY +roomFloorY, hasInverted);
				LadDecorationIce.setIceTriangularPrism(world, roomX +roomWidth -4, roomZ +roomCenter -2, roomY +roomFloorY, hasInverted);
				break;
			}
		}

		/* - - - - - - - - - -
		 * 以下、敵のスポーン
		 * - - - - - - - - - */

		switch (roomDirection) {
		case 0:
			// 逆三角柱の上にスポーン
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter +4, roomY +roomFloorY +4, roomZ +roomCenter +4, LadRoomID.ICE_CAVE + LadRoomID.getDifOfRoom());
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter +4, roomY +roomFloorY +4, roomZ +roomCenter -4, LadRoomID.ICE_CAVE + LadRoomID.getDifOfRoom());
			// 四隅の三角柱の上にスポーン
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -1, roomY +roomFloorY +4, roomZ +1, LadRoomID.ICE_CAVE +LadRoomID.getDifOfRoom());
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -1, roomY +roomFloorY +4, roomZ +roomWidth -1, LadRoomID.ICE_CAVE +LadRoomID.getDifOfRoom());
			break;
		case 1:
			// 逆三角柱の上にスポーン
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter -4, roomY +roomFloorY +4, roomZ +roomCenter +4, LadRoomID.ICE_CAVE + LadRoomID.getDifOfRoom());
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter +4, roomY +roomFloorY +4, roomZ +roomCenter +4, LadRoomID.ICE_CAVE + LadRoomID.getDifOfRoom());
			// 四隅の三角柱の上にスポーン
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +1, roomY +roomFloorY +4, roomZ +roomWidth -1, LadRoomID.ICE_CAVE +LadRoomID.getDifOfRoom());
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -1, roomY +roomFloorY +4, roomZ +roomWidth -1, LadRoomID.ICE_CAVE +LadRoomID.getDifOfRoom());
			break;
		case 2:
			// 逆三角柱の上にスポーン
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter -4, roomY +roomFloorY +4, roomZ +roomCenter -4, LadRoomID.ICE_CAVE + LadRoomID.getDifOfRoom());
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter -4, roomY +roomFloorY +4, roomZ +roomCenter +4, LadRoomID.ICE_CAVE + LadRoomID.getDifOfRoom());
			// 四隅の三角柱の上にスポーン
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +1, roomY +roomFloorY +4, roomZ +1, LadRoomID.ICE_CAVE +LadRoomID.getDifOfRoom());
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +1, roomY +roomFloorY +4, roomZ +roomWidth -1, LadRoomID.ICE_CAVE +LadRoomID.getDifOfRoom());
			break;
		case 3:
			// 逆三角柱の上にスポーン
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter -4, roomY +roomFloorY +4, roomZ +roomCenter -4, LadRoomID.ICE_CAVE + LadRoomID.getDifOfRoom());
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter +4, roomY +roomFloorY +4, roomZ +roomCenter -4, LadRoomID.ICE_CAVE + LadRoomID.getDifOfRoom());
			// 四隅の三角柱の上にスポーン
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +1, roomY +roomFloorY +4, roomZ +1, LadRoomID.ICE_CAVE +LadRoomID.getDifOfRoom());
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -1, roomY +roomFloorY +4, roomZ +1, LadRoomID.ICE_CAVE +LadRoomID.getDifOfRoom());
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
}