package lawisAddonDqr1.event.rooms.decoration;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;

/*
 * 戦闘部屋を生成する時に利用するメソッドを集めたクラスのうちの1つ。
 *
 * 松明を設置するためのメソッド。
 */
public class LadDecorationTorch {
	/* - - - - - - - - - -
	 * 基本メソッド
	 * - - - - - - - - - */

	/*
	 * 四箇所に松明を設置
	 */
	public static void setFourTorch(World world, int x1, int x2, int z1, int z2, int y) {
		world.setBlock(x1, y, z1, Blocks.torch, 5, 3);
		world.setBlock(x1, y, z2, Blocks.torch, 5, 3);
		world.setBlock(x2, y, z1, Blocks.torch, 5, 3);
		world.setBlock(x2, y, z2, Blocks.torch, 5, 3);
	}

	/*
	 * 一頂点を基点に斜め四方向に松明を設置
	 */
	public static void setFourTorchSlanting(World world, int x, int z, int y, int width, int margin) {
		world.setBlock(x +margin, y, z +margin, Blocks.torch, 5, 3);
		world.setBlock(x +margin, y, z +width - margin, Blocks.torch, 5, 3);
		world.setBlock(x +width - margin, y, z +margin, Blocks.torch, 5, 3);
		world.setBlock(x +width - margin, y, z +width - margin, Blocks.torch, 5, 3);
	}

	/*
	 * 中心を基点に四方向(東西南北)に松明を設置
	 */
	public static void setFourTorchCenterCross(World world, int x, int z, int y, int interval) {
		world.setBlock(x, y, z -interval, Blocks.torch, 5, 3);
		world.setBlock(x, y, z +interval, Blocks.torch, 5, 3);
		world.setBlock(x +interval, y, z, Blocks.torch, 5, 3);
		world.setBlock(x -interval, y, z, Blocks.torch, 5, 3);
	}

	/*
	 * 中心を基点に斜め四方向に松明を設置
	 */
	public static void setFourTorchCenterSlanting(World world, int x, int z, int y, int interval) {
		world.setBlock(x -interval, y, z -interval, Blocks.torch, 5, 3);
		world.setBlock(x -interval, y, z +interval, Blocks.torch, 5, 3);
		world.setBlock(x +interval, y, z -interval, Blocks.torch, 5, 3);
		world.setBlock(x +interval, y, z +interval, Blocks.torch, 5, 3);
	}

	/*
	 * ブロックを囲うように松明を設置
	 */
	public static void setFourTorchEnclosure(World world, int x, int z, int y) {
		world.setBlock(x, y, z +1, Blocks.torch, 3, 3);
		world.setBlock(x, y, z -1, Blocks.torch, 4, 3);
		world.setBlock(x +1, y, z, Blocks.torch, 1, 3);
		world.setBlock(x -1, y, z, Blocks.torch, 2, 3);
	}

	/* - - - - - - - - - -
	 * 応用メソッド
	 * - - - - - - - - - */
	/*
	 * 村の街灯を設置
	 */
	public static void setVillageLight(World world, int x, int z, int y) {
		// 村の灯りのパーツ「フェンス」を設置
		LadDecorationPillar.setPillar(world, Blocks.fence, x, z, y, 3);
		// 村の灯りのパーツ「黒色の羊毛」を設置
		world.setBlock(x, y +3, z, Blocks.wool, 15, 2);
		// 「黒色の羊毛」の周りに「松明」の設置
		setFourTorchEnclosure(world, x, z, y +3);
	}
}
