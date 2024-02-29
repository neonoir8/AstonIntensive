package homeworks.hw1;

public interface CustomArrayListInterface<E> {

    void add(E element);

    void add(int index, E element);

    E get(int index);

    boolean isEmpty();

    int size();

    void clear();

    E remove(int index);

    boolean remove(E element);

    int indexOff(E element);

    void sort();
}
