package lawisAddonDqr1.event.rooms.room3;

import java.util.Random;

import lawisAddonDqr1.event.rooms.LadRoomID;
import lawisAddonDqr1.event.rooms.decoration.LadDecorationCross;
import lawisAddonDqr1.event.rooms.decoration.LadDecorationDeteriorated;
import lawisAddonDqr1.event.rooms.decoration.LadDecorationPillar;
import lawisAddonDqr1.event.rooms.decoration.LadFillBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class LadRoomEndPortal {
	/*
	 * 動画の番外編で製作した特殊な戦闘部屋(下層)
	 */
	public static void setRoom(World world, EntityPlayer player) {
		Random rand = new Random();
		int roomDirection = LadRoomID.getDirectionRoom(player, 0);
		boolean isRoomDirection02 = false;

		int roomX = (int)player.posX;			// 部屋の起点となるX座標
		int roomZ = (int)player.posZ -1;		// 部屋の起点となるZ座標（-1）
		int roomY = (int)player.posY -2;		// 部屋の起点となるY座標（-2）

		int roomHeight = 6;					// 部屋の高さ
		int roomWidthX = 20;					// 部屋の幅（-1）
		int roomWidthZ = 20;					// 部屋の幅（-1）
		int roomCenterX = roomWidthX /2;		// 部屋の中心
		int roomCenterZ = roomWidthZ /2;		// 部屋の中心

		// マイナス座標の時に、部屋の位置がズレることの修正
		if (player.posX < 0) roomX -=1;
		if (player.posZ < 0) roomZ -=1;

		// プレイヤーの向きから部屋の起点となる座標を決める
		switch (roomDirection) {
		case 0:
			roomX -= 2;
			roomZ -= roomCenterZ;
			isRoomDirection02 = true;
			break;
		case 1:
			roomX -= roomCenterX;
			roomZ -= 2;
			break;
		case 2:
			roomX -= roomWidthX -2;
			roomZ -= roomCenterZ;
			isRoomDirection02 = true;
			break;
		case 3:
			roomX -= roomCenterX;
			roomZ -= roomWidthZ -2;
			break;
		}

		/* - - - - - - - - - -
		 * 以下、TODO 部屋の生成
		 * - - - - - - - - - */

		// 床・壁・天井・中の空気
		LadDecorationDeteriorated.fillStoneBrickXZ(world, roomX, roomX +roomWidthX, roomZ, roomZ +roomWidthZ, roomY -1);
		LadDecorationDeteriorated.setStoneBrickWall(world, roomX -1, roomX +roomWidthX +1, roomZ -1, roomZ +roomWidthZ +1, roomY -1, roomY +roomHeight);
		LadDecorationDeteriorated.fillStoneBrickXZ(world, roomX, roomX +roomWidthX, roomZ, roomZ +roomWidthZ, roomY +roomHeight);
		LadFillBlock.fillBlockToAir(world, roomX, roomX +roomWidthX, roomZ, roomZ +roomWidthZ, roomY, roomY +roomHeight -1);

		setEndPortal(world, roomX +roomCenterX, roomY, roomZ +roomCenterZ);
		setLavaX1Z3(world, roomX, roomY, roomZ +1);
		setLavaX1Z3(world, roomX +roomWidthX, roomY, roomZ +roomWidthZ -1);
		setLavaX3Z1(world, roomX +roomWidthX -1, roomY, roomZ);
		setLavaX3Z1(world, roomX +1, roomY, roomZ +roomWidthZ);

		setStoneBrickStairs(world, roomX +roomCenterX -3, roomY, roomZ +roomCenterZ, 0);
		setStoneBrickStairs(world, roomX +roomCenterX +3, roomY, roomZ +roomCenterZ, 1);

		setStoneBrickStairs(world, roomX +roomCenterX, roomY, roomZ +roomCenterZ -3, 2);
		setStoneBrickStairs(world, roomX +roomCenterX, roomY, roomZ +roomCenterZ +3, 3);

		/* - - - - - - - - - -
		 * 以下、TODO 敵のスポーン
		 * - - - - - - - - - */

		/* - - - - - - - - - -
		 * 以下、TODO 報酬
		 * - - - - - - - - - */
	}

	/*
	 * エンドポータルの設置
	 */
	public static void setEndPortal(World world, int x, int y, int z) {
		LadDecorationDeteriorated.setStoneBrickEnclosure(world, x -2, x +2, z -2, z +2, y);
		LadFillBlock.fillBlockXZ(world, Blocks.lava, x -1, x +1, z -1, z +1, y);
		LadDecorationPillar.setBlockEnclosure(world, Blocks.end_portal_frame, x -2, x +2, z -2, z +2, y +2);
		LadDecorationCross.setFourBlockSlantingToAir(world, x, z, y +2, 2);
	}

	/*
	 * x1×z3の溶岩
	 */
	public static void setLavaX1Z3(World world, int x, int y, int z) {
		LadDecorationDeteriorated.setStoneBrickEnclosure(world, x -1, x +1, z -2, z +2, y);
		LadFillBlock.fillBlockZ(world, Blocks.lava, x, z -1, z +1, y);
	}

	/*
	 * x3×z1の溶岩
	 */
	public static void setLavaX3Z1(World world, int x, int y, int z) {
		LadDecorationDeteriorated.setStoneBrickEnclosure(world, x -2, x +2, z -1, z +1, y);
		LadFillBlock.fillBlockX(world, Blocks.lava, x -1, x +1, z, y);
	}

	/*
	 * エンドポータル周りの階段
	 */
	public static void setStoneBrickStairs(World world, int x, int y, int z, int meta) {
		if (meta == 0) {
			for (int i = -1; i <= 1; i++) {
				LadDecorationDeteriorated.setStoneBrick(world, x, y, z +i);
				LadDecorationDeteriorated.setStoneBrick(world, x, y +1, z +i);
				LadDecorationDeteriorated.setStoneBrick(world, x, y +2, z +i);

				LadDecorationDeteriorated.setStoneBrick(world, x -1, y, z +i);
				LadDecorationDeteriorated.setStoneBrick(world, x -1, y +1, z +i);
				world.setBlock(x -1, y +2, z +i, Blocks.stone_brick_stairs, meta, 2);

				LadDecorationDeteriorated.setStoneBrick(world, x -2, y, z +i);
				world.setBlock(x -2, y +1, z +i, Blocks.stone_brick_stairs, meta, 2);

				world.setBlock(x -3, y, z +i, Blocks.stone_brick_stairs, meta, 2);
			}
		} else if (meta == 1) {
			for (int i = -1; i <= 1; i++) {
				LadDecorationDeteriorated.setStoneBrick(world, x, y, z +i);
				LadDecorationDeteriorated.setStoneBrick(world, x, y +1, z +i);
				LadDecorationDeteriorated.setStoneBrick(world, x, y +2, z +i);

				LadDecorationDeteriorated.setStoneBrick(world, x +1, y, z +i);
				LadDecorationDeteriorated.setStoneBrick(world, x +1, y +1, z +i);
				world.setBlock(x +1, y +2, z +i, Blocks.stone_brick_stairs, meta, 2);

				LadDecorationDeteriorated.setStoneBrick(world, x +2, y, z +i);
				world.setBlock(x +2, y +1, z +i, Blocks.stone_brick_stairs, meta, 2);

				world.setBlock(x +3, y, z +i, Blocks.stone_brick_stairs, meta, 2);
			}
		} else if (meta == 2) {
			for (int i = -1; i <= 1; i++) {
				LadDecorationDeteriorated.setStoneBrick(world, x +i, y, z);
				LadDecorationDeteriorated.setStoneBrick(world, x +i, y +1, z);
				LadDecorationDeteriorated.setStoneBrick(world, x +i, y +2, z);

				LadDecorationDeteriorated.setStoneBrick(world, x +i, y, z -1);
				LadDecorationDeteriorated.setStoneBrick(world, x +i, y +1, z -1);
				world.setBlock(x +i, y +2, z -1, Blocks.stone_brick_stairs, meta, 2);

				LadDecorationDeteriorated.setStoneBrick(world, x +i, y, z -2);
				world.setBlock(x +i, y +1, z -2, Blocks.stone_brick_stairs, meta, 2);

				world.setBlock(x +i, y, z -3, Blocks.stone_brick_stairs, meta, 2);
			}
		} else {
			for (int i = -1; i <= 1; i++) {
				LadDecorationDeteriorated.setStoneBrick(world, x +i, y, z);
				LadDecorationDeteriorated.setStoneBrick(world, x +i, y +1, z);
				LadDecorationDeteriorated.setStoneBrick(world, x +i, y +2, z);

				LadDecorationDeteriorated.setStoneBrick(world, x +i, y, z +1);
				LadDecorationDeteriorated.setStoneBrick(world, x +i, y +1, z +1);
				world.setBlock(x +i, y +2, z +1, Blocks.stone_brick_stairs, meta, 2);

				LadDecorationDeteriorated.setStoneBrick(world, x +i, y, z +2);
				world.setBlock(x +i, y +1, z +2, Blocks.stone_brick_stairs, meta, 2);

				world.setBlock(x +i, y, z +3, Blocks.stone_brick_stairs, meta, 2);
			}
		}
	}
}
