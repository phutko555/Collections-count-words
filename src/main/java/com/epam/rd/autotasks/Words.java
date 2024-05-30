package com.epam.rd.autotasks;

import java.util.*;

public class Words {

    public String countWords(List<String> lines) {
        Map<String, Integer> wordCount = new HashMap<>();


        for (String line : lines) {
            String[] words = line.split("\\s+");
            for (String word : words) {
                word = word.replaceAll("[^a-zA-Zа-яА-Я]", "").toLowerCase();
                if (word.length() >= 4) {
                    if (wordCount.containsKey(word)) {
                        wordCount.put(word, wordCount.get(word) + 1);
                    } else {
                        wordCount.put(word, 1);
                    }
                }
            }
        }

        Iterator<Map.Entry<String, Integer>> iterator = wordCount.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            if (entry.getValue() < 10) {
                iterator.remove();
            }
        }


        List<Map.Entry<String, Integer>> sortedWords = new ArrayList<>(wordCount.entrySet());
        Collections.sort(sortedWords, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
                int compare = Integer.compare(b.getValue(), a.getValue());
                if (compare == 0) {
                    return a.getKey().compareTo(b.getKey());
                }
                return compare;
            }
        });

        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, Integer> entry : sortedWords) {
            result.append(entry.getKey()).append(" - ").append(entry.getValue()).append("\n");
        }

        return result.toString().trim();
    }
}
