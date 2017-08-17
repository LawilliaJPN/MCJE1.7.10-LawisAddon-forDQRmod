package lawisAddonDqr1.event.rooms.room2;

import java.util.Random;

import dqr.api.Blocks.DQBlocks;
import dqr.api.Blocks.DQDecorates;
import lawisAddonDqr1.config.LadDebug;
import lawisAddonDqr1.event.rooms.LadRoomCore;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class LadRoomDama {
	/*
	 * DQRの「ダーマ神殿」をモチーフにした戦闘部屋
	 *
	 * [Unimplemented] 大枠実装完了、細かい部分やスポーン設定は未実装。
	 */
	public static void setRoom(World world, EntityPlayer player) {
		Random rand = new Random();
		int roomDirection = LadRoomCore.getDirectionRoom(player, 0);

		int roomX = (int)player.posX;			// 部屋の起点となるX座標
		int roomZ = (int)player.posZ -1;		// 部屋の起点となるZ座標（-1）
		int roomY = (int)player.posY -1;		// 部屋の起点となるY座標（-1）

		int roomHeight = 6;					// 部屋の高さ
		int roomFloorY = -1;					// 床の高さ
		int roomWidthX = 12;					// 部屋のX方向の幅（-1）
		int roomWidthZ = 12;					// 部屋のZ方向の幅（-1）
		int roomCenterX = roomWidthX /2;		// 部屋のX方向の中心
		int roomCenterZ = roomWidthZ /2;		// 部屋のX方向の中心

		// [Debug] 戦闘部屋固定時に生成方向がチャット表示される（デバッグ用）
		if (LadDebug.getDebugRoom() >=0) {
			player.addChatMessage(new ChatComponentTranslation("roomDirection == " + roomDirection));
		}

		// プレイヤーの向きから部屋の起点となる座標を決める
		switch (roomDirection) {
		case 0:
			roomWidthX += 10;
			roomCenterX = roomWidthX /2;
			roomX -= 3;
			roomZ -= roomCenterZ;
			break;
		case 1:
			roomWidthZ += 10;
			roomCenterZ = roomWidthZ /2;
			roomX -= roomCenterX;
			roomZ -= 3;
			break;
		case 2:
			roomWidthX += 10;
			roomCenterX = roomWidthX /2;
			roomX -= roomWidthX -3;
			roomZ -= roomCenterZ;
			break;
		case 3:
			roomWidthZ += 10;
			roomCenterZ = roomWidthZ /2;
			roomX -= roomCenterX;
			roomZ -= roomWidthZ -3;
			break;
		}


		/* - - - - - - - - - -
		 * 以下、部屋の生成
		 * - - - - - - - - - */

		/* 空間 */
		// 「ネザー水晶の装飾石」の設置
		for (int x = 0; x <= roomWidthX; x++) {
			for (int z = 0; z <= roomWidthZ; z++) {
				for (int y = -2; y <= roomHeight; y++) {
					world.setBlock(roomX +x, roomY +y, roomZ +z, DQBlocks.DqmQuartzBlock);
				}
			}
		}
		// 「空気」の設置
		for (int x = 1; x <= roomWidthX -1; x++) {
			for (int z = 1; z <= roomWidthZ -1; z++) {
				for (int y = 0; y <= roomHeight -1; y++) {
					world.setBlockToAir(roomX +x, roomY +y, roomZ +z);
				}
			}
		}

		/* 装飾 */
		switch (roomDirection) {
		case 0:
		case 2:
			/* 床 */
			// 「ダメージ床」の設置
			for (int x = roomCenterX -1; x <= roomCenterX +1; x++) {
				for (int z = 1; z <= roomWidthZ -1; z++) {
					world.setBlock(roomX +x, roomY +roomFloorY, roomZ +z, DQBlocks.DqmBlockToramanaYuka2);
				}
			}
			// 「金の装飾石」の設置
			for (int z = 1; z <= roomWidthZ -1; z++) {
				world.setBlock(roomX +roomCenterX +2, roomY +roomFloorY, roomZ +z, DQBlocks.DqmBlockKowareru6);
				world.setBlock(roomX +roomCenterX -2, roomY +roomFloorY, roomZ +z, DQBlocks.DqmBlockKowareru6);
			}
			// 「溶岩」の設置
			for (int z = 1; z <= roomWidthZ -1; z++) {
				world.setBlock(roomX +1, roomY +roomFloorY, roomZ +z, Blocks.lava);
				world.setBlock(roomX +roomWidthX -1, roomY +roomFloorY, roomZ +z, Blocks.lava);
			}

			/* 他 */
			// 「大聖杯」と「炎」の設置
			setDqmBlockKagaribidai(world, roomX +roomCenterX +4, roomY +roomFloorY +1, roomZ +3);
			setDqmBlockKagaribidai(world, roomX +roomCenterX +4, roomY +roomFloorY +1, roomZ +roomWidthZ -3);
			setDqmBlockKagaribidai(world, roomX +roomCenterX -4, roomY +roomFloorY +1, roomZ +3);
			setDqmBlockKagaribidai(world, roomX +roomCenterX -4, roomY +roomFloorY +1, roomZ +roomWidthZ -3);


			// 「ネザーブロック柱」の設置
			setDqmBlockHasiraQ(world, roomX +roomCenterX +6, roomY +roomFloorY +1, roomZ +4, roomHeight -1);
			setDqmBlockHasiraQ(world, roomX +roomCenterX +6, roomY +roomFloorY +1, roomZ +roomWidthZ -4, roomHeight -1);
			setDqmBlockHasiraQ(world, roomX +roomCenterX -6, roomY +roomFloorY +1, roomZ +4, roomHeight -1);
			setDqmBlockHasiraQ(world, roomX +roomCenterX -6, roomY +roomFloorY +1, roomZ +roomWidthZ -4, roomHeight -1);

			// 「ベビーパンサーの黄金像」の設置
			setDaizaAndKirapan(world, roomX +3, roomY +roomFloorY +1, roomZ +3, 1);
			setDaizaAndKirapan(world, roomX +3, roomY +roomFloorY +1, roomZ +roomWidthZ -3, 1);
			setDaizaAndKirapan(world, roomX +roomWidthX -3, roomY +roomFloorY +1, roomZ +3, 3);
			setDaizaAndKirapan(world, roomX +roomWidthX -3, roomY +roomFloorY +1, roomZ +roomWidthZ -3, 3);
			break;
		case 1:
		case 3:
			/* 床 */
			// 「ダメージ床」の設置
			for (int x = 1; x <= roomWidthX -1; x++) {
				for (int z = roomCenterZ -1; z <= roomCenterZ +1; z++) {
					world.setBlock(roomX +x, roomY +roomFloorY, roomZ +z, DQBlocks.DqmBlockToramanaYuka2);
				}
			}
			// 「金の装飾石」の設置
			for (int x = 1; x <= roomWidthX -1; x++) {
				world.setBlock(roomX +x, roomY +roomFloorY, roomZ +roomCenterZ -2, DQBlocks.DqmBlockKowareru6);
				world.setBlock(roomX +x, roomY +roomFloorY, roomZ +roomCenterZ +2, DQBlocks.DqmBlockKowareru6);
			}
			// 「溶岩」の設置
			for (int x = 1; x <= roomWidthX -1; x++) {
				world.setBlock(roomX +x, roomY +roomFloorY, roomZ +1, Blocks.lava);
				world.setBlock(roomX +x, roomY +roomFloorY, roomZ +roomWidthZ -1, Blocks.lava);
			}

			/* 他 */
			// 「大聖杯」と「炎」の設置
			setDqmBlockKagaribidai(world, roomX +3, roomY +roomFloorY +1, roomZ +roomCenterZ +4);
			setDqmBlockKagaribidai(world, roomX +3, roomY +roomFloorY +1, roomZ +roomCenterZ -4);
			setDqmBlockKagaribidai(world, roomX +roomWidthX -3, roomY +roomFloorY +1, roomZ +roomCenterZ +4);
			setDqmBlockKagaribidai(world, roomX +roomWidthX -3, roomY +roomFloorY +1, roomZ +roomCenterZ -4);

			// 「ネザーブロック柱」の設置
			setDqmBlockHasiraQ(world, roomX +4, roomY +roomFloorY +1, roomZ +roomCenterZ +6, roomHeight -1);
			setDqmBlockHasiraQ(world, roomX +4, roomY +roomFloorY +1, roomZ +roomCenterZ -6, roomHeight -1);
			setDqmBlockHasiraQ(world, roomX +roomWidthX -4, roomY +roomFloorY +1, roomZ +roomCenterZ +6, roomHeight -1);
			setDqmBlockHasiraQ(world, roomX +roomWidthX -4, roomY +roomFloorY +1, roomZ +roomCenterZ -6, roomHeight -1);

			// 「ベビーパンサーの黄金像」の設置
			setDaizaAndKirapan(world, roomX +3, roomY +roomFloorY +1, roomZ +3, 2);
			setDaizaAndKirapan(world, roomX +3, roomY +roomFloorY +1, roomZ +roomWidthZ -3, 0);
			setDaizaAndKirapan(world, roomX +roomWidthX -3, roomY +roomFloorY +1, roomZ +3, 2);
			setDaizaAndKirapan(world, roomX +roomWidthX -3, roomY +roomFloorY +1, roomZ +roomWidthZ -3, 0);
			break;
		}

		/* - - - - - - - - - -
		 * 以下、敵のスポーン
		 * - - - - - - - - - */
	}


	public static void setDqmBlockKagaribidai(World world, int x, int y, int z) {
		world.setBlock(x, y, z, DQDecorates.DqmBlockKagaribidai);
		world.setBlock(x, y +1, z, Blocks.fire);
	}

	public static void setDqmBlockHasiraQ(World world, int x, int y, int z, int height) {
		world.setBlock(x, y, z, DQDecorates.DqmBlockHasiraQ);
		world.setBlock(x, y +height, z, DQDecorates.DqmBlockHasiraueQ);

		for (int i = 1; i < height; i++) {
			world.setBlock(x, y +i, z, DQDecorates.DqmBlockHasiranakaQ);
		}
	}

	public static void setDaizaAndKirapan(World world, int x, int y, int z, int meta) {
		world.setBlock(x, y, z, DQDecorates.DqmBlockDaizaQ);
		world.setBlock(x, y +1, z, DQDecorates.DqmBlockSKirapan, meta, 2);
	}
}
