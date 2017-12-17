package lawisAddonDqr1.api.event;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/*
 * 他のmodがこのmodに介入しやすくするために用意したイベントのうちの1つ。
 */
@Cancelable
public class LadSetBattleRoomsEvent extends Event {
	public final World world;
	public final EntityPlayer player;

	/*
	 * 戦闘部屋の生成関連のイベント。
	 * lawisAddonDqr1.event.LadEventHandler.javaのsetBattleRoomsメソッド内。
	 */
	public LadSetBattleRoomsEvent(World world, EntityPlayer player) {
		super();
		this.world = world;
		// 「石ブロック」を破壊したプレイヤー
		this.player = player;
	}

	/*
	 * 戦闘部屋の生成前にフック。
	 */
	public static class PreSetRoomEvent extends LadSetBattleRoomsEvent {
		public PreSetRoomEvent(World world, EntityPlayer player) {
			super(world, player);
		}
	}

	/*
	 * 戦闘部屋の生成後にフック。
	 */
	public static class PostSetRoomEvent extends LadSetBattleRoomsEvent {
		public PostSetRoomEvent(World world, EntityPlayer player) {
			super(world, player);
		}
	}
}