package lawisAddonDqr1.event;

import lawisAddonDqr1.event.rooms.Room12WeaponShop;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class MiningPenalty {
	public MiningPenalty(int blockX, int blockY, int blockZ, World world,
			Block block, int blockMetadata, EntityPlayer player) {
		// 疑似乱数
		int numOfRooms = 2;
		int r = (blockX + blockY + blockZ)%numOfRooms;


		if (!world.isRemote) {
			new Room12WeaponShop(world, block, player, getDirectionStone(player, 0));
			// new Room11GrassWell(world, block, player, getDirectionStone(player, 1));
		}
	}

	public static int getDirectionStone (EntityPlayer player, int i) {
		/* i == 0 -> 上下左右, i == 1 ->斜め
		   ,-0+X
		  -,130
		  0,0P2
		  +,213
		  Z
		*/

		switch(i){
		case 0:
			return MathHelper.floor_double((double)((player.rotationYaw +180.0F) *4.0F /360.0F) -0.5D) & 3;
		case 1:
			return MathHelper.floor_double((double)((player.rotationYaw +180.0F +45.0F) *4.0F /360.0F) -0.5D) & 3;
		}

		return 0;
	}
}

