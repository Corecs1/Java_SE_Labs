package com.corecs.javase.buildings.office.list.officeBuildingList;

import com.corecs.javase.buildings.interfaces.Floor;
import com.corecs.javase.exceptions.SpaceIndexOutOfBoundsException;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class OfficeBuildingList implements List<Floor>, Serializable {
    private Node head;

    private int size;

    public OfficeBuildingList() {
        initHead();
    }

    public OfficeBuildingList(Collection<Floor> list) {
        this();
        addAll(list);
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
    public Iterator<Floor> iterator() {
        Iterator<Floor> iter = new Iterator<Floor>() {
            Node ref = head;

            @Override
            public boolean hasNext() {
                if (ref == null) return false;
                if (ref.next == null) return false;
                return !ref.next.equals(head);
            }

            @Override
            public Floor next() {
                Node current = ref.next;
                ref = ref.next;
                return current.officeFloor;
            }
        };
        return iter;
    }

    @Override
    public Object[] toArray() {
        Floor[] officeFloors = new Floor[size];
        int count = 0;
        for (Node ref = this.head.next; ref != this.head; ref = ref.next) {
            officeFloors[count++] = ref.officeFloor;
        }
        return officeFloors;
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
    public boolean add(Floor officeFloor) {
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
    public boolean addAll(Collection<? extends Floor> officeFloors) {
        if (officeFloors != null && !officeFloors.isEmpty()) {
            for (Floor officeFloor : officeFloors) {
                add(officeFloor);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Floor> officeFloors) {
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
            Iterator<Floor> iterator = iterator();
            while (iterator.hasNext()) {
                Floor offices = iterator.next();
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
    public Floor get(int index) {
        checkIndex(index);
        return getNodeByIndex(index).officeFloor;
    }

    @Override
    public Floor set(int index, Floor element) {
        checkIndex(index);
        Floor refOfficeFloor = null;
        if (element != null) {
            Node setNode = getNodeByIndex(index);
            refOfficeFloor = setNode.officeFloor;
            setNode.officeFloor = element;
        }
        return refOfficeFloor;
    }

    @Override
    public void add(int index, Floor element) {
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
    public Floor remove(int index) {
        checkIndex(index);
        Node deleteNode = getNodeByIndex(index);
        Floor deleteOfficeFloor = null;
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
    public ListIterator<Floor> listIterator() {
        return null;
    }

    @Override
    public ListIterator<Floor> listIterator(int index) {
        return null;
    }

    @Override
    public List<Floor> subList(int fromIndex, int toIndex) {
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

    private void addFirst(Floor list) {
        if (list != null) {
            Node first = new Node(this.head.getNext(), this.head, list);
            this.head.getNext().setPrev(first);
            this.head.setNext(this.head.getNext().getPrev());
            this.size++;
        }
    }

    private void addLast(Floor officeFloor) {
        if (officeFloor != null) {
            Node last = new Node(this.head, this.head.getPrev(), officeFloor);
            this.head.getPrev().setNext(last);
            this.head.setPrev(this.head.getPrev().getNext());
            this.size++;
        }
    }

    private Floor removeFirst() {
        Floor oldElement = this.head.getNext().getList();
        Node delete = this.head.getNext();
        this.head.setNext(delete.getNext());
        delete.getNext().setPrev(this.head);
        this.size--;
        return oldElement;
    }

    private Floor removeLast() {
        Floor oldElement = this.head.getPrev().getList();
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
        private Floor officeFloor;

        Node(Node next, Node prev, Floor officeFloor) {
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

        Floor getList() {
            return officeFloor;
        }

        void setList(Floor list) {
            this.officeFloor = list;
        }

        @Override
        public String toString() {
            return "List{list=" + officeFloor + '}';
        }
    }
}
