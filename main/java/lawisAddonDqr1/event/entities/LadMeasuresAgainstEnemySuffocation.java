package lawisAddonDqr1.event.entities;

import java.util.Random;

import dqr.api.Blocks.DQBlocks;
import dqr.entity.mobEntity.DqmMobBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

/*
 * 敵がブロックを破壊する処理
 */
public class LadMeasuresAgainstEnemySuffocation {
	/*
	 * 周囲のブロックを破壊する
	 */
	public static void enemyBreakBlock(DqmMobBase enemy, EntityPlayer player) {
		World world = enemy.worldObj;
		Random rand = new Random();

		int x = (int)enemy.posX;
		int y = (int)enemy.posY;
		int z = (int)enemy.posZ;

		if (enemy.posX < 0) x -= 1;
		if (enemy.posZ < 0) z -= 1;

		// 敵の攻撃力の高さ
		int breakPW = (int)(enemy.DqmMobPW *0.1D) +1;
		int breakRange = (breakPW /4) *2 +3;

		for (int i = 1; i <= breakPW; i++) {
			// 破壊するブロックの抽選
			x = x +rand.nextInt(breakRange) -(breakRange /2);
			y = y +rand.nextInt(5) -1;
			z = z +rand.nextInt(breakRange) -(breakRange /2);
			Block block = world.getBlock(x, y, z);

			// 「空気」と「液体」と「DQRのスポブロ」は破壊しない
			if (!(block.isAir(world, x, y, z)) && !(block instanceof BlockLiquid)) {
				if (!(block == Blocks.bedrock) && !(block == DQBlocks.DqmBlockMobSpawner)) {
					// ログのためのブロック名を破壊前に事前取得
					String strBlock = world.getBlock(x, y, z).getLocalizedName();
					String strSound = "dig." + world.getBlock(x, y, z).stepSound.soundName;

					// 名前の設定されていないブロックのログでの表現変更
					if (strBlock.equals("tile.dirt.name")) strBlock = "土ブロック";
					else if (strBlock.equals("tile.sand.name")) strBlock = "砂ブロック";
					else if (strBlock.startsWith("tile.")) strBlock = "ブロック";

					// 以下、サーバー側のみ
					if (!world.isRemote) {
						// 空気を設置
						world.setBlockToAir(x, y, z);
						// 効果音
						world.playSoundAtEntity(enemy, strSound, 1.0F, 1.0F);
						// チャットで報告
						player.addChatMessage(new ChatComponentTranslation(enemy.getCommandSenderName() + "が" + strBlock + "を破壊した"));
					}
				}
			}
		}
	}
}

/* 未使用
public static void enemyBreakBlockInWall(EntityLivingBase enemy) {
	World world = enemy.worldObj;
	Random rand = new Random();

	int x = (int)enemy.posX;
	int y = (int)enemy.posY;
	int z = (int)enemy.posZ;

	Block block1 = world.getBlock(x, y, z);
	Block block2 = world.getBlock(x, y +1, z);

	// 周囲のEntityをListに入れる
	List list = enemy.worldObj.getEntitiesWithinAABBExcludingEntity(enemy,
			enemy.boundingBox.addCoord(enemy.motionX, enemy.motionY, enemy.motionZ).expand(20.0D, 10.0D, 20.0D));

	// Listが空の場合とnullの場合を除く
    if (list != null && !list.isEmpty()) {
    	// Listに入っているEntityを一匹ずつ確認
    	for (int n = 0 ; n < list.size() ; n++) {
    		Entity entity = (Entity)list.get(n);

    		if (entity != null) {
    			// Entityがプレイヤーだった場合
    			if(entity instanceof EntityPlayer) {
    				EntityPlayer player = (EntityPlayer)entity;

    				if ((block1.canEntityDestroy(world, x, y, z, player)) && (block2.canEntityDestroy(world, x, y, z, player))) {
    					// 以下、サーバー側のみ
    					if (!world.isRemote) {
    						// 空気を設置
    						world.setBlockToAir(x, y, z);
    						// 効果音
    						world.playSoundAtEntity(enemy, "dig.stone", 1.0F, 1.0F);
    						// チャットで報告
    						player.addChatMessage(new ChatComponentTranslation(enemy.getCommandSenderName() + "が周囲のブロックを破壊した"));
    					}
    				}
    				return;
    			}
    		}
    	}
    }
}*/