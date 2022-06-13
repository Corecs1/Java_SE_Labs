package com.corecs.javase.buildings.office.list.officeBuildingList;

import com.corecs.javase.buildings.office.OfficeFloor;

public class Node {
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
