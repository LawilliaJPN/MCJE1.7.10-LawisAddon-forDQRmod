package lawisAddonDqr1.event.rooms.decoration;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class LadDecorationCross {
	/*
	 * 十字型にブロックを設置するメソッド
	 * sizeは0～3に対応
	 */
	public static void setBlockCross(World world, Block block, int x, int y, int z, int size) {
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
	public static void setBlockDiamond(World world, Block block, int x, int y, int z, int size) {
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
	 * 四方向(東西南北)に設置
	 */
	public static void setFourBlockCross(World world, Block block, int x, int z, int y, int interval) {
		world.setBlock(x, y, z -interval, block);
		world.setBlock(x, y, z +interval, block);
		world.setBlock(x +interval, y, z, block);
		world.setBlock(x -interval, y, z, block);
	}

	public static void setFourBlockCross(World world, Block block, int meta, int x, int z, int y, int interval) {
		world.setBlock(x, y, z -interval, block, meta, 2);
		world.setBlock(x, y, z +interval, block, meta, 2);
		world.setBlock(x +interval, y, z, block, meta, 2);
		world.setBlock(x -interval, y, z, block, meta, 2);
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
}
