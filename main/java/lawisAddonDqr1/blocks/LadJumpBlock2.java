package lawisAddonDqr1.blocks;

import dqr.api.potion.DQPotionPlus;
import dqr.blocks.standard.DqmJumpBlock2;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class LadJumpBlock2 extends DqmJumpBlock2 {
	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity e) {
		if (e instanceof EntityLivingBase) {
			EntityLivingBase entity = (EntityLivingBase)e;
			entity.motionY = 1.3;
			entity.motionX *= 1.8;
			entity.motionZ *= 1.8;

			entity.addPotionEffect(new PotionEffect(DQPotionPlus.potionSubayasanotane.id, 20 * 5, 0));
		}
    }
}