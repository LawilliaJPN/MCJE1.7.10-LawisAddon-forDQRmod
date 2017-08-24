package lawisAddonDqr1.addon;

import cpw.mods.fml.common.Loader;
import lawisAddonDqr1.LawisAddonDQR01;
import lawisAddonDqr1.achievement.LadAchievementCore;
import lawisAddonDqr1.block.LadInitBlocks;
import lawisAddonDqr1.block.LadRecipes;
import lawisAddonDqr1.config.LadConfigCore;
import lawisAddonDqr1.event.LadEventHundler;
import net.minecraftforge.common.MinecraftForge;

public class LadAddons {
	private static boolean DqrLoaded = false;

	/*
	 *  連携先のDQRmodと併用されているかを確認するメソッド
	 */
	public static void loadDQR() {
		if (Loader.isModLoaded("DQMIIINext")) {
			try {
				// System.out.println("DQRAddon OK");
				DqrLoaded = true;

				// コンフィグの反映
				LadConfigCore.syncConfig();
				LadEventHundler.updateCountRandomEncounter();

				// 仕様変更したブロックの追加
				LadInitBlocks.initBlocks();

				// 追加したブロックに関連するレシピの追加
				LadRecipes.initLadRecipes();

				// 実績の追加
				LadAchievementCore.initLadAchievements();

				// ブロックを破壊した時のイベント処理
				MinecraftForge.EVENT_BUS.register(new LadEventHundler());

			} catch (Throwable t) {
				LawisAddonDQR01.logger.warn("Failed to load DQR mod");
			}
		}
	}

	/*
	 *  変数 DqrLoaded の getter
	 */
	public static boolean isDqrLoaded(){
		return DqrLoaded;
	}
}

