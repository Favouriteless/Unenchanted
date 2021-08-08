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
