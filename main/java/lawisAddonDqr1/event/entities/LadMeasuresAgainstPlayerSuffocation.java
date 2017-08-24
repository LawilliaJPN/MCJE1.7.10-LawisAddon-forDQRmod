package lawisAddonDqr1.event.entities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class LadMeasuresAgainstPlayerSuffocation {
	/*
	 * 戦闘部屋生成時のプレイヤーの窒息対策処理。
	 * プレイヤーの周囲にいるペットをプレイヤーの位置に飛ばす。
	 *
	 * DQRの「ルーラの杖」のコードを参考にさせていただきました。
	 */
	public static void adjustPlayerPos(World world, EntityPlayer player) {
		int x = (int)player.posX;
		int y = (int)player.posY;
		int z = (int)player.posZ;
		player.setPositionAndUpdate(x +0.5D, y, z +0.5D);
	}
}
