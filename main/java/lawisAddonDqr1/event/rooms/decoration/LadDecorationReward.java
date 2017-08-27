package lawisAddonDqr1.event.rooms.decoration;

import java.util.Random;

import dqr.DQR;
import lawisAddonDqr1.config.LadDebug;
import lawisAddonDqr1.event.rooms.LadRoomID;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;

/*
 * 戦闘部屋を生成する時に利用するメソッドを集めたクラスのうちの1つ。
 *
 * 戦闘報酬の設置。
 * DQRのコードをスポブロ部屋生成処理を参考にさせてもらいました。
 */
public class LadDecorationReward {
	/*
	 * 各部屋の報酬チェストを設置するためのメソッド
	 */
	public static void setChest(World world, int x, int y, int z) {
		Random rand = new Random();

		// [Debug] 戦闘部屋固定時に、チェストが100%生成される（デバッグ用）
		if (!(LadDebug.getDebugRoom() >=0)) {
			// 100分の「部屋の難易度」の確率でチェストが生成される（他の場合は生成中止）
			if (!(rand.nextInt(100) <= LadRoomID.getDifOfRoom())) return;
		}

		// チェストの設置
		world.setBlock(x, y, z, Blocks.chest);
		world.setBlockToAir(x, y +1, z);

		// チェストの下が空気なら石ブロックを設置
		if (world.getBlock(x, y -1, z).isAir(world, x, y -1, z)) {
			world.setBlock(x, y -1, z, Blocks.stone);
		}

		// チェストの中身
    	if(world.getTileEntity(x, y, z) instanceof TileEntityChest) {
            TileEntityChest tileentitychest = (TileEntityChest)world.getTileEntity(x, y, z);

            if (tileentitychest != null) {
            	// 上層
            	if (LadRoomID.getDifOfRoom() <= 3) {
            		// 1%の確率で、夜スポブロ級のアイテム
            		if (rand.nextInt(100) == 0) {
                		DQR.randomItem.generateChestContentsRank2(rand, tileentitychest);
                	} else {
                		DQR.randomItem.generateChestContentsRank1(rand, tileentitychest);
                	}

            	// 中層
            	} else if (LadRoomID.getDifOfRoom() <= 5) {
            		// 10%の確率で、夜スポブロ級のアイテム
            		if (rand.nextInt(10) == 0) {
                		DQR.randomItem.generateChestContentsRank2(rand, tileentitychest);
                	} else {
                		DQR.randomItem.generateChestContentsRank1(rand, tileentitychest);
                	}
            	// 下層
            	} else {
            		// 50%の確率で、夜スポブロ級のアイテム
            		if (rand.nextInt(2) == 0) {
                		DQR.randomItem.generateChestContentsRank2(rand, tileentitychest);
                	} else {
                		DQR.randomItem.generateChestContentsRank1(rand, tileentitychest);
                	}
            	}
            }
    	}
	}

	/*
	 * チェストを設置するためのメソッド（ピラミッド等用）。
	 * 4分の1の確率で空になる。
	 */
	public static void setChestP(World world, int x, int y, int z) {
		Random rand = new Random();
		world.setBlock(x, y, z, Blocks.chest);

		if (rand.nextInt(4) == 0) {
			if (world.getTileEntity(x, y, z) instanceof TileEntityChest) {
	            TileEntityChest tileentitychest = (TileEntityChest)world.getTileEntity(x, y, z);

	            if (tileentitychest != null) {
	            	// 上層
	            	if (LadRoomID.getDifOfRoom() <= 3) {
	            		// 1%の確率で、夜スポブロ級のアイテム
	            		if (rand.nextInt(100) == 0) {
	                		DQR.randomItem.generateChestContentsRank2(rand, tileentitychest);
	                	} else {
	                		DQR.randomItem.generateChestContentsRank1(rand, tileentitychest);
	                	}

	            	// 中層
	            	} else if (LadRoomID.getDifOfRoom() <= 5) {
	            		// 10%の確率で、夜スポブロ級のアイテム
	            		if (rand.nextInt(10) == 0) {
	                		DQR.randomItem.generateChestContentsRank2(rand, tileentitychest);
	                	} else {
	                		DQR.randomItem.generateChestContentsRank1(rand, tileentitychest);
	                	}
	            	// 下層
	            	} else {
	            		// 50%の確率で、夜スポブロ級のアイテム
	            		if (rand.nextInt(2) == 0) {
	                		DQR.randomItem.generateChestContentsRank2(rand, tileentitychest);
	                	} else {
	                		DQR.randomItem.generateChestContentsRank1(rand, tileentitychest);
	                	}
	            	}
	            }
	    	}
		}
	}
}
