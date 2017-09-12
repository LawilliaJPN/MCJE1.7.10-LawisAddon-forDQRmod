package lawisAddonDqr1.item;

import cpw.mods.fml.common.registry.GameRegistry;
import dqr.api.Blocks.DQBlocks;
import lawisAddonDqr1.api.items.LadItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

/*
 * レシピを追加するクラス。
 */
public class LadRecipesItems {
	/*
	 * このmodで追加したアイテムに関連するレシピを追加するメソッド。
	 */
	public static void initLadItemRecipes() {
		/* 鉱石辞書への登録 */
		// 上層の装飾石（ラピス、レッドストーン）
		OreDictionary.registerOre("OrnamentU", DQBlocks.DqmBlockKowareru8);
		OreDictionary.registerOre("OrnamentU", DQBlocks.DqmBlockKowareru5);
		// 中層の装飾石（金、エメラルド）
		OreDictionary.registerOre("OrnamentM", DQBlocks.DqmBlockKowareru6);
		OreDictionary.registerOre("OrnamentM", DQBlocks.DqmBlockKowareru9);
		// 下層の装飾石（ダイヤモンド、ネザー水晶）
		OreDictionary.registerOre("OrnamentL", DQBlocks.DqmBlockKowareru7);
		OreDictionary.registerOre("OrnamentL", DQBlocks.DqmQuartzBlock);

		/* 装飾石のツルハシ(上層) */
		// 製作レシピ
		GameRegistry.addRecipe(new ShapedOreRecipe(
				new ItemStack(LadItems.pickaxeOrnamentU, 1, 0),
				"DDD",
				" S ",
				" S ",
				'S', Items.stick, 'D', "OrnamentU" )
		);

		// 修理レシピ
		GameRegistry.addRecipe(new ShapelessOreRecipe(
				new ItemStack(LadItems.pickaxeOrnamentU, 1, 0),
				new ItemStack(LadItems.pickaxeOrnamentU, 1, OreDictionary.WILDCARD_VALUE),
				"OrnamentU" )
		);

		/* 装飾石のツルハシ(中層) */
		// 製作レシピ
		GameRegistry.addRecipe(new ShapedOreRecipe(
				new ItemStack(LadItems.pickaxeOrnamentM, 1, 0),
				"DDD",
				" S ",
				" S ",
				'S', Items.stick, 'D', "OrnamentM" )
		);

		// 修理レシピ
		GameRegistry.addRecipe(new ShapelessOreRecipe(
				new ItemStack(LadItems.pickaxeOrnamentM, 1, 0),
				new ItemStack(LadItems.pickaxeOrnamentM, 1, OreDictionary.WILDCARD_VALUE),
				"OrnamentM" )
		);

		/* 装飾石のツルハシ(下層) */
		// 製作レシピ
		GameRegistry.addRecipe(new ShapedOreRecipe(
				new ItemStack(LadItems.pickaxeOrnamentL, 1, 0),
				"DDD",
				" S ",
				" S ",
				'S', Items.stick, 'D', "OrnamentL" )
		);

		// 修理レシピ
		GameRegistry.addRecipe(new ShapelessOreRecipe(
				new ItemStack(LadItems.pickaxeOrnamentL, 1, 0),
				new ItemStack(LadItems.pickaxeOrnamentL, 1, OreDictionary.WILDCARD_VALUE),
				"OrnamentL" )
		);
	}
}
