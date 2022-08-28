package com.corecs.javase.buildings.office.list.officeBuildingList;

import com.corecs.javase.buildings.office.Office;
import com.corecs.javase.buildings.office.OfficeFloor;

import java.util.*;
import java.util.function.UnaryOperator;

public class OfficeBuildingListOld implements List<Office> {
    private Node head;
    private int size;

    public OfficeBuildingListOld() {
        initHead();
    }

    private void initHead() {
        this.head = new Node(null, null, null);
        this.head.setPrev(this.head);
        this.head.setNext(this.head.getPrev());
    }

    public Node get(int index) {
        Node resultNode = null;
        int half = this.size / 2;
        int count = (half > index) ? index : (this.size - index - 1);
        for (Node node = ((half > index) ? this.head.getNext() : this.head.getPrev());
             node != this.head; node = (half > index ? node.getNext() : node.getPrev())) {
            if (count-- == 0) {
                resultNode = node;
                break;
            }
        }
        return resultNode;
    }

    @Override
    public Office set(int index, Office element) {
        return null;
    }

    @Override
    public void add(int index, Office element) {

    }

    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<Office> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(Office office) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Office> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Office> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void replaceAll(UnaryOperator<Office> operator) {
        List.super.replaceAll(operator);
    }

    @Override
    public void sort(Comparator<? super Office> c) {
        List.super.sort(c);
    }

    @Override
    public void clear() {

    }

    public boolean addBuilding(OfficeFloor list) {
        addLast(list);
        return true;
    }

    private void addFirst(OfficeFloor list) {
        if (list != null) {
            Node first = new Node(this.head.getNext(), this.head, list);
            this.head.getNext().setPrev(first);
            this.head.setNext(this.head.getNext().getPrev());
            this.size++;
        }
    }

    private void addLast(OfficeFloor list) {
        if (list != null) {
            Node last = new Node(this.head, this.head.getPrev(), list);
            this.head.getPrev().setNext(last);
            this.head.setPrev(this.head.getPrev().getNext());
            this.size++;
        }
    }

    public void addBuilding(int index, OfficeFloor list) {
        if (list != null) {
            if (index == 0) {
                addFirst(list);
            } else if (index == this.size) {
                addLast(list);
            } else {
                Node node = get(index);
                if (node != null) {
                    Node newNode = new Node(node, node.getPrev(), list);
                    node.getPrev().setNext(newNode);
                    node.setPrev(node.getPrev().getNext());
                    this.size++;
                }
            }
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

    public OfficeFloor remove(int index) {
        OfficeFloor element = null;
        Node delete = get(index);
        if (index == 0) {
            removeFirst();
        } else if (index == (size - 1)) {
            removeLast();
        } else {
            element = delete.getList();
            delete.getNext().setPrev(delete.getPrev());
            delete.getPrev().setNext(delete.getNext());
            this.size--;
        }
        return element;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<Office> listIterator() {
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

    public boolean set(int index, OfficeFloor list) {
        Node update = get(index);
        update.setList(list);
        return true;
    }

    public void print() {
        Node ref = head.getNext();
        for (int i = 0; i < size; i++) {
            ref.getList().print();
            ref = ref.getNext();
        }
    }

    private class Node {
        private Node next;
        private Node prev;
        private OfficeFloor list;

        public Node(Node next, Node prev, OfficeFloor list) {
            this.next = next;
            this.prev = prev;
            this.list = list;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public OfficeFloor getList() {
            return list;
        }

        public void setList(OfficeFloor list) {
            this.list = list;
        }

        @Override
        public String toString() {
            return "List{list=" + list + '}';
        }
    }
}
