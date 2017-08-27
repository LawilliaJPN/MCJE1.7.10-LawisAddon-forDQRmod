package lawisAddonDqr1.event.rooms.decoration;

import net.minecraft.block.Block;
import net.minecraft.world.World;

/*
 * 戦闘部屋を生成する時に利用するメソッドを集めたクラスのうちの1つ。
 *
 * 柱を設置するためのメソッド。
 */
public class LadDecorationPillar {
	/* - - - - - - - - - -
	 * 基本メソッド
	 * - - - - - - - - - */

	/*
	 * 指定座標x,y,zから高さheight分のブロックを設置する。
	 */
	public static void setPillar(World world, Block block, int x, int y, int z, int height) {
		for (int i = 0; i < height; i++) {
			world.setBlock(x, y +i, z, block);
		}
	}

	/*
	 * 指定座標x,y,zから高さheight分のブロック（データ値meta）を設置する。
	 */
	public static void setPillar(World world, Block block, int meta, int x, int y, int z, int height) {
		for (int i = 0; i < height; i++) {
			world.setBlock(x, y +i, z, block, meta, 2);
		}
	}

	/*
	 * 指定座標x,y,zから高さheight分の空気ブロックを設置する。
	 */
	public static void setPillar(World world, int x, int y, int z, int height) {
		for (int i = 0; i < height; i++) {
			world.setBlockToAir(x, y +i, z);
		}
	}

	/* - - - - - - - - - -
	 * 応用メソッド
	 * - - - - - - - - - */

	/*
	 * 四方向(東西南北)に柱を設置
	 */
	public static void setPillarCenter(World world, Block block, int centerX, int centerZ, int y, int height, int position) {
		setPillar(world, block, centerX, y, centerZ -position, height);
		setPillar(world, block, centerX, y, centerZ +position, height);
		setPillar(world, block, centerX +position, y, centerZ, height);
		setPillar(world, block, centerX -position, y, centerZ, height);
	}
	/*
	 * 斜め四方向に柱を設置
	 */
	public static void setPillarSlanting(World world, Block block, int centerX, int centerZ, int y, int height, int position) {
		setPillar(world, block, centerX -position, y, centerZ -position, height);
		setPillar(world, block, centerX -position, y, centerZ +position, height);
		setPillar(world, block, centerX +position, y, centerZ -position, height);
		setPillar(world, block, centerX +position, y, centerZ +position, height);
	}
}
