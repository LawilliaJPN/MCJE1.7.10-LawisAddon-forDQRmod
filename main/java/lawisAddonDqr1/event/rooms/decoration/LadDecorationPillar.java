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
	public static void setPillarToAir(World world, int x, int y, int z, int height) {
		for (int i = 0; i < height; i++) {
			world.setBlockToAir(x, y +i, z);
		}
	}

	/* - - - - - - - - - -
	 * 応用メソッド
	 * - - - - - - - - - */

	/*
	 * 太い柱を設置
	 */
	public static void setThickPillar(World world, Block block, int x1, int z1, int width, int y, int height) {
		for (int x = x1; x <= x1 +width; x++) {
			for (int z = z1; z <= z1 +width; z++) {
				setPillar(world, block, x, y, z, height);
			}
		}
	}

	public static void setThickPillar(World world, Block block, int x1, int x2, int z1, int z2, int y, int height) {
		for (int x = x1; x <= x2; x++) {
			for (int z = z1; z <= z2; z++) {
				setPillar(world, block, x, y, z, height);
			}
		}
	}

	/*
	 * 4つの柱を設置
	 */
	public static void setFourPillar(World world, Block block, int x1, int x2, int z1, int z2, int y, int height) {
		setPillar(world, block, x1, y, z1, height);
		setPillar(world, block, x1, y, z2, height);
		setPillar(world, block, x2, y, z1, height);
		setPillar(world, block, x2, y, z2, height);
	}
	public static void setFourPillarToAir(World world, int x1, int x2, int z1, int z2, int y, int height) {
		setPillarToAir(world, x1, y, z1, height);
		setPillarToAir(world, x1, y, z2, height);
		setPillarToAir(world, x2, y, z1, height);
		setPillarToAir(world, x2, y, z2, height);
	}

	/*
	 * 四方向(東西南北)に柱を設置
	 */
	public static void setFourPillarCross(World world, Block block, int centerX, int centerZ, int y, int height, int position) {
		setPillar(world, block, centerX, y, centerZ -position, height);
		setPillar(world, block, centerX, y, centerZ +position, height);
		setPillar(world, block, centerX +position, y, centerZ, height);
		setPillar(world, block, centerX -position, y, centerZ, height);
	}
	public static void setFourPillarCrossToAir(World world, int centerX, int centerZ, int y, int height, int position) {
		setPillarToAir(world, centerX, y, centerZ -position, height);
		setPillarToAir(world, centerX, y, centerZ +position, height);
		setPillarToAir(world, centerX +position, y, centerZ, height);
		setPillarToAir(world, centerX -position, y, centerZ, height);
	}

	/*
	 * 斜め四方向に柱を設置
	 */
	public static void setFourPillarSlanting(World world, Block block, int centerX, int centerZ, int y, int height, int position) {
		setPillar(world, block, centerX -position, y, centerZ -position, height);
		setPillar(world, block, centerX -position, y, centerZ +position, height);
		setPillar(world, block, centerX +position, y, centerZ -position, height);
		setPillar(world, block, centerX +position, y, centerZ +position, height);
	}

	/*
	 * Z座標方向の壁を設置(x1, z)～(x2 ,z)
	 * 条件：x1＜x2
	 */
	public static void setWallX(World world, Block block, int x1, int x2, int z, int y, int height) {
		for (int x = x1; x <= x2; x++) {
			setPillar(world, block, x, y, z, height);
		}
	}
	public static void setWallXToAir(World world, int x1, int x2, int z, int y, int height) {
		for (int x = x1; x <= x2; x++) {
			setPillarToAir(world, x, y, z, height);
		}
	}

	/*
	 * X座標方向の壁を設置(x, z1)～(x ,z2)
	 * 条件：z1＜z2
	 */
	public static void setWallZ(World world, Block block, int x, int z1, int z2, int y, int height) {
		for (int z = z1; z <= z2; z++) {
			setPillar(world, block, x, y, z, height);
		}
	}
	public static void setWallZToAir(World world, int x, int z1, int z2, int y, int height) {
		for (int z = z1; z <= z2; z++) {
			setPillarToAir(world, x, y, z, height);
		}
	}

	/*
	 * 囲うように壁を設置(x1, z1)～(x2 ,z2)
	 * 条件：x1＜x2かつz1＜z2
	 */
	public static void setWall(World world, Block block, int x1, int x2, int z1, int z2, int y, int height) {
		setFourPillar(world, block, x1, x2, z1, z2, y, height);
		setWallX(world, block, x1 +1, x2 -1, z1, y, height);
		setWallX(world, block, x1 +1, x2 -1, z2, y, height);
		setWallZ(world, block, x1, z1 +1, z2 -1, y, height);
		setWallZ(world, block, x2, z1 +1, z2 -1, y, height);
	}

	public static void setWall(World world, Block block, int x1, int z1, int width, int y, int height) {
		setFourPillar(world, block, x1, x1 +width, z1, z1 +width, y, height);
		setWallX(world, block, x1 +1, x1 +width -1, z1, y, height);
		setWallX(world, block, x1 +1, x1 +width -1, z1 +width, y, height);
		setWallZ(world, block, x1, z1 +1, z1 +width -1, y, height);
		setWallZ(world, block, x1 +width, z1 +1, z1 +width -1, y, height);
	}

	/*
	 * 高さが1の場合
	 * 囲うようにブロックを設置(x1, z1)～(x2 ,z2)
	 * 条件：x1＜x2かつz1＜z2
	 */
	public static void setBlockEnclosure(World world, Block block, int x1, int x2, int z1, int z2, int y) {
		LadDecorationCross.setFourBlock(world, block, x1, x2, z1, z2, y);
		LadFillBlock.fillBlockX(world, block, x1 +1, x2 -1, z1, y);
		LadFillBlock.fillBlockX(world, block, x1 +1, x2 -1, z2, y);
		LadFillBlock.fillBlockZ(world, block, x1, z1 +1, z2 -1, y);
		LadFillBlock.fillBlockZ(world, block, x2, z1 +1, z2 -1, y);
	}

	public static void setBlockEnclosure(World world, Block block, int x1, int z1, int width, int y) {
		LadDecorationCross.setFourBlock(world, block, x1, x1 +width, z1, z1 +width, y);
		LadFillBlock.fillBlockX(world, block, x1 +1, x1 +width -1, z1, y);
		LadFillBlock.fillBlockX(world, block, x1 +1, x1 +width -1, z1 +width, y);
		LadFillBlock.fillBlockZ(world, block, x1, z1 +1, z1 +width -1, y);
		LadFillBlock.fillBlockZ(world, block, x1 +width, z1 +1, z1 +width -1, y);
	}
}
