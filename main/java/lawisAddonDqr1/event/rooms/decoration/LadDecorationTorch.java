package lawisAddonDqr1.event.rooms.decoration;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;

/*
 * 戦闘部屋を生成する時に利用するメソッドを集めたクラスのうちの1つ。
 *
 * 松明を設置するためのメソッド。
 */
public class LadDecorationTorch {
	/*
	 * 松明を中心を基点に四方向に設置
	 */
	public static void setTorchCenter (World world, int x, int z, int y, int interval) {
		world.setBlock(x, y, z -interval, Blocks.torch, 5, 3);
		world.setBlock(x, y, z +interval, Blocks.torch, 5, 3);
		world.setBlock(x +interval, y, z, Blocks.torch, 5, 3);
		world.setBlock(x -interval, y, z, Blocks.torch, 5, 3);
	}
	/*
	 * 松明を中心を基点に斜め四方向に設置
	 */
	public static void setTorchCenterSlanting (World world, int x, int z, int y, int interval) {
		world.setBlock(x -interval, y, z -interval, Blocks.torch, 5, 3);
		world.setBlock(x -interval, y, z +interval, Blocks.torch, 5, 3);
		world.setBlock(x +interval, y, z -interval, Blocks.torch, 5, 3);
		world.setBlock(x +interval, y, z +interval, Blocks.torch, 5, 3);
	}
}
