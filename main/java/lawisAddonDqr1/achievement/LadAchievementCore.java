package lawisAddonDqr1.achievement;

import dqr.api.Blocks.DQBlocks;
import dqr.api.Blocks.DQDecorates;
import dqr.api.Items.DQBuilders;
import dqr.api.Items.DQEmblems;
import dqr.api.Items.DQMiscs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

/*
 * 実績の追加を行うクラス
 */
public class LadAchievementCore {
	// 上層
    public static Achievement roomBeach;
    public static Achievement roomDesertWell;
    public static Achievement roomForest;
    public static Achievement roomIcePlains;
    public static Achievement roomVillageWell;
    public static Achievement roomWeaponShop;
    public static Achievement roomSpecial01;

    // 上層イベント
    	// 未実装：砂浜
		// 未実装：砂漠の井戸
    public static Achievement eventAnimal;
		// 未実装：氷原
    public static Achievement eventShop;
    public static Achievement eventZombie;
    	// 未実装：上層特殊部屋

	// 中層
    public static Achievement roomDama;
    public static Achievement roomIceCave;
    public static Achievement roomMedalKing;
    public static Achievement roomMineShaft;
    public static Achievement roomPyramid;
    public static Achievement roomStronghold;
    public static Achievement roomSpecial02;

    // 中層イベント
    public static Achievement eventPriest;
    	// 未実装：氷洞
    public static Achievement eventMedalKing;
    public static Achievement eventSpider;
    	// 未実装：ピラミッド
    	// 未実装：要塞
    	// 未実装：中層特殊部屋

    /*
     * 実装関連の追加
     *
     * 戦闘部屋を引き当てた時に実装を獲得
     */
	public static void initLadAchievements() {
		/* 実績の追加 */
		// 上層
		roomSpecial01 = new Achievement("roomSpecial01", "roomSpecial01", 0, 0, DQBlocks.DqmBlockKowareru8, null).registerStat();
		roomBeach = new Achievement("roomBeach", "roomBeach", 1, 0, Items.water_bucket, null).registerStat();
		roomDesertWell = new Achievement("roomDesertWell", "roomDesertWell", 2, 0, Blocks.cactus, null).registerStat();
		roomForest = new Achievement("roomForest", "roomForest", 3, 0, Blocks.leaves, null).registerStat();
		roomIcePlains = new Achievement("roomIcePlains", "roomIcePlains", 4, 0, Blocks.snow_layer, null).registerStat();
		roomVillageWell = new Achievement("roomVillageWell", "roomVillageWell", 5, 0, Blocks.cobblestone, null).registerStat();
		roomWeaponShop = new Achievement("roomWeaponShop", "roomWeaponShop", 6, 0, DQDecorates.DqmBlockBukiya, null).registerStat();
		// 上層イベント
		eventAnimal = new Achievement("eventAnimal", "eventAnimal", 3, 1, Items.egg, null).registerStat();
		eventZombie = new Achievement("eventZombie", "eventZombie", 5, 1, Items.rotten_flesh, null).registerStat();
		eventShop = new Achievement("eventShop", "eventShop", 6, 1, DQMiscs.itemOkane, null).registerStat();
		// 中層
		roomSpecial02 = new Achievement("roomSpecial02", "roomSpecial02", 0, 2, DQBlocks.DqmBlockKowareru9, null).registerStat();
		roomMedalKing = new Achievement("roomMedalKing", "roomMedalKing", 1, 2, DQDecorates.DqmBlockIdo, null).registerStat();
		roomPyramid = new Achievement("roomPyramid", "roomPyramid", 2, 2, Blocks.tnt, null).registerStat();
		roomMineShaft = new Achievement("roomMineShaft", "roomMineShaft", 3, 2, Blocks.rail, null).registerStat();
		roomIceCave = new Achievement("roomIceCave", "roomIceCave", 4, 2, Blocks.packed_ice, null).registerStat();
		roomStronghold = new Achievement("roomStronghold", "roomStronghold", 5, 2, Blocks.stonebrick, null).registerStat();
		roomDama = new Achievement("roomDama", "roomDama", 6, 2, DQBuilders.itemBuilderDama, null).registerStat();
		// 中層イベント
		eventMedalKing = new Achievement("eventMedalKing", "eventMedalKing", 1, 3, DQMiscs.itemLittlemedal, null).registerStat();
		eventSpider = new Achievement("eventSpider", "eventSpider", 3, 3, Blocks.web, null).registerStat();
		eventPriest = new Achievement("eventPriest", "eventPriest", 6, 3, DQEmblems.itemEmbCivilian, null).registerStat();

		/* 実績ページの追加 */
		AchievementPage.registerAchievementPage(new AchievementPage("MR-Encounter for DQRmod", new Achievement[]{
				roomBeach, roomDesertWell, roomForest, roomIcePlains, roomVillageWell, roomWeaponShop,
				eventAnimal, eventZombie, eventShop,
				roomDama, roomIceCave, roomMedalKing, roomMineShaft, roomPyramid, roomStronghold,
				eventMedalKing, eventSpider, eventPriest,
				roomSpecial01, roomSpecial02
				}));
	}
}
