package lawisAddonDqr1.event.rooms;

import dqr.api.Blocks.DQDecorates;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemDoor;
import net.minecraft.world.World;

public class Room12WeaponShop {
	public Room12WeaponShop(World world, Block block, EntityPlayer player, int direction) {
		int playerX = (int)player.posX;				// プレイヤーのX座標
		int playerY = (int)player.posY;				// プレイヤーのY座標
		int playerZ = (int)player.posZ;				// プレイヤーのZ座標

		int roomX = playerX;
		int roomZ = playerZ;
		int roomWidthX = 9;
		int roomWidthZ = 9;
		int roomHeight = 4;

		switch(direction){
		case 0:
			roomX -= 6;
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
			roomX -= 4;
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
		// 地面の下に土ブロックを敷く
		for (int x = 0; x <= roomWidthX; x++) {
			for (int z = 0; z <= roomWidthZ; z++) {
				world.setBlock(roomX +x, playerY -2, roomZ +z, Blocks.dirt);
			}
		}

		// 地面に草ブロックを敷く
		for (int x = 0; x <= roomWidthX; x++) {
			for (int z = 0; z <= roomWidthZ; z++) {
				world.setBlock(roomX +x, playerY -1, roomZ +z, Blocks.grass);
			}
		}

		// 空気を設置する
		for (int x = 0; x <= roomWidthX; x++) {
			for (int y = 0; y <= roomHeight+1; y++) {
				for (int z = 0; z <= roomWidthZ; z++) {
					world.setBlockToAir(roomX +x, playerY +y, roomZ +z);
				}
			}
		}

		// 松明を設置する
		world.setBlock(roomX, playerY, roomZ, Blocks.torch, 5, 3);
		world.setBlock(roomX + roomWidthX, playerY, roomZ, Blocks.torch, 5, 3);
		world.setBlock(roomX, playerY, roomZ + roomWidthZ, Blocks.torch, 5, 3);
		world.setBlock(roomX + roomWidthX, playerY, roomZ + roomWidthZ, Blocks.torch, 5, 3);


		/* 建物基本 */
		// 床にレンガブロックを敷く
		for (int x = 1; x <= roomWidthX -1; x++) {
			for (int z = 1; z <= roomWidthZ -1; z++) {
				world.setBlock(roomX +x, playerY -1, roomZ +z, Blocks.brick_block);
			}
		}

		// 壁の石レンガを設置する
		for (int x = 1; x <= roomWidthX -1; x++) {
			for (int y = 0; y <= roomHeight-1; y++) {
				for (int z = 1; z <= roomWidthZ -1; z++) {
					world.setBlock(roomX +x, playerY +y, roomZ +z, Blocks.stonebrick);
				}
			}
		}

		// 空気を設置する
		for (int x = 2; x <= roomWidthX -2; x++) {
			for (int y = 0; y <= roomHeight-1; y++) {
				for (int z = 2; z <= roomWidthZ -2; z++) {
					world.setBlockToAir(roomX +x, playerY +y, roomZ +z);
				}
			}
		}

		// 屋根の石のハーフブロックを設置する
		for (int x = 1; x <= roomWidthX -1; x++) {
			for (int z = 1; z <= roomWidthZ -1; z++) {
				world.setBlock(roomX +x, playerY +roomHeight, roomZ +z, Blocks.stone_slab);
			}
		}

		if (direction == 0) {
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
			// 窓ガラスの設置
			world.setBlock(roomX +3, playerY +1, roomZ +1, Blocks.glass_pane);
			world.setBlock(roomX +4, playerY +1, roomZ +1, Blocks.glass_pane);
			world.setBlock(roomX +6, playerY +1, roomZ +1, Blocks.glass_pane);
			world.setBlock(roomX +7, playerY +1, roomZ +1, Blocks.glass_pane);
			world.setBlock(roomX +3, playerY +1, roomZ +roomWidthZ -1, Blocks.glass_pane);
			world.setBlock(roomX +4, playerY +1, roomZ +roomWidthZ -1, Blocks.glass_pane);
			world.setBlock(roomX +6, playerY +1, roomZ +roomWidthZ -1, Blocks.glass_pane);
			world.setBlock(roomX +7, playerY +1, roomZ +roomWidthZ -1, Blocks.glass_pane);

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
			// 窓ガラスの設置
			world.setBlock(roomX +1, playerY +1, roomZ +3, Blocks.glass_pane);
			world.setBlock(roomX +1, playerY +1, roomZ +4, Blocks.glass_pane);
			world.setBlock(roomX +1, playerY +1, roomZ +6, Blocks.glass_pane);
			world.setBlock(roomX +1, playerY +1, roomZ +7, Blocks.glass_pane);
			world.setBlock(roomX +roomWidthX -1, playerY +1, roomZ +3, Blocks.glass_pane);
			world.setBlock(roomX +roomWidthX -1, playerY +1, roomZ +4, Blocks.glass_pane);
			world.setBlock(roomX +roomWidthX -1, playerY +1, roomZ +6, Blocks.glass_pane);
			world.setBlock(roomX +roomWidthX -1, playerY +1, roomZ +7, Blocks.glass_pane);

		} else if (direction == 2) {
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
			// 窓ガラスの設置
			world.setBlock(roomX +3, playerY +1, roomZ +1, Blocks.glass_pane);
			world.setBlock(roomX +4, playerY +1, roomZ +1, Blocks.glass_pane);
			world.setBlock(roomX +6, playerY +1, roomZ +1, Blocks.glass_pane);
			world.setBlock(roomX +7, playerY +1, roomZ +1, Blocks.glass_pane);
			world.setBlock(roomX +3, playerY +1, roomZ +roomWidthZ -1, Blocks.glass_pane);
			world.setBlock(roomX +4, playerY +1, roomZ +roomWidthZ -1, Blocks.glass_pane);
			world.setBlock(roomX +6, playerY +1, roomZ +roomWidthZ -1, Blocks.glass_pane);
			world.setBlock(roomX +7, playerY +1, roomZ +roomWidthZ -1, Blocks.glass_pane);

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
			// 窓ガラスの設置
			world.setBlock(roomX +1, playerY +1, roomZ +3, Blocks.glass_pane);
			world.setBlock(roomX +1, playerY +1, roomZ +4, Blocks.glass_pane);
			world.setBlock(roomX +1, playerY +1, roomZ +6, Blocks.glass_pane);
			world.setBlock(roomX +1, playerY +1, roomZ +7, Blocks.glass_pane);
			world.setBlock(roomX +roomWidthX -1, playerY +1, roomZ +3, Blocks.glass_pane);
			world.setBlock(roomX +roomWidthX -1, playerY +1, roomZ +4, Blocks.glass_pane);
			world.setBlock(roomX +roomWidthX -1, playerY +1, roomZ +6, Blocks.glass_pane);
			world.setBlock(roomX +roomWidthX -1, playerY +1, roomZ +7, Blocks.glass_pane);

			// ドアの設置⇒失敗
			world.setBlockToAir(roomX +4, playerY, roomZ +1);
			world.setBlockToAir(roomX +4, playerY+1, roomZ +1);
			ItemDoor.placeDoorBlock(world, roomX+4, playerY, roomZ +1, 4, Blocks.wooden_door);
			// 看板の設置
			world.setBlock(roomX +2, playerY +1, roomZ, DQDecorates.DqmBlockBukiya, 0, 2);
			world.setBlock(roomX +roomWidthX -2, playerY +1, roomZ, DQDecorates.DqmBlockBukiya, 0, 2);


			/* 建物内装 */
			// 小さい椅子
			world.setBlock(roomX +3, playerY, roomZ +4, DQDecorates.DqmBlockIsu);
			world.setBlock(roomX +roomWidthX -3, playerY, roomZ +4, DQDecorates.DqmBlockIsu);
			// キャンドルスタンド
			world.setBlock(roomX +2, playerY, roomZ +4, DQDecorates.DqmBlockTaimatu2);
			world.setBlock(roomX +roomWidthX -2, playerY, roomZ +4, DQDecorates.DqmBlockTaimatu2);
			// 薪 (d==0||3 >meta=0, d==1||2 >meta=1)
			world.setBlock(roomX +roomWidthX -2, playerY, roomZ +6, DQDecorates.DqmBlockMaki, 0, 2);
			// ツボ
			world.setBlock(roomX +roomWidthX -2, playerY, roomZ +7, DQDecorates.DqmBlockTubo);
			world.setBlock(roomX +roomWidthX -2, playerY, roomZ +8, DQDecorates.DqmBlockTubo);
			// タル
			world.setBlock(roomX +2, playerY, roomZ +8, DQDecorates.DqmBlockTaru);
			// 本棚
			world.setBlock(roomX +4, playerY, roomZ +8, DQDecorates.DqmBlockHondana, 0, 2);
			// ヘパイトスのひだね
			world.setBlock(roomX +3, playerY +1, roomZ +5, DQDecorates.DqmBlockHepaitosu, 0, 2);
			// 階段
			for (int x = 2; x <= roomWidthX -2; x++) {
				world.setBlock(roomX +x, playerY, roomZ +5, Blocks.oak_stairs, 6, 2);
			}
		}

	}
}