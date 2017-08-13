package lawisAddonDqr1.event;

import java.util.Random;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import lawisAddonDqr1.api.event.LadSetBattleRoomEvent;
import lawisAddonDqr1.config.LadConfigCore;
import lawisAddonDqr1.config.LadDebug;
import lawisAddonDqr1.event.rooms.RoomID;
import lawisAddonDqr1.event.rooms.room1.Room11GrassWell;
import lawisAddonDqr1.event.rooms.room1.Room12WeaponShop;
import lawisAddonDqr1.event.rooms.room1.Room13DesertWell;
import lawisAddonDqr1.event.rooms.room1.Room14IcePlains;
import lawisAddonDqr1.event.rooms.room1.Room15Forest;
import lawisAddonDqr1.event.rooms.room1.Room16Beach;
import lawisAddonDqr1.event.rooms.room2.Room21Pyramid;
import lawisAddonDqr1.event.rooms.room4.Room41Special01;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;

public class LadEventHundler {
	// 戦闘部屋の種類の数
	final public static int numOfRooms = 7;
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
				// 強制戦闘
				RoomID.updateDifOfRoom(event.y);
				MiningPenalty(event.world, event.getPlayer());

				// 次の強制戦闘までのカウントを決定
				Random rand = new Random();
				countRandomEncounter = LadConfigCore.frequencyOfBattle + rand.nextInt(LadConfigCore.frequencyOfBattle +1);
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
	public void UseBedEvent(PlayerWakeUpEvent event) {
		// System.out.println("UseBedEvent OK");

		// コンフィグ：ベッドペナルティがオンの時は、目覚めたら戦闘
		if (LadConfigCore.isBedPenalty) {
			World world = event.entityPlayer.worldObj;
			EntityPlayer player = event.entityPlayer;

			// ベッドを使い捨てにするために周囲に空気ブロック設置
			if (!world.isRemote) {
				for (int x = -3; x <= 3; x++) {
					for (int z = -3; z <= 3; z++) {
						for (int y = -3; y <= 3; y++) {
							world.setBlockToAir((int)player.posX +x, (int)player.posY +y, (int)player.posZ +z);
						}
					}
				}
			}

			// 強制戦闘
			RoomID.updateDifOfRoom(world);
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
				case RoomID.roomForest:
					Room15Forest.setRoom(world, player, getDirectionRoom(player, 0));
					break;
				case RoomID.roomGrassWell:
					Room11GrassWell.setRoom(world, player, getDirectionRoom(player, 1), false);
					break;
				case RoomID.roomGrassWellIsCursed:
					Room11GrassWell.setRoom(world, player, getDirectionRoom(player, 0), true);
					break;
				case RoomID.roomBeach:
					Room16Beach.setRoom(world, player, getDirectionRoom(player, 0));
					break;
				case RoomID.roomDesertWell:
					Room13DesertWell.setRoom(world, player, getDirectionRoom(player, 0));
					break;
				case RoomID.roomIcePlains:
					Room14IcePlains.setRoom(world, player, getDirectionRoom(player, 0));
					break;
				case RoomID.roomWeaponShop:
					Room12WeaponShop.setRoom(world, player, getDirectionRoom(player, 0));
					break;
				case RoomID.roomPyramid:
					Room21Pyramid.setRoom(world, player, getDirectionRoom(player, 0));
					break;
				case RoomID.roomSpecial01:
					Room41Special01.setRoom(world, player);
					break;
				}
			}

