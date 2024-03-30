package ru.naumen.collection.task2;

import java.util.HashMap;
import java.util.Random;

/**
 * Дано:
 * <pre>
 * public class Ticket {
 *     private long id;
 *     private String client;
 *     …
 * }</pre>
 * <p>Разработать программу для бармена в холле огромного концертного зала.
 * Зрители в кассе покупают билет (класс Ticket), на котором указан идентификатор билета (id) и имя зрителя.
 * При этом, есть возможность докупить наборы разных товаров (комбо-обед): нет товаров, напитки, еда и напитки.
 * Доп. услуги оформляются через интернет и привязываются к билету, но хранятся отдельно от билета
 * (нельзя добавить товары в класс Ticket).</p>
 * <p>Бармен сканирует билет и получает объект Ticket. По этому объекту нужно уметь
 * находить необходимые товары по номеру билета. И делать это нужно очень быстро,
 * ведь нужно как можно быстрее всех накормить.</p>
 * <p>
 * См. {@link Ticket}
 *
 * @author vpyzhyanov
 * @since 19.10.2023
 */
public class Task2 {

    private enum ComboLunch{
        Empty,
        Drinks,
        FoodAndDrinks
    }

    private static HashMap<Ticket, ComboLunch> internetService = new HashMap<>();

    /**
     * 1. Использован HashMap для эффективного поиска типа ланча по id билета.
     * 2. О(1).
     * 3. Получение значения по ключу в HashMap - О(1).
     */
    public static ComboLunch getLunchForId(Ticket ticket){
        return internetService.get(ticket);
    }
}
