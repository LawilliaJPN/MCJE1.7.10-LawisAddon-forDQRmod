package lawisAddonDqr1.addons;

import cpw.mods.fml.common.Loader;
import lawisAddonDqr1.LawisAddonDQR01;
import lawisAddonDqr1.event.BreakEventHundler;
import net.minecraftforge.common.MinecraftForge;

public class Addons {
	private static boolean DqrLoaded = false;

	/*
	 *  連携先のDQRmodと併用されているかを確認するメソッド
	 */
	public static void loadDQR(){
		if (Loader.isModLoaded("DQMIIINext")) {
			try{
				// System.out.println("DQRAddon OK");
				DqrLoaded = true;
				MinecraftForge.EVENT_BUS.register(new BreakEventHundler());
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

