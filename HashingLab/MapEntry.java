import java.util.Map.Entry;

/**
 * 
 * @author joelmanning
 * @version 1.0
 * @param <K>
 *            the type of key to hold
 * @param <V>
 *            the type of value to hold
 */
public class MapEntry<K, V> implements Entry<K, V>
{
    private K key;
    private V value;

    /**
     * @param key
     *            the initial key for the entry
     * @param value
     *            the initial value for the entry
     */
    public MapEntry(K key, V value)
    {
        super();
        this.key = key;
        this.value = value;
    }

    /**
     * Retrieves the key stored at the mapEntry
     * @return the key in the entry
     */
    public K getKey()
    {
        return key;
    }

    /**
     * Mutates the key stored at the MapEntry
     * @param key the key to set this entry's key to
     */
    public void setKey(K key)
    {
        this.key = key;
    }

    /**
     * Retrieves the value stored at the MapEntry
     * @return the value of the entry
     */
    public V getValue()
    {
        return value;
    }

    /**
     * Mutates the value stored at the MapEntry
     * @param val the value to set this entry's value to
     * @return the previous value in the entry
     */
    public V setValue(V val)
    {
        V past = value;
        value = val;
        return past;
    }

    /**
     * @param other the MapEntry to check if this is equal to
     * @return true if the MapEntries have the same key, false otherwise
     */
    public boolean equals(MapEntry other)
    {
        return key.equals(other.getKey());
    }
    
    /**
     * Retrieves a String representation of the MapEntry
     * @return A String representation of the MapEntry
     */
    public String toString() 
    {
        return key + "=" + value;
    }
}
