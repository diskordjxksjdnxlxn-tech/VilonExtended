package client.modules.misc;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextFormatting;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MockModule {
    private boolean enabled = true;
    private boolean profanity = true;
    private Random random = new Random();
    private long lastMessageTime = 0;

    private List<String> messages = new ArrayList<String>() {{
        add("{killer} сказал: Ой, опять {player} умерла... плак-плак 😢");
        add("{player}, ты как табуретка — каждый раз падаешь.");
        add("Вот это поворот, {player} опять труп.");
        add("Скилл {player} — это миф.");
        add("Ой-ой-ой, {player}, ну ты и лузер.");
        add("{player}, иди собирай кубики, это не твоё.");
    }};

    private List<String> profanityMessages = new ArrayList<String>() {{
        add("{player}, ты просто ***, иди в ракету.");
        add("{player}, твоя мамаша плачет от твоего скилла.");
        add("Ебать ты лох, {player}.");
        add("{player}, ты хуже бота.");
    }};

    @SubscribeEvent
    public void onEntityDeath(LivingDeathEvent event) {
        if (!enabled) return;
        if (!(event.getEntity() instanceof PlayerEntity)) return;
        PlayerEntity victim = (PlayerEntity) event.getEntity();
        PlayerEntity killer = (PlayerEntity) event.getSource().getEntity();
        if (killer == null || !killer.equals(Minecraft.getInstance().player)) return;
        if (System.currentTimeMillis() - lastMessageTime < 5000) return;
        lastMessageTime = System.currentTimeMillis();

        String msg = getRandomMessage()
            .replace("{player}", victim.getName().getString())
            .replace("{killer}", killer.getName().getString());

        Minecraft.getInstance().player.sendMessage(
            new StringTextComponent(TextFormatting.RED + msg),
            Minecraft.getInstance().player.getUUID()
        );
    }

    private String getRandomMessage() {
        List<String> pool = new ArrayList<>(messages);
        if (profanity) pool.addAll(profanityMessages);
        return pool.get(random.nextInt(pool.size()));
    }
}
