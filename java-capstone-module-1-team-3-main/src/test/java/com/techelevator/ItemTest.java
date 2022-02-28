package com.techelevator;

import junit.framework.TestCase;

import java.math.BigDecimal;

public class ItemTest extends TestCase {

    public void testTestGetName() {
        Item item = new Item ("Rock", "candy", "A1", new BigDecimal("1.05") );
        assertEquals("Rock", item.getName());
    }

    public void testGetLocation() {
        Item item = new Item ("Rock", "candy", "B3", new BigDecimal("1.05"));
        assertEquals("B3", item.getLocation());
    }

    public void testGetPrice() {
        Item item = new Item ("Rock", "candy", "B3", new BigDecimal("1.05") );
        assertEquals(new BigDecimal("1.05"), item.getPrice());
    }

    public void testGetType() {
        Item item = new Item ("Rock", "candy", "B3", new BigDecimal("1.05") );
        assertEquals("candy", item.getType());
    }

    public void testTestSetName() {
        String expected = "Lays";
        Item item = new Item ();
        item.setName(expected);
        assertEquals(item.getName(), expected);
    }

    public void testSetLocation() {
        String expected = "A1";
        Item item = new Item ();
        item.setLocation(expected);
        assertEquals(item.getLocation(), expected);
    }

    public void testSetPrice() {
        BigDecimal expected = new BigDecimal("1.00");
        Item item = new Item ();
        item.setPrice(expected);
        assertEquals(item.getPrice(), expected);
    }

    public void testSetType() {
        String expected = "chip";
        Item item = new Item ();
        item.setType(expected);
        assertEquals(item.getType(), expected);
    }
}