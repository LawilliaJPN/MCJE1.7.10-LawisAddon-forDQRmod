package lawisAddonDqr1.event.rooms;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class Room11GrassWell {
	public Room11GrassWell(World world, Block block, EntityPlayer player, int direction) {
		int playerX = (int)player.posX;				// プレイヤーのX座標
		int playerY = (int)player.posY;				// プレイヤーのY座標
		int playerZ = (int)player.posZ;				// プレイヤーのZ座標

		int roomX = playerX;
		int roomZ = playerZ;
		int roomHeight = 6;
		int roomWidth = 12;

		switch(direction){
		case 0:
			roomX -= 10;
			roomZ -= 1;
			break;
		case 1:
			roomX -= 1;
			roomZ -= 1;
			break;
		case 2:
			roomX -= 1;
			roomZ -= 10;
			break;
		case 3:
			roomX -= 10;
			roomZ -= 10;
			break;
		}

		/* 地面 */
		// 地面の下に土ブロックを敷く
		for (int x = 0; x < roomWidth; x++) {
			for (int z = 0; z < roomWidth; z++) {
				world.setBlock(roomX +x, playerY -2, roomZ +z, Blocks.dirt);
			}
		}

		// 地面に草ブロックを敷く
		for (int x = 0; x < roomWidth; x++) {
			for (int z = 0; z < roomWidth; z++) {
				world.setBlock(roomX +x, playerY -1, roomZ +z, Blocks.grass);
			}
		}

		// 地面に砂利ブロックを敷く
		for (int x = 0; x < roomWidth; x++) {
			for (int z = 5; z <= 6; z++) {
				world.setBlock(roomX +x, playerY -1, roomZ +z, Blocks.gravel);
			}
		}
		for (int z = 0; z < roomWidth; z++) {
			for (int x = 5; x <= 6; x++) {
				world.setBlock(roomX +x, playerY -1, roomZ +z, Blocks.gravel);
			}
		}

		/* 空気ブロック */
		// 空間を作る
		for (int x = 0; x < roomWidth; x++) {
			for (int y = 0; y < roomHeight; y++) {
				for (int z = 0; z < roomWidth; z++) {
					world.setBlockToAir(roomX +x, playerY +y, roomZ +z);
				}
			}
		}

		/* 井戸 */
		// 砂利ブロックの設置
		for (int x = 3; x <= 8; x++) {
			for (int z = 3; z <= 8; z++) {
				world.setBlock(roomX +x, playerY, roomZ +z, Blocks.gravel);
			}
		}

		// 丸石の設置
		for (int x = 4; x <= 7; x++) {
			for (int y = 0; y <= 1; y++) {
				for (int z = 4; z <= 7; z++) {
					world.setBlock(roomX +x, playerY +y, roomZ +z, Blocks.cobblestone);
				}
			}
		}

		// 水の設置
		for (int x = 5; x <= 6; x++) {
			for (int z = 5; z <= 6; z++) {
				world.setBlock(roomX +x, playerY, roomZ +z, Blocks.water);
			}
		}

		// 空気の設置
		for (int x = 5; x <= 6; x++) {
			for (int z = 5; z <= 6; z++) {
				world.setBlockToAir(roomX +x, playerY + 1, roomZ +z);
			}
		}

		// フェンスの設置
		world.setBlock(roomX +4, playerY + 2, roomZ +4, Blocks.fence);
		world.setBlock(roomX +4, playerY + 3, roomZ +4, Blocks.fence);
		world.setBlock(roomX +4, playerY + 2, roomZ +7, Blocks.fence);
		world.setBlock(roomX +4, playerY + 3, roomZ +7, Blocks.fence);
		world.setBlock(roomX +7, playerY + 2, roomZ +4, Blocks.fence);
		world.setBlock(roomX +7, playerY + 3, roomZ +4, Blocks.fence);
		world.setBlock(roomX +7, playerY + 2, roomZ +7, Blocks.fence);
		world.setBlock(roomX +7, playerY + 3, roomZ +7, Blocks.fence);

		// 丸石の設置
		for (int x = 4; x <= 7; x++) {
			for (int z = 4; z <= 7; z++) {
				world.setBlock(roomX +x, playerY +4, roomZ +z, Blocks.cobblestone);
			}
		}

		/* 光源 */
		// 松明の設置
		world.setBlock(roomX +2, playerY, roomZ +2, Blocks.torch, 5, 3);
		world.setBlock(roomX +9, playerY, roomZ +2, Blocks.torch, 5, 3);
		world.setBlock(roomX +2, playerY, roomZ +9, Blocks.torch, 5, 3);
		world.setBlock(roomX +9, playerY, roomZ +9, Blocks.torch, 5, 3);
	}
}
/* 設計図
o,0,1,2,3,4,5,6,7,8,9,0,1,x
0,_,_,_,_,_,g,g,_,_,_,_,_
1,_,0,_,_,_,g,g,_,_,_,1,_
2,_,_,t,_,_,g,g,_,_,t,_,_
3,_,_,_,g,g,g,g,g,g,_,_,_
4,_,_,_,g,c,c,c,c,g,_,_,_
5,g,g,g,g,c,w,w,c,g,g,g,g
6,g,g,g,g,c,w,w,c,g,g,g,g
7,_,_,_,g,c,c,c,c,g,_,_,_
8,_,_,_,g,g,g,g,g,g,_,_,_
9,_,_,t,_,_,g,g,_,_,t,_,_
0,_,2,_,_,_,g,g,_,_,_,3,_
1,_,_,_,_,_,g,g,_,_,_,_,_
z
*/