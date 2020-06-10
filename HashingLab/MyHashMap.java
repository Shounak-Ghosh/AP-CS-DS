import java.util.*;
/**
 * MyHashMap acts like a map.
 * 
 * @author Anu Datar
 * @author Shounak Ghosh
 * @version 1.25.2018
 * @param <K> the type of key
 * @param <V> the type of value
 */
public class MyHashMap<K, V> implements Map<K, V>
{
    private static final int NUM_BUCKETS = 5;
    // the map is an array of MapEntry LinkedLists
    private ListNode<MapEntry<K, V>>[] buckets;
    private int size;

    /**
     * a constructor
     */
    public MyHashMap()
    {
        buckets = new ListNode[NUM_BUCKETS];
        size = 0;

    }

    /**
     * @param obj the object to find the bucket index for
     * @return the correct bucket index for that object
     */
    private int toBucketIndex(Object obj)
    {
        return Math.abs(obj.hashCode()) % NUM_BUCKETS;
    }

    /**
     * Retrieves the current number of elements stored in the MyHashMap 
     * @return how many objects are stored within the MyHashMap 
     */
    public int size()
    {
        return size;
    }

    /**
     * Checks whether the map is empty
     * @return true if there are no elements in the map; false otherwise
     */
    public boolean isEmpty()
    {
        return size == 0;
    }

    @Override
    /**
     * Checks whether a key is present within the map
     * @param key the key to be searched for within the map
     * @return true if the key is present in the map; false otherwise
     */
    public boolean containsKey(Object key)
    {
        //throw new RuntimeException("IMPLEMENT ME!");

        return keySet().contains(key);
    }

    /**
     * Checks whether a value is present within the map 
     * @param value the value to be searched for within the map
     * @return true if the value is present in the map; false otherwise
     */
    public boolean containsValue(Object value)
    {
        //throw new RuntimeException("IMPLEMENT ME!");

        return values().contains(value);
    }

    /**
     * Retrieves the value paired with a given key
     * @param key the key of the value to be returned
     * @return The value associated with the key, or
     *         null if the key is not found within the map
     */
    public V get(Object key)
    {
        //throw new RuntimeException("IMPLEMENT ME!");
        int index = toBucketIndex(key);
        ListNode<MapEntry<K, V>> head = buckets[index];

        while (head != null) 
        {
            if (head.getValue().getKey().equals(key)) 
            {
                return head.getValue().getValue();
            }
            head = head.getNext();
        }
        return null;
    }

    /**
     * Adds a key-value pairing if the key 
     * is not already present within the map,
     * or replaces the value mapped to the key if the key is already present
     * @param key the key to be added to the map if not already present
     * @param value the value to be associated with the key
     * @return the value originally paired with the key, 
     *         or null if the key was added to the map
     */
    public V put(K key, V value)
    {
        //throw new RuntimeException("IMPLEMENT ME!");
        int index = toBucketIndex(key);
        //System.out.println(index);
        ListNode<MapEntry<K, V>> head = buckets[index];

        if (containsKey(key)) 
        // since the key already exists, 
        //replace the value and return the old value
        {
            while (head != null) 
            {
                if (head.getValue().getKey().equals(key)) 
                {
                    V old = head.getValue().getValue();
                    head.getValue().setValue(value);
                    return old;
                }
            }
        }
        else 
        {
            buckets[index] = new ListNode(new MapEntry<K, V> (key, value), 
                                          buckets[index]);
        }
        size++;
        return null;
    }

    /**
     * Removes a key-value mapping from the map
     * @param key the key to be removed from the map
     * @return the value stored with the key
     */
    public V remove(Object key)
    {
        //throw new RuntimeException("IMPLEMENT ME!");
        int index = toBucketIndex(key);
        ListNode<MapEntry<K, V>> head = buckets[index];
        ListNode<MapEntry<K, V>> prev = null;

        while (head != null) 
        {
            if (head.getValue().getKey().equals(key)) 
            {
                break;
            }
            prev = head;
            head = head.getNext();
        }

        if (head == null) 
        {
            return null;
        }

        size--;

        if (prev != null) 
        {
            prev.setNext(head.getNext());
        }
        else 
        {
            buckets[index] = head.getNext();
        }

        return head.getValue().getValue();
    }

    /**
     * Copies all of the mappings from the specified map to this map.
     * @param m figure this out
     */
    public void putAll(Map<? extends K, ? extends V> m)
    {
        for (K key : m.keySet())
        {
            put(key, m.get(key));
        }
    }

    /**
     * Removes all elements from the map
     */
    public void clear()
    {
        for (int i = 0; i < NUM_BUCKETS; i++)
        {
            buckets[i] = null;
        }
    }

    /**
     * Retrieves a set of all keys stored in the map
     * @return a set of all keys within the map
     */
    public Set<K> keySet()
    {
        //throw new RuntimeException("IMPLEMENT ME!");
        Set<K> keys = new HashSet();

        for (int i = 0; i < buckets.length; i++) 
        {
            ListNode<MapEntry<K, V>> head = buckets[i];
            while (head != null) 
            {
                keys.add(head.getValue().getKey());
                head = head.getNext();
            }
        }
        //System.out.println(keys);
        return keys;

    }

    /**
     * Retrieves a collection of all values stored in the map
     * @return A collection of all the values stored in the map
     */
    public Collection<V> values()
    {
        //throw new RuntimeException("IMPLEMENT ME!");
        ArrayList<V> values = new ArrayList<V>();

        for (int i = 0; i < buckets.length; i++) 
        {
            ListNode<MapEntry<K, V>> head = buckets[i];
            while (head != null) 
            {
                values.add(head.getValue().getValue());
                head = head.getNext();
            }
        }
        return values;

    }

    /**
     * Retrieves a Set representation of the Map
     * @return A Set representation of the Map
     */
    @Override
    public Set<java.util.Map.Entry<K, V>> entrySet()
    {
        //throw new RuntimeException("IMPLEMENT ME!");

        Set<java.util.Map.Entry<K, V>> mappings = new HashSet();

        for (int i = 0; i < buckets.length; i++) 
        {
            ListNode<MapEntry<K, V>> head = buckets[i];
            while (head != null) 
            {
                mappings.add(head.getValue());
                head = head.getNext();
            }
        }
        return mappings;

    }

    /**
     * Retrieves a String representation of the map
     * @return A String representation of the map
     */
    public String toString() 
    {
        String s = "";
        for (int i = 0; i < buckets.length; i++) 
        {

            ListNode<MapEntry<K, V>> head = buckets[i];

            //s += head +  " ";
            while (head != null) 
            {
                //System.out.println(head.getValue() + " ");
                s += head.getValue().toString() + " ";
                head = head.getNext();
            }

        }
        return s;
    }

}
