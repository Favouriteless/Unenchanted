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
