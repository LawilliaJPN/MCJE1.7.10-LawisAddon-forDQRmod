package lawisAddonDqr1.event.rooms.decoration;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class LadDecorationCross {
	/*
	 * 十字型にブロックを設置するメソッド
	 * sizeは0～3に対応
	 */
	public static void setBlockCross(World world, Block block, int x, int z, int y, int size) {
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
	}

	/*
	 * 菱型にブロックを設置するメソッド
	 * sizeは0～3に対応
	 */
	public static void setBlockDiamond(World world, Block block, int x, int z, int y, int size) {
		switch(size) {
		case 3:
			world.setBlock(x, y, z +3, block);
			world.setBlock(x, y, z -3, block);
			world.setBlock(x +3, y, z, block);
			world.setBlock(x -3, y, z, block);

			world.setBlock(x +1, y, z +2, block);
			world.setBlock(x +1, y, z -2, block);
			world.setBlock(x -1, y, z +2, block);
			world.setBlock(x -1, y, z -2, block);

			world.setBlock(x +2, y, z +1, block);
			world.setBlock(x +2, y, z -1, block);
			world.setBlock(x -2, y, z +1, block);
			world.setBlock(x -2, y, z -1, block);
		case 2:
			world.setBlock(x, y, z +2, block);
			world.setBlock(x, y, z -2, block);
			world.setBlock(x +2, y, z, block);
			world.setBlock(x -2, y, z, block);

			world.setBlock(x +1, y, z +1, block);
			world.setBlock(x +1, y, z -1, block);
			world.setBlock(x -1, y, z +1, block);
			world.setBlock(x -1, y, z -1, block);
		case 1:
			world.setBlock(x, y, z +1, block);
			world.setBlock(x, y, z -1, block);
			world.setBlock(x +1, y, z, block);
			world.setBlock(x -1, y, z, block);
		default:
			world.setBlock(x, y, z, block);
		}
	}

	/*
	 * 4つブロックを設置
	 */
	public static void setFourBlock(World world, Block block, int x1, int x2, int z1, int z2, int y) {
		world.setBlock(x1, y, z1, block);
		world.setBlock(x1, y, z2, block);
		world.setBlock(x2, y, z1, block);
		world.setBlock(x2, y, z2, block);
	}

	/*
	 * 四方向(東西南北)に設置
	 */
	public static void setFourBlockCross(World world, Block block, int centerX, int centerZ, int y, int interval) {
		world.setBlock(centerX, y, centerZ -interval, block);
		world.setBlock(centerX, y, centerZ +interval, block);
		world.setBlock(centerX +interval, y, centerZ, block);
		world.setBlock(centerX -interval, y, centerZ, block);
	}

	public static void setFourBlockCross(World world, Block block, int meta, int centerX, int centerZ, int y, int interval) {
		world.setBlock(centerX, y, centerZ -interval, block, meta, 2);
		world.setBlock(centerX, y, centerZ +interval, block, meta, 2);
		world.setBlock(centerX +interval, y, centerZ, block, meta, 2);
		world.setBlock(centerX -interval, y, centerZ, block, meta, 2);
	}

	public static void setFourBlockCrossWith2Meta(World world, Block block, int centerX, int centerZ, int y, int interval) {
		world.setBlock(centerX, y, centerZ -interval, block);
		world.setBlock(centerX, y, centerZ +interval, block);
		world.setBlock(centerX +interval, y, centerZ, block, 1, 2);
		world.setBlock(centerX -interval, y, centerZ, block, 1, 2);
	}

	/*
	 * 斜め四方向に設置
	 */
	public static void setFourBlockSlanting(World world, Block block, int x, int z, int y, int interval) {
		world.setBlock(x -interval, y, z -interval, block);
		world.setBlock(x -interval, y, z +interval, block);
		world.setBlock(x +interval, y, z -interval, block);
		world.setBlock(x +interval, y, z +interval, block);
	}

	public static void setFourBlockSlanting(World world, Block block, int meta, int x, int z, int y, int interval) {
		world.setBlock(x -interval, y, z -interval, block, meta, 2);
		world.setBlock(x -interval, y, z +interval, block, meta, 2);
		world.setBlock(x +interval, y, z -interval, block, meta, 2);
		world.setBlock(x +interval, y, z +interval, block, meta, 2);
	}

	public static void setFourBlockSlantingToAir(World world, int x, int z, int y, int interval) {
		world.setBlockToAir(x -interval, y, z -interval);
		world.setBlockToAir(x -interval, y, z +interval);
		world.setBlockToAir(x +interval, y, z -interval);
		world.setBlockToAir(x +interval, y, z +interval);
	}
}
