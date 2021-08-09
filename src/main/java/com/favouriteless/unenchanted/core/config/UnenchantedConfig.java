/*
 * Copyright (c) 2021. Favouriteless
 * Unenchanted, a minecraft mod.
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

package com.favouriteless.unenchanted.core.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class UnenchantedConfig {

    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Integer> costPerEnchantment;
    public static final ForgeConfigSpec.ConfigValue<Integer> costPerEnchantmentLevel;
    public static final ForgeConfigSpec.ConfigValue<Boolean> destroyItem;

    static {
        costPerEnchantment = BUILDER.comment("XP Cost per enchantment (in levels)").define("cost_per_enchantment", 4);
        costPerEnchantmentLevel = BUILDER.comment("XP Cost per enchantment level (in levels)").define("cost_per_enchantment_level", 1);
        destroyItem = BUILDER.comment("Destroy item upon disenchanting").define("destroy_item", true);

        SPEC = BUILDER.build();
    }

}
