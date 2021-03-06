package lawisAddonDqr1.event.rooms.room01upper;

import java.util.Random;

import lawisAddonDqr1.achievement.LadAchievementCore;
import lawisAddonDqr1.config.LadDebug;
import lawisAddonDqr1.event.entities.LadMeasuresAgainstPlayerSuffocation;
import lawisAddonDqr1.event.entities.LadSpawnEnemyCore;
import lawisAddonDqr1.event.rooms.LadRoomID;
import lawisAddonDqr1.event.rooms.decoration.LadDecorationCross;
import lawisAddonDqr1.event.rooms.decoration.LadDecorationPillar;
import lawisAddonDqr1.event.rooms.decoration.LadDecorationReward;
import lawisAddonDqr1.event.rooms.decoration.LadDecorationTorch;
import lawisAddonDqr1.event.rooms.decoration.LadDecorationTree;
import lawisAddonDqr1.event.rooms.decoration.LadFillBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class LadRoomForest {
	/*
	 * 森林の戦闘部屋
	 */
	public static void setRoom(World world, EntityPlayer player) {
		Random rand = new Random();
		int roomDirection = LadRoomID.getDirectionRoom(player, 0);

		int roomX = (int)player.posX;			// 部屋の起点となるX座標
		int roomZ = (int)player.posZ;			// 部屋の起点となるZ座標
		int roomY = (int)player.posY -1;		// 部屋の起点となるY座標

		int roomHeight = 6;					// 部屋の高さ
		int roomWidth = 12;					// 部屋の幅（-1）
		int roomCenter = roomWidth /2;		// 部屋の中心

		int roomType = rand.nextInt(4);		// 部屋の種類
		if (roomType >= 2) roomType +=2;		// シラカバのメタデータ値は2
		Boolean isSpawnAnimal = false;		// 友好mobスポーンパターン

		// [Debug] 戦闘部屋固定時に生成方向がチャット表示される（デバッグ用）
		if (LadDebug.getDebugRoom() >=0) {
			player.addChatMessage(new ChatComponentTranslation("roomDirection == " + roomDirection));
			player.addChatMessage(new ChatComponentTranslation("difOfRoom == " + LadRoomID.getDifOfRoom()));
		}

		// 実績の取得
		player.triggerAchievement(LadAchievementCore.roomForest);

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

		// 落下物対策
		LadMeasuresAgainstPlayerSuffocation.measuresAgainstFallingObject(world, roomX, roomZ, roomWidth, roomWidth, roomY +roomHeight +1);

		/* 地面 */
		// 地面の下に「土ブロック」を敷く
		LadFillBlock.fillBlockXZ(world, Blocks.dirt, roomX, roomZ, roomWidth, roomY -2);

		// 地面に「草ブロック」を敷く
		LadFillBlock.fillBlockXZ(world, Blocks.grass, roomX, roomZ, roomWidth, roomY -1);

		/* 空間 */
		// 「空気」の設置
		LadFillBlock.fillBlockToAir(world, roomX, roomZ, roomWidth, roomY, roomHeight);

		/* 木 */
		// 「オークの木」の生成
		LadDecorationTree.setTree(world, rand, roomX +2, roomZ +2, roomY, roomType/2);
		LadDecorationTree.setTree(world, rand, roomX +2, roomZ +roomWidth -2, roomY, roomType/2);
		LadDecorationTree.setTree(world, rand, roomX +roomWidth -2, roomZ +2, roomY, roomType/2);
		LadDecorationTree.setTree(world, rand, roomX +roomWidth -2, roomZ +roomWidth -2, roomY, roomType/2);

		// 木の多い森
		if (roomType%2 == 0) {
			LadDecorationTree.setTree(world, rand, roomX +3, roomZ +roomCenter, roomY, roomType/2);
			LadDecorationTree.setTree(world, rand, roomX +roomCenter, roomZ +3, roomY, roomType/2);
			LadDecorationTree.setTree(world, rand, roomX +roomWidth -3, roomZ +roomCenter, roomY, roomType/2);
			LadDecorationTree.setTree(world, rand, roomX +roomCenter, roomZ +roomWidth -3, roomY, roomType/2);
			LadDecorationTree.setTree(world, rand, roomX +roomCenter, roomZ +roomCenter, roomY, roomType/2);

			// 部屋の四隅に明るさ確保のための「松明」の設置
			LadDecorationTorch.setFourTorchSlanting(world, roomX, roomZ, roomY, roomWidth, 0);

		// 村の街灯があり、木が少ない森
		} else {
			/* 光源 */
			// 村の街灯を中央に設置
			LadDecorationTorch.setVillageLight(world, roomX +roomCenter, roomZ +roomCenter, roomY);

			// フェンスで周囲を囲うか囲わないか
			if (rand.nextInt(2) == 0) {
				// フェンスで戦闘部屋を囲う
				LadDecorationPillar.setBlockEnclosure(world, Blocks.fence, roomX, roomZ, roomWidth, roomY);
				// 部屋の四隅に明るさ確保のための「松明」の設置
				LadDecorationTorch.setFourTorchSlanting(world, roomX, roomZ, roomY +1, roomWidth, 0);
				// フェンスを一部フェンスゲートに変更
				LadDecorationCross.setFourBlockCrossWith2Meta(world, Blocks.fence_gate, roomX +roomCenter, roomZ +roomCenter, roomY, roomCenter);

				// 友好mobスポーンパターンの抽選
				if (rand.nextInt(5) == 0) {
					isSpawnAnimal = true;
				}
			}
		}

		/* - - - - - - - - - -
		 * 以下、敵のスポーン
		 * - - - - - - - - - */

		if (isSpawnAnimal) {
			switch (roomDirection) {
			case 0:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -1, roomY, roomZ +roomCenter, LadRoomID.FOREST);
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -4, roomY, roomZ +2, LadRoomID.FOREST);
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -4, roomY, roomZ +roomWidth -2, LadRoomID.FOREST);
				break;
			case 1:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY, roomZ +roomWidth -1, LadRoomID.FOREST);
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +2, roomY, roomZ +roomWidth -4, LadRoomID.FOREST);
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -2, roomY, roomZ +roomWidth -4, LadRoomID.FOREST);
				break;
			case 2:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +1, roomY, roomZ +roomCenter, LadRoomID.FOREST);
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +4, roomY, roomZ +2, LadRoomID.FOREST);
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +4, roomY, roomZ +roomWidth -2, LadRoomID.FOREST);
				break;
			case 3:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY, roomZ +1, LadRoomID.FOREST);
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +2, roomY, roomZ +4, LadRoomID.FOREST);
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -2, roomY, roomZ +4, LadRoomID.FOREST);
				break;
			}
			// イベント実績
			player.triggerAchievement(LadAchievementCore.eventAnimal);

		} else {
			// 確定スポーン
			switch (roomDirection) {
			case 0:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -1, roomY, roomZ +roomCenter, LadRoomID.FOREST + LadRoomID.getDifOfRoom());
				break;
			case 1:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY, roomZ +roomWidth -1, LadRoomID.FOREST + LadRoomID.getDifOfRoom());
				break;
			case 2:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +1, roomY, roomZ +roomCenter, LadRoomID.FOREST + LadRoomID.getDifOfRoom());
				break;
			case 3:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY, roomZ +1, LadRoomID.FOREST + LadRoomID.getDifOfRoom());
				break;
			}

			// 確率スポーン
			if (LadRoomID.getDifOfRoom() >= rand.nextInt(4)) {
				switch (roomDirection) {
				case 0:
					LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -4, roomY, roomZ +2, LadRoomID.FOREST + LadRoomID.getDifOfRoom());
					LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -4, roomY, roomZ +roomWidth -2, LadRoomID.FOREST + LadRoomID.getDifOfRoom());
					break;
				case 1:
					LadSpawnEnemyCore.spawnEnemy(world, player, roomX +2, roomY, roomZ +roomWidth -4, LadRoomID.FOREST + LadRoomID.getDifOfRoom());
					LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -2, roomY, roomZ +roomWidth -4, LadRoomID.FOREST + LadRoomID.getDifOfRoom());
					break;
				case 2:
					LadSpawnEnemyCore.spawnEnemy(world, player, roomX +4, roomY, roomZ +2, LadRoomID.FOREST + LadRoomID.getDifOfRoom());
					LadSpawnEnemyCore.spawnEnemy(world, player, roomX +4, roomY, roomZ +roomWidth -2, LadRoomID.FOREST + LadRoomID.getDifOfRoom());
					break;
				case 3:
					LadSpawnEnemyCore.spawnEnemy(world, player, roomX +2, roomY, roomZ +4, LadRoomID.FOREST + LadRoomID.getDifOfRoom());
					LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -2, roomY, roomZ +4, LadRoomID.FOREST + LadRoomID.getDifOfRoom());
					break;
				}
			}
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