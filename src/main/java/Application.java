import models.AuthorSignModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;

/**
 * Created by Admin on 12.07.2017.
 */
public class Application {
    public static void main(String[] args) {
        AuthorSignModel signModel = AuthorSignModel.getInstance();
        String author = "";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                author = getAuthor(reader);

                if ("-e".equals(author)) {
                    System.out.println("Выход из программы.");
                    break;
                }

                String sign = null;
                try {
                    sign = signModel.getSign(author);
                    System.out.println("Авторский код для автора " + author + ": " + sign);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }

                System.out.println("============================================================");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String getAuthor(BufferedReader reader) throws IOException {
        String author = "";
        while ("".equals(author)) {
            System.out.println("Введите \"-e\" для выхода");
            System.out.print("Автор: ");
            author = reader.readLine();
        }
        return author;
    }
}
