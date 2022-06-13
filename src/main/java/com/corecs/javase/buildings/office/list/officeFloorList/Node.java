package com.corecs.javase.buildings.office.list.officeFloorList;

import com.corecs.javase.buildings.office.Office;

public class Node {
    private Node next;
    private Office office;

    public Node() {
    }

    public Node(Node next, Office office) {
        this.next = next;
        this.office = office;
    }

    protected Node getNext() {
        return next;
    }

    protected void setNext(Node next) {
        this.next = next;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    @Override
    public String toString() {
        return "Node{" + office + '}';
    }
}
