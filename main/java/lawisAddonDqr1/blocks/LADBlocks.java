package lawisAddonDqr1.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import dqr.DQR;
import net.minecraft.block.Block;

public class LadBlocks {
	public static Block ladJumpBlock2;

	public static void initBlocks() {
		ladJumpBlock2 = new LadJumpBlock2()
			// 変更なし
			.setBlockUnbreakable()
			.setResistance(2000.0F)
			.setStepSound(Block.soundTypeStone)
			.setCreativeTab(DQR.tabs.DqmTabBlock)
			.setBlockTextureName("dqr:JumpBlock2")

			// 変更あり
			.setBlockName("ladJumpBlock2")
			.setLightLevel(1.0F);

		GameRegistry.registerBlock(ladJumpBlock2, "ladJumpBlock2");
	}
}
