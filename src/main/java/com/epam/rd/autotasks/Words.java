package com.epam.rd.autotasks;


import java.util.*;

public class Words {
    HashMap<String, Integer> list = new HashMap<>();

    public String countWords(List<String> lines) {
        List<String> words = new ArrayList<>();
        for (String line:
                lines) {

            String[] wordList = line.replace('.', ' ')
                    .replace(',', ' ')
                    .replace('!', ' ')
                    .replace('?', ' ')
                    .replace('"', ' ')
                    .replace('“', ' ')
                    .replace('’', ' ')
                    .replace(':', ' ')
                    .replace('—', ' ')
                    .replace('”', ' ')
                    .replace('(', ' ')
                    .replace(')', ' ')
                    .replace(';', ' ')
                    .replace('‘', ' ')
                    .replace('-', ' ')
                    .replace('*', ' ')
                    .replace('\'', ' ')
                    .replace('/', ' ')
                    .split(" ");
            for (String word:
                    wordList) {
                if(word.length() >= 4){
                    words.add(word.toLowerCase());
                }
            }
        }

        for (String word: words){
            if(list.containsKey(word)){
                list.put(word, list.get(word) + 1);
            }else{
                list.put(word, 1);
            }
        }

        for (String word:
                words) {
            if(list.containsKey(word) && list.get(word) < 10){
                list.remove(word);
            }
        }

        StringBuilder result = new StringBuilder();
        HashMap<String, Integer> sortedList = sortByValue(list);


        for (String word:
                sortedList.keySet()) {
            result.append(word);
            result.append(" - ");
            result.append(sortedList.get(word));
            result.append("\n");

        }
        int last = result.lastIndexOf("\n");
        result.deleteCharAt(last);


        return result.toString();
    }

    public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm){
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer> > list =
                new LinkedList<>(hm.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                int valueComparison = o2.getValue().compareTo(o1.getValue());
                if (valueComparison == 0) { // If values are equal, compare keys
                    return o1.getKey().compareTo(o2.getKey());
                }
                return valueComparison;
            }
        });

        HashMap<String, Integer> temp = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

}
