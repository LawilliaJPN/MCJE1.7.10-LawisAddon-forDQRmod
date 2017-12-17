package lawisAddonDqr1.event.rooms.room01upper;

import java.util.Random;

import lawisAddonDqr1.LawisAddonDQR01;
import lawisAddonDqr1.achievement.LadAchievementCore;
import lawisAddonDqr1.addon.LadAddons;
import lawisAddonDqr1.config.LadDebug;
import lawisAddonDqr1.event.entities.LadMeasuresAgainstPlayerSuffocation;
import lawisAddonDqr1.event.entities.LadSpawnEnemyCore;
import lawisAddonDqr1.event.rooms.LadRoomID;
import lawisAddonDqr1.event.rooms.decoration.LadDecorationCrops;
import lawisAddonDqr1.event.rooms.decoration.LadDecorationCross;
import lawisAddonDqr1.event.rooms.decoration.LadDecorationPillar;
import lawisAddonDqr1.event.rooms.decoration.LadDecorationReward;
import lawisAddonDqr1.event.rooms.decoration.LadDecorationTorch;
import lawisAddonDqr1.event.rooms.decoration.LadFillBlock;
import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class LadRoomBeach {
	/*
	 * 砂浜の戦闘部屋
	 */
	public static void setRoom(World world, EntityPlayer player) {
		Random rand = new Random();
		int roomDirection = LadRoomID.getDirectionRoom(player, 0);

		int roomX = (int)player.posX;			// 部屋の起点となるX座標
		int roomZ = (int)player.posZ;			// 部屋の起点となるZ座標
		int roomY = (int)player.posY -1;		// 部屋の起点となるY座標

		int roomHeight = 4;					// 部屋の高さ
		int roomDepth = -4;					// 部屋の深さ
		int roomWidth = 12;					// 部屋の幅（-1）
		int roomCenter = roomWidth /2;		// 部屋の中心

		int roomType = rand.nextInt(6);		// 部屋の種類
		boolean isGravelBeach = false;		// 「砂利の砂浜」であるかどうか
		if (rand.nextInt(16) == 0) isGravelBeach = true;

		// [Debug] 戦闘部屋固定時に生成方向がチャット表示される（デバッグ用）
		if (LadDebug.getDebugRoom() >=0) {
			player.addChatMessage(new ChatComponentTranslation("roomDirection == " + roomDirection));
			player.addChatMessage(new ChatComponentTranslation("roomType == " + roomType));
			player.addChatMessage(new ChatComponentTranslation("difOfRoom == " + LadRoomID.getDifOfRoom()));
		}

		// 実績の取得
		player.triggerAchievement(LadAchievementCore.roomBeach);

		// マイナス座標の時に、部屋の位置がズレることの修正
		if (player.posX < 0) roomX -=1;
		if (player.posZ < 0) roomZ -=1;

		// プレイヤーの向きから部屋の起点となる座標を決める
		switch (roomDirection) {
		case 0:
			roomX -= 2;
			roomZ -= roomCenter;
			break;
		case 1:
			roomX -= roomCenter;
			roomZ -= 2;
			break;
		case 2:
			roomX -= roomWidth -2;
			roomZ -= roomCenter;
			break;
		case 3:
			roomX -= roomCenter;
			roomZ -= roomWidth -2;
			break;
		}

		/* - - - - - - - - - -
		 * 以下、部屋の生成
		 * - - - - - - - - - */

		// 落下物対策
		LadMeasuresAgainstPlayerSuffocation.measuresAgainstFallingObject(world, roomX, roomZ, roomWidth, roomWidth, roomY +roomHeight +1);

		/* 空間 */
		// 「空気」の設置
		LadFillBlock.fillBlockToAir(world, roomX, roomZ, roomWidth, roomY, roomHeight);

		/* 地面 */
		// 地面よりも下に「土ブロック」を敷く
		LadFillBlock.fillBlockXZ(world, Blocks.dirt, roomX, roomZ, roomWidth, roomY +roomDepth -1);

		// 地面に「砂ブロック」を敷く
		if (isGravelBeach) {
			LadFillBlock.fillBlock(world, Blocks.gravel, roomX, roomZ, roomWidth, roomY +roomDepth, -roomDepth -1);
			LadDecorationPillar.setBlockEnclosure(world, Blocks.gravel, roomX, roomZ, roomWidth, roomY);
			LadDecorationCross.setFourBlock(world, Blocks.gravel, roomX +1, roomX +roomWidth -1, roomZ +1, roomZ +roomWidth -1, roomY);
		} else {
			LadFillBlock.fillBlock(world, Blocks.sand, roomX, roomZ, roomWidth, roomY +roomDepth, -roomDepth -1);
			LadDecorationPillar.setBlockEnclosure(world, Blocks.sand, roomX, roomZ, roomWidth, roomY);
			LadDecorationCross.setFourBlock(world, Blocks.sand, roomX +1, roomX +roomWidth -1, roomZ +1, roomZ +roomWidth -1, roomY);
		}

		/* 手前と奥で差別化する必要がない部分を2方向に分けて生成 */
		switch (roomDirection) {
		case 0:
		case 2:
			// 「砂ブロック」を敷く
			if (isGravelBeach) {
				LadDecorationCross.setFourBlock(world, Blocks.gravel, roomX +1, roomX +roomWidth -1, roomZ +2, roomZ +roomWidth -2, roomY);
				LadDecorationCross.setFourBlock(world, Blocks.gravel, roomX +1, roomX +roomWidth -1, roomZ +3, roomZ +roomWidth -3, roomY);
			} else {
				LadDecorationCross.setFourBlock(world, Blocks.sand, roomX +1, roomX +roomWidth -1, roomZ +2, roomZ +roomWidth -2, roomY);
				LadDecorationCross.setFourBlock(world, Blocks.sand, roomX +1, roomX +roomWidth -1, roomZ +3, roomZ +roomWidth -3, roomY);
			}

			// 「水」を設置する
			LadFillBlock.fillBlock(world, Blocks.water, roomX +2, roomX +roomWidth -2, roomZ +1, roomZ +roomWidth -1, roomY +roomDepth, roomY -1);

			// 固定の足場となる「砂ブロック」を設置する
			if (isGravelBeach) {
				LadFillBlock.fillBlock(world, Blocks.gravel, roomX +2, roomX +4, roomZ +4, roomZ +roomWidth -4, roomY +roomDepth, roomY -1);
				LadFillBlock.fillBlock(world, Blocks.gravel, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +4, roomZ +roomWidth -4, roomY +roomDepth, roomY -1);
			} else {
				LadFillBlock.fillBlock(world, Blocks.sand, roomX +2, roomX +4, roomZ +4, roomZ +roomWidth -4, roomY +roomDepth, roomY -1);
				LadFillBlock.fillBlock(world, Blocks.sand, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +4, roomZ +roomWidth -4, roomY +roomDepth, roomY -1);
			}

			// 変動の足場となる、中央の両端の「砂ブロック」の設置
			if (roomType%2 == 0) {
				if (isGravelBeach) {
					LadFillBlock.fillBlock(world, Blocks.gravel, roomX +5, roomX +roomWidth -5, roomZ +1, roomZ +3, roomY +roomDepth, roomY -1);
					LadFillBlock.fillBlock(world, Blocks.gravel, roomX +5, roomX +roomWidth -5, roomZ +roomWidth -3, roomZ +roomWidth -1, roomY +roomDepth, roomY -1);
				} else {
					LadFillBlock.fillBlock(world, Blocks.sand, roomX +5, roomX +roomWidth -5, roomZ +1, roomZ +3, roomY +roomDepth, roomY -1);
					LadFillBlock.fillBlock(world, Blocks.sand, roomX +5, roomX +roomWidth -5, roomZ +roomWidth -3, roomZ +roomWidth -1, roomY +roomDepth, roomY -1);
				}
			}

			// 明るさ確保のための「松明」の設置
			LadDecorationTorch.setFourTorch(world, roomX +1, roomX +roomWidth -1, roomZ +4, roomZ +roomWidth -4, roomY);
			break;

		case 1:
		case 3:
			// 「砂ブロック」を敷く
			if (isGravelBeach) {
				LadDecorationCross.setFourBlock(world, Blocks.gravel, roomX +2, roomX +roomWidth -2, roomZ +1, roomZ +roomWidth -1, roomY);
				LadDecorationCross.setFourBlock(world, Blocks.gravel, roomX +3, roomX +roomWidth -3, roomZ +1, roomZ +roomWidth -1, roomY);
			} else {
				LadDecorationCross.setFourBlock(world, Blocks.sand, roomX +2, roomX +roomWidth -2, roomZ +1, roomZ +roomWidth -1, roomY);
				LadDecorationCross.setFourBlock(world, Blocks.sand, roomX +3, roomX +roomWidth -3, roomZ +1, roomZ +roomWidth -1, roomY);
			}

			// 「水」を設置する
			LadFillBlock.fillBlock(world, Blocks.water, roomX +1, roomX +roomWidth -1, roomZ +2, roomZ +roomWidth -2, roomY +roomDepth, roomY -1);

			// 固定の足場となる「砂ブロック」を設置する
			if (isGravelBeach) {
				LadFillBlock.fillBlock(world, Blocks.gravel, roomX +4, roomX +roomWidth -4, roomZ +2, roomZ +4, roomY +roomDepth, roomY -1);
				LadFillBlock.fillBlock(world, Blocks.gravel, roomX +4, roomX +roomWidth -4, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY +roomDepth, roomY -1);
			} else {
				LadFillBlock.fillBlock(world, Blocks.sand, roomX +4, roomX +roomWidth -4, roomZ +2, roomZ +4, roomY +roomDepth, roomY -1);
				LadFillBlock.fillBlock(world, Blocks.sand, roomX +4, roomX +roomWidth -4, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY +roomDepth, roomY -1);
			}

			// 変動の足場となる、中央の両端の「砂ブロック」の設置
			if (roomType%2 == 0) {
				if (isGravelBeach) {
					LadFillBlock.fillBlock(world, Blocks.gravel, roomX +1, roomX +3, roomZ +5, roomZ +roomWidth -5, roomY +roomDepth, roomY -1);
					LadFillBlock.fillBlock(world, Blocks.gravel, roomX +roomWidth -3, roomX +roomWidth -1, roomZ +5, roomZ +roomWidth -5, roomY +roomDepth, roomY -1);
				} else {
					LadFillBlock.fillBlock(world, Blocks.sand, roomX +1, roomX +3, roomZ +5, roomZ +roomWidth -5, roomY +roomDepth, roomY -1);
					LadFillBlock.fillBlock(world, Blocks.sand, roomX +roomWidth -3, roomX +roomWidth -1, roomZ +5, roomZ +roomWidth -5, roomY +roomDepth, roomY -1);
				}
			}

			// 明るさ確保のための「松明」の設置
			LadDecorationTorch.setFourTorch(world, roomX +4, roomX +roomWidth -4, roomZ +1, roomZ +roomWidth -1, roomY);
			break;
		}


		/* 「手前」と「奥」を差別化しなければならない部分を、4方向に分けて生成 */
		switch (roomDirection) {
		case 0:
			if (isGravelBeach) {
				// 変動の足場となる、手前の両端の「砂利ブロック」の設置
				if (roomType%3 == 0) {
					LadFillBlock.fillBlock(world, Blocks.gravel, roomX +2, roomX +4, roomZ +1, roomZ +3, roomY +roomDepth, roomY -1);
					LadFillBlock.fillBlock(world, Blocks.gravel, roomX +2, roomX +4, roomZ +roomWidth -3, roomZ +roomWidth -1, roomY +roomDepth, roomY -1);
				}
				// 変動の足場となる、奥の両端の「砂利ブロック」の設置
				if (roomType%3 == 1) {
					LadFillBlock.fillBlock(world, Blocks.gravel, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +1, roomZ +3, roomY +roomDepth, roomY -1);
					LadFillBlock.fillBlock(world, Blocks.gravel, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +roomWidth -3, roomZ +roomWidth -1, roomY +roomDepth, roomY -1);
				}

			} else {
				// 変動の足場となる、手前の両端の「砂ブロック」の設置
				if (roomType%3 == 0) {
					LadFillBlock.fillBlock(world, Blocks.sand, roomX +2, roomX +4, roomZ +1, roomZ +3, roomY +roomDepth, roomY -1);
					LadFillBlock.fillBlock(world, Blocks.sand, roomX +2, roomX +4, roomZ +roomWidth -3, roomZ +roomWidth -1, roomY +roomDepth, roomY -1);
				}
				// 変動の足場となる、奥の両端の「砂ブロック」の設置
				if (roomType%3 == 1) {
					LadFillBlock.fillBlock(world, Blocks.sand, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +1, roomZ +3, roomY +roomDepth, roomY -1);
					LadFillBlock.fillBlock(world, Blocks.sand, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +roomWidth -3, roomZ +roomWidth -1, roomY +roomDepth, roomY -1);
				}

				// 手前の両端の「サトウキビ」の設置
				if ((roomType%3 != 0) && (rand.nextInt(4) == 0)) {
					LadDecorationCrops.setReedsX(world, rand, roomX +2, roomX +4, roomZ +4, roomY);
					LadDecorationCrops.setReedsX(world, rand, roomX +2, roomX +4, roomZ +roomWidth -4, roomY);
				}
				// 奥の両端の「サトウキビ」の設置
				if ((roomType%3 != 1) && (rand.nextInt(4) == 0)) {
					LadDecorationCrops.setReedsX(world, rand, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +4, roomY);
					LadDecorationCrops.setReedsX(world, rand, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +roomWidth -4, roomY);
				}
			}
			break;

		case 1:
			if (isGravelBeach) {
				// 変動の足場となる、手前の両端の「砂利ブロック」の設置
				if (roomType%3 == 0) {
					LadFillBlock.fillBlock(world, Blocks.gravel, roomX +1, roomX +3, roomZ +2, roomZ +4, roomY +roomDepth, roomY -1);
					LadFillBlock.fillBlock(world, Blocks.gravel, roomX +roomWidth -3, roomX +roomWidth -1, roomZ +2, roomZ +4, roomY +roomDepth, roomY -1);
				}
				// 変動の足場となる、奥の両端の「砂利ブロック」の設置
				if (roomType%3 == 1) {
					LadFillBlock.fillBlock(world, Blocks.gravel, roomX +1, roomX +3, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY +roomDepth, roomY -1);
					LadFillBlock.fillBlock(world, Blocks.gravel, roomX +roomWidth -3, roomX +roomWidth -1, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY +roomDepth, roomY -1);
				}

			} else {
				// 変動の足場となる、手前の両端の「砂ブロック」の設置
				if (roomType%3 == 0) {
					LadFillBlock.fillBlock(world, Blocks.sand, roomX +1, roomX +3, roomZ +2, roomZ +4, roomY +roomDepth, roomY -1);
					LadFillBlock.fillBlock(world, Blocks.sand, roomX +roomWidth -3, roomX +roomWidth -1, roomZ +2, roomZ +4, roomY +roomDepth, roomY -1);
				}
				// 変動の足場となる、奥の両端の「砂ブロック」の設置
				if (roomType%3 == 1) {
					LadFillBlock.fillBlock(world, Blocks.sand, roomX +1, roomX +3, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY +roomDepth, roomY -1);
					LadFillBlock.fillBlock(world, Blocks.sand, roomX +roomWidth -3, roomX +roomWidth -1, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY +roomDepth, roomY -1);
				}

				// 手前の両端の「サトウキビ」の設置
				if ((roomType%3 != 0) && (rand.nextInt(4) == 0)) {
					LadDecorationCrops.setReedsZ(world, rand, roomX +4, roomZ +2, roomZ +4, roomY);
					LadDecorationCrops.setReedsZ(world, rand, roomX +roomWidth -4, roomZ +2, roomZ +4, roomY);
				}
				// 奥の両端の「サトウキビ」の設置
				if ((roomType%3 != 1) && (rand.nextInt(4) == 0)) {
					LadDecorationCrops.setReedsZ(world, rand, roomX +4, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY);
					LadDecorationCrops.setReedsZ(world, rand, roomX +roomWidth -4, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY);
				}
			}
			break;

		case 2:
			if (isGravelBeach) {
				// 変動の足場となる、手前の両端の「砂利ブロック」の設置
				if (roomType%3 == 0) {
					LadFillBlock.fillBlock(world, Blocks.gravel, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +1, roomZ +3, roomY +roomDepth, roomY -1);
					LadFillBlock.fillBlock(world, Blocks.gravel, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +roomWidth -3, roomZ +roomWidth -1, roomY +roomDepth, roomY -1);
				}
				// 変動の足場となる、奥の両端の「砂利ブロック」の設置
				if (roomType%3 == 1) {
					LadFillBlock.fillBlock(world, Blocks.gravel, roomX +2, roomX +4, roomZ +1, roomZ +3, roomY +roomDepth, roomY -1);
					LadFillBlock.fillBlock(world, Blocks.gravel, roomX +2, roomX +4, roomZ +roomWidth -3, roomZ +roomWidth -1, roomY +roomDepth, roomY -1);
				}

			} else {
				// 変動の足場となる、手前の両端の「砂ブロック」の設置
				if (roomType%3 == 0) {
					LadFillBlock.fillBlock(world, Blocks.sand, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +1, roomZ +3, roomY +roomDepth, roomY -1);
					LadFillBlock.fillBlock(world, Blocks.sand, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +roomWidth -3, roomZ +roomWidth -1, roomY +roomDepth, roomY -1);
				}
				// 変動の足場となる、奥の両端の「砂ブロック」の設置
				if (roomType%3 == 1) {
					LadFillBlock.fillBlock(world, Blocks.sand, roomX +2, roomX +4, roomZ +1, roomZ +3, roomY +roomDepth, roomY -1);
					LadFillBlock.fillBlock(world, Blocks.sand, roomX +2, roomX +4, roomZ +roomWidth -3, roomZ +roomWidth -1, roomY +roomDepth, roomY -1);
				}

				// 手前の両端の「サトウキビ」の設置
				if ((roomType%3 != 0) && (rand.nextInt(4) == 0)) {
					LadDecorationCrops.setReedsX(world, rand, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +4, roomY);
					LadDecorationCrops.setReedsX(world, rand, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +roomWidth -4, roomY);
				}
				// 奥の両端の「サトウキビ」の設置
				if ((roomType%3 != 1) && (rand.nextInt(4) == 0)) {
					LadDecorationCrops.setReedsX(world, rand, roomX +2, roomX +4, roomZ +4, roomY);
					LadDecorationCrops.setReedsX(world, rand, roomX +2, roomX +4, roomZ +roomWidth -4, roomY);
				}
			}
			break;

		case 3:
			if (isGravelBeach) {
				// 変動の足場となる、手前の両端の「砂利ブロック」の設置
				if (roomType%3 == 0) {
					LadFillBlock.fillBlock(world, Blocks.gravel, roomX +1, roomX +3, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY +roomDepth, roomY -1);
					LadFillBlock.fillBlock(world, Blocks.gravel, roomX +roomWidth -3, roomX +roomWidth -1, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY +roomDepth, roomY -1);
				}
				// 変動の足場となる、奥の両端の「砂利ブロック」の設置
				if (roomType%3 == 1) {
					LadFillBlock.fillBlock(world, Blocks.gravel, roomX +1, roomX +3, roomZ +2, roomZ +4, roomY +roomDepth, roomY -1);
					LadFillBlock.fillBlock(world, Blocks.gravel, roomX +roomWidth -3, roomX +roomWidth -1, roomZ +2, roomZ +4, roomY +roomDepth, roomY -1);
				}

			} else {
				// 変動の足場となる、手前の両端の「砂ブロック」の設置
				if (roomType%3 == 0) {
					LadFillBlock.fillBlock(world, Blocks.sand, roomX +1, roomX +3, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY +roomDepth, roomY -1);
					LadFillBlock.fillBlock(world, Blocks.sand, roomX +roomWidth -3, roomX +roomWidth -1, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY +roomDepth, roomY -1);
				}
				// 変動の足場となる、奥の両端の「砂ブロック」の設置
				if (roomType%3 == 1) {
					LadFillBlock.fillBlock(world, Blocks.sand, roomX +1, roomX +3, roomZ +2, roomZ +4, roomY +roomDepth, roomY -1);
					LadFillBlock.fillBlock(world, Blocks.sand, roomX +roomWidth -3, roomX +roomWidth -1, roomZ +2, roomZ +4, roomY +roomDepth, roomY -1);
				}

				// 手前の両端の「サトウキビ」の設置
				if ((roomType%3 != 0) && (rand.nextInt(4) == 0)) {
					LadDecorationCrops.setReedsZ(world, rand, roomX +4, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY);
					LadDecorationCrops.setReedsZ(world, rand, roomX +roomWidth -4, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY);
				}

				// 奥の両端の「サトウキビ」の設置
				if ((roomType%3 != 1) && (rand.nextInt(4) == 0)) {
					LadDecorationCrops.setReedsZ(world, rand, roomX +4, roomZ +2, roomZ +4, roomY);
					LadDecorationCrops.setReedsZ(world, rand, roomX +roomWidth -4, roomZ +2, roomZ +4, roomY);
				}
			}
			break;
		}

		// 変動の足場となる、中央の「砂ブロック」の設置
		if (roomType%2 == 1) {
			if (isGravelBeach) {
				LadFillBlock.fillBlock(world, Blocks.gravel, roomX +5, roomX +roomWidth -5, roomZ +5, roomZ +roomWidth -5, roomY +roomDepth, roomY -1);
			} else {
				LadFillBlock.fillBlock(world, Blocks.sand, roomX +5, roomX +roomWidth -5, roomZ +5, roomZ +roomWidth -5, roomY +roomDepth, roomY -1);
			}
		}

		if (!isGravelBeach) {
			// AMTと併用している場合
			if (LadAddons.isAmt2Loaded()) {
				try {
					// 中央に足場がない時に
					if (roomType%2 == 0) {
						// 8分の1の確率（合わせて16分の1、6.2%）
						if (rand.nextInt(8) == 0) {
							switch(roomDirection) {
							case 0:
							case 2:
								// 中央に2段低い「砂ブロック」の設置
								LadFillBlock.fillBlock(world, Blocks.sand, roomX +5, roomX +roomWidth -5, roomZ +4, roomZ +roomWidth -4, roomY +roomDepth, roomY -3);
								// 1段低い部分に「ハマグリ」の入った「砂ブロック」の設置
								LadFillBlock.fillBlockXZ(world, DCsAppleMilk.clamSand, roomX +5, roomX +roomWidth -5, roomZ +4, roomZ +roomWidth -4, roomY -2);
								break;
							case 1:
							case 3:
								// 中央に2段低い「砂ブロック」の設置
								LadFillBlock.fillBlock(world, Blocks.sand, roomX +4, roomX +roomWidth -4, roomZ +5, roomZ +roomWidth -5, roomY +roomDepth, roomY -3);
								// 1段低い部分に「ハマグリ」の入った「砂ブロック」の設置
								LadFillBlock.fillBlockXZ(world, DCsAppleMilk.clamSand, roomX +4, roomX +roomWidth -4, roomZ +5, roomZ +roomWidth -5, roomY -2);
								break;
							}
						}
					}
				} catch (Throwable t) {
					LawisAddonDQR01.logger.warn("Failed to load AMT2");
				}
			}
		}

		/* - - - - - - - - - -
		 * 以下、敵のスポーン
		 * - - - - - - - - - */

		// 確定スポーン
		switch (roomDirection) {
		case 0:
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -2, roomY +1, roomZ +roomCenter, LadRoomID.BEACH + LadRoomID.getDifOfRoom());
			break;
		case 1:
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +1, roomZ +roomWidth -2, LadRoomID.BEACH + LadRoomID.getDifOfRoom());
			break;
		case 2:
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +2, roomY +1, roomZ +roomCenter, LadRoomID.BEACH + LadRoomID.getDifOfRoom());
			break;
		case 3:
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +1, roomZ +2, LadRoomID.BEACH + LadRoomID.getDifOfRoom());
			break;
		}

		// 変動スポーン
		// 奥の両端が陸の時
		if (roomType%3 == 1) {
			switch (roomDirection) {
			case 0:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -2, roomY +1, roomZ +2, LadRoomID.BEACH + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -2, roomY +1, roomZ +roomWidth -2, LadRoomID.BEACH + LadRoomID.getDifOfRoom());
				break;
			case 1:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +2, roomY +1, roomZ +roomWidth -2, LadRoomID.BEACH + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -2, roomY +1, roomZ +roomWidth -2, LadRoomID.BEACH + LadRoomID.getDifOfRoom());
				break;
			case 2:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +2, roomY +1, roomZ +2, LadRoomID.BEACH + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +2, roomY +1, roomZ +roomWidth -2, LadRoomID.BEACH + LadRoomID.getDifOfRoom());
				break;
			case 3:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +2, roomY +1, roomZ +2, LadRoomID.BEACH + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -2, roomY +1, roomZ +2, LadRoomID.BEACH + LadRoomID.getDifOfRoom());
				break;
			}
		// 中央の両端が陸の時
		} else if (roomType%2 == 0) {
			switch (roomDirection) {
			case 0:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +1, roomZ +2, LadRoomID.BEACH + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +1, roomZ +roomWidth -2, LadRoomID.BEACH + LadRoomID.getDifOfRoom());
				break;
			case 1:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +2, roomY +1, roomZ +roomCenter, LadRoomID.BEACH + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -2, roomY +1, roomZ +roomCenter, LadRoomID.BEACH + LadRoomID.getDifOfRoom());
				break;
			case 2:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +1, roomZ +2, LadRoomID.BEACH + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +1, roomZ +roomWidth -2, LadRoomID.BEACH + LadRoomID.getDifOfRoom());
				break;
			case 3:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +2, roomY +1, roomZ +roomCenter, LadRoomID.BEACH + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -2, roomY +1, roomZ +roomCenter, LadRoomID.BEACH + LadRoomID.getDifOfRoom());
				break;
			}
		}

		/* - - - - - - - - - -
		 * 以下、報酬
		 * - - - - - - - - - */

		switch (roomDirection) {
		case 0:
			LadDecorationReward.setChest(world, roomX +roomWidth, roomZ +roomCenter, roomY);
			break;
		case 1:
			LadDecorationReward.setChest(world, roomX +roomCenter, roomZ +roomWidth, roomY);
			break;
		case 2:
			LadDecorationReward.setChest(world, roomX, roomZ +roomCenter, roomY);
			break;
		case 3:
			LadDecorationReward.setChest(world, roomX +roomCenter, roomZ, roomY);
			break;
		}
	}
}
