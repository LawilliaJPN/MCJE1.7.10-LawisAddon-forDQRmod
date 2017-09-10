package lawisAddonDqr1.event.rooms.decoration;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;

/*
 * 戦闘部屋を生成する時に利用するメソッドを集めたクラスのうちの1つ。
 *
 * ブロックを老朽化させるメソッド。
 */
public class LadDecorationDeteriorated {
	// 「草ブロック」や「土ブロック」を「荒い土」に変える
	public static void setCoarseDirtRandomly(World world, Random rand, int x1, int x2, int z1, int z2, int y1, int y2) {
		for (int x = x1; x <= x2; x++) {
			for (int z = z1; z <= z2; z++) {
				for (int y = y1; y <= y2; y++) {
					if ((world.getBlock(x, y, z) == Blocks.dirt) || (world.getBlock(x, y, z) == Blocks.grass)) {
						int r = rand.nextInt(4);
						if (r == 0) world.setBlock(x, y, z, Blocks.dirt, 1, 2);
					}
				}
			}
		}
	}

	// 「丸石」を「苔石」に変える
	public static void deteriorateCobbleStone(World world, Random rand, int x1, int x2, int z1, int z2, int y1, int y2) {
		for (int x = x1; x <= x2; x++) {
			for (int z = z1; z <= z2; z++) {
				for (int y = y1; y <= y2; y++) {
					if (world.getBlock(x, y, z) == Blocks.cobblestone) {
						int r = rand.nextInt(4);
						if (r == 0) world.setBlock(x, y, z, Blocks.mossy_cobblestone);
					} else if (world.getBlock(x, y, z) == Blocks.cobblestone_wall) {
						int r = rand.nextInt(4);
						if (r == 0) world.setBlock(x, y, z, Blocks.cobblestone_wall, 1, 2);
					}
				}
			}
		}
	}

	// ブロックをなくす
	public static void setBlockToAirRandomly(World world, Random rand, int x1, int x2, int z1, int z2, int y1, int y2) {
		for (int x = x1; x <= x2; x++) {
			for (int z = z1; z <= z2; z++) {
				for (int y = y1; y <= y2; y++) {
					if (rand.nextInt(4) == 0) {
						world.setBlockToAir(x, y, z);
					}
				}
			}
		}
	}

	// 石レンガをヒビ・コケの有無ランダムで設置する
	public static void setStoneBrick(World world, int x, int y, int z) {
		Random rand = new Random();
		switch (rand.nextInt(4)) {
		case 0:
			world.setBlock(x, y, z, Blocks.stonebrick, 1, 2);
			break;
		case 1:
			world.setBlock(x, y, z, Blocks.stonebrick, 2, 2);
			break;
		default:
			world.setBlock(x, y, z, Blocks.stonebrick);
			break;
		}
	}
	public static void fillStoneBrick(World world, int x1, int x2, int z1, int z2, int y1, int y2) {
		for (int x = x1; x <= x2; x++) {
			for (int z = z1; z <= z2; z++) {
				for (int y = y1; y <= y2; y++) {
					setStoneBrick(world, x, y, z);
				}
			}
		}
	}
	public static void fillStoneBrickXZ(World world, int x1, int x2, int z1, int z2, int y) {
		for (int x = x1; x <= x2; x++) {
			for (int z = z1; z <= z2; z++) {
				setStoneBrick(world, x, y, z);
			}
		}
	}
	public static void setStoneBrickEnclosure(World world, int x1, int x2, int z1, int z2, int y) {
		for (int x = x1; x <= x2; x++) {
			setStoneBrick(world, x, y, z1);
			setStoneBrick(world, x, y, z2);
		}

		for (int z = z1 +1; z <= z2 -1; z++) {
			setStoneBrick(world, x1, y, z);
			setStoneBrick(world, x2, y, z);
		}
	}
	public static void setStoneBrickWall(World world, int x1, int x2, int z1, int z2, int y1, int y2) {
		for (int y = y1; y <= y2; y++){
			for (int x = x1; x <= x2; x++) {
				setStoneBrick(world, x, y, z1);
				setStoneBrick(world, x, y, z2);
			}

			for (int z = z1 +1; z <= z2 -1; z++) {
				setStoneBrick(world, x1, y, z);
				setStoneBrick(world, x2, y, z);
			}
		}
	}
}
