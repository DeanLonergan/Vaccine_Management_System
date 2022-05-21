package com.vaccine_management_system;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

class CustomListTest {
    CustomList<VaccinationCentre> vcl;
    VaccinationCentre vc1 = new VaccinationCentre("Name1", "Location1", "eirCode1", "100");
    VaccinationCentre vc2 = new VaccinationCentre("Name2", "Location2", "eirCode2", "100");
    VaccinationCentre vc3 = new VaccinationCentre("Name3", "Location3", "eirCode3", "100");
    VaccinationCentre vc4 = new VaccinationCentre("Name4", "Location4", "eirCode4", "100");

    @BeforeEach
    void setUp() {
        vcl = new CustomList<>();
        vcl.add(vc2);
        vcl.add(vc1);
    }

    @AfterEach
    void tearDown() {
        vcl.head = null;
    }

    @Test
    void addFirstTest() {
        assertEquals(vcl.get(0), vc1);
        vcl.addFirst(vc3);
        assertEquals(vcl.get(0), vc3);
    }

    @Test
    void addLastTest() {
        assertEquals(vcl.get(1), vc2);
        vcl.addLast(vc3);
        assertEquals(vcl.get(2), vc3);
        vcl.addFirst(vc4);
        assertEquals(vcl.get(3), vc3);
    }

    @Test
    void sizeTest() {
        assertEquals(2, vcl.size());
        vcl.add(vc3);
        assertEquals(3, vcl.size());
        vcl.add(vc4);
        assertEquals(4, vcl.size());
        vcl.remove(vc4);
        assertEquals(3, vcl.size());
        vcl.remove(vc3);
        vcl.remove(vc2);
        vcl.remove(vc1);
        assertEquals(0, vcl.size());
    }

    @Test
    void isEmptyTest() {
        assertFalse(vcl.isEmpty());
    }

    @Test
    void containsTest() {
        assertFalse(vcl.contains(vc3));
        vcl.add(vc3);
        assertTrue(vcl.contains(vc3));
    }

    @Test
    void removeTest() {
        assertEquals(vcl.get(0), vc1);
        vcl.addFirst(vc3);
        assertEquals(vcl.get(0), vc3);
        assertTrue(vcl.remove(vc3));
        assertFalse(vcl.contains(vc3));
        vcl.addLast(vc4);
        assertTrue(vcl.contains(vc4));
        assertTrue(vcl.remove(vc4));
        assertFalse(vcl.contains(vc4));
        vcl.remove(vc1);
        vcl.remove(vc2);
        vcl.remove(vc3);
        vcl.remove(vc4);
        assertEquals(0, vcl.size());

        vcl.addFirst(vc3);
        vcl.addFirst(vc2);
        vcl.addFirst(vc1);
        assertEquals(3, vcl.size());
        assertEquals(vcl.get(2), vc3);
        assertEquals(vcl.get(1), vcl.remove(1));
        assertEquals(2, vcl.size());
        assertFalse(vcl.contains(vc2));
        vcl.addFirst(vc2);
        assertEquals(3, vcl.size());
        assertEquals(vcl.get(0), vcl.remove(0));
        assertEquals(2, vcl.size());
        assertFalse(vcl.contains(vc2));
        assertEquals(vcl.get(1), vcl.remove(1));
        assertEquals(1, vcl.size());
        assertEquals(vcl.get(0), vcl.remove(0));
        assertEquals(0, vcl.size());
    }


    @Test
    void clearTest() {
        assertEquals(2, vcl.size());
        vcl.add(vc3);
        assertEquals(3, vcl.size());
        vcl.add(vc4);
        assertEquals(4, vcl.size());
        vcl.clear();
        assertEquals(0, vcl.size());
    }

    @Test
    void getTest() {
        assertEquals(vc1,vcl.get(0));
        assertEquals(vc2, vcl.get(1));
        vcl.add(vc3);
        assertEquals(vc3,vcl.get(0));
    }

    @Test
    void setTest() {
        assertEquals(vc1, vcl.get(0));
        assertEquals(vc2, vcl.get(1));
        assertEquals(vc3, vcl.set(0,vc3));
        assertEquals(vc4, vcl.set(1,vc4));
        assertEquals(vc3, vcl.get(0));
        assertEquals(vc4, vcl.get(1));
        assertFalse(vcl.contains(vc1));
        assertFalse(vcl.contains(vc2));
    }

    @Test
    void addTest() {
        assertEquals(vcl.get(0), vc1);
        vcl.add(0, vc3);
        vcl.add(1, vc4);
        assertEquals(vcl.get(0), vc3);
        assertEquals(vcl.get(1), vc4);
        assertEquals(vcl.get(2), vc1);
        assertEquals(vcl.get(3), vc2);
        assertNull(vcl.get(4));
    }


    @Test
    void indexOfTest() {
        vcl.add(0, vc3);
        vcl.add(1, vc4);
        assertEquals(0,vcl.indexOf(vc3));
        assertEquals(1,vcl.indexOf(vc4));
        vcl.clear();
        assertEquals(-1,vcl.indexOf(vc3));
        vcl.add(vc4);
        vcl.add(vc3);
        vcl.add(vc2);
        vcl.add(vc1);
        assertEquals(0,vcl.indexOf(vc1));
        assertEquals(1,vcl.indexOf(vc2));
        assertEquals(2,vcl.indexOf(vc3));
        assertEquals(3,vcl.indexOf(vc4));
    }

    @Test
    void lastIndexOfTest() {
    }

    @Test
    void addAllTest() {
    }

    @Test
    void retainAllTest() {
    }

    @Test
    void removeAllTest() {
    }

    @Test
    void containsAllTest() {
    }

    @Test
    void iteratorTest() {
    }

    @Test
    void listIteratorTest() {
    }


    @Test
    void subListTest() {
    }

    @Test
    void toArrayTest() {
    }
}