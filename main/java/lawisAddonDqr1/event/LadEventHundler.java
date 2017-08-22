package lawisAddonDqr1.event;

import java.util.Random;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import dqr.api.Items.DQMagicTools;
import lawisAddonDqr1.config.LadConfigCore;
import lawisAddonDqr1.config.LadDebug;
import lawisAddonDqr1.event.rooms.LadRoomCore;
import lawisAddonDqr1.event.rooms.LadRoomID;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;

public class LadEventHundler {
	// 戦闘が起こるかどうかのカウント
	private static int countRandomEncounter = 0;

	/*
	 * ブロックが破壊された時に呼び出される処理
	 * MinecraftForge.EVENT_BUS.registerで呼び出されるので、staticを付けずに@SubscribeEventを付ける
	 */
	@SubscribeEvent
	public void BreakBlockEvent(BreakEvent event) {
		// System.out.println("BreakBlockEvent OK");

		// ピースフルの時は、このイベントは動作しない
		if (event.world.difficultySetting == EnumDifficulty.PEACEFUL) {
			return;
		}

		Block block = event.block;
		int dim = event.world.provider.dimensionId;
		int playerY = (int)event.getPlayer().posY;

		// 「オーバーワールドのY座標45以下」の「石ブロック」が「Y座標6以上にいるプレイヤー」に破壊された時のみ処理を実行
		if ((dim == 0) && (block == Blocks.stone) && (event.y <= 45) && (playerY >= 6)) {
			// [Debug]変数countRandomEncounterを0に固定して、必ず強制戦闘が行われるようにする（デバッグ用）
			if (LadDebug.isDebugCountRandomEncounter0()) countRandomEncounter = 0;

			// ランダムエンカウント
			if (countRandomEncounter <= 0) {
				// 戦闘部屋の難易度を決定
				LadRoomID.updateDifOfRoom(event.y);

				// 戦闘部屋の生成
				LadRoomCore.setBattleRooms(event.world, event.getPlayer());

				// 次の強制戦闘までのカウントを決定
				updateCountRandomEncounter();
			} else {
				countRandomEncounter--;
			}
		}
	}

	/*
	 * プレイヤーが目を覚ましたときに呼び出される処理
	 * MinecraftForge.EVENT_BUS.registerで呼び出されるので、staticを付けずに@SubscribeEventを付ける
	 */
	@SubscribeEvent
	public void WakeUpEvent(PlayerWakeUpEvent event) {
		// System.out.println("UseBedEvent OK");

		// コンフィグ：ベッドペナルティがオンの時は、目覚めたら戦闘
		if (LadConfigCore.isBedPenalty) {
			World world = event.entityPlayer.worldObj;
			EntityPlayer player = event.entityPlayer;

			// ピースフルの時は、このイベントは動作しない
			if (world.difficultySetting == EnumDifficulty.PEACEFUL) {
				return;
			}

			// プレイヤーの位置確認（Y=21～200以外では発生しない）
			int dim = world.provider.dimensionId;
			int playerY = (int)player.posY;
			if ((dim == 0) && (playerY >= 21) && (playerY <= 200)) {

				// ベッドを使い捨てにするために、周囲に空気ブロックを設置
				if (!world.isRemote) {
					for (int x = -3; x <= 3; x++) {
						for (int z = -3; z <= 3; z++) {
							for (int y = -3; y <= 3; y++) {
								world.setBlockToAir((int)player.posX +x, (int)player.posY +y, (int)player.posZ +z);
							}
						}
					}
				}

				// 戦闘部屋の難易度を決定
				LadRoomID.updateDifOfRoom(world);

				// 戦闘部屋の生成
				LadRoomCore.setBattleRooms(world, player);
			}
		}
	}

	/*
	 * プレイヤーが何かしらをクリックした時に呼び出される処理
	 * MinecraftForge.EVENT_BUS.registerで呼び出されるので、staticを付けずに@SubscribeEventを付ける
	 */
	@SubscribeEvent
	public void MGTCancelEvent(PlayerInteractEvent event) {
		// ピースフルの時は、このイベントは動作しない
		if (event.world.difficultySetting == EnumDifficulty.PEACEFUL) {
			return;
		}

		// ブロックを右クリックした時
		if (event.action == event.action.RIGHT_CLICK_BLOCK) {
			// コンフィグ：採掘速度低下がオンの時
			if (LadConfigCore.isMiningFatigue) {
				// 採掘速度低下ポーションが付与されている時
				if (event.entityPlayer.isPotionActive(Potion.digSlowdown)) {
					// 破壊マジックツールを使用した時に、効果をキャンセルする
					if (event.entityPlayer.getHeldItem().getItem() == DQMagicTools.itemMagicToolBreak1) {
						event.entityPlayer.addChatMessage(new ChatComponentTranslation("「採掘速度低下」の効果中のため、「マジックツール(指定ブロック破壊)」を使用できない。"));
						event.setCanceled(true);
					} else if (event.entityPlayer.getHeldItem().getItem() == DQMagicTools.itemMagicToolBreak2) {
						event.entityPlayer.addChatMessage(new ChatComponentTranslation("「採掘速度低下」の効果中のため、「マジックツール(全ブロック破壊)」を使用できない。"));
						event.setCanceled(true);
					}
				}
			}
		}
	}

	/*
	 * 以下、getterやsetter等
	 */
	public static int getCountRandomEncounter() {
		return countRandomEncounter;
	}

	public static void setCountRandomEncounter(int i) {
		if (i <= 0) i = 0;
		else if (i >= 100) i = 100;

		countRandomEncounter = i;
	}

	public static void updateCountRandomEncounter() {
		Random rand = new Random();
		setCountRandomEncounter(LadConfigCore.frequencyOfBattle + rand.nextInt(LadConfigCore.frequencyOfBattle +1));
	}
}