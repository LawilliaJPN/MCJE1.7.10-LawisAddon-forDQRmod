package lawisAddonDqr1.event.rooms.decoration;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;

/*
 * 戦闘部屋を生成する時に利用するメソッドを集めたクラスのうちの1つ。
 *
 * 砂漠系統の装飾を設置するためのメソッド。
 */
public class LadDecorationDesert {
	/*
	 * 砂漠の井戸を生成するメソッド
	 */
	public static void setDesertWell(World world, int centerX, int centerZ, int floorY){
		// 井戸の底に「砂岩ブロック」を敷く
		for (int x = centerX -2; x <= centerX +2; x++) {
			for (int z = centerZ -2; z <= centerZ +2; z++) {
				for (int y = -1; y <= 0; y++) {
					world.setBlock(x, floorY +y, z, Blocks.sandstone);
				}
			}
		}

		// 井戸の中に「水」を敷く
		LadDecorationFloor.setBlockAndAirCross(world, Blocks.water, centerX, centerZ, floorY -1);

		// 井戸の周りに「砂岩ハーフブロック」を敷く
		LadDecorationCross.setFourBlockCross(world, Blocks.stone_slab, 1, centerX, centerZ, floorY, 2);

		// 柱となる「砂岩」を設置
		LadDecorationPillar.setFourPillarSlanting(world, Blocks.sandstone, centerX, centerZ, floorY +1, 2, 1);

		// 屋根となる「砂岩ハーフブロック」を設置
		for (int x = centerX -1; x <= centerX +1; x++) {
			for (int z = centerZ -1; z <= centerZ +1; z++) {
				world.setBlock(x, floorY +3, z, Blocks.stone_slab, 1, 2);
			}
		}

		// 頂点の「砂岩」を設置
		world.setBlock(centerX, floorY +3, centerZ, Blocks.sandstone);
	}

	/*
	 * サボテンや枯れ木を設置するメソッド
	 */
	public static void setCactusOrDeadbush(World world, Random rand, int x, int z, int roomY) {
		int r = rand.nextInt(8);

		if (r/4 == 1) {
			world.setBlock(x, roomY, z, Blocks.deadbush);
		} else {
			for (int y = 0; y < r; y++) {
				world.setBlock(x, roomY +y, z, Blocks.cactus);
			}
		}
	}

	/*
	 * 砂岩の柱を設置するメソッド
	 */
	public static void setSandstonePillar(World world, Random rand, int x, int z, int roomY) {
		world.setBlock(x, roomY, z, Blocks.sandstone, 2, 2);
		world.setBlock(x, roomY +1, z, Blocks.sandstone, 2, 2);
		world.setBlock(x, roomY +2, z, Blocks.sandstone, 1, 2);
	}
}
