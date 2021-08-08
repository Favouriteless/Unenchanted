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

package com.favouriteless.unenchanted.common.tileentities;

import com.favouriteless.unenchanted.core.init.UnenchantedTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.INameable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nullable;

public class UnenchantingTableTileEntity extends TileEntity implements INameable {
    private ITextComponent name;

    public UnenchantingTableTileEntity(TileEntityType<?> typeIn) {
        super(typeIn);
    }

    public UnenchantingTableTileEntity() {
        this(UnenchantedTileEntities.UNENCHANTING_TABLE.get());
    }

    @Override
    public ITextComponent getName() {
        return (ITextComponent)(this.name != null ? this.name : new TranslationTextComponent("unenchanted.container.unenchanting"));
    }

    public void setCustomName(@Nullable ITextComponent textComponent) {
        this.name = textComponent;
    }

    @Nullable
    public ITextComponent getCustomName() {
        return this.name;
    }

    @Override
    public CompoundNBT save(CompoundNBT nbt) {
        super.save(nbt);
        if (this.hasCustomName()) {
            nbt.putString("CustomName", ITextComponent.Serializer.toJson(this.name));
        }

        return nbt;
    }

    @Override
    public void load(BlockState state, CompoundNBT nbt) {
        super.load(state, nbt);
        if (nbt.contains("CustomName", 8)) {
            this.name = ITextComponent.Serializer.fromJson(nbt.getString("CustomName"));
        }

    }
}
