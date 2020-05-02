package RowsObject;

public class Row {

    String defaultIntent;

    String message;

    String answer;

    public Row(String defaultIntent, String message, String answer) {
        this.defaultIntent = defaultIntent;
        this.message = message;
        this.answer = answer;
    }

    public String getDefaultIntent() {
        return defaultIntent;
    }

    public void setDefaultIntent(String defaultIntent) {
        this.defaultIntent = defaultIntent;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
