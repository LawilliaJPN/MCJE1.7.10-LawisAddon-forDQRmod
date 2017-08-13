package lawisAddonDqr1.event.rooms;

import lawisAddonDqr1.config.LadDebug;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class LadRooms {
	/* Room ID */
	// 部屋の基本ID
	public static final int DEBUG_IS_FALSE = -1;
	public static final int VILLAGE_WELL = 1100;
	public static final int VILLAGE_WELL_HAS_CURSED = 1110;
	public static final int WEAPON_SHOP = 1200;
	public static final int DESERT_WELL = 1300;
	public static final int ICE_PLAINS = 1400;
	public static final int FOREST = 1500;
	public static final int BEACH = 1600;
	public static final int PYRAMID = 2100;
	public static final int SPECIAL_01 = 4100;
	// スポーン用ID
	public static final int VILLAGE_WELL_HAS_CURSED_ON_WATER = 1120;
	public static final int WEAPON_SHOP_CUSTOMER = 1210;

	/* Difficulty of Room */
	// 戦闘部屋の難易度
	private static int difOfRoom = 0;

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
	 * 経過日数から、部屋の難易度を決定するメソッド
	 * コンフィグ：ベッドペナルティがオンの時に、目覚めた時に生成する部屋の難易度
	 */
	public static void updateDifOfRoom(World world) {
		int d = 0;
		int time = (int) (world.getTotalWorldTime() /24000);

		// [Debug]戦闘部屋の難易度を固定する処理（デバッグ用）
		if (LadDebug.getDebugDifOfRoom() >= 0) d = LadDebug.getDebugDifOfRoom();

		setDifOfRoom(d);
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

		// [Debug]戦闘部屋の難易度を固定する処理（デバッグ用）
		if (LadDebug.getDebugDifOfRoom() >= 0) d = LadDebug.getDebugDifOfRoom();

		setDifOfRoom(d);
	}

	/*
	 * 変数 difOfRoom の getter
	 */
	public static int getDifOfRoom() {
		return difOfRoom;
	}

	/*
	 * 変数 difOfRoom の setter
	 */
	public static void setDifOfRoom(int d) {
		if (d <= 1) d = 1;
		if (d >= 7) d = 7;

		difOfRoom = d;
	}

	/*
	 * int型 ⇒ 戦闘部屋の日本語名
	 */
	public static String getNameRoom(int roomID) {
		switch (roomID) {
		case 1100:
			return "村の井戸";
		case 1110:
		case 1120:
			return "呪われた井戸";
		case 1200:
		case 1210:
			return "武器屋";
		case 1300:
			return "砂漠の井戸";
		case 1400:
			return "氷原";
		case 1500:
			return "森林";
		case 1600:
			return "砂浜";
		case 2100:
			return "ピラミッド";
		case 4100:
			return "特殊な部屋";
		}
		return "";
	}

	/*
	 * int型 ⇒ RoomID 変数名（部屋の基本ID）
	 */
	public static String getNameRoomID(int roomID) {
		switch (roomID) {
		case 1100:
			return "VILLAGE_WELL";
		case 1110:
		case 1120:
			return "VILLAGE_WELL_HAS_CURSED";
		case 1200:
		case 1210:
			return "WEAPON_SHOP";
		case 1300:
			return "DESERT_WELL";
		case 1400:
			return "ICE_PLAINS";
		case 1500:
			return "FOREST";
		case 1600:
			return "BEACH";
		case 2100:
			return "PYRAMID";
		case 4100:
			return "SPECIAL_01";
		}
		return "";
	}
}
