package lawisAddonDqr1.addons;

import cpw.mods.fml.common.Loader;
import lawisAddonDqr1.LawisAddonDQR01;
import lawisAddonDqr1.event.BreakEventHundler;
import net.minecraftforge.common.MinecraftForge;

public class Addons {
	public static boolean DQRAddon = false;

	public Addons(){
		//DQRアドオン
		if (Loader.isModLoaded("DQMIIINext")) {
			try{
				// System.out.println("DQRAddon OK");
				DQRAddon = true;
				MinecraftForge.EVENT_BUS.register(new BreakEventHundler());
			} catch (Throwable t) {
				LawisAddonDQR01.logger.warn("Failed to get DQR mod");
			}
		}
	}
}

