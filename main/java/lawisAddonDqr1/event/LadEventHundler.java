package lawisAddonDqr1.event;

import java.util.Random;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import lawisAddonDqr1.api.event.LadSetBattleRoomEvent;
import lawisAddonDqr1.config.LadConfigCore;
import lawisAddonDqr1.config.LadDebug;
import lawisAddonDqr1.event.rooms.LadRooms;
import lawisAddonDqr1.event.rooms.room1.RoomBeach;
import lawisAddonDqr1.event.rooms.room1.RoomDesertWell;
import lawisAddonDqr1.event.rooms.room1.RoomForest;
import lawisAddonDqr1.event.rooms.room1.RoomIcePlains;
import lawisAddonDqr1.event.rooms.room1.RoomVillageWell;
import lawisAddonDqr1.event.rooms.room1.RoomWeaponShop;
import lawisAddonDqr1.event.rooms.room2.RoomPyramid;
import lawisAddonDqr1.event.rooms.room4.RoomSpecial01;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
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
				LadRooms.updateDifOfRoom(event.y);

				// 戦闘部屋の生成
				MiningPenalty(event.world, event.getPlayer());

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
			LadRooms.updateDifOfRoom(world);

			// 戦闘部屋の生成
			MiningPenalty(world, player);
		}
	}

	/*
	 * 強制的に戦闘を起こす処理
	 *
	 * [Unimplemented] Y=30以下は未実装
	 */
	public static void MiningPenalty(World world, EntityPlayer player) {
		Random rand = new Random();

		// [ForgeEvent] 戦闘部屋生成前 介入用のイベント
		LadSetBattleRoomEvent.PreSetRoomEvent preEvent = new LadSetBattleRoomEvent.PreSetRoomEvent(world, player);
		MinecraftForge.EVENT_BUS.post(preEvent);

		/* 戦闘部屋の生成 */
		// [Debug]戦闘部屋の種類を固定する処理（デバッグ用）
		if (LadDebug.getDebugRoom() >= 0) {
			if (!world.isRemote) {
				switch (LadDebug.getDebugRoom()) {
				case LadRooms.FOREST:
					RoomForest.setRoom(world, player, LadRooms.getDirectionRoom(player, 0));
					break;
				case LadRooms.VILLAGE_WELL:
					RoomVillageWell.setRoom(world, player, LadRooms.getDirectionRoom(player, 1), false);
					break;
				case LadRooms.VILLAGE_WELL_HAS_CURSED:
					RoomVillageWell.setRoom(world, player, LadRooms.getDirectionRoom(player, 0), true);
					break;
				case LadRooms.BEACH:
					RoomBeach.setRoom(world, player, LadRooms.getDirectionRoom(player, 0));
					break;
				case LadRooms.DESERT_WELL:
					RoomDesertWell.setRoom(world, player, LadRooms.getDirectionRoom(player, 0));
					break;
				case LadRooms.ICE_PLAINS:
					RoomIcePlains.setRoom(world, player, LadRooms.getDirectionRoom(player, 0));
					break;
				case LadRooms.WEAPON_SHOP:
					RoomWeaponShop.setRoom(world, player, LadRooms.getDirectionRoom(player, 0));
					break;
				case LadRooms.PYRAMID:
					RoomPyramid.setRoom(world, player, LadRooms.getDirectionRoom(player, 0));
					break;
				case LadRooms.SPECIAL_01:
					RoomSpecial01.setRoom(world, player);
					break;
				}
			}

		// Y=41～45
		} else if (LadRooms.getDifOfRoom() == 1) {
			if (!world.isRemote) {
				switch (rand.nextInt(5)) {
				case 0:
					RoomForest.setRoom(world, player, LadRooms.getDirectionRoom(player, 0));
					break;
				case 1:
					RoomVillageWell.setRoom(world, player, LadRooms.getDirectionRoom(player, 1), false);
					break;
				case 2:
					RoomBeach.setRoom(world, player, LadRooms.getDirectionRoom(player, 0));
					break;
				case 3:
					RoomDesertWell.setRoom(world, player, LadRooms.getDirectionRoom(player, 0));
					break;
				case 4:
					RoomSpecial01.setRoom(world, player);
					break;
				}
			}

		// Y=36～40
		} else if (LadRooms.getDifOfRoom() == 2) {
			if (!world.isRemote) {
				switch (rand.nextInt(6)) {
				case 0:
					RoomForest.setRoom(world, player, LadRooms.getDirectionRoom(player, 0));
					break;
				case 1:
					RoomVillageWell.setRoom(world, player, LadRooms.getDirectionRoom(player, 0), true);
					break;
				case 2:
					RoomBeach.setRoom(world, player, LadRooms.getDirectionRoom(player, 0));
					break;
				case 3:
					RoomIcePlains.setRoom(world, player, LadRooms.getDirectionRoom(player, 0));
					break;
				case 4:
					RoomWeaponShop.setRoom(world, player, LadRooms.getDirectionRoom(player, 0));
					break;
				case 5:
					RoomSpecial01.setRoom(world, player);
					break;
				}
			}

		// Y=31～35
		} else if (LadRooms.getDifOfRoom() == 3) {
			if (!world.isRemote) {
				switch (rand.nextInt(8)) {
				case 0:
					RoomForest.setRoom(world, player, LadRooms.getDirectionRoom(player, 0));
					break;
				case 1:
					RoomVillageWell.setRoom(world, player, LadRooms.getDirectionRoom(player, 1), false);
					break;
				case 2:
					RoomVillageWell.setRoom(world, player, LadRooms.getDirectionRoom(player, 0), true);
					break;
				case 3:
					RoomBeach.setRoom(world, player, LadRooms.getDirectionRoom(player, 0));
					break;
				case 4:
					RoomDesertWell.setRoom(world, player, LadRooms.getDirectionRoom(player, 0));
					break;
				case 5:
					RoomIcePlains.setRoom(world, player, LadRooms.getDirectionRoom(player, 0));
					break;
				case 6:
					RoomWeaponShop.setRoom(world, player, LadRooms.getDirectionRoom(player, 0));
					break;
				case 7:
					RoomSpecial01.setRoom(world, player);
					break;
				}
			}

		// Y=30以下 未実装につき、Y=31～35と同様のものに、仮設定
		} else {
			if (!world.isRemote) {
				switch (rand.nextInt(8)) {
				case 0:
					RoomForest.setRoom(world, player, LadRooms.getDirectionRoom(player, 0));
					break;
				case 1:
					RoomVillageWell.setRoom(world, player, LadRooms.getDirectionRoom(player, 1), false);
					break;
				case 2:
					RoomVillageWell.setRoom(world, player, LadRooms.getDirectionRoom(player, 0), true);
					break;
				case 3:
					RoomBeach.setRoom(world, player, LadRooms.getDirectionRoom(player, 0));
					break;
				case 4:
					RoomDesertWell.setRoom(world, player, LadRooms.getDirectionRoom(player, 0));
					break;
				case 5:
					RoomIcePlains.setRoom(world, player, LadRooms.getDirectionRoom(player, 0));
					break;
				case 6:
					RoomWeaponShop.setRoom(world, player, LadRooms.getDirectionRoom(player, 0));
					break;
				case 7:
					RoomSpecial01.setRoom(world, player);
					break;
				}
			}
		}

		// [ForgeEvent] 戦闘部屋生成後 介入用のイベント
		LadSetBattleRoomEvent.PostSetRoomEvent postEvent = new LadSetBattleRoomEvent.PostSetRoomEvent(world, player);
		MinecraftForge.EVENT_BUS.post(postEvent);
	}

	/*
	 * 以下、getter,setter等
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