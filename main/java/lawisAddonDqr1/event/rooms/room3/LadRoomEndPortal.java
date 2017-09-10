package lawisAddonDqr1.event.rooms.room3;

import java.util.Random;

import lawisAddonDqr1.achievement.LadAchievementCore;
import lawisAddonDqr1.config.LadDebug;
import lawisAddonDqr1.event.entities.LadSpawnEnemyCore;
import lawisAddonDqr1.event.rooms.LadRoomID;
import lawisAddonDqr1.event.rooms.decoration.LadDecorationCross;
import lawisAddonDqr1.event.rooms.decoration.LadDecorationDeteriorated;
import lawisAddonDqr1.event.rooms.decoration.LadDecorationPillar;
import lawisAddonDqr1.event.rooms.decoration.LadDecorationReward;
import lawisAddonDqr1.event.rooms.decoration.LadFillBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentTranslation;
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
		int roomZ = (int)player.posZ;			// 部屋の起点となるZ座標
		int roomY = (int)player.posY -1;		// 部屋の起点となるY座標（-1）

		int roomHeight = 5;					// 部屋の高さ
		int roomWidthX = 18;					// 部屋の幅（-1）
		int roomWidthZ = 18;					// 部屋の幅（-1）
		int roomCenterX = roomWidthX /2;		// 部屋の中心
		int roomCenterZ = roomWidthZ /2;		// 部屋の中心

		int roomType = rand.nextInt(3);		// 部屋の種類

		/* 部屋の生成位置などの調整 */
		// マイナス座標の時に、部屋の位置がズレることの修正
		if (player.posX < 0) roomX -=1;
		if (player.posZ < 0) roomZ -=1;

		// プレイヤーの向きから部屋の起点となる座標を決める
		switch (roomDirection) {
		case 0:
			roomX -= 1;
			roomZ -= roomCenterZ;
			isRoomDirection02 = true;
			break;
		case 1:
			roomX -= roomCenterX;
			roomZ -= 1;
			break;
		case 2:
			roomX -= roomWidthX -1;
			roomZ -= roomCenterZ;
			isRoomDirection02 = true;
			break;
		case 3:
			roomX -= roomCenterX;
			roomZ -= roomWidthZ -1;
			break;
		}

		/* デバッグ用 */
		// [Debug] 戦闘部屋固定時に生成方向がチャット表示される（デバッグ用）
		if (LadDebug.getDebugRoom() >=0) {
			player.addChatMessage(new ChatComponentTranslation("roomDirection == " + roomDirection));
			player.addChatMessage(new ChatComponentTranslation("roomType == " + roomType));
			player.addChatMessage(new ChatComponentTranslation("difOfRoom == " + LadRoomID.getDifOfRoom()));
		}

		// 実績の取得
		player.triggerAchievement(LadAchievementCore.roomEndPortal);

		/* - - - - - - - - - -
		 * 以下、部屋の生成
		 * - - - - - - - - - */

		// 床・壁・天井・中の空気
		LadDecorationDeteriorated.fillStoneBrickXZ(world, roomX, roomX +roomWidthX, roomZ, roomZ +roomWidthZ, roomY -1);
		LadDecorationDeteriorated.setStoneBrickWall(world, roomX -1, roomX +roomWidthX +1, roomZ -1, roomZ +roomWidthZ +1, roomY -1, roomY +roomHeight);
		LadDecorationDeteriorated.fillStoneBrickXZ(world, roomX, roomX +roomWidthX, roomZ, roomZ +roomWidthZ, roomY +roomHeight);
		LadFillBlock.fillBlockToAir(world, roomX, roomX +roomWidthX, roomZ, roomZ +roomWidthZ, roomY, roomY +roomHeight -1);

		// エンドポータルと階段の設置
		setEndPortal(world, roomX +roomCenterX, roomY, roomZ +roomCenterZ);
		setStoneBrickStairs(world, roomX +roomCenterX -3, roomY, roomZ +roomCenterZ, 0);
		setStoneBrickStairs(world, roomX +roomCenterX +3, roomY, roomZ +roomCenterZ, 1);
		setStoneBrickStairs(world, roomX +roomCenterX, roomY, roomZ +roomCenterZ -3, 2);
		setStoneBrickStairs(world, roomX +roomCenterX, roomY, roomZ +roomCenterZ +3, 3);

		// 隅の溶岩の設置
		setLavaX1Z3(world, roomX, roomY, roomZ +1);
		setLavaX1Z3(world, roomX +roomWidthX, roomY, roomZ +roomWidthZ -1);
		setLavaX3Z1(world, roomX +roomWidthX -1, roomY, roomZ);
		setLavaX3Z1(world, roomX +1, roomY, roomZ +roomWidthZ);

		// 出入口の設置
		setGateX3Z1(world, roomX +roomCenterX, roomY, roomZ -1);
		setGateX3Z1(world, roomX +roomCenterX, roomY, roomZ +roomWidthZ +1);
		setGateX1Z3(world, roomX -1, roomY, roomZ +roomCenterZ);
		setGateX1Z3(world, roomX +roomWidthZ +1, roomY, roomZ +roomCenterZ);

		// 鉄格子の窓
		setIronBarsWindowsX(world, roomX, roomY, roomZ -1, roomWidthX, roomWidthX /2 -3);
		setIronBarsWindowsX(world, roomX, roomY, roomZ +roomWidthZ +1, roomWidthX, roomWidthX /2 -3);
		setIronBarsWindowsZ(world, roomX -1, roomY, roomZ, roomWidthZ, roomWidthZ /2 -3);
		setIronBarsWindowsZ(world, roomX +roomWidthX +1, roomY, roomZ, roomWidthZ, roomWidthZ /2 -3);

		// 柱
		if (roomType == 1) {
			LadDecorationDeteriorated.fillStoneBrick(world, roomX +roomCenterX -4, roomX +roomCenterX -2, roomZ +roomCenterZ -4, roomZ +roomCenterZ -2, roomY, roomY +roomHeight -1);
			LadDecorationDeteriorated.fillStoneBrick(world, roomX +roomCenterX -4, roomX +roomCenterX -2, roomZ +roomCenterZ +2, roomZ +roomCenterZ +4, roomY, roomY +roomHeight -1);
			LadDecorationDeteriorated.fillStoneBrick(world, roomX +roomCenterX +2, roomX +roomCenterX +4, roomZ +roomCenterZ -4, roomZ +roomCenterZ -2, roomY, roomY +roomHeight -1);
			LadDecorationDeteriorated.fillStoneBrick(world, roomX +roomCenterX +2, roomX +roomCenterX +4, roomZ +roomCenterZ +2, roomZ +roomCenterZ +4, roomY, roomY +roomHeight -1);
		} else if (roomType == 2) {
			LadDecorationDeteriorated.fillStoneBrick(world, roomX +roomCenterX -5, roomX +roomCenterX -2, roomZ +roomCenterZ -5, roomZ +roomCenterZ -2, roomY, roomY +2);
			LadDecorationDeteriorated.fillStoneBrick(world, roomX +roomCenterX -5, roomX +roomCenterX -2, roomZ +roomCenterZ +2, roomZ +roomCenterZ +5, roomY, roomY +2);
			LadDecorationDeteriorated.fillStoneBrick(world, roomX +roomCenterX +2, roomX +roomCenterX +5, roomZ +roomCenterZ -5, roomZ +roomCenterZ -2, roomY, roomY +2);
			LadDecorationDeteriorated.fillStoneBrick(world, roomX +roomCenterX +2, roomX +roomCenterX +5, roomZ +roomCenterZ +2, roomZ +roomCenterZ +5, roomY, roomY +2);
		}

		/* - - - - - - - - - -
		 * 以下、敵のスポーン
		 * - - - - - - - - - */
		switch (roomDirection) {
		case 0:
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidthX -1, roomY +1, roomZ +roomCenterZ, LadRoomID.END_PORTAL + LadRoomID.getDifOfRoom());
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidthX -2, roomY +1, roomZ +roomCenterZ -6, LadRoomID.END_PORTAL +LadRoomID.getDifOfRoom());
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidthX -2, roomY +1, roomZ +roomCenterZ +6, LadRoomID.END_PORTAL +LadRoomID.getDifOfRoom());
			if (rand.nextInt(4) == 0) {
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenterX, roomY +1, roomZ +roomWidthZ -1, LadRoomID.END_PORTAL + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenterX, roomY +1, roomZ +1, LadRoomID.END_PORTAL + LadRoomID.getDifOfRoom());
			}
			break;
		case 1:
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenterX, roomY +1, roomZ +roomWidthZ -1, LadRoomID.END_PORTAL + LadRoomID.getDifOfRoom());
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenterX -6, roomY +1, roomZ +roomWidthZ -2, LadRoomID.END_PORTAL +LadRoomID.getDifOfRoom());
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenterX +6, roomY +1, roomZ +roomWidthZ -2, LadRoomID.END_PORTAL +LadRoomID.getDifOfRoom());
			if (rand.nextInt(4) == 0) {
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidthX -1, roomY +1, roomZ +roomCenterZ, LadRoomID.END_PORTAL + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +1, roomY +1, roomZ +roomCenterZ, LadRoomID.END_PORTAL + LadRoomID.getDifOfRoom());
			}
			break;
		case 2:
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +1, roomY +1, roomZ +roomCenterZ, LadRoomID.END_PORTAL + LadRoomID.getDifOfRoom());
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +2, roomY +1, roomZ +roomCenterZ -6, LadRoomID.END_PORTAL +LadRoomID.getDifOfRoom());
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +2, roomY +1, roomZ +roomCenterZ +6, LadRoomID.END_PORTAL +LadRoomID.getDifOfRoom());
			if (rand.nextInt(4) == 0) {
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenterX, roomY +1, roomZ +roomWidthZ -1, LadRoomID.END_PORTAL + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenterX, roomY +1, roomZ +1, LadRoomID.END_PORTAL + LadRoomID.getDifOfRoom());
			}
			break;
		case 3:
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenterX, roomY +1, roomZ +1, LadRoomID.END_PORTAL + LadRoomID.getDifOfRoom());
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenterX -6, roomY +1, roomZ +2, LadRoomID.END_PORTAL +LadRoomID.getDifOfRoom());
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenterX +6, roomY +1, roomZ +2, LadRoomID.END_PORTAL +LadRoomID.getDifOfRoom());
			if (rand.nextInt(4) == 0) {
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidthX -1, roomY +1, roomZ +roomCenterZ, LadRoomID.END_PORTAL + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +1, roomY +1, roomZ +roomCenterZ, LadRoomID.END_PORTAL + LadRoomID.getDifOfRoom());
			}
			break;
		}

		/* - - - - - - - - - -
		 * 以下、報酬
		 * - - - - - - - - - */
		switch (roomDirection) {
		case 0:
			LadDecorationReward.setChest(world, roomX +roomWidthX +1, roomY, roomZ +roomCenterZ);
			break;
		case 1:
			LadDecorationReward.setChest(world, roomX +roomCenterX, roomY, roomZ +roomWidthZ +1);
			break;
		case 2:
			LadDecorationReward.setChest(world, roomX -1, roomY, roomZ +roomCenterZ);
			break;
		case 3:
			LadDecorationReward.setChest(world, roomX +roomCenterX, roomY, roomZ -1);
			break;
		}
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
	 * x1×z3の出入口
	 */
	public static void setGateX1Z3(World world, int x, int y, int z) {
		LadDecorationPillar.setWallZ(world, Blocks.iron_bars, x, z -1, z +1, y, 3);
		world.setBlockToAir(x, y, z);
		world.setBlockToAir(x, y +1, z);
	}

	/*
	 * x3×z1の出入口
	 */
	public static void setGateX3Z1(World world, int x, int y, int z) {
		LadDecorationPillar.setWallX(world, Blocks.iron_bars, x -1, x +1, z, y, 3);
		world.setBlockToAir(x, y, z);
		world.setBlockToAir(x, y +1, z);
	}

	/*
	 * 鉄格子高さ2段
	 */
	public static void setIronBars(World world, int x, int y, int z) {
		world.setBlock(x, y, z, Blocks.iron_bars);
		world.setBlock(x, y +1, z, Blocks.iron_bars);
	}

	/*
	 * 鉄格子の窓
	 */
	public static void setIronBarsWindowsX(World world, int x, int y, int z, int roomWidthX, int range) {
		for (int i = 1; i <= range; i++) {
			if (i%2 == 0) {
				setIronBars(world, x +i, y +2, z);
				setIronBars(world, x +roomWidthX -i, y +2, z);
			}
		}
	}
	public static void setIronBarsWindowsZ(World world, int x, int y, int z, int roomWidthZ, int range) {
		for (int i = 1; i <= range; i++) {
			if (i%2 == 0) {
				setIronBars(world, x, y +2, z +i);
				setIronBars(world, x, y +2, z +roomWidthZ -i);
			}
		}
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
