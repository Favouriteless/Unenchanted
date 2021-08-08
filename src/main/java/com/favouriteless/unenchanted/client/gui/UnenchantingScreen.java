package com.favouriteless.unenchanted.client.gui;

import com.favouriteless.unenchanted.Unenchanted;
import com.favouriteless.unenchanted.common.containers.UnenchantingContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class UnenchantingScreen extends ContainerScreen<UnenchantingContainer> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Unenchanted.MOD_ID, "textures/gui/unenchanting_table.png");

    public UnenchantingScreen(UnenchantingContainer container, PlayerInventory playerInventory, ITextComponent textComponent) {
        super(container, playerInventory, textComponent);
        this.titleLabelX = 58;
        this.titleLabelY = 22;
    }

    @Override
    public void render(MatrixStack matrixStack, int partialTicks, int x, float y) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, partialTicks, x, y);
        RenderSystem.disableBlend();
        this.renderTooltip(matrixStack, partialTicks, x);
    }

    @Override
    protected void renderBg(MatrixStack matrixStack, float partialTicks, int x, int y) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bind(TEXTURE);

        int edgeSpacingX = (this.width - this.imageWidth) / 2;
        int edgeSpacingY = (this.height - this.imageHeight) / 2;
        this.blit(matrixStack, edgeSpacingX, edgeSpacingY, 0, 0, this.imageWidth, this.imageHeight);
        if ((this.menu.getSlot(0).hasItem() || this.menu.getSlot(1).hasItem()) && !this.menu.getSlot(2).hasItem()) {
            this.blit(matrixStack, edgeSpacingX + 99, edgeSpacingY + 45, this.imageWidth, 0, 28, 21);
        }
    }

    @Override
    protected void renderLabels(MatrixStack matrixStack, int mouseX, int mouseY) {
        RenderSystem.disableBlend();
        super.renderLabels(matrixStack, mouseX, mouseY);
        int cost = this.menu.getCost();
        if (cost > 0) {
            int j = 8453920;
            ITextComponent textComponent;

            if (!this.menu.getSlot(2).hasItem()) {
                textComponent = null;
            } else {
                textComponent = new TranslationTextComponent("container.unenchant.cost", cost);
                if (!this.menu.getSlot(2).mayPickup(this.inventory.player)) {
                    j = 16736352;
                }
            }

            if (textComponent != null) {
                int k = this.imageWidth - 8 - this.font.width(textComponent) - 2;
                int l = 69;
                fill(matrixStack, k - 2, 67, this.imageWidth - 8, 79, 1325400064);
                this.font.drawShadow(matrixStack, textComponent, (float)k, 69.0F, j);
            }
        }

    }
}