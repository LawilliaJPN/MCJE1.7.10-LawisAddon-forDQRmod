package lawisAddonDqr1.config;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import lawisAddonDqr1.LawisAddonDQR01;
import lawisAddonDqr1.event.LadEventHundler;

/*
 * コンフィグ変更を反映させるイベント。
 *
 * 「TNT MODDERS」様の「MOD製作チュートリアル」を参考にさせていただきました。
 * https://www63.atwiki.jp/akasatanahama/pages/131.html
 */
public class LadConfigEventHundler {
	/*
	 * コンフィグが変更されたときに呼び出されるメソッド
	 */
	@SubscribeEvent
	public void onConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
		// 変更されたコンフィグがこのmodのものであるとき
		if (event.modID.equals(LawisAddonDQR01.MOD_ID)) {
			syncConfigAndResetCount();
		}
	}

	/*
	 * 上記メソッドやlawisAddonDqr1.addon.LadAddons.javaで呼び出されるメソッド。
	 * コンフィグ同期と関連変数のリセット。
	 */
	public static void syncConfigAndResetCount() {
		// コンフィグの同期を行う
		LadConfigCore.syncConfig();

		// ランダムエンカウントのためのカウントのリセットを行う
		LadEventHundler.updateCountRandomEncounter();
	}
}