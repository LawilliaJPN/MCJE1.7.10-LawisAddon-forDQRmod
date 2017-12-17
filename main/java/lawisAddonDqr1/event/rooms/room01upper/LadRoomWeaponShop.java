package lawisAddonDqr1.event.rooms.room01upper;

import java.util.Random;

import dqr.api.Blocks.DQDecorates;
import lawisAddonDqr1.achievement.LadAchievementCore;
import lawisAddonDqr1.config.LadDebug;
import lawisAddonDqr1.event.entities.LadMeasuresAgainstPlayerSuffocation;
import lawisAddonDqr1.event.entities.LadSpawnEnemyCore;
import lawisAddonDqr1.event.rooms.LadRoomID;
import lawisAddonDqr1.event.rooms.decoration.LadDecorationPillar;
import lawisAddonDqr1.event.rooms.decoration.LadDecorationReward;
import lawisAddonDqr1.event.rooms.decoration.LadDecorationTorch;
import lawisAddonDqr1.event.rooms.decoration.LadFillBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemDoor;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class LadRoomWeaponShop {
	/*
	 * DQRmodの村の武器屋をモチーフとした戦闘部屋
	 *
	 * TODO 要素不足
	 */
	public static void setRoom(World world, EntityPlayer player) {
		Random rand = new Random();
		int roomDirection = LadRoomID.getDirectionRoom(player, 0);

		int roomX = (int)player.posX;			// 部屋の起点となるX座標
		int roomZ = (int)player.posZ ;			// 部屋の起点となるZ座標
		int roomY = (int)player.posY -1;		// 部屋の起点となるY座標（-1）

		int roomWidthX = 9;					// 部屋のX座標方向の幅
		int roomWidthZ = 9;					// 部屋のZ座標方向の幅
		int roomHeight = 4;					// 部屋の高さ
		int roomCenterX = 0;					// 部屋のX座標方向の中央
		int roomCenterZ = 0;					// 部屋のX座標方向の中央
		int roomOutside = 2;					// 部屋の外の広さ

		Boolean playerIsCustomer = false;	// NPCスポーンパターン
		if ((rand.nextInt(20) == 0) || (LadDebug.getDebugRoom() == LadRoomID.WEAPON_SHOP_CUSTOMER)) {
			playerIsCustomer = true;
			roomDirection = (roomDirection +2)%4;
		}

		// マイナス座標の時に、部屋の位置がズレることの修正
		if (player.posX < 0) roomX -=1;
		if (player.posZ < 0) roomZ -=1;

		// プレイヤーの向きから部屋の起点となる座標を決める
		switch (roomDirection) {
		case 0:
			if (playerIsCustomer) roomX -= 6;
			else roomX -= 4;
			roomZ -= 4;
			roomWidthX++;
			roomWidthZ--;
			break;
		case 1:
			roomX -= 4;
			if (playerIsCustomer) roomZ -= 6;
			else roomZ -= 4;
			roomWidthX--;
			roomWidthZ++;
			break;
		case 2:
			if (playerIsCustomer) roomX -= 4;
			else roomX -= 6;
			roomZ -= 4;
			roomWidthX++;
			roomWidthZ--;
			break;
		case 3:
			roomX -= 4;
			if (playerIsCustomer) roomZ -= 4;
			else roomZ -= 6;
			roomWidthX--;
			roomWidthZ++;
			break;
		}

		roomCenterX = roomWidthX /2;
		roomCenterZ = roomWidthZ /2;

		// [Debug] 戦闘部屋固定時に生成方向がチャット表示される（デバッグ用）
		if (LadDebug.getDebugRoom() >=0) {
			player.addChatMessage(new ChatComponentTranslation("roomDirection == " + roomDirection));
			player.addChatMessage(new ChatComponentTranslation("difOfRoom == " + LadRoomID.getDifOfRoom()));
		}

		// 実績の取得
		player.triggerAchievement(LadAchievementCore.roomWeaponShop);

		/* - - - - - - - - - -
		 * 以下、部屋の生成
		 * - - - - - - - - - */

		// 落下物対策
		LadMeasuresAgainstPlayerSuffocation.measuresAgainstFallingObject(world, roomX -3, roomZ -3, roomWidthX +6, roomWidthZ +6, roomY +roomHeight +3);

		/* 地面 */
		// 地面の下に「土ブロック」を敷く
		LadFillBlock.fillBlockXZ(world, Blocks.dirt, roomX -roomOutside, roomX +roomWidthX +roomOutside, roomZ -roomOutside, roomZ +roomWidthZ +roomOutside, roomY -2);

		// 地面に「草ブロック」を敷く
		LadFillBlock.fillBlockXZ(world, Blocks.grass, roomX -roomOutside, roomX +roomWidthX +roomOutside, roomZ -roomOutside, roomZ +roomWidthZ +roomOutside, roomY -1);

		// 空間の確保のために「空気」を設置する
		LadFillBlock.fillBlockToAir(world, roomX -roomOutside, roomX +roomWidthX +roomOutside, roomZ -roomOutside, roomZ +roomWidthZ +roomOutside, roomY, roomY +roomHeight +roomOutside);

		// 明るさ確保のために「松明」を設置する
		LadDecorationTorch.setFourTorch(world, roomX, roomX +roomWidthX, roomZ, roomZ +roomWidthZ, roomY);

		/* 建物基本 */
		// 床に「レンガブロック」を敷く
		LadFillBlock.fillBlockXZ(world, Blocks.brick_block, roomX +1, roomX +roomWidthX -1, roomZ +1, roomZ +roomWidthZ -1, roomY -1);

		// 壁の「石レンガ」を設置する（壁の生成1）
		LadDecorationPillar.setWall(world, Blocks.stonebrick, roomX +1, roomX +roomWidthX -1, roomZ +1, roomZ +roomWidthZ -1, roomY, roomHeight);

		// 屋根の「石のハーフブロック」を設置する
		LadFillBlock.fillBlockXZ(world, Blocks.stone_slab, roomX +1, roomX +roomWidthX -1, roomZ +1, roomZ +roomWidthZ -1, roomY +roomHeight);

		// 窓ガラスの設置
		switch (roomDirection) {
		case 0:
		case 2:
			world.setBlock(roomX +3, roomY +1, roomZ +1, Blocks.glass_pane);
			world.setBlock(roomX +4, roomY +1, roomZ +1, Blocks.glass_pane);
			world.setBlock(roomX +roomWidthX -3, roomY +1, roomZ +1, Blocks.glass_pane);
			world.setBlock(roomX +roomWidthX -4, roomY +1, roomZ +1, Blocks.glass_pane);
			world.setBlock(roomX +3, roomY +1, roomZ +roomWidthZ -1, Blocks.glass_pane);
			world.setBlock(roomX +4, roomY +1, roomZ +roomWidthZ -1, Blocks.glass_pane);
			world.setBlock(roomX +roomWidthX -3, roomY +1, roomZ +roomWidthZ -1, Blocks.glass_pane);
			world.setBlock(roomX +roomWidthX -4, roomY +1, roomZ +roomWidthZ -1, Blocks.glass_pane);
			break;
		case 1:
		case 3:
			world.setBlock(roomX +1, roomY +1, roomZ +3, Blocks.glass_pane);
			world.setBlock(roomX +1, roomY +1, roomZ +4, Blocks.glass_pane);
			world.setBlock(roomX +1, roomY +1, roomZ +roomWidthZ -3, Blocks.glass_pane);
			world.setBlock(roomX +1, roomY +1, roomZ +roomWidthZ -4, Blocks.glass_pane);
			world.setBlock(roomX +roomWidthX -1, roomY +1, roomZ +3, Blocks.glass_pane);
			world.setBlock(roomX +roomWidthX -1, roomY +1, roomZ +4, Blocks.glass_pane);
			world.setBlock(roomX +roomWidthX -1, roomY +1, roomZ +roomWidthZ -3, Blocks.glass_pane);
			world.setBlock(roomX +roomWidthX -1, roomY +1, roomZ +roomWidthZ -4, Blocks.glass_pane);
			break;
		}

		/* 以下、方向ごとに座標を指定した方が楽なものの設置 */
		if (roomDirection == 0) {
			/*
			o,0,1,2,3,4,5,6,7,8,9,10,x
			0,_,_,_,_,_,_,_,_,_,_,_,
			1,_,w,w,g,g,w,g,g,w,w,_,
			2,_,w,b,_,_,s,l,_,_,w,s,
			3,_,w,_,_,_,l,c,_,_,w,_,
			4,_,w,b,_,p,s,_,_,_,d,_,
			5,_,w,_,_,_,s,c,_,_,w,_,
			6,_,w,p,p,w,s,l,_,_,w,s,
			7,_,w,w,g,g,w,g,g,w,w,_,
			8,_,_,_,_,_,_,_,_,_,_,_,
			z
			*/

			/* 建物外観 */
			// 「ドア」の設置
			world.setBlockToAir(roomX +roomWidthX -1, roomY, roomZ +4);
			world.setBlockToAir(roomX +roomWidthX -1, roomY +1, roomZ +4);
			ItemDoor.placeDoorBlock(world, roomX +roomWidthX -1, roomY, roomZ +4, 2, Blocks.wooden_door);
			// 「看板」の設置
			world.setBlock(roomX +roomWidthX, roomY +1, roomZ +2, DQDecorates.DqmBlockBukiya, 1, 2);
			world.setBlock(roomX +roomWidthX, roomY +1, roomZ +roomWidthZ -2, DQDecorates.DqmBlockBukiya, 1, 2);

			/* 建物内装 */
			// 「小さい椅子」の設置
			world.setBlock(roomX +roomWidthX -4, roomY, roomZ +3, DQDecorates.DqmBlockIsu);
			world.setBlock(roomX +roomWidthX -4, roomY, roomZ +roomWidthZ -3, DQDecorates.DqmBlockIsu);
			// 「キャンドルスタンド」の設置
			world.setBlock(roomX +roomWidthX -4, roomY, roomZ +2, DQDecorates.DqmBlockTaimatu2);
			world.setBlock(roomX +roomWidthX -4, roomY, roomZ +roomWidthZ -2, DQDecorates.DqmBlockTaimatu2);
			// 「薪」の設置
			world.setBlock(roomX +4, roomY, roomZ +roomWidthZ -2, DQDecorates.DqmBlockMaki, 1, 2);
			// 「ツボ」の設置
			world.setBlock(roomX +2, roomY, roomZ +roomWidthZ -2, DQDecorates.DqmBlockTubo);
			world.setBlock(roomX +3, roomY, roomZ +roomWidthZ -2, DQDecorates.DqmBlockTubo);
			// 「タル」の設置
			world.setBlock(roomX +2, roomY, roomZ +2, DQDecorates.DqmBlockTaru);
			// 「本棚」の設置
			world.setBlock(roomX +2, roomY, roomZ +4, DQDecorates.DqmBlockHondana, 1, 2);
			// 「ヘパイトスのひだね」の設置
			world.setBlock(roomX +5, roomY +1, roomZ +3, DQDecorates.DqmBlockHepaitosu, 1, 2);
			// カウンターとなる「階段」の設置
			for (int z = 2; z <= roomWidthZ -2; z++) {
				world.setBlock(roomX +5, roomY, roomZ +z, Blocks.oak_stairs, 5, 2);
			}


		} else if (roomDirection == 1) {
			/*
			o,0,1,2,3,4,5,6,7,8,x
			0,_,_,_,_,_,_,_,_,_,
			1,_,w,w,w,w,w,w,w,_,
			2,_,w,b,_,b,_,p,w,_,
			3,_,g,_,_,_,_,p,g,_,
			4,_,g,_,_,p,_,w,g,_,
			5,_,w,s,s,s,l,s,w,_,
			6,_,g,l,c,_,c,l,g,_,
			7,_,g,_,_,_,_,_,g,_,
			8,_,w,_,_,_,_,_,w,_,
			9,_,w,w,w,d,w,w,w,_,
			10,_,_,s,_,_,_,s,_,_,
			z
			*/

			/* 建物外観 */
			// 「ドア」の設置
			world.setBlockToAir(roomX +4, roomY, roomZ +roomWidthZ -1);
			world.setBlockToAir(roomX +4, roomY +1, roomZ +roomWidthZ -1);
			ItemDoor.placeDoorBlock(world, roomX +4, roomY, roomZ +roomWidthZ -1, 3, Blocks.wooden_door);
			// 「看板」の設置
			world.setBlock(roomX +2, roomY +1, roomZ +roomWidthZ, DQDecorates.DqmBlockBukiya, 2, 2);
			world.setBlock(roomX +roomWidthX -2, roomY +1, roomZ +roomWidthZ, DQDecorates.DqmBlockBukiya, 2, 2);

			/* 建物内装 */
			// 「小さい椅子」の設置
			world.setBlock(roomX +3, roomY, roomZ +roomWidthZ -4, DQDecorates.DqmBlockIsu);
			world.setBlock(roomX +roomWidthX -3, roomY, roomZ +roomWidthZ -4, DQDecorates.DqmBlockIsu);
			// 「キャンドルスタンド」の設置
			world.setBlock(roomX +2, roomY, roomZ +roomWidthZ -4, DQDecorates.DqmBlockTaimatu2);
			world.setBlock(roomX +roomWidthX -2, roomY, roomZ +roomWidthZ -4, DQDecorates.DqmBlockTaimatu2);
			// 「薪」の設置
			world.setBlock(roomX +roomWidthX -2, roomY, roomZ +4, DQDecorates.DqmBlockMaki, 0, 2);
			// 「ツボ」の設置
			world.setBlock(roomX +roomWidthX -2, roomY, roomZ +2, DQDecorates.DqmBlockTubo);
			world.setBlock(roomX +roomWidthX -2, roomY, roomZ +3, DQDecorates.DqmBlockTubo);
			// 「タル」の設置
			world.setBlock(roomX +2, roomY, roomZ +2, DQDecorates.DqmBlockTaru);
			// 「本棚」の設置
			world.setBlock(roomX +4, roomY, roomZ +2, DQDecorates.DqmBlockHondana, 2, 2);
			// 「ヘパイトスのひだね」の設置
			world.setBlock(roomX +roomWidthX -3, roomY +1, roomZ +5, DQDecorates.DqmBlockHepaitosu, 2, 2);
			// カウンターとなる「階段」の設置×
			for (int x = 2; x <= roomWidthX -2; x++) {
				world.setBlock(roomX +x, roomY, roomZ +5, Blocks.oak_stairs, 7, 2);
			}


		} else if (roomDirection == 2) {
			/*
			o,0,1,2,3,4,5,6,7,8,9,10,x
			0,_,_,_,_,_,_,_,_,_,_,_,
			1,_,w,w,g,g,w,g,g,w,w,_,
			2,s,w,_,_,l,s,_,_,b,w,_,
			3,_,w,_,_,c,s,_,_,_,w,_,
			4,_,d,_,_,_,s,p,_,b,w,_,
			5,_,w,_,_,c,l,_,_,_,w,_,
			6,s,w,_,_,l,s,w,p,p,w,_,
			7,_,w,w,g,g,w,g,g,w,w,_,
			8,_,_,_,_,_,_,_,_,_,_,_,
			z
			*/

			/* 建物外観 */
			// 「ドア」の設置
			world.setBlockToAir(roomX +1, roomY, roomZ +4);
			world.setBlockToAir(roomX +1, roomY +1, roomZ +4);
			ItemDoor.placeDoorBlock(world, roomX +1, roomY, roomZ +4, 0, Blocks.wooden_door);
			// 「看板」の設置
			world.setBlock(roomX, roomY +1, roomZ +2, DQDecorates.DqmBlockBukiya, 3, 2);
			world.setBlock(roomX, roomY +1, roomZ +roomWidthZ -2, DQDecorates.DqmBlockBukiya, 3, 2);

			/* 建物内装 */
			// 「小さい椅子」の設置
			world.setBlock(roomX +4, roomY, roomZ +3, DQDecorates.DqmBlockIsu);
			world.setBlock(roomX +4, roomY, roomZ +roomWidthZ -3, DQDecorates.DqmBlockIsu);
			// 「キャンドルスタンド」の設置
			world.setBlock(roomX +4, roomY, roomZ +2, DQDecorates.DqmBlockTaimatu2);
			world.setBlock(roomX +4, roomY, roomZ +roomWidthZ -2, DQDecorates.DqmBlockTaimatu2);
			// 「薪」の設置
			world.setBlock(roomX +roomWidthX -4, roomY, roomZ +roomWidthZ -2, DQDecorates.DqmBlockMaki, 1, 2);
			// 「ツボ」の設置
			world.setBlock(roomX +roomWidthX -2, roomY, roomZ +roomWidthZ -2, DQDecorates.DqmBlockTubo);
			world.setBlock(roomX +roomWidthX -3, roomY, roomZ +roomWidthZ -2, DQDecorates.DqmBlockTubo);
			// 「タル」の設置
			world.setBlock(roomX +roomWidthX -2, roomY, roomZ +2, DQDecorates.DqmBlockTaru);
			// 「本棚」の設置×
			world.setBlock(roomX +roomWidthX -2, roomY, roomZ +4, DQDecorates.DqmBlockHondana, 3, 2);
			// 「ヘパイトスのひだね」の設置×
			world.setBlock(roomX +5, roomY +1, roomZ +roomWidthZ -3, DQDecorates.DqmBlockHepaitosu, 3, 2);
			// カウンターとなる「階段」の設置
			for (int z = 2; z <= roomWidthZ -2; z++) {
				world.setBlock(roomX +5, roomY, roomZ +z, Blocks.oak_stairs, 4, 2);
			}

		} else if (roomDirection == 3) {
			/*
			roomDirection == 0
			o,0,1,2,3,4,5,6,7,8,x
			0,_,_,s,_,_,_,s,_,_,
			1,_,w,w,w,d,w,w,w,_,
			2,_,w,_,_,_,_,_,w,_,
			3,_,g,_,_,_,_,_,g,_,
			4,_,g,l,c,_,c,l,g,_,
			5,_,w,s,l,s,s,s,w,_,
			6,_,g,_,_,p,_,w,g,_,
			7,_,g,_,_,_,_,p,g,_,
			8,_,w,b,_,b,_,p,w,_,
			9,_,w,w,w,w,w,w,w,_,
			0,_,_,_,_,_,_,_,_,_,
			z
			*/

			/* 建物外観 */
			// 「ドア」の設置
			world.setBlockToAir(roomX +4, roomY, roomZ +1);
			world.setBlockToAir(roomX +4, roomY +1, roomZ +1);
			ItemDoor.placeDoorBlock(world, roomX +4, roomY, roomZ +1, 1, Blocks.wooden_door);
			// 「看板」の設置
			world.setBlock(roomX +2, roomY +1, roomZ, DQDecorates.DqmBlockBukiya, 0, 2);
			world.setBlock(roomX +roomWidthX -2, roomY +1, roomZ, DQDecorates.DqmBlockBukiya, 0, 2);

			/* 建物内装 */
			// 「小さい椅子」の設置
			world.setBlock(roomX +3, roomY, roomZ +4, DQDecorates.DqmBlockIsu);
			world.setBlock(roomX +roomWidthX -3, roomY, roomZ +4, DQDecorates.DqmBlockIsu);
			// 「キャンドルスタンド」の設置
			world.setBlock(roomX +2, roomY, roomZ +4, DQDecorates.DqmBlockTaimatu2);
			world.setBlock(roomX +roomWidthX -2, roomY, roomZ +4, DQDecorates.DqmBlockTaimatu2);
			// 「薪」の設置
			world.setBlock(roomX +roomWidthX -2, roomY, roomZ +roomWidthZ -4, DQDecorates.DqmBlockMaki, 0, 2);
			// 「ツボ」の設置
			world.setBlock(roomX +roomWidthX -2, roomY, roomZ +roomWidthZ -2, DQDecorates.DqmBlockTubo);
			world.setBlock(roomX +roomWidthX -2, roomY, roomZ +roomWidthZ -3, DQDecorates.DqmBlockTubo);
			// 「タル」の設置
			world.setBlock(roomX +2, roomY, roomZ +roomWidthZ -2, DQDecorates.DqmBlockTaru);
			// 「本棚」の設置
			world.setBlock(roomX +4, roomY, roomZ +roomWidthZ -2, DQDecorates.DqmBlockHondana, 0, 2);
			// 「ヘパイトスのひだね」の設置
			world.setBlock(roomX +3, roomY +1, roomZ +5, DQDecorates.DqmBlockHepaitosu, 0, 2);
			// カウンターとなる「階段」の設置
			for (int x = 2; x <= roomWidthX -2; x++) {
				world.setBlock(roomX +x, roomY, roomZ +5, Blocks.oak_stairs, 6, 2);
			}
		}


		/* - - - - - - - - - -
		 * 以下、敵のスポーン
		 * - - - - - - - - - */

		// 確定スポーン 建物内
		if (playerIsCustomer) {
			// 店員側にNPC、客側にプレイヤー
			switch (roomDirection) {
			case 0:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidthX -6, roomY +1, roomZ +roomCenterZ, LadRoomID.WEAPON_SHOP_CUSTOMER);
				break;
			case 1:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenterX, roomY +1, roomZ  +roomWidthZ -6, LadRoomID.WEAPON_SHOP_CUSTOMER);
				break;
			case 2:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +6, roomY +1, roomZ +roomCenterZ, LadRoomID.WEAPON_SHOP_CUSTOMER);
				break;
			case 3:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenterX, roomY +1, roomZ +6, LadRoomID.WEAPON_SHOP_CUSTOMER);
				break;
			}

			// 実績の取得
			player.triggerAchievement(LadAchievementCore.eventShop);

		} else {
			// 店員側にプレイヤー、客側に敵
			switch (roomDirection) {
			case 0:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidthX -4, roomY +1, roomZ +roomCenterZ, LadRoomID.WEAPON_SHOP_CUSTOMER + LadRoomID.getDifOfRoom());
				break;
			case 1:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenterX, roomY +1, roomZ  +roomWidthZ -4, LadRoomID.WEAPON_SHOP_CUSTOMER + LadRoomID.getDifOfRoom());
				break;
			case 2:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +4, roomY +1, roomZ +roomCenterZ, LadRoomID.WEAPON_SHOP_CUSTOMER + LadRoomID.getDifOfRoom());
				break;
			case 3:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenterX, roomY +1, roomZ +4, LadRoomID.WEAPON_SHOP_CUSTOMER + LadRoomID.getDifOfRoom());
				break;
			}
		}


		// 確率スポーン 建物外
		for (int i = 0; i < 4; i++) {
			if (LadRoomID.getDifOfRoom() >= rand.nextInt(4)) {
				int x = 0, z = 0;

				switch (i) {
				case 0:
					x = roomX;
					z = roomZ +roomCenterZ;
					break;
				case 1:
					x = roomX +roomWidthX;
					z = roomZ +roomCenterZ;
					break;
				case 2:
					x = roomX +roomCenterX;
					z = roomZ;
					break;
				case 3:
					x = roomX +roomCenterX;
					z = roomZ +roomWidthZ;
					break;
				}

				LadSpawnEnemyCore.spawnEnemy(world, player, x, roomY, z, LadRoomID.WEAPON_SHOP + LadRoomID.getDifOfRoom());
			}
		}

		/* - - - - - - - - - -
		 * 以下、報酬
		 * - - - - - - - - - */

		switch (roomDirection) {
		case 0:
			LadDecorationReward.setChest(world, roomX +roomWidthX +3, roomZ +roomCenterZ, roomY);
			break;
		case 1:
			LadDecorationReward.setChest(world, roomX +roomCenterX, roomZ +roomWidthZ +3, roomY);
			break;
		case 2:
			LadDecorationReward.setChest(world, roomX -3, roomZ +roomCenterZ, roomY);
			break;
		case 3:
			LadDecorationReward.setChest(world, roomX +roomCenterX, roomZ -3, roomY);
			break;
		}
	}
}