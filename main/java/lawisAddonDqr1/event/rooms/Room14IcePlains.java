package lawisAddonDqr1.event.rooms;

import lawisAddonDqr1.config.LadDebug;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class Room14IcePlains {
	/*
	 * 氷原の戦闘部屋
	 */
	public static void setRoomIcePlains(World world, EntityPlayer player, int direction) {
		int roomX = (int)player.posX;			// 部屋の起点となるX座標
		int roomZ = (int)player.posZ -1;		// 部屋の起点となるZ座標（-1）
		int roomY = (int)player.posY -1;		// 部屋の起点となるY座標（-1）

		int roomHeight = 5;					// 部屋の高さ
		int roomWidth = 12;					// 部屋の幅（-1）
		int roomCenter = roomWidth/2;			// 部屋の中心

		// [Debug] 戦闘部屋固定時に生成方向がチャット表示される（デバッグ用）
		if (LadDebug.getDebugRoom() >=0) {
			player.addChatMessage(new ChatComponentTranslation("direction == " + direction));
		}

		// プレイヤーの向きから部屋の起点となる座標を決める
		switch (direction) {
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

		switch (direction) {
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

		// 中央の床となる「氷塊」を設置する
		for (int x = 5; x <= roomWidth -5; x++) {
			for (int z = 5; z <= roomWidth -5; z++) {
				world.setBlock(roomX +x, roomY -1, roomZ +z, Blocks.packed_ice);
			}
		}

		// 柱となる「氷塊」を設置する
		for (int y = 0; y <= roomHeight; y++) {
			world.setBlock(roomX +4, roomY +y, roomZ +4, Blocks.packed_ice);
			world.setBlock(roomX +4, roomY +y, roomZ +roomWidth -4, Blocks.packed_ice);
			world.setBlock(roomX +roomWidth -4, roomY +y, roomZ +4, Blocks.packed_ice);
			world.setBlock(roomX +roomWidth -4, roomY +y, roomZ +roomWidth -4, Blocks.packed_ice);
		}
		for (int x = 4; x <= roomWidth -4; x += 4) {
			for (int z = 4; z <= roomWidth -4; z += 4) {
				world.setBlock(roomX +x, roomY +3, roomZ +z +1, Blocks.packed_ice);
				world.setBlock(roomX +x, roomY +3, roomZ +z -1, Blocks.packed_ice);
				world.setBlock(roomX +x +1, roomY +3, roomZ +z, Blocks.packed_ice);
				world.setBlock(roomX +x -1, roomY +3, roomZ +z, Blocks.packed_ice);
			}
		}
		for (int x = 3; x <= roomWidth -3; x += 2) {
			for (int z = 3; z <= roomWidth -3; z += 2) {
				world.setBlock(roomX +x, roomY +4, roomZ +z, Blocks.packed_ice);
			}
		}
		world.setBlock(roomX +roomCenter, roomY +3, roomZ +roomCenter, Blocks.packed_ice);
		world.setBlock(roomX +roomCenter, roomY +5, roomZ +roomCenter, Blocks.packed_ice);

		/* 光源 */
		// 明るさ確保のための「松明」の設置
		world.setBlock(roomX +roomCenter, roomY +4, roomZ +roomCenter, Blocks.torch, 5, 3);
		switch (direction) {
		case 0:
		case 2:
			world.setBlock(roomX +4, roomY +4, roomZ +3, Blocks.torch, 4, 3);
			world.setBlock(roomX +4, roomY +4, roomZ +roomWidth -3, Blocks.torch, 3, 3);
			world.setBlock(roomX +roomWidth -4, roomY +4, roomZ +3, Blocks.torch, 4, 3);
			world.setBlock(roomX +roomWidth -4, roomY +4, roomZ +roomWidth -3, Blocks.torch, 3, 3);
			break;
		case 1:
		case 3:
			world.setBlock(roomX +3, roomY +4, roomZ +4, Blocks.torch, 2, 3);
			world.setBlock(roomX +3, roomY +4, roomZ +roomWidth -4, Blocks.torch, 2, 3);
			world.setBlock(roomX +roomWidth -3, roomY +4, roomZ +4, Blocks.torch, 1, 3);
			world.setBlock(roomX +roomWidth -3, roomY +4, roomZ +roomWidth -4, Blocks.torch, 1, 3);
			break;
		}

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