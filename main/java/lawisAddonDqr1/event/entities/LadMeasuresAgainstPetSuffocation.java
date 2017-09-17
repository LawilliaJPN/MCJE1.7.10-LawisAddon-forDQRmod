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
	 *
	 * TODO ペットの検索範囲の調整
	 */
	public static void pullPets(World world, EntityPlayer player) {
		// プレイヤーの周囲のEntityをListに入れる
		List list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player,
        		player.boundingBox.addCoord(player.motionX, player.motionY, player.motionZ).expand(20.0D, 10.0D, 20.0D));

		// Listが空の場合とnullの場合を除く
        if (list != null && !list.isEmpty()) {
        	// Listに入っているEntityを一匹ずつ確認
        	for (int n = 0 ; n < list.size() ; n++) {
        		Entity target = (Entity)list.get(n);

        		if (target != null) {
        			// EntityがDQRのペットだった場合
        			if (target instanceof DqmPetBase) {
        				// 以下、自分のペットかどうかの確認
						DqmPetBase petMob = (DqmPetBase)target;
        				EntityLivingBase petMobE = petMob.getOwner();

        				if(petMobE != null) {
        					String tameUuid = petMobE.getUniqueID().toString();
            				if(player.getUniqueID().toString().equalsIgnoreCase(tameUuid)) {

            					// 自分のペットは自分の位置へワープさせる
            					petMob.setPositionAndUpdate(player.posX, player.posY, player.posZ);
            				}
        				}
					}
        		}
        	}
        }
	}
}
