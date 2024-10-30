package classes;

import exceptions.ArrayStringListNotFoundException;
import exceptions.ArrayStringListOverflowException;
import interfaces.StringList;

import java.util.Objects;

public class ArrayStringList implements StringList {

    private final String[] array;
    private int size;
    private final int maxSize;

    public ArrayStringList(int size) {
        if (size <= 0 || size > MAX_STRING_LIST_SIZE) {
            throw new ArrayStringListOverflowException();
        }
        this.array = new String[size];
        this.size = 0;
        this.maxSize = size;
    }

    @Override
    public String add(String item) {
        if (size >= maxSize) {
            throw new ArrayStringListOverflowException();
        }
        return array[size++] = item;
    }

    @Override
    public String add(int index, String item) {
        if (index > size || size >= maxSize) {
            throw new ArrayStringListOverflowException();
        }

        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }
        size++;
        return array[index] = item;
    }

    @Override
    public String set(int index, String item) {
        if (index >= size) {
            throw new ArrayStringListOverflowException();
        }
        return array[index] = item;
    }

    @Override
    public String remove(String item) {
        boolean flag = false;
        String removeResult = null;
        int count = size;
        while (count > 0) {
            if (Objects.equals(item, array[--count])) {
                removeResult = remove(count--);
                flag = true;
            }
        }
        if (!flag) {
            throw new ArrayStringListNotFoundException();
        }
        return removeResult;
    }

    @Override
    public String remove(int index) {
        if (size == 0) {
            throw new ArrayStringListNotFoundException();
        }
        if (index >= size) {
            throw new ArrayStringListOverflowException();
        }
        String removeResult = array[index];
        for (int i = index; i < size; i++) {
            array[i] = array[i + 1];
        }
        size--;
        return removeResult;
    }

    @Override
    public boolean contains(String item) {
        for (String elem : array) {
            if (Objects.equals(elem, item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(String item) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(array[i], item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        int result = -1;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(array[i], item)) {
                result = i;
            }
        }
        return result;
    }

    @Override
    public String get(int index) {
        if (index >= size) {
            throw new ArrayStringListOverflowException();
        }
        return array[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        if (otherList.size() == size) {
            for (int i = 0; i < size; i++) {
                if (!Objects.equals(array[i], otherList.get(i))) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public String[] toArray() {
        String[] resultArray = new String[size];
        for (int i = 0; i < size; i++) {
            resultArray[i] = array[i];
        }
        return resultArray;
    }
}
