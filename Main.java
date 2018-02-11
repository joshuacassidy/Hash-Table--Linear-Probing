import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        HashTable<String, Integer> hashTable = new HashTable<>();
        hashTable.put("Josh", 21);
        hashTable.put("A", 1);
        hashTable.put("B", 23);
        hashTable.put("C", 21);
        hashTable.put("D", 21);
        hashTable.put("E", 23);
        hashTable.put("F", 23);
        hashTable.put("G", 21);
        hashTable.put("H", 21);
        hashTable.put("I", 21);
        hashTable.put("J", 21);
        hashTable.put("K", 21);
        hashTable.put("L", hashTable.get("C")*22);
        hashTable.put("Josh", hashTable.get("Josh")+1);
        System.out.println("Found the value for Josh: " +hashTable.get("Josh"));
        System.out.println("Found the value for C: " +hashTable.get("C"));
        System.out.println("Found the value for D: " + hashTable.get("D"));
        System.out.println("A Removed: " + hashTable.remove("A"));
        System.out.println("B Removed: " + hashTable.remove("B"));
        System.out.println("C Removed: " + hashTable.remove("C"));
        System.out.println("E Removed: " + hashTable.remove("E"));
        System.out.println("D Removed: " + hashTable.remove("D"));
        System.out.println("F Removed: " + hashTable.remove("F"));
        System.out.println("Found the value for G: " +hashTable.get("G"));
        System.out.println(hashTable.getCapacity());
        hashTable.traverse();

        System.out.println("G Removed: " + hashTable.remove("G"));

        System.out.println("H Removed: " + hashTable.remove("H"));
        System.out.println(hashTable.getCapacity());

        System.out.println("I Removed: " + hashTable.remove("I"));
        System.out.println("J Removed: " + hashTable.remove("J"));
        hashTable.remove("Josh");
        System.out.println("L Removed: " + hashTable.remove("L"));
        System.out.println("K Removed: " + hashTable.remove("K"));
        hashTable.traverse();



        System.out.println(hashTable.getSize());
    }

}
