package lawisAddonDqr1.event.rooms.decoration;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;

/*
 * 戦闘部屋を生成する時に利用するメソッドを集めたクラスのうちの1つ。
 *
 * 農作物を設置するためのメソッド。
 */
public class LadDecorationCrops {
	/*
	 * サトウキビを生成するメソッド
	 */
	public static void setReeds(World world, Random rand, int x, int z, int y) {
		int height = rand.nextInt(3) +1;
		LadDecorationPillar.setPillar(world, Blocks.reeds, x, z, y, height);
	}
	public static void setReedsX(World world, Random rand, int x1, int x2, int z, int y) {
		int height = 0;

		for (int x = x1; x <= x2; x++) {
			height = rand.nextInt(3) +1;
			LadDecorationPillar.setPillar(world, Blocks.reeds, x, z, y, height);
		}
	}
	public static void setReedsZ(World world, Random rand, int x, int z1, int z2, int y) {
		int height = 0;

		for (int z = z1; z <= z2; z++) {
			height = rand.nextInt(3) +1;
			LadDecorationPillar.setPillar(world, Blocks.reeds, x, z, y, height);
		}
	}

	/*
	 * キノコを設置するかもしれない。
	 */
	public static void setMushroom(World world, Random rand, int x, int y, int z) {
		switch (rand.nextInt(4)) {
		case 0:
			world.setBlock(x, y, z, Blocks.brown_mushroom);
			break;
		case 1:
			world.setBlock(x, y, z, Blocks.red_mushroom);
			break;
		}
	}
}
