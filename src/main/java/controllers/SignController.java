package controllers;

import beans.AuthorSign;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by Admin on 12.07.2017.
 */
public class SignController {
    private static SignController instance =  new SignController();

    private Map<Character, List<AuthorSign>> table;

    private SignController() {
        initTable();
        fillTableFromFile();
    }

    public static SignController getInstance() {
        return instance;
    }

    public Map<Character, List<AuthorSign>> getTable() {
        return table;
    }

    private void fillTableFromFile() {
        String line;

        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/Havkina.csv"))) {
            line = reader.readLine();

            while (reader.ready()) {
                line = reader.readLine();
                AuthorSign authorSign = getAuthorSignFromLIne(line);
                char firstChar = authorSign.getCode().charAt(0);

                table.get(firstChar).add(authorSign);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private AuthorSign getAuthorSignFromLIne(String line) {
        int delimiter2 = line.indexOf(';', 1);
//        int delimiter3 = line.indexOf(';', delimiter2 + 1);
        String sign = line.substring(1, delimiter2);
        String template = line.substring(delimiter2 + 1, line.length() - 1);

        return new AuthorSign(sign, template);
    }

    private void initTable() {
        table = new TreeMap<>();
        for (char c = 'А'; c <= 'Я'; c++) {
            if (c == 'й' || c == 'ъ' || c == 'ы' || c == 'ь') {
                continue;
            }
            table.put(c, new ArrayList<AuthorSign>(100));
        }
    }
}
