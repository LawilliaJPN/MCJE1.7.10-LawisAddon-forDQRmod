package lawisAddonDqr1.event.rooms;

import dqr.api.Blocks.DQDecorates;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemDoor;
import net.minecraft.world.World;

public class Room12WeaponShop {
	/*
	 * DQRmodの村の武器屋をモチーフとした戦闘部屋
	 */
	public static void setRoomWeaponShop(World world, EntityPlayer player, int direction) {
		// player.addChatMessage(new ChatComponentTranslation("direction == " + direction));

		int playerX = (int)player.posX;		// プレイヤーのX座標
		int playerY = (int)player.posY;		// プレイヤーのY座標
		int playerZ = (int)player.posZ;		// プレイヤーのZ座標

		int roomX = playerX;					// 部屋の起点となるX座標
		int roomZ = playerZ -1;				// 部屋の起点となるZ座標（-1）
		int roomWidthX = 9;					// 部屋のX座標方向の幅
		int roomWidthZ = 9;					// 部屋のZ座標方向の幅
		int roomHeight = 4;					// 部屋の高さ

		// プレイヤーの向きから部屋の起点となる座標を決める
		switch (direction) {
		case 0:
			roomX -= 4;
			roomZ -= 4;
			roomWidthX++;
			roomWidthZ--;
			break;
		case 1:
			roomX -= 4;
			roomZ -= 4;
			roomWidthX--;
			roomWidthZ++;
			break;
		case 2:
			roomX -= 6;
			roomZ -= 4;
			roomWidthX++;
			roomWidthZ--;
			break;
		case 3:
			roomX -= 4;
			roomZ -= 6;
			roomWidthX--;
			roomWidthZ++;
			break;
		}

		/* 地面 */
		// 地面の下に「土ブロック」を敷く
		for (int x = 0; x <= roomWidthX; x++) {
			for (int z = 0; z <= roomWidthZ; z++) {
				world.setBlock(roomX +x, playerY -2, roomZ +z, Blocks.dirt);
			}
		}

		// 地面に「草ブロック」を敷く
		for (int x = 0; x <= roomWidthX; x++) {
			for (int z = 0; z <= roomWidthZ; z++) {
				world.setBlock(roomX +x, playerY -1, roomZ +z, Blocks.grass);
			}
		}

		// 空間の確保のために「空気」を設置する
		for (int x = 0; x <= roomWidthX; x++) {
			for (int z = 0; z <= roomWidthZ; z++) {
				for (int y = 0; y <= roomHeight+1; y++) {
					world.setBlockToAir(roomX +x, playerY +y, roomZ +z);
				}
			}
		}

		// 明るさ確保のために「松明」を設置する
		world.setBlock(roomX, playerY, roomZ, Blocks.torch, 5, 3);
		world.setBlock(roomX +roomWidthX, playerY, roomZ, Blocks.torch, 5, 3);
		world.setBlock(roomX, playerY, roomZ +roomWidthZ, Blocks.torch, 5, 3);
		world.setBlock(roomX +roomWidthX, playerY, roomZ +roomWidthZ, Blocks.torch, 5, 3);


		/* 建物基本 */
		// 床に「レンガブロック」を敷く
		for (int x = 1; x <= roomWidthX -1; x++) {
			for (int z = 1; z <= roomWidthZ -1; z++) {
				world.setBlock(roomX +x, playerY -1, roomZ +z, Blocks.brick_block);
			}
		}

		// 壁の「石レンガ」を設置する（壁の生成1）
		for (int x = 1; x <= roomWidthX -1; x++) {
			for (int y = 0; y <= roomHeight-1; y++) {
				for (int z = 1; z <= roomWidthZ -1; z++) {
					world.setBlock(roomX +x, playerY +y, roomZ +z, Blocks.stonebrick);
				}
			}
		}

		// 「空気」を設置する（壁の生成2）
		for (int x = 2; x <= roomWidthX -2; x++) {
			for (int y = 0; y <= roomHeight-1; y++) {
				for (int z = 2; z <= roomWidthZ -2; z++) {
					world.setBlockToAir(roomX +x, playerY +y, roomZ +z);
				}
			}
		}

		// 屋根の「石のハーフブロック」を設置する
		for (int x = 1; x <= roomWidthX -1; x++) {
			for (int z = 1; z <= roomWidthZ -1; z++) {
				world.setBlock(roomX +x, playerY +roomHeight, roomZ +z, Blocks.stone_slab);
			}
		}


		/* 以下、方向ごとに座標を指定した方が楽なものの設置 */
		if (direction == 0) {
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
			// 「窓ガラス」の設置
			world.setBlock(roomX +3, playerY +1, roomZ +1, Blocks.glass_pane);
			world.setBlock(roomX +4, playerY +1, roomZ +1, Blocks.glass_pane);
			world.setBlock(roomX +roomWidthX -3, playerY +1, roomZ +1, Blocks.glass_pane);
			world.setBlock(roomX +roomWidthX -4, playerY +1, roomZ +1, Blocks.glass_pane);
			world.setBlock(roomX +3, playerY +1, roomZ +roomWidthZ -1, Blocks.glass_pane);
			world.setBlock(roomX +4, playerY +1, roomZ +roomWidthZ -1, Blocks.glass_pane);
			world.setBlock(roomX +roomWidthX -3, playerY +1, roomZ +roomWidthZ -1, Blocks.glass_pane);
			world.setBlock(roomX +roomWidthX -4, playerY +1, roomZ +roomWidthZ -1, Blocks.glass_pane);
			// 「ドア」の設置 2 5
			world.setBlockToAir(roomX +roomWidthX -1, playerY, roomZ +4);
			world.setBlockToAir(roomX +roomWidthX -1, playerY +1, roomZ +4);
			ItemDoor.placeDoorBlock(world, roomX +roomWidthX -1, playerY, roomZ +4, 2, Blocks.wooden_door);
			// 「看板」の設置
			world.setBlock(roomX +roomWidthX, playerY +1, roomZ +2, DQDecorates.DqmBlockBukiya, 1, 2);
			world.setBlock(roomX +roomWidthX, playerY +1, roomZ +roomWidthZ -2, DQDecorates.DqmBlockBukiya, 1, 2);

			/* 建物内装 */
			// 「小さい椅子」の設置
			world.setBlock(roomX +roomWidthX -4, playerY, roomZ +3, DQDecorates.DqmBlockIsu);
			world.setBlock(roomX +roomWidthX -4, playerY, roomZ +roomWidthZ -3, DQDecorates.DqmBlockIsu);
			// 「キャンドルスタンド」の設置
			world.setBlock(roomX +roomWidthX -4, playerY, roomZ +2, DQDecorates.DqmBlockTaimatu2);
			world.setBlock(roomX +roomWidthX -4, playerY, roomZ +roomWidthZ -2, DQDecorates.DqmBlockTaimatu2);
			// 「薪」の設置
			world.setBlock(roomX +4, playerY, roomZ +roomWidthZ -2, DQDecorates.DqmBlockMaki, 1, 2);
			// 「ツボ」の設置
			world.setBlock(roomX +2, playerY, roomZ +roomWidthZ -2, DQDecorates.DqmBlockTubo);
			world.setBlock(roomX +3, playerY, roomZ +roomWidthZ -2, DQDecorates.DqmBlockTubo);
			// 「タル」の設置
			world.setBlock(roomX +2, playerY, roomZ +2, DQDecorates.DqmBlockTaru);
			// 「本棚」の設置
			world.setBlock(roomX +2, playerY, roomZ +4, DQDecorates.DqmBlockHondana, 1, 2);
			// 「ヘパイトスのひだね」の設置
			world.setBlock(roomX +5, playerY +1, roomZ +3, DQDecorates.DqmBlockHepaitosu, 1, 2);
			// カウンターとなる「階段」の設置
			for (int z = 2; z <= roomWidthZ -2; z++) {
				world.setBlock(roomX +5, playerY, roomZ +z, Blocks.oak_stairs, 5, 2);
			}


		} else if (direction == 1) {
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
			// 「窓ガラス」の設置
			world.setBlock(roomX +1, playerY +1, roomZ +3, Blocks.glass_pane);
			world.setBlock(roomX +1, playerY +1, roomZ +4, Blocks.glass_pane);
			world.setBlock(roomX +1, playerY +1, roomZ +roomWidthZ -3, Blocks.glass_pane);
			world.setBlock(roomX +1, playerY +1, roomZ +roomWidthZ -4, Blocks.glass_pane);
			world.setBlock(roomX +roomWidthX -1, playerY +1, roomZ +3, Blocks.glass_pane);
			world.setBlock(roomX +roomWidthX -1, playerY +1, roomZ +4, Blocks.glass_pane);
			world.setBlock(roomX +roomWidthX -1, playerY +1, roomZ +roomWidthZ -3, Blocks.glass_pane);
			world.setBlock(roomX +roomWidthX -1, playerY +1, roomZ +roomWidthZ -4, Blocks.glass_pane);
			// 「ドア」の設置
			world.setBlockToAir(roomX +4, playerY, roomZ +roomWidthZ -1);
			world.setBlockToAir(roomX +4, playerY +1, roomZ +roomWidthZ -1);
			ItemDoor.placeDoorBlock(world, roomX +4, playerY, roomZ +roomWidthZ -1, 3, Blocks.wooden_door);
			// 「看板」の設置
			world.setBlock(roomX +2, playerY +1, roomZ +roomWidthZ, DQDecorates.DqmBlockBukiya, 2, 2);
			world.setBlock(roomX +roomWidthX -2, playerY +1, roomZ +roomWidthZ, DQDecorates.DqmBlockBukiya, 2, 2);

			/* 建物内装 */
			// 「小さい椅子」の設置
			world.setBlock(roomX +3, playerY, roomZ +roomWidthZ -4, DQDecorates.DqmBlockIsu);
			world.setBlock(roomX +roomWidthX -3, playerY, roomZ +roomWidthZ -4, DQDecorates.DqmBlockIsu);
			// 「キャンドルスタンド」の設置
			world.setBlock(roomX +2, playerY, roomZ +roomWidthZ -4, DQDecorates.DqmBlockTaimatu2);
			world.setBlock(roomX +roomWidthX -2, playerY, roomZ +roomWidthZ -4, DQDecorates.DqmBlockTaimatu2);
			// 「薪」の設置
			world.setBlock(roomX +roomWidthX -2, playerY, roomZ +4, DQDecorates.DqmBlockMaki, 0, 2);
			// 「ツボ」の設置
			world.setBlock(roomX +roomWidthX -2, playerY, roomZ +2, DQDecorates.DqmBlockTubo);
			world.setBlock(roomX +roomWidthX -2, playerY, roomZ +3, DQDecorates.DqmBlockTubo);
			// 「タル」の設置
			world.setBlock(roomX +2, playerY, roomZ +2, DQDecorates.DqmBlockTaru);
			// 「本棚」の設置
			world.setBlock(roomX +4, playerY, roomZ +2, DQDecorates.DqmBlockHondana, 2, 2);
			// 「ヘパイトスのひだね」の設置
			world.setBlock(roomX +roomWidthX -3, playerY +1, roomZ +5, DQDecorates.DqmBlockHepaitosu, 2, 2);
			// カウンターとなる「階段」の設置×
			for (int x = 2; x <= roomWidthX -2; x++) {
				world.setBlock(roomX +x, playerY, roomZ +5, Blocks.oak_stairs, 7, 2);
			}


		} else if (direction == 2) {
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
			// 「窓ガラス」の設置
			world.setBlock(roomX +3, playerY +1, roomZ +1, Blocks.glass_pane);
			world.setBlock(roomX +4, playerY +1, roomZ +1, Blocks.glass_pane);
			world.setBlock(roomX +roomWidthX -3, playerY +1, roomZ +1, Blocks.glass_pane);
			world.setBlock(roomX +roomWidthX -4, playerY +1, roomZ +1, Blocks.glass_pane);
			world.setBlock(roomX +3, playerY +1, roomZ +roomWidthZ -1, Blocks.glass_pane);
			world.setBlock(roomX +4, playerY +1, roomZ +roomWidthZ -1, Blocks.glass_pane);
			world.setBlock(roomX +roomWidthX -3, playerY +1, roomZ +roomWidthZ -1, Blocks.glass_pane);
			world.setBlock(roomX +roomWidthX -4, playerY +1, roomZ +roomWidthZ -1, Blocks.glass_pane);
			// 「ドア」の設置
			world.setBlockToAir(roomX +1, playerY, roomZ +4);
			world.setBlockToAir(roomX +1, playerY +1, roomZ +4);
			ItemDoor.placeDoorBlock(world, roomX +1, playerY, roomZ +4, 0, Blocks.wooden_door);
			// 「看板」の設置
			world.setBlock(roomX, playerY +1, roomZ +2, DQDecorates.DqmBlockBukiya, 3, 2);
			world.setBlock(roomX, playerY +1, roomZ +roomWidthZ -2, DQDecorates.DqmBlockBukiya, 3, 2);

			/* 建物内装 */
			// 「小さい椅子」の設置
			world.setBlock(roomX +4, playerY, roomZ +3, DQDecorates.DqmBlockIsu);
			world.setBlock(roomX +4, playerY, roomZ +roomWidthZ -3, DQDecorates.DqmBlockIsu);
			// 「キャンドルスタンド」の設置
			world.setBlock(roomX +4, playerY, roomZ +2, DQDecorates.DqmBlockTaimatu2);
			world.setBlock(roomX +4, playerY, roomZ +roomWidthZ -2, DQDecorates.DqmBlockTaimatu2);
			// 「薪」の設置
			world.setBlock(roomX +roomWidthX -4, playerY, roomZ +roomWidthZ -2, DQDecorates.DqmBlockMaki, 1, 2);
			// 「ツボ」の設置
			world.setBlock(roomX +roomWidthX -2, playerY, roomZ +roomWidthZ -2, DQDecorates.DqmBlockTubo);
			world.setBlock(roomX +roomWidthX -3, playerY, roomZ +roomWidthZ -2, DQDecorates.DqmBlockTubo);
			// 「タル」の設置
			world.setBlock(roomX +roomWidthX -2, playerY, roomZ +2, DQDecorates.DqmBlockTaru);
			// 「本棚」の設置×
			world.setBlock(roomX +roomWidthX -2, playerY, roomZ +4, DQDecorates.DqmBlockHondana, 3, 2);
			// 「ヘパイトスのひだね」の設置×
			world.setBlock(roomX +5, playerY +1, roomZ +roomWidthZ -3, DQDecorates.DqmBlockHepaitosu, 3, 2);
			// カウンターとなる「階段」の設置
			for (int z = 2; z <= roomWidthZ -2; z++) {
				world.setBlock(roomX +5, playerY, roomZ +z, Blocks.oak_stairs, 4, 2);
			}

		} else if (direction == 3) {
			/*
			direction == 0
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
			// 「窓ガラス」の設置
			world.setBlock(roomX +1, playerY +1, roomZ +3, Blocks.glass_pane);
			world.setBlock(roomX +1, playerY +1, roomZ +4, Blocks.glass_pane);
			world.setBlock(roomX +1, playerY +1, roomZ +roomWidthZ -3, Blocks.glass_pane);
			world.setBlock(roomX +1, playerY +1, roomZ +roomWidthZ -4, Blocks.glass_pane);
			world.setBlock(roomX +roomWidthX -1, playerY +1, roomZ +3, Blocks.glass_pane);
			world.setBlock(roomX +roomWidthX -1, playerY +1, roomZ +4, Blocks.glass_pane);
			world.setBlock(roomX +roomWidthX -1, playerY +1, roomZ +roomWidthZ -3, Blocks.glass_pane);
			world.setBlock(roomX +roomWidthX -1, playerY +1, roomZ +roomWidthZ -4, Blocks.glass_pane);
			// 「ドア」の設置 1
			world.setBlockToAir(roomX +4, playerY, roomZ +1);
			world.setBlockToAir(roomX +4, playerY +1, roomZ +1);
			ItemDoor.placeDoorBlock(world, roomX +4, playerY, roomZ +1, 1, Blocks.wooden_door);
			// 「看板」の設置
			world.setBlock(roomX +2, playerY +1, roomZ, DQDecorates.DqmBlockBukiya, 0, 2);
			world.setBlock(roomX +roomWidthX -2, playerY +1, roomZ, DQDecorates.DqmBlockBukiya, 0, 2);

			/* 建物内装 */
			// 「小さい椅子」の設置
			world.setBlock(roomX +3, playerY, roomZ +4, DQDecorates.DqmBlockIsu);
			world.setBlock(roomX +roomWidthX -3, playerY, roomZ +4, DQDecorates.DqmBlockIsu);
			// 「キャンドルスタンド」の設置
			world.setBlock(roomX +2, playerY, roomZ +4, DQDecorates.DqmBlockTaimatu2);
			world.setBlock(roomX +roomWidthX -2, playerY, roomZ +4, DQDecorates.DqmBlockTaimatu2);
			// 「薪」の設置
			world.setBlock(roomX +roomWidthX -2, playerY, roomZ +roomWidthZ -4, DQDecorates.DqmBlockMaki, 0, 2);
			// 「ツボ」の設置
			world.setBlock(roomX +roomWidthX -2, playerY, roomZ +roomWidthZ -2, DQDecorates.DqmBlockTubo);
			world.setBlock(roomX +roomWidthX -2, playerY, roomZ +roomWidthZ -3, DQDecorates.DqmBlockTubo);
			// 「タル」の設置
			world.setBlock(roomX +2, playerY, roomZ +roomWidthZ -2, DQDecorates.DqmBlockTaru);
			// 「本棚」の設置
			world.setBlock(roomX +4, playerY, roomZ +roomWidthZ -2, DQDecorates.DqmBlockHondana, 0, 2);
			// 「ヘパイトスのひだね」の設置
			world.setBlock(roomX +3, playerY +1, roomZ +5, DQDecorates.DqmBlockHepaitosu, 0, 2);
			// カウンターとなる「階段」の設置
			for (int x = 2; x <= roomWidthX -2; x++) {
				world.setBlock(roomX +x, playerY, roomZ +5, Blocks.oak_stairs, 6, 2);
			}
		}

	}
}