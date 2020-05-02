package RowsObject;

import org.apache.poi.sl.usermodel.Sheet;

import java.util.*;

public class ReadService {


    public static void main(String[] args) {
        List<Row> rows = new ArrayList<>();
        rows.add(new Row("Эталон 1", "сообщение 1", "Ответ 1"));
        rows.add(new Row("Эталон 1", "сообщение 2", "Ответ 1"));
        rows.add(new Row("Эталон 1", "сообщение 2", "Ответ 1"));
        rows.add(new Row("Эталон 1", "сообщение 2", "Ответ 1"));
        rows.add(new Row("Эталон 2", "сообщение 3", "Ответ 2"));

        List<Intent> intents = new ArrayList<>();

        for (Row row : rows) {
            Intent intent;
            if (intents.size() > 0 && intents.get(intents.size() - 1).defaultIntent.equals(row.defaultIntent))
                intent = intents.get(intents.size() - 1);
            else {
                intent = new Intent();
                intents.add(intent);
            }
            intent.setDefaultIntent(row.getDefaultIntent());
            intent.messages.add(row.getMessage());
            if (!intent.answers.contains(row.answer)) intent.answers.add(row.answer);
        }

        System.out.println(Arrays.toString(intents.toArray()));

        intents.forEach(intent -> {
            intent.messages.forEach(message -> {
                String answer = request(message);
                intent.answers.contains(answer);
            });
        });

        // эталон: Эталон 1; сообщения: [сообщение 1, сообщение 2]
        // эталон: Эталон 2; сообщения: [сообщение 3]

    }

    public static String request(String intent){
        return "answer";
    }

}
