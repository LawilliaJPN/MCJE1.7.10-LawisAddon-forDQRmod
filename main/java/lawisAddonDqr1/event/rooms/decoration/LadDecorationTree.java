package lawisAddonDqr1.event.rooms.decoration;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;

/*
 * 戦闘部屋を生成する時に利用するメソッドを集めたクラスのうちの1つ。
 *
 * 木を設置するためのメソッド。
 */
public class LadDecorationTree {
	/*
	 * 木を生成するメソッド
	 */
	public static void setTree(World world, Random rand, int x, int z, int y, int meta) {
		int treeHeight = 2;

		if (rand.nextInt(2) == 0)  x++;
		if (rand.nextInt(2) == 0)  x--;
		if (rand.nextInt(2) == 0)  z++;
		if (rand.nextInt(2) == 0)  z--;
		if (rand.nextInt(2) == 0)  treeHeight++;

		// 木の下を「土ブロック」に変える
		world.setBlock(x, y -1, z, Blocks.dirt);

		/* 葉ブロック */
		// 頂上 十字
		setLeaves(world, x, z, y +treeHeight +3, meta);
		setLeaves(world, x, z +1, y +treeHeight +3, meta);
		setLeaves(world, x, z -1, y +treeHeight +3, meta);
		setLeaves(world, x +1, z, y +treeHeight +3, meta);
		setLeaves(world, x -1, z, y +treeHeight +3, meta);

		// 上段
		for (int x2 = -1; x2 <= 1; x2++) {
			for (int z2 = -1; z2 <= 1; z2++) {
				setLeaves(world, x +x2, z +z2, y +treeHeight +2, meta);
			}
		}

		// 下段
		for (int x2 = -2; x2 <= 2; x2++) {
			for (int z2 = -2; z2 <= 2; z2++) {
				for (int y2 = 0; y2 <= 1; y2++) {
					setLeaves(world, x +x2, z +z2, y +treeHeight +y2, meta);
				}
			}
		}

		/* 原木 */
		for (int i = 0; i <= treeHeight+2; i++) {
			world.setBlock(x, y +i, z, Blocks.log, meta, 2);
		}

		/* ジャックオランタン */
		if (rand.nextInt(64) == 0) {
			world.setBlock(x, y +treeHeight -1, z, Blocks.lit_pumpkin, rand.nextInt(4), 2);
		}
	}

	/*
	 * 葉を設置メソッド
	 */
	public static void setLeaves(World world, int x, int z, int y, int meta) {
		if (world.getBlock(x, y, z).isAir(world, x, y, z)) {
			world.setBlock(x, y, z, Blocks.leaves, meta, 2);
		}
	}
}
