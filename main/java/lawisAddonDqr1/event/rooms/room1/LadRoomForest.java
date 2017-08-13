package lawisAddonDqr1.event.rooms.room1;

import java.util.Random;

import lawisAddonDqr1.config.LadDebug;
import lawisAddonDqr1.event.enemies.LadSpawnEnemyCore;
import lawisAddonDqr1.event.rooms.LadRoomCore;
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
		int roomDirection = LadRoomCore.getDirectionRoom(player, 0);

		int roomX = (int)player.posX;			// 部屋の起点となるX座標
		int roomZ = (int)player.posZ -1;		// 部屋の起点となるZ座標（-1）
		int roomY = (int)player.posY -1;		// 部屋の起点となるY座標

		int roomHeight = 6;					// 部屋の高さ
		int roomWidth = 12;					// 部屋の幅（-1）
		int roomCenter = roomWidth /2;		// 部屋の中心

		int roomType = rand.nextInt(4);		// 部屋の種類
		if (roomType >= 2) roomType +=2;		// シラカバのメタデータ値は2

		// [Debug] 戦闘部屋固定時に生成方向がチャット表示される（デバッグ用）
		if (LadDebug.getDebugRoom() >=0) {
			player.addChatMessage(new ChatComponentTranslation("roomDirection == " + roomDirection));
		}

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

		/* 地面 */
		// 地面の下に「土ブロック」を敷く
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

		/* 空間 */
		// 「空気」の設置
		for (int x = 0; x <= roomWidth; x++) {
			for (int z = 0; z <= roomWidth; z++) {
				for (int y = 0; y <= roomHeight; y++) {
					world.setBlockToAir(roomX +x, roomY +y, roomZ +z);
				}
			}
		}

		/* 木 */
		// 「オークの木」の生成
		setTree(world, rand, roomX +2, roomY, roomZ +2, roomType/2);
		setTree(world, rand, roomX +2, roomY, roomZ +roomWidth -2, roomType/2);
		setTree(world, rand, roomX +roomWidth -2, roomY, roomZ +2, roomType/2);
		setTree(world, rand, roomX +roomWidth -2, roomY, roomZ +roomWidth -2, roomType/2);

		// 木の多い森
		if (roomType%2 == 0) {
			setTree(world, rand, roomX +3, roomY, roomZ +roomCenter, roomType/2);
			setTree(world, rand, roomX +roomCenter, roomY, roomZ +3, roomType/2);
			setTree(world, rand, roomX +roomWidth -3, roomY, roomZ +roomCenter, roomType/2);
			setTree(world, rand, roomX +roomCenter, roomY, roomZ +roomWidth -3, roomType/2);
			setTree(world, rand, roomX +roomCenter, roomY, roomZ +roomCenter, roomType/2);

			/* 光源 */
			// 明るさ確保のための「松明」の設置
			world.setBlock(roomX, roomY, roomZ, Blocks.torch, 5, 3);
			world.setBlock(roomX, roomY, roomZ +roomWidth, Blocks.torch, 5, 3);
			world.setBlock(roomX +roomWidth, roomY, roomZ, Blocks.torch, 5, 3);
			world.setBlock(roomX +roomWidth, roomY, roomZ +roomWidth, Blocks.torch, 5, 3);

		// 村の街灯があり、木が少ない森
		} else {
			/* 光源 */
			// 村の灯りのパーツ「フェンス」を設置
			world.setBlock(roomX +roomCenter, roomY, roomZ +roomCenter, Blocks.fence);
			world.setBlock(roomX +roomCenter, roomY +1, roomZ +roomCenter, Blocks.fence);
			world.setBlock(roomX +roomCenter, roomY +2, roomZ +roomCenter, Blocks.fence);
			// 村の灯りのパーツ「黒色の羊毛」を設置」
			world.setBlock(roomX +roomCenter, roomY +3, roomZ +roomCenter, Blocks.wool, 15, 2);
			// 明るさ確保のための「松明」の設置
			world.setBlock(roomX +roomCenter, roomY +3, roomZ +roomCenter +1, Blocks.torch, 3, 3);
			world.setBlock(roomX +roomCenter, roomY +3, roomZ +roomCenter -1, Blocks.torch, 4, 3);
			world.setBlock(roomX +roomCenter +1, roomY +3, roomZ +roomCenter, Blocks.torch, 1, 3);
			world.setBlock(roomX +roomCenter -1, roomY +3, roomZ +roomCenter, Blocks.torch, 2, 3);
		}


		/* - - - - - - - - - -
		 * 以下、敵のスポーン
		 * - - - - - - - - - */

		// 確定スポーン
		switch (roomDirection) {
		case 0:
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -1, roomY, roomZ +roomCenter, LadRoomCore.FOREST + LadRoomCore.getDifOfRoom());
			break;
		case 1:
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY, roomZ +roomWidth -1, LadRoomCore.FOREST + LadRoomCore.getDifOfRoom());
			break;
		case 2:
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +1, roomY, roomZ +roomCenter, LadRoomCore.FOREST + LadRoomCore.getDifOfRoom());
			break;
		case 3:
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY, roomZ +1, LadRoomCore.FOREST + LadRoomCore.getDifOfRoom());
			break;
		}

		// 確率スポーン
		if (LadRoomCore.getDifOfRoom() >= rand.nextInt(4)) {
			switch (roomDirection) {
			case 0:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -4, roomY, roomZ +2, LadRoomCore.FOREST + LadRoomCore.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -4, roomY, roomZ +roomWidth -2, LadRoomCore.FOREST + LadRoomCore.getDifOfRoom());
				break;
			case 1:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +2, roomY, roomZ +roomWidth -4, LadRoomCore.FOREST + LadRoomCore.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -2, roomY, roomZ +roomWidth -4, LadRoomCore.FOREST + LadRoomCore.getDifOfRoom());
				break;
			case 2:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +4, roomY, roomZ +2, LadRoomCore.FOREST + LadRoomCore.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +4, roomY, roomZ +roomWidth -2, LadRoomCore.FOREST + LadRoomCore.getDifOfRoom());
				break;
			case 3:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +2, roomY, roomZ +4, LadRoomCore.FOREST + LadRoomCore.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -2, roomY, roomZ +4, LadRoomCore.FOREST + LadRoomCore.getDifOfRoom());
				break;
			}
		}
	}


	/*
	 * 木を生成するメソッド
	 */
	public static void setTree(World world, Random rand, int x, int y, int z, int meta) {
		int treeHeight = 2;

		if (rand.nextInt(2) == 0)  x++;
		if (rand.nextInt(2) == 0)  x--;
		if (rand.nextInt(2) == 0)  z++;
		if (rand.nextInt(2) == 0)  z--;
		if (rand.nextInt(2) == 0)  treeHeight++;

		// 木の下を「土ブロック」に変える
		world.setBlock(x, y -1, z, Blocks.dirt);

		/* 葉ブロック */
		// 頂上 十字
		world.setBlock(x, y +treeHeight +3, z, Blocks.leaves, meta, 2);
		world.setBlock(x, y +treeHeight +3, z +1, Blocks.leaves, meta, 2);
		world.setBlock(x, y +treeHeight +3, z -1, Blocks.leaves, meta, 2);
		world.setBlock(x +1, y +treeHeight +3, z, Blocks.leaves, meta, 2);
		world.setBlock(x -1, y +treeHeight +3, z, Blocks.leaves, meta, 2);

		// 上段
		for (int x2 = -1; x2 <= 1; x2++) {
			for (int z2 = -1; z2 <= 1; z2++) {
				world.setBlock(x +x2, y +treeHeight +2, z +z2, Blocks.leaves, meta, 2);
			}
		}

		// 下段
		for (int x2 = -2; x2 <= 2; x2++) {
			for (int z2 = -2; z2 <= 2; z2++) {
				for (int y2 = 0; y2 <= 1; y2++) {
					world.setBlock(x +x2, y +treeHeight +y2, z +z2, Blocks.leaves, meta, 2);
				}
			}
		}

		/* 原木 */
		for (int i = 0; i <= treeHeight+2; i++) {
			world.setBlock(x, y +i, z, Blocks.log, meta, 2);
		}
	}
}
