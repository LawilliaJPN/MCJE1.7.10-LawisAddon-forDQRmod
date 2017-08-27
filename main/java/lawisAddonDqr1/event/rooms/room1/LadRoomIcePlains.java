package lawisAddonDqr1.event.rooms.room1;

import java.util.Random;

import lawisAddonDqr1.achievement.LadAchievementCore;
import lawisAddonDqr1.config.LadDebug;
import lawisAddonDqr1.event.entities.LadMeasuresAgainstPlayerSuffocation;
import lawisAddonDqr1.event.entities.LadSpawnEnemyCore;
import lawisAddonDqr1.event.rooms.LadRoomID;
import lawisAddonDqr1.event.rooms.decoration.LadDecorationReward;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class LadRoomIcePlains {
	/*
	 * 氷原の戦闘部屋
	 *
	 * TODO リファクタリング
	 */
	public static void setRoom(World world, EntityPlayer player) {
		Random rand = new Random();
		int roomDirection = LadRoomID.getDirectionRoom(player, 0);

		int roomX = (int)player.posX;			// 部屋の起点となるX座標
		int roomZ = (int)player.posZ;			// 部屋の起点となるZ座標
		int roomY = (int)player.posY -1;		// 部屋の起点となるY座標（-1）

		int roomHeight = 5;					// 部屋の高さ
		int roomWidth = 12;					// 部屋の幅（-1）
		int roomCenter = roomWidth/2;			// 部屋の中心
		int roomType = rand.nextInt(9);		// 部屋の種類

		// [Debug] 戦闘部屋固定時に生成方向がチャット表示される（デバッグ用）
		if (LadDebug.getDebugRoom() >=0) {
			player.addChatMessage(new ChatComponentTranslation("roomDirection == " + roomDirection));
			player.addChatMessage(new ChatComponentTranslation("roomType == " + roomType));
			player.addChatMessage(new ChatComponentTranslation("difOfRoom == " + LadRoomID.getDifOfRoom()));
		}

		// 実績の取得
		player.triggerAchievement(LadAchievementCore.roomIcePlains);

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

		/* 空間 */
		// 「空気」の設置
		for (int x = 0; x <= roomWidth; x++) {
			for (int z = 0; z <= roomWidth; z++) {
				for (int y = 0; y <= roomHeight; y++) {
					world.setBlockToAir(roomX +x, roomY +y, roomZ +z);
				}
			}
		}

		/* 地面 */
		// 地面に4段「土ブロック」を敷く
		for (int x = 0; x <= roomWidth; x++) {
			for (int z = 0; z <= roomWidth; z++) {
				for (int y = -3; y <= 0; y++) {
					world.setBlock(roomX +x, roomY +y, roomZ +z, Blocks.dirt);
				}
			}
		}

		// 「土ブロック」の上に「雪ブロック」を敷く
		for (int x = 0; x <= roomWidth; x++) {
			for (int z = 0; z <= roomWidth; z++) {
				world.setBlock(roomX +x, roomY +1, roomZ +z, Blocks.snow_layer);
			}
		}

		switch (roomDirection) {
		case 0:
		case 2:
			// 「空気」を設置する
			for (int x = 2; x <= roomWidth -2; x++) {
				for (int z = 1; z <= roomWidth -1; z++) {
					for (int y = 0; y <= 1; y++) {
						world.setBlockToAir(roomX +x, roomY +y, roomZ +z);
					}
				}
			}
			for (int z = 4; z <= roomWidth -4; z++) {
				world.setBlockToAir(roomX +1, roomY, roomZ +z);
				world.setBlockToAir(roomX +roomWidth -1, roomY, roomZ +z);
				world.setBlockToAir(roomX +1, roomY +1, roomZ +z);
				world.setBlockToAir(roomX +roomWidth -1, roomY +1, roomZ +z);
			}

			// 「土ブロック」の上に「雪ブロック」を敷く
			for (int z = 4; z <= roomWidth -4; z++) {
				world.setBlock(roomX +1, roomY, roomZ +z, Blocks.snow_layer);
				world.setBlock(roomX +roomWidth -1, roomY, roomZ +z, Blocks.snow_layer);
			}

			// 「氷塊」を設置する
			for (int x = 2; x <= roomWidth -2; x++) {
				for (int z = 1; z <= roomWidth -1; z++) {
					world.setBlock(roomX +x, roomY -1, roomZ +z, Blocks.packed_ice);
				}
			}

			// 「水」を設置する
			for (int x = 2; x <= roomWidth -2; x++) {
				for (int z = 1; z <= roomWidth -1; z++) {
					world.setBlock(roomX +x, roomY -2, roomZ +z, Blocks.water);
				}
			}
			for (int x = 5; x <= roomWidth -5; x++) {
				for (int z = 1; z <= roomWidth -1; z++) {
					world.setBlock(roomX +x, roomY -1, roomZ +z, Blocks.water);
				}
			}

			break;
		case 1:
		case 3:
			// 「空気」を設置する
			for (int x = 1; x <= roomWidth -1; x++) {
				for (int z = 2; z <= roomWidth -2; z++) {
					for (int y = 0; y <= 1; y++) {
						world.setBlockToAir(roomX +x, roomY +y, roomZ +z);
					}
				}
			}
			for (int x = 4; x <= roomWidth -4; x++) {
				world.setBlockToAir(roomX +x, roomY, roomZ +1);
				world.setBlockToAir(roomX +x, roomY, roomZ +roomWidth -1);
				world.setBlockToAir(roomX +x, roomY +1, roomZ +1);
				world.setBlockToAir(roomX +x, roomY +1, roomZ +roomWidth -1);
			}

			// 「土ブロック」の上に「雪ブロック」を敷く
			for (int x = 4; x <= roomWidth -4; x++) {
				world.setBlock(roomX +x, roomY, roomZ +1, Blocks.snow_layer);
				world.setBlock(roomX +x, roomY, roomZ +roomWidth -1, Blocks.snow_layer);
			}

			// 「氷塊」を設置する
			for (int x = 1; x <= roomWidth -1; x++) {
				for (int z = 2; z <= roomWidth -2; z++) {
					world.setBlock(roomX +x, roomY -1, roomZ +z, Blocks.packed_ice);
				}
			}

			// 「水」を設置する
			for (int x = 1; x <= roomWidth -1; x++) {
				for (int z = 2; z <= roomWidth -2; z++) {
					world.setBlock(roomX +x, roomY -2, roomZ +z, Blocks.water);
				}
			}
			for (int x = 1; x <= roomWidth -1; x++) {
				for (int z = 5; z <= roomWidth -5; z++) {
					world.setBlock(roomX +x, roomY -1, roomZ +z, Blocks.water);
				}
			}
			break;
		}

		if (roomType%3 == 0) {
			switch (roomDirection) {
			case 0:
			case 2:
				setIceTree(world, roomX +2, roomY, roomZ +1, roomHeight);
				setIceTree(world, roomX +2, roomY, roomZ +roomWidth -1, roomHeight);
				setIceTree(world, roomX +roomWidth -2, roomY, roomZ +1, roomHeight);
				setIceTree(world, roomX +roomWidth -2, roomY, roomZ +roomWidth -1, roomHeight);
				break;
			case 1:
			case 3:
				setIceTree(world, roomX +1, roomY, roomZ +2, roomHeight);
				setIceTree(world, roomX +1, roomY, roomZ +roomWidth -2, roomHeight);
				setIceTree(world, roomX +roomWidth -1, roomY, roomZ +2, roomHeight);
				setIceTree(world, roomX +roomWidth -1, roomY, roomZ +roomWidth -2, roomHeight);
				break;
			}
			setIceTree(world, roomX +roomCenter, roomY, roomZ +roomCenter, roomHeight);

		} else if (roomType%3 ==1) {
			switch (roomDirection) {
			case 0:
			case 2:
				setIceTree(world, roomX +roomCenter, roomY, roomZ +2, roomHeight);
				setIceTree(world, roomX +roomCenter, roomY, roomZ +roomWidth -2, roomHeight);
				setIceTree(world, roomX +4, roomY, roomZ +roomCenter, roomHeight);
				setIceTree(world, roomX +roomWidth -4, roomY, roomZ +roomCenter, roomHeight);
				break;
			case 1:
			case 3:
				setIceTree(world, roomX +2, roomY, roomZ +roomCenter, roomHeight);
				setIceTree(world, roomX +roomWidth -2, roomY, roomZ +roomCenter, roomHeight);
				setIceTree(world, roomX +roomCenter, roomY, roomZ +4, roomHeight);
				setIceTree(world, roomX +roomCenter, roomY, roomZ +roomWidth -4, roomHeight);
				break;
			}

		} else if (roomType %3 == 2) {
			setIceTree(world, roomX +4, roomY, roomZ +4, roomHeight);
			setIceTree(world, roomX +4, roomY, roomZ +roomWidth -4, roomHeight);
			setIceTree(world, roomX +roomWidth -4, roomY, roomZ +4, roomHeight);
			setIceTree(world, roomX +roomWidth -4, roomY, roomZ +roomWidth -4, roomHeight);
		}

		// 両端が陸、中央が水
		if (roomType <= 2) {
			switch (roomDirection) {
			case 0:
			case 2:
				// 中央の両端の床の「氷塊」の設置
				for (int x = 5; x <= roomWidth -5; x++) {
					for (int z = 1; z <= 3; z++) {
						world.setBlock(roomX +x, roomY -1, roomZ +z, Blocks.packed_ice);
						world.setBlock(roomX +x, roomY -1, roomZ +roomWidth -z, Blocks.packed_ice);
					}
				}
				break;
			case 1:
			case 3:
				// 中央の両端の床の「氷塊」の設置
				for (int x = 1; x <= 3; x++) {
					for (int z = 5; z <= roomWidth -5; z++) {
						world.setBlock(roomX +x, roomY -1, roomZ +z, Blocks.packed_ice);
						world.setBlock(roomX +roomWidth -x, roomY -1, roomZ +z, Blocks.packed_ice);
					}
				}
				break;
			}

		// 中央が陸、両端が水
		} else if (roomType <=5){
			// 中央の床となる「氷塊」を設置する
			for (int x = 5; x <= roomWidth -5; x++) {
				for (int z = 5; z <= roomWidth -5; z++) {
					world.setBlock(roomX +x, roomY -1, roomZ +z, Blocks.packed_ice);
				}
			}
		}

		/* - - - - - - - - - -
		 * 以下、敵のスポーン
		 * - - - - - - - - - */

		// 確定スポーン
		switch (roomDirection) {
		case 0:
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -2, roomY +1, roomZ +roomCenter, LadRoomID.ICE_PLAINS + LadRoomID.getDifOfRoom());
			break;
		case 1:
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +1, roomZ +roomWidth -2, LadRoomID.ICE_PLAINS + LadRoomID.getDifOfRoom());
			break;
		case 2:
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +2, roomY +1, roomZ +roomCenter, LadRoomID.ICE_PLAINS + LadRoomID.getDifOfRoom());
			break;
		case 3:
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +1, roomZ +2, LadRoomID.ICE_PLAINS + LadRoomID.getDifOfRoom());
			break;
		}

		// 変動スポーン
		if (LadRoomID.getDifOfRoom() >= rand.nextInt(4)) {
			switch (roomDirection) {
			case 0:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -2, roomY +1, roomZ +2, LadRoomID.ICE_PLAINS + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -2, roomY +1, roomZ +roomWidth -2, LadRoomID.ICE_PLAINS + LadRoomID.getDifOfRoom());
				break;
			case 1:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +2, roomY +1, roomZ +roomWidth -2, LadRoomID.ICE_PLAINS + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -2, roomY +1, roomZ +roomWidth -2, LadRoomID.ICE_PLAINS + LadRoomID.getDifOfRoom());
				break;
			case 2:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +2, roomY +1, roomZ +2, LadRoomID.ICE_PLAINS + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +2, roomY +1, roomZ +roomWidth -2, LadRoomID.ICE_PLAINS + LadRoomID.getDifOfRoom());
				break;
			case 3:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +2, roomY +1, roomZ +2, LadRoomID.ICE_PLAINS + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -2, roomY +1, roomZ +2, LadRoomID.ICE_PLAINS + LadRoomID.getDifOfRoom());
				break;
			}
		}

		/* - - - - - - - - - -
		 * 以下、報酬
		 * - - - - - - - - - */

		switch (roomDirection) {
		case 0:
			LadDecorationReward.setChest(world, roomX +roomWidth, roomY, roomZ +roomCenter);
			break;
		case 1:
			LadDecorationReward.setChest(world, roomX +roomCenter, roomY, roomZ +roomWidth);
			break;
		case 2:
			LadDecorationReward.setChest(world, roomX, roomY, roomZ +roomCenter);
			break;
		case 3:
			LadDecorationReward.setChest(world, roomX +roomCenter, roomY, roomZ);
			break;
		}
	}

	/*
	 * 氷の木を生成するメソッド
	 */
	public static void setIceTree(World world, int x, int y, int z, int roomHeight) {
		// 柱となる「氷塊」を設置する
		for (int y2 = -2; y2 <= roomHeight; y2++) {
			world.setBlock(x, y +y2, z, Blocks.packed_ice);
		}

		// 3段目の「氷塊」を設置する
		world.setBlock(x, y +3, z +1, Blocks.packed_ice);
		world.setBlock(x, y +3, z -1, Blocks.packed_ice);
		world.setBlock(x +1, y +3, z, Blocks.packed_ice);
		world.setBlock(x -1, y +3, z, Blocks.packed_ice);

		// 4段目の「氷塊」を設置する
		world.setBlock(x +1, y +4, z +1, Blocks.packed_ice);
		world.setBlock(x +1, y +4, z -1, Blocks.packed_ice);
		world.setBlock(x -1, y +4, z +1, Blocks.packed_ice);
		world.setBlock(x -1, y +4, z -1, Blocks.packed_ice);

		// 明るさ確保のための「松明」の設置
		world.setBlock(x, y +4, z +1, Blocks.torch, 3, 3);
		world.setBlock(x, y +4, z -1, Blocks.torch, 4, 3);
		world.setBlock(x +1, y +4, z, Blocks.torch, 1, 3);
		world.setBlock(x -1, y +4, z, Blocks.torch, 2, 3);
	}
}
/* 設計図
o,0,1,2,3,4,5,6,5,4,3,2,1,0,x
0,_,_,_,_,_,_,_,_,_,_,_,_,_,
1,_,_,i,i,i,w,w,w,i,i,i,_,_,
2,_,_,i,i,i,w,w,w,i,i,i,_,_,
3,_,_,i,i,i,w,w,w,i,i,i,_,_,
4,_,s,i,i,p,w,w,w,p,i,i,s,_,
5,_,s,i,i,i,i,i,i,i,i,i,s,_,
6,_,s,0,i,i,i,i,i,i,i,2,s,_,
5,_,s,i,i,i,i,i,i,i,i,i,s,_,
4,_,s,i,i,p,w,w,w,p,i,i,s,_,
3,_,_,i,i,i,w,w,w,i,i,i,_,_,
2,_,_,i,i,i,w,w,w,i,i,i,_,_,
1,_,_,i,i,i,w,w,w,i,i,i,_,_,
0,_,_,_,_,_,_,_,_,_,_,_,_,_,
z,

o,0,1,2,3,4,5,6,5,4,3,2,1,0,x
0,_,_,_,_,_,_,_,_,_,_,_,_,_,
1,_,_,_,_,s,s,s,s,s,_,_,_,_,
2,_,i,i,i,i,i,1,i,i,i,i,i,_,
3,_,i,i,i,i,i,i,i,i,i,i,i,_,
4,_,i,i,i,p,i,i,i,p,i,i,i,_,
5,_,w,w,w,w,i,i,i,w,w,w,w,_,
6,_,w,w,w,w,i,i,i,w,w,w,w,_,
5,_,w,w,w,w,i,i,i,w,w,w,w,_,
4,_,i,i,i,p,i,i,i,p,i,i,i,_,
3,_,i,i,i,i,i,i,i,i,i,i,i,_,
2,_,i,i,i,i,i,3,i,i,i,i,i,_,
1,_,_,_,_,s,s,s,s,s,_,_,_,_,
0,_,_,_,_,_,_,_,_,_,_,_,_,_,
z,
*/