/*
 * Copyright (c) 2021. Favouriteless
 * GNU GPLv3 License
 *
 *     This file is part of Unenchanted.
 *
 *     Unenchanted is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Unenchanted is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Unenchanted.  If not, see <https://www.gnu.org/licenses/>.
 */

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
