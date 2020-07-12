import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TicketList<T extends Matchable<K>, K> implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<T> list = new LinkedList<T>();

    /**
     * Checks whether ticket with a given id exists.
     * 
     * @param key the id of the item
     * @return the item iff the item exists
     * 
     */
    public T search(K key) {
	for (T item : list) {
	    if (item.matches(key)) {
		return item;
	    }
	}
	return null;
    }

    /**
     * Adds a ticket to the list.
     * 
     * @param item the item to be added
     * @return true iff the item could be added
     */
    public boolean add(T item) {
	return list.add(item);
    }

    /**
     * Removes a ticket from the list
     * 
     * @param item the item to be removed
     * @return true iff the item could be removed
     */
    public boolean remove(T item) {
	return list.remove(item);
    }

    /**
     * Returns an iterator for the collection
     * 
     * @return iterator for the collection
     */
    public Iterator<T> iterator() {
	return list.iterator();
    }

}
