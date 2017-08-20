package lawisAddonDqr1.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import dqr.DQR;
import lawisAddonDqr1.api.blocks.LadBlocks;
import lawisAddonDqr1.api.blocks.LadPackedIce;
import net.minecraft.block.Block;

public class LadInitBlocks {
	public static void initBlocks() {
		/* ジャンプブロック(LAD) */
		LadBlocks.ladJumpBlock2 = new LadJumpBlock2()
			// 変更なし
			.setBlockUnbreakable()
			.setResistance(2000.0F)
			.setStepSound(Block.soundTypeStone)
			.setCreativeTab(DQR.tabs.DqmTabBlock)
			.setBlockTextureName("dqr:JumpBlock2")
			// 変更あり
			.setBlockName("ladJumpBlock2")
			.setLightLevel(1.0F);

		// 登録
		GameRegistry.registerBlock(LadBlocks.ladJumpBlock2, "ladJumpBlock2");


		/* 氷塊(LAD) */
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
