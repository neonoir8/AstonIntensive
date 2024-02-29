package homeworks.hw1;

import java.util.Arrays;

public class CustomArrayList<E extends Comparable<E>> implements CustomArrayListInterface<E> {

    private static final int INITIAL_CAPACITY = 10;
    private final Object[] elements;
    private int size = 0;

    public CustomArrayList() {
        elements = new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(E element) {
        if (size == elements.length) {
            increaseCapacity();
        }
        elements[size++] = element;
    }

    private void increaseCapacity() {
        int newCapacity = elements.length * 2;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        if (size == elements.length) {
            increaseCapacity();
        }

        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + "Size: " + size);
        }
        return (E) elements[index];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        Arrays.fill(elements, null);
        size = 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        E removedElement = (E) elements[index];

        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[size - 1] = null;
        size--;

        return removedElement;
    }

    @Override
    public boolean remove(E element) {
        int index = indexOff(element);
        if (index >= 0) {
            remove(index);
            return true;
        }
        return false;
    }

    @Override
    public int indexOff(E element) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(elements)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void sort() {
        quickSort(0, size - 1);
    }

    private void quickSort(int begin, int end) {
        if (begin < end) {
            int pi = partition(begin, end);

            quickSort(begin, pi - 1);
            quickSort(pi + 1, end);
        }
    }

    @SuppressWarnings("unchecked")
    private int partition(int begin, int end) {
        E pivot = (E) elements[end];
        int i = (begin - 1);

        for (int j = 0; j < end; j++) {
            if (((E) elements[j]).compareTo(pivot) < 0) {
                i++;

                E temp = (E) elements[i];
                elements[i] = elements[j];
                elements[j] = temp;
            }
        }

        E temp = (E) elements[i + 1];
        elements[i + 1] = elements[end];
        elements[end] = temp;

        return i + 1;

    }
}



