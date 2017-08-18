package lawisAddonDqr1.event.entities;

import java.util.List;

import dqr.entity.petEntity.DqmPetBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class LadMeasuresAgainstPetSuffocation {
	/*
	 * 戦闘部屋生成時のペットの窒息対策処理。
	 * プレイヤーの周囲にいるペットをプレイヤーの位置に飛ばす。
	 *
	 * DQRの「ルーラの杖」のコードを参考にさせていただきました。
	 */
	public static void pullPets(World world, EntityPlayer player) {
		List list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player,
        		player.boundingBox.addCoord(player.motionX, player.motionY, player.motionZ).expand(10.0D, 5.0D, 10.0D));

        if (list != null && !list.isEmpty()) {
        	for (int n = 0 ; n < list.size() ; n++) {
        		Entity target = (Entity)list.get(n);

        		if (target != null) {
        			if(target instanceof DqmPetBase) {
						DqmPetBase petMob = (DqmPetBase)target;
        				EntityLivingBase petMobE = petMob.getOwner();

        				if(petMobE != null) {
        					String tameUuid = petMobE.getUniqueID().toString();

            				if(player.getUniqueID().toString().equalsIgnoreCase(tameUuid)) {
            					petMob.setPositionAndUpdate(player.posX, player.posY + 0.5D, player.posZ);
            				}
        				}
					}
        		}
        	}
        }
	}
}
