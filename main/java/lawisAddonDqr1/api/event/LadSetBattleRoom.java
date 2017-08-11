package lawisAddonDqr1.api.event;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

@Cancelable
public class LadSetBattleRoom extends Event {
	public final World world;
	public final EntityPlayer player;

	/*
	 * 戦闘部屋の生成関連のイベント。
	 * lawisAddonDqr1.event.BreakEventHundler.javaのMiningPenaltyメソッド内。
	 *
	 * [Unimplemented] 引数は最低限のみ実装、今後必要に応じて追加予定。
	 */
	public LadSetBattleRoom(World world, EntityPlayer player) {
		super();
		this.world = world;
		// 「石ブロック」を破壊したプレイヤー
		this.player = player;
	}

	/*
	 * 戦闘部屋の生成直前にフック。
	 */
	public static class PreSetRoom extends LadSetBattleRoom {
		public PreSetRoom(World world, EntityPlayer player) {
			super(world, player);
		}
	}

	/*
	 * 戦闘部屋の生成直後にフック。
	 */
	public static class PostSetRoom extends LadSetBattleRoom {
		public PostSetRoom(World world, EntityPlayer player) {
			super(world, player);
		}
	}
}
