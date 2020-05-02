package RowsObject;

import java.util.ArrayList;
import java.util.List;


public class Intent {

    String defaultIntent;

    List<String> messages = new ArrayList<>();

    List<String> answers  = new ArrayList<>();

    public String getDefaultIntent() {
        return defaultIntent;
    }

    public void setDefaultIntent(String defaultIntent) {
        this.defaultIntent = defaultIntent;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "Intent{" +
                "defaultIntent='" + defaultIntent + '\'' +
                ", messages=" + messages +
                ", answers=" + answers +
                '}';
    }
}
