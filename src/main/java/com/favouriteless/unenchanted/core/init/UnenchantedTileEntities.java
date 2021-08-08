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

package com.favouriteless.unenchanted.core.init;

import com.favouriteless.unenchanted.Unenchanted;
import com.favouriteless.unenchanted.common.tileentities.UnenchantingTableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class UnenchantedTileEntities {

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Unenchanted.MOD_ID);

    public static final RegistryObject<TileEntityType<UnenchantingTableTileEntity>> UNENCHANTING_TABLE = TILE_ENTITY_TYPES.register("unenchanting_table",
            () -> TileEntityType.Builder.of(UnenchantingTableTileEntity::new, UnenchantedBlocks.UNENCHANTING_TABLE.get()).build(null));

}
