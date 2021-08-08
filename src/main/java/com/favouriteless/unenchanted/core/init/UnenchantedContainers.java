package com.favouriteless.unenchanted.core.init;

import com.favouriteless.unenchanted.Unenchanted;
import com.favouriteless.unenchanted.common.containers.UnenchantingContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class UnenchantedContainers {

    public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = DeferredRegister.create(ForgeRegistries.CONTAINERS, Unenchanted.MOD_ID);

    public static final RegistryObject<ContainerType<UnenchantingContainer>> UNENCHANTING_TABLE = CONTAINER_TYPES.register("unenchanting_table",
            () -> IForgeContainerType.create(UnenchantingContainer::new));


}