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
	public static void setFloor(World world, Block block, int x, int y, int z, int height) {
		world.setBlock(x, y, z, block);

		for (int i = 1; i <= height; i++) {
			world.setBlockToAir(x, y +i, z);
		}
	}

	/*
	 * 指定座標にブロック（データ値meta）を設置した後に、
	 * その上にheightマス分空気を設置する。
	 */
	public static void setFloor(World world, Block block, int meta, int x, int y, int z, int height) {
		world.setBlock(x, y, z, block, meta, 2);

		for (int i = 1; i <= height; i++) {
			world.setBlockToAir(x, y +i, z);
		}
	}

	/*
	 * 指定座標にブロックを設置した後に、
	 * その上に1マス分空気を設置する。
	 */
	public static void setBlockAndAir(World world, Block block, int x, int y, int z) {
		world.setBlock(x, y, z, block);
		world.setBlockToAir(x, y +1, z);
	}

	/*
	 * 指定座標にブロック（データ値meta）を設置した後に、
	 * その上に1マス分空気を設置する。
	 */
	public static void setBlockAndAir(World world, Block block, int meta, int x, int y, int z) {
		world.setBlock(x, y, z, block, meta, 2);
		world.setBlockToAir(x, y +1, z);
	}


	/* - - - - - - - - - -
	 * 応用メソッド
	 * - - - - - - - - - */

	/*
	 * 十字型の床を生成する
	 */
	public static void setFloorCross(World world, Block block, int x, int y, int z, int height) {
		setFloor(world, block, x, y, z, height);
		setFloor(world, block, x, y, z +1, height);
		setFloor(world, block, x, y, z -1, height);
		setFloor(world, block, x +1, y, z, height);
		setFloor(world, block, x -1, y, z, height);
	}

	public static void setFloorCross(World world, Block block, int meta, int x, int y, int z, int height) {
		setFloor(world, block, meta, x, y, z, height);
		setFloor(world, block, meta, x, y, z +1, height);
		setFloor(world, block, meta, x, y, z -1, height);
		setFloor(world, block, meta, x +1, y, z, height);
		setFloor(world, block, meta, x -1, y, z, height);
	}

	public static void setBlockAndAirCross(World world, Block block, int x, int y, int z) {
		setBlockAndAir(world, block, x, y, z);
		setBlockAndAir(world, block, x, y, z +1);
		setBlockAndAir(world, block, x, y, z -1);
		setBlockAndAir(world, block, x +1, y, z);
		setBlockAndAir(world, block, x -1, y, z);
	}

	public static void setBlockAndAirCross(World world, Block block, int meta, int x, int y, int z) {
		setBlockAndAir(world, block, meta, x, y, z);
		setBlockAndAir(world, block, meta, x, y, z +1);
		setBlockAndAir(world, block, meta, x, y, z -1);
		setBlockAndAir(world, block, meta, x +1, y, z);
		setBlockAndAir(world, block, meta, x -1, y, z);
	}
}
