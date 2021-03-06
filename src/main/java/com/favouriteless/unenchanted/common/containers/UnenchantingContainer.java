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

package com.favouriteless.unenchanted.common.containers;

import com.favouriteless.unenchanted.core.config.UnenchantedConfig;
import com.favouriteless.unenchanted.core.init.UnenchantedBlocks;
import com.favouriteless.unenchanted.core.init.UnenchantedContainers;
import com.google.common.collect.Maps;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.IntReferenceHolder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class UnenchantingContainer extends Container {

    public static final Random RANDOM = new Random();
    private final IInventory inputSlots = new Inventory(2) {
        @Override
        public void setChanged() {
            super.setChanged();
            UnenchantingContainer.this.slotsChanged(this);
        }
    };
    private final IInventory resultSlots = new CraftResultInventory();
    private final IWorldPosCallable access;
    private final IntReferenceHolder cost = IntReferenceHolder.standalone();

    public UnenchantingContainer(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data) {
        this(windowId, playerInventory);
    }

    public UnenchantingContainer(int windowId, PlayerInventory playerInventory) {
        this(windowId, playerInventory, IWorldPosCallable.NULL);
    }

    public UnenchantingContainer(int windowId, PlayerInventory playerInventory, IWorldPosCallable access) {
        super(UnenchantedContainers.UNENCHANTING_TABLE.get(), windowId);
        this.access = access;

        this.addSlot(new SlotEnchantInput(this.inputSlots, 0, 27, 47));
        this.addSlot(new SlotBookInput(this.inputSlots, 1, 76, 47));
        this.addSlot(new SlotOutput(this.resultSlots, 0, 134, 47));

        this.AddInventorySlots(playerInventory);

        this.addDataSlot(cost);

    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return stillValid(this.access, player, UnenchantedBlocks.UNENCHANTING_TABLE.get());
    }

    @Override
    public void removed(PlayerEntity playerEntity) {
        super.removed(playerEntity);
        this.access.execute((p_217004_2_, p_217004_3_) -> {
            this.clearContainer(playerEntity, playerEntity.level, this.inputSlots);
        });
    }

    public void slotsChanged(IInventory inventory) {
        super.slotsChanged(inventory);
        if(inventory != this.resultSlots) {
            this.createResult();
        }
    }

    protected void AddInventorySlots(PlayerInventory playerInventory) {
        for (int y = 0; y < 3; y++) { // Main Inventory
            for (int x = 0; x < 9; x++) {
                this.addSlot(new Slot(playerInventory, x + (y * 9) + 9,  8 + (x * 18), 84 + (y * 18)));
            }
        }
        for (int x = 0; x < 9; x++) { // Hotbar
            this.addSlot(new Slot(playerInventory, x, 8 + (18 * x), 142));
        }
    }

    protected boolean mayPickup(PlayerEntity playerEntity, boolean hasItem) {
        return (playerEntity.abilities.instabuild || playerEntity.experienceLevel >= this.cost.get() );
    }

    protected ItemStack onTake(PlayerEntity playerEntity, ItemStack itemStack) {
        if (!playerEntity.abilities.instabuild) {
            playerEntity.giveExperienceLevels(-this.cost.get());
        }

        ItemStack inputItem = this.inputSlots.getItem(0);

        Map<Enchantment, Integer> inputEnchantMap = EnchantmentHelper.getEnchantments(inputItem);
        Map<Enchantment, Integer> resultEnchantMap = EnchantmentHelper.getEnchantments(itemStack);
        for(Enchantment enchantment : resultEnchantMap.keySet()) {
            inputEnchantMap.remove(enchantment);
        }
        EnchantmentHelper.setEnchantments(inputEnchantMap, inputItem);

        inputItem.setDamageValue(inputItem.getDamageValue() + inputItem.getMaxDamage() * UnenchantedConfig.itemDurabilityPenalty.get() / 100);
        if(inputItem.getDamageValue() >= inputItem.getMaxDamage() - 1) {
            inputItem.shrink(1);
        } else {
            this.inputSlots.setChanged();
        }
        this.inputSlots.removeItem(1, 1);

        this.cost.set(0);
        this.access.execute((world, pos) -> {
            world.levelEvent(1030, pos, 0);
        });

        return itemStack;
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity playerEntity, int index) {
        ItemStack slotItemCopy;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem()) {

            ItemStack slotItem = slot.getItem();
            slotItemCopy = slotItem.copy();

            if(index <= 1) { // If slot is input
                if (!this.moveItemStackTo(slotItem, 3, 39, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (index == 2) { // If slot is output
                if (!this.moveItemStackTo(slotItem, 3, 39, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onTake(playerEntity, slotItemCopy);
            } else if (index < 39) { // If slot is from player inventory
                if(this.slots.get(0).mayPlace(slotItem)) {
                    if (!this.moveItemStackTo(slotItem, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (this.slots.get(1).mayPlace(slotItem)) {
                    if (!this.moveItemStackTo(slotItem, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            }

            if (slotItem.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (slotItem.getCount() == slotItemCopy.getCount()) {
                return ItemStack.EMPTY;
            }
        }
        return ItemStack.EMPTY;
    }

    public void createResult() {
        ItemStack inputItem = this.inputSlots.getItem(0);
        ItemStack inputBook = this.inputSlots.getItem(1);

        if(inputItem.isEmpty() || inputBook.isEmpty()) {
            this.resultSlots.setItem(0, ItemStack.EMPTY);
            this.cost.set(0);
            return;
        }

        ItemStack resultItem = new ItemStack(Items.ENCHANTED_BOOK);
        Map<Enchantment, Integer> enchantmentMap = EnchantmentHelper.getEnchantments(inputItem);
        Map<Enchantment, Integer> resultEnchantmentMap = Maps.newLinkedHashMap();
        List<Enchantment> keyList = new ArrayList<>(enchantmentMap.keySet());

        switch(UnenchantedConfig.unenchantMode.get()) {
            case 0:
                resultEnchantmentMap.put(keyList.get(0), enchantmentMap.get(keyList.get(0)));
                break;

            case 1:
                Enchantment enchantment = keyList.get(RANDOM.nextInt(enchantmentMap.size()));
                resultEnchantmentMap.put(enchantment, enchantmentMap.get(enchantment));
                break;

            case 2:
                resultEnchantmentMap.putAll(enchantmentMap);
                break;
        }
        EnchantmentHelper.setEnchantments(resultEnchantmentMap, resultItem);

        int levelCost = 0;
        for (Enchantment enchantment : resultEnchantmentMap.keySet()) {
            levelCost += (UnenchantedConfig.costPerEnchantment.get() + (resultEnchantmentMap.get(enchantment) * UnenchantedConfig.costPerEnchantmentLevel.get()));
        }

        this.resultSlots.setItem(0, resultItem);
        this.cost.set(levelCost);
    }

    public int getCost() {
        return this.cost.get();
    }

    public class SlotEnchantInput extends Slot {

        public SlotEnchantInput(IInventory inventoryIn, int index, int xPosition, int yPosition) {
            super(inventoryIn, index, xPosition, yPosition);
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            return (stack.getItem() != Items.BOOK) && stack.isEnchanted();
        }
    }

    public class SlotBookInput extends Slot {
        public SlotBookInput(IInventory inventoryIn, int index, int xPosition, int yPosition) {
            super(inventoryIn, index, xPosition, yPosition);
        }

        // if this function returns false, the player won't be able to insert the given item into this slot
        @Override
        public boolean mayPlace(ItemStack stack) {
            return (stack.getItem() == Items.BOOK) && !stack.isEnchanted();
        }
    }

    public class SlotOutput extends Slot {
        public SlotOutput(IInventory inventoryIn, int index, int xPosition, int yPosition) {
            super(inventoryIn, index, xPosition, yPosition);
        }

        @Override
        public boolean mayPlace(ItemStack itemStack) {
            return false;
        }

        @Override
        public boolean mayPickup(PlayerEntity playerEntity) {
            return UnenchantingContainer.this.mayPickup(playerEntity, this.hasItem());
        }

        @Override
        public ItemStack onTake(PlayerEntity playerEntity, ItemStack itemStack) {
            return UnenchantingContainer.this.onTake(playerEntity, itemStack);
        }
    }
}
