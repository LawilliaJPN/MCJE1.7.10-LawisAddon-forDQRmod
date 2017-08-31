package lawisAddonDqr1.event.rooms.decoration;

import net.minecraft.block.Block;
import net.minecraft.world.World;

/*
 * 戦闘部屋を生成する時に利用するメソッドを集めたクラスのうちの1つ。
 *
 * 一定範囲に同じブロックを埋め尽くす処理。
 */
public class LadFillBlock {
	/* XYZ直方体 */
	public static void fillBlock(World world, Block block, int x1, int x2, int z1, int z2, int y1, int y2) {
		for (int x = x1; x <= x2; x++) {
			for (int z = z1; z <= z2; z++) {
				for (int y = y1; y <= y2; y++) {
					world.setBlock(x, y, z, block);
				}
			}
		}
	}
	public static void fillBlock(World world, Block block, int meta, int x1, int x2, int z1, int z2, int y1, int y2) {
		for (int x = x1; x <= x2; x++) {
			for (int z = z1; z <= z2; z++) {
				for (int y = y1; y <= y2; y++) {
					world.setBlock(x, y, z, block, meta, 2);
				}
			}
		}
	}
	public static void fillBlockToAir(World world, int x1, int x2, int z1, int z2, int y1, int y2) {
		for (int x = x1; x <= x2; x++) {
			for (int z = z1; z <= z2; z++) {
				for (int y = y1; y <= y2; y++) {
					world.setBlockToAir(x, y, z);
				}
			}
		}
	}

	public static void fillBlock(World world, Block block, int x1, int z1, int width, int y1, int height) {
		for (int x = x1; x <= x1 +width; x++) {
			for (int z = z1; z <= z1 +width; z++) {
				for (int y = y1; y <= y1 +height; y++) {
					world.setBlock(x, y, z, block);
				}
			}
		}
	}
	public static void fillBlockToAir(World world, int x1, int z1, int width, int y1, int height) {
		for (int x = x1; x <= x1 +width; x++) {
			for (int z = z1; z <= z1 +width; z++) {
				for (int y = y1; y <= y1 +height; y++) {
					world.setBlockToAir(x, y, z);
				}
			}
		}
	}

	/* XYZ立方体 */
	public static void fillBlockCube(World world, Block block, int x1, int z1, int y1, int size) {
		for (int x = x1; x <= x1 +size; x++) {
			for (int z = z1; z <= z1 +size; z++) {
				for (int y = y1; y <= y1 +size; y++) {
					world.setBlock(x, y, z, block);
				}
			}
		}
	}
	public static void fillBlockCube(World world, Block block, int meta, int x1, int z1, int y1, int size) {
		for (int x = x1; x <= x1 +size; x++) {
			for (int z = z1; z <= z1 +size; z++) {
				for (int y = y1; y <= y1 +size; y++) {
					world.setBlock(x, y, z, block, meta, 2);
				}
			}
		}
	}
	public static void fillBlockCubeToAir(World world, int x1, int z1, int y1, int size) {
		for (int x = x1; x <= x1 +size; x++) {
			for (int z = z1; z <= z1 +size; z++) {
				for (int y = y1; y <= y1 +size; y++) {
					world.setBlockToAir(x, y, z);
				}
			}
		}
	}

	/* XZ平面(床、地面、天井等) */
	public static void fillBlockXZ(World world, Block block, int x1, int x2, int z1, int z2, int y) {
		for (int x = x1; x <= x2; x++) {
			for (int z = z1; z <= z2; z++) {
				world.setBlock(x, y, z, block);
			}
		}
	}

	public static void fillBlockXZ(World world, Block block, int x1, int z1, int width, int y) {
		for (int x = x1; x <= x1 +width; x++) {
			for (int z = z1; z <= z1 +width; z++) {
				world.setBlock(x, y, z, block);
			}
		}
	}

	/* X直線 */
	public static void fillBlockX(World world, Block block, int x1, int x2, int z, int y) {
		for (int x = x1; x <= x2; x++) {
			world.setBlock(x, y, z, block);
		}
	}

	/* Z直線 */
	public static void fillBlockZ(World world, Block block, int x, int z1, int z2, int y) {
		for (int z = z1; z <= z2; z++) {
			world.setBlock(x, y, z, block);
		}
	}
}
