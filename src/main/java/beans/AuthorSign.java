package beans;

/**
 * Created by Admin on 12.07.2017.
 */
public class AuthorSign {
    private String code;
    private String template;

    public AuthorSign() {
    }

    public AuthorSign(String code, String template) {
        this.code = code;
        this.template = template;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }
}
