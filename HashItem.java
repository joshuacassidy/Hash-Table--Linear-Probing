/**
 * Created by Josh on 28/01/2018.
 */
public class HashItem<Key, Value> {
    public Key key;
    public Value value;

    public HashItem(Key key, Value value) {
        this.key = key;
        this.value = value;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public Key getKey() {
        return key;
    }

    public Value getValue() {
        return value;
    }
}
