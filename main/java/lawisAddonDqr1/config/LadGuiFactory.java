package lawisAddonDqr1.config;

import java.util.Set;

import cpw.mods.fml.client.IModGuiFactory;
import cpw.mods.fml.client.config.GuiConfig;
import lawisAddonDqr1.LawisAddonDQR01;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;

/*
 * ゲーム内でコンフィグを変更するためのGUI。
 *
 * 「TNT MODDERS」様の「MOD製作チュートリアル」を参考にさせていただきました。
 */
public class LadGuiFactory implements IModGuiFactory {
	@Override
	public void initialize(Minecraft minecraftInstance) {
	}

	@Override
	public Class<? extends GuiScreen> mainConfigGuiClass() {
		return LadConfigGui.class;
	}

	@Override
	public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
		return null;
	}

	@Override
	public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element) {
		return null;
	}

	public static class LadConfigGui extends GuiConfig {
		public LadConfigGui(GuiScreen parent) {
			super(parent, (new ConfigElement<Object>(LadConfigCore.cfg.getCategory(LadConfigCore.GENERAL))).getChildElements(), LawisAddonDQR01.MOD_ID, false, false, LawisAddonDQR01.MOD_NAME);
		}
	}
}