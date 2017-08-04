package lawisAddonDqr1.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;

public class BreakEventHundler {
	@SubscribeEvent
	public void BreakBlockEvent (BreakEvent event) {
		// System.out.println("BreakEvent OK");
		Block block = event.block;
		if (block == null) return;

		// Y=6～40の、石ブロックが破壊された時のみ処理を実行
		if ((block == Blocks.stone) && (event.y <= 45) && (event.y >= 6)) {
			new MiningPenalty(event.x, event.y, event.z, event.world, block, event.blockMetadata, event.getPlayer());
		}
	}
}