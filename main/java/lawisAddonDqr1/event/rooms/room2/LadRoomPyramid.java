package lawisAddonDqr1.event.rooms.room2;

import java.util.Random;

import dqr.DQR;
import lawisAddonDqr1.achievement.LadAchievementCore;
import lawisAddonDqr1.api.blocks.LadBlocks;
import lawisAddonDqr1.config.LadDebug;
import lawisAddonDqr1.event.entities.LadSpawnEnemyCore;
import lawisAddonDqr1.event.rooms.LadRoomID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class LadRoomPyramid {
	/*
	 * バニラの「ピラミッド」をモチーフにした戦闘部屋
	 */
	public static void setRoom(World world, EntityPlayer player) {
		Random rand = new Random();

		int roomX = (int)player.posX;			// 部屋の起点となるX座標
		int roomZ = (int)player.posZ;			// 部屋の起点となるZ座標
		int roomY = (int)player.posY -1;		// 部屋の起点となるY座標（-1）

		int roomHeight = 8;					// 部屋の高さ
		int roomWidth = 18;					// 部屋の幅（-1）
		int roomCenter = roomWidth /2;		// 部屋の中心
		int roomType = rand.nextInt(6);		// 部屋の種類

		int roomFloorB1Y = -6;
		int roomFloor1Y = -1;
		int roomFloor2Y = 3;

		// [Debug] 部屋の種類がチャット表示される（デバッグ用）
		if (LadDebug.getDebugRoom() >=0) {
			player.addChatMessage(new ChatComponentTranslation("roomType == " + roomType));
		}

		// 実績の取得
		player.triggerAchievement(LadAchievementCore.roomPyramid);

		// マイナス座標の時に、部屋の位置がズレることの修正
		if (roomX < 0) roomX -=1;
		if (roomZ < 0) roomZ -=1;

		// プレイヤーが中心になるように生成(仮)
		roomX -= roomCenter;
		roomZ -= roomCenter;

		/* - - - - - - - - - -
		 * 以下、部屋の生成
		 * - - - - - - - - - */

		/* 空間 */
		// 「空気」を設置
		for (int x = 0; x <= roomWidth; x++) {
			for (int z = 0; z <= roomWidth; z++) {
				for (int y = 0; y <= roomHeight +2; y++) {
					world.setBlockToAir(roomX +x, roomY +y, roomZ +z);
				}
			}
		}

		// 「砂岩」を設置
		for (int i = roomFloor1Y -1; i <= roomHeight; i++) {
			for (int x = i; x <= roomWidth -i; x++) {
				for (int z = i; z <= roomWidth -i; z++) {
					world.setBlock(roomX +x, roomY +i, roomZ +z, Blocks.sandstone);
				}
			}
		}

		// 「空気」を設置
		for (int i = roomFloor1Y +1; i <= roomHeight; i++) {
			for (int x = i +1; x <= roomWidth -i -1; x++) {
				for (int z = i +1; z <= roomWidth -i -1; z++) {
					world.setBlockToAir(roomX +x, roomY +i, roomZ +z);
				}
			}
		}

		// 1階の柱の「滑らかな砂岩」を設置
		for (int y = 0; y <= roomFloor2Y -1; y++) {
			world.setBlock(roomX +roomCenter +2, roomY +y, roomZ +roomCenter +2, Blocks.sandstone, 2, 2);
			world.setBlock(roomX +roomCenter +2, roomY +y, roomZ +roomCenter -2, Blocks.sandstone, 2, 2);
			world.setBlock(roomX +roomCenter -2, roomY +y, roomZ +roomCenter +2, Blocks.sandstone, 2, 2);
			world.setBlock(roomX +roomCenter -2, roomY +y, roomZ +roomCenter -2, Blocks.sandstone, 2, 2);
		}

		// 2階部分の床となる「砂岩」を設置
		for (int x = roomFloor2Y; x <= roomWidth -roomFloor2Y; x++) {
			for (int z = roomFloor2Y; z <= roomWidth -roomFloor2Y; z++) {
				world.setBlock(roomX +x, roomY +roomFloor2Y, roomZ +z, Blocks.sandstone);
			}
		}

		// 2階の床の吹き抜け部分となる「空気」を設置
		for (int x = roomCenter -1; x <= roomCenter +1; x++) {
			for (int z = roomCenter -1; z <= roomCenter +1; z++) {
				world.setBlockToAir(roomX +x, roomY +roomFloor2Y, roomZ +z);
			}
		}

		// 2階の出入口となる「滑らかな砂岩」を設置
		for (int y = roomFloor2Y +1; y <= roomFloor2Y +3; y++) {
			for (int x = roomCenter -1; x <= roomCenter +1; x++) {
				world.setBlock(roomX +x, roomY +y, roomZ +roomCenter +5, Blocks.sandstone, 2, 2);
				world.setBlock(roomX +x, roomY +y, roomZ +roomCenter -5, Blocks.sandstone, 2, 2);
			}

			for (int z = roomCenter -1; z <= roomCenter +1; z++) {
				world.setBlock(roomX +roomCenter +5, roomY +y, roomZ +z, Blocks.sandstone, 2, 2);
				world.setBlock(roomX +roomCenter -5, roomY +y, roomZ +z, Blocks.sandstone, 2, 2);
			}
		}

		// 2階の出入口となる「空気」を設置
		for (int y = roomFloor2Y +1; y <= roomFloor2Y +2; y++) {
			for (int i = 4; i <= 5; i++) {
				world.setBlockToAir(roomX +roomCenter, roomY +y, roomZ +roomCenter +i);
				world.setBlockToAir(roomX +roomCenter, roomY +y, roomZ +roomCenter -i);
				world.setBlockToAir(roomX +roomCenter +i, roomY +y, roomZ +roomCenter);
				world.setBlockToAir(roomX +roomCenter -i, roomY +y, roomZ +roomCenter);
			}
		}

		// 2階の出入口となる「砂岩」を設置
		for (int x = roomCenter -1; x <= roomCenter +1; x++) {
			world.setBlock(roomX +x, roomY +roomFloor2Y +3, roomZ +roomCenter +4, Blocks.sandstone);
			world.setBlock(roomX +x, roomY +roomFloor2Y +3, roomZ +roomCenter -4, Blocks.sandstone);
		}
		for (int z = roomCenter -1; z <= roomCenter +1; z++) {
			world.setBlock(roomX +roomCenter +4, roomY +roomFloor2Y +3, roomZ +z, Blocks.sandstone);
			world.setBlock(roomX +roomCenter -4, roomY +roomFloor2Y +3, roomZ +z, Blocks.sandstone);
		}

		// 2階の外の地面となる「砂岩」を設置
		for (int x = roomCenter -2; x <= roomCenter +2; x++) {
			world.setBlock(roomX +x, roomY +roomFloor2Y, roomZ +roomCenter +7, Blocks.sandstone);
			world.setBlock(roomX +x, roomY +roomFloor2Y, roomZ +roomCenter -7, Blocks.sandstone);
			world.setBlock(roomX +x, roomY +roomFloor2Y, roomZ +roomCenter +8, Blocks.sandstone);
			world.setBlock(roomX +x, roomY +roomFloor2Y, roomZ +roomCenter -8, Blocks.sandstone);
			world.setBlock(roomX +x, roomY +roomFloor2Y -1, roomZ +roomCenter +8, Blocks.sandstone);
			world.setBlock(roomX +x, roomY +roomFloor2Y -1, roomZ +roomCenter -8, Blocks.sandstone);
		}
		for (int z = roomCenter -2; z <= roomCenter +2; z++) {
			world.setBlock(roomX +roomCenter +7, roomY +roomFloor2Y, roomZ +z, Blocks.sandstone);
			world.setBlock(roomX +roomCenter -7, roomY +roomFloor2Y, roomZ +z, Blocks.sandstone);
			world.setBlock(roomX +roomCenter +8, roomY +roomFloor2Y, roomZ +z, Blocks.sandstone);
			world.setBlock(roomX +roomCenter -8, roomY +roomFloor2Y, roomZ +z, Blocks.sandstone);
			world.setBlock(roomX +roomCenter +8, roomY +roomFloor2Y -1, roomZ +z, Blocks.sandstone);
			world.setBlock(roomX +roomCenter -8, roomY +roomFloor2Y -1, roomZ +z, Blocks.sandstone);
		}

		/* 装飾 */
		// 1階の床に「青色の羊毛」を設置
		world.setBlock(roomX +roomCenter, roomY +roomFloor1Y, roomZ +roomCenter, Blocks.wool, 11, 2);

		// 1階の床に「橙色の羊毛」を設置
		world.setBlock(roomX +roomCenter, roomY +roomFloor1Y, roomZ +roomCenter +2, Blocks.wool, 1, 2);
		world.setBlock(roomX +roomCenter, roomY +roomFloor1Y, roomZ +roomCenter +3, Blocks.wool, 1, 2);
		world.setBlock(roomX +roomCenter, roomY +roomFloor1Y, roomZ +roomCenter -2, Blocks.wool, 1, 2);
		world.setBlock(roomX +roomCenter, roomY +roomFloor1Y, roomZ +roomCenter -3, Blocks.wool, 1, 2);

		world.setBlock(roomX +roomCenter +2, roomY +roomFloor1Y, roomZ +roomCenter, Blocks.wool, 1, 2);
		world.setBlock(roomX +roomCenter +3, roomY +roomFloor1Y, roomZ +roomCenter, Blocks.wool, 1, 2);
		world.setBlock(roomX +roomCenter -2, roomY +roomFloor1Y, roomZ +roomCenter, Blocks.wool, 1, 2);
		world.setBlock(roomX +roomCenter -3, roomY +roomFloor1Y, roomZ +roomCenter, Blocks.wool, 1, 2);

		world.setBlock(roomX +roomCenter +1, roomY +roomFloor1Y, roomZ +roomCenter +1, Blocks.wool, 1, 2);
		world.setBlock(roomX +roomCenter +1, roomY +roomFloor1Y, roomZ +roomCenter -1, Blocks.wool, 1, 2);
		world.setBlock(roomX +roomCenter -1, roomY +roomFloor1Y, roomZ +roomCenter +1, Blocks.wool, 1, 2);
		world.setBlock(roomX +roomCenter -1, roomY +roomFloor1Y, roomZ +roomCenter -1, Blocks.wool, 1, 2);

		// 1階の外側の柱に「模様入りの砂岩」と「滑らかな砂岩」を設置
		for (int x = roomCenter -5; x <= roomCenter +5; x += 2) {
			world.setBlock(roomX +x, roomY +roomFloor1Y +1, roomZ +roomCenter +6, Blocks.sandstone, 2, 2);
			world.setBlock(roomX +x, roomY +roomFloor1Y +1, roomZ +roomCenter -6, Blocks.sandstone, 2, 2);
			world.setBlock(roomX +x, roomY +roomFloor1Y +2, roomZ +roomCenter +6, Blocks.sandstone, 1, 2);
			world.setBlock(roomX +x, roomY +roomFloor1Y +2, roomZ +roomCenter -6, Blocks.sandstone, 1, 2);
		}

		for (int z = roomCenter -5; z <= roomCenter +5; z += 2) {
			world.setBlock(roomX +roomCenter +6, roomY +roomFloor1Y +1, roomZ +z, Blocks.sandstone, 2, 2);
			world.setBlock(roomX +roomCenter -6, roomY +roomFloor1Y +1, roomZ +z, Blocks.sandstone, 2, 2);
			world.setBlock(roomX +roomCenter +6, roomY +roomFloor1Y +2, roomZ +z, Blocks.sandstone, 1, 2);
			world.setBlock(roomX +roomCenter -6, roomY +roomFloor1Y +2, roomZ +z, Blocks.sandstone, 1, 2);
		}

		// 1階の外側の天井に「砂岩」を設置
		for (int x = roomCenter -6; x <= roomCenter +6; x++) {
			world.setBlock(roomX +x, roomY +roomFloor1Y +3, roomZ +roomCenter +6, Blocks.sandstone);
			world.setBlock(roomX +x, roomY +roomFloor1Y +3, roomZ +roomCenter -6, Blocks.sandstone);
		}

		for (int z = roomCenter -5; z <= roomCenter +5; z++) {
			world.setBlock(roomX +roomCenter +6, roomY +roomFloor1Y +3, roomZ +z, Blocks.sandstone);
			world.setBlock(roomX +roomCenter -6, roomY +roomFloor1Y +3, roomZ +z, Blocks.sandstone);
		}

		/* 光源 */
		// 明るさ確保のための「松明」の設置
		if (roomType == 5) {

		}

		// 1階の四隅
		world.setBlock(roomX +roomCenter +6, roomY +roomFloor1Y +1, roomZ +roomCenter +6, Blocks.torch, 5, 3);
		world.setBlock(roomX +roomCenter +6, roomY +roomFloor1Y +1, roomZ +roomCenter -6, Blocks.torch, 5, 3);
		world.setBlock(roomX +roomCenter -6, roomY +roomFloor1Y +1, roomZ +roomCenter +6, Blocks.torch, 5, 3);
		world.setBlock(roomX +roomCenter -6, roomY +roomFloor1Y +1, roomZ +roomCenter -6, Blocks.torch, 5, 3);

		// 2階の四隅
		world.setBlock(roomX +roomCenter +4, roomY +roomFloor2Y +1, roomZ +roomCenter +4, Blocks.torch, 5, 3);
		world.setBlock(roomX +roomCenter +4, roomY +roomFloor2Y +1, roomZ +roomCenter -4, Blocks.torch, 5, 3);
		world.setBlock(roomX +roomCenter -4, roomY +roomFloor2Y +1, roomZ +roomCenter +4, Blocks.torch, 5, 3);
		world.setBlock(roomX +roomCenter -4, roomY +roomFloor2Y +1, roomZ +roomCenter -4, Blocks.torch, 5, 3);

		/* 部屋の種類ごとの設置 */
		// 地下室
		if (roomType/4 == 0) {
			// 「砂岩」を設置
			for (int x = roomCenter -3; x <= roomCenter +3; x++) {
				for (int z = roomCenter -3; z <= roomCenter +3; z++) {
					for (int y = roomFloorB1Y -2; y <= roomFloorB1Y; y++) {
						world.setBlock(roomX +x, roomY +y, roomZ +z, Blocks.sandstone);
					}
				}
			}

			// 壁の「模様入りの砂岩」「滑らかな砂岩」を設置
			for (int y = roomFloorB1Y; y <= roomFloor1Y -1 ; y++) {
				int meta = 2;
				if (y == roomFloorB1Y +3) meta = 1;

				for (int x = roomCenter -3; x <= roomCenter +3; x++) {
					for (int z = roomCenter -3; z <= roomCenter +3; z++) {
						world.setBlock(roomX +x, roomY +y, roomZ +z, Blocks.sandstone, meta, 2);
					}
				}
			}

			// 空間の「空気」を設置
			for (int x = roomCenter -1; x <= roomCenter +1; x++) {
				for (int z = roomCenter -1; z <= roomCenter +1; z++) {
					for (int y = roomFloorB1Y +1; y <= roomFloor1Y -1; y++) {
						world.setBlockToAir(roomX +x, roomY +y, roomZ +z);
					}
				}
			}

			// 壁に「空気」を設置
			for (int y = roomFloorB1Y +2; y <= roomFloorB1Y +3 ; y++) {
				world.setBlockToAir(roomX +roomCenter, roomY +y, roomZ +roomCenter +2);
				world.setBlockToAir(roomX +roomCenter, roomY +y, roomZ +roomCenter -2);
				world.setBlockToAir(roomX +roomCenter +2, roomY +y, roomZ +roomCenter);
				world.setBlockToAir(roomX +roomCenter -2, roomY +y, roomZ +roomCenter);
			}

			// 「チェスト」を設置
			setChest(world, roomX +roomCenter, roomY +roomFloorB1Y +2, roomZ +roomCenter +2);
			setChest(world, roomX +roomCenter, roomY +roomFloorB1Y +2, roomZ +roomCenter -2);
			setChest(world, roomX +roomCenter +2, roomY +roomFloorB1Y +2, roomZ +roomCenter);
			setChest(world, roomX +roomCenter -2, roomY +roomFloorB1Y +2, roomZ +roomCenter);


			if (roomType == 0) {
				// 「TNT」の設置
				for (int x = roomCenter -1; x <= roomCenter +1; x++) {
					for (int z = roomCenter -1; z <= roomCenter +1; z++) {
						world.setBlock(roomX +x, roomY +roomFloorB1Y -1, roomZ +z, Blocks.tnt);
					}
				}

				// 「石の感圧板」の設置
				world.setBlock(roomX +roomCenter, roomY +roomFloorB1Y +1, roomZ +roomCenter, Blocks.stone_pressure_plate);
			} else if (roomType <= 2) {
				// 「ジャンプブロック(LAD)」の設置
				for (int x = roomCenter -1; x <= roomCenter +1; x++) {
					for (int z = roomCenter -1; z <= roomCenter +1; z++) {
						world.setBlock(roomX +x, roomY +roomFloorB1Y, roomZ +z, LadBlocks.ladJumpBlock2);
					}
				}
			}
		// 地下室なし
		} else if (roomType == 4) {
			// 橙色の羊毛の上に「松明」
			world.setBlock(roomX +roomCenter, roomY +roomFloor1Y +1, roomZ +roomCenter +3, Blocks.torch, 5, 3);
			world.setBlock(roomX +roomCenter, roomY +roomFloor1Y +1, roomZ +roomCenter -3, Blocks.torch, 5, 3);
			world.setBlock(roomX +roomCenter +3, roomY +roomFloor1Y +1, roomZ +roomCenter, Blocks.torch, 5, 3);
			world.setBlock(roomX +roomCenter -3, roomY +roomFloor1Y +1, roomZ +roomCenter, Blocks.torch, 5, 3);
		} else if (roomType == 5) {
			// 「ジャンプブロック(LAD)」の設置
			world.setBlock(roomX +roomCenter, roomY +roomFloor1Y, roomZ +roomCenter, LadBlocks.ladJumpBlock2);

			// 天井の高さ変更
			for (int x = roomCenter -1; x <= roomCenter +1; x++) {
				for (int z = roomCenter -1; z <= roomCenter +1; z++) {
					world.setBlockToAir(roomX +x, roomY +roomHeight, roomZ +z);
					world.setBlock(roomX +x, roomY +roomHeight -1, roomZ +z, Blocks.sandstone);
				}
			}
			world.setBlockToAir(roomX +roomCenter, roomY +roomHeight -1, roomZ +roomCenter);
		}


		/* - - - - - - - - - -
		 * 以下、敵のスポーン
		 * - - - - - - - - - */

		// 1階中
		switch (rand.nextInt(4)) {
		case 0:
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter +7, roomY +roomFloor1Y +1, roomZ +roomCenter +7, LadRoomID.PYRAMID + LadRoomID.getDifOfRoom());
			break;
		case 1:
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter +7, roomY +roomFloor1Y +1, roomZ +roomCenter -7, LadRoomID.PYRAMID + LadRoomID.getDifOfRoom());
			break;
		case 2:
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter -7, roomY +roomFloor1Y +1, roomZ +roomCenter +7, LadRoomID.PYRAMID + LadRoomID.getDifOfRoom());
			break;
		case 3:
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter -7, roomY +roomFloor1Y +1, roomZ +roomCenter -7, LadRoomID.PYRAMID + LadRoomID.getDifOfRoom());
			break;
		}

		// 2階外
		LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +roomFloor2Y +2, roomZ +roomCenter +7, LadRoomID.PYRAMID + LadRoomID.getDifOfRoom());
		LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +roomFloor2Y +2, roomZ +roomCenter -7, LadRoomID.PYRAMID + LadRoomID.getDifOfRoom());
		LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter +7, roomY +roomFloor2Y +2, roomZ +roomCenter, LadRoomID.PYRAMID + LadRoomID.getDifOfRoom());
		LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter -7, roomY +roomFloor2Y +2, roomZ +roomCenter, LadRoomID.PYRAMID + LadRoomID.getDifOfRoom());

		// 地下
		switch (roomType) {
		case 1:
		case 3:
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +roomFloorB1Y +2, roomZ +roomCenter, LadRoomID.PYRAMID);
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter +1, roomY +roomFloorB1Y +2, roomZ +roomCenter +1, LadRoomID.PYRAMID);
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter +1, roomY +roomFloorB1Y +2, roomZ +roomCenter -1, LadRoomID.PYRAMID);
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter -1, roomY +roomFloorB1Y +2, roomZ +roomCenter +1, LadRoomID.PYRAMID);
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter -1, roomY +roomFloorB1Y +2, roomZ +roomCenter -1, LadRoomID.PYRAMID);
			break;
		case 2:
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +roomFloorB1Y +2, roomZ +roomCenter, LadRoomID.Metal_Slime_Without_Log);
			break;
		}
	}

	/*
	 * チェストを設置するためのメソッド
	 *
	 * DQRのコードをスポブロ部屋生成処理を参考にさせてもらいました。
	 */
	public static void setChest(World world, int x, int y, int z) {
		Random rand = new Random();
		world.setBlock(x, y, z, Blocks.chest);

    	if(world.getTileEntity(x, y, z) instanceof TileEntityChest) {
            TileEntityChest tileentitychest = (TileEntityChest)world.getTileEntity(x, y, z);

            if (tileentitychest != null) {
            	if (rand.nextInt(4) == 0) {
            		DQR.randomItem.generateChestContentsRank2(rand, tileentitychest);
            	} else {
            		DQR.randomItem.generateChestContentsRank1(rand, tileentitychest);
            	}
            }
    	}
	}
}
