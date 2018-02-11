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
        if (key == null) {
            throw new KeyIsNullException("Key is null");
        } else {
            int index = hash(key);
            while (hashTable[index] != null && hashTable[index].getKey() != key) {
                index = (index + 1) % capacity;
            }
            return index;
        }
    }

    public Value get(Key key){
        int index = find(key);
        return hashTable[index] != null ? hashTable[index].getValue() : null;
    }

    public void put(Key key, Value value) {
        int index = find(key);
        if (hashTable[index] != null) {
            hashTable[index].setValue(value);
            return;
        }
        hashTable[index] = new HashItem<>(key, value);

        size++;

        if (size >= capacity * 0.75) {
            resize(2*capacity);
        }
    }

    public Value remove(Key key) {
        int index = find(key);

        if (hashTable[index] == null || hashTable[index].getKey() == null) {
            return null;
        }

        Value temp = hashTable[index].getValue();
        hashTable[index].setKey(null);
        hashTable[index].setValue(null);
        readjustTable(index);
        size--;
        if (size <= capacity /3) {
            resize(capacity/2);
        }
        return temp;
    }

    public void readjustTable(int index) {
        while (hashTable[index].getKey() != null) {
            Key tempKey = hashTable[index].getKey();
            Value tempVal = hashTable[index].getValue();
            hashTable[index].setKey(null);
            hashTable[index].setValue(null);
            size--;
            put(tempKey, tempVal);
            index = (index+1) % capacity;
        }
    }


    public void resize(int newCapacity) {
        HashItem[] temp = hashTable;
        hashTable = new HashItem[newCapacity];
        capacity= newCapacity;
        for (int i = 0; i < temp.length; i++) {
            if(temp[i] != null && temp[i].getKey() != null) {
                put((Key) temp[i].getKey(), (Value) temp[i].getValue());
                size--;
            }
        }
    }


    public HashItem[] getHashTable() {
        return hashTable;
    }

    public int getCapacity() {
        return capacity;
    }

    public void traverse() {
        for (int i = 0; i < capacity; i = i+1 % capacity) {
            System.out.print(hashTable[i] == null ? "" : hashTable[i].getKey() + " " + hashTable[i].getValue() + "\n");
        }
    }

    public int getSize() {
        return size;
    }

    public int hash(Key key) {
        return Math.abs(key.hashCode()) % capacity;
    }

}
