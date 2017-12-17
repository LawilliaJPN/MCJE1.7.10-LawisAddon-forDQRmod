package lawisAddonDqr1.event.rooms.decoration;

import lawisAddonDqr1.api.blocks.LadBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

/*
 * 戦闘部屋を生成する時に利用するメソッドを集めたクラスのうちの1つ。
 *
 * 氷の装飾を設置するためのメソッド。
 */
public class LadDecorationIce {
	/*
	 * 氷の木を生成するメソッド
	 */
	public static void setIceTree(World world, int x, int z, int y, int roomDepth, int roomHeight) {
		// 柱となる「氷塊」を設置する
		LadDecorationPillar.setPillar(world, LadBlocks.ladPackedIce, x, z, y +roomDepth, roomHeight -roomDepth +1);

		// 3段目の「氷塊」を設置する
		LadDecorationCross.setFourBlockCross(world, LadBlocks.ladPackedIce, x, z, y +3, 1);

		// 4段目の「氷塊」を設置する
		LadDecorationCross.setFourBlockSlanting(world, LadBlocks.ladPackedIce, x, z, y +4, 1);
	}

	/*
	 * 水中の氷の木を生成するメソッド
	 */
	public static void setIceTreeInWater(World world, int x, int z, int y, int roomDepth) {
		// 柱となる「氷塊」を設置する
		LadDecorationPillar.setPillar(world, LadBlocks.ladPackedIce, x, z, y +roomDepth, -roomDepth -1);

		// 3段目の「氷塊」を設置する
		LadDecorationCross.setFourBlockCross(world, LadBlocks.ladPackedIce, x, z, y +roomDepth +2, 1);

		// 4段目の「氷塊」を設置する
		LadDecorationCross.setFourBlockSlanting(world, LadBlocks.ladPackedIce, x, z, y +roomDepth +3, 1);
	}

	/*
	 * 氷の柱を設置するメソッド
	 *
	 * hasInvertedがtrueの時は、天井に近いほど大きい
	 * falseの時は、床に近いほど大きい
	 */
	public static void setIcePillar(World world, int x, int z, int floorY, int height, boolean hasInverted) {
		for (int y = 0; y <= height; y++){
			int i = 0;

			if (hasInverted) {
				if (y == height) i = 3;
				else if (y >= height -2) i = 2;
				else if (y >= height -5) i = 1;
			} else {
				if (y == 0) i = 3;
				else if (y <= 2) i = 2;
				else if (y <= 5) i = 1;
			}

			LadDecorationCross.setBlockDiamond(world, LadBlocks.ladPackedIce, x, z, floorY +y +1, i);
		}
	}

	/*
	 * 三角柱の氷を設置するメソッド
	 *
	 * hasInvertedがtrueの時は、天井に近いほど大きい
	 * falseの時は、床に近いほど大きい
	 */
	public static void setIceTriangularPrism(World world, int x, int z, int floorY, boolean hasInverted) {
		if (hasInverted) {
			LadDecorationCross.setBlockCross(world, Blocks.packed_ice, x, z, floorY +1, 0);
			LadDecorationCross.setBlockCross(world, Blocks.packed_ice, x, z, floorY +2, 1);
			LadDecorationCross.setBlockDiamond(world, Blocks.packed_ice, x, z, floorY +3, 2);
		} else {
			LadDecorationCross.setBlockCross(world, Blocks.packed_ice, x, z, floorY +3, 0);
			LadDecorationCross.setBlockCross(world, Blocks.packed_ice, x, z, floorY +2, 1);
			LadDecorationCross.setBlockDiamond(world, Blocks.packed_ice, x, z, floorY +1, 2);
		}
	}

	/*
	 * 中央の柱を噴水のようにするメソッド
	 */
	public static void setWater(World world, int x, int z, int floorY, int height) {
		world.setBlock(x, floorY +height +1, z, Blocks.flowing_water);

		for (int i = 0; i <= 2; i++) {
			world.setBlock(x +i, floorY +1, z +5 -i, LadBlocks.ladPackedIce);
			world.setBlock(x -i, floorY +1, z +5 -i, LadBlocks.ladPackedIce);
			world.setBlock(x +i, floorY +1, z -5 +i, LadBlocks.ladPackedIce);
			world.setBlock(x -i, floorY +1, z -5 +i, LadBlocks.ladPackedIce);
			world.setBlock(x +5 -i, floorY +1, z +i, LadBlocks.ladPackedIce);
			world.setBlock(x +5 -i, floorY +1, z -i, LadBlocks.ladPackedIce);
			world.setBlock(x -5 +i, floorY +1, z +i, LadBlocks.ladPackedIce);
			world.setBlock(x -5 +i, floorY +1, z -i, LadBlocks.ladPackedIce);
		}
	}
}
