package com.corecs.javase.buildings.office.list.officeBuildingList;

import com.corecs.javase.buildings.office.OfficeFloor;

public class OfficeBuildingList {
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

    public int size() {
        return this.size;
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
}
