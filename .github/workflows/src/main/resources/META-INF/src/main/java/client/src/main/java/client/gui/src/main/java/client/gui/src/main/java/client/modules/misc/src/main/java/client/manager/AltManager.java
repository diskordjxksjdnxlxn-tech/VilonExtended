package client.manager;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.client.Minecraft;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AltManager {
    private List<Account> accounts = new ArrayList<>();
    private int currentIndex = 0;

    // Загрузка аккаунтов из файла alts.txt (формат: логин:пароль)
    public void loadAlts(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(":");
            if (parts.length >= 2) {
                accounts.add(new Account(parts[0], parts[1]));
            }
        }
        reader.close();
        System.out.println("[AltManager] Загружено " + accounts.size() + " аккаунтов.");
    }

    // Автоматический логин на сервере через команду /login
    public void autoLogin() {
        if (accounts.isEmpty()) {
            System.out.println("[AltManager] Список аккаунтов пуст.");
            return;
        }
        Account acc = accounts.get(currentIndex);
        Minecraft.getInstance().player.sendMessage(
            new StringTextComponent("/login " + acc.password),
            Minecraft.getInstance().player.getUUID()
        );
        System.out.println("[AltManager] Выполнен логин для: " + acc.username);
    }

    // Переключение на следующий аккаунт (например, при бане/смерти)
    public void switchToNext() {
        if (accounts.isEmpty()) return;
        currentIndex = (currentIndex + 1) % accounts.size();
        System.out.println("[AltManager] Переключено на: " + accounts.get(currentIndex).username);
    }

    // Получить текущий аккаунт
    public Account getCurrentAccount() {
        if (accounts.isEmpty()) return null;
        return accounts.get(currentIndex);
    }

    // Внутренний класс для хранения данных аккаунта
    static class Account {
        String username, password;
        Account(String u, String p) {
            username = u;
            password = p;
        }
    }
}
