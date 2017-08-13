package lawisAddonDqr1.api.event;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

@Cancelable
public class LadSetBattleRoomEvent extends Event {
	public final World world;
	public final EntityPlayer player;

	/*
	 * 戦闘部屋の生成関連のイベント。
	 * lawisAddonDqr1.event.LadEventHundler.javaのMiningPenaltyメソッド内。
	 *
	 * [Unimplemented] 引数は最低限のみ実装、今後必要に応じて追加予定。
	 */
	public LadSetBattleRoomEvent(World world, EntityPlayer player) {
		super();
		this.world = world;
		// 「石ブロック」を破壊したプレイヤー
		this.player = player;
	}

	/*
	 * 戦闘部屋の生成直前にフック。
	 */
	public static class PreSetRoomEvent extends LadSetBattleRoomEvent {
		public PreSetRoomEvent(World world, EntityPlayer player) {
			super(world, player);
		}
	}

	/*
	 * 戦闘部屋の生成直後にフック。
	 */
	public static class PostSetRoomEvent extends LadSetBattleRoomEvent {
		public PostSetRoomEvent(World world, EntityPlayer player) {
			super(world, player);
		}
	}
}
