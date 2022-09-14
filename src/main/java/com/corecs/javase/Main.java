package com.corecs.javase;

import com.corecs.javase.buildings.interfaces.Space;
import com.corecs.javase.buildings.office.Office;
import com.corecs.javase.buildings.office.OfficeBuilding;
import com.corecs.javase.buildings.office.OfficeFloor;
import com.corecs.javase.buildings.office.list.officeFloorList.OfficeFloorList;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Office office1 = new Office();
        Office office2 = new Office(100);
        Office office3 = new Office(400, 5);
        Office[] offices = {office3, office2, office1};

        OfficeFloor officeFloor = new OfficeFloor(offices);
        System.out.println(officeFloor.getAmountOfSpaces());
        officeFloor.deleteSpace(0);
        System.out.println(officeFloor.getAmountOfSpaces());
        System.out.println("----------------------------------------------");

        List<Office> officeList1 = new ArrayList<>();
        officeList1.add(office1);
        officeList1.add(office2);
        officeList1.add(office3);

        List<Space> officeList2 = new ArrayList<>();
        officeList2.add(new Office(500));
        officeList2.add(new Office(450));
        officeList2.add(new Office(600));
        officeList2.add(new Office(1000));
        officeList2.add(new Office(700));

        List<Space> list = new OfficeFloorList(officeList2);
        for (Space space : list) {
            System.out.println(space);
        }

        OfficeFloor officeFloor1 = new OfficeFloor(officeList2);
        System.out.println(officeFloor1.getTotalSpaceArea());
        System.out.println(officeFloor1.getTotalAmountOfRooms());
        System.out.println(officeFloor1.getBestSpace());
    }
}
