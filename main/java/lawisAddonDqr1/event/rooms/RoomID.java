package lawisAddonDqr1.event.rooms;

import lawisAddonDqr1.config.LadDebug;

public class RoomID {
	/* Room ID */
	final public static int debugFalse = -1;
	final public static int roomGrassWell = 1100;
	final public static int roomGrassWellIsCursed = 1110;
	final public static int roomWeaponShop = 1200;
	final public static int roomDesertWell = 1300;
	final public static int roomIcePlains = 1400;
	final public static int roomForest = 1500;
	final public static int roomBeach = 1600;
	final public static int roomSpecial01 = 4100;

	/* Difficulty of Room */
	// 戦闘部屋の難易度
	private static int difOfRoom = 0;

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

		difOfRoom = d;
	}

	/*
	 * 変数 difOfRoom の getter
	 */
	public static int getDifOfRoom() {
		return difOfRoom;
	}
}
