package home.henry.calculator;

import java.util.LinkedList;
import java.util.List;

public class History {
    List<String> historyList = new LinkedList();

    void houseHouseHistory() {
        while (historyList.size() > 6) {
            historyList.remove(0);
        }
    }

    String toPrinttableString() {
        houseHouseHistory();
        String history = "";
        for (String line : historyList) {
            history = history + "\n" + line;
        }
        return history;
    }
    void add(String line){
        historyList.add(line);
    }
}
