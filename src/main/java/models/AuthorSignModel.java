package models;

import beans.AuthorSign;
import controllers.SignController;

import java.util.ArrayList;

/**
 * Created by Admin on 12.07.2017.
 */
public class AuthorSignModel {
    private static AuthorSignModel ourInstance = new AuthorSignModel();
    private static final String ERROR_MESSAGE = "ERROR!";

    public static AuthorSignModel getInstance() {
        return ourInstance;
    }

    private SignController controller = SignController.getInstance();

    private AuthorSignModel() {
    }

    public String getSign(String author) throws IllegalArgumentException {
        author = transformAuthorName(author);

        ArrayList<AuthorSign> signs = getSignListByFirstLeter(author);
        int maxMatchCount = 0, currentMatchCount, matchTemplateIndex = -1;

        for (int i = 0; i < signs.size(); i++) {
            AuthorSign sign = signs.get(i);
            currentMatchCount = getMatchCount(sign.getTemplate(), author);
            if (currentMatchCount > maxMatchCount) {
                maxMatchCount = currentMatchCount;
                matchTemplateIndex = i;
            } else if (currentMatchCount < maxMatchCount) {
                break;
            }
        }

        if (matchTemplateIndex == -1) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }

        return signs.get(matchTemplateIndex).getCode();
    }

    private int getMatchCount(String template, String sample) {
        int index = 0;

        while (index < template.length() && index < sample.length()) {
            if (template.charAt(index) == sample.charAt(index)) {
                index++;
            } else {
                break;
            }
        }

        return index;
    }

    private String transformAuthorName(String authorName) {
        String result = authorName.toLowerCase().replaceAll("й", "и")
                .replaceAll("[ъ|ы|ь]", "ш");

        return result;
    }

    private ArrayList<AuthorSign> getSignListByFirstLeter(String author) throws IllegalArgumentException {
        char firstLetter = Character.toUpperCase(author.charAt(0));
        ArrayList<AuthorSign> result = null;
        try {
            result = (ArrayList<AuthorSign>) controller.getTable().get(firstLetter);
            if (result == null) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Скорее всего, введены некорректные данные.");
            //e.printStackTrace();
        }
        return result;
    }
}