		// Y=41～45
		} else if (RoomID.getDifOfRoom() == 1) {
			if (!world.isRemote) {
				switch (rand.nextInt(5)) {
				case 0:
					Room15Forest.setRoom(world, player, getDirectionRoom(player, 0));
					break;
				case 1:
					Room11GrassWell.setRoom(world, player, getDirectionRoom(player, 1), false);
					break;
				case 2:
					Room16Beach.setRoom(world, player, getDirectionRoom(player, 0));
					break;
				case 3:
					Room13DesertWell.setRoom(world, player, getDirectionRoom(player, 0));
					break;
				case 4:
					Room41Special01.setRoom(world, player);
					break;
				}
			}

		// Y=36～40
		} else if (RoomID.getDifOfRoom() == 2) {
			if (!world.isRemote) {
				switch (rand.nextInt(6)) {
				case 0:
					Room15Forest.setRoom(world, player, getDirectionRoom(player, 0));
					break;
				case 1:
					Room11GrassWell.setRoom(world, player, getDirectionRoom(player, 0), true);
					break;
				case 2:
					Room16Beach.setRoom(world, player, getDirectionRoom(player, 0));
					break;
				case 3:
					Room14IcePlains.setRoom(world, player, getDirectionRoom(player, 0));
					break;
				case 4:
					Room12WeaponShop.setRoom(world, player, getDirectionRoom(player, 0));
					break;
				case 5:
					Room41Special01.setRoom(world, player);
					break;
				}
			}

		// Y=31～35
		} else if (RoomID.getDifOfRoom() == 3) {
			if (!world.isRemote) {
				switch (rand.nextInt(8)) {
				case 0:
					Room15Forest.setRoom(world, player, getDirectionRoom(player, 0));
					break;
				case 1:
					Room11GrassWell.setRoom(world, player, getDirectionRoom(player, 1), false);
					break;
				case 2:
					Room11GrassWell.setRoom(world, player, getDirectionRoom(player, 0), true);
					break;
				case 3:
					Room16Beach.setRoom(world, player, getDirectionRoom(player, 0));
					break;
				case 4:
					Room13DesertWell.setRoom(world, player, getDirectionRoom(player, 0));
					break;
				case 5:
					Room14IcePlains.setRoom(world, player, getDirectionRoom(player, 0));
					break;
				case 6:
					Room12WeaponShop.setRoom(world, player, getDirectionRoom(player, 0));
					break;
				case 7:
					Room41Special01.setRoom(world, player);
					break;
				}
			}

		// Y=30以下 未実装につき、Y=31～35と同様のものに、仮設定
		} else {
			if (!world.isRemote) {
				switch (rand.nextInt(8)) {
				case 0:
					Room15Forest.setRoom(world, player, getDirectionRoom(player, 0));
					break;
				case 1:
					Room11GrassWell.setRoom(world, player, getDirectionRoom(player, 1), false);
					break;
				case 2:
					Room11GrassWell.setRoom(world, player, getDirectionRoom(player, 0), true);
					break;
				case 3:
					Room16Beach.setRoom(world, player, getDirectionRoom(player, 0));
					break;
				case 4:
					Room13DesertWell.setRoom(world, player, getDirectionRoom(player, 0));
					break;
				case 5:
					Room14IcePlains.setRoom(world, player, getDirectionRoom(player, 0));
					break;
				case 6:
					Room12WeaponShop.setRoom(world, player, getDirectionRoom(player, 0));
					break;
				case 7:
					Room41Special01.setRoom(world, player);
					break;
				}
			}
		}

		// [ForgeEvent] 戦闘部屋生成後 介入用のイベント
		LadSetBattleRoomEvent.PostSetRoomEvent postEvent = new LadSetBattleRoomEvent.PostSetRoomEvent(world, player);
		MinecraftForge.EVENT_BUS.post(postEvent);
	}

	/*
	 * プレイヤーの水平方向の向きから、部屋の生成方向を決定するメソッド
	 */
	public static int getDirectionRoom(EntityPlayer player, int i) {
		/* i == 0 -> 上下左右, i == 1 ->斜め
		   ,-0+X
		  -,130
		  0,0P2
		  +,213
		  Z
		*/

		switch (i) {
		case 0:
			return MathHelper.floor_double((double)((player.rotationYaw +180.0F) *4.0F /360.0F) -0.5D) & 3;
		case 1:
			return MathHelper.floor_double((double)((player.rotationYaw +180.0F +45.0F) *4.0F /360.0F) -0.5D) & 3;
		}

		return 0;
	}
}