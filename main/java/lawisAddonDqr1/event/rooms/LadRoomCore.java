package lawisAddonDqr1.event.rooms;

import java.util.Random;

import lawisAddonDqr1.api.event.LadSetBattleRoomsEvent;
import lawisAddonDqr1.config.LadConfigCore;
import lawisAddonDqr1.config.LadDebug;
import lawisAddonDqr1.event.entities.LadMeasuresAgainstPetSuffocation;
import lawisAddonDqr1.event.entities.LadMeasuresAgainstPlayerSuffocation;
import lawisAddonDqr1.event.rooms.room01upper.LadRoomBeach;
import lawisAddonDqr1.event.rooms.room01upper.LadRoomDesertWell;
import lawisAddonDqr1.event.rooms.room01upper.LadRoomForest;
import lawisAddonDqr1.event.rooms.room01upper.LadRoomIcePlains;
import lawisAddonDqr1.event.rooms.room01upper.LadRoomVillageWell;
import lawisAddonDqr1.event.rooms.room01upper.LadRoomWeaponShop;
import lawisAddonDqr1.event.rooms.room02middle.LadRoomDama;
import lawisAddonDqr1.event.rooms.room02middle.LadRoomIceCave;
import lawisAddonDqr1.event.rooms.room02middle.LadRoomMedalKing;
import lawisAddonDqr1.event.rooms.room02middle.LadRoomMineShaft;
import lawisAddonDqr1.event.rooms.room02middle.LadRoomPyramid;
import lawisAddonDqr1.event.rooms.room02middle.LadRoomStronghold;
import lawisAddonDqr1.event.rooms.room03lower.LadRoomBottomOfOverWorld;
import lawisAddonDqr1.event.rooms.room03lower.LadRoomEndPortal;
import lawisAddonDqr1.event.rooms.room03lower.LadRoomNether;
import lawisAddonDqr1.event.rooms.room04special.LadRoomDebug;
import lawisAddonDqr1.event.rooms.room04special.LadRoomSpecial01;
import lawisAddonDqr1.event.rooms.room04special.LadRoomSpecial02;
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
		if (!world.isRemote) {
			// プレイヤーの窒息対策
			LadMeasuresAgainstPlayerSuffocation.adjustPlayerPos(world, player);
			// 周囲のペットの窒息対策
			LadMeasuresAgainstPetSuffocation.pullPets(world, player);
		}


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
				case LadRoomID.BOTTOM_OF_OVERWORLD:
					LadRoomBottomOfOverWorld.setRoom(world, player);
					break;
				case LadRoomID.SPECIAL_01:
					LadRoomSpecial01.setRoom(world, player);
					break;
				case LadRoomID.SPECIAL_02:
					LadRoomSpecial02.setRoom(world, player);
					break;
				case LadRoomID.DEBUG_ROOM:
					LadRoomDebug.setRoom(world, player);
				default:
					System.out.println("デバッグ用の戦闘部屋ID指定が間違っています。");
				}
			}

		// Y=41～45
		} else if (LadRoomID.getDifOfRoom() == 1) {
			if (!world.isRemote) {
				int r = rand.nextInt(LadRoomID.getWeightOfRooms(LadRoomID.FOREST, LadRoomID.VILLAGE_WELL, LadRoomID.BEACH, LadRoomID.DESERT_WELL));

				if (r < LadRoomID.getWeightOfRoom(LadRoomID.FOREST)) {
					LadRoomForest.setRoom(world, player);
				} else if (r < LadRoomID.getWeightOfRooms(LadRoomID.FOREST, LadRoomID.VILLAGE_WELL)) {
					LadRoomVillageWell.setRoom(world, player);
				} else if (r < LadRoomID.getWeightOfRooms(LadRoomID.FOREST, LadRoomID.VILLAGE_WELL, LadRoomID.BEACH)) {
					LadRoomBeach.setRoom(world, player);
				} else {
					LadRoomDesertWell.setRoom(world, player);
				}
			}

		// Y=36～40
		} else if (LadRoomID.getDifOfRoom() == 2) {
			if (!world.isRemote) {
				int r = rand.nextInt(LadRoomID.getWeightOfRooms(LadRoomID.FOREST, LadRoomID.VILLAGE_WELL, LadRoomID.BEACH, LadRoomID.ICE_PLAINS, LadRoomID.WEAPON_SHOP, LadRoomID.SPECIAL_01));

				if (r < LadRoomID.getWeightOfRoom(LadRoomID.FOREST)) {
					LadRoomForest.setRoom(world, player);
				} else if (r < LadRoomID.getWeightOfRooms(LadRoomID.FOREST, LadRoomID.VILLAGE_WELL)) {
					LadRoomVillageWell.setRoom(world, player);
				} else if (r < LadRoomID.getWeightOfRooms(LadRoomID.FOREST, LadRoomID.VILLAGE_WELL, LadRoomID.BEACH)) {
					LadRoomBeach.setRoom(world, player);
				} else if (r < LadRoomID.getWeightOfRooms(LadRoomID.FOREST, LadRoomID.VILLAGE_WELL, LadRoomID.BEACH, LadRoomID.ICE_PLAINS)) {
					LadRoomIcePlains.setRoom(world, player);
				} else if (r < LadRoomID.getWeightOfRooms(LadRoomID.FOREST, LadRoomID.VILLAGE_WELL, LadRoomID.BEACH, LadRoomID.ICE_PLAINS, LadRoomID.WEAPON_SHOP)) {
					LadRoomWeaponShop.setRoom(world, player);
				} else {
					LadRoomSpecial01.setRoom(world, player);
				}
			}

		// Y=31～35
		} else if (LadRoomID.getDifOfRoom() == 3) {
			if (!world.isRemote) {
				int r = rand.nextInt(LadRoomID.getWeightOfRooms(LadRoomID.FOREST, LadRoomID.VILLAGE_WELL, LadRoomID.BEACH, LadRoomID.DESERT_WELL, LadRoomID.ICE_PLAINS, LadRoomID.WEAPON_SHOP, LadRoomID.SPECIAL_01));

				if (r < LadRoomID.getWeightOfRoom(LadRoomID.FOREST)) {
					LadRoomForest.setRoom(world, player);
				} else if (r < LadRoomID.getWeightOfRooms(LadRoomID.FOREST, LadRoomID.VILLAGE_WELL)) {
					LadRoomVillageWell.setRoom(world, player);
				} else if (r < LadRoomID.getWeightOfRooms(LadRoomID.FOREST, LadRoomID.VILLAGE_WELL, LadRoomID.BEACH)) {
					LadRoomBeach.setRoom(world, player);
				} else if (r < LadRoomID.getWeightOfRooms(LadRoomID.FOREST, LadRoomID.VILLAGE_WELL, LadRoomID.BEACH, LadRoomID.DESERT_WELL)) {
					LadRoomDesertWell.setRoom(world, player);
				} else if (r < LadRoomID.getWeightOfRooms(LadRoomID.FOREST, LadRoomID.VILLAGE_WELL, LadRoomID.BEACH, LadRoomID.DESERT_WELL, LadRoomID.ICE_PLAINS)) {
					LadRoomIcePlains.setRoom(world, player);
				} else if (r < LadRoomID.getWeightOfRooms(LadRoomID.FOREST, LadRoomID.VILLAGE_WELL, LadRoomID.BEACH, LadRoomID.DESERT_WELL, LadRoomID.ICE_PLAINS, LadRoomID.WEAPON_SHOP)) {
					LadRoomWeaponShop.setRoom(world, player);
				} else {
					LadRoomSpecial01.setRoom(world, player);
				}
			}

		// Y=21～30（中層）
		} else if (LadRoomID.getDifOfRoom() <= 5) {
			if (!world.isRemote) {
				int r = rand.nextInt(LadRoomID.getWeightOfRooms(LadRoomID.PYRAMID, LadRoomID.MEDAL_KING, LadRoomID.MINE_SHAFT, LadRoomID.DAMA, LadRoomID.STRONGHOLD, LadRoomID.ICE_CAVE, LadRoomID.SPECIAL_02));

				if (r < LadRoomID.getWeightOfRoom(LadRoomID.PYRAMID)) {
					LadRoomPyramid.setRoom(world, player);
				} else if (r < LadRoomID.getWeightOfRooms(LadRoomID.PYRAMID, LadRoomID.MEDAL_KING)) {
					LadRoomMedalKing.setRoom(world, player);
				} else if (r < LadRoomID.getWeightOfRooms(LadRoomID.PYRAMID, LadRoomID.MEDAL_KING, LadRoomID.MINE_SHAFT)) {
					LadRoomMineShaft.setRoom(world, player);
				} else if (r < LadRoomID.getWeightOfRooms(LadRoomID.PYRAMID, LadRoomID.MEDAL_KING, LadRoomID.MINE_SHAFT, LadRoomID.DAMA)) {
					LadRoomDama.setRoom(world, player);
				} else if (r < LadRoomID.getWeightOfRooms(LadRoomID.PYRAMID, LadRoomID.MEDAL_KING, LadRoomID.MINE_SHAFT, LadRoomID.DAMA, LadRoomID.STRONGHOLD)) {
					LadRoomStronghold.setRoom(world, player);
				} else if (r < LadRoomID.getWeightOfRooms(LadRoomID.PYRAMID, LadRoomID.MEDAL_KING, LadRoomID.MINE_SHAFT, LadRoomID.DAMA, LadRoomID.STRONGHOLD, LadRoomID.ICE_CAVE)) {
					LadRoomIceCave.setRoom(world, player);
				} else {
					LadRoomSpecial02.setRoom(world, player);
				}
			}

		// Y=06～20（下層）
		} else if (LadRoomID.getDifOfRoom() <= 7) {
			if (!world.isRemote) {
				int r = rand.nextInt(LadRoomID.getWeightOfRooms(LadRoomID.NETHER, LadRoomID.END_PORTAL, LadRoomID.BOTTOM_OF_OVERWORLD));

				if (r < LadRoomID.getWeightOfRoom(LadRoomID.NETHER)) {
					LadRoomNether.setRoom(world, player);
				} else if (r < LadRoomID.getWeightOfRooms(LadRoomID.NETHER, LadRoomID.END_PORTAL)) {
					LadRoomEndPortal.setRoom(world, player);
				} else {
					LadRoomBottomOfOverWorld.setRoom(world, player);
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