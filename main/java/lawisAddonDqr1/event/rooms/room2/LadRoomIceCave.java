package lawisAddonDqr1.event.rooms.room2;

import java.util.Random;

import lawisAddonDqr1.api.blocks.LadBlocks;
import lawisAddonDqr1.config.LadDebug;
import lawisAddonDqr1.event.rooms.LadRoomID;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class LadRoomIceCave {
	/*
	 * 氷の洞窟の戦闘部屋
	 *
	 *
	 */
	public static void setRoom(World world, EntityPlayer player) {
		Random rand = new Random();
		int roomDirection = LadRoomID.getDirectionRoom(player, 0);

		int roomX = (int)player.posX;			// 部屋の起点となるX座標
		int roomZ = (int)player.posZ -1;		// 部屋の起点となるZ座標（-1）
		int roomY = (int)player.posY -2;		// 部屋の起点となるY座標（-2）

		int roomHeight = 9;					// 部屋の高さ
		int roomWidth = 20;					// 部屋の幅（-1）
		int roomCenter = roomWidth /2;		// 部屋の中心
		int roomFloorY = -1;					// 地面の高さ

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

		/* 空間 */
		// 「氷塊」の設置
		for (int x = -1; x <= roomWidth +1; x++) {
			for (int z = -1; z <= roomWidth +1; z++) {
				for (int y = -2; y <= roomHeight +1; y++) {
					world.setBlock(roomX +x, roomY +y, roomZ +z, Blocks.packed_ice);
				}
			}
		}

		// 「空気」を設置
		for (int x = 0; x <= roomWidth; x++) {
			for (int z = 0; z <= roomWidth; z++) {
				for (int y = 0; y <= roomHeight; y++) {
					world.setBlockToAir(roomX +x, roomY +y, roomZ +z);
				}
			}
		}

		// 柱となる「氷塊」の設置
		setIcePillar(world, roomX +roomCenter, roomZ +roomCenter, roomY +roomFloorY, roomHeight, false);
		setIcePillar(world, roomX +roomCenter +6, roomZ +roomCenter +6, roomY +roomFloorY, roomHeight, true);
		setIcePillar(world, roomX +roomCenter +6, roomZ +roomCenter -6, roomY +roomFloorY, roomHeight, true);
		setIcePillar(world, roomX +roomCenter -6, roomZ +roomCenter +6, roomY +roomFloorY, roomHeight, true);
		setIcePillar(world, roomX +roomCenter -6, roomZ +roomCenter -6, roomY +roomFloorY, roomHeight, true);


		/* - - - - - - - - - -
		 * 以下、敵のスポーン
		 * - - - - - - - - - */
	}

	/*
	 * 氷の柱を設置するメソッド
	 *
	 * hasInvertedがtrueの時は、天井に近いほど大きい
	 * falseの時は、床に近いほど大きい
	 */
	public static void setIcePillar(World world, int x, int z, int floorY, int height, boolean hasInverted) {
		for (int y = 0; y <= height; y++){
			int i = 0;

			if (hasInverted) {
				if (y == height) i = 3;
				else if (y >= height -2) i = 2;
				else if (y >= height -5) i = 1;
			} else {
				if (y == 0) i = 3;
				else if (y <= 2) i = 2;
				else if (y <= 5) i = 1;
			}

			setBlockCross(world, LadBlocks.ladPackedIce, x, floorY +y +1, z, i, true);
		}
	}


	/*
	 * 十字型(isDiamondがtrueの時は菱形)にブロックを設置するメソッド
	 * sizeは0～3に対応
	 */
	public static void setBlockCross(World world, Block block, int x, int y, int z, int size, boolean isDiamond) {
		switch(size) {
		case 3:
			world.setBlock(x, y, z +3, block);
			world.setBlock(x, y, z -3, block);
			world.setBlock(x +3, y, z, block);
			world.setBlock(x -3, y, z, block);
		case 2:
			world.setBlock(x, y, z +2, block);
			world.setBlock(x, y, z -2, block);
			world.setBlock(x +2, y, z, block);
			world.setBlock(x -2, y, z, block);
		case 1:
			world.setBlock(x, y, z +1, block);
			world.setBlock(x, y, z -1, block);
			world.setBlock(x +1, y, z, block);
			world.setBlock(x -1, y, z, block);
		default:
			world.setBlock(x, y, z, block);
		}

		if (isDiamond) {
			switch(size) {
			case 3:
				world.setBlock(x +1, y, z +2, block);
				world.setBlock(x +1, y, z -2, block);
				world.setBlock(x -1, y, z +2, block);
				world.setBlock(x -1, y, z -2, block);

				world.setBlock(x +2, y, z +1, block);
				world.setBlock(x +2, y, z -1, block);
				world.setBlock(x -2, y, z +1, block);
				world.setBlock(x -2, y, z -1, block);
			case 2:
				world.setBlock(x +1, y, z +1, block);
				world.setBlock(x +1, y, z -1, block);
				world.setBlock(x -1, y, z +1, block);
				world.setBlock(x -1, y, z -1, block);
			}
		}
	}
}
