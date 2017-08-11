package lawisAddonDqr1.config;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import lawisAddonDqr1.LawisAddonDQR01;

/*
 * コンフィグ変更を反映させるイベント。
 * 「TNT MODDERS」様の「MOD製作チュートリアル」を参考にさせていただきました。
 * https://www63.atwiki.jp/akasatanahama/pages/131.html
 */
public class LadConfigEventHundler {
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.modID.equals(LawisAddonDQR01.MOD_ID)) {
			LadConfigCore.syncConfig();
		}
	}
}
