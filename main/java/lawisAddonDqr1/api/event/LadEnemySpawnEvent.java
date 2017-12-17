package lawisAddonDqr1.api.event;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/*
 * 他のmodがこのmodに介入しやすくするために用意したイベントのうちの1つ。
 */
@Cancelable
public class LadEnemySpawnEvent extends Event {
	public final World world;
	public final EntityPlayer player;
	public final EntityLiving enemy;
	public final int enemyGroup;

	/*
	 * このModにより敵がスポーンされる直前にフック。
	 * lawisAddonDqr1.event.enemies.SpawnEnemyCore.javaのspawnEnemyメソッド内。
	 */
	public LadEnemySpawnEvent(World world, EntityPlayer player, EntityLiving enemy, int enemyGroup) {
		super();
		this.world = world;
		// 「石ブロック」を破壊したプレイヤー
		this.player = player;
		// スポーンされる予定の敵
		this.enemy = enemy;


		// スポーンさせる敵を決めるための敵グループを表す4桁の数字
		this.enemyGroup = enemyGroup;
		/*
		 * 1～2桁「戦闘部屋の分類」、3桁目「同分類部屋のパターン判別」、4桁目「戦闘部屋の難易度」
		 *
		 * 1～3桁目の詳細は、lawisAddonDqr1.event.rooms.LadRoomCore.javaの変数を参照。
		 * 4桁目の詳細は、同クラスのupdateDifOfRoomメソッド（複数あり）を参照。
		 */

	}
}