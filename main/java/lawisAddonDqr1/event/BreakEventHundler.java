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
	public static int countRandomEncounter = 0;

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
			// デバッグ用
			if (LadDebug.isDebugCountRandomEncounter0()) countRandomEncounter = 0;

			// ランダムエンカウント
			if (countRandomEncounter <= 0) {
				// 強制戦闘
				MiningPenalty(event.world, event.y, event.getPlayer());

				// 次の強制戦闘までのカウントを決定
				Random rand = new Random();
				countRandomEncounter = 10 + rand.nextInt(16);
			} else {
				countRandomEncounter--;
			}
		}
	}

	/*
	 * 安全な採掘を防ぐために強制的に戦闘を起こす処理
	 */
	public static void MiningPenalty(World world, int y, EntityPlayer player) {
		// System.out.println("MiningPenalty OK");

		// 戦闘部屋の決定
		Random rand = new Random();
		int r = rand.nextInt(numOfRooms);

		// デバッグ用
		if (LadDebug.getDebugRoom() >= 0) r = LadDebug.getDebugRoom();

		// 戦闘部屋の生成
		if (y >= 41) {
			if (!world.isRemote) {
				switch (rand.nextInt(5)) {
				case 0:
					Room11GrassWell.setRoomGrassWell(world, player, getDirectionStone(player, 1));
					break;
				case 1:
					Room15Forest.setRoomRoomForest(world, player, getDirectionStone(player, 0));
					break;
				case 2:
					Room13DesertWell.setRoomDesertWell(world, player, getDirectionStone(player, 0));
					break;
				case 3:
					Room16Beach.setRoomRoomBeach(world, player, getDirectionStone(player, 0));
					break;
				case 4:
					Room41Special01.setRoomRoomSpecial01(world, player, getDirectionStone(player, 0));
					break;
				}
			}
		} else {
			if (!world.isRemote) {
				switch (r) {
				case 0:
					Room11GrassWell.setRoomGrassWell(world, player, getDirectionStone(player, 1));
					break;
				case 1:
					Room12WeaponShop.setRoomWeaponShop(world, player, getDirectionStone(player, 0));
					break;
				case 2:
					Room13DesertWell.setRoomDesertWell(world, player, getDirectionStone(player, 0));
					break;
				case 3:
					Room14IcePlains.setRoomIcePlains(world, player, getDirectionStone(player, 0));
					break;
				case 4:
					Room15Forest.setRoomRoomForest(world, player, getDirectionStone(player, 0));
					break;
				case 5:
					Room16Beach.setRoomRoomBeach(world, player, getDirectionStone(player, 0));
					break;
				case 6:
					Room41Special01.setRoomRoomSpecial01(world, player, getDirectionStone(player, 0));
					break;
				}
			}
		}
	}

	/*
	 * プレイヤーの水平方向の向きから、部屋の生成方向を決定するメソッド
	 */
	public static int getDirectionStone(EntityPlayer player, int i) {
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