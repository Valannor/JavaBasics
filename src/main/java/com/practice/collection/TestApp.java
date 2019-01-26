package com.practice.collection;

public class TestApp {

    public static void main(String[] args) {

//        testArrayList();
//        testLinkedList();
        testHashMap();
    }

    private static void testHashMap() {

        MyHashMap<String, String> map = new MyHashMap<>();
        map.put("Key 0", "Value 0");
        map.put("Key 1", "Value 1");
        map.put("Key 2", "Value 2");
        map.put("Key 3", "Value 3");
        map.put("Key 4", "Value 4");
        map.put(null, "Value 4");
        System.out.println("Map after initialisation: " + map);

        map.put("Key 0", "Value 9");
        System.out.println("Map after second put for key \"Key 0\" : " + map);

        System.out.println("Map size: " + map.size());

        System.out.println("Get for not existing key \"Key\": " + map.get("Key"));
        System.out.println("Get for existing key \"Key 0\": " + map.get("Key 0"));

        System.out.println("Return of removal result for not existing key \"Key\": " + map.remove("Key"));
        System.out.println("Return of removal result for existing key \"Key 0\": " + map.remove("Key 0"));

        System.out.println("Map after all operations: " + map);
        System.out.println("Map size after all operations: " + map.size());
    }

    public static void testArrayList() {
        String[] strings = {"a", "b", "c", "d", "e"};

        MyArrayList<String> list = new MyArrayList<>();
        list.add("New 1");
        list.add("New 2");
        list.add("New 3");
        list.add("New 4");
        list.add("New 5");
        list.add("New 6");
        list.add("New 7");
        list.add("New 8");
        list.add("New 9");
        list.add("New 10");
        list.add("New 11");
        list.add("New 12");

        System.out.println(list);
        list.add(5, "New 0");
        System.out.println(list);

//        System.out.println(list.size());

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    public static void testLinkedList() {
        MyLinkedList<String> list = new MyLinkedList<>();

        list.add("New 1");
        list.add("New 2");
        list.add("New 3");
        list.add("New 4");
        list.add("New 5");

        list.add(0, "New 0");

        list.remove(0);

//        System.out.println(list);

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

}
