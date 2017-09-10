package lawisAddonDqr1.event.rooms;

import java.util.Random;

import lawisAddonDqr1.api.event.LadSetBattleRoomsEvent;
import lawisAddonDqr1.config.LadConfigCore;
import lawisAddonDqr1.config.LadDebug;
import lawisAddonDqr1.event.entities.LadMeasuresAgainstPetSuffocation;
import lawisAddonDqr1.event.entities.LadMeasuresAgainstPlayerSuffocation;
import lawisAddonDqr1.event.rooms.room1.LadRoomBeach;
import lawisAddonDqr1.event.rooms.room1.LadRoomDesertWell;
import lawisAddonDqr1.event.rooms.room1.LadRoomForest;
import lawisAddonDqr1.event.rooms.room1.LadRoomIcePlains;
import lawisAddonDqr1.event.rooms.room1.LadRoomVillageWell;
import lawisAddonDqr1.event.rooms.room1.LadRoomWeaponShop;
import lawisAddonDqr1.event.rooms.room2.LadRoomDama;
import lawisAddonDqr1.event.rooms.room2.LadRoomIceCave;
import lawisAddonDqr1.event.rooms.room2.LadRoomMedalKing;
import lawisAddonDqr1.event.rooms.room2.LadRoomMineShaft;
import lawisAddonDqr1.event.rooms.room2.LadRoomPyramid;
import lawisAddonDqr1.event.rooms.room2.LadRoomStronghold;
import lawisAddonDqr1.event.rooms.room3.LadRoomEndPortal;
import lawisAddonDqr1.event.rooms.room3.LadRoomNether;
import lawisAddonDqr1.event.rooms.room4.LadRoomSpecial01;
import lawisAddonDqr1.event.rooms.room4.LadRoomSpecial02;
import lawisAddonDqr1.event.rooms.room4.LadRoomSpecial04;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class LadRoomCore {
	/*
	 * 戦闘部屋を生成する処理
	 */
	public static void setBattleRooms(World world, EntityPlayer player) {
		Random rand = new Random();

		// [ForgeEvent] 戦闘部屋生成前 介入用のイベント
		LadSetBattleRoomsEvent.PreSetRoomEvent preEvent = new LadSetBattleRoomsEvent.PreSetRoomEvent(world, player);
		MinecraftForge.EVENT_BUS.post(preEvent);


		/* 戦闘部屋の生成の前に */
		// プレイヤーの窒息対策
		LadMeasuresAgainstPlayerSuffocation.adjustPlayerPos(world, player);
		// 周囲のペットの窒息対策
		LadMeasuresAgainstPetSuffocation.pullPets(world, player);

		/* 戦闘部屋の生成 */
		// [Debug]戦闘部屋の種類を固定する処理（デバッグ用）
		if (LadDebug.getDebugRoom() >= 0) {
			if (!world.isRemote) {
				switch (LadDebug.getDebugRoom()) {
				case LadRoomID.FOREST:
					LadRoomForest.setRoom(world, player);
					break;
				case LadRoomID.VILLAGE_WELL:
				case LadRoomID.VILLAGE_WELL_HAS_CURSED:
					LadRoomVillageWell.setRoom(world, player);
					break;
				case LadRoomID.BEACH:
					LadRoomBeach.setRoom(world, player);
					break;
				case LadRoomID.DESERT_WELL:
					LadRoomDesertWell.setRoom(world, player);
					break;
				case LadRoomID.ICE_PLAINS:
					LadRoomIcePlains.setRoom(world, player);
					break;
				case LadRoomID.WEAPON_SHOP:
				case LadRoomID.WEAPON_SHOP_CUSTOMER:
					LadRoomWeaponShop.setRoom(world, player);
					break;
				case LadRoomID.PYRAMID:
					LadRoomPyramid.setRoom(world, player);
					break;
				case LadRoomID.MEDAL_KING:
					LadRoomMedalKing.setRoom(world, player);
					break;
				case LadRoomID.MINE_SHAFT:
					LadRoomMineShaft.setRoom(world, player);
					break;
				case LadRoomID.DAMA:
					LadRoomDama.setRoom(world, player);
					break;
				case LadRoomID.STRONGHOLD:
					LadRoomStronghold.setRoom(world, player);
					break;
				case LadRoomID.ICE_CAVE:
					LadRoomIceCave.setRoom(world, player);
					break;
				case LadRoomID.NETHER:
					LadRoomNether.setRoom(world, player);
					break;
				case LadRoomID.END_PORTAL:
					LadRoomEndPortal.setRoom(world, player);
					break;
				case LadRoomID.SPECIAL_01:
					LadRoomSpecial01.setRoom(world, player);
					break;
				case LadRoomID.SPECIAL_02:
					LadRoomSpecial02.setRoom(world, player);
					break;
				case LadRoomID.SPECIAL_04:
					LadRoomSpecial04.setRoom(world, player);
					break;
				default:
					System.out.println("デバッグ用の戦闘部屋ID指定が間違っています。");
				}
			}

		// Y=41～45
		} else if (LadRoomID.getDifOfRoom() == 1) {
			if (!world.isRemote) {
				switch (rand.nextInt(4)) {
				case 0:
					LadRoomForest.setRoom(world, player);
					break;
				case 1:
					LadRoomVillageWell.setRoom(world, player);
					break;
				case 2:
					LadRoomBeach.setRoom(world, player);
					break;
				case 3:
					LadRoomDesertWell.setRoom(world, player);
					break;
				}
			}

		// Y=36～40
		} else if (LadRoomID.getDifOfRoom() == 2) {
			if (!world.isRemote) {
				switch (rand.nextInt(6)) {
				case 0:
					LadRoomForest.setRoom(world, player);
					break;
				case 1:
					LadRoomVillageWell.setRoom(world, player);
					break;
				case 2:
					LadRoomBeach.setRoom(world, player);
					break;
				case 3:
					LadRoomIcePlains.setRoom(world, player);
					break;
				case 4:
					LadRoomWeaponShop.setRoom(world, player);
					break;
				case 5:
					LadRoomSpecial01.setRoom(world, player);
					break;
				}
			}

		// Y=31～35
		} else if (LadRoomID.getDifOfRoom() == 3) {
			if (!world.isRemote) {
				switch (rand.nextInt(7)) {
				case 0:
					LadRoomForest.setRoom(world, player);
					break;
				case 1:
					LadRoomVillageWell.setRoom(world, player);
					break;
				case 2:
					LadRoomBeach.setRoom(world, player);
					break;
				case 3:
					LadRoomDesertWell.setRoom(world, player);
					break;
				case 4:
					LadRoomIcePlains.setRoom(world, player);
					break;
				case 5:
					LadRoomWeaponShop.setRoom(world, player);
					break;
				case 6:
					LadRoomSpecial01.setRoom(world, player);
					break;
				}
			}

		// Y=21～30（中層）
		} else if (LadRoomID.getDifOfRoom() <= 5) {
			if (!world.isRemote) {
				switch (rand.nextInt(7)) {
					case 0:
						LadRoomPyramid.setRoom(world, player);
						break;
					case 1:
						LadRoomMedalKing.setRoom(world, player);
						break;
					case 2:
						LadRoomMineShaft.setRoom(world, player);
						break;
					case 3:
						LadRoomDama.setRoom(world, player);
						break;
					case 4:
						LadRoomStronghold.setRoom(world, player);
						break;
					case 5:
						LadRoomIceCave.setRoom(world, player);
						break;
					case 6:
						LadRoomSpecial02.setRoom(world, player);
						break;
				}
			}

		// Y=06～20（下層）
		} else if (LadRoomID.getDifOfRoom() <= 7) {
			if (!world.isRemote) {
				switch (rand.nextInt(2)) {
				case 0:
					LadRoomNether.setRoom(world, player);
					break;
				case 1:
					LadRoomEndPortal.setRoom(world, player);
					break;
				}
			}
		}

		// コンフィグ：採掘速度低下がオンの時、「採掘速度低下Ⅱ」のステータスを付与
		if (LadConfigCore.isMiningFatigue) {
			if (world.difficultySetting == EnumDifficulty.EASY){
				// 難易度イージーなら、10秒間
				player.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 10 * 20, 1));
            } else if (world.difficultySetting == EnumDifficulty.NORMAL){
            	// 難易度ノーマルなら、15秒間
            	player.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 15 * 20, 1));
            } else if (world.difficultySetting == EnumDifficulty.HARD){
            	// 難易度ハードなら、20秒間
            	player.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 20 * 20, 1));
            }

		}

		// [ForgeEvent] 戦闘部屋生成後 介入用のイベント
		LadSetBattleRoomsEvent.PostSetRoomEvent postEvent = new LadSetBattleRoomsEvent.PostSetRoomEvent(world, player);
		MinecraftForge.EVENT_BUS.post(postEvent);
	}
}
