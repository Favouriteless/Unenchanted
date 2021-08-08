package com.favouriteless.unenchanted.core.init;

import com.favouriteless.unenchanted.Unenchanted;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class UnenchantedItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Unenchanted.MOD_ID);

    public static RegistryObject<Item> UNENCHANTING_TABLE = ITEMS.register("unenchanting_table", () -> new BlockItem(UnenchantedBlocks.UNENCHANTING_TABLE.get(), new Item.Properties().tab(ItemGroup.TAB_DECORATIONS)));

}
