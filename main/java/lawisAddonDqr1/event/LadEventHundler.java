package lawisAddonDqr1.event;

import java.util.Random;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import dqr.api.Items.DQMagicTools;
import lawisAddonDqr1.LawisAddonDQR01;
import lawisAddonDqr1.config.LadConfigCore;
import lawisAddonDqr1.config.LadDebug;
import lawisAddonDqr1.event.rooms.LadRoomCore;
import lawisAddonDqr1.event.rooms.LadRoomID;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.util.ChatComponentTranslation;
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

	/*
	 * プレイヤーが何かしらをクリックした時に呼び出される処理
	 * MinecraftForge.EVENT_BUS.registerで呼び出されるので、staticを付けずに@SubscribeEventを付ける
	 */
	@SubscribeEvent
	public void MGTCancelEvent(PlayerInteractEvent event) {
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
	 * コンフィグ変更を反映させるイベント。
	 * MinecraftForge.EVENT_BUS.registerで呼び出されるので、staticを付けずに@SubscribeEventを付ける
	 */
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.modID.equals(LawisAddonDQR01.MOD_ID)) {
			LadConfigCore.syncConfig();
			LadEventHundler.updateCountRandomEncounter();
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