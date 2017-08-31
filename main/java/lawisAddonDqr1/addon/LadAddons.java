package lawisAddonDqr1.addon;

import cpw.mods.fml.common.Loader;
import lawisAddonDqr1.LawisAddonDQR01;
import lawisAddonDqr1.achievement.LadAchievementCore;
import lawisAddonDqr1.block.LadInitBlocks;
import lawisAddonDqr1.block.LadRecipes;
import lawisAddonDqr1.config.LadConfigEventHundler;
import lawisAddonDqr1.event.LadEventHundler;
import net.minecraftforge.common.MinecraftForge;

/*
 * メインからPostInitで呼び出されるクラス。
 * DQRmodと併用されている時のみ有効な処理の呼び出し。
 */
public class LadAddons {
	private static boolean DqrLoaded = false;
	private static boolean Amt2Loaded = false;

	/*
	 *  連携先のDQRmodが併用されているかを確認するメソッド
	 */
	public static void loadDQR() {
		if (Loader.isModLoaded("DQMIIINext")) {
			try {
				// 他のmodの確認
				loadAMT2();

				// System.out.println("DQRAddon OK");
				DqrLoaded = true;

				// コンフィグの反映
				LadConfigEventHundler.syncConfigAndResetCount();

				// 仕様変更したブロックの追加
				LadInitBlocks.initBlocks();

				// 追加したブロックに関連するレシピの追加
				LadRecipes.initLadRecipes();

				// 実績の追加
				LadAchievementCore.initLadAchievements();

				// ブロックを破壊した時のイベント処理
				MinecraftForge.EVENT_BUS.register(new LadEventHundler());

			} catch (Throwable t) {
				LawisAddonDQR01.logger.warn("Failed to load DQRmod");
			}
		}
	}

	/*
	 *  連携先のAMT2が併用されているかを確認するメソッド
	 */
	public static void loadAMT2() {
		if (Loader.isModLoaded("DCsAppleMilk")) {
			Amt2Loaded = true;
		}
	}

	/*
	 *  getter
	 */
	public static boolean isDqrLoaded(){
		return DqrLoaded;
	}

	public static boolean isAmt2Loaded(){
		return Amt2Loaded;
	}
}

