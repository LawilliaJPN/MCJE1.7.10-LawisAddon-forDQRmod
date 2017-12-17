package lawisAddonDqr1.block;

import cpw.mods.fml.common.registry.GameRegistry;
import dqr.api.Blocks.DQBlocks;
import dqr.api.Items.DQIngots;
import lawisAddonDqr1.api.blocks.LadBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

/*
 * レシピを追加するクラス。
 */
public class LadRecipesBlocks {
	/*
	 * このmodで追加したブロックに関連するレシピを追加するメソッド。
	 */
	public static void initLadBlockRecipes() {
		// ジャンプブロック
		GameRegistry.addShapelessRecipe(new ItemStack(LadBlocks.ladJumpBlock2), DQBlocks.DqmBlockJampBlock2, DQIngots.itemHikarinoisi);
		GameRegistry.addShapelessRecipe(new ItemStack(DQBlocks.DqmBlockJampBlock2), LadBlocks.ladJumpBlock2);

		// 氷塊
		GameRegistry.addShapelessRecipe(new ItemStack(LadBlocks.ladPackedIce), Blocks.packed_ice, DQIngots.itemKoorinokessyou);
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.packed_ice), LadBlocks.ladPackedIce);
	}
}