package lawisAddonDqr1.item;

import cpw.mods.fml.common.registry.GameRegistry;
import lawisAddonDqr1.api.items.LadItems;
import lawisAddonDqr1.item.items.PickaxeOrnamentM;
import lawisAddonDqr1.item.items.PickaxeOrnamentU;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.util.EnumHelper;

/*
 * アイテムを追加するクラス。
 */
public class LadInitItems {
	/*
	 * アイテムを追加・登録するメソッド。
	 */
	public static void initItems() {
		// ツールマテリアル (耐久が石、他の性能が鉄)
		LadItems.ORNAMENT = EnumHelper.addToolMaterial("ORNAMENT", 2, 131, 6.0F, 2.0F, 14);

		// 装飾石のツルハシ(上層)
		LadItems.pickaxeOrnamentU = new PickaxeOrnamentU(LadItems.ORNAMENT)
				.setCreativeTab(CreativeTabs.tabTools)
				.setUnlocalizedName("pickaxeOrnamentU")
				.setTextureName("lawisadoondqr01:pickaxe_ornament_u");
		GameRegistry.registerItem(LadItems.pickaxeOrnamentU, "pickaxeOrnamentU");

		// 装飾石のツルハシ(中層)
		LadItems.pickaxeOrnamentM = new PickaxeOrnamentM(LadItems.ORNAMENT)
				.setCreativeTab(CreativeTabs.tabTools)
				.setUnlocalizedName("pickaxeOrnamentM")
				.setTextureName("lawisadoondqr01:pickaxe_ornament_m");
		GameRegistry.registerItem(LadItems.pickaxeOrnamentM, "pickaxeOrnamentM");
	}
}
