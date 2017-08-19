package lawisAddonDqr1.achievement;

import dqr.api.Blocks.DQDecorates;
import lawisAddonDqr1.LawisAddonDQR01;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class LadAchievementCore {
	// 上層
    public static Achievement roomBeach;
    public static Achievement roomDesertWell;
    public static Achievement roomForest;
    public static Achievement roomIcePlains;
    public static Achievement roomVillageWell;
    public static Achievement roomWeaponShop;

    /*
     * 実装関連の追加
     *
     * 戦闘部屋を引き当てた時に実装を獲得
     */
	public static void initLadAchievements() {
		/* 実績の追加 */
		// 上層
		roomBeach = new Achievement("roomBeach", "roomBeach", 1, 0, Items.water_bucket, null).registerStat();
		roomDesertWell = new Achievement("roomDesertWell", "roomDesertWell", 2, 0, Blocks.cactus, null).registerStat();
		roomForest = new Achievement("roomForest", "roomForest", 3, 0, Blocks.leaves, null).registerStat();
		roomIcePlains = new Achievement("roomIcePlains", "roomIcePlains", 4, 0, Blocks.snow_layer, null).registerStat();
		roomVillageWell = new Achievement("roomVillageWell", "roomVillageWell", 5, 0, Items.emerald, null).registerStat();
		roomWeaponShop = new Achievement("roomWeaponShop", "roomWeaponShop", 6, 0, DQDecorates.DqmBlockBukiya, null).registerStat();

		/* 実績ページの追加 */
		AchievementPage.registerAchievementPage(new AchievementPage(LawisAddonDQR01.MOD_NAME, new Achievement[]{roomBeach, roomDesertWell, roomForest, roomIcePlains, roomVillageWell, roomWeaponShop}));
	}
}
