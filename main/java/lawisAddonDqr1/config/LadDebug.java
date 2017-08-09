package lawisAddonDqr1.config;

/*
 * デバッグ用に変数を固定したりするためのクラス。
 * 1箇所にまとめておかないと戻し忘れるため、追加。
 */
public class LadDebug {
	/* 戦闘部屋デバッグ用 */
	// 戦闘部屋固定（任意の負の整数で解除）
	private static int debugRoom = -1;
	// 戦闘確率100％（falseで解除）
	private static boolean debugCountRandomEncounter0 = false;

	/* getter */
	public static int getDebugRoom() {
		return debugRoom;
	}

	public static boolean isDebugCountRandomEncounter0() {
		return debugCountRandomEncounter0;
	}
}
