package lawisAddonDqr1.event.entities;

import net.minecraft.entity.player.EntityPlayer;
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

		if (player.posX < 0) x2 -= 0.5D;
		else x2 += 0.5D;
		if (player.posZ < 0) z2 -= 0.5D;
		else z2 += 0.5D;

		player.setPositionAndUpdate(x2, y, z2);
	}
}
