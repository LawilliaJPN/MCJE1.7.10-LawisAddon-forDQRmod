package lawisAddonDqr1.config;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import lawisAddonDqr1.LawisAddonDQR01;
import net.minecraftforge.common.config.Configuration;

/*
 * コンフィグ設定の中心となるクラス。
 * 「TNT MODDERS」様の「MOD製作チュートリアル」を参考にさせていただきました。
 * https://www63.atwiki.jp/akasatanahama/pages/131.html
 */
public class LadConfigCore {
	public static Configuration cfg;

	/* GENERAL Mod全般の設定 */
	public static final String GENERAL = "General";
	// ENCOUNTER 戦闘の頻度
	private static final String ENCOUNTER = GENERAL + ".Encounter";
	public static int frequencyOfBattle = 10;
	// REDUCTION 負荷軽減
	private static final String REDUCTION = GENERAL + ".Reduction";
	public static boolean isRoomReduction = false;
	// BED_PENALTY ベッドペナルティ
	private static final String BED_PENALTY = GENERAL + ".BedPenalty";
	public static boolean isBedPenalty = false;


	/*
	 * preInit コンフィグの読み込み
	 */
	public static void loadConfig(FMLPreInitializationEvent event) {
		// Configurationのインスタンス化
		cfg = new Configuration(event.getSuggestedConfigurationFile(), LawisAddonDQR01.MOD_VERSION, true);

		// コンフィグの初期化・同期
		initConfig();
		syncConfig();
	}

	/*
	 * コンフィグの初期化
	 */
	private static void initConfig() {
		/* GENERAL Mod全般の設定 */
		cfg.addCustomCategoryComment(GENERAL, "The general settings of  " + LawisAddonDQR01.MOD_NAME + ".");
		cfg.setCategoryLanguageKey(GENERAL, "config.lad.category.general");

		// ENCOUNTER 戦闘の頻度
		cfg.addCustomCategoryComment(ENCOUNTER, "The setting of frequency of battle when you destroy stone-block.");
		cfg.setCategoryLanguageKey(ENCOUNTER, "config.lad.category.encounter");

		// REDUCTION 負荷軽減
		cfg.addCustomCategoryComment(REDUCTION, "The setting of size of the rooms, for workload reduction.");
		cfg.setCategoryLanguageKey(REDUCTION, "config.lad.category.reduction");

		// BED_PENALTY ベッドペナルティ
		cfg.addCustomCategoryComment(BED_PENALTY, "The setting of Bed-Penalty ON/OFF.");
		cfg.setCategoryLanguageKey(BED_PENALTY, "config.lad.category.bedpenalty");
	}

	/*
	 * コンフィグの同期
	 */
	public static void syncConfig() {
		/* 変数への反映 */
		// ENCOUNTER 戦闘の頻度
		frequencyOfBattle = cfg.getInt("Frequency of Battle", ENCOUNTER, frequencyOfBattle, 0, 50, "The lower you set this number, the more you encounter enemies.", "config.lad.category.encounter");
		// REDUCTION 負荷軽減
		isRoomReduction = cfg.getBoolean("Workload Reduction", REDUCTION, isRoomReduction, "When this setting is true, large rooms become smaller for workload reduction.", "config.lad.category.reduction");
		// BED_PENALTY ベッドペナルティ
		isBedPenalty = cfg.getBoolean("Bed Penalty", BED_PENALTY, isBedPenalty, "Even when you wake up, you encounter enemies if this setting true.", "config.lad.category.bedpenalty");

		/* コンフィグファイルの保存 */
		cfg.save();
	}
}
