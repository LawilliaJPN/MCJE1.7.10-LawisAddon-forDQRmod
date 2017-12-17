package lawisAddonDqr1.item.items;

import dqr.api.Blocks.DQBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class PickaxeOrnamentM extends ItemPickaxe {
	public PickaxeOrnamentM(ToolMaterial material) {
		super(material);
	}

	// 右クリックした時の処理を追加
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		Block block = world.getBlock(x, y, z);

		// ブロックが「金の装飾石」または「エメラルドの装飾石」の場合
		if ((block == DQBlocks.DqmBlockKowareru6) || (block == DQBlocks.DqmBlockKowareru9)) {

			if (player.isPotionActive(Potion.digSlowdown)) {
				// 採掘速度低下中は効果なし
				if(!world.isRemote) {
					player.addChatMessage(new ChatComponentTranslation("「採掘速度低下」中のため、使用できない。"));
				}
				return false;

			} else {
				if(!world.isRemote) {
					// ブロックをなくす
					world.setBlockToAir(x, y, z);

					// 耐久値を1減らす
					stack.damageItem(1, player);
				}
				return true;
			}
		}
		return false;
	}
}