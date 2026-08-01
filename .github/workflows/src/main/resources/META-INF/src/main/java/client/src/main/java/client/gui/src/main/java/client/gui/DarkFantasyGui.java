package client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import java.awt.Color;

@OnlyIn(Dist.CLIENT)
public class DarkFantasyGui extends Screen {
    public DarkFantasyGui() {
        super(new StringTextComponent("Dark Fantasy Menu"));
    }

    @Override
    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        fill(stack, 0, 0, width, height, new Color(10, 10, 10, 220).getRGB());
        font.drawShadow(stack, "Combat", 30, 30, 0xE0C080);
        font.drawShadow(stack, "Movement", 30, 50, 0xE0C080);
        font.drawShadow(stack, "Player", 30, 70, 0xE0C080);
        font.drawShadow(stack, "Misc", 30, 90, 0xE0C080);
        font.drawShadow(stack, "Visuals", 30, 110, 0xE0C080);
        super.render(stack, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
