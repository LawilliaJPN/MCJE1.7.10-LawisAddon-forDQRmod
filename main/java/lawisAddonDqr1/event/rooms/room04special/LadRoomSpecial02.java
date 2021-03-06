package lawisAddonDqr1.event.rooms.room04special;

import java.util.Random;

import dqr.api.Blocks.DQBlocks;
import lawisAddonDqr1.achievement.LadAchievementCore;
import lawisAddonDqr1.api.blocks.LadBlocks;
import lawisAddonDqr1.config.LadDebug;
import lawisAddonDqr1.event.entities.LadSpawnEnemyCore;
import lawisAddonDqr1.event.rooms.LadRoomID;
import lawisAddonDqr1.event.rooms.decoration.LadDecorationFloor;
import lawisAddonDqr1.event.rooms.decoration.LadDecorationReward;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class LadRoomSpecial02 {
	/*
	 * DQRmodのブロックを利用した特殊な戦闘部屋(中層)
	 */
	public static void setRoom(World world, EntityPlayer player) {
		Random rand = new Random();
		int roomDirection = LadRoomID.getDirectionRoom(player, 0);

		int roomX = (int)player.posX;			// 部屋の起点となるX座標
		int roomZ = (int)player.posZ;			// 部屋の起点となるZ座標
		int roomY = (int)player.posY -1;		// 部屋の起点となるY座標（-1）

		int roomHeight = 9;					// 部屋の高さ
		int roomWidth = 20;					// 部屋の幅（-1）
		int roomCenter = roomWidth /2;		// 部屋の中心

		int roomFloor1Y = -1;					// 1階の高さ
		int roomFloor2Y = 5;					// 2階の高さ


		// [Debug] 戦闘部屋固定時に生成方向がチャット表示される（デバッグ用）
		if (LadDebug.getDebugRoom() >=0) {
			player.addChatMessage(new ChatComponentTranslation("roomDirection == " + roomDirection));
			player.addChatMessage(new ChatComponentTranslation("difOfRoom == " + LadRoomID.getDifOfRoom()));

		}

		// 実績の取得
		player.triggerAchievement(LadAchievementCore.roomSpecial02);

		// マイナス座標の時に、部屋の位置がズレることの修正
		if (player.posX < 0) roomX -=1;
		if (player.posZ < 0) roomZ -=1;

		// プレイヤーの向きから部屋の起点となる座標を決める
		switch (roomDirection) {
		case 0:
			roomX -= 4;
			roomZ -= roomCenter;
			break;
		case 1:
			roomX -= roomCenter;
			roomZ -= 4;
			break;
		case 2:
			roomX -= roomWidth -4;
			roomZ -= roomCenter;
			break;
		case 3:
			roomX -= roomCenter;
			roomZ -= roomWidth -4;
			break;
		}
		roomY -= roomFloor2Y;

		/* - - - - - - - - - -
		 * 以下、部屋の生成
		 * - - - - - - - - - */

		/* 空間 */
		// 「エメラルドの装飾石」を設置
		for (int x = -1; x <= roomWidth +1; x++) {
			for (int z = -1; z <= roomWidth +1; z++) {
				for (int y = roomFloor1Y -1; y <= roomHeight; y++) {
					world.setBlock(roomX +x, roomY +y, roomZ +z, DQBlocks.DqmBlockKowareru9);
					// world.setBlock(roomX +x, roomY +y, roomZ +z, Blocks.glass);
				}
			}
		}

		/* 金の装飾石 */
		// 四隅の床の「金の装飾石」を設置
		for (int x = 0; x < 5; x++) {
			for (int z = 0; z < 5; z++) {
				LadDecorationFloor.setFloor(world, DQBlocks.DqmBlockKowareru6, roomX +x, roomZ +z, roomY + roomFloor1Y, roomHeight -roomFloor1Y -1);
				LadDecorationFloor.setFloor(world, DQBlocks.DqmBlockKowareru6, roomX +x, roomZ +roomWidth -z, roomY + roomFloor1Y, roomHeight -roomFloor1Y -1);
				LadDecorationFloor.setFloor(world, DQBlocks.DqmBlockKowareru6, roomX +roomWidth -x, roomZ +z, roomY + roomFloor1Y, roomHeight -roomFloor1Y -1);
				LadDecorationFloor.setFloor(world, DQBlocks.DqmBlockKowareru6, roomX +roomWidth -x, roomZ +roomWidth -z, roomY + roomFloor1Y, roomHeight -roomFloor1Y -1);
			}
		}

		// 2階の床の「金の装飾石」を設置
		for (int iw = 3; iw <= 6; iw++) {
			for (int ic = roomCenter -3; ic <= roomCenter +3; ic++) {
				LadDecorationFloor.setFloor(world, DQBlocks.DqmBlockKowareru6, roomX +iw, roomZ +ic, roomY + roomFloor2Y, roomHeight -roomFloor2Y -1);
				LadDecorationFloor.setFloor(world, DQBlocks.DqmBlockKowareru6, roomX +roomWidth -iw, roomZ +ic, roomY + roomFloor2Y, roomHeight -roomFloor2Y -1);
				LadDecorationFloor.setFloor(world, DQBlocks.DqmBlockKowareru6, roomX +ic, roomZ +iw, roomY + roomFloor2Y, roomHeight -roomFloor2Y -1);
				LadDecorationFloor.setFloor(world, DQBlocks.DqmBlockKowareru6, roomX +ic, roomZ +roomWidth -iw, roomY + roomFloor2Y, roomHeight -roomFloor2Y -1);
			}
		}
		for (int i = roomCenter -1; i <= roomCenter +1; i++) {
			LadDecorationFloor.setFloor(world, DQBlocks.DqmBlockKowareru6, roomX +i, roomZ +roomCenter -3, roomY + roomFloor2Y, roomHeight -roomFloor2Y -1);
			LadDecorationFloor.setFloor(world, DQBlocks.DqmBlockKowareru6, roomX +i, roomZ +roomCenter +3, roomY + roomFloor2Y, roomHeight -roomFloor2Y -1);
			LadDecorationFloor.setFloor(world, DQBlocks.DqmBlockKowareru6, roomX +roomCenter -3, roomZ +i, roomY + roomFloor2Y, roomHeight -roomFloor2Y -1);
			LadDecorationFloor.setFloor(world, DQBlocks.DqmBlockKowareru6, roomX +roomCenter +3, roomZ +i, roomY + roomFloor2Y, roomHeight -roomFloor2Y -1);
		}

		// 1階の出口の「金の装飾石」を設置
		for (int iw = -1; iw <= 2; iw++) {
			for (int ic = roomCenter -1; ic <= roomCenter +1; ic++) {
				LadDecorationFloor.setFloor(world, DQBlocks.DqmBlockKowareru6, roomX +iw, roomZ +ic, roomY + roomFloor1Y, 2);
				LadDecorationFloor.setFloor(world, DQBlocks.DqmBlockKowareru6, roomX +roomWidth -iw, roomZ +ic, roomY + roomFloor1Y, 2);
				LadDecorationFloor.setFloor(world, DQBlocks.DqmBlockKowareru6, roomX +ic, roomZ +iw, roomY + roomFloor1Y, 2);
				LadDecorationFloor.setFloor(world, DQBlocks.DqmBlockKowareru6, roomX +ic, roomZ +roomWidth -iw, roomY + roomFloor1Y, 2);
			}
		}

		/* ジャンプブロック */
		// 1階中央の「ジャンプブロック」を設置
		for (int x = roomCenter -1; x <= roomCenter +1; x++) {
			for (int z = roomCenter -1; z <= roomCenter +1; z++) {
				LadDecorationFloor.setFloor(world, LadBlocks.ladJumpBlock2, roomX +x, roomZ +z, roomY + roomFloor1Y, roomHeight -roomFloor1Y -1);
			}
		}

		// 1階四隅から2階への「ジャンプブロック」の設置
		for (int i = 3; i <= 4; i++) {
			LadDecorationFloor.setFloor(world, LadBlocks.ladJumpBlock2, roomX +i, roomZ +roomCenter -4, roomY + roomFloor1Y, roomHeight -roomFloor1Y -1);
			LadDecorationFloor.setFloor(world, LadBlocks.ladJumpBlock2, roomX +roomWidth -i, roomZ +roomCenter -4, roomY + roomFloor1Y, roomHeight -roomFloor1Y -1);

			LadDecorationFloor.setFloor(world, LadBlocks.ladJumpBlock2, roomX +i, roomZ +roomCenter +4, roomY + roomFloor1Y, roomHeight -roomFloor1Y -1);
			LadDecorationFloor.setFloor(world, LadBlocks.ladJumpBlock2, roomX +roomWidth -i, roomZ +roomCenter +4, roomY + roomFloor1Y, roomHeight -roomFloor1Y -1);

			LadDecorationFloor.setFloor(world, LadBlocks.ladJumpBlock2, roomX +roomCenter -4, roomZ +i, roomY + roomFloor1Y, roomHeight -roomFloor1Y -1);
			LadDecorationFloor.setFloor(world, LadBlocks.ladJumpBlock2, roomX +roomCenter -4, roomZ +roomWidth -i, roomY + roomFloor1Y, roomHeight -roomFloor1Y -1);

			LadDecorationFloor.setFloor(world, LadBlocks.ladJumpBlock2, roomX +roomCenter +4, roomZ +i, roomY + roomFloor1Y, roomHeight -roomFloor1Y -1);
			LadDecorationFloor.setFloor(world, LadBlocks.ladJumpBlock2, roomX +roomCenter +4, roomZ +roomWidth -i, roomY + roomFloor1Y, roomHeight -roomFloor1Y -1);
		}

		/* 強制移動床・改 */
		// 出口から四隅への「強制移動床・改」の設置
		for (int i = 5; i <= 8; i++) {
			for (int j = 0; j <= 1; j++) {
				LadDecorationFloor.setFloor(world, DQBlocks.DqmBlockW2, roomX +i, roomZ +j, roomY + roomFloor1Y, 2);
				LadDecorationFloor.setFloor(world, DQBlocks.DqmBlockW2, roomX +i, roomZ +roomWidth -j, roomY + roomFloor1Y, 2);

				LadDecorationFloor.setFloor(world, DQBlocks.DqmBlockE2, roomX +roomWidth -i, roomZ +j, roomY + roomFloor1Y, 2);
				LadDecorationFloor.setFloor(world, DQBlocks.DqmBlockE2, roomX +roomWidth -i, roomZ +roomWidth -j, roomY + roomFloor1Y, 2);

				LadDecorationFloor.setFloor(world, DQBlocks.DqmBlockN2, roomX +j, roomZ +i, roomY + roomFloor1Y, 2);
				LadDecorationFloor.setFloor(world, DQBlocks.DqmBlockN2, roomX +roomWidth -j, roomZ +i, roomY + roomFloor1Y, 2);

				LadDecorationFloor.setFloor(world, DQBlocks.DqmBlockS2, roomX +j, roomZ +roomWidth -i, roomY + roomFloor1Y, 2);
				LadDecorationFloor.setFloor(world, DQBlocks.DqmBlockS2, roomX +roomWidth -j, roomZ +roomWidth -i, roomY + roomFloor1Y, 2);
			}
		}

		// 1階四隅から2階への「強制移動床・改」の設置
		for (int i = 3; i <= 4; i++) {
			LadDecorationFloor.setFloor(world, DQBlocks.DqmBlockW2, roomX +roomCenter +5, roomZ +i, roomY + roomFloor1Y, roomHeight -roomFloor1Y -1);
			LadDecorationFloor.setFloor(world, DQBlocks.DqmBlockW2, roomX +roomCenter +5, roomZ +roomWidth -i, roomY + roomFloor1Y, roomHeight -roomFloor1Y -1);

			LadDecorationFloor.setFloor(world, DQBlocks.DqmBlockE2, roomX +roomCenter -5, roomZ +i, roomY + roomFloor1Y, roomHeight -roomFloor1Y -1);
			LadDecorationFloor.setFloor(world, DQBlocks.DqmBlockE2, roomX +roomCenter -5, roomZ +roomWidth -i, roomY + roomFloor1Y, roomHeight -roomFloor1Y -1);

			LadDecorationFloor.setFloor(world, DQBlocks.DqmBlockN2, roomX +i, roomZ +roomCenter +5, roomY + roomFloor1Y, roomHeight -roomFloor1Y -1);
			LadDecorationFloor.setFloor(world, DQBlocks.DqmBlockN2, roomX +roomWidth -i, roomZ +roomCenter +5, roomY + roomFloor1Y, roomHeight -roomFloor1Y -1);

			LadDecorationFloor.setFloor(world, DQBlocks.DqmBlockS2, roomX +i, roomZ +roomCenter -5, roomY + roomFloor1Y, roomHeight -roomFloor1Y -1);
			LadDecorationFloor.setFloor(world, DQBlocks.DqmBlockS2, roomX +roomWidth -i, roomZ +roomCenter -5, roomY + roomFloor1Y, roomHeight -roomFloor1Y -1);
		}

		// 中央から出口への「強制移動床・改」の設置
		for (int iw = 3; iw <= roomCenter -3; iw++) {
			for (int ic = roomCenter -1; ic <= roomCenter +1; ic++) {
				LadDecorationFloor.setFloor(world, DQBlocks.DqmBlockW2, roomX +iw, roomZ +ic, roomY + roomFloor1Y, 2);
				LadDecorationFloor.setFloor(world, DQBlocks.DqmBlockE2, roomX +roomWidth -iw, roomZ +ic, roomY + roomFloor1Y, 2);
				LadDecorationFloor.setFloor(world, DQBlocks.DqmBlockN2, roomX +ic, roomZ +iw, roomY + roomFloor1Y, 2);
				LadDecorationFloor.setFloor(world, DQBlocks.DqmBlockS2, roomX +ic, roomZ +roomWidth -iw, roomY + roomFloor1Y, 2);
			}
		}
		for (int i = roomCenter -1; i <= roomCenter +1; i++) {
			LadDecorationFloor.setFloor(world, DQBlocks.DqmBlockW2, roomX +roomCenter -2, roomZ +i, roomY + roomFloor1Y, roomHeight -roomFloor1Y -1);
			LadDecorationFloor.setFloor(world, DQBlocks.DqmBlockE2, roomX +roomCenter +2, roomZ +i, roomY + roomFloor1Y, roomHeight -roomFloor1Y -1);
			LadDecorationFloor.setFloor(world, DQBlocks.DqmBlockN2, roomX +i, roomZ +roomCenter -2, roomY + roomFloor1Y, roomHeight -roomFloor1Y -1);
			LadDecorationFloor.setFloor(world, DQBlocks.DqmBlockS2, roomX +i, roomZ +roomCenter +2, roomY + roomFloor1Y, roomHeight -roomFloor1Y -1);

		}

		/* - - - - - - - - - -
		 * 以下、敵のスポーン
		 * - - - - - - - - - */

		// 1階四隅
		LadSpawnEnemyCore.spawnEnemy(world, player, roomX +1, roomY +roomFloor1Y +2, roomZ +1, LadRoomID.SPECIAL_02 + LadRoomID.getDifOfRoom());
		LadSpawnEnemyCore.spawnEnemy(world, player, roomX +1, roomY +roomFloor1Y +2, roomZ +roomWidth -1, LadRoomID.SPECIAL_02 + LadRoomID.getDifOfRoom());
		LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -1, roomY +roomFloor1Y +2, roomZ +1, LadRoomID.SPECIAL_02 + LadRoomID.getDifOfRoom());
		LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -1, roomY +roomFloor1Y +2, roomZ +roomWidth -1, LadRoomID.SPECIAL_02 + LadRoomID.getDifOfRoom());

		// 2階プレイヤーのいない方向
		switch (roomDirection) {
		case 0:
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -4, roomY +roomFloor2Y +2, roomZ +roomCenter, LadRoomID.SPECIAL_02 + LadRoomID.getDifOfRoom());
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +roomFloor2Y +2, roomZ +4, LadRoomID.SPECIAL_02 + LadRoomID.getDifOfRoom());
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +roomFloor2Y +2, roomZ +roomWidth -4, LadRoomID.SPECIAL_02 + LadRoomID.getDifOfRoom());
			break;
		case 1:
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +4, roomY +roomFloor2Y +2, roomZ +roomCenter, LadRoomID.SPECIAL_02 + LadRoomID.getDifOfRoom());
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -4, roomY +roomFloor2Y +2, roomZ +roomCenter, LadRoomID.SPECIAL_02 + LadRoomID.getDifOfRoom());
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +roomFloor2Y +2, roomZ +roomWidth -4, LadRoomID.SPECIAL_02 + LadRoomID.getDifOfRoom());
			break;
		case 2:
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +4, roomY +roomFloor2Y +2, roomZ +roomCenter, LadRoomID.SPECIAL_02 + LadRoomID.getDifOfRoom());
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +roomFloor2Y +2, roomZ +4, LadRoomID.SPECIAL_02 + LadRoomID.getDifOfRoom());
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +roomFloor2Y +2, roomZ +roomWidth -4, LadRoomID.SPECIAL_02 + LadRoomID.getDifOfRoom());
			break;
		case 3:
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +4, roomY +roomFloor2Y +2, roomZ +roomCenter, LadRoomID.SPECIAL_02 + LadRoomID.getDifOfRoom());
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -4, roomY +roomFloor2Y +2, roomZ +roomCenter, LadRoomID.SPECIAL_02 + LadRoomID.getDifOfRoom());
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +roomFloor2Y +2, roomZ +4, LadRoomID.SPECIAL_02 + LadRoomID.getDifOfRoom());
			break;
		}

		/* - - - - - - - - - -
		 * 以下、報酬
		 * - - - - - - - - - */

		switch (roomDirection) {
		case 0:
			LadDecorationReward.setChest(world, roomX +roomWidth +1, roomZ, roomY);
			LadDecorationReward.setChest(world, roomX +roomWidth +1, roomZ +roomWidth, roomY);
			break;
		case 1:
			LadDecorationReward.setChest(world, roomX, roomZ +roomWidth +1, roomY);
			LadDecorationReward.setChest(world, roomX +roomWidth, roomZ +roomWidth +1, roomY);
			break;
		case 2:
			LadDecorationReward.setChest(world, roomX -1, roomZ, roomY);
			LadDecorationReward.setChest(world, roomX -1, roomZ +roomWidth, roomY);
			break;
		case 3:
			LadDecorationReward.setChest(world, roomX, roomZ -1, roomY);
			LadDecorationReward.setChest(world, roomX +roomWidth, roomZ -1, roomY);
			break;
		}
	}
}