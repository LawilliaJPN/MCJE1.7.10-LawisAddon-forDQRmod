package lawisAddonDqr1.event.entities;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class LadMeasuresAgainstPlayerSuffocation {
	/*
	 * 戦闘部屋生成時のプレイヤーの窒息対策処理。
	 * プレイヤーをブロックの中央に移動させる。
	 */
	public static void adjustPlayerPos(World world, EntityPlayer player) {
		int x = (int)player.posX;
		int y = (int)player.posY;
		int z = (int)player.posZ;

		double x2 = x;
		double z2 = z;

		/*
		 * それぞれ正か負かによって±0.5Dをする。
		 *
		 * 座標がマイナスの時に＋0.5Dをすると1マスずれてしまうので、-0.5Dとする必要性がある。
		 * （例：座標-5.6をintでキャストすると-5になり、そこに＋0.5Dすると座標-4.5となるため、1マスずれることとなる。）
		 */
		if (player.posX < 0) x2 -= 0.5D;
		else x2 += 0.5D;
		if (player.posZ < 0) z2 -= 0.5D;
		else z2 += 0.5D;

		player.setPositionAndUpdate(x2, y, z2);
	}

	/*
	 * 屋根のない戦闘部屋が生成された時に、
	 * 上から落下するブロックが落ちてこないようにする処理。
	 */
	public static void measuresAgainstFallingObject(World world, int roomX, int roomZ, int roomWidthX, int roomWidthZ, int roomCeilingY) {
		for (int x = roomX; x <= roomX +roomWidthX; x++) {
			for (int z = roomZ; z <= roomZ + roomWidthZ; z++) {
				Block block = world.getBlock(x, roomCeilingY, z);

				if ((block.isAir(world, x, roomCeilingY, z)) || (block instanceof BlockLiquid) || (block instanceof BlockFalling)) {
					world.setBlock(x, roomCeilingY, z, Blocks.cobblestone);
				}
			}
		}
	}
}