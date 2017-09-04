package lawisAddonDqr1.item;

import cpw.mods.fml.common.registry.GameRegistry;
import dqr.api.Blocks.DQBlocks;
import lawisAddonDqr1.api.items.LadItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

/*
 * レシピを追加するクラス。
 */
public class LadRecipesItems {
	/*
	 * このmodで追加したアイテムに関連するレシピを追加するメソッド。
	 */
	public static void initLadItemRecipes() {
		/* 装飾石のツルハシ(上層) */
		// 製作レシピ
		GameRegistry.addRecipe(new ItemStack(LadItems.pickaxeOrnamentU, 1, 0),
				"DDD",
				" S ",
				" S ",
				'S', Items.stick,
				'D', DQBlocks.DqmBlockKowareru8
		);

		GameRegistry.addRecipe(new ItemStack(LadItems.pickaxeOrnamentU, 1, 0),
				"DDD",
				" S ",
				" S ",
				'S', Items.stick,
				'D', DQBlocks.DqmBlockKowareru5
		);

		// 修理レシピ
		GameRegistry.addShapelessRecipe(new ItemStack(LadItems.pickaxeOrnamentU, 1, 0),
				new ItemStack(LadItems.pickaxeOrnamentU, 1, OreDictionary.WILDCARD_VALUE),
				DQBlocks.DqmBlockKowareru8
		);

		GameRegistry.addShapelessRecipe(new ItemStack(LadItems.pickaxeOrnamentU, 1, 0),
				new ItemStack(LadItems.pickaxeOrnamentU, 1, OreDictionary.WILDCARD_VALUE),
				DQBlocks.DqmBlockKowareru5
		);

		/* 装飾石のツルハシ(中層) */
		// 製作レシピ
		GameRegistry.addRecipe(new ItemStack(LadItems.pickaxeOrnamentM, 1, 0),
				"DDD",
				" S ",
				" S ",
				'S', Items.stick,
				'D', DQBlocks.DqmBlockKowareru6
		);

		GameRegistry.addRecipe(new ItemStack(LadItems.pickaxeOrnamentM, 1, 0),
				"DDD",
				" S ",
				" S ",
				'S', Items.stick,
				'D', DQBlocks.DqmBlockKowareru9
		);

		// 修理レシピ
		GameRegistry.addShapelessRecipe(new ItemStack(LadItems.pickaxeOrnamentM, 1, 0),
				new ItemStack(LadItems.pickaxeOrnamentM, 1, OreDictionary.WILDCARD_VALUE),
				DQBlocks.DqmBlockKowareru6
		);

		GameRegistry.addShapelessRecipe(new ItemStack(LadItems.pickaxeOrnamentM, 1, 0),
				new ItemStack(LadItems.pickaxeOrnamentM, 1, OreDictionary.WILDCARD_VALUE),
				DQBlocks.DqmBlockKowareru9
		);
	}
}
