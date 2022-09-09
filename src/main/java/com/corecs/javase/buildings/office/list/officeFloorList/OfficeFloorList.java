package com.corecs.javase.buildings.office.list.officeFloorList;

import com.corecs.javase.buildings.office.Office;
import com.corecs.javase.exceptions.SpaceIndexOutOfBoundsException;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.UnaryOperator;

public class OfficeFloorList implements List<Office> {
    private Node head;
    private int size;

    // Конструктор инициализирует выделенную голову
    protected OfficeFloorList() {
        initHead();
    }

    // Конструктор инициализирует переданный лист
    protected OfficeFloorList(Collection<Office> list) {
        this();
        addAll(list);
    }

    // Метод инициации выделенной головы
    private void initHead() {
        this.head = new Node(null, null);
        this.head.next = this.head;
    }

    // Проверка индекса
    private void checkIndex(int index) {
        if (index >= this.size || index < 0) {
            throw new SpaceIndexOutOfBoundsException("Incorrect index of office...");
        }
    }

    // Проверка индекса для методов добавления
    private void checkIndexForAdd(int index) {
        if (index > this.size || index < 0) {
            throw new SpaceIndexOutOfBoundsException("Incorrect index of office...");
        }
    }

    // Метод получения узла по индексу
    private Node getNodeByIndex(int index) {
        checkIndex(index);
        Node ref = null;
        int count = 0;
        for (Node node = this.head.next; node != this.head; node = node.next) {
            if (count++ == index) {
                ref = node;
                break;
            }
        }
        return ref;
    }

    // Получение последнего узла
    private Node getLastNode() {
        Node last = this.head;
        for (Node ref = this.head.next; ref != this.head; ref = ref.next) {
            last = ref;
        }
        return last;
    }

    // Добавление первого элемента
    private void addFirst(Office office) {
        if (office != null) {
            this.head.next = new Node(this.head.next, office);
            this.size++;
        }
    }

    // Добавление последнего элемента
    private void addLast(Office office) {
        if (office != null) {
            getLastNode().next = new Node(this.head, office);
            this.size++;
        }
    }

