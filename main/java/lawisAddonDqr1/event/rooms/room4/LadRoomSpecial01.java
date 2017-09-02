package lawisAddonDqr1.event.rooms.room4;

import java.util.Random;

import dqr.api.Blocks.DQBlocks;
import lawisAddonDqr1.achievement.LadAchievementCore;
import lawisAddonDqr1.api.blocks.LadBlocks;
import lawisAddonDqr1.config.LadConfigCore;
import lawisAddonDqr1.config.LadDebug;
import lawisAddonDqr1.event.entities.LadSpawnEnemyCore;
import lawisAddonDqr1.event.rooms.LadRoomID;
import lawisAddonDqr1.event.rooms.decoration.LadDecorationPillar;
import lawisAddonDqr1.event.rooms.decoration.LadDecorationReward;
import lawisAddonDqr1.event.rooms.decoration.LadFillBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class LadRoomSpecial01 {
	/*
	 * DQRのブロックを利用した特殊な戦闘部屋(上層)
	 *
	 * TODO 要素不足
	 */
	public static void setRoom(World world, EntityPlayer player) {
		Random rand = new Random();

		int roomX = (int)player.posX;			// 部屋の起点となるX座標
		int roomZ = (int)player.posZ;			// 部屋の起点となるZ座標
		int roomY = (int)player.posY -2;		// 部屋の起点となるY座標（-2）

		int roomHeight = 9;					// 部屋の高さ
		int roomWidth = 20;					// 部屋の幅（-1）
		int roomCenter = roomWidth /2;		// 部屋の中心

		int roomFloor1Y = 0;					// 1階の高さ (部屋の高さはroomHeight - roomFloor1Y)
		int roomFloor2Y = 5;					// 2階の高さ
		int gateType = rand.nextInt(2);		// 出入口の種類
		int wallType = rand.nextInt(9);		// 壁の種類

		// マイナス座標の時に、部屋の位置がズレることの修正
		if (player.posX < 0) roomX -=1;
		if (player.posZ < 0) roomZ -=1;

		// プレイヤーの位置から部屋の起点となる座標を決める
		roomX -= roomCenter;
		roomZ -= roomCenter;
		roomY -= roomFloor2Y;

		// コンフィグ：負荷軽減オンの時は、2階のみ生成 → 1階の高さを2階の高さにする
		if (LadConfigCore.isRoomReduction) roomFloor1Y = roomFloor2Y +1;


		// [Debug] 戦闘部屋固定時に部屋の情報がチャット表示される（デバッグ用）
		if (LadDebug.getDebugRoom() >=0) {
			player.addChatMessage(new ChatComponentTranslation("gateType == " + gateType));
			player.addChatMessage(new ChatComponentTranslation("wallType == " + wallType));
			player.addChatMessage(new ChatComponentTranslation("difOfRoom == " + LadRoomID.getDifOfRoom()));
		}
		// 実績の取得
		player.triggerAchievement(LadAchievementCore.roomSpecial01);


		/* - - - - - - - - - -
		 * 以下、部屋の生成
		 * - - - - - - - - - */

		/* 空間 */
		// 床下と壁に「レッドストーンの装飾石」を設置
		LadFillBlock.fillBlockXZ(world, DQBlocks.DqmBlockKowareru8, roomX -1, roomX +roomWidth +1, roomZ -1, roomZ +roomWidth +1, roomY +roomFloor1Y -2);
		LadDecorationPillar.setWall(world, DQBlocks.DqmBlockKowareru8, roomX -1, roomX +roomWidth +1, roomZ -1, roomZ +roomWidth +1, roomY +roomFloor1Y -1, roomHeight -roomFloor1Y +2);
		// 「空気」を設置
		LadFillBlock.fillBlockToAir(world, roomX, roomZ, roomWidth, roomY +roomFloor1Y, roomHeight -roomFloor1Y);

		// 天井に「ダメージ床」を設置
		LadFillBlock.fillBlockXZ(world, DQBlocks.DqmBlockToramanaYuka2, roomX -1, roomX +roomWidth +1, roomZ -1, roomZ +roomWidth +1, roomY +roomHeight +1);


		/* 床 */
		// コンフィグ：負荷軽減オンの時は、2階のみ生成
		if (LadConfigCore.isRoomReduction) {
			// 地面に「ラピスの装飾石」を設置
			LadFillBlock.fillBlockXZ(world, DQBlocks.DqmBlockKowareru5, roomX, roomX +roomWidth, roomZ, roomZ +roomWidth, roomY +roomFloor2Y);

		// コンフィグ：負荷軽減オフの時は、1階～2階を生成
		} else {
			// 地面に「ラピスの装飾石」を設置
			for (int x = 0; x <= roomWidth; x++) {
				for (int z = 0; z <= roomWidth; z++) {
					world.setBlock(roomX +x, roomY -2, roomZ +z, DQBlocks.DqmBlockKowareru5);
					world.setBlock(roomX +x, roomY -1, roomZ +z, DQBlocks.DqmBlockKowareru5);
					world.setBlock(roomX +x, roomY +roomFloor2Y, roomZ +z, DQBlocks.DqmBlockKowareru5);
				}
			}
			// 階段となる「ラピスの装飾石」を設置
			world.setBlock(roomX +2, roomY, roomZ +roomWidth, DQBlocks.DqmBlockKowareru5);
			world.setBlock(roomX +1, roomY +1, roomZ +roomWidth, DQBlocks.DqmBlockKowareru5);
			world.setBlock(roomX, roomY +2, roomZ +roomWidth, DQBlocks.DqmBlockKowareru5);
			world.setBlock(roomX, roomY +3, roomZ +roomWidth -1, DQBlocks.DqmBlockKowareru5);
			world.setBlock(roomX, roomY +4, roomZ +roomWidth -2, DQBlocks.DqmBlockKowareru5);
			world.setBlock(roomX +roomWidth -2, roomY, roomZ, DQBlocks.DqmBlockKowareru5);
			world.setBlock(roomX +roomWidth -1, roomY +1, roomZ, DQBlocks.DqmBlockKowareru5);
			world.setBlock(roomX +roomWidth, roomY +2, roomZ, DQBlocks.DqmBlockKowareru5);
			world.setBlock(roomX +roomWidth, roomY +3, roomZ +1, DQBlocks.DqmBlockKowareru5);
			world.setBlock(roomX +roomWidth, roomY +4, roomZ +2, DQBlocks.DqmBlockKowareru5);

			// 2階の床の吹き抜け部分に「空気ブロック」を設置
			for (int x = 9; x <= roomWidth -9; x++) {
				for (int z = 9; z <= roomWidth -9; z++) {
					world.setBlockToAir(roomX +x, roomY +roomFloor2Y, roomZ +z);
					world.setBlockToAir(roomX +x -5, roomY +roomFloor2Y, roomZ +z -5);
					world.setBlockToAir(roomX +x -5, roomY +roomFloor2Y, roomZ +z +5);
					world.setBlockToAir(roomX +x +5, roomY +roomFloor2Y, roomZ +z -5);
					world.setBlockToAir(roomX +x +5, roomY +roomFloor2Y, roomZ +z +5);
				}
			}
			for (int x = 0; x <= 2; x++) {
				for (int z = 4; z <= 6; z++) {
					world.setBlockToAir(roomX +x, roomY +roomFloor2Y, roomZ +z);
					world.setBlockToAir(roomX +roomWidth -x, roomY +roomFloor2Y, roomZ +roomWidth -z);
				}
			}
			for (int z = 0; z <=2; z++) {
				world.setBlockToAir(roomX +roomWidth, roomY +roomFloor2Y, roomZ +z);
				world.setBlockToAir(roomX, roomY +roomFloor2Y, roomZ +roomWidth -z);
			}

			/* 「ジャンプブロック」※プレイヤー以外のEntityもジャンプする等の仕様変更したブロック */
			// 中央
			for (int x = 9; x <= roomWidth -9; x++) {
				for (int z = 9; z <= roomWidth -9; z++) {
					world.setBlock(roomX +x, roomY -1, roomZ +z, LadBlocks.ladJumpBlock2);
				}
			}
			// 通路
			for (int x = 0; x <= 2; x++) {
				for (int z = 4; z <= 6; z++) {
					world.setBlock(roomX +x, roomY -1, roomZ +z, LadBlocks.ladJumpBlock2);
					world.setBlock(roomX +roomWidth -x, roomY -1, roomZ +roomWidth -z, LadBlocks.ladJumpBlock2);
				}
			}
			/* 「強制移動床・改」 */
			// 中央十字
			for (int i = 9; i <= roomWidth -9; i++) {
				for (int j = 3; j <= 8; j++) {
					world.setBlock(roomX +i, roomY -1, roomZ +j, DQBlocks.DqmBlockS2);
					world.setBlock(roomX +i, roomY -1, roomZ +roomWidth -j, DQBlocks.DqmBlockN2);
					world.setBlock(roomX +j, roomY -1, roomZ +i, DQBlocks.DqmBlockW2);
					world.setBlock(roomX +roomWidth -j, roomY -1, roomZ +i, DQBlocks.DqmBlockE2);
				}
			}
			for (int x = 3; x <= 7; x++) {
				world.setBlock(roomX +x, roomY -1, roomZ +8, DQBlocks.DqmBlockW2);
				world.setBlock(roomX +x, roomY -1, roomZ +roomWidth -8, DQBlocks.DqmBlockW2);
				world.setBlock(roomX +roomWidth -x, roomY -1, roomZ +8, DQBlocks.DqmBlockE2);
				world.setBlock(roomX +roomWidth -x, roomY -1, roomZ +roomWidth -8, DQBlocks.DqmBlockE2);
			}
			for (int z = 3; z <= 7; z++) {
				world.setBlock(roomX +8, roomY -1, roomZ +z, DQBlocks.DqmBlockE2);
				world.setBlock(roomX +8, roomY -1, roomZ +roomWidth -z, DQBlocks.DqmBlockE2);
				world.setBlock(roomX +roomWidth -8, roomY -1, roomZ +z, DQBlocks.DqmBlockW2);
				world.setBlock(roomX +roomWidth -8, roomY -1, roomZ +roomWidth -z, DQBlocks.DqmBlockW2);
			}

			// 中央四隅
			for (int i = 4; i <= 7; i++) {
				for (int j = 4; j <= 7; j++) {
					world.setBlock(roomX +i, roomY -1, roomZ +j, DQBlocks.DqmBlockE2);
					world.setBlock(roomX +roomWidth -j, roomY -1, roomZ +i, DQBlocks.DqmBlockS2);
					world.setBlock(roomX +i, roomY -1, roomZ +roomWidth -j, DQBlocks.DqmBlockN2);
					world.setBlock(roomX +roomWidth -j, roomY -1, roomZ +roomWidth -i, DQBlocks.DqmBlockW2);
				}
			}

			// 通路（ジャンプブロックへ）
			for (int z = 7; z <= 12; z++) {
				for (int x = 0; x <= 2; x++) {
					world.setBlock(roomX +x, roomY -1, roomZ +z, DQBlocks.DqmBlockN2);
					world.setBlock(roomX +roomWidth -x, roomY -1, roomZ +roomWidth -z, DQBlocks.DqmBlockS2);
				}
			}

			/* 光源 */
			// 明るさ確保のための「松明」の設置
			world.setBlock(roomX, roomY, roomZ +roomWidth, Blocks.torch, 5, 3);
			world.setBlock(roomX +roomWidth, roomY, roomZ, Blocks.torch, 5, 3);
		}


		/* 壁・柱 */
		// 壁となる「レッドストーンの装飾石」を設置
		for (int y = roomFloor1Y; y <= roomHeight; y++) {
			for (int x = 3; x <= 7; x++) {
				world.setBlock(roomX +x, roomY +y, roomZ +3, DQBlocks.DqmBlockKowareru8);
				world.setBlock(roomX +x, roomY +y, roomZ +roomWidth -3, DQBlocks.DqmBlockKowareru8);
				world.setBlock(roomX +roomWidth -x, roomY +y, roomZ +3, DQBlocks.DqmBlockKowareru8);
				world.setBlock(roomX +roomWidth -x, roomY +y, roomZ +roomWidth -3, DQBlocks.DqmBlockKowareru8);
			}

			for (int z = 4; z <= 7; z++) {
				world.setBlock(roomX +3, roomY +y, roomZ +z, DQBlocks.DqmBlockKowareru8);
				world.setBlock(roomX +3, roomY +y, roomZ +roomWidth -z, DQBlocks.DqmBlockKowareru8);
				world.setBlock(roomX +roomWidth -3, roomY +y, roomZ +z, DQBlocks.DqmBlockKowareru8);
				world.setBlock(roomX +roomWidth -3, roomY +y, roomZ +roomWidth -z, DQBlocks.DqmBlockKowareru8);
			}
		}

		// 柱となる「レッドストーンの装飾石」を設置
		LadDecorationPillar.setFourPillarSlanting(world, DQBlocks.DqmBlockKowareru8, roomX +roomCenter, roomZ +roomCenter, roomY +roomFloor1Y, roomHeight -roomFloor1Y +1, 2);

		// 出入口を作成（壁の「レッドストーンの装飾石」が硬いため）
		if (LadConfigCore.isRoomReduction) {
			// コンフィグ：負荷軽減オンの時
			LadDecorationPillar.setWallZToAir(world, roomX -1, roomZ +1, roomZ +2, roomY +roomFloor1Y, 3);
			LadDecorationPillar.setWallZToAir(world, roomX +roomWidth +1, roomZ +roomWidth -2, roomZ +roomWidth -1, roomY +roomFloor1Y, 3);
			LadDecorationPillar.setWallXToAir(world, roomX +1, roomX +2, roomZ +roomWidth +1, roomY +roomFloor1Y, 3);
			LadDecorationPillar.setWallXToAir(world, roomX +roomWidth -2, roomX +roomWidth -1, roomZ -1, roomY +roomFloor1Y, 3);
		} else {
			// コンフィグ：負荷軽減オフの時
			if (gateType == 0) {
				LadDecorationPillar.setWallZToAir(world, roomX -1, roomZ +1, roomZ +2, roomY +roomFloor1Y, 3);
				LadDecorationPillar.setWallZToAir(world, roomX +roomWidth +1, roomZ +roomWidth -2, roomZ +roomWidth -1, roomY +roomFloor1Y, 3);
			} else {
				LadDecorationPillar.setWallZToAir(world, roomX -1, roomZ, roomZ +2, roomY +roomFloor1Y, 3);
				LadDecorationPillar.setWallZToAir(world, roomX +roomWidth +1, roomZ +roomWidth -2, roomZ +roomWidth, roomY +roomFloor1Y, 3);
				// 壁となる「箱」を設置
				LadDecorationPillar.setWallX(world, DQBlocks.DqmBlockHako2, roomX, roomX +2, roomZ +3, roomY +roomFloor1Y, roomFloor2Y -roomFloor1Y);
				LadDecorationPillar.setWallX(world, DQBlocks.DqmBlockHako2, roomX +roomWidth -2, roomX +roomWidth, roomZ +roomWidth -3, roomY +roomFloor1Y, roomFloor2Y -roomFloor1Y);
			}
		}

		// 壁となる「箱」を設置]
		if (wallType/3 == 0) {
			LadDecorationPillar.setWallX(world, DQBlocks.DqmBlockHako2, roomX, roomX +2, roomZ +3, roomY +roomFloor2Y +1, roomHeight -roomFloor2Y);
			LadDecorationPillar.setWallX(world, DQBlocks.DqmBlockHako2, roomX +roomWidth -2, roomX +roomWidth, roomZ +roomWidth -3, roomY +roomFloor2Y +1, roomHeight -roomFloor2Y);
			if (wallType%3 == 0) {
				LadDecorationPillar.setWallX(world, DQBlocks.DqmBlockHako2, roomX +roomWidth -2, roomX +roomWidth, roomZ +3, roomY +roomFloor2Y +1, roomHeight -roomFloor2Y);
				LadDecorationPillar.setWallX(world, DQBlocks.DqmBlockHako2, roomX, roomX +2, roomZ +roomWidth -3, roomY +roomFloor2Y +1, roomHeight -roomFloor2Y);
			} else if (wallType%3 == 1) {
				LadDecorationPillar.setWallZ(world, DQBlocks.DqmBlockHako2, roomX +roomWidth -3, roomZ, roomZ +2, roomY +roomFloor2Y +1, roomHeight -roomFloor2Y);
				LadDecorationPillar.setWallZ(world, DQBlocks.DqmBlockHako2, roomX +3, roomZ +roomWidth -2, roomZ +roomWidth, roomY +roomFloor2Y +1, roomHeight -roomFloor2Y);
			}
		} else if (wallType/3 == 1) {
			LadDecorationPillar.setWallX(world, DQBlocks.DqmBlockHako2, roomX, roomX +2, roomZ +7, roomY +roomFloor2Y +1, roomHeight -roomFloor2Y);
			LadDecorationPillar.setWallX(world, DQBlocks.DqmBlockHako2, roomX +roomWidth -2, roomX +roomWidth, roomZ +roomWidth -7, roomY +roomFloor2Y +1, roomHeight -roomFloor2Y);
			if (wallType%3 == 0) {
				LadDecorationPillar.setWallX(world, DQBlocks.DqmBlockHako2, roomX +roomCenter -2, roomX +roomCenter +2, roomZ +3, roomY +roomFloor2Y +1, roomHeight -roomFloor2Y);
				LadDecorationPillar.setWallX(world, DQBlocks.DqmBlockHako2, roomX +roomCenter -2, roomX +roomCenter +2, roomZ +roomWidth -3, roomY +roomFloor2Y +1, roomHeight -roomFloor2Y);
			} else if (wallType%3 == 1) {
				LadDecorationPillar.setWallZ(world, DQBlocks.DqmBlockHako2, roomX +roomWidth -7, roomZ, roomZ +2, roomY +roomFloor2Y +1, roomHeight -roomFloor2Y);
				LadDecorationPillar.setWallZ(world, DQBlocks.DqmBlockHako2, roomX +7, roomZ +roomWidth -2, roomZ +roomWidth, roomY +roomFloor2Y +1, roomHeight -roomFloor2Y);
			}
		} else {
			if (wallType%3 == 0) {
				LadDecorationPillar.setWallX(world, DQBlocks.DqmBlockHako2, roomX +roomWidth -2, roomX +roomWidth, roomZ +3, roomY +roomFloor2Y +1, roomHeight -roomFloor2Y);
				LadDecorationPillar.setWallX(world, DQBlocks.DqmBlockHako2, roomX, roomX +2, roomZ +roomWidth -3, roomY +roomFloor2Y +1, roomHeight -roomFloor2Y);
				LadDecorationPillar.setWallX(world, DQBlocks.DqmBlockHako2, roomX +roomCenter -2, roomX +roomCenter +2, roomZ +3, roomY +roomFloor2Y +1, roomHeight -roomFloor2Y);
				LadDecorationPillar.setWallX(world, DQBlocks.DqmBlockHako2, roomX +roomCenter -2, roomX +roomCenter +2, roomZ +roomWidth -3, roomY +roomFloor2Y +1, roomHeight -roomFloor2Y);
			} else if (wallType%3 == 1) {
				LadDecorationPillar.setWallZ(world, DQBlocks.DqmBlockHako2, roomX +roomWidth -7, roomZ, roomZ +2, roomY +roomFloor2Y +1, roomHeight -roomFloor2Y);
				LadDecorationPillar.setWallZ(world, DQBlocks.DqmBlockHako2, roomX +7, roomZ +roomWidth -2, roomZ +roomWidth, roomY +roomFloor2Y +1, roomHeight -roomFloor2Y);
				LadDecorationPillar.setWallZ(world, DQBlocks.DqmBlockHako2, roomX +roomWidth -3, roomZ +roomCenter -2, roomZ +roomCenter +2, roomY +roomFloor2Y +1, roomHeight -roomFloor2Y);
				LadDecorationPillar.setWallZ(world, DQBlocks.DqmBlockHako2, roomX +3, roomZ +roomCenter -2, roomZ +roomCenter +2, roomY +roomFloor2Y +1, roomHeight -roomFloor2Y);
			}
		}

		/* - - - - - - - - - -
		 * 以下、敵のスポーン
		 * - - - - - - - - - */

		// 確定スポーン
		LadSpawnEnemyCore.spawnEnemy(world, player, roomX +1, roomY +roomFloor2Y +2, roomZ +1, LadRoomID.SPECIAL_01 + LadRoomID.getDifOfRoom());
		LadSpawnEnemyCore.spawnEnemy(world, player, roomX +1, roomY +roomFloor2Y +2, roomZ +roomWidth -1, LadRoomID.SPECIAL_01 + LadRoomID.getDifOfRoom());
		LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -1, roomY +roomFloor2Y +2, roomZ +1, LadRoomID.SPECIAL_01 + LadRoomID.getDifOfRoom());
		LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -1, roomY +roomFloor2Y +2, roomZ +roomWidth -1, LadRoomID.SPECIAL_01 + LadRoomID.getDifOfRoom());

		// コンフィグ：負荷軽減オフの時に追加スポーン
		if (!(LadConfigCore.isRoomReduction)) {
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +1, roomY +2, roomZ +1, LadRoomID.SPECIAL_01 + LadRoomID.getDifOfRoom());
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +1, roomY +2, roomZ +roomWidth -1, LadRoomID.SPECIAL_01 + LadRoomID.getDifOfRoom());
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -1, roomY +2, roomZ +1, LadRoomID.SPECIAL_01 + LadRoomID.getDifOfRoom());
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -1, roomY +2, roomZ +roomWidth -1, LadRoomID.SPECIAL_01 + LadRoomID.getDifOfRoom());
		}

		/* - - - - - - - - - -
		 * 以下、報酬
		 * - - - - - - - - - */

		LadDecorationReward.setChest(world, roomX -1, roomY +roomFloor1Y +1, roomZ +5);
		LadDecorationReward.setChest(world, roomX +roomWidth +1, roomY +roomFloor1Y +1, roomZ +roomWidth -5);
	}
}
