package lawisAddonDqr1.event;

import java.util.Random;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import lawisAddonDqr1.config.LadDebug;
import lawisAddonDqr1.event.rooms.Room11GrassWell;
import lawisAddonDqr1.event.rooms.Room12WeaponShop;
import lawisAddonDqr1.event.rooms.Room13DesertWell;
import lawisAddonDqr1.event.rooms.Room14IcePlains;
import lawisAddonDqr1.event.rooms.Room15Forest;
import lawisAddonDqr1.event.rooms.Room16Beach;
import lawisAddonDqr1.event.rooms.Room41Special01;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;

public class BreakEventHundler {
	// 戦闘部屋の種類の数
	final public static int numOfRooms = 7;
	// 戦闘が起こるかどうかのカウント
	private static int countRandomEncounter = 0;
	// 戦闘部屋の難易度
	private static int difOfRoom = 0;

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
			// [Debug]countRandomEncounterを0に固定して、必ず強制戦闘が行われるようにする（デバッグ用）
			if (LadDebug.isDebugCountRandomEncounter0()) countRandomEncounter = 0;

			// ランダムエンカウント
			if (countRandomEncounter <= 0) {
				// 強制戦闘
				updateDifOfRoom(event.y);
				MiningPenalty(event.world, event.getPlayer());

				// 次の強制戦闘までのカウントを決定
				Random rand = new Random();
				countRandomEncounter = 10 + rand.nextInt(16);
			} else {
				countRandomEncounter--;
			}
		}
	}

	/*
	 * 強制的に戦闘を起こす処理
	 *
	 * [Unimplemented] Y=30以下は未実装
	 */
	public static void MiningPenalty(World world, EntityPlayer player) {
		// System.out.println("MiningPenalty OK");
		Random rand = new Random();

		/* 戦闘部屋の生成 */
		// [Debug]戦闘部屋を固定する処理（デバッグ用）
		if (LadDebug.getDebugRoom() >= 0) {
			if (!world.isRemote) {
				switch (LadDebug.getDebugRoom()) {
				case 15:
					Room15Forest.setRoomRoomForest(world, player, getDirectionRoom(player, 0));
					break;
				case 11:
					Room11GrassWell.setRoomGrassWell(world, player, getDirectionRoom(player, 1), false);
					break;
				case 112:
					Room11GrassWell.setRoomGrassWell(world, player, getDirectionRoom(player, 1), true);
					break;
				case 16:
					Room16Beach.setRoomRoomBeach(world, player, getDirectionRoom(player, 0));
					break;
				case 13:
					Room13DesertWell.setRoomDesertWell(world, player, getDirectionRoom(player, 0));
					break;
				case 14:
					Room14IcePlains.setRoomIcePlains(world, player, getDirectionRoom(player, 0));
					break;
				case 12:
					Room12WeaponShop.setRoomWeaponShop(world, player, getDirectionRoom(player, 0));
					break;
				case 41:
					Room41Special01.setRoomSpecial01(world, player);
					break;
				}
			}

		// Y=41～45
		} else if (difOfRoom == 1) {
			if (!world.isRemote) {
				switch (rand.nextInt(5)) {
				case 0:
					Room15Forest.setRoomRoomForest(world, player, getDirectionRoom(player, 0));
					break;
				case 1:
					Room11GrassWell.setRoomGrassWell(world, player, getDirectionRoom(player, 1), false);
					break;
				case 2:
					Room16Beach.setRoomRoomBeach(world, player, getDirectionRoom(player, 0));
					break;
				case 3:
					Room13DesertWell.setRoomDesertWell(world, player, getDirectionRoom(player, 0));
					break;
				case 4:
					Room41Special01.setRoomSpecial01(world, player);
					break;
				}
			}

		// Y=36～40
		} else if (difOfRoom == 2) {
			if (!world.isRemote) {
				switch (rand.nextInt(6)) {
				case 0:
					Room15Forest.setRoomRoomForest(world, player, getDirectionRoom(player, 0));
					break;
				case 1:
					Room11GrassWell.setRoomGrassWell(world, player, getDirectionRoom(player, 1), true);
					break;
				case 2:
					Room16Beach.setRoomRoomBeach(world, player, getDirectionRoom(player, 0));
					break;
				case 3:
					Room14IcePlains.setRoomIcePlains(world, player, getDirectionRoom(player, 0));
					break;
				case 4:
					Room12WeaponShop.setRoomWeaponShop(world, player, getDirectionRoom(player, 0));
					break;
				case 5:
					Room41Special01.setRoomSpecial01(world, player);
					break;
				}
			}

		// Y=31～35
		} else if (difOfRoom == 3) {
			if (!world.isRemote) {
				switch (rand.nextInt(8)) {
				case 0:
					Room15Forest.setRoomRoomForest(world, player, getDirectionRoom(player, 0));
					break;
				case 1:
					Room11GrassWell.setRoomGrassWell(world, player, getDirectionRoom(player, 1), false);
					break;
				case 2:
					Room11GrassWell.setRoomGrassWell(world, player, getDirectionRoom(player, 1), true);
					break;
				case 3:
					Room16Beach.setRoomRoomBeach(world, player, getDirectionRoom(player, 0));
					break;
				case 4:
					Room13DesertWell.setRoomDesertWell(world, player, getDirectionRoom(player, 0));
					break;
				case 5:
					Room14IcePlains.setRoomIcePlains(world, player, getDirectionRoom(player, 0));
					break;
				case 6:
					Room12WeaponShop.setRoomWeaponShop(world, player, getDirectionRoom(player, 0));
					break;
				case 7:
					Room41Special01.setRoomSpecial01(world, player);
					break;
				}
			}

		// Y=30以下(未実装)
		} else {

		}
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

	/*
	 * 破壊した「石ブロック」のY座標から、部屋の難易度を決定するメソッド
	 *
	 * [Unimplemented] 昼か夜かで変化する等の要素を後日実装予定。
	 * デバッグのしやすさを考慮し、すべての部屋が揃うまでは、高度ごとにそのまま難易度が決まる状態に
	 */
	public static void updateDifOfRoom(int y) {
		int d = 0;

		if (y >= 46) d = 0;
		else if (y >= 41) d = 1;
		else if (y >= 36) d = 2;
		else if (y >= 31) d = 3;
		else if (y >= 26) d = 4;
		else if (y >= 21) d = 5;
		else if (y >= 16) d = 6;
		else if (y >=  6) d = 7;
		else d = 0;

		difOfRoom = d;
	}

	/*
	 * 変数 difOfRoom の getter
	 */
	public static int getDifOfRoom() {
		return difOfRoom;
	}
}