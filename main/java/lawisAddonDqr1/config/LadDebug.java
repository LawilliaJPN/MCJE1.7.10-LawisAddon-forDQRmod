package lawisAddonDqr1.config;

import lawisAddonDqr1.event.rooms.LadRoomID;

/*
 * デバッグ用に変数を固定したりするためのクラス。
 * 1箇所にまとめておかないと戻し忘れるため、追加。
 */
public class LadDebug {
	/* 戦闘部屋デバッグ用 */
	// 戦闘部屋の種類固定（任意の負の整数で、解除）
	public static final int DEBUG_ROOM = LadRoomID.DEBUG_IS_FALSE;
	// 戦闘部屋の難易度固定（任意の負の整数で、解除）
	public static final int DEBUG_DIF_OF_ROOM = -1;
	// 戦闘確率100％（falseで解除）
	public static final boolean DEBUG_COUNT_RANDOMEN_COUNTER_0 = false;

	/* getter */
	public static int getDebugRoom() {
		return DEBUG_ROOM;
	}

	public static int getDebugDifOfRoom() {
		return DEBUG_DIF_OF_ROOM;
	}

	public static boolean isDebugCountRandomEncounter0() {
		return DEBUG_COUNT_RANDOMEN_COUNTER_0;
	}
}
