package lawisAddonDqr1.event.rooms.room3;

import java.util.Random;

import lawisAddonDqr1.achievement.LadAchievementCore;
import lawisAddonDqr1.config.LadDebug;
import lawisAddonDqr1.event.entities.LadSpawnEnemyCore;
import lawisAddonDqr1.event.rooms.LadRoomID;
import lawisAddonDqr1.event.rooms.decoration.LadDecorationCross;
import lawisAddonDqr1.event.rooms.decoration.LadDecorationPillar;
import lawisAddonDqr1.event.rooms.decoration.LadDecorationReward;
import lawisAddonDqr1.event.rooms.decoration.LadFillBlock;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class LadRoomNether {
	/*
	 * ネザーの戦闘部屋
	 */
	public static void setRoom(World world, EntityPlayer player) {
		Random rand = new Random();
		int roomDirection = LadRoomID.getDirectionRoom(player, 0);
		boolean isRoomDirection02 = false;

		int roomX = (int)player.posX;			// 部屋の起点となるX座標
		int roomZ = (int)player.posZ;			// 部屋の起点となるZ座標
		int roomY = (int)player.posY -1;		// 部屋の起点となるY座標（-1）

		int roomHeight = 8;					// 部屋の高さ
		int roomWidth = 14;					// 部屋の幅（-1）
		int roomCenter = roomWidth /2;		// 部屋の中心

		int roomFloor1Y = -1;					// 1階の床の高さ
		int floorType = rand.nextInt(4);		// 部屋の種類(床配置)
		int pillarType = rand.nextInt(12);	// 部屋の種類(柱配置)
		boolean isFourNetherPillarSlanting = false;

		/* ブロックの種類の決定 */
		int blockType = rand.nextInt(3);
		Block fBlock = Blocks.netherrack;
		Block eBlock = Blocks.netherrack;
		if (blockType == 0) {
			fBlock = Blocks.soul_sand;
		} else if (blockType == 1) {
			fBlock = Blocks.nether_brick;
			eBlock = Blocks.nether_brick;
		}

		/* 部屋の生成位置などの調整 */
		// マイナス座標の時に、部屋の位置がズレることの修正
		if (player.posX < 0) roomX -=1;
		if (player.posZ < 0) roomZ -=1;

		// プレイヤーの向きから部屋の起点となる座標を決める
		switch (roomDirection) {
		case 0:
			roomX -= 2;
			roomZ -= roomCenter;
			isRoomDirection02 = true;
			break;
		case 1:
			roomX -= roomCenter;
			roomZ -= 2;
			break;
		case 2:
			roomX -= roomWidth -2;
			roomZ -= roomCenter;
			isRoomDirection02 = true;
			break;
		case 3:
			roomX -= roomCenter;
			roomZ -= roomWidth -2;
			break;
		}

		/* デバッグ用 */
		// [Debug] 戦闘部屋固定時に生成方向がチャット表示される（デバッグ用）
		if (LadDebug.getDebugRoom() >=0) {
			player.addChatMessage(new ChatComponentTranslation("roomDirection == " + roomDirection));
			player.addChatMessage(new ChatComponentTranslation("blockType == " + blockType + ":" + fBlock.getLocalizedName()));
			player.addChatMessage(new ChatComponentTranslation("floorType == " + floorType));
			player.addChatMessage(new ChatComponentTranslation("pillarType == " + pillarType));
			player.addChatMessage(new ChatComponentTranslation("difOfRoom == " + LadRoomID.getDifOfRoom()));
		}

		// 実績の取得
		player.triggerAchievement(LadAchievementCore.roomNether);

		/* - - - - - - - - - -
		 * 以下、部屋の生成
		 * - - - - - - - - - */

		/* 空間 */
		// 床
		if (blockType == 0) {
			switch (rand.nextInt(3)) {
			case 0:
				// 全面がソウルサンド
				LadFillBlock.fillBlock(world, fBlock, roomX, roomZ, roomWidth, roomY -2, 2);
				break;
			case 1:
				// 中央がソウルサンド、両端がネザーラック
				LadFillBlock.fillBlockXZ(world, eBlock, roomX, roomZ, roomWidth, roomY -2);
				if (isRoomDirection02) {
					LadFillBlock.fillBlockXZ(world, fBlock, roomX +3, roomX +roomWidth -3, roomZ, roomZ +roomWidth, roomY -1);
					LadFillBlock.fillBlockXZ(world, eBlock, roomX, roomX +3, roomZ, roomZ +roomWidth, roomY -1);
					LadFillBlock.fillBlockXZ(world, eBlock, roomX +roomWidth -3, roomX +roomWidth, roomZ, roomZ +roomWidth, roomY -1);
				} else {

					LadFillBlock.fillBlockXZ(world, fBlock, roomX, roomX +roomWidth, roomZ +3, roomZ +roomWidth -3, roomY -1);
					LadFillBlock.fillBlockXZ(world, eBlock, roomX, roomX +roomWidth, roomZ, roomZ +3, roomY -1);
					LadFillBlock.fillBlockXZ(world, eBlock, roomX, roomX +roomWidth, roomZ +roomWidth -3, roomZ +roomWidth, roomY -1);
				}
				break;
			case 2:
				// 中央がネザーラック、両端がソウルサンド
				LadFillBlock.fillBlockXZ(world, eBlock, roomX, roomZ, roomWidth, roomY -2);
				if (isRoomDirection02) {
					LadFillBlock.fillBlockXZ(world, eBlock, roomX +3, roomX +roomWidth -3, roomZ, roomZ +roomWidth, roomY -1);
					LadFillBlock.fillBlockXZ(world, fBlock, roomX, roomX +3, roomZ, roomZ +roomWidth, roomY -1);
					LadFillBlock.fillBlockXZ(world, fBlock, roomX +roomWidth -3, roomX +roomWidth, roomZ, roomZ +roomWidth, roomY -1);
				} else {
					LadFillBlock.fillBlockXZ(world, eBlock, roomX, roomX +roomWidth, roomZ +3, roomZ +roomWidth -3, roomY -1);
					LadFillBlock.fillBlockXZ(world, fBlock, roomX, roomX +roomWidth, roomZ, roomZ +3, roomY -1);
					LadFillBlock.fillBlockXZ(world, fBlock, roomX, roomX +roomWidth, roomZ +roomWidth -3, roomZ +roomWidth, roomY -1);
				}
				break;
			}
		} else {
			LadFillBlock.fillBlock(world, fBlock, roomX, roomZ, roomWidth, roomY -2, 2);
		}

		// 壁・天井・中の空気
		LadDecorationPillar.setWall(world, eBlock, roomX -1, roomZ -1, roomWidth +2, roomY -2, roomHeight +2);
		LadFillBlock.fillBlockXZ(world, eBlock, roomX -1, roomZ -1, roomWidth +2, roomY +roomHeight);
		LadFillBlock.fillBlockToAir(world, roomX, roomZ, roomWidth, roomY, roomHeight -1);

		/* 火の装飾 */
		if (rand.nextInt(2) == 0) {
			/* 火を設置する場所の候補を3グループに分けて、グループごとにどの候補に火を設置するかを決める。 */
			int r1 = rand.nextInt(3);
			int r2 = rand.nextInt(4);
			int r3 = rand.nextInt(4);

			if (r1 != 0) LadDecorationCross.setFourBlockCross(world, Blocks.fire, roomX +roomCenter, roomZ +roomCenter, roomY, r1 +1);
			if (r2 != 0) LadDecorationCross.setFourBlockSlanting(world, Blocks.fire, roomX +roomCenter, roomZ +roomCenter, roomY, r2);

			if (r3 /2 == 0) {
				LadDecorationCross.setFourBlock(world, Blocks.fire, roomX +roomCenter -2, roomX +roomCenter +2, roomZ +roomCenter -1, roomZ +roomCenter +1, roomY);
			}

			if (r3 %2 == 0) {
				LadDecorationCross.setFourBlock(world, Blocks.fire, roomX +roomCenter -1, roomX +roomCenter +1, roomZ +roomCenter -2, roomZ +roomCenter +2, roomY);
			}

		} else {
			/* 火を設置する場所の候補を3グループに分けて、グループごとにすべて火を設置するか否かを決める。 */
			if (rand.nextInt(2) == 0) {
				LadDecorationCross.setFourBlockCross(world, Blocks.fire, roomX +roomCenter, roomZ +roomCenter, roomY, 2);
				LadDecorationCross.setFourBlockCross(world, Blocks.fire, roomX +roomCenter, roomZ +roomCenter, roomY, 3);
			}

			if (rand.nextInt(2) == 0) {
				LadDecorationCross.setFourBlockSlanting(world, Blocks.fire, roomX +roomCenter, roomZ +roomCenter, roomY, 1);
				LadDecorationCross.setFourBlockSlanting(world, Blocks.fire, roomX +roomCenter, roomZ +roomCenter, roomY, 2);
				LadDecorationCross.setFourBlockSlanting(world, Blocks.fire, roomX +roomCenter, roomZ +roomCenter, roomY, 3);
			}

			if (rand.nextInt(2) == 0) {
				LadDecorationCross.setFourBlock(world, Blocks.fire, roomX +roomCenter -2, roomX +roomCenter +2, roomZ +roomCenter -1, roomZ +roomCenter +1, roomY);
				LadDecorationCross.setFourBlock(world, Blocks.fire, roomX +roomCenter -1, roomX +roomCenter +1, roomZ +roomCenter -2, roomZ +roomCenter +2, roomY);
			}
		}

		/* 柱 */
		if (floorType <= 3) {
			// 中央
			if (pillarType /2 == 0) {
				if (pillarType /4 == 0) {
					// 溶岩の柱
					setLavaPillar(world, roomX +roomCenter, roomZ +roomCenter, roomY -1, roomHeight);
				} else if (pillarType /4 == 1) {
					// ネザーラックかネザーレンガの柱
					setNetherPillar(world, eBlock, roomX +roomCenter, roomZ +roomCenter, roomY -1, roomHeight, rand.nextInt(4));
				} else {
					// グロウストーン付きの柱
					setGlowstonePillar(world, eBlock, roomX +roomCenter, roomZ +roomCenter, roomY -1, roomHeight, rand.nextInt(18));
				}
			}

			// 四隅
			if (pillarType %2 == 0) {
				if (pillarType /4 == 0) {
					// 溶岩の柱
					setLavaPillar(world, roomX +1, roomZ +1, roomY -1, roomHeight);
					setLavaPillar(world, roomX +1, roomZ +roomWidth -1, roomY -1, roomHeight);
					setLavaPillar(world, roomX +roomWidth -1, roomZ +1, roomY -1, roomHeight);
					setLavaPillar(world, roomX +roomWidth -1, roomZ +roomWidth -1, roomY -1, roomHeight);
				} else if (pillarType /4 == 1) {
					// ネザーラックかネザーレンガの柱
					int type = rand.nextInt(4);
					setNetherPillar(world, eBlock, roomX +2, roomZ +2, roomY -1, roomHeight, type);
					setNetherPillar(world, eBlock, roomX +2, roomZ +roomWidth -2, roomY -1, roomHeight, type);
					setNetherPillar(world, eBlock, roomX +roomWidth -2, roomZ +2, roomY -1, roomHeight, type);
					setNetherPillar(world, eBlock, roomX +roomWidth -2, roomZ +roomWidth -2, roomY -1, roomHeight, type);
					// 四隅に柱がある場合に、地面の溶岩の配置を変更するための変数
					isFourNetherPillarSlanting = true;
				} else {
					// グロウストーン付きの柱
					int type = rand.nextInt(18);
					setGlowstonePillar(world, eBlock, roomX +2, roomZ +2, roomY -1, roomHeight, type);
					setGlowstonePillar(world, eBlock, roomX +2, roomZ +roomWidth -2, roomY -1, roomHeight, type);
					setGlowstonePillar(world, eBlock, roomX +roomWidth -2, roomZ +2, roomY -1, roomHeight, type);
					setGlowstonePillar(world, eBlock, roomX +roomWidth -2, roomZ +roomWidth -2, roomY -1, roomHeight, type);
				}
			}
		}

		/* 床の地形 */
		if (floorType == 1) {
			/* 左右に溶岩が配置されるパターン */
			int lavaWidth = 2;
			if (isFourNetherPillarSlanting) lavaWidth = 4;

			if (isRoomDirection02) {
				LadFillBlock.fillBlockXZ(world, Blocks.lava, roomX, roomX +roomWidth, roomZ, roomZ +lavaWidth, roomY -1);
				LadFillBlock.fillBlockXZ(world, Blocks.lava, roomX, roomX +roomWidth, roomZ +roomWidth -lavaWidth, roomZ +roomWidth, roomY -1);
			} else {
				LadFillBlock.fillBlockXZ(world, Blocks.lava, roomX, roomX +lavaWidth, roomZ, roomZ +roomWidth, roomY -1);
				LadFillBlock.fillBlockXZ(world, Blocks.lava, roomX +roomWidth -lavaWidth, roomX +roomWidth, roomZ, roomZ +roomWidth, roomY -1);
			}

		} else if (floorType == 2) {
			/* 四隅に溶岩が配置されるパターン */
			int lavaWidth = 3;
			if (isFourNetherPillarSlanting) lavaWidth = 4;

			LadFillBlock.fillBlockXZ(world, Blocks.lava, roomX, roomX +lavaWidth, roomZ, roomZ +lavaWidth, roomY -1);
			LadFillBlock.fillBlockXZ(world, Blocks.lava, roomX, roomX +lavaWidth, roomZ +roomWidth -lavaWidth, roomZ +roomWidth, roomY -1);
			LadFillBlock.fillBlockXZ(world, Blocks.lava, roomX +roomWidth -lavaWidth, roomX +roomWidth, roomZ, roomZ +lavaWidth, roomY -1);
			LadFillBlock.fillBlockXZ(world, Blocks.lava, roomX +roomWidth -lavaWidth, roomX +roomWidth, roomZ +roomWidth -lavaWidth, roomZ +roomWidth, roomY -1);

		} else if (floorType == 3) {
			/* 中央部分のみの左右に溶岩が配置されるパターン */
			if (isRoomDirection02) {
				LadFillBlock.fillBlockXZ(world, Blocks.lava, roomX +5, roomX +roomWidth -5, roomZ, roomZ +3, roomY -1);
				LadFillBlock.fillBlockXZ(world, Blocks.lava, roomX +5, roomX +roomWidth -5, roomZ +roomWidth -3, roomZ +roomWidth, roomY -1);
			} else {
				LadFillBlock.fillBlockXZ(world, Blocks.lava, roomX, roomX +3, roomZ +5, roomZ +roomWidth -5, roomY -1);
				LadFillBlock.fillBlockXZ(world, Blocks.lava, roomX +roomWidth -3, roomX +roomWidth, roomZ +5, roomZ +roomWidth -5, roomY -1);
			}
		}

		// 柱の一番下のブロックを再度上書き
		if (isFourNetherPillarSlanting) {
			LadDecorationCross.setBlockDiamond(world, eBlock, roomX +2, roomY -1, roomZ +2, 2);
			LadDecorationCross.setBlockDiamond(world, eBlock, roomX +2, roomY -1, roomZ +roomWidth -2, 2);
			LadDecorationCross.setBlockDiamond(world, eBlock, roomX +roomWidth -2, roomY -1, roomZ +2, 2);
			LadDecorationCross.setBlockDiamond(world, eBlock, roomX +roomWidth -2, roomY -1, roomZ +roomWidth -2, 2);
		}

		/* - - - - - - - - - -
		 * 以下、敵のスポーン
		 * - - - - - - - - - */

		switch (roomDirection) {
		case 0:
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -3, roomY +1, roomZ +roomCenter, LadRoomID.NETHER + LadRoomID.getDifOfRoom());
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -2, roomY +1, roomZ +roomCenter -2, LadRoomID.NETHER +LadRoomID.getDifOfRoom());
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomWidth -2, roomY +1, roomZ +roomCenter +2, LadRoomID.NETHER +LadRoomID.getDifOfRoom());
			break;
		case 1:
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +1, roomZ +roomWidth -3, LadRoomID.NETHER + LadRoomID.getDifOfRoom());
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter -2, roomY +1, roomZ +roomWidth -2, LadRoomID.NETHER +LadRoomID.getDifOfRoom());
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter +2, roomY +1, roomZ +roomWidth -2, LadRoomID.NETHER +LadRoomID.getDifOfRoom());
			break;
		case 2:
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +3, roomY +1, roomZ +roomCenter, LadRoomID.NETHER + LadRoomID.getDifOfRoom());
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +2, roomY +1, roomZ +roomCenter -2, LadRoomID.NETHER +LadRoomID.getDifOfRoom());
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +2, roomY +1, roomZ +roomCenter +2, LadRoomID.NETHER +LadRoomID.getDifOfRoom());
			break;
		case 3:
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter, roomY +1, roomZ +3, LadRoomID.NETHER + LadRoomID.getDifOfRoom());
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter -2, roomY +1, roomZ +2, LadRoomID.NETHER +LadRoomID.getDifOfRoom());
			LadSpawnEnemyCore.spawnEnemy(world, player, roomX +roomCenter +2, roomY +1, roomZ +2, LadRoomID.NETHER +LadRoomID.getDifOfRoom());
			break;
		}

		/* - - - - - - - - - -
		 * 以下、報酬
		 * - - - - - - - - - */

		switch (roomDirection) {
		case 0:
			LadDecorationReward.setChest(world, roomX +roomWidth +1, roomY, roomZ +roomCenter);
			break;
		case 1:
			LadDecorationReward.setChest(world, roomX +roomCenter, roomY, roomZ +roomWidth +1);
			break;
		case 2:
			LadDecorationReward.setChest(world, roomX -1, roomY, roomZ +roomCenter);
			break;
		case 3:
			LadDecorationReward.setChest(world, roomX +roomCenter, roomY, roomZ -1);
			break;
		}
	}

	/*
	 * 天井から溶岩を流すメソッド
	 */
	public static void setLavaPillar(World world, int x, int z, int floorY, int height) {
		LadDecorationPillar.setPillarToAir(world, x, floorY, z, height +1);
		world.setBlock(x, floorY +height +1, z, Blocks.flowing_lava);
	}

	/*
	 * 柱を生成するメソッド
	 * type rand.nextInt(4)を入れることを想定
	 */
	public static void setNetherPillar(World world, Block block, int x, int z, int floorY, int height, int type) {
		LadDecorationPillar.setPillar(world, block, x, floorY, z, height +1);

		LadDecorationCross.setBlockDiamond(world, block, x, floorY +1, z, 2);
		LadDecorationCross.setBlockDiamond(world, block, x, floorY +height, z, 2);

		LadDecorationCross.setFourBlockCross(world, block, x, z, floorY +2, 1);
		LadDecorationCross.setFourBlockCross(world, block, x, z, floorY +3, 1);
		LadDecorationCross.setFourBlockCross(world, block, x, z, floorY +height -1, 1);
		LadDecorationCross.setFourBlockCross(world, block, x, z, floorY +height -2, 1);

		// 以下、火
		if (type /2 == 0) {
			LadDecorationCross.setFourBlockCross(world, Blocks.fire, x, z, floorY +4, 1);
		}

		if (type %2 == 0) {
			LadDecorationCross.setFourBlockSlanting(world, Blocks.fire, x, z, floorY +2, 1);
		}
	}

	/*
	 * グロウストーンの柱を生成するメソッド
	 * type rand.nextInt(18)を入れることを想定
	 */
	public static void setGlowstonePillar(World world, Block block, int x, int z, int floorY, int height, int type) {
		LadDecorationCross.setBlockDiamond(world, Blocks.glowstone, x, floorY +height, z, 2);

		if (type /6 == 0) {
			LadDecorationCross.setBlockDiamond(world, Blocks.glowstone, x, floorY +height -2, z, 2);
			if (type %2 == 0) {
				LadDecorationCross.setBlockCross(world, Blocks.glowstone, x, floorY +height -1, z, 1);
			}
		} else if (type /6 == 1) {
			LadDecorationCross.setBlockCross(world, Blocks.glowstone, x, floorY +height -2, z, 1);
		} else {
			LadDecorationCross.setBlockCross(world, Blocks.glowstone, x, floorY +height -1, z, 1);
		}

		if (type /6  <= 1) {
			if (type %3 == 0) LadDecorationCross.setBlockDiamond(world, Blocks.glowstone, x, floorY +height -4, z, 2);
			else if (type %3 == 1) LadDecorationCross.setBlockCross(world, Blocks.glowstone, x, floorY +height -4, z, 1);
		}

		LadDecorationPillar.setPillar(world, block, x, floorY, z, height +1);
	}
}
