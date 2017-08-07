package lawisAddonDqr1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import lawisAddonDqr1.addons.Addons;

@Mod(modid = LawisAddonDQR01.MODID, name = LawisAddonDQR01.MODNAME, version = LawisAddonDQR01.VERSION)

public class LawisAddonDQR01 {
	public static final String MODID = "lawisadoondqr01";
	public static final String MODNAME = "Lawi's Addon for DQR 01";
	public static final String VERSION = "1.0";
	public static Logger logger = LogManager.getLogger("lawisadoondqr01");

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		// DQRmodへのアドオン
		Addons.loadDQR();
	}
}


/* 未使用
	@SidedProxy(
			clientSide = "lawisAddonDqr1.proxy.ClientProxy",
			serverSide = "lawisAddonDqr1.proxy.ServerProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event){

	}

	@EventHandler
	public void init(FMLInitializationEvent event){

	}
*/