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

package com.favouriteless.unenchanted;

import com.favouriteless.unenchanted.core.init.UnenchantedBlocks;
import com.favouriteless.unenchanted.core.init.UnenchantedContainers;
import com.favouriteless.unenchanted.core.init.UnenchantedItems;
import com.favouriteless.unenchanted.core.init.UnenchantedTileEntities;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;;

@Mod(Unenchanted.MOD_ID)
public class Unenchanted
{
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "unenchanted";
    public static Unenchanted instance;

    public Unenchanted() {
        registerAll();

        instance = this;
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void registerAll() {

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        UnenchantedBlocks.BLOCKS.register(modEventBus);
        UnenchantedItems.ITEMS.register(modEventBus);
        UnenchantedTileEntities.TILE_ENTITY_TYPES.register(modEventBus);
        UnenchantedContainers.CONTAINER_TYPES.register(modEventBus);
    }

}
