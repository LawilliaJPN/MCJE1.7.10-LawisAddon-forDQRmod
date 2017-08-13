package lawisAddonDqr1.api.event;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

@Cancelable
public class LadEnemySpawnEvent extends Event {
	public final World world;
	public final EntityPlayer player;
	public final EntityLiving enemy;
	public final int roomID;

	/*
	 * このModにより敵がスポーンされる直前にフック。
	 * lawisAddonDqr1.event.enemies.SpawnEnemyCore.javaのspawnEnemyメソッド内。
	 */
	public LadEnemySpawnEvent(World world, EntityPlayer player, EntityLiving enemy, int roomID) {
		super();
		this.world = world;
		// 「石ブロック」を破壊したプレイヤー
		this.player = player;
		// スポーンされる予定の敵
		this.enemy = enemy;
		// 戦闘部屋のID、lawisAddonDqr1.event.rooms.RoomID.javaを参照
		this.roomID = roomID;
	}
}
