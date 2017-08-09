package lawisAddonDqr1.event.rooms;

import lawisAddonDqr1.config.LadDebug;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class Room11GrassWell {
	/*
	 * バニラの村の井戸をモチーフとした戦闘部屋
	 */
	public static void setRoomGrassWell(World world, EntityPlayer player, int direction, boolean isCursed){
		int roomX = (int)player.posX;			// 部屋の起点となるX座標
		int roomZ = (int)player.posZ -1;		// 部屋の起点となるZ座標（-1）
		int roomY = (int)player.posY;			// 部屋の起点となるY座標
		int roomHeight = 6;					// 部屋の高さ
		int roomWidth = 11;					// 部屋の幅（-1）

		// [Debug] 戦闘部屋固定時に生成方向がチャット表示される（デバッグ用）
		if (LadDebug.getDebugRoom() >=0) {
			player.addChatMessage(new ChatComponentTranslation("direction == " + direction));
		}

		// プレイヤーの向きから部屋の起点となる座標を決める
		switch (direction) {
		case 0:
			roomX -= 1;
			roomZ -= roomWidth -1;
			break;
		case 1:
			roomX -= 1;
			roomZ -= 1;
			break;
		case 2:
			roomX -= roomWidth -1;
			roomZ -= 1;
			break;
		case 3:
			roomX -= roomWidth -1;
			roomZ -= roomWidth -1;
			break;
		}

		/* 地面 */
		// 地面の下に「土ブロック」を敷く（地面となる「砂利ブロック」の落下防止のため）
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

		// 地面に「砂利ブロック」を敷く
		for (int x = 0; x <= roomWidth; x++) {
			for (int z = 5; z <= roomWidth -5; z++) {
				world.setBlock(roomX +x, roomY -1, roomZ +z, Blocks.gravel);
			}
		}
		for (int z = 0; z <= roomWidth; z++) {
			for (int x = 5; x <= roomWidth -5; x++) {
				world.setBlock(roomX +x, roomY -1, roomZ +z, Blocks.gravel);
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

		/* 井戸 */
		// 井戸の周りの「砂利ブロック」の設置
		for (int x = 3; x <= roomWidth -3; x++) {
			for (int z = 3; z <= roomWidth -3; z++) {
				world.setBlock(roomX +x, roomY, roomZ +z, Blocks.gravel);
			}
		}

		// 井戸の周りの「丸石」の設置
		for (int x = 4; x <= roomWidth -4; x++) {
			for (int y = 0; y <= 1; y++) {
				for (int z = 4; z <= roomWidth -4; z++) {
					world.setBlock(roomX +x, roomY +y, roomZ +z, Blocks.cobblestone);
				}
			}
		}

		// 井戸の中の「水」の設置
		for (int x = 5; x <= roomWidth -5; x++) {
			for (int z = 5; z <= roomWidth -5; z++) {
				world.setBlock(roomX +x, roomY, roomZ +z, Blocks.water);
			}
		}

		// 井戸の中の「空気」の設置
		for (int x = 5; x <= roomWidth -5; x++) {
			for (int z = 5; z <= roomWidth -5; z++) {
				world.setBlockToAir(roomX +x, roomY +1, roomZ +z);
			}
		}

		// 「フェンス」の設置
		world.setBlock(roomX +4, roomY +2, roomZ +4, Blocks.fence);
		world.setBlock(roomX +4, roomY +3, roomZ +4, Blocks.fence);
		world.setBlock(roomX +4, roomY +2, roomZ +roomWidth -4, Blocks.fence);
		world.setBlock(roomX +4, roomY +3, roomZ +roomWidth -4, Blocks.fence);
		world.setBlock(roomX +roomWidth -4, roomY +2, roomZ +4, Blocks.fence);
		world.setBlock(roomX +roomWidth -4, roomY +3, roomZ +4, Blocks.fence);
		world.setBlock(roomX +roomWidth -4, roomY +2, roomZ +roomWidth -4, Blocks.fence);
		world.setBlock(roomX +roomWidth -4, roomY +3, roomZ +roomWidth -4, Blocks.fence);

		// 屋根の「丸石」の設置
		for (int x = 4; x <= roomWidth -4; x++) {
			for (int z = 4; z <= roomWidth -4; z++) {
				world.setBlock(roomX +x, roomY +4, roomZ +z, Blocks.cobblestone);
			}
		}

		/* 光源 */
		// 明るさ確保のための「松明」の設置
		world.setBlock(roomX +2, roomY, roomZ +2, Blocks.torch, 5, 3);
		world.setBlock(roomX +roomWidth -2, roomY, roomZ +2, Blocks.torch, 5, 3);
		world.setBlock(roomX +2, roomY, roomZ +roomWidth -2, Blocks.torch, 5, 3);
		world.setBlock(roomX +roomWidth -2, roomY, roomZ +roomWidth -2, Blocks.torch, 5, 3);

	}
}
/* 設計図
o,0,1,2,3,4,5,6,7,8,9,0,1,x
0,_,_,_,_,_,g,g,_,_,_,_,_
1,_,3,_,_,_,g,g,_,_,_,2,_
2,_,_,t,_,_,g,g,_,_,t,_,_
3,_,_,_,g,g,g,g,g,g,_,_,_
4,_,_,_,g,c,c,c,c,g,_,_,_
5,g,g,g,g,c,w,w,c,g,g,g,g
6,g,g,g,g,c,w,w,c,g,g,g,g
7,_,_,_,g,c,c,c,c,g,_,_,_
8,_,_,_,g,g,g,g,g,g,_,_,_
9,_,_,t,_,_,g,g,_,_,t,_,_
0,_,0,_,_,_,g,g,_,_,_,1,_
1,_,_,_,_,_,g,g,_,_,_,_,_
z
*/
