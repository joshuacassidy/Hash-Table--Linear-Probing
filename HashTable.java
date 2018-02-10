public class HashTable<Key, Value> implements IHashTable<Key, Value> {
    private int capacity, size;
    private HashItem<Key, Value>[] hashTable;

    public HashTable() {
        this.capacity = 10;
        hashTable = new HashItem[capacity];
    }

    public HashTable(int capacity) {
        this.capacity = capacity;
        this.hashTable = new HashItem[capacity];
    }

    public int find(Key key) {
        int i = hash(key);
        while (hashTable[i] != null && hashTable[i].getKey() != key) {
            i = (i+1) % capacity;
        }
        return i;
    }

    public Value get(Key key){
        int i = find(key);
        if (hashTable[i] != null) {
            return hashTable[i].getValue();
        } else {
            return null;
        }
    }

    public void put(Key key, Value value) {
        int i = find(key);
        if (hashTable[i] != null) {
            hashTable[i].setValue(value);
            return;
        }
        hashTable[i] = new HashItem(key, value);

        size++;

        if (size >= capacity * 0.75) {
            resize(2*capacity);
        }
    }


    public void resize(int newCapacity) {
        HashTable<Key, Value> table = new HashTable<>(newCapacity);

        for(int i =0; i <capacity; i++) {
            if (hashTable[i] != null) {
                table.put(hashTable[i].getKey(), hashTable[i].getValue());
            }
        }

        hashTable = table.getHashTable();
        capacity = table.getCapacity();

    }

    public Value remove(Key key) {
        int i = hash(key);
        if(hashTable[i] == null) {
            return null;
        }
        System.out.println(hashTable[0]);
        while (hashTable[i].getKey() == null || !hashTable[i].getKey().equals(key)) {
            i = (i+1) % capacity;


        }
        Value temp = hashTable[i].getValue();
        hashTable[i].setKey(null);
        hashTable[i].setValue(null);


        size--;

        return temp;
    }



    public HashItem[] getHashTable() {
        return hashTable;
    }

    public int getCapacity() {
        return capacity;
    }

    public void traverse() {
        System.out.println("printing");

        for (int i = 0; i < capacity; i = i+1 % capacity) {
            System.out.print(hashTable[i] == null ? "" : hashTable[i].getKey() + " " + hashTable[i].getValue() + "\n");
        }
    }

    public int hash(Key key) {
        return Math.abs(key.hashCode()) % capacity;
    }

}
