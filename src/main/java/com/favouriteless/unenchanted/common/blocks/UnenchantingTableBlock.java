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

package com.favouriteless.unenchanted.common.blocks;

import com.favouriteless.unenchanted.common.containers.UnenchantingContainer;
import com.favouriteless.unenchanted.common.tileentities.UnenchantingTableTileEntity;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.INameable;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class UnenchantingTableBlock extends ContainerBlock {
    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D);

    public UnenchantingTableBlock(AbstractBlock.Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTraceResult) {
        if (world.isClientSide()) {
            return ActionResultType.SUCCESS;
        } else {
            TileEntity tileEntity = world.getBlockEntity(pos);
            if (tileEntity instanceof UnenchantingTableTileEntity) {
                NetworkHooks.openGui((ServerPlayerEntity) player, state.getMenuProvider(world, pos), tileEntity.getBlockPos());
            }
            return ActionResultType.CONSUME;
        }
    }

    @Nullable
    @Override
    public INamedContainerProvider getMenuProvider(BlockState state, World world, BlockPos pos) {
        TileEntity tileentity = world.getBlockEntity(pos);
        if (tileentity instanceof UnenchantingTableTileEntity) {
            ITextComponent itextcomponent = ((INameable)tileentity).getDisplayName();
            return new SimpleNamedContainerProvider((windowId, playerInventory, p_220147_4_) -> new UnenchantingContainer(windowId, playerInventory, IWorldPosCallable.create(world, pos)), itextcomponent);
        } else {
            return null;
        }
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader blockReader, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public BlockRenderType getRenderShape(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public TileEntity newBlockEntity(IBlockReader p_196283_1_) {
        return new UnenchantingTableTileEntity();
    }
}
