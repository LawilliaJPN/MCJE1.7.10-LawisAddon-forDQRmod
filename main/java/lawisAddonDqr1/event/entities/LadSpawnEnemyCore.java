package lawisAddonDqr1.event.entities;

import java.util.Random;

import dqr.entity.mobEntity.monsterDay.DqmEntityAyasiikage;
import dqr.entity.mobEntity.monsterDay.DqmEntityBigCrow;
import dqr.entity.mobEntity.monsterDay.DqmEntityBigguhatto;
import dqr.entity.mobEntity.monsterDay.DqmEntityBubsura;
import dqr.entity.mobEntity.monsterDay.DqmEntityBurauni;
import dqr.entity.mobEntity.monsterDay.DqmEntityButisuraimu;
import dqr.entity.mobEntity.monsterDay.DqmEntityDokuroarai;
import dqr.entity.mobEntity.monsterDay.DqmEntityDoronuba;
import dqr.entity.mobEntity.monsterDay.DqmEntityDorozara;
import dqr.entity.mobEntity.monsterDay.DqmEntityDoruido;
import dqr.entity.mobEntity.monsterDay.DqmEntityDragosuraimu;
import dqr.entity.mobEntity.monsterDay.DqmEntityDucksbill;
import dqr.entity.mobEntity.monsterDay.DqmEntityEbiruapple;
import dqr.entity.mobEntity.monsterDay.DqmEntityFaratto;
import dqr.entity.mobEntity.monsterDay.DqmEntityGaikotu;
import dqr.entity.mobEntity.monsterDay.DqmEntityGizumoAZ;
import dqr.entity.mobEntity.monsterDay.DqmEntityGuntaigani;
import dqr.entity.mobEntity.monsterDay.DqmEntityHitokuikibako;
import dqr.entity.mobEntity.monsterDay.DqmEntityHitokuisaberu;
import dqr.entity.mobEntity.monsterDay.DqmEntityHoimisura;
import dqr.entity.mobEntity.monsterDay.DqmEntityIkkakuusagi;
import dqr.entity.mobEntity.monsterDay.DqmEntityItamogu;
import dqr.entity.mobEntity.monsterDay.DqmEntityKimera;
import dqr.entity.mobEntity.monsterDay.DqmEntityKirapan;
import dqr.entity.mobEntity.monsterDay.DqmEntityKirikabuobake;
import dqr.entity.mobEntity.monsterDay.DqmEntityMadohando;
import dqr.entity.mobEntity.monsterDay.DqmEntityMomon;
import dqr.entity.mobEntity.monsterDay.DqmEntityMomonja;
import dqr.entity.mobEntity.monsterDay.DqmEntityObakekinoko;
import dqr.entity.mobEntity.monsterDay.DqmEntityObakeumiusi;
import dqr.entity.mobEntity.monsterDay.DqmEntityOnikozou;
import dqr.entity.mobEntity.monsterDay.DqmEntityOokiduti;
import dqr.entity.mobEntity.monsterDay.DqmEntityOokutibasi;
import dqr.entity.mobEntity.monsterDay.DqmEntityOomedama;
import dqr.entity.mobEntity.monsterDay.DqmEntityOonamekuji;
import dqr.entity.mobEntity.monsterDay.DqmEntityPapetkozou;
import dqr.entity.mobEntity.monsterDay.DqmEntityPurizunyan;
import dqr.entity.mobEntity.monsterDay.DqmEntityRemonsuraimu;
import dqr.entity.mobEntity.monsterDay.DqmEntityRippusu;
import dqr.entity.mobEntity.monsterDay.DqmEntityRiripat;
import dqr.entity.mobEntity.monsterDay.DqmEntitySabotenboru;
import dqr.entity.mobEntity.monsterDay.DqmEntitySibirekurage;
import dqr.entity.mobEntity.monsterDay.DqmEntitySimasimacat;
import dqr.entity.mobEntity.monsterDay.DqmEntitySirudokozou;
import dqr.entity.mobEntity.monsterDay.DqmEntitySukippaa;
import dqr.entity.mobEntity.monsterDay.DqmEntitySunomon;
import dqr.entity.mobEntity.monsterDay.DqmEntitySupini;
import dqr.entity.mobEntity.monsterDay.DqmEntitySura;
import dqr.entity.mobEntity.monsterDay.DqmEntitySuraimubesu;
import dqr.entity.mobEntity.monsterDay.DqmEntitySuraimunaito;
import dqr.entity.mobEntity.monsterDay.DqmEntitySuraimutawa;
import dqr.entity.mobEntity.monsterDay.DqmEntitySuraimutumuri;
import dqr.entity.mobEntity.monsterDay.DqmEntityTogebouzu;
import dqr.entity.mobEntity.monsterDay.DqmEntityTukaima;
import dqr.entity.mobEntity.monsterDay.DqmEntityUzusioking;
import dqr.entity.mobEntity.monsterDay.DqmEntityWaraibukuro;
import dqr.entity.mobEntity.monsterDay.DqmEntityZinmentyou;
import dqr.entity.mobEntity.monsterDay.DqmEntityZukkinya;
import dqr.entity.mobEntity.monsterEtc.DqmEntityFurosutogizumo;
import dqr.entity.mobEntity.monsterEtc.DqmEntityHiitogizumo;
import dqr.entity.mobEntity.monsterNight.DqmEntityAkumanosyo;
import dqr.entity.mobEntity.monsterNight.DqmEntityAnimaruzonbi;
import dqr.entity.mobEntity.monsterNight.DqmEntityArumiraji;
import dqr.entity.mobEntity.monsterNight.DqmEntityBaburin;
import dqr.entity.mobEntity.monsterNight.DqmEntityBakudanbebi;
import dqr.entity.mobEntity.monsterNight.DqmEntityBebisatan;
import dqr.entity.mobEntity.monsterNight.DqmEntityBehoimisuraimu;
import dqr.entity.mobEntity.monsterNight.DqmEntityBerobero;
import dqr.entity.mobEntity.monsterNight.DqmEntityBuchunpa;
import dqr.entity.mobEntity.monsterNight.DqmEntityButtizukinya;
import dqr.entity.mobEntity.monsterNight.DqmEntityDesufuratta;
import dqr.entity.mobEntity.monsterNight.DqmEntityDokuyazukin;
import dqr.entity.mobEntity.monsterNight.DqmEntityDoraki;
import dqr.entity.mobEntity.monsterNight.DqmEntityDorakima;
import dqr.entity.mobEntity.monsterNight.DqmEntityGaikotukensi;
import dqr.entity.mobEntity.monsterNight.DqmEntityGhost;
import dqr.entity.mobEntity.monsterNight.DqmEntityHerughost;
import dqr.entity.mobEntity.monsterNight.DqmEntityHitokuiga;
import dqr.entity.mobEntity.monsterNight.DqmEntityHoroghost;
import dqr.entity.mobEntity.monsterNight.DqmEntityHyouganmajin;
import dqr.entity.mobEntity.monsterNight.DqmEntityJeriman;
import dqr.entity.mobEntity.monsterNight.DqmEntityKirasuko;
import dqr.entity.mobEntity.monsterNight.DqmEntityMarinsuraimu;
import dqr.entity.mobEntity.monsterNight.DqmEntityMatango;
import dqr.entity.mobEntity.monsterNight.DqmEntityMeragosuto;
import dqr.entity.mobEntity.monsterNight.DqmEntityMetoroghost;
import dqr.entity.mobEntity.monsterNight.DqmEntityObakekyandoru;
import dqr.entity.mobEntity.monsterNight.DqmEntityOdoruhouseki;
import dqr.entity.mobEntity.monsterNight.DqmEntityPinkmomon;
import dqr.entity.mobEntity.monsterNight.DqmEntityRaimusuraimu;
import dqr.entity.mobEntity.monsterNight.DqmEntitySamayoutamasii;
import dqr.entity.mobEntity.monsterNight.DqmEntitySibireageha;
import dqr.entity.mobEntity.monsterNight.DqmEntitySibiredanbira;
import dqr.entity.mobEntity.monsterNight.DqmEntitySumoruguru;
import dqr.entity.mobEntity.monsterNight.DqmEntitySupekutetto;
import dqr.entity.mobEntity.monsterNight.DqmEntitySura2;
import dqr.entity.mobEntity.monsterNight.DqmEntitySuraimubogu;
import dqr.entity.mobEntity.monsterNight.DqmEntitySuraimuburesu;
import dqr.entity.mobEntity.monsterNight.DqmEntitySyado;
import dqr.entity.mobEntity.monsterNight.DqmEntityTahodoraki;
import dqr.entity.mobEntity.monsterNight.DqmEntityTomosibikozou;
import dqr.entity.mobEntity.monsterNight.DqmEntityTonburero;
import dqr.entity.mobEntity.monsterNight.DqmEntityTutiwarasi;
import dqr.entity.mobEntity.monsterNight.DqmEntityUmiusi;
import lawisAddonDqr1.api.event.LadEnemySpawnEvent;
import lawisAddonDqr1.config.LadDebug;
import lawisAddonDqr1.event.rooms.LadRoomID;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class LadSpawnEnemyCore {
	/*
	 * 敵をスポーンさせる処理
	 *
	 * enemyGroup：1～2桁「戦闘部屋の分類」、3桁目「同分類部屋のパターン判別」、4桁目「戦闘部屋の難易度」
	 * 1～3桁目の詳細は、lawisAddonDqr1.event.rooms.LadRoomCore.javaの変数を参照。
	 * 4桁目の詳細は、同クラスのupdateDifOfRoomメソッド（複数あり）を参照。
	 */
	public static void spawnEnemy(World world, EntityPlayer player, int x, int y, int z, int enemyGroup) {
		// System.out.println("spawnEnemy OK");

		Random rand = new Random();
		EntityLiving entity = null;

		// false にすると「～が あらわれた！」というログが表示されなくなる
		Boolean encounterLog = true;


		/*
		 * 以下、どの敵をスポーンさせるか決める処理
		 */


		/*
		 * 上層 Y= 31～45
		 */
		if (LadRoomID.getDifOfRoom() <= 3) {
			/* 森林 */
			// 森林 Y=41～45
			if (enemyGroup == LadRoomID.FOREST +1) {
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

			// 森林 Y=36～40
			} else if (enemyGroup == LadRoomID.FOREST +2) {
				int r = rand.nextInt(12);

				if (r == 0) entity = new DqmEntityRippusu(world);
				else if (r == 1) entity = new DqmEntityObakekinoko(world);
				else if (r == 2) entity = new DqmEntityDragosuraimu(world);
				else if (r == 3) entity = new DqmEntityOomedama(world);
				else if (r == 4) entity = new DqmEntityKirikabuobake(world);
				else if (r == 5) entity = new DqmEntityPapetkozou(world);
				else if (r == 6) entity = new DqmEntitySuraimutawa(world);
				else if (r == 7) entity = new DqmEntityFaratto(world);
				else if (r == 8) entity = new DqmEntityRiripat(world);
				else if (r == 9) entity = new DqmEntityOokutibasi(world);
				else if (r == 10) entity = new DqmEntityDorakima(world);
				else if (r == 11) entity = new DqmEntityEbiruapple(world);

			// 森林 Y=31～35
			} else if (enemyGroup == LadRoomID.FOREST +3) {
				int r = rand.nextInt(12);

				if (r == 0) entity = new DqmEntityDragosuraimu(world);
				else if (r == 1) entity = new DqmEntityTukaima(world);
				else if (r == 2) entity = new DqmEntityKirikabuobake(world);
				else if (r == 3) entity = new DqmEntitySuraimutawa(world);
				else if (r == 4) entity = new DqmEntityFaratto(world);
				else if (r == 5) entity = new DqmEntityRiripat(world);
				else if (r == 6) entity = new DqmEntityOokutibasi(world);
				else if (r == 7) entity = new DqmEntityKimera(world);
				else if (r == 8) entity = new DqmEntityDorakima(world);
				else if (r == 9) entity = new DqmEntityEbiruapple(world);
				else if (r == 10) entity = new DqmEntityWaraibukuro(world);
				else if (r == 11) entity = new DqmEntityHitokuiga(world);


			/* 村の井戸 */
			// 井戸（通常） Y=41～45
			} else if (enemyGroup == LadRoomID.VILLAGE_WELL +1) {
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

			// 井戸（通常） Y=31～35
			} else if (enemyGroup == LadRoomID.VILLAGE_WELL +3) {
				int r = rand.nextInt(12);

				if (r == 0) entity = new DqmEntityOomedama(world);
				else if (r == 1) entity = new DqmEntityKirikabuobake(world);
				else if (r == 2) entity = new DqmEntityObakeumiusi(world);
				else if (r == 3) entity = new DqmEntityDokuroarai(world);
				else if (r == 4) entity = new DqmEntityMeragosuto(world);
				else if (r == 5) entity = new DqmEntityPapetkozou(world);
				else if (r == 6) entity = new DqmEntitySupini(world);
				else if (r == 7) entity = new DqmEntityObakekyandoru(world);
				else if (r == 8) entity = new DqmEntityMadohando(world);
				else if (r == 9) entity = new DqmEntityUzusioking(world);
				else if (r == 10) entity = new DqmEntityHitokuiga(world);
				else if (r == 11) entity = new DqmEntityRaimusuraimu(world);

			// 「呪われた井戸」 Y=36～40 確定スポーン
			} else if (enemyGroup == LadRoomID.VILLAGE_WELL_HAS_CURSED_ON_WATER +2) {
				int r = rand.nextInt(4);

				if (r == 0) entity = new DqmEntityAyasiikage(world);
				else if (r == 1) entity = new DqmEntityGhost(world);
				else if (r == 2) entity = new DqmEntityMeragosuto(world);
				else if (r == 3) entity = new DqmEntityTutiwarasi(world);

			// 「呪われた井戸」 Y=36～40 変動スポーン
			} else if (enemyGroup == LadRoomID.VILLAGE_WELL_HAS_CURSED +2) {
				int r = rand.nextInt(8);

				if (r == 0) entity = new DqmEntityOnikozou(world);
				else if (r == 1) entity = new DqmEntitySukippaa(world);
				else if (r == 2) entity = new DqmEntityObakekinoko(world);
				else if (r == 3) entity = new DqmEntityDoraki(world);
				else if (r == 4) entity = new DqmEntityKirikabuobake(world);
				else if (r == 5) entity = new DqmEntityObakeumiusi(world);
				else if (r == 6) entity = new DqmEntityDokuroarai(world);
				else if (r == 7) entity = new DqmEntityMadohando(world);

			// 「呪われた井戸」 Y=31～35 確定スポーン
			} else if (enemyGroup == LadRoomID.VILLAGE_WELL_HAS_CURSED_ON_WATER +3) {
				int r = rand.nextInt(4);

				if (r == 0) entity = new DqmEntityMeragosuto(world);
				else if (r == 1) entity = new DqmEntityTutiwarasi(world);
				else if (r == 2) entity = new DqmEntitySyado(world);
				else if (r == 3) entity = new DqmEntityMetoroghost(world);

			// 「呪われた井戸」 Y=36～40 変動スポーン
			} else if (enemyGroup == LadRoomID.VILLAGE_WELL_HAS_CURSED +3) {
				int r = rand.nextInt(8);

				if (r == 0) entity = new DqmEntityTukaima(world);
				else if (r == 1) entity = new DqmEntityObakeumiusi(world);
				else if (r == 2) entity = new DqmEntityPapetkozou(world);
				else if (r == 3) entity = new DqmEntityObakekyandoru(world);
				else if (r == 4) entity = new DqmEntityDoruido(world);
				else if (r == 5) entity = new DqmEntityMadohando(world);
				else if (r == 6) entity = new DqmEntityDoronuba(world);
				else if (r == 7) entity = new DqmEntityGaikotu(world);

			// 「呪われた井戸」イレギュラーパターン →バニラのゾンビ
			} else if (enemyGroup == LadRoomID.VILLAGE_WELL_HAS_CURSED) {
				entity = new EntityZombie(world);
				encounterLog = false;


			/* 砂浜 */
			// 砂浜 Y=41～45
			} else if (enemyGroup == LadRoomID.BEACH +1) {
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

			// 砂浜 Y=36～40
			} else if (enemyGroup == LadRoomID.BEACH +2) {
				int r = rand.nextInt(12);

				if (r == 0) entity = new DqmEntitySimasimacat(world);
				else if (r == 1) entity = new DqmEntityBigguhatto(world);
				else if (r == 2) entity = new DqmEntityIkkakuusagi(world);
				else if (r == 3) entity = new DqmEntityGizumoAZ(world);
				else if (r == 4) entity = new DqmEntityGuntaigani(world);
				else if (r == 5) entity = new DqmEntitySuraimutumuri(world);
				else if (r == 6) entity = new DqmEntitySunomon(world);
				else if (r == 7) entity = new DqmEntitySibirekurage(world);
				else if (r == 8) entity = new DqmEntityHoimisura(world);
				else if (r == 9) entity = new DqmEntitySuraimutawa(world);
				else if (r == 10) entity = new DqmEntityDoronuba(world);
				else if (r == 11) entity = new DqmEntityUzusioking(world);

			// 砂浜 Y=31～35
			} else if (enemyGroup == LadRoomID.BEACH +3) {
				int r = rand.nextInt(12);

				if (r == 0) entity = new DqmEntityOokiduti(world);
				else if (r == 1) entity = new DqmEntitySuraimutumuri(world);
				else if (r == 2) entity = new DqmEntitySunomon(world);
				else if (r == 3) entity = new DqmEntitySibirekurage(world);
				else if (r == 4) entity = new DqmEntityHoimisura(world);
				else if (r == 5) entity = new DqmEntitySuraimutawa(world);
				else if (r == 6) entity = new DqmEntityDoronuba(world);
				else if (r == 7) entity = new DqmEntityBurauni(world);
				else if (r == 8) entity = new DqmEntityUzusioking(world);
				else if (r == 9) entity = new DqmEntityWaraibukuro(world);
				else if (r == 10) entity = new DqmEntityPurizunyan(world);
				else if (r == 11) entity = new DqmEntityRaimusuraimu(world);

			/* 砂漠の井戸 */
			// 砂漠の井戸 Y=41～45
			} else if (enemyGroup == LadRoomID.DESERT_WELL +1) {
				int r = rand.nextInt(12);

				if (r == 0) entity = new DqmEntitySuraimubesu(world);
				else if (r == 1) entity = new DqmEntityOonamekuji(world);
				else if (r == 2) entity = new DqmEntityDorozara(world);
				else if (r == 3) entity = new DqmEntityButisuraimu(world);
				else if (r == 4) entity = new DqmEntityTogebouzu(world);
				else if (r == 5) entity = new DqmEntityItamogu(world);
				else if (r == 6) entity = new DqmEntityOnikozou(world);
				else if (r == 7) entity = new DqmEntityGizumoAZ(world);
				else if (r == 8) entity = new DqmEntityOokiduti(world);
				else if (r == 9) entity = new DqmEntityDokuroarai(world);
				else if (r == 10) entity = new DqmEntityMadohando(world);
				else if (r == 11) entity = new DqmEntitySabotenboru(world);

			// 砂漠の井戸 Y=31～35
			} else if (enemyGroup == LadRoomID.DESERT_WELL +3) {
				int r = rand.nextInt(12);

				if (r == 0) entity = new DqmEntityObakeumiusi(world);
				else if (r == 1) entity = new DqmEntityDokuroarai(world);
				else if (r == 2) entity = new DqmEntityHitokuisaberu(world);
				else if (r == 3) entity = new DqmEntitySupini(world);
				else if (r == 4) entity = new DqmEntityObakekyandoru(world);
				else if (r == 5) entity = new DqmEntityKimera(world);
				else if (r == 6) entity = new DqmEntityMadohando(world);
				else if (r == 7) entity = new DqmEntitySabotenboru(world);
				else if (r == 8) entity = new DqmEntitySirudokozou(world);
				else if (r == 9) entity = new DqmEntityUzusioking(world);
				else if (r == 10) entity = new DqmEntityGaikotu(world);
				else if (r == 11) entity = new DqmEntityRaimusuraimu(world);

			/* 氷原 */
			// 氷原 Y=36～40
			} else if (enemyGroup == LadRoomID.ICE_PLAINS +2) {
				int r = rand.nextInt(12);

				if (r == 0) entity = new DqmEntityDoraki(world);
				else if (r == 1) entity = new DqmEntityIkkakuusagi(world);
				else if (r == 2) entity = new DqmEntityAyasiikage(world);
				else if (r == 3) entity = new DqmEntityMomonja(world);
				else if (r == 4) entity = new DqmEntityGuntaigani(world);
				else if (r == 5) entity = new DqmEntityGhost(world);
				else if (r == 6) entity = new DqmEntityOokiduti(world);
				else if (r == 7) entity = new DqmEntitySuraimutumuri(world);
				else if (r == 8) entity = new DqmEntitySunomon(world);
				else if (r == 9) entity = new DqmEntitySibirekurage(world);
				else if (r == 10) entity = new DqmEntityFaratto(world);
				else if (r == 11) entity = new DqmEntityPurizunyan(world);

			// 氷原 Y=31～35
			} else if (enemyGroup == LadRoomID.ICE_PLAINS +3) {
				int r = rand.nextInt(12);

				if (r == 0) entity = new DqmEntityGhost(world);
				else if (r == 1) entity = new DqmEntityOokiduti(world);
				else if (r == 2) entity = new DqmEntitySuraimutumuri(world);
				else if (r == 3) entity = new DqmEntitySunomon(world);
				else if (r == 4) entity = new DqmEntitySibirekurage(world);
				else if (r == 5) entity = new DqmEntityFaratto(world);
				else if (r == 6) entity = new DqmEntityGaikotu(world);
				else if (r == 7) entity = new DqmEntityPurizunyan(world);
				else if (r == 8) entity = new DqmEntityDucksbill(world);
				else if (r == 9) entity = new DqmEntitySyado(world);
				else if (r == 10) entity = new DqmEntityMetoroghost(world);
				else if (r == 11) entity = new DqmEntityRaimusuraimu(world);

			/* 武器屋 */
			// 武器屋 Y=36～40 確定スポーン
			} else if (enemyGroup == LadRoomID.WEAPON_SHOP_CUSTOMER +2) {
				int r = rand.nextInt(6);

				if (r == 0) entity = new DqmEntityItamogu(world);
				else if (r == 1) entity = new DqmEntityOokiduti(world);
				else if (r == 2) entity = new DqmEntityHitokuisaberu(world);
				else if (r == 3) entity = new DqmEntitySirudokozou(world);
				else if (r == 4) entity = new DqmEntityBurauni(world);
				else if (r == 5) entity = new DqmEntityGaikotu(world);

			// 武器屋 Y=36～40 変動スポーン
			} else if (enemyGroup == LadRoomID.WEAPON_SHOP +2) {
				int r = rand.nextInt(12);

				if (r == 0) entity = new DqmEntityItamogu(world);
				else if (r == 1) entity = new DqmEntitySukippaa(world);
				else if (r == 2) entity = new DqmEntityTukaima(world);
				else if (r == 3) entity = new DqmEntityOokiduti(world);
				else if (r == 4) entity = new DqmEntityHitokuisaberu(world);
				else if (r == 5) entity = new DqmEntityPapetkozou(world);
				else if (r == 6) entity = new DqmEntityRiripat(world);
				else if (r == 7) entity = new DqmEntityDoruido(world);
				else if (r == 8) entity = new DqmEntitySirudokozou(world);
				else if (r == 9) entity = new DqmEntityBurauni(world);
				else if (r == 10) entity = new DqmEntityGaikotu(world);
				else if (r == 11) entity = new DqmEntityWaraibukuro(world);

			// 武器屋 Y=31～35 確定スポーン
			} else if (enemyGroup == LadRoomID.WEAPON_SHOP_CUSTOMER +3) {
				int r = rand.nextInt(6);

				if (r == 0) entity = new DqmEntityOokiduti(world);
				else if (r == 1) entity = new DqmEntityHitokuisaberu(world);
				else if (r == 2) entity = new DqmEntitySirudokozou(world);
				else if (r == 3) entity = new DqmEntityBurauni(world);
				else if (r == 4) entity = new DqmEntityGaikotu(world);
				else if (r == 5) entity = new DqmEntitySuraimunaito(world);

			// 武器屋 Y=31～35 変動スポーン
			} else if (enemyGroup == LadRoomID.WEAPON_SHOP +3) {
				int r = rand.nextInt(12);

				if (r == 0) entity = new DqmEntityTukaima(world);
				else if (r == 1) entity = new DqmEntityOokiduti(world);
				else if (r == 2) entity = new DqmEntityHitokuisaberu(world);
				else if (r == 3) entity = new DqmEntityPapetkozou(world);
				else if (r == 4) entity = new DqmEntityRiripat(world);
				else if (r == 5) entity = new DqmEntityDoruido(world);
				else if (r == 6) entity = new DqmEntitySirudokozou(world);
				else if (r == 7) entity = new DqmEntityBurauni(world);
				else if (r == 8) entity = new DqmEntityGaikotu(world);
				else if (r == 9) entity = new DqmEntityWaraibukuro(world);
				else if (r == 10) entity = new DqmEntitySuraimunaito(world);
				else if (r == 11) entity = new DqmEntityHitokuikibako(world);

			/* 特殊部屋1 */
			// 特殊部屋1 Y=41～45
			} else if (enemyGroup == LadRoomID.SPECIAL_01 +1) {
				switch (rand.nextInt(4)) {
				case 0:
					spawnEnemy(world, player, x, y, z, LadRoomID.FOREST + LadRoomID.getDifOfRoom());
					break;
				case 1:
					spawnEnemy(world, player, x, y, z, LadRoomID.VILLAGE_WELL + LadRoomID.getDifOfRoom());
					break;
				case 2:
					spawnEnemy(world, player, x, y, z, LadRoomID.BEACH + LadRoomID.getDifOfRoom());
					break;
				case 3:
					spawnEnemy(world, player, x, y, z, LadRoomID.DESERT_WELL + LadRoomID.getDifOfRoom());
					break;
				}
				return;

			// 特殊部屋1 Y=36～40
			} else if (enemyGroup == LadRoomID.SPECIAL_01 +2) {
				switch (rand.nextInt(6)) {
				case 0:
					spawnEnemy(world, player, x, y, z, LadRoomID.FOREST + LadRoomID.getDifOfRoom());
					break;
				case 1:
					spawnEnemy(world, player, x, y, z, LadRoomID.VILLAGE_WELL_HAS_CURSED + LadRoomID.getDifOfRoom());
					break;
				case 2:
					spawnEnemy(world, player, x, y, z, LadRoomID.VILLAGE_WELL_HAS_CURSED_ON_WATER + LadRoomID.getDifOfRoom());
					break;
				case 3:
					spawnEnemy(world, player, x, y, z, LadRoomID.BEACH + LadRoomID.getDifOfRoom());
					break;
				case 4:
					spawnEnemy(world, player, x, y, z, LadRoomID.ICE_PLAINS + LadRoomID.getDifOfRoom());
					break;
				case 5:
					spawnEnemy(world, player, x, y, z, LadRoomID.WEAPON_SHOP + LadRoomID.getDifOfRoom());
					break;
				}
				return;

			// 特殊部屋1 Y=31～35
			} else if (enemyGroup == LadRoomID.SPECIAL_01 +3) {
				switch (rand.nextInt(8)) {
				case 0:
					spawnEnemy(world, player, x, y, z, LadRoomID.FOREST + LadRoomID.getDifOfRoom());
					break;
				case 1:
					spawnEnemy(world, player, x, y, z, LadRoomID.VILLAGE_WELL + LadRoomID.getDifOfRoom());
					break;
				case 2:
					spawnEnemy(world, player, x, y, z, LadRoomID.VILLAGE_WELL_HAS_CURSED + LadRoomID.getDifOfRoom());
					break;
				case 3:
					spawnEnemy(world, player, x, y, z, LadRoomID.VILLAGE_WELL_HAS_CURSED_ON_WATER + LadRoomID.getDifOfRoom());
					break;
				case 4:
					spawnEnemy(world, player, x, y, z, LadRoomID.BEACH + LadRoomID.getDifOfRoom());
					break;
				case 5:
					spawnEnemy(world, player, x, y, z, LadRoomID.DESERT_WELL + LadRoomID.getDifOfRoom());
					break;
				case 6:
					spawnEnemy(world, player, x, y, z, LadRoomID.ICE_PLAINS + LadRoomID.getDifOfRoom());
					break;
				case 7:
					spawnEnemy(world, player, x, y, z, LadRoomID.WEAPON_SHOP + LadRoomID.getDifOfRoom());
					break;
				}
				return;
			}


		/*
		 * 中層 Y= 21～30
		 */
		} else if (LadRoomID.getDifOfRoom() <= 5) {
			/* 廃坑 */
			// 「廃坑」Y=26～30
			if (enemyGroup == LadRoomID.MINE_SHAFT +4) {
				int r = rand.nextInt(12);

				if (r == 0) entity = new DqmEntitySupini(world);
				else if (r == 1) entity = new DqmEntityOokutibasi(world);
				else if (r == 2) entity = new DqmEntityDorakima(world);
				else if (r == 3) entity = new DqmEntityDoronuba(world);
				else if (r == 4) entity = new DqmEntityHitokuiga(world);
				else if (r == 5) entity = new DqmEntityHitokuikibako(world);
				else if (r == 6) entity = new DqmEntityUmiusi(world);
				else if (r == 7) entity = new DqmEntitySibireageha(world);
				else if (r == 8) entity = new DqmEntityMatango(world);
				else if (r == 9) entity = new DqmEntityBuchunpa(world);
				else if (r == 10) entity = new DqmEntityDesufuratta(world);
				else if (r == 11) entity = new DqmEntityTonburero(world);

			// 「廃坑」Y=21～25
			} else if (enemyGroup == LadRoomID.MINE_SHAFT +5) {
				int r = rand.nextInt(12);

				if (r == 0) entity = new DqmEntityDoronuba(world);
				else if (r == 1) entity = new DqmEntityHitokuiga(world);
				else if (r == 2) entity = new DqmEntityHitokuikibako(world);
				else if (r == 3) entity = new DqmEntityUmiusi(world);
				else if (r == 4) entity = new DqmEntitySibireageha(world);
				else if (r == 5) entity = new DqmEntityMatango(world);
				else if (r == 6) entity = new DqmEntityBuchunpa(world);
				else if (r == 7) entity = new DqmEntityDesufuratta(world);
				else if (r == 8) entity = new DqmEntityTonburero(world);
				else if (r == 9) entity = new DqmEntityDokuyazukin(world);
				else if (r == 10) entity = new DqmEntityButtizukinya(world);
				else if (r == 11) entity = new DqmEntitySupekutetto(world);


			/* ピラミッド */
			// 「ピラミッド」Y=26～30
			} else if (enemyGroup == LadRoomID.PYRAMID +4) {
				int r = rand.nextInt(12);

				if (r == 0) entity = new DqmEntitySupini(world);
				else if (r == 1) entity = new DqmEntityKimera(world);
				else if (r == 2) entity = new DqmEntityMadohando(world);
				else if (r == 3) entity = new DqmEntitySabotenboru(world);
				else if (r == 4) entity = new DqmEntityWaraibukuro(world);
				else if (r == 5) entity = new DqmEntityTutiwarasi(world);
				else if (r == 6) entity = new DqmEntitySuraimunaito(world);
				else if (r == 7) entity = new DqmEntityHiitogizumo(world);
				else if (r == 8) entity = new DqmEntitySibiredanbira(world);
				else if (r == 9) entity = new DqmEntityBakudanbebi(world);
				else if (r == 10) entity = new DqmEntityBerobero(world);
				else if (r == 11) entity = new DqmEntityAkumanosyo(world);

			// 「ピラミッド」Y=21～25
			} else if (enemyGroup == LadRoomID.PYRAMID +5) {
				int r = rand.nextInt(12);

				if (r == 0) entity = new DqmEntityTutiwarasi(world);
				else if (r == 1) entity = new DqmEntitySuraimunaito(world);
				else if (r == 2) entity = new DqmEntityHiitogizumo(world);
				else if (r == 3) entity = new DqmEntitySibiredanbira(world);
				else if (r == 4) entity = new DqmEntityBakudanbebi(world);
				else if (r == 5) entity = new DqmEntityBerobero(world);
				else if (r == 6) entity = new DqmEntityAkumanosyo(world);
				else if (r == 7) entity = new DqmEntityBaburin(world);
				else if (r == 8) entity = new DqmEntityGaikotukensi(world);
				else if (r == 9) entity = new DqmEntityOdoruhouseki(world);
				else if (r == 10) entity = new DqmEntitySamayoutamasii(world);
				else if (r == 11) entity = new DqmEntitySumoruguru(world);

			/* 氷洞 */
			// 「氷洞」Y=26～30
			} else if (enemyGroup == LadRoomID.ICE_CAVE +4) {
				int r = rand.nextInt(12);

				if (r == 0) entity = new DqmEntityPurizunyan(world);
				else if (r == 1) entity = new DqmEntityDucksbill(world);
				else if (r == 2) entity = new DqmEntitySyado(world);
				else if (r == 3) entity = new DqmEntityMetoroghost(world);
				else if (r == 4) entity = new DqmEntityTahodoraki(world);
				else if (r == 5) entity = new DqmEntityHoroghost(world);
				else if (r == 6) entity = new DqmEntityFurosutogizumo(world);
				else if (r == 7) entity = new DqmEntityHerughost(world);
				else if (r == 8) entity = new DqmEntityHyouganmajin(world);
				else if (r == 9) entity = new DqmEntityTomosibikozou(world);
				else if (r == 10) entity = new DqmEntityArumiraji(world);
				else if (r == 11) entity = new DqmEntityJeriman(world);

			// 「氷洞」Y=21～25
			} else if (enemyGroup == LadRoomID.ICE_CAVE +5) {
				int r = rand.nextInt(12);

				if (r == 0) entity = new DqmEntityDucksbill(world);
				else if (r == 1) entity = new DqmEntitySyado(world);
				else if (r == 2) entity = new DqmEntityMetoroghost(world);
				else if (r == 3) entity = new DqmEntityTahodoraki(world);
				else if (r == 4) entity = new DqmEntityHoroghost(world);
				else if (r == 5) entity = new DqmEntityFurosutogizumo(world);
				else if (r == 6) entity = new DqmEntityHerughost(world);
				else if (r == 7) entity = new DqmEntityHyouganmajin(world);
				else if (r == 8) entity = new DqmEntityTomosibikozou(world);
				else if (r == 9) entity = new DqmEntityArumiraji(world);
				else if (r == 10) entity = new DqmEntityJeriman(world);
				else if (r == 11) entity = new DqmEntityMarinsuraimu(world);

			/* 要塞(Stronghold) */
			// 「要塞(Stronghold)」Y=26～30
			} else if (enemyGroup == LadRoomID.STRONGHOLD +4) {
				int r = rand.nextInt(12);

				if (r == 0) entity = new DqmEntityObakekyandoru(world);
				else if (r == 1) entity = new DqmEntityKimera(world);
				else if (r == 2) entity = new DqmEntityTutiwarasi(world);
				else if (r == 3) entity = new DqmEntitySuraimunaito(world);
				else if (r == 4) entity = new DqmEntityMetoroghost(world);
				else if (r == 5) entity = new DqmEntityKirapan(world);
				else if (r == 6) entity = new DqmEntitySuraimubogu(world);
				else if (r == 7) entity = new DqmEntityHoroghost(world);
				else if (r == 8) entity = new DqmEntityAnimaruzonbi(world);
				else if (r == 9) entity = new DqmEntityHerughost(world);
				else if (r == 10) entity = new DqmEntityTomosibikozou(world);
				else if (r == 11) entity = new DqmEntityBerobero(world);

			// 「要塞(Stronghold)」Y=21～25
			} else if (enemyGroup == LadRoomID.STRONGHOLD +5) {
				int r = rand.nextInt(12);

				if (r == 0) entity = new DqmEntityTutiwarasi(world);
				else if (r == 1) entity = new DqmEntitySuraimunaito(world);
				else if (r == 2) entity = new DqmEntityMetoroghost(world);
				else if (r == 3) entity = new DqmEntityKirapan(world);
				else if (r == 4) entity = new DqmEntitySuraimubogu(world);
				else if (r == 5) entity = new DqmEntityHoroghost(world);
				else if (r == 6) entity = new DqmEntityAnimaruzonbi(world);
				else if (r == 7) entity = new DqmEntityHerughost(world);
				else if (r == 8) entity = new DqmEntityTomosibikozou(world);
				else if (r == 9) entity = new DqmEntityBerobero(world);
				else if (r == 10) entity = new DqmEntitySamayoutamasii(world);
				else if (r == 11) entity = new DqmEntitySumoruguru(world);

			/* メダル王の部屋 */
			// 「メダル王の部屋」Y=26～30
			} else if (enemyGroup == LadRoomID.MEDAL_KING +4) {
				int r = rand.nextInt(12);

				if (r == 0) entity = new DqmEntityDorakima(world);
				else if (r == 1) entity = new DqmEntityBurauni(world);
				else if (r == 2) entity = new DqmEntityWaraibukuro(world);
				else if (r == 3) entity = new DqmEntityPurizunyan(world);
				else if (r == 4) entity = new DqmEntityDucksbill(world);
				else if (r == 5) entity = new DqmEntityRaimusuraimu(world);
				else if (r == 6) entity = new DqmEntityTahodoraki(world);
				else if (r == 7) entity = new DqmEntityKirasuko(world);
				else if (r == 8) entity = new DqmEntityPinkmomon(world);
				else if (r == 9) entity = new DqmEntityArumiraji(world);
				else if (r == 10) entity = new DqmEntityTonburero(world);
				else if (r == 11) entity = new DqmEntitySura2(world);

			// 「メダル王の部屋」Y=21～25
			} else if (enemyGroup == LadRoomID.MEDAL_KING +5) {
				int r = rand.nextInt(12);

				if (r == 0) entity = new DqmEntityPurizunyan(world);
				else if (r == 1) entity = new DqmEntityDucksbill(world);
				else if (r == 2) entity = new DqmEntityRaimusuraimu(world);
				else if (r == 3) entity = new DqmEntityTahodoraki(world);
				else if (r == 4) entity = new DqmEntityKirasuko(world);
				else if (r == 5) entity = new DqmEntityPinkmomon(world);
				else if (r == 6) entity = new DqmEntityArumiraji(world);
				else if (r == 7) entity = new DqmEntityTonburero(world);
				else if (r == 8) entity = new DqmEntitySura2(world);
				else if (r == 9) entity = new DqmEntityBehoimisuraimu(world);
				else if (r == 10) entity = new DqmEntityOdoruhouseki(world);
				else if (r == 11) entity = new DqmEntitySuraimuburesu(world);

			/* ダーマ神殿 */
			// 「ダーマ神殿」Y=26～30
			} else if (enemyGroup == LadRoomID.DAMA +4) {
				int r = rand.nextInt(12);

				if (r == 0) entity = new DqmEntityRiripat(world);
				else if (r == 1) entity = new DqmEntityDoruido(world);
				else if (r == 2) entity = new DqmEntitySirudokozou(world);
				else if (r == 3) entity = new DqmEntityBurauni(world);
				else if (r == 4) entity = new DqmEntityGaikotu(world);
				else if (r == 5) entity = new DqmEntitySuraimunaito(world);
				else if (r == 6) entity = new DqmEntityKirapan(world);
				else if (r == 7) entity = new DqmEntitySuraimubogu(world);
				else if (r == 8) entity = new DqmEntityKirasuko(world);
				else if (r == 9) entity = new DqmEntitySibiredanbira(world);
				else if (r == 10) entity = new DqmEntityAkumanosyo(world);
				else if (r == 11) entity = new DqmEntityBebisatan(world);

			// 「ダーマ神殿」Y=21～25
			} else if (enemyGroup == LadRoomID.DAMA +5) {
				int r = rand.nextInt(12);

				if (r == 0) entity = new DqmEntitySirudokozou(world);
				else if (r == 1) entity = new DqmEntityBurauni(world);
				else if (r == 2) entity = new DqmEntitySuraimunaito(world);
				else if (r == 3) entity = new DqmEntityKirapan(world);
				else if (r == 4) entity = new DqmEntitySuraimubogu(world);
				else if (r == 5) entity = new DqmEntityKirasuko(world);
				else if (r == 6) entity = new DqmEntitySibiredanbira(world);
				else if (r == 7) entity = new DqmEntityAkumanosyo(world);
				else if (r == 8) entity = new DqmEntityBebisatan(world);
				else if (r == 9) entity = new DqmEntityDokuyazukin(world);
				else if (r == 10) entity = new DqmEntityGaikotukensi(world);
				else if (r == 11) entity = new DqmEntityButtizukinya(world);


			// 特殊部屋2 Y=21～30
			} else if ((enemyGroup == LadRoomID.SPECIAL_01 +4) || (enemyGroup == LadRoomID.SPECIAL_01 +5)) {
				switch (rand.nextInt(8)) {
				case 0:
					spawnEnemy(world, player, x, y, z, LadRoomID.DAMA + LadRoomID.getDifOfRoom());
					break;
				case 1:
					spawnEnemy(world, player, x, y, z, LadRoomID.ICE_CAVE + LadRoomID.getDifOfRoom());
					break;
				case 2:
					spawnEnemy(world, player, x, y, z, LadRoomID.MEDAL_KING + LadRoomID.getDifOfRoom());
					break;
				case 3:
					spawnEnemy(world, player, x, y, z, LadRoomID.MINE_SHAFT + LadRoomID.getDifOfRoom());
					break;
				case 4:
					spawnEnemy(world, player, x, y, z, LadRoomID.PYRAMID + LadRoomID.getDifOfRoom());
					break;
				case 5:
					spawnEnemy(world, player, x, y, z, LadRoomID.STRONGHOLD + LadRoomID.getDifOfRoom());
					break;
				}
				return;

			}
		}


		/*
		 * 以下、決めた敵をスポーンさせる処理
		 */

		// [Debug]戦闘部屋を固定している時、敵のスポーン位置の下にダイヤモンドブロックを敷く（デバッグ用）
		if (LadDebug.getDebugRoom() >= 0) {
			world.setBlock(x, y-1, z, Blocks.diamond_block);
		}

		if (entity == null) return;
		entity.setLocationAndAngles(x +0.5D, y, z +0.5D, MathHelper.wrapAngleTo180_float(world.rand.nextFloat() * 360.0F), 0.0F);
		entity.rotationYawHead = entity.rotationYaw;
		entity.renderYawOffset = entity.rotationYaw;
		entity.onSpawnWithEgg((IEntityLivingData)null);

		// [ForgeEvent] 敵スポーン直前に 介入用のイベント
		LadEnemySpawnEvent event = new LadEnemySpawnEvent(world, player, entity, enemyGroup);
		MinecraftForge.EVENT_BUS.post(event);

		world.spawnEntityInWorld(entity);
		entity.playLivingSound();

		if (encounterLog) {
			player.addChatMessage(new ChatComponentTranslation(entity.getCommandSenderName() + "が あらわれた！"));
		}
	}
}