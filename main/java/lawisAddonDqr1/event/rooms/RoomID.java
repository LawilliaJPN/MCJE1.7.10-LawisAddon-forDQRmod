package lawisAddonDqr1.event.rooms;

import lawisAddonDqr1.config.LadDebug;
import net.minecraft.world.World;

public class RoomID {
	/* Room ID */
	// 部屋の基本ID
	final public static int debugFalse = -1;
	final public static int roomGrassWell = 1100;
	final public static int roomGrassWellIsCursed = 1110;
	final public static int roomWeaponShop = 1200;
	final public static int roomDesertWell = 1300;
	final public static int roomIcePlains = 1400;
	final public static int roomForest = 1500;
	final public static int roomBeach = 1600;
	final public static int roomPyramid = 2100;
	final public static int roomSpecial01 = 4100;
	// スポーン用ID
	final public static int roomGrassWellIsCursedOnWater = 1120;
	final public static int roomWeaponShopCustomer = 1210;

	/* Difficulty of Room */
	// 戦闘部屋の難易度
	private static int difOfRoom = 0;

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
			return "roomGrassWell";
		case 1110:
		case 1120:
			return "roomGrassWellIsCursed";
		case 1200:
		case 1210:
			return "roomWeaponShop";
		case 1300:
			return "roomDesertWell";
		case 1400:
			return "roomIcePlains";
		case 1500:
			return "roomForest";
		case 1600:
			return "roomBeach";
		case 2100:
			return "roomPyramid";
		case 4100:
			return "roomSpecial01";
		}
		return "";
	}
}
