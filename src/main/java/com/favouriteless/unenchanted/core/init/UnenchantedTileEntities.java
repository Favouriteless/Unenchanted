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
