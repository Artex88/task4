package ru.naumen.collection.task1;

import java.util.*;

/**
 * Дано:
 * <pre>
 * public class User {
 *     private String username;
 *     private String email;
 *     private byte[] passwordHash;
 *     …
 * }
 * </pre>
 * Нужно написать утилитный метод
 * <pre>
 * public static List<User> findDuplicates(Collection<User> collA, Collection<User> collB);
 * </pre>
 * <p>который возвращает дубликаты пользователей, которые есть в обеих коллекциях.</p>
 * <p>Одинаковыми считаем пользователей, у которых совпадают все 3 поля: username,
 * email, passwordHash. Дубликаты внутри коллекций collA, collB можно не учитывать.</p>
 * <p>Метод должен быть оптимален по производительности.</p>
 * <p>Пользоваться можно только стандартными классами Java SE.
 * Коллекции collA, collB изменять запрещено.</p>
 *
 * См. {@link User}
 *
 * @author vpyzhyanov
 * @since 19.10.2023
 */
public class Task1 {

    /**
     * Возвращает дубликаты пользователей, которые есть в обеих коллекциях
     * 1. Использован HashSet для ускоренного поиска дубликатов во второй коллекции. ArrayList для быстрого сохранения дубликата.
     * 2. 0(n).
     * 3. преобразование Collection в Set - 0(n) т.к. проходимся по всей коллекции.
     */
    public static List<User> findDuplicates(Collection<User> collA, Collection<User> collB) {
        List<User> userList = new ArrayList<>();
        Set<User> setA = new HashSet<>(collA);

        for (User user: collB)
            if (setA.contains(user))
                userList.add(user);

        return userList;
    }
}
