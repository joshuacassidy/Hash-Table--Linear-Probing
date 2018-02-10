/**
 * Created by Josh on 10/02/2018.
 */
public interface IHashTable<Key, Value> {
    public Value get(Key key);
    public void put(Key key, Value value);
    public void resize(int newCapacity);
    public Value remove(Key key);
    public int hash(Key key);

}
