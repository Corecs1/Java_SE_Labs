package com.corecs.javase.buildings.office.list.officeFloorList;

import com.corecs.javase.buildings.interfaces.Space;
import com.corecs.javase.exceptions.SpaceIndexOutOfBoundsException;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;
import java.util.function.UnaryOperator;

public class OfficeFloorList implements List<Space>, Serializable {
    private Node head;
    private int size;

    // Конструктор инициализирует выделенную голову
    public OfficeFloorList() {
        initHead();
    }

    // Конструктор инициализирует переданный лист
    public OfficeFloorList(Collection<Space> list) {
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
    private void addFirst(Space office) {
        if (office != null) {
            this.head.next = new Node(this.head.next, office);
            this.size++;
        }
    }

    // Добавление последнего элемента
    private void addLast(Space office) {
        if (office != null) {
            getLastNode().next = new Node(this.head, office);
            this.size++;
        }
    }

    // Метод удаления первого элемента
    private Space removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Element not found...");
        }
        Space deleteOffice = this.head.next.office;
        this.head.next = this.head.next.next;
        this.size--;
        return deleteOffice;
    }

    // Метод удаления последнего элемента
    private Space removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Element not found...");
        }
        Space deleteOffice = getLastNode().office;
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
    public Iterator<Space> iterator() {
        Iterator<Space> iter = new Iterator<>() {
            Node ref = head;

            @Override
            public boolean hasNext() {
                if (ref == null) return false;
                if (ref.next == null) return false;
                return !ref.next.equals(head);
            }

            @Override
            public Space next() {
                Node current = ref.next;
                ref = ref.next;
                return current.office;
            }
        };
        return iter;
    }

    // Получение масива оффисов
    @Override
    public Object[] toArray() {
        Space[] offices = new Space[this.size];
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
    public boolean add(Space office) {
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
    public boolean addAll(Collection<? extends Space> officeList) {
        if (officeList != null && !officeList.isEmpty()) {
            for (Space office : officeList) {
                add(office);
            }
        }
        return true;
    }

    // Вставляет все элементы указанной коллекции в этот список, начиная с указанной позиции
    @Override
    public boolean addAll(int index, Collection<? extends Space> officeList) {
        checkIndexForAdd(index);
        Node node = getNodeByIndex(index);
        Node nexNode = node.next;
        boolean modified = false;
        if (index == 0) {
            addAll(officeList);
            modified = true;
        } else {
            for (Space office : officeList) {
                add(index++, office);
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
        Iterator<Space> iterator = iterator();
        while (iterator.hasNext()) {
            Space office = iterator.next();
            if (!offices.contains(office)) {
                modified = true;
                remove(office);
            }
        }
        return modified;
    }

    @Override
    public void replaceAll(UnaryOperator<Space> operator) {
        List.super.replaceAll(operator);
    }

    @Override
    public void sort(Comparator<? super Space> c) {
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
    public Space get(int index) {
        checkIndex(index);
        return getNodeByIndex(index).office;
    }

    // Замена офиса по индексу
    @Override
    public Space set(int index, Space office) {
        checkIndex(index);
        Space refOffice = null;
        if (office != null) {
            Node setNode = getNodeByIndex(index);
            refOffice = setNode.office;
            setNode.office = office;
        }
        return refOffice;
    }

    // Добавление оффиса по индексу
    @Override
    public void add(int index, Space office) {
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
    public Space remove(int index) {
        checkIndex(index);
        Space deleteOffice = null;
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
    public ListIterator<Space> listIterator() {
        ListIterator<Space> listIterator = new ListIterator<Space>() {

            @Override
            public boolean hasNext() {
                return head.next != null;
            }

            @Override
            public Space next() {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public Space previous() {
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
            public void set(Space office) {

            }

            @Override
            public void add(Space office) {

            }
        };
        return null;
    }

    @Override
    public ListIterator<Space> listIterator(int index) {
        return null;
    }

    @Override
    public List<Space> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public Spliterator<Space> spliterator() {
        return List.super.spliterator();
    }

    private class Node implements Serializable {
        private Node next;
        private Space office;

        Node(Node next, Space office) {
            this.next = next;
            this.office = office;
        }

        @Override
        public String toString() {
            return "Node{" + office + '}';
        }
    }
}
