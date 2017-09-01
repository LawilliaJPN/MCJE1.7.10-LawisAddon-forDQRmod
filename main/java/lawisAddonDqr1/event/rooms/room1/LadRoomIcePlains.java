package lawisAddonDqr1.event.rooms.room1;

import java.util.Random;

import lawisAddonDqr1.achievement.LadAchievementCore;
import lawisAddonDqr1.api.blocks.LadBlocks;
import lawisAddonDqr1.config.LadConfigCore;
import lawisAddonDqr1.config.LadDebug;
import lawisAddonDqr1.event.entities.LadMeasuresAgainstPlayerSuffocation;
import lawisAddonDqr1.event.entities.LadSpawnEnemyCore;
import lawisAddonDqr1.event.rooms.LadRoomID;
import lawisAddonDqr1.event.rooms.decoration.LadDecorationCross;
import lawisAddonDqr1.event.rooms.decoration.LadDecorationFloor;
import lawisAddonDqr1.event.rooms.decoration.LadDecorationPillar;
import lawisAddonDqr1.event.rooms.decoration.LadDecorationReward;
import lawisAddonDqr1.event.rooms.decoration.LadFillBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class LadRoomIcePlains {
	/*
	 * 氷原の戦闘部屋
	 */
	public static void setRoom(World world, EntityPlayer player) {
		Random rand = new Random();
		int roomDirection = LadRoomID.getDirectionRoom(player, 0);
		boolean isRoomDirection02 = false;

		int roomX = (int)player.posX;			// 部屋の起点となるX座標
		int roomZ = (int)player.posZ;			// 部屋の起点となるZ座標
		int roomY = (int)player.posY -1;		// 部屋の起点となるY座標（-1）

		int roomHeight = 5;					// 部屋の高さ
		int roomWidth = 12;					// 部屋の幅（-1）
		int roomCenter = roomWidth/2;			// 部屋の中心
		int roomDepth = -3;					// 部屋の深さ

		int floorType = rand.nextInt(9);		// 部屋の種類(床配置)
		int treeType = rand.nextInt(4);		// 部屋の種類(装飾)

		// コンフィグ：負荷軽減 オフの時に部屋を深くする
		if (!LadConfigCore.isRoomReduction) roomDepth = -6;

		// [Debug] 戦闘部屋固定時に生成方向がチャット表示される（デバッグ用）
		if (LadDebug.getDebugRoom() >=0) {
			player.addChatMessage(new ChatComponentTranslation("roomDirection == " + roomDirection));
			player.addChatMessage(new ChatComponentTranslation("floorType == " + floorType));
			player.addChatMessage(new ChatComponentTranslation("treeType == " + treeType));
			player.addChatMessage(new ChatComponentTranslation("difOfRoom == " + LadRoomID.getDifOfRoom()));
		}

		// 実績の取得
		player.triggerAchievement(LadAchievementCore.roomIcePlains);

		// マイナス座標の時に、部屋の位置がズレることの修正
		if (player.posX < 0) roomX -=1;
		if (player.posZ < 0) roomZ -=1;

		// プレイヤーの向きから部屋の起点となる座標を決める
		switch (roomDirection) {
		case 0:
			roomX -= 1;
			roomZ -= roomCenter;
			isRoomDirection02 = true;
			break;
		case 1:
			roomX -= roomCenter;
			roomZ -= 1;
			break;
		case 2:
			roomX -= roomWidth -1;
			roomZ -= roomCenter;
			isRoomDirection02 = true;
			break;
		case 3:
			roomX -= roomCenter;
			roomZ -= roomWidth -1;
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
		// 地面に「土ブロック」を敷く
		LadFillBlock.fillBlockXZ(world, Blocks.dirt, roomX, roomZ, roomWidth, roomY +roomDepth -1);
		LadDecorationPillar.setWall(world, Blocks.dirt, roomX, roomZ, roomWidth, roomY +roomDepth, -roomDepth +1);
		LadDecorationPillar.setFourPillar(world, Blocks.dirt, roomX +1, roomX +roomWidth -1, roomZ +1, roomZ +roomWidth -1, roomY +roomDepth, -roomDepth +1);

		// 表面の「土ブロック」と「雪ブロック」を敷く
		LadDecorationPillar.setBlockEnclosure(world, Blocks.snow_layer, roomX, roomZ, roomWidth, roomY +1);
		LadDecorationCross.setFourBlock(world, Blocks.snow_layer, roomX +1, roomX +roomWidth -1, roomZ +1, roomZ +roomWidth -1, roomY +1);

		if (isRoomDirection02) {
			// 表面の「土ブロック」と「雪ブロック」を敷く
			LadDecorationPillar.setFourPillar(world, Blocks.dirt, roomX +1, roomX +roomWidth -1, roomZ +2, roomZ +roomWidth -2, roomY +roomDepth, -roomDepth +1);
			LadDecorationPillar.setFourPillar(world, Blocks.dirt, roomX +1, roomX +roomWidth -1, roomZ +3, roomZ +roomWidth -3, roomY +roomDepth, -roomDepth +1);
			LadDecorationCross.setFourBlock(world, Blocks.snow_layer, roomX +1, roomX +roomWidth -1, roomZ +2, roomZ +roomWidth -2, roomY +1);
			LadDecorationCross.setFourBlock(world, Blocks.snow_layer, roomX +1, roomX +roomWidth -1, roomZ +3, roomZ +roomWidth -3, roomY +1);
			LadDecorationPillar.setWallZ(world, Blocks.dirt, roomX +1, roomZ +4, roomZ +roomWidth -4, roomY +roomDepth, -roomDepth);
			LadDecorationPillar.setWallZ(world, Blocks.dirt, roomX +roomWidth -1, roomZ +4, roomZ +roomWidth -4, roomY +roomDepth, -roomDepth);
			LadFillBlock.fillBlockZ(world, Blocks.snow_layer, roomX +1, roomZ +4, roomZ +roomWidth -4, roomY);
			LadFillBlock.fillBlockZ(world, Blocks.snow_layer, roomX +roomWidth -1, roomZ +4, roomZ +roomWidth -4, roomY);

			// 「水」を設置する
			LadFillBlock.fillBlock(world, Blocks.water, roomX +2, roomX +roomWidth -2, roomZ +1, roomZ +roomWidth -1, roomY +roomDepth, roomY -1);
		} else {
			// 表面の「土ブロック」と「雪ブロック」を敷く
			LadDecorationPillar.setFourPillar(world, Blocks.dirt, roomX +2, roomX +roomWidth -2, roomZ +1, roomZ +roomWidth -1, roomY +roomDepth, -roomDepth +1);
			LadDecorationPillar.setFourPillar(world, Blocks.dirt, roomX +3, roomX +roomWidth -3, roomZ +1, roomZ +roomWidth -1, roomY +roomDepth, -roomDepth +1);
			LadDecorationCross.setFourBlock(world, Blocks.snow_layer, roomX +2, roomX +roomWidth -2, roomZ +1, roomZ +roomWidth -1, roomY +1);
			LadDecorationCross.setFourBlock(world, Blocks.snow_layer, roomX +3, roomX +roomWidth -3, roomZ +1, roomZ +roomWidth -1, roomY +1);
			LadDecorationPillar.setWallX(world, Blocks.dirt, roomX +4, roomX +roomWidth -4, roomZ +1, roomY +roomDepth, -roomDepth);
			LadDecorationPillar.setWallX(world, Blocks.dirt, roomX +4, roomX +roomWidth -4, roomZ +roomWidth -1, roomY +roomDepth, -roomDepth);
			LadFillBlock.fillBlockX(world, Blocks.snow_layer, roomX +4, roomX +roomWidth -4, roomZ +1, roomY);
			LadFillBlock.fillBlockX(world, Blocks.snow_layer, roomX +4, roomX +roomWidth -4, roomZ +roomWidth -1, roomY);

			// 「水」を設置する
			LadFillBlock.fillBlock(world, Blocks.water, roomX +1, roomX +roomWidth -1, roomZ +2, roomZ +roomWidth -2, roomY +roomDepth, roomY -1);
		}

		// 手前の足場
		switch (floorType) {
		case 0:
		case 1:
		case 4:
		case 5:
		case 6:
			// 中央のみ
			if (roomDirection == 0) {
				LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +2, roomX +4, roomZ +roomCenter -2, roomZ +roomCenter +2, roomY -1);
			} else if (roomDirection == 2) {
				LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +roomCenter -2, roomZ +roomCenter +2, roomY -1);
			} else if (roomDirection == 1) {
				LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +roomCenter -2, roomX +roomCenter +2, roomZ +2, roomZ +4, roomY -1);
			} else {
				LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +roomCenter -2, roomX +roomCenter +2, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY -1);
			}
			break;
		default:
			// 全体
			int r = rand.nextInt(8);

			// コンフィグ：負荷軽減 オフの時に一定確率で両端の足場の高さが変わる
			if ((LadConfigCore.isRoomReduction) ||(r >= 4)) {
				if (roomDirection == 0) {
					LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +2, roomX +4, roomZ +1, roomZ +roomWidth -1, roomY -1);
				} else if (roomDirection == 2) {
					LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +1, roomZ +roomWidth -1, roomY -1);
				} else if (roomDirection == 1) {
					LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +1, roomX +roomWidth -1, roomZ +2, roomZ +4, roomY -1);
				} else {
					LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +1, roomX +roomWidth -1, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY -1);
				}
			} else {
				if (roomDirection == 0) {
					LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +2, roomX +4, roomZ +roomCenter -2, roomZ +roomCenter +2, roomY -1);
				} else if (roomDirection == 2) {
					LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +roomCenter -2, roomZ +roomCenter +2, roomY -1);
				} else if (roomDirection == 1) {
					LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +roomCenter -2, roomX +roomCenter +2, roomZ +2, roomZ +4, roomY -1);
				} else {
					LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +roomCenter -2, roomX +roomCenter +2, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY -1);
				}

				// 四隅に氷の木が生えない場合に、四隅の足場が氷塊ではなく雪ブロックになることがある。
				if ((treeType >= 2) && (r >= 2)) {
					switch (floorType) {
					case 0:
					case 2:
					case 5:
					case 8:
						if (r %2 == 0) {
							System.out.println("1");
							// 本来と同じ位置
							if (roomDirection == 0) {
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +2, roomX +4, roomZ +1, roomZ +3, roomY +roomDepth, roomY -1);
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +2, roomX +4, roomZ +roomWidth -3, roomZ +roomWidth -1, roomY +roomDepth, roomY -1);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +2, roomX +4, roomZ +1, roomZ +3, roomY);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +2, roomX +4, roomZ +roomWidth -3, roomZ +roomWidth -1, roomY);
							} else if (roomDirection == 2) {
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +1, roomZ +3, roomY +roomDepth, roomY -1);
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +roomWidth -3, roomZ +roomWidth -1, roomY +roomDepth, roomY -1);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +1, roomZ +3, roomY);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +roomWidth -3, roomZ +roomWidth -1, roomY);
							} else if (roomDirection == 1) {
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +1, roomX +3, roomZ +2, roomZ +4, roomY +roomDepth, roomY -1);
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +roomWidth -3, roomX +roomWidth -1, roomZ +2, roomZ +4, roomY +roomDepth, roomY -1);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +1, roomX +3, roomZ +2, roomZ +4, roomY);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +roomWidth -3, roomX +roomWidth -1, roomZ +2, roomZ +4, roomY);
							} else {
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +1, roomX +3, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY +roomDepth, roomY -1);
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +roomWidth -3, roomX +roomWidth -1, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY +roomDepth, roomY -1);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +1, roomX +3, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +roomWidth -3, roomX +roomWidth -1, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY);
							}
						} else if (r %2 == 1) {
							System.out.println("2");
							// 本来より高い位置
							if (roomDirection == 0) {
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +2, roomX +4, roomZ +1, roomZ +3, roomY +roomDepth, roomY);
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +2, roomX +4, roomZ +roomWidth -3, roomZ +roomWidth -1, roomY +roomDepth, roomY);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +2, roomX +4, roomZ +1, roomZ +3, roomY +1);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +2, roomX +4, roomZ +roomWidth -3, roomZ +roomWidth -1, roomY +1);
							} else if (roomDirection == 2) {
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +1, roomZ +3, roomY +roomDepth, roomY);
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +roomWidth -3, roomZ +roomWidth -1, roomY +roomDepth, roomY);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +1, roomZ +3, roomY +1);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +roomWidth -3, roomZ +roomWidth -1, roomY +1);
							} else if (roomDirection == 1) {
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +1, roomX +3, roomZ +2, roomZ +4, roomY +roomDepth, roomY);
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +roomWidth -3, roomX +roomWidth -1, roomZ +2, roomZ +4, roomY +roomDepth, roomY);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +1, roomX +3, roomZ +2, roomZ +4, roomY +1);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +roomWidth -3, roomX +roomWidth -1, roomZ +2, roomZ +4, roomY +1);
							} else {
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +1, roomX +3, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY +roomDepth, roomY);
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +roomWidth -3, roomX +roomWidth -1, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY +roomDepth, roomY);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +1, roomX +3, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY +1);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +roomWidth -3, roomX +roomWidth -1, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY +1);
							}
						}
						break;

					default:
						if (r %2 == 0) {
							System.out.println("3");
							// 本来より低い位置
							if (roomDirection == 0) {
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +2, roomX +4, roomZ +1, roomZ +3, roomY +roomDepth, roomY -2);
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +2, roomX +4, roomZ +roomWidth -3, roomZ +roomWidth -1, roomY +roomDepth, roomY -2);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +2, roomX +4, roomZ +1, roomZ +3, roomY -1);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +2, roomX +4, roomZ +roomWidth -3, roomZ +roomWidth -1, roomY -1);
							} else if (roomDirection == 2) {
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +1, roomZ +3, roomY +roomDepth, roomY -2);
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +roomWidth -3, roomZ +roomWidth -1, roomY +roomDepth, roomY -2);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +1, roomZ +3, roomY -1);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +roomWidth -3, roomZ +roomWidth -1, roomY -1);
							} else if (roomDirection == 1) {
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +1, roomX +3, roomZ +2, roomZ +4, roomY +roomDepth, roomY -2);
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +roomWidth -3, roomX +roomWidth -1, roomZ +2, roomZ +4, roomY +roomDepth, roomY -2);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +1, roomX +3, roomZ +2, roomZ +4, roomY -1);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +roomWidth -3, roomX +roomWidth -1, roomZ +2, roomZ +4, roomY -1);
							} else {
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +1, roomX +3, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY +roomDepth, roomY -2);
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +roomWidth -3, roomX +roomWidth -1, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY +roomDepth, roomY -2);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +1, roomX +3, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY -1);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +roomWidth -3, roomX +roomWidth -1, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY -1);
							}
						} else if (r %2 == 1) {
							System.out.println("4");
							r = rand.nextInt(2);
							// 本来より高い位置
							if (roomDirection == 0) {
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +2, roomX +4, roomZ +1, roomZ +3, roomY +roomDepth, roomY +r);
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +2, roomX +4, roomZ +roomWidth -3, roomZ +roomWidth -1, roomY +roomDepth, roomY +r);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +2, roomX +4, roomZ +1, roomZ +3, roomY +r +1);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +2, roomX +4, roomZ +roomWidth -3, roomZ +roomWidth -1, roomY +r +1);
							} else if (roomDirection == 2) {
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +1, roomZ +3, roomY +roomDepth, roomY +r);
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +roomWidth -3, roomZ +roomWidth -1, roomY +roomDepth, roomY +r);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +1, roomZ +3, roomY +r +1);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +roomWidth -3, roomZ +roomWidth -1, roomY +r +1);
							} else if (roomDirection == 1) {
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +1, roomX +3, roomZ +2, roomZ +4, roomY +roomDepth, roomY +r);
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +roomWidth -3, roomX +roomWidth -1, roomZ +2, roomZ +4, roomY +roomDepth, roomY +r);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +1, roomX +3, roomZ +2, roomZ +4, roomY +r +1);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +roomWidth -3, roomX +roomWidth -1, roomZ +2, roomZ +4, roomY +r +1);
							} else {
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +1, roomX +3, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY +roomDepth, roomY +r);
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +roomWidth -3, roomX +roomWidth -1, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY +roomDepth, roomY +r);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +1, roomX +3, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY +r +1);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +roomWidth -3, roomX +roomWidth -1, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY +r +1);
							}
						}
						break;
					}

				// 足場が氷塊
				} else {
					switch (floorType) {
					case 0:
					case 2:
					case 5:
					case 8:
						// 中央の両端がない場合は、水面より低い位置には足場ができない
						if (roomDirection == 0) {
							LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +2, roomX +4, roomZ +1, roomZ +3, roomY);
							LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +2, roomX +4, roomZ +roomWidth -3, roomZ +roomWidth -1, roomY);
						} else if (roomDirection == 2) {
							LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +1, roomZ +3, roomY);
							LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +roomWidth -3, roomZ +roomWidth -1, roomY);
						} else if (roomDirection == 1) {
							LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +1, roomX +3, roomZ +2, roomZ +4, roomY);
							LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +roomWidth -3, roomX +roomWidth -1, roomZ +2, roomZ +4, roomY);
						} else {
							LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +1, roomX +3, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY);
							LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +roomWidth -3, roomX +roomWidth -1, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY);
						}
						break;

					default:
						if (r %2 == 0) {
							System.out.println("5");
							// 本来より低い位置
							if (roomDirection == 0) {
								LadDecorationFloor.fillBlockAndAirXZ(world, Blocks.packed_ice, roomX +2, roomX +4, roomZ +1, roomZ +3, roomY -2);
								LadDecorationFloor.fillBlockAndAirXZ(world, Blocks.packed_ice, roomX +2, roomX +4, roomZ +roomWidth -3, roomZ +roomWidth -1, roomY -2);
							} else if (roomDirection == 2) {
								LadDecorationFloor.fillBlockAndAirXZ(world, Blocks.packed_ice, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +1, roomZ +3, roomY -2);
								LadDecorationFloor.fillBlockAndAirXZ(world, Blocks.packed_ice, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +roomWidth -3, roomZ +roomWidth -1, roomY -2);
							} else if (roomDirection == 1) {
								LadDecorationFloor.fillBlockAndAirXZ(world, Blocks.packed_ice, roomX +1, roomX +3, roomZ +2, roomZ +4, roomY -2);
								LadDecorationFloor.fillBlockAndAirXZ(world, Blocks.packed_ice, roomX +roomWidth -3, roomX +roomWidth -1, roomZ +2, roomZ +4, roomY -2);
							} else {
								LadDecorationFloor.fillBlockAndAirXZ(world, Blocks.packed_ice, roomX +1, roomX +3, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY -2);
								LadDecorationFloor.fillBlockAndAirXZ(world, Blocks.packed_ice, roomX +roomWidth -3, roomX +roomWidth -1, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY -2);
							}
							// 本来より高い位置
						} else if (r %2 == 1) {
							System.out.println("6");
							if (roomDirection == 0) {
								LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +2, roomX +4, roomZ +1, roomZ +3, roomY);
								LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +2, roomX +4, roomZ +roomWidth -3, roomZ +roomWidth -1, roomY);
							} else if (roomDirection == 2) {
								LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +1, roomZ +3, roomY);
								LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +roomWidth -3, roomZ +roomWidth -1, roomY);
							} else if (roomDirection == 1) {
								LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +1, roomX +3, roomZ +2, roomZ +4, roomY);
								LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +roomWidth -3, roomX +roomWidth -1, roomZ +2, roomZ +4, roomY);
							} else {
								LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +1, roomX +3, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY);
								LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +roomWidth -3, roomX +roomWidth -1, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY);
							}
						}
						break;
					}

				}
			}
			break;
		}

		// 中央の足場
		switch (floorType) {
		case 0:
		case 2:
		case 5:
		case 8:
			// 中央のみ
			LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +roomCenter -1, roomX + roomCenter +1, roomZ +roomCenter -1, roomZ +roomCenter +1, roomY -1);
			break;

		case 9:
			// 両端のみ
			if (isRoomDirection02) {
				LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +1, roomX +3, roomZ +roomCenter -1, roomZ +roomCenter +1, roomY -1);
				LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +roomWidth -3, roomX +roomWidth -1, roomZ +roomCenter -1, roomZ +roomCenter +1, roomY -1);
			} else {
				LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +roomCenter -1, roomX + roomCenter +1, roomZ +1, roomZ +3, roomY -1);
				LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +roomCenter -1, roomX + roomCenter +1, roomZ +roomWidth -3, roomZ +roomWidth -1, roomY -1);
			}
			break;

		default:
			// 全体
			if (isRoomDirection02) {
				LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +roomCenter -1, roomX + roomCenter +1, roomZ +1, roomZ +roomWidth -1, roomY -1);
			} else {
				LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +1, roomX +roomWidth -1, roomZ +roomCenter -1, roomZ +roomCenter +1, roomY -1);
			}
			break;
		}

		// 奥の足場
		switch (floorType) {
		case 0:
		case 1:
		case 4:
		case 5:
		case 6:
			// 中央のみ
			if (roomDirection == 0) {
				LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +roomCenter -2, roomZ +roomCenter +2, roomY -1);
			} else if (roomDirection == 2) {
				LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +2, roomX +4, roomZ +roomCenter -2, roomZ +roomCenter +2, roomY -1);
			} else if (roomDirection == 1) {
				LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +roomCenter -2, roomX +roomCenter +2, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY -1);
			} else {
				LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +roomCenter -2, roomX +roomCenter +2, roomZ +2, roomZ +4, roomY -1);
			}
			break;
		default:
			// 全体
			int r = rand.nextInt(8);

			// コンフィグ：負荷軽減 オフの時に一定確率で両端の足場の高さが変わる
			if ((LadConfigCore.isRoomReduction) ||(r >= 4)) {
				if (roomDirection == 0) {
					LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +1, roomZ +roomWidth -1, roomY -1);
				} else if (roomDirection == 2) {
					LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +2, roomX +4, roomZ +1, roomZ +roomWidth -1, roomY -1);
				} else if (roomDirection == 1) {
					LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +1, roomX +roomWidth -1, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY -1);
				} else {
					LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +1, roomX +roomWidth -1, roomZ +2, roomZ +4, roomY -1);
				}
			} else {
				if (roomDirection == 0) {
					LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +roomCenter -2, roomZ +roomCenter +2, roomY -1);
				} else if (roomDirection == 2) {
					LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +2, roomX +4, roomZ +roomCenter -2, roomZ +roomCenter +2, roomY -1);
				} else if (roomDirection == 1) {
					LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +roomCenter -2, roomX +roomCenter +2, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY -1);
				} else {
					LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +roomCenter -2, roomX +roomCenter +2, roomZ +2, roomZ +4, roomY -1);
				}

				// 四隅に氷の木が生えない場合に、四隅の足場が氷塊ではなく雪ブロックになることがある。
				if ((treeType >= 2) && (r >= 2)) {
					switch (floorType) {
					case 0:
					case 2:
					case 5:
					case 8:
						if (r %2 == 0) {
							System.out.println("7");
							// 本来と同じ位置
							if (roomDirection == 0) {
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +1, roomZ +3, roomY +roomDepth, roomY -1);
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +roomWidth -3, roomZ +roomWidth -1, roomY +roomDepth, roomY -1);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +1, roomZ +3, roomY);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +roomWidth -3, roomZ +roomWidth -1, roomY);
							} else if (roomDirection == 2) {
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +2, roomX +4, roomZ +1, roomZ +3, roomY +roomDepth, roomY -1);
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +2, roomX +4, roomZ +roomWidth -3, roomZ +roomWidth -1, roomY +roomDepth, roomY -1);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +2, roomX +4, roomZ +1, roomZ +3, roomY);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +2, roomX +4, roomZ +roomWidth -3, roomZ +roomWidth -1, roomY);
							} else if (roomDirection == 1) {
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +1, roomX +3, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY +roomDepth, roomY -1);
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +roomWidth -3, roomX +roomWidth -1, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY +roomDepth, roomY -1);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +1, roomX +3, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +roomWidth -3, roomX +roomWidth -1, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY);
							} else {
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +1, roomX +3, roomZ +2, roomZ +4, roomY +roomDepth, roomY -1);
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +roomWidth -3, roomX +roomWidth -1, roomZ +2, roomZ +4, roomY +roomDepth, roomY -1);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +1, roomX +3, roomZ +2, roomZ +4, roomY);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +roomWidth -3, roomX +roomWidth -1, roomZ +2, roomZ +4, roomY);
							}
						} else if (r %2 == 1) {
							System.out.println("8");
							// 本来より高い位置
							if (roomDirection == 0) {
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +1, roomZ +3, roomY +roomDepth, roomY);
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +roomWidth -3, roomZ +roomWidth -1, roomY +roomDepth, roomY);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +1, roomZ +3, roomY +1);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +roomWidth -3, roomZ +roomWidth -1, roomY +1);
							} else if (roomDirection == 2) {
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +2, roomX +4, roomZ +1, roomZ +3, roomY +roomDepth, roomY);
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +2, roomX +4, roomZ +roomWidth -3, roomZ +roomWidth -1, roomY +roomDepth, roomY);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +2, roomX +4, roomZ +1, roomZ +3, roomY +1);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +2, roomX +4, roomZ +roomWidth -3, roomZ +roomWidth -1, roomY +1);
							} else if (roomDirection == 1) {
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +1, roomX +3, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY +roomDepth, roomY);
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +roomWidth -3, roomX +roomWidth -1, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY +roomDepth, roomY);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +1, roomX +3, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY +1);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +roomWidth -3, roomX +roomWidth -1, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY +1);
							} else {
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +1, roomX +3, roomZ +2, roomZ +4, roomY +roomDepth, roomY);
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +roomWidth -3, roomX +roomWidth -1, roomZ +2, roomZ +4, roomY +roomDepth, roomY);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +1, roomX +3, roomZ +2, roomZ +4, roomY +1);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +roomWidth -3, roomX +roomWidth -1, roomZ +2, roomZ +4, roomY +1);
							}
						}
						break;

					default:
						if (r %2 == 0) {
							System.out.println("9");
							// 本来より低い位置
							if (roomDirection == 0) {
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +1, roomZ +3, roomY +roomDepth, roomY -2);
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +roomWidth -3, roomZ +roomWidth -1, roomY +roomDepth, roomY -2);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +1, roomZ +3, roomY -1);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +roomWidth -3, roomZ +roomWidth -1, roomY -1);
							} else if (roomDirection == 2) {
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +2, roomX +4, roomZ +1, roomZ +3, roomY +roomDepth, roomY -2);
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +2, roomX +4, roomZ +roomWidth -3, roomZ +roomWidth -1, roomY +roomDepth, roomY -2);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +2, roomX +4, roomZ +1, roomZ +3, roomY -1);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +2, roomX +4, roomZ +roomWidth -3, roomZ +roomWidth -1, roomY -1);
							} else if (roomDirection == 1) {
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +1, roomX +3, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY +roomDepth, roomY -2);
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +roomWidth -3, roomX +roomWidth -1, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY +roomDepth, roomY -2);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +1, roomX +3, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY -1);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +roomWidth -3, roomX +roomWidth -1, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY -1);
							} else {
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +1, roomX +3, roomZ +2, roomZ +4, roomY +roomDepth, roomY -2);
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +roomWidth -3, roomX +roomWidth -1, roomZ +2, roomZ +4, roomY +roomDepth, roomY -2);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +1, roomX +3, roomZ +2, roomZ +4, roomY -1);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +roomWidth -3, roomX +roomWidth -1, roomZ +2, roomZ +4, roomY -1);
							}
						} else if (r %2 == 1) {
							System.out.println("10");
							r = rand.nextInt(2);
							// 本来より高い位置
							if (roomDirection == 0) {
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +1, roomZ +3, roomY +roomDepth, roomY +r);
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +roomWidth -3, roomZ +roomWidth -1, roomY +roomDepth, roomY +r);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +1, roomZ +3, roomY +r +1);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +roomWidth -3, roomZ +roomWidth -1, roomY +r +1);
							} else if (roomDirection == 2) {
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +2, roomX +4, roomZ +1, roomZ +3, roomY +roomDepth, roomY +r);
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +2, roomX +4, roomZ +roomWidth -3, roomZ +roomWidth -1, roomY +roomDepth, roomY +r);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +2, roomX +4, roomZ +1, roomZ +3, roomY +r +1);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +2, roomX +4, roomZ +roomWidth -3, roomZ +roomWidth -1, roomY +r +1);
							} else if (roomDirection == 1) {
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +1, roomX +3, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY +roomDepth, roomY +r);
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +roomWidth -3, roomX +roomWidth -1, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY +roomDepth, roomY +r);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +1, roomX +3, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY +r +1);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +roomWidth -3, roomX +roomWidth -1, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY +r +1);
							} else {
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +1, roomX +3, roomZ +2, roomZ +4, roomY +roomDepth, roomY +r);
								LadFillBlock.fillBlock(world, Blocks.dirt, roomX +roomWidth -3, roomX +roomWidth -1, roomZ +2, roomZ +4, roomY +roomDepth, roomY +r);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +1, roomX +3, roomZ +2, roomZ +4, roomY +r +1);
								LadFillBlock.fillBlockXZ(world, Blocks.snow_layer, roomX +roomWidth -3, roomX +roomWidth -1, roomZ +2, roomZ +4, roomY +r +1);
							}
						}
						break;
					}

				// 足場が氷塊
				} else {
					switch (floorType) {
					case 0:
					case 2:
					case 5:
					case 8:
						// 中央の両端がない場合は、水面より低い位置には足場ができない
						if (roomDirection == 0) {
							LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +1, roomZ +3, roomY);
							LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +roomWidth -3, roomZ +roomWidth -1, roomY);
						} else if (roomDirection == 2) {
							LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +2, roomX +4, roomZ +1, roomZ +3, roomY);
							LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +2, roomX +4, roomZ +roomWidth -3, roomZ +roomWidth -1, roomY);
						} else if (roomDirection == 1) {
							LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +1, roomX +3, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY);
							LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +roomWidth -3, roomX +roomWidth -1, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY);
						} else {
							LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +1, roomX +3, roomZ +2, roomZ +4, roomY);
							LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +roomWidth -3, roomX +roomWidth -1, roomZ +2, roomZ +4, roomY);
						}
						break;

					default:
						if (r %2 == 0) {
							System.out.println("11");
							// 本来より低い位置
							if (roomDirection == 0) {
								LadDecorationFloor.fillBlockAndAirXZ(world, Blocks.packed_ice, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +1, roomZ +3, roomY -2);
								LadDecorationFloor.fillBlockAndAirXZ(world, Blocks.packed_ice, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +roomWidth -3, roomZ +roomWidth -1, roomY -2);
							} else if (roomDirection == 2) {
								LadDecorationFloor.fillBlockAndAirXZ(world, Blocks.packed_ice, roomX +2, roomX +4, roomZ +1, roomZ +3, roomY -2);
								LadDecorationFloor.fillBlockAndAirXZ(world, Blocks.packed_ice, roomX +2, roomX +4, roomZ +roomWidth -3, roomZ +roomWidth -1, roomY -2);
							} else if (roomDirection == 1) {
								LadDecorationFloor.fillBlockAndAirXZ(world, Blocks.packed_ice, roomX +1, roomX +3, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY -2);
								LadDecorationFloor.fillBlockAndAirXZ(world, Blocks.packed_ice, roomX +roomWidth -3, roomX +roomWidth -1, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY -2);
							} else {
								LadDecorationFloor.fillBlockAndAirXZ(world, Blocks.packed_ice, roomX +1, roomX +3, roomZ +2, roomZ +4, roomY -2);
								LadDecorationFloor.fillBlockAndAirXZ(world, Blocks.packed_ice, roomX +roomWidth -3, roomX +roomWidth -1, roomZ +2, roomZ +4, roomY -2);
							}
							// 本来より高い位置
						} else if (r %2 == 1) {
							System.out.println("12");
							if (roomDirection == 0) {
								LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +1, roomZ +3, roomY);
								LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +roomWidth -4, roomX +roomWidth -2, roomZ +roomWidth -3, roomZ +roomWidth -1, roomY);
							} else if (roomDirection == 2) {
								LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +2, roomX +4, roomZ +1, roomZ +3, roomY);
								LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +2, roomX +4, roomZ +roomWidth -3, roomZ +roomWidth -1, roomY);
							} else if (roomDirection == 1) {
								LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +1, roomX +3, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY);
								LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +roomWidth -3, roomX +roomWidth -1, roomZ +roomWidth -4, roomZ +roomWidth -2, roomY);
							} else {
								LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +1, roomX +3, roomZ +2, roomZ +4, roomY);
								LadFillBlock.fillBlockXZ(world, Blocks.packed_ice, roomX +roomWidth -3, roomX +roomWidth -1, roomZ +2, roomZ +4, roomY);
							}
						}
						break;
					}

				}
			}
			break;
		}

		/* 装飾 */
		// 中央
		if (treeType <= 1) {
			if (treeType == 1) {
				// 中央に1本
				setIceTree(world, roomX +roomCenter, roomY, roomZ +roomCenter, roomDepth, roomHeight);
			}

			if (rand.nextInt(2) == 0) {
				// 四隅に1本ずつ
				switch (roomDirection) {
				case 0:
				case 2:
					setIceTree(world, roomX +2, roomY, roomZ +1, roomDepth, roomHeight);
					setIceTree(world, roomX +2, roomY, roomZ +roomWidth -1, roomDepth, roomHeight);
					setIceTree(world, roomX +roomWidth -2, roomY, roomZ +1, roomDepth, roomHeight);
					setIceTree(world, roomX +roomWidth -2, roomY, roomZ +roomWidth -1, roomDepth, roomHeight);
					break;
				case 1:
				case 3:
					setIceTree(world, roomX +1, roomY, roomZ +2, roomDepth, roomHeight);
					setIceTree(world, roomX +1, roomY, roomZ +roomWidth -2, roomDepth, roomHeight);
					setIceTree(world, roomX +roomWidth -1, roomY, roomZ +2, roomDepth, roomHeight);
					setIceTree(world, roomX +roomWidth -1, roomY, roomZ +roomWidth -2, roomDepth, roomHeight);
					break;
				}
			} else {
				// 水中に光源
				LadFillBlock.fillBlockXZ(world, LadBlocks.ladPackedIce, roomX +1, roomZ +1, roomWidth -2, roomY +roomDepth);

				// コンフィグ：負荷軽減 オフの時に水中に木を生成
				if (!LadConfigCore.isRoomReduction) {
					setIceTreeInWater(world, roomX +roomCenter, roomY, roomZ +roomCenter, roomDepth);
					if (isRoomDirection02) {
						setIceTreeInWater(world, roomX +roomCenter -3, roomY, roomZ +roomCenter -4, roomDepth);
						setIceTreeInWater(world, roomX +roomCenter -3, roomY, roomZ +roomCenter +4, roomDepth);
						setIceTreeInWater(world, roomX +roomCenter +3, roomY, roomZ +roomCenter -4, roomDepth);
						setIceTreeInWater(world, roomX +roomCenter +3, roomY, roomZ +roomCenter +4, roomDepth);
					} else {
						setIceTreeInWater(world, roomX +roomCenter -4, roomY, roomZ +roomCenter -3, roomDepth);
						setIceTreeInWater(world, roomX +roomCenter -4, roomY, roomZ +roomCenter +3, roomDepth);
						setIceTreeInWater(world, roomX +roomCenter +4, roomY, roomZ +roomCenter -3, roomDepth);
						setIceTreeInWater(world, roomX +roomCenter +4, roomY, roomZ +roomCenter +3, roomDepth);
					}
				}
			}

		} else if (treeType == 2) {
			// 中央両端に1本ずつ、手前・奥に1本ずつ
			if (isRoomDirection02) {
				setIceTree(world, roomX +roomCenter, roomY, roomZ +2, roomDepth, roomHeight);
				setIceTree(world, roomX +roomCenter, roomY, roomZ +roomWidth -2, roomDepth, roomHeight);
				setIceTree(world, roomX +4, roomY, roomZ +roomCenter, roomDepth, roomHeight);
				setIceTree(world, roomX +roomWidth -4, roomY, roomZ +roomCenter, roomDepth, roomHeight);
			} else {
				setIceTree(world, roomX +2, roomY, roomZ +roomCenter, roomDepth, roomHeight);
				setIceTree(world, roomX +roomWidth -2, roomY, roomZ +roomCenter, roomDepth, roomHeight);
				setIceTree(world, roomX +roomCenter, roomY, roomZ +4, roomDepth, roomHeight);
				setIceTree(world, roomX +roomCenter, roomY, roomZ +roomWidth -4, roomDepth, roomHeight);
			}

		} else if (treeType == 3) {
			// 中央に4本
			setIceTree(world, roomX +4, roomY, roomZ +4, roomDepth, roomHeight);
			setIceTree(world, roomX +4, roomY, roomZ +roomWidth -4, roomDepth, roomHeight);
			setIceTree(world, roomX +roomWidth -4, roomY, roomZ +4, roomDepth, roomHeight);
			setIceTree(world, roomX +roomWidth -4, roomY, roomZ +roomWidth -4, roomDepth, roomHeight);
		}

		/* - - - - - - - - - -
		 * 以下、敵のスポーン
		 * - - - - - - - - - */

		// 確定スポーン
		switch (roomDirection) {
		case 0:
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -2, roomY +1, roomZ +roomCenter, LadRoomID.ICE_PLAINS + LadRoomID.getDifOfRoom());
			break;
		case 1:
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +1, roomZ +roomWidth -2, LadRoomID.ICE_PLAINS + LadRoomID.getDifOfRoom());
			break;
		case 2:
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +2, roomY +1, roomZ +roomCenter, LadRoomID.ICE_PLAINS + LadRoomID.getDifOfRoom());
			break;
		case 3:
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +1, roomZ +2, LadRoomID.ICE_PLAINS + LadRoomID.getDifOfRoom());
			break;
		}

		// 変動スポーン
		if (LadRoomID.getDifOfRoom() >= rand.nextInt(4)) {
			switch (roomDirection) {
			case 0:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -1, roomY +2, roomZ +2, LadRoomID.ICE_PLAINS + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -1, roomY +2, roomZ +roomWidth -2, LadRoomID.ICE_PLAINS + LadRoomID.getDifOfRoom());
				break;
			case 1:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +2, roomY +2, roomZ +roomWidth -1, LadRoomID.ICE_PLAINS + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -2, roomY +2, roomZ +roomWidth -1, LadRoomID.ICE_PLAINS + LadRoomID.getDifOfRoom());
				break;
			case 2:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +1, roomY +2, roomZ +2, LadRoomID.ICE_PLAINS + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +1, roomY +2, roomZ +roomWidth -2, LadRoomID.ICE_PLAINS + LadRoomID.getDifOfRoom());
				break;
			case 3:
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +2, roomY +2, roomZ +1, LadRoomID.ICE_PLAINS + LadRoomID.getDifOfRoom());
				LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -2, roomY +2, roomZ +1, LadRoomID.ICE_PLAINS + LadRoomID.getDifOfRoom());
				break;
			}
		}

		/* - - - - - - - - - -
		 * 以下、報酬
		 * - - - - - - - - - */

		switch (roomDirection) {
		case 0:
			LadDecorationReward.setChest(world, roomX +roomWidth, roomY, roomZ +roomCenter);
			break;
		case 1:
			LadDecorationReward.setChest(world, roomX +roomCenter, roomY, roomZ +roomWidth);
			break;
		case 2:
			LadDecorationReward.setChest(world, roomX, roomY, roomZ +roomCenter);
			break;
		case 3:
			LadDecorationReward.setChest(world, roomX +roomCenter, roomY, roomZ);
			break;
		}
	}

	/*
	 * 氷の木を生成するメソッド
	 */
	public static void setIceTree(World world, int x, int y, int z, int roomDepth, int roomHeight) {
		// 柱となる「氷塊」を設置する
		LadDecorationPillar.setPillar(world, LadBlocks.ladPackedIce, x, y +roomDepth, z, roomHeight -roomDepth +1);

		// 3段目の「氷塊」を設置する
		LadDecorationCross.setFourBlockCross(world, LadBlocks.ladPackedIce, x, z, y +3, 1);

		// 4段目の「氷塊」を設置する
		LadDecorationCross.setFourBlockSlanting(world, LadBlocks.ladPackedIce, x, z, y +4, 1);
	}
	/*
	 * 水中の氷の木を生成するメソッド
	 */
	public static void setIceTreeInWater(World world, int x, int y, int z, int roomDepth) {
		// 柱となる「氷塊」を設置する
		LadDecorationPillar.setPillar(world, LadBlocks.ladPackedIce, x, y +roomDepth, z, -roomDepth -1);

		// 3段目の「氷塊」を設置する
		LadDecorationCross.setFourBlockCross(world, LadBlocks.ladPackedIce, x, z, y +roomDepth +2, 1);

		// 4段目の「氷塊」を設置する
		LadDecorationCross.setFourBlockSlanting(world, LadBlocks.ladPackedIce, x, z, y +roomDepth +3, 1);
	}

}