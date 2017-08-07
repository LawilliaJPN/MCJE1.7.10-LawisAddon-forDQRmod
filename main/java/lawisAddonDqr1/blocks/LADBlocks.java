package lawisAddonDqr1.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import dqr.DQR;
import net.minecraft.block.Block;

public class LADBlocks {
	public static Block ladJumpBlock2;

	public static void initBlocks() {
		ladJumpBlock2 = new LadJumpBlock2()
			.setBlockName("ladJumpBlock2")				// 変更
			.setBlockUnbreakable()
			.setResistance(2000.0F)
			.setStepSound(Block.soundTypeStone)
			.setCreativeTab(DQR.tabs.DqmTabBlock)
			.setBlockTextureName("dqr:JumpBlock2")
			.setLightLevel(1.0F);						// 変更
		GameRegistry.registerBlock(ladJumpBlock2, "ladJumpBlock2");
	}
}
