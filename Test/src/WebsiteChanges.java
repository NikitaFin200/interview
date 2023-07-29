import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WebsiteChanges {
    public static void main(String[] args) {
        // Создаем и заполняем таблицу состояния на вчера
        Map<String, String> yesterdayData = new HashMap<>();
        yesterdayData.put("https://example.com/page1", "<html>Page 1 HTML</html>");
        yesterdayData.put("https://example.com/page2", "<html>Page 2 HTML</html>");
        yesterdayData.put("https://example.com/page3", "<html>Page 3 HTML</html>");

        // Создаем и заполняем таблицу состояния на сегодня
        Map<String, String> todayData = new HashMap<>();
        todayData.put("https://example.com/page2", "<html>New Page 2 HTML</html>"); // Страница изменилась
        todayData.put("https://example.com/page4", "<html>Page 4 HTML</html>"); // Новая страница
        todayData.put("https://example.com/page5", "<html>Page 5 HTML</html>"); // Новая страница

        // Находим исчезнувшие страницы
        Set<String> disappearedPages = new HashSet<>(yesterdayData.keySet());
        disappearedPages.removeAll(todayData.keySet());

        // Находим новые страницы
        Set<String> newPages = new HashSet<>(todayData.keySet());
        newPages.removeAll(yesterdayData.keySet());

        // Находим изменившиеся страницы
        Set<String> changedPages = new HashSet<>();
        for (String url : yesterdayData.keySet()) {
            if (todayData.containsKey(url) && !yesterdayData.get(url).equals(todayData.get(url))) {
                changedPages.add(url);
            }
        }

        // Формируем письмо
        StringBuilder emailContent = new StringBuilder();
        emailContent.append("Здравствуйте, дорогая и.о. секретаря\n\n");
        emailContent.append("За последние сутки во вверенных Вам сайтах произошли следующие изменения:\n\n");

        if (!disappearedPages.isEmpty()) {
            emailContent.append("Исчезли следующие страницы:\n");
            for (String url : disappearedPages) {
                emailContent.append(url).append("\n");
            }
            emailContent.append("\n");
        }

        if (!newPages.isEmpty()) {
            emailContent.append("Появились следующие новые страницы:\n");
            for (String url : newPages) {
                emailContent.append(url).append("\n");
            }
            emailContent.append("\n");
        }

        if (!changedPages.isEmpty()) {
            emailContent.append("Изменились следующие страницы:\n");
            for (String url : changedPages) {
                emailContent.append(url).append("\n");
            }
            emailContent.append("\n");
        }

        // Выводим письмо
        System.out.println(emailContent.toString());
    }
}
