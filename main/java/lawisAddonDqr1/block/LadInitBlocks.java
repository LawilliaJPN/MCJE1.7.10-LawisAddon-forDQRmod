package lawisAddonDqr1.block;

import cpw.mods.fml.common.registry.GameRegistry;
import lawisAddonDqr1.api.blocks.LadBlocks;
import lawisAddonDqr1.block.blocks.LadJumpBlock2;
import lawisAddonDqr1.block.blocks.LadPackedIce;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

/*
 * ブロックを追加するクラス。
 */
public class LadInitBlocks {
	/*
	 * ブロックを追加・登録するメソッド。
	 */
	public static void initBlocks() {
		/* ジャンプブロック */
		LadBlocks.ladJumpBlock2 = new LadJumpBlock2()
			// 変更なし
			.setResistance(2000.0F)
			.setStepSound(Block.soundTypeStone)
			.setCreativeTab(CreativeTabs.tabBlock)
			.setBlockTextureName("dqr:JumpBlock2")
			// 変更あり
			.setHardness(0.5f)
			.setBlockName("ladJumpBlock2")
			.setLightLevel(1.0F);

		// 登録
		GameRegistry.registerBlock(LadBlocks.ladJumpBlock2, "ladJumpBlock2");

		/* 氷塊 */
		LadBlocks.ladPackedIce = new LadPackedIce()
			// 変更なし
			.setHardness(0.5F)
			.setStepSound(Block.soundTypeGlass)
			.setBlockTextureName("ice_packed")
			// 変更あり
			.setBlockName("ladPackedIce")
			.setLightLevel(0.8F);

		// 登録
		GameRegistry.registerBlock(LadBlocks.ladPackedIce, "ladPackedIce");
	}
}