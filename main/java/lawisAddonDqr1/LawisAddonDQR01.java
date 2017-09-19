package lawisAddonDqr1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Metadata;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import lawisAddonDqr1.addon.LadAddons;
import lawisAddonDqr1.config.LadConfigCore;
import lawisAddonDqr1.config.LadConfigEventHandler;
import lawisAddonDqr1.config.LadInfoCore;

@Mod(modid = LawisAddonDQR01.MOD_ID, name = LawisAddonDQR01.MOD_NAME, version = LawisAddonDQR01.MOD_VERSION, guiFactory = "lawisAddonDqr1.config.LadGuiFactory")

public class LawisAddonDQR01 {
	public static final String MOD_ID = "MREncounterForDQR";
	public static final String MOD_NAME = "Mining Random Encounter for DQRmod";
	public static final String MOD_VERSION = "0.2.2";
	public static Logger logger = LogManager.getLogger("MREncounterForDQR");

	@Metadata(MOD_ID)
	private static ModMetadata meta;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		// MODの情報の登録
		LadInfoCore.registerInfo(meta);
		// コンフィグの読み込み
		LadConfigCore.loadConfig(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event){
		// ゲーム内コンフィグ変更の反映
		FMLCommonHandler.instance().bus().register(new LadConfigEventHandler());
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		// DQRmodへのアドオン
		LadAddons.loadDQR();
	}
}