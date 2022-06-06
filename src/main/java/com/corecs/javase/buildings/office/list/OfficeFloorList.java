package com.corecs.javase.buildings.office.list;

import com.corecs.javase.buildings.office.Office;

public class OfficeFloorList {
    private Node head;
    private int size;

    // Конструктор создаёт пустой лист
    public OfficeFloorList() {
    }

    // Метод добавления офиса
    public int size() {
        return size;
    }

    // Метод добавления офиса в начало
    public void addFirst(Office office) {
        head.setNext(new Node(head.getNext(), office));
        size++;
    }

    // Метод добавления офиса в конец
    public void addLast(Office office) {
        Node temp = new Node(head.getNext(), office);
        head.setNext(temp);
        head = head.getNext();
        size++;
    }

    // Если элементов нет, то создаёт головной элемент
    private void addHead(Office office) {
        head = new Node(null, office);
        head.setNext(head);
        size++;
    }

    // Метод добавления офиса
    public boolean addOffice(Office office) {
        if (head == null) {
            addHead(office);
        } else {
            addLast(office);
        }
        return true;
    }

    // Метод получения по индексу
    public Node get(int index) {
        Node ref = head.getNext();
        for (int i = 0; i < index; i++) {
            ref = ref.getNext();
        }
        return ref;
    }

    // Метод добавления офиса по индексу
    public boolean addOffice(int index, Office office) {
        if (index == 0 && head == null) {
            addHead(office);
        } else if (index == 0) {
            addFirst(office);
        } else if (index == (size)) {
            addLast(office);
        } else {
            Node ref = get(index - 1);
            ref.setNext(new Node(ref.getNext(), office));
            size++;
        }
        return true;
    }

    public void deleteFirst() {
        head.setNext(head.getNext().getNext());
        size--;
    }

    public boolean delete(int index) {
        if (index == 0) {
            deleteFirst();
        } else {
            Node ref = get(index - 1);
            ref.setNext(ref.getNext().getNext());
            size--;
        }
        return true;
    }

    public void print() {
        Node ref = head.getNext();
        for (int i = 0; i < size; i++) {
            System.out.println(ref.getOffice());
            ref = ref.getNext();
        }
    }
}
