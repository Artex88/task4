package ru.naumen.collection.task3;

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

    private static final Path WAR_AND_PEACE_FILE_PATH = Path.of("src/main/resources/WarAndPeace.txt");

    /**
     * 1. Использовал LinkedHashMap для эффективного подсчёта слов и дальнейшей итерации по этой коллекции
     *    и PriorityQueue для поиска 10 наиб. и 10 наим. встречаемых слов.
     * 2. O(n)
     * 3. Читаем все слова - O(n). Добавление и сортировка в PriorityQueue происходит за константу, т.к. размер PriorityQueue - константа.
     */
    public static void main(String[] args) {
        Map<String, Integer> linkedHashMap = new LinkedHashMap<>();
        Queue<Map.Entry<String, Integer>> top10Big = new PriorityQueue<>(11, Comparator.comparingInt(Map.Entry::getValue));
        Queue<Map.Entry<String, Integer>> top10Small = new PriorityQueue<>(11, (a,b) -> b.getValue()  - a.getValue());

        WordParser wordParser = new WordParser(WAR_AND_PEACE_FILE_PATH);

        wordParser.forEachWord(word -> {
            linkedHashMap.put(word, linkedHashMap.getOrDefault(word, 0) + 1);
        });

        for (Map.Entry<String,Integer> entry: linkedHashMap.entrySet()){

            top10Big.add(entry);
            if (top10Big.size() == 11)
                top10Big.poll();

            top10Small.add(entry);
            if (top10Small.size() == 11)
                top10Small.poll();
        }

        for (Map.Entry<String, Integer> entry: top10Big)
            System.out.println(entry.getKey() + ": " + entry.getValue());
        System.out.println("-------------------");
        for (Map.Entry<String, Integer> entry: top10Small)
            System.out.println(entry.getKey() + ": " + entry.getValue());
    }
}
