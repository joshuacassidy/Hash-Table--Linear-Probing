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

    public Value remove(Key key) {
        if(key==null) return null;
        int index = find(key);

        if (hashTable[index] == null || hashTable[index].getKey() == null) {
            return null;
        }

        Value temp = hashTable[index].getValue();
        hashTable[index].setKey(null);
        hashTable[index].setValue(null);

        while (hashTable[index].getKey() != null) {
            Key tempKey = hashTable[index].getKey();
            Value tempVal = hashTable[index].getValue();
            hashTable[index].setKey(null);
            hashTable[index].setValue(null);
            size--;
            put(tempKey, tempVal);
            index = (index+1) % capacity;
        }
        size--;
        if (size <= capacity /3) {
            resize(capacity/2);
        }
        return temp;
    }

    public void resize(int newCapacity) {
        HashItem[] temp = new HashItem[newCapacity];

        for (int i = 0,j=0; i < capacity; i++) {
            if(hashTable[i]  null && hashTable[i].getKey() != null) {
                temp[j] = hashTable[i];
                j++;
            }
        }
        hashTable = new HashItem[newCapacity];
        capacity= newCapacity;
        for (int i = 0; i < newCapacity; i++) {
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
        System.out.println("printing");

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
