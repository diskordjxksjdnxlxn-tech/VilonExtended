package client;

import client.gui.DarkFantasyGui;
import client.modules.misc.MockModule;
import client.manager.AltManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("vilonextended")
public class VilonExtended {
    public static DarkFantasyGui gui;
    public static MockModule mock;
    public static AltManager altManager;

    public VilonExtended() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        mock = new MockModule();
        MinecraftForge.EVENT_BUS.register(mock);
        altManager = new AltManager();
    }

    @SubscribeEvent
    public void onClientSetup(FMLClientSetupEvent event) {
        gui = new DarkFantasyGui();
    }
}
