package com.corecs.javase.buildings.office.list.officeBuildingList;

import com.corecs.javase.buildings.office.OfficeFloor;
import com.corecs.javase.exceptions.SpaceIndexOutOfBoundsException;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class OfficeBuildingList implements List<OfficeFloor>, Serializable {
    private Node head;

    private int size;

    public OfficeBuildingList() {
        initHead();
    }

    private void initHead() {
        this.head = new Node(null, null, null);
        this.head.setPrev(this.head);
        this.head.setNext(this.head.getPrev());
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
    public boolean contains(Object officeFloor) {
        return indexOf(officeFloor) != -1;
    }

    @Override
    public Iterator<OfficeFloor> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        OfficeFloor[] officeFloors = new OfficeFloor[size];
        int count = 0;
        for (Node ref = this.head.next; ref != this.head; ref = ref.next) {
            officeFloors[count++] = ref.officeFloor;
        }
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] array) {
        int count = 0;
        if (array.length < size) {
            array = (T[]) Array.newInstance(array.getClass().getComponentType(), this.size);
        }
        for (Node ref = this.head.next; ref != this.head; ref = ref.next) {
            array[count++] = (T) ref.officeFloor;
        }
        return array;
    }

    @Override
    public boolean add(OfficeFloor officeFloor) {
        addLast(officeFloor);
        return true;
    }

    @Override
    public boolean remove(Object officeFloor) {
        if (officeFloor != null) {
            int index = indexOf(officeFloor);
            remove(index);
        }
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> officeFloors) {
        for (Object object : officeFloors) {
            if (!contains(object)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends OfficeFloor> officeFloors) {
        if (officeFloors != null && !officeFloors.isEmpty()) {
            for (OfficeFloor officeFloor : officeFloors) {
                add(officeFloor);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends OfficeFloor> officeFloors) {
        checkIndexForAdd(index);
        if (officeFloors != null && !officeFloors.isEmpty()) {
            indexOf(index);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> officeFloors) {
        if (officeFloors != null && !officeFloors.isEmpty()) {
            for (Object officeFloor : officeFloors) {
                remove(officeFloor);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> officeFloors) {
        boolean modified = false;
        if (officeFloors != null && !officeFloors.isEmpty()) {
            Iterator<OfficeFloor> iterator = iterator();
            while (iterator.hasNext()) {
                OfficeFloor offices = iterator.next();
                if (!officeFloors.contains(offices)) {
                    modified = true;
                    remove(offices);
                }
            }
        }
        return modified;
    }

    @Override
    public void clear() {
        initHead();
        this.size = 0;
    }

    @Override
    public OfficeFloor get(int index) {
        checkIndex(index);
        return getNodeByIndex(index).officeFloor;
    }

    @Override
    public OfficeFloor set(int index, OfficeFloor element) {
        checkIndex(index);
        OfficeFloor refOfficeFloor = null;
        if (element != null) {
            Node setNode = getNodeByIndex(index);
            refOfficeFloor = setNode.officeFloor;
            setNode.officeFloor = element;
        }
        return refOfficeFloor;
    }

    @Override
    public void add(int index, OfficeFloor element) {
        checkIndexForAdd(index);
        if (element != null) {
            if (index == 0) {
                addFirst(element);
            } else if (index == size) {
                addLast(element);
            } else {
                Node refNode = getNodeByIndex(index);
                Node newNode = new Node(refNode, refNode.prev, element);
                refNode.prev.next = newNode;
                refNode.prev = newNode;
                size++;
            }
        }
    }

    @Override
    public OfficeFloor remove(int index) {
        checkIndex(index);
        Node deleteNode = getNodeByIndex(index);
        OfficeFloor deleteOfficeFloor = null;
        if (index == 0) {
            removeFirst();
        } else if (index == (size - 1)) {
            removeLast();
        } else {
            deleteOfficeFloor = deleteNode.officeFloor;
            deleteNode.prev.next = deleteNode.next;
            deleteNode.next.prev = deleteNode.prev;
            size--;
        }
        return deleteOfficeFloor;
    }

    @Override
    public int indexOf(Object officeFloor) {
        int firstIndex = -1;
        if (officeFloor == null) {
            return -1;
        }
        for (Node ref = this.head.next; ref != this.head; ref = ref.next) {
            firstIndex++;
            if (officeFloor.equals(ref.officeFloor)) {
                return firstIndex;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object officeFloor) {
        int lastIndex = -1;
        if (officeFloor != null) {
            int index = -1;
            for (Node ref = head.next; ref != this.head; ref = ref.next) {
                index++;
                if (officeFloor.equals(ref)) {
                    lastIndex = index;
                }
            }
        }
        return lastIndex;
    }

    @Override
    public ListIterator<OfficeFloor> listIterator() {
        return null;
    }

    @Override
    public ListIterator<OfficeFloor> listIterator(int index) {
        return null;
    }

    @Override
    public List<OfficeFloor> subList(int fromIndex, int toIndex) {
        return null;
    }

    // Проверка индекса
    private void checkIndex(int index) {
        if (index >= this.size || index < 0) {
            throw new SpaceIndexOutOfBoundsException("Incorrect index of officeFloor...");
        }
    }

    // Проверка индекса для методов добавления
    private void checkIndexForAdd(int index) {
        if (index > this.size || index < 0) {
            throw new SpaceIndexOutOfBoundsException("Incorrect index of officeFloor...");
        }
    }

    private void addFirst(OfficeFloor list) {
        if (list != null) {
            Node first = new Node(this.head.getNext(), this.head, list);
            this.head.getNext().setPrev(first);
            this.head.setNext(this.head.getNext().getPrev());
            this.size++;
        }
    }

    private void addLast(OfficeFloor officeFloor) {
        if (officeFloor != null) {
            Node last = new Node(this.head, this.head.getPrev(), officeFloor);
            this.head.getPrev().setNext(last);
            this.head.setPrev(this.head.getPrev().getNext());
            this.size++;
        }
    }

    private OfficeFloor removeFirst() {
        OfficeFloor oldElement = this.head.getNext().getList();
        Node delete = this.head.getNext();
        this.head.setNext(delete.getNext());
        delete.getNext().setPrev(this.head);
        this.size--;
        return oldElement;
    }

    private OfficeFloor removeLast() {
        OfficeFloor oldElement = this.head.getPrev().getList();
        Node delete = this.head.getPrev();
        this.head.setPrev(delete.getPrev());
        delete.getPrev().setNext(this.head);
        this.size--;
        return oldElement;
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

    private class Node implements Serializable {
        private Node next;
        private Node prev;
        private OfficeFloor officeFloor;

        Node(Node next, Node prev, OfficeFloor officeFloor) {
            this.next = next;
            this.prev = prev;
            this.officeFloor = officeFloor;
        }

        Node getNext() {
            return next;
        }

        void setNext(Node next) {
            this.next = next;
        }

        Node getPrev() {
            return prev;
        }

        void setPrev(Node prev) {
            this.prev = prev;
        }

        OfficeFloor getList() {
            return officeFloor;
        }

        void setList(OfficeFloor list) {
            this.officeFloor = list;
        }

        @Override
        public String toString() {
            return "List{list=" + officeFloor + '}';
        }
    }
}
