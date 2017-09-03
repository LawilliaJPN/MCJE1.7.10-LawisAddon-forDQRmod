package lawisAddonDqr1.block.blocks;

import dqr.api.potion.DQPotionPlus;
import dqr.blocks.standard.DqmJumpBlock2;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

/*
 * 追加した「ジャンプブロック」のクラス。
 * DQRの「ジャンプブロック」を継承し、一部仕様を変更したブロック。
 */
public class LadJumpBlock2 extends DqmJumpBlock2 {
	/*
	 * lawisAddonDqr1.block.LadInitBlocks.javaにて一部仕様変更。
	 */
	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity e) {

		// 元のブロックはプレイヤーのみだが、生物全般が効果を受ける
		if (e instanceof EntityLivingBase) {
			EntityLivingBase entity = (EntityLivingBase)e;

			// ジャンプ（元のブロックから変更なし）
			entity.motionY = 1.3;

			// XZ方向への加速度を増加させる（追加した仕様）
			entity.motionX *= 1.5;
			entity.motionZ *= 1.5;

			// 落下ダメージをなくすための、ポーション付与（元のブロックから変更なし）
			entity.addPotionEffect(new PotionEffect(DQPotionPlus.potionSubayasanotane.id, 20 * 5, 0));
		}
    }
}