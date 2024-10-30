package test;

import classes.ArrayStringList;
import exceptions.ArrayStringListOverflowException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArrayStringListTest {

    ArrayStringList array = new ArrayStringList(10);

    @Test
    @DisplayName("Constructor: Инициализация предельного количества элементов в конструкторе (память)")
    void constructorInit() {
        ArrayStringList arrayNew = new ArrayStringList(1000);
        for (int i = 0; i< 1000; i++) arrayNew.add("test");
        assertEquals(1000,arrayNew.size());
    }

    @Test
    @DisplayName("Constructor: Инициализация ошибочного количества элементов в конструкторе")
    void constructorZeroInit() {
        assertThrows(ArrayStringListOverflowException.class, ()-> new ArrayStringList(0),"Нулевая длина: провалена");
        assertThrows(ArrayStringListOverflowException.class, ()-> new ArrayStringList(1001),"Максимальная длина сверх 1000: провалена");
    }

    @Test
    @DisplayName("Метод Add: Проверка добавления null")
    void addNull() {
        array.add(null);
        assertNull(array.get(0),"Получение null: провалено");
        assertEquals(array.size(),1,"Размер массива 1: провалено");
    }

    @Test
    @DisplayName("Метод Add: Проверка добавления пустой строки")
    void addEmptyString() {
        array.add("");
        assertEquals(array.get(0),"","Получение пустой строки: провалено");
        assertEquals(array.size(),1,"Размер массива 1: провалено");
    }

    @Test
    @DisplayName("Метод Add: Проверка добавления по значению")
    void addElement() {
        array.add("0");
        assertEquals("0",array.get(0));
    }

    @Test
    @DisplayName("Метод Add: Проверка добавления нескольких элементов")
    void addElements() {
        array.add("1");
        array.add("2");
        array.add("3");
        array.add("0");
        assertEquals(array.get(0) +array.get(1)+ array.get(2) + array.get(3),"1230","Валидность строк: провалено");
        assertEquals(array.size(),4,"Размер массива 1: провалено");
    }

    @Test
    @DisplayName("Метод Add: Проверка вставки элемента в начало")
    void testAddFirstInsert() {
        array.add("1");
        array.add("2");
        array.add("3");
        array.add(0,"0");
        assertEquals(array.get(0) +array.get(1)+ array.get(2) + array.get(3),"0123","Валидность строк: провалено");
        assertEquals(array.size(),4,"Размер массива 1: провалено");
    }

    @Test
    @DisplayName("Метод Add: Проверка вставки элемента в конец")
    void testAddLastInsert() {
        array.add("1");
        array.add("2");
        array.add("3");
        array.add(3,"0");
        assertEquals(array.get(0) +array.get(1)+ array.get(2) + array.get(3),"1230","Валидность строк: провалено");
        assertEquals(array.size(),4,"Размер массива 1: провалено");
    }

    @Test
    @DisplayName("Метод Add: Проверка вставки элемента в середину")
    void testAddMiddleInsert() {
        array.add("1");
        array.add("2");
        array.add("3");
        array.add(2,"0");
        assertEquals(array.get(0) +array.get(1)+ array.get(2) + array.get(3),"1203","Валидность строк: провалено");
        assertEquals(array.size(),4,"Размер массива 1: провалено");
    }

    @Test
    @DisplayName("Метод Add: Проверка возвращаемого значения")
    void addReturn() {
        String actual = array.add("Test#");
        assertEquals("Test#",actual);
    }

    @Test
    @DisplayName("Метод Set: Проверка присвоения значения")
    void set() {
        array.add("0");
        array.set(0,"000");
        assertEquals(array.get(0) ,"000","Валидность строк: провалено");
        assertEquals(array.size(),1,"Размер массива 1: провалено");
    }

    @Test
    @DisplayName("Метод set: Проверка присвоения null")
    void setNull() {
        array.add("0");
        array.set(0,null);
        assertNull(array.get(0) ,"Валидность строк: провалено");
        assertEquals(array.size(),1,"Размер массива 1: провалено");
    }

    @Test
    @DisplayName("Метод Set: Проверка присвоения пустой строки")
    void setEmptyString() {
        array.add("0");
        array.set(0,"");
        assertEquals(array.get(0) ,"","Валидность строк: провалено");
        assertEquals(array.size(),1,"Размер массива 1: провалено");
    }

    @Test
    @DisplayName("Метод Remove: Проверка удаления по Id первого, среднего и последнего элемента")
    void removeById() {
        array.add("1");
        array.add("2");
        array.add("3");
        array.add("4");
        array.add("5");

        array.remove(0);
        array.remove(1);
        array.remove(2);
        assertEquals(array.size(), 2, "Размер после удаления должен быть 2: провалено");
        assertEquals(array.get(0)+ array.get(1) ,"24","Проверка корректности значений после удаления: провалено");
    }


    @Test
    @DisplayName("Метод Remove: Проверка удаления по String первого, среднего и последнего элемента")
    void removeBString() {
        array.add("1");
        array.add("2");
        array.add("3");
        array.add("4");
        array.add("5");

        array.remove("1");
        array.remove("3");
        array.remove("5");

        assertEquals(array.size(), 2, "Размер после удаления должен быть 2: провалено");
        assertEquals(array.get(0)+ array.get(1) ,"24","Проверка корректности значений после удаления: провалено");
    }

    @Test
    @DisplayName("Метод Remove: Проверка на содержание значения в массиве")
    void contains() {
        array.add("1");
        array.add("2");
        array.add("3");
        array.add("4");
        array.add("5");

        assertTrue(array.contains("5"),"Поиск последнего элемента: провалено");
        assertTrue(array.contains("1"),"Поиск первого элемента: провалено");
    }

    @Test
    @DisplayName("Метод indexOf - первое вхождение")
    void indexOf() {
        array.add("1");
        array.add("1");
        array.add("3");
        array.add("5");
        array.add("5");

        assertEquals(3,array.indexOf("5"),"Поиск последнего элемента: провалено");
        assertEquals(0,array.indexOf("1"),"Поиск первого элемента: провалено");
    }

    @Test
    @DisplayName("Метод indexOf - первое вхождение (значение не найдено)")
    void indexOfNotFound() {
        array.add("1");
        array.add("1");
        array.add("3");
        array.add("5");
        array.add("5");

        assertEquals(-1,array.indexOf("test"));
    }

    @Test
    @DisplayName("Метод indexOf - первое вхождение в пустой базе -1")
    void indeOfxEmptySearch() {
        assertEquals(-1,array.indexOf("test"));
    }

    @Test
    @DisplayName("Метод lastIndexOf - последнее вхождение")
    void lastIndexOf() {
        array.add("1");
        array.add("1");
        array.add("3");
        array.add("5");
        array.add("5");

        assertEquals(4,array.lastIndexOf("5"),"Поиск последнего элемента: провалено");
        assertEquals(1,array.lastIndexOf("1"),"Поиск первого элемента: провалено");
    }

    @Test
    @DisplayName("Метод lastIndexOf - последнее вхождение в пустой базе = -1")
    void lastIndexOfEmptySearch() {
        assertEquals(-1,array.lastIndexOf("test"));
    }

    @Test
    @DisplayName("Метод get - получение результата")
    void get() {
        array.add("1");
        array.add("2");

        assertEquals("2",array.get(1));
    }

    @Test
    @DisplayName("Метод get - ошибка несуществующего элемента ArrayStringListOverflowException")
    void getNull() {
        assertThrows(ArrayStringListOverflowException.class,() -> array.get(0));
    }


    @Test
    @DisplayName("Метод equals - сравнение объекта")
    void testEquals() {
        array.add("1");
        ArrayStringList arrayExpected = new ArrayStringList(10);
        arrayExpected.add("1");
        assertTrue(array.equals(arrayExpected));
    }

    @Test
    @DisplayName("Метод size - сравнение размера")
    void size() {
        array.add("1");
        array.add("1");
        assertEquals(2,array.size());
    }

    @Test
    @DisplayName("Метод isEmpty() - негативный тест: не пустое")
    void NotIsEmpty() {
        array.add("1");
        array.add("1");
        assertFalse(array.isEmpty());
    }

    @Test
    @DisplayName("Метод isEmpty() - пустое")
    void isEmpty() {
        assertTrue(array.isEmpty());
    }

    @Test
    @DisplayName("Метод clear() - корректность очистки")
    void clear() {
        array.add("1");
        array.add("2");
        array.clear();
        assertTrue(array.isEmpty());
    }

    @Test
    @DisplayName("Метод toArray() - в массив")
    void toArray() {
        array.add("1");
        array.add("2");
        String[] arrayExpected = new String[2];
        arrayExpected[0] = "1";
        arrayExpected[1] = "2";
        assertArrayEquals(arrayExpected,array.toArray());
    }
}