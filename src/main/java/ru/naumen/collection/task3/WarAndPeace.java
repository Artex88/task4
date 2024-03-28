package ru.naumen.collection.task3;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * <p>Написать консольное приложение, которое принимает на вход произвольный текстовый файл в формате txt.
 * Нужно собрать все встречающийся слова и посчитать для каждого из них количество раз, сколько слово встретилось.
 * Морфологию не учитываем.</p>
 * <p>Вывести на экран наиболее используемые (TOP) 10 слов и наименее используемые (LAST) 10 слов</p>
 * <p>Проверить работу на романе Льва Толстого “Война и мир”</p>
 *
 * @author vpyzhyanov
 * @since 19.10.2023
 */
public class WarAndPeace {

    private static class Pair{
        private final String word;
        private int count;

        public Pair(String word, int count) {
            this.word = word;
            this.count = count;
        }
    }

    private static final Path WAR_AND_PEACE_FILE_PATH = Path.of("/home/artex/IdeaProjects/naumen/CollectionsTemplate/src/main/resources/WarAndPeace.txt");

    public static void main(String[] args) {
        Map<String, Integer> hashMap = new HashMap<>();
        Pair[] pairListTop10Small = new Pair[10];
        Pair[] pairListTop10Big = new Pair[10];
        SortedMap<Integer, String> stringSortedMap = new TreeMap<>();
        HashSet<String> top10SmallWords = new HashSet<>();


        Comparator<Pair> comparator = Comparator.comparingInt(o -> o.count);
        var ref = new Object() {
            int cnt1 = 0;
            int cnt2 = 0;

            int maxMinimCount = Integer.MAX_VALUE;
        };

        for (int i = 0; i < 10; i++){
            pairListTop10Small[i] = new Pair("", Integer.MAX_VALUE);
            pairListTop10Big[i] = new Pair("", 0);
        }

        WordParser wordParser = new WordParser(WAR_AND_PEACE_FILE_PATH);
        wordParser.forEachWord(word -> {

            if (hashMap.containsKey(word))
                hashMap.put(word, hashMap.get(word) + 1);
            else
                hashMap.put(word, 1);
            int wordCnt = hashMap.get(word);

            if (wordCnt >= pairListTop10Big[0].count){
                boolean flag = false;

                for (Pair pair : pairListTop10Big) {
                    if (pair.word.equals(word)) {
                        flag = true;
                        pair.count = wordCnt;
                        break;
                    }
                }

                if (flag)
                    Arrays.sort(pairListTop10Big, comparator);
                else {
                    if (ref.cnt1 != 10){
                        pairListTop10Big[ref.cnt1] = new Pair(word,wordCnt);
                        ref.cnt1++;
                    }
                    else
                        pairListTop10Big[0] = new Pair(word,wordCnt);
                }

            }

            if (wordCnt <= pairListTop10Small[9].count || top10SmallWords.contains(word)){
                boolean flag = false;

                for (Pair pair : pairListTop10Small) {
                    if (pair.word.equals(word)) {
                        flag = true;
                        pair.count = wordCnt;
                        break;
                    }
                }
                if (!flag) {
                    if (ref.cnt2 != 10){
                        pairListTop10Small[ref.cnt2] = new Pair(word,wordCnt);
                        top10SmallWords.add(word);
                        ref.cnt2++;
                    }
                    else{
                        top10SmallWords.remove(pairListTop10Small[9].word);
                        top10SmallWords.add(word);
                        pairListTop10Small[9] = new Pair(word,wordCnt);
                    }
                }
                Arrays.sort(pairListTop10Small, comparator);
            }
        });

        for (Pair pair: pairListTop10Big)
            System.out.println(pair.word + ": " + pair.count);
        System.out.println("-------------");
        for (Pair pair: pairListTop10Small)
            System.out.println(pair.word + ": " + pair.count);
    }
}
