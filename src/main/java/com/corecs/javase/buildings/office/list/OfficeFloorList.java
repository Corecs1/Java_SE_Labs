package com.corecs.javase.buildings.office.list;

import com.corecs.javase.buildings.office.Office;

public class OfficeFloorList {
    private Node head;
    private int size;

    // Конструктор создаёт пустой лист
    public OfficeFloorList() {
    }

    public int size() {
        return size;
    }

    // Метод добавления офиса
    public void addOffice(Office office) {
        if (head == null) {
            head = new Node(null, office);
            head.setNext(head);
            size++;
        } else {
            Node temp = new Node(head.getNext(), office);
            head.setNext(temp);
            head = head.getNext();
            size++;
        }
    }

    // Метод получения по индексу

    // Метод добавления по индексу

    public void print() {
        Node ref = head;
        for (int i = 0; i < size; i++) {
            System.out.println(ref.getOffice());
            ref = ref.getNext();
        }
    }
}