    // Метод удаления первого элемента
    private Office removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Element not found...");
        }
        Office deleteOffice = this.head.next.office;
        this.head.next = this.head.next.next;
        this.size--;
        return deleteOffice;
    }

    // Метод удаления последнего элемента
    private Office removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Element not found...");
        }
        Office deleteOffice = getLastNode().office;
        Node prevNode = getNodeByIndex(size - 2);
        prevNode.next = prevNode.next.next;
        this.size--;
        return deleteOffice;
    }

    // Метод получения размера листа
    @Override
    public int size() {
        return this.size;
    }

    // Проверка пустой ли лист
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    // Метод проверки есть ли данный объект в листе
    @Override
    public boolean contains(Object office) {
        return indexOf(office) != -1;
    }

    // Итератор над листом оффисов
    @Override
    public Iterator<Office> iterator() {
        Iterator<Office> iter = new Iterator<Office>() {
            Node ref = head.next;

            @Override
            public boolean hasNext() {
                return ref.next.office != null;
            }

            @Override
            public Office next() {
                Node current = ref;
                ref = ref.next;
                return current.office;
            }
        };
        return iter;
    }

    // Получение масива оффисов
    @Override
    public Object[] toArray() {
        Office[] offices = new Office[this.size];
        int count = 0;
        for (Node ref = this.head.next; ref != this.head; ref = ref.next) {
            offices[count++] = ref.office;
        }
        return offices;
    }

    // Получение массива объектов
    @Override
    public <T> T[] toArray(T[] array) {
        int count = 0;
        if (array.length < size) {
            array = (T[]) Array.newInstance(array.getClass().getComponentType(), this.size);
        }
        for (Node ref = this.head.next; ref != this.head; ref = ref.next) {
            array[count++] = (T) ref.office;
        }
        return array;
    }

    // Метод добавления оффиса
    @Override
    public boolean add(Office office) {
        addLast(office);
        return true;
    }

    // Удаление оффиса по объекту оффиса
    @Override
    public boolean remove(Object office) {
        if (office != null) {
            int index = indexOf(office);
            remove(index);
        }
        return true;
    }

    // Возвращает true, если этот список содержит все элементы указанной коллекции
    @Override
    public boolean containsAll(Collection<?> offices) {
        for (Object object : offices) {
            if (!contains(object)) {
                return false;
            }
        }
        return true;
    }

    // Добавление листа оффисов
    @Override
    public boolean addAll(Collection<? extends Office> officeList) {
        if (officeList != null && !officeList.isEmpty()) {
            for (Office office : officeList) {
                add(office);
            }
        }
        return true;
    }

    // Вставляет все элементы указанной коллекции в этот список, начиная с указанной позиции
    @Override
    public boolean addAll(int index, Collection<? extends Office> officeList) {
        checkIndexForAdd(index);
        Node node = getNodeByIndex(index);
        Node nexNode = node.next;
        boolean modified = false;
        if (index == 0) {
            addAll(officeList);
            modified = true;
        } else {
            for (Office office : officeList) {
//                add(index++, office);
                modified = true;
            }
        }
        return modified;
    }

    // Метод удаляет из листа все элементы переданной коллекции
    @Override
    public boolean removeAll(Collection<?> offices) {
        Objects.requireNonNull(offices);
        boolean modified = false;
        for (Object object : offices) {
            if (contains(object)) {
                remove(object);
                modified = true;
            }
        }
        return modified;
    }

    // Метод удаляет из листа элементы, не принадлижащие переданной коллекции
    @Override
    public boolean retainAll(Collection<?> offices) {
        Objects.requireNonNull(offices);
        boolean modified = false;
        Iterator<Office> iterator = iterator();
        while (iterator.hasNext()) {
            Office office = iterator.next();
            if (!offices.contains(office)) {
                modified = true;
                remove(office);
            }
        }
        return modified;
    }

    @Override
    public void replaceAll(UnaryOperator<Office> operator) {
        List.super.replaceAll(operator);
    }

    @Override
    public void sort(Comparator<? super Office> c) {
        List.super.sort(c);
    }

    // Очистка листа
    @Override
    public void clear() {
        initHead();
        this.size = 0;
    }

    // Метод получения оффиса по индексу
    @Override
    public Office get(int index) {
        checkIndex(index);
        return getNodeByIndex(index).office;
    }

    // Замена офиса по индексу
    @Override
    public Office set(int index, Office office) {
        checkIndex(index);
        Office refOffice = null;
        if (office != null) {
            Node setNode = getNodeByIndex(index);
            refOffice = setNode.office;
            setNode.office = office;
        }
        return refOffice;
    }

    // Добавление оффиса по индексу
    @Override
    public void add(int index, Office office) {
        checkIndexForAdd(index);
        if (office != null) {
            Node prev;
            if (index == 0) {
                prev = head;
            } else {
                prev = getNodeByIndex(index - 1);
            }
            prev.next = new Node(prev.next, office);
            this.size++;
        }
    }

    // Удаление элемента по индексу
    @Override
    public Office remove(int index) {
        checkIndex(index);
        Office deleteOffice = null;
        if (index == 0) {
            deleteOffice = removeFirst();
        } else if (index == (size - 1)) {
            deleteOffice = removeLast();
        } else {
            Node prevNode = getNodeByIndex(index - 1);
            deleteOffice = prevNode.next.office;
            prevNode.next = prevNode.next.next;
            this.size--;
        }
        return deleteOffice;
    }

    // Поиск позиции первого вхождения офиса по объекту офиса в листе
    @Override
    public int indexOf(Object office) {
        int firstIndex = -1;
        if (office == null) {
            return -1;
        }
        for (Node ref = this.head.next; ref != this.head; ref = ref.next) {
            firstIndex++;
            if (office.equals(ref.office)) {
                return firstIndex;
            }
        }
        return -1;
    }

    // Поиск позиции последнего вхождения офиса по объекту офиса в листе
    @Override
    public int lastIndexOf(Object office) {
        int lastIndex = -1;
        if (office != null) {
            int index = -1;
            for (Node ref = this.head.next; ref != this.head; ref = ref.next) {
                index++;
                if (office.equals(ref.office)) {
                    lastIndex = index;
                }
            }
        }
        return lastIndex;
    }

    @Override
    public ListIterator<Office> listIterator() {
        ListIterator<Office> listIterator = new ListIterator<Office>() {

            @Override
            public boolean hasNext() {
                return head.next != null;
            }

            @Override
            public Office next() {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public Office previous() {
                return null;
            }

            @Override
            public int nextIndex() {
                return 0;
            }

            @Override
            public int previousIndex() {
                return 0;
            }

            @Override
            public void remove() {

            }

            @Override
            public void set(Office office) {

            }

            @Override
            public void add(Office office) {

            }
        };
        return null;
    }

    @Override
    public ListIterator<Office> listIterator(int index) {
        return null;
    }

    @Override
    public List<Office> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public Spliterator<Office> spliterator() {
        return List.super.spliterator();
    }

    private class Node {
        private Node next;
        private Office office;

        Node(Node next, Office office) {
            this.next = next;
            this.office = office;
        }

        @Override
        public String toString() {
            return "Node{" + office + '}';
        }
    }
}
