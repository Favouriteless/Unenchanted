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
import com.favouriteless.unenchanted.common.blocks.UnenchantingTableBlock;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Unenchanted.MOD_ID)
public class UnenchantedBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Unenchanted.MOD_ID);


    public static final RegistryObject<Block> UNENCHANTING_TABLE = BLOCKS.register("unenchanting_table", () -> new UnenchantingTableBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_RED).requiresCorrectToolForDrops().strength(5.0F, 1200.0F)));

}
