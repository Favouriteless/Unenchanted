package com.favouriteless.unenchanted.core.events;

import com.favouriteless.unenchanted.Unenchanted;
import com.favouriteless.unenchanted.client.gui.UnenchantingScreen;
import com.favouriteless.unenchanted.core.init.UnenchantedContainers;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid=Unenchanted.MOD_ID, bus= Mod.EventBusSubscriber.Bus.MOD)
public class SetupEvents {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        ScreenManager.register(UnenchantedContainers.UNENCHANTING_TABLE.get(), UnenchantingScreen::new);
    }

}
