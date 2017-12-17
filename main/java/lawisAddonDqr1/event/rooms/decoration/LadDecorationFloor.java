package lawisAddonDqr1.event.rooms.decoration;

import net.minecraft.block.Block;
import net.minecraft.world.World;

/*
 * 戦闘部屋を生成する時に利用するメソッドを集めたクラスのうちの1つ。
 *
 * 地面を装飾するためのメソッド。
 */
public class LadDecorationFloor {
	/* - - - - - - - - - -
	 * 基本メソッド
	 * - - - - - - - - - */

	/*
	 * 指定座標にブロックを設置した後に、
	 * その上にheightマス分空気を設置する。
	 */
	public static void setFloor(World world, Block block, int x, int z, int y, int height) {
		world.setBlock(x, y, z, block);

		for (int i = 1; i <= height; i++) {
			world.setBlockToAir(x, y +i, z);
		}
	}

	/*
	 * 指定座標にブロック（データ値meta）を設置した後に、
	 * その上にheightマス分空気を設置する。
	 */
	public static void setFloor(World world, Block block, int meta, int x, int z, int y, int height) {
		world.setBlock(x, y, z, block, meta, 2);

		for (int i = 1; i <= height; i++) {
			world.setBlockToAir(x, y +i, z);
		}
	}

	/*
	 * 指定座標にブロックを設置した後に、
	 * その上に1マス分空気を設置する。
	 */
	public static void setBlockAndAir(World world, Block block, int x, int z, int y) {
		world.setBlock(x, y, z, block);
		world.setBlockToAir(x, y +1, z);
	}

	/*
	 * 指定座標にブロック（データ値meta）を設置した後に、
	 * その上に1マス分空気を設置する。
	 */
	public static void setBlockAndAir(World world, Block block, int meta, int x, int z, int y) {
		world.setBlock(x, y, z, block, meta, 2);
		world.setBlockToAir(x, y +1, z);
	}


	/* - - - - - - - - - -
	 * 応用メソッド
	 * - - - - - - - - - */

	/*
	 * 十字型の床を生成する
	 */
	public static void setFloorCross(World world, Block block, int x, int z, int y, int height) {
		setFloor(world, block, x, z, y, height);
		setFloor(world, block, x, z +1, y, height);
		setFloor(world, block, x, z -1, y, height);
		setFloor(world, block, x +1, z, y, height);
		setFloor(world, block, x -1, z, y, height);
	}

	public static void setFloorCross(World world, Block block, int meta, int x, int z, int y, int height) {
		setFloor(world, block, meta, x, z, y, height);
		setFloor(world, block, meta, x, z +1, y, height);
		setFloor(world, block, meta, x, z -1,y, height);
		setFloor(world, block, meta, x +1, z, y, height);
		setFloor(world, block, meta, x -1, z, y, height);
	}

	public static void setBlockAndAirCross(World world, Block block, int x, int z, int y) {
		setBlockAndAir(world, block, x, z, y);
		setBlockAndAir(world, block, x, z +1, y);
		setBlockAndAir(world, block, x, z -1, y);
		setBlockAndAir(world, block, x +1, z, y);
		setBlockAndAir(world, block, x -1, z, y);
	}

	public static void setBlockAndAirCross(World world, Block block, int meta, int x, int z, int y) {
		setBlockAndAir(world, block, meta, x, z, y);
		setBlockAndAir(world, block, meta, x, z +1, y);
		setBlockAndAir(world, block, meta, x, z -1, y);
		setBlockAndAir(world, block, meta, x +1, z, y);
		setBlockAndAir(world, block, meta, x -1, z, y);
	}

	/*
	 * 床を敷き詰める
	 */
	public static void fillFloorXZ(World world, Block block, int x1, int x2, int z1, int z2, int y, int height) {
		LadFillBlock.fillBlockXZ(world, block, x1, x2, z1, z2, y);
		LadFillBlock.fillBlockToAir(world, x1, x2, z1, z2, y +1, y +height);
	}

	public static void fillFloorXZ(World world, Block block, int x1, int z1, int width, int y, int height) {
		LadFillBlock.fillBlockXZ(world, block, x1, z1, width, y);
		LadFillBlock.fillBlockToAir(world, x1, z1, width, y +1, height);
	}

	public static void fillBlockAndAirXZ(World world, Block block, int x1, int x2, int z1, int z2, int y) {
		LadFillBlock.fillBlockXZ(world, block, x1, x2, z1, z2, y);
		LadFillBlock.fillBlockXZToAir(world, x1, x2, z1, z2, y +1);
	}

	public static void fillBlockAndAirXZ(World world, Block block, int x1, int z1, int width, int y) {
		LadFillBlock.fillBlockXZ(world, block, x1, z1, width, y);
		LadFillBlock.fillBlockXZToAir(world, x1, z1, width, y +1);
	}
}
