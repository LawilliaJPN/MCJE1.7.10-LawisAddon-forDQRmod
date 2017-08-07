package lawisAddonDqr1.event.rooms;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class Room13DesertWell {
	/*
	 * バニラの砂漠の井戸をモチーフとした戦闘部屋（村の井戸ではない）
	 */
	public static void setRoomDesertWell(World world, EntityPlayer player, int direction) {
		player.addChatMessage(new ChatComponentTranslation("direction == " + direction));

		int playerX = (int)player.posX;		// プレイヤーのX座標
		int playerY = (int)player.posY;		// プレイヤーのY座標
		int playerZ = (int)player.posZ;		// プレイヤーのZ座標

		int roomX = playerX;					// 部屋の起点となるX座標
		int roomZ = playerZ -1;				// 部屋の起点となるZ座標（何故か-1しないとズレる）
		int roomHeight = 6;					// 部屋の高さ
		int roomWidth = 10;					// 部屋の幅

		// プレイヤーの向きから部屋の起点となる座標を決める
		switch(direction) {
		case 0:
			roomX -= 1;
			roomZ -= 5;
			break;
		case 1:
			roomX -= 5;
			roomZ -= 1;
			break;
		case 2:
			roomX -= 9;
			roomZ -= 5;
			break;
		case 3:
			roomX -= 5;
			roomZ -= 9;
			break;
		}

		/* 地面 */
		// 地面の下に「砂岩ブロック」を敷く（地面となる「砂ブロック」の落下防止のため）
		for (int x = 0; x <= roomWidth; x++) {
			for (int z = 0; z <= roomWidth; z++) {
				world.setBlock(roomX +x, playerY -2, roomZ +z, Blocks.sandstone);
			}
		}

		// 地面に「砂ブロック」を敷く
		for (int x = 0; x <= roomWidth; x++) {
			for (int z = 0; z <= roomWidth; z++) {
				world.setBlock(roomX +x, playerY -1, roomZ +z, Blocks.sand);
			}
		}

		/* 空間 */
		// 「空気」の設置
		for (int x = 0; x <= roomWidth; x++) {
			for (int z = 0; z <= roomWidth; z++) {
				for (int y = 0; y <= roomHeight; y++) {
					world.setBlockToAir(roomX +x, playerY +y, roomZ +z);
				}
			}
		}

		/* 井戸の底 */
		// 井戸の底に「砂岩ブロック」を敷く
		for (int x = 3; x <= roomWidth -3; x++) {
			for (int z = 3; z <= roomWidth -3; z++) {
				for (int y = -1; y <= 0; y++) {
					world.setBlock(roomX +x, playerY +y, roomZ +z, Blocks.sandstone);
				}
			}
		}

		// 井戸の中に「水」を敷く
		world.setBlock(roomX +5, playerY -1, roomZ +5, Blocks.water);
		world.setBlock(roomX +4, playerY -1, roomZ +5, Blocks.water);
		world.setBlock(roomX +5, playerY -1, roomZ +4, Blocks.water);
		world.setBlock(roomX +5, playerY -1, roomZ +roomWidth -4, Blocks.water);
		world.setBlock(roomX +roomWidth -4, playerY -1, roomZ +5, Blocks.water);

		// 井戸の中に「空気」を設置
		world.setBlockToAir(roomX +5, playerY, roomZ +5);
		world.setBlockToAir(roomX +4, playerY, roomZ +5);
		world.setBlockToAir(roomX +5, playerY, roomZ +4);
		world.setBlockToAir(roomX +5, playerY, roomZ +roomWidth -4);
		world.setBlockToAir(roomX +roomWidth -4, playerY, roomZ +5);

		// 井戸の周りに「砂岩ハーフブロック」を敷く
		world.setBlock(roomX +3, playerY, roomZ +5, Blocks.stone_slab, 1, 2);
		world.setBlock(roomX +5, playerY, roomZ +3, Blocks.stone_slab, 1, 2);
		world.setBlock(roomX +roomWidth -3, playerY, roomZ +roomWidth -5, Blocks.stone_slab, 1, 2);
		world.setBlock(roomX +roomWidth -5, playerY, roomZ +roomWidth -3, Blocks.stone_slab, 1, 2);

		/* 井戸の屋根 */
		// 柱となる「砂岩」を設置
		for (int y = 1; y <= 2; y++) {
			world.setBlock(roomX +4, playerY +y, roomZ +4, Blocks.sandstone);
			world.setBlock(roomX +4, playerY +y, roomZ +roomWidth -4, Blocks.sandstone);
			world.setBlock(roomX +roomWidth -4, playerY +y, roomZ +4, Blocks.sandstone);
			world.setBlock(roomX +roomWidth -4, playerY +y, roomZ +roomWidth -4, Blocks.sandstone);
		}

		// 屋根となる「砂岩ハーフブロック」を設置
		for (int x = 4; x <= roomWidth -4; x++) {
			for (int z = 4; z <= roomWidth -4; z++) {
				world.setBlock(roomX +x, playerY +3, roomZ +z, Blocks.stone_slab, 1, 2);
			}
		}

		// 頂点の「砂岩」を設置
		world.setBlock(roomX +5, playerY +3, roomZ +5, Blocks.sandstone);

		/* 光源 */
		// 明るさ確保のための「松明」の設置
		world.setBlock(roomX +3, playerY +1, roomZ +3, Blocks.torch, 5, 3);
		world.setBlock(roomX +3, playerY +1, roomZ +roomWidth -3, Blocks.torch, 5, 3);
		world.setBlock(roomX +roomWidth -3, playerY +1, roomZ +3, Blocks.torch, 5, 3);
		world.setBlock(roomX +roomWidth -3, playerY +1, roomZ +roomWidth -3, Blocks.torch, 5, 3);
	}
}
/* 設計図
o,0,1,2,3,4,5,4,3,2,1,0,x
0,_,_,_,_,_,_,_,_,_,_,_,
1,_,_,_,_,_,1,_,_,_,_,_,
2,_,_,_,_,_,_,_,_,_,_,_,
3,_,_,_,b,b,h,b,b,_,_,_,
4,_,_,_,b,b,w,b,b,_,_,_,
5,_,2,_,h,w,w,w,h,_,0,_,
4,_,_,_,b,b,w,b,b,_,_,_,
3,_,_,_,b,b,h,b,b,_,_,_,
2,_,_,_,_,_,_,_,_,_,_,_,
1,_,_,_,_,_,3,_,_,_,_,_,
0,_,_,_,_,_,_,_,_,_,_,_,
z,
*/