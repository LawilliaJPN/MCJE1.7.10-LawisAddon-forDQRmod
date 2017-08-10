package lawisAddonDqr1.event.enemies;

import java.util.Random;

import dqr.entity.mobEntity.monsterDay.DqmEntityBigCrow;
import dqr.entity.mobEntity.monsterDay.DqmEntityBigguhatto;
import dqr.entity.mobEntity.monsterDay.DqmEntityBubsura;
import dqr.entity.mobEntity.monsterDay.DqmEntityButisuraimu;
import dqr.entity.mobEntity.monsterDay.DqmEntityDokuroarai;
import dqr.entity.mobEntity.monsterDay.DqmEntityDorozara;
import dqr.entity.mobEntity.monsterDay.DqmEntityGuntaigani;
import dqr.entity.mobEntity.monsterDay.DqmEntityIkkakuusagi;
import dqr.entity.mobEntity.monsterDay.DqmEntityItamogu;
import dqr.entity.mobEntity.monsterDay.DqmEntityKirikabuobake;
import dqr.entity.mobEntity.monsterDay.DqmEntityMadohando;
import dqr.entity.mobEntity.monsterDay.DqmEntityMomon;
import dqr.entity.mobEntity.monsterDay.DqmEntityMomonja;
import dqr.entity.mobEntity.monsterDay.DqmEntityObakekinoko;
import dqr.entity.mobEntity.monsterDay.DqmEntityOnikozou;
import dqr.entity.mobEntity.monsterDay.DqmEntityOokiduti;
import dqr.entity.mobEntity.monsterDay.DqmEntityOomedama;
import dqr.entity.mobEntity.monsterDay.DqmEntityOonamekuji;
import dqr.entity.mobEntity.monsterDay.DqmEntityRemonsuraimu;
import dqr.entity.mobEntity.monsterDay.DqmEntityRippusu;
import dqr.entity.mobEntity.monsterDay.DqmEntitySabotenboru;
import dqr.entity.mobEntity.monsterDay.DqmEntitySimasimacat;
import dqr.entity.mobEntity.monsterDay.DqmEntitySukippaa;
import dqr.entity.mobEntity.monsterDay.DqmEntitySura;
import dqr.entity.mobEntity.monsterDay.DqmEntitySuraimubesu;
import dqr.entity.mobEntity.monsterDay.DqmEntityTogebouzu;
import dqr.entity.mobEntity.monsterDay.DqmEntityZinmentyou;
import dqr.entity.mobEntity.monsterDay.DqmEntityZukkinya;
import dqr.entity.mobEntity.monsterEtc.DqmEntityGizumo;
import lawisAddonDqr1.config.LadDebug;
import lawisAddonDqr1.event.rooms.RoomID;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class SpawnEnemyCore {
	/*
	 * 敵をスポーンさせる処理
	 *
	 * enemyGroup：1～2桁「戦闘部屋のID」、3桁目「戦闘部屋のパターン」、4桁目「敵の分類」
	 */
	public static void spawnEnemy(World world, EntityPlayer player, int x, int y, int z, int enemyGroup) {
		// System.out.println("spawnEnemy OK");
		if (LadDebug.getDebugRoom() >= 0) {
			world.setBlock(x, y-1, z, Blocks.diamond_block);
		}

		Random rand = new Random();
		EntityLiving entity = null;
		Boolean encounterLog = true;

		/* 森林 */
		// 森林 Y=41～45
		if (enemyGroup == RoomID.roomForest +1) {
			int r = rand.nextInt(12);

			if (r == 0) entity = new DqmEntitySura(world);
			else if (r == 1) entity = new DqmEntityZinmentyou(world);
			else if (r == 2) entity = new DqmEntityBigCrow(world);
			else if (r == 3) entity = new DqmEntityMomon(world);
			else if (r == 4) entity = new DqmEntityButisuraimu(world);
			else if (r == 5) entity = new DqmEntityZukkinya(world);
			else if (r == 6) entity = new DqmEntityTogebouzu(world);
			else if (r == 7) entity = new DqmEntityItamogu(world);
			else if (r == 8) entity = new DqmEntityRippusu(world);
			else if (r == 9) entity = new DqmEntityObakekinoko(world);
			else if (r == 10) entity = new DqmEntityOomedama(world);
			else if (r == 11) entity = new DqmEntityKirikabuobake(world);

		/* 村の井戸 */
		// 井戸（通常） Y=41～45
		} else if (enemyGroup == RoomID.roomGrassWell +1) {
			int r = rand.nextInt(12);

			if (r == 0) entity = new DqmEntityOonamekuji(world);
			else if (r == 1) entity = new DqmEntityZinmentyou(world);
			else if (r == 2) entity = new DqmEntityBigCrow(world);
			else if (r == 3) entity = new DqmEntityMomon(world);
			else if (r == 4) entity = new DqmEntityRemonsuraimu(world);
			else if (r == 5) entity = new DqmEntityTogebouzu(world);
			else if (r == 6) entity = new DqmEntityBubsura(world);
			else if (r == 7) entity = new DqmEntityOnikozou(world);
			else if (r == 8) entity = new DqmEntityRippusu(world);
			else if (r == 9) entity = new DqmEntitySukippaa(world);
			else if (r == 10) entity = new DqmEntityGuntaigani(world);
			else if (r == 11) entity = new DqmEntityOomedama(world);

		// 「呪われた井戸」イレギュラーパターン →バニラのゾンビ
		} else if (enemyGroup == RoomID.roomGrassWellIsCursed) {
			entity = new EntityZombie(world);
			encounterLog = false;

		/* 砂浜 */
		// 砂浜 Y=41～45
		} else if (enemyGroup == RoomID.roomBeach + 1) {
			int r = rand.nextInt(12);

			if (r == 0) entity = new DqmEntitySura(world);
			else if (r == 1) entity = new DqmEntitySuraimubesu(world);
			else if (r == 2) entity = new DqmEntityDorozara(world);
			else if (r == 3) entity = new DqmEntityRemonsuraimu(world);
			else if (r == 4) entity = new DqmEntityZukkinya(world);
			else if (r == 5) entity = new DqmEntityBubsura(world);
			else if (r == 6) entity = new DqmEntitySimasimacat(world);
			else if (r == 7) entity = new DqmEntityBigguhatto(world);
			else if (r == 8) entity = new DqmEntityIkkakuusagi(world);
			else if (r == 9) entity = new DqmEntityMomonja(world);
			else if (r == 10) entity = new DqmEntityGuntaigani(world);
			else if (r == 11) entity = new DqmEntityOokiduti(world);

		/* 砂漠の井戸 */
		// 砂漠の井戸 Y=41～45
		} else if (enemyGroup == RoomID.roomDesertWell + 1) {
			int r = rand.nextInt(12);

			if (r == 0) entity = new DqmEntitySuraimubesu(world);
			else if (r == 1) entity = new DqmEntityOonamekuji(world);
			else if (r == 2) entity = new DqmEntityDorozara(world);
			else if (r == 3) entity = new DqmEntityButisuraimu(world);
			else if (r == 4) entity = new DqmEntityTogebouzu(world);
			else if (r == 5) entity = new DqmEntityItamogu(world);
			else if (r == 6) entity = new DqmEntityOnikozou(world);
			else if (r == 7) entity = new DqmEntityGizumo(world);
			else if (r == 8) entity = new DqmEntityOokiduti(world);
			else if (r == 9) entity = new DqmEntityDokuroarai(world);
			else if (r == 10) entity = new DqmEntityMadohando(world);
			else if (r == 11) entity = new DqmEntitySabotenboru(world);

		/* 氷原 */

		/* 武器屋 */

		/* 特殊部屋1 */
		// 特殊部屋1 Y=41～45
		} else if (enemyGroup == RoomID.roomSpecial01 + 1) {
			int r = rand.nextInt(29);

			if (r == 0) entity = new DqmEntitySura(world);
			else if (r == 1) entity = new DqmEntitySuraimubesu(world);
			else if (r == 2) entity = new DqmEntityOonamekuji(world);
			else if (r == 3) entity = new DqmEntityZinmentyou(world);
			else if (r == 4) entity = new DqmEntityBigCrow(world);
			else if (r == 5) entity = new DqmEntityDorozara(world);
			else if (r == 6) entity = new DqmEntityMomon(world);
			else if (r == 7) entity = new DqmEntityButisuraimu(world);
			else if (r == 8) entity = new DqmEntityRemonsuraimu(world);
			else if (r == 9) entity = new DqmEntityTogebouzu(world);
			else if (r == 10) entity = new DqmEntityZukkinya(world);
			else if (r == 11) entity = new DqmEntityBubsura(world);
			else if (r == 12) entity = new DqmEntityItamogu(world);
			else if (r == 13) entity = new DqmEntitySimasimacat(world);
			else if (r == 14) entity = new DqmEntityBigguhatto(world);
			else if (r == 15) entity = new DqmEntityOnikozou(world);
			else if (r == 16) entity = new DqmEntityRippusu(world);
			else if (r == 17) entity = new DqmEntitySukippaa(world);
			else if (r == 18) entity = new DqmEntityObakekinoko(world);
			else if (r == 19) entity = new DqmEntityIkkakuusagi(world);
			else if (r == 20) entity = new DqmEntityGizumo(world);
			else if (r == 21) entity = new DqmEntityMomonja(world);
			else if (r == 22) entity = new DqmEntityGuntaigani(world);
			else if (r == 23) entity = new DqmEntityOomedama(world);
			else if (r == 24) entity = new DqmEntityOokiduti(world);
			else if (r == 25) entity = new DqmEntityKirikabuobake(world);
			else if (r == 26) entity = new DqmEntityDokuroarai(world);
			else if (r == 27) entity = new DqmEntityMadohando(world);
			else if (r == 28) entity = new DqmEntitySabotenboru(world);

		}

		if (entity == null) return;
		entity.setLocationAndAngles(x +0.5D, y, z +0.5D, MathHelper.wrapAngleTo180_float(world.rand.nextFloat() * 360.0F), 0.0F);
		entity.rotationYawHead = entity.rotationYaw;
		entity.renderYawOffset = entity.rotationYaw;
		entity.onSpawnWithEgg((IEntityLivingData)null);
        world.spawnEntityInWorld(entity);
        entity.playLivingSound();

        if (encounterLog) {
        	player.addChatMessage(new ChatComponentTranslation(entity.getCommandSenderName() + "が あらわれた！"));
        }
	}
}
