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

    public static final ForgeConfigSpec.ConfigValue<Integer> unenchantMode;
    public static final ForgeConfigSpec.ConfigValue<Integer> costPerEnchantment;
    public static final ForgeConfigSpec.ConfigValue<Integer> costPerEnchantmentLevel;
    public static final ForgeConfigSpec.ConfigValue<Integer> itemDurabilityPenalty;

    static {
        BUILDER.push("Unenchanted Configuration");
        unenchantMode = BUILDER.comment("Unenchanting mode: 0 = first enchantment only, 1 = random enchantment only, 2 = all enchantments #default: 0").define("unenchanting_mode", 0);
        costPerEnchantment = BUILDER.comment("XP Cost per enchantment (in levels) #default: 1").define("cost_per_enchantment", 1);
        costPerEnchantmentLevel = BUILDER.comment("XP Cost per enchantment level (in levels) #default: 2").define("cost_per_enchantment_level", 2);
        itemDurabilityPenalty = BUILDER.comment("Percentage durability lost upon a disenchantment (0-100) #default: 50").define("item_durability_penalty", 50);
        BUILDER.pop();

        SPEC = BUILDER.build();
    }

}
