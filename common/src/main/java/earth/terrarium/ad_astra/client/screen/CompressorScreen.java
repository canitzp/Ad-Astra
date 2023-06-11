package earth.terrarium.ad_astra.client.screen;

import earth.terrarium.ad_astra.AdAstra;
import earth.terrarium.ad_astra.common.block.machine.entity.CompressorBlockEntity;
import earth.terrarium.ad_astra.common.screen.menu.CompressorMenu;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import java.awt.*;

@Environment(EnvType.CLIENT)
public class CompressorScreen extends AbstractMachineScreen<CompressorBlockEntity, CompressorMenu> {

    public static final int ENERGY_LEFT = 147;
    public static final int ENERGY_TOP = 30;
    public static final int HAMMER_LEFT = 78;
    public static final int HAMMER_TOP = 57;
    private static final ResourceLocation TEXTURE = new ResourceLocation(AdAstra.MOD_ID, "textures/gui/screens/compressor.png");

    public CompressorScreen(CompressorMenu handler, Inventory inventory, Component title) {
        super(handler, inventory, title, TEXTURE);
        this.imageWidth = 177;
        this.imageHeight = 196;
        this.inventoryLabelY = this.imageHeight - 93;
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float delta, int mouseX, int mouseY) {
        super.renderBg(graphics, delta, mouseX, mouseY);


        GuiUtil.drawHammer(graphics, this.leftPos + HAMMER_LEFT, this.topPos + HAMMER_TOP, this.menu.getCookTime(), this.menu.getCookTimeTotal());
        GuiUtil.drawEnergy(graphics, this.leftPos + ENERGY_LEFT, this.topPos + ENERGY_TOP, this.menu.getEnergyAmount(), this.machine.getMaxCapacity());
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        super.render(graphics, mouseX, mouseY, delta);

        if (GuiUtil.isHovering(this.getEnergyBounds(), mouseX, mouseY)) {
            GuiUtil.drawEnergyTooltip(graphics, this.menu.getEnergyAmount(), this.machine.getMaxCapacity(), mouseX, mouseY);
        }

        // Burn time tooltip.
        if (GuiUtil.isHovering(this.getHammerBounds(), mouseX, mouseY)) {
            graphics.renderTooltip(font, this.getHammerTooltip(), mouseX, mouseY);
        }
    }

    public Component getHammerTooltip() {
        return Component.translatable("gauge.ad_astra.cook_time", this.menu.getCookTime(), this.menu.getCookTimeTotal());
    }

    public Rectangle getEnergyBounds() {
        return GuiUtil.getEnergyBounds(this.leftPos + ENERGY_LEFT, this.topPos + ENERGY_TOP);
    }

    public Rectangle getHammerBounds() {
        return GuiUtil.getHammerBounds(this.leftPos + HAMMER_LEFT, this.topPos + HAMMER_TOP);
    }
}