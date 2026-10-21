import java.util.ArrayList;

//BucketHashTable will implement the hashtable with separate chaining.
public class BucketHashTable<K, V> extends HashTableBase<K, V> {

	//bucket hash table uses its own data structure to hold items.
    //we use another ArrayList to represent the "chain" in each bucket, which represents the first level slot of the table.
	private ArrayList<ArrayList<HashItem<K, V>>> _buckets;

	protected int getHash(K item)
	{
		return _hasher.getHash(item, _buckets.size());
	}

	//determines whether or not we need to resize
	//for separate chaining, you have the option of NEVER doing resizing
	//because you can always insert new elements.
	//To turn it off, just always return false
	protected boolean needsResize()
	{
		if (_number_of_elements > 0.5 * _buckets.size())
			return true;
		return false;
	}

	@Override
	protected void resizeCheck() {
		//resize if necessary
		if (needsResize())
		{
			_local_prime_index++;

			HasherBase<K> hasher = _hasher;
			BucketHashTable<K, V> new_hash = new BucketHashTable<K, V>(hasher, _primes[_local_prime_index]);

			for (ArrayList<HashItem<K, V>> item: _buckets)
			{
				for (HashItem<K, V> sub_item: item)
				{
					if (sub_item.isEmpty() == false)
					{
						//add to new hash table
						new_hash.addElement(sub_item.getKey(), sub_item.getValue());
					}
				}
			}

			_buckets = new_hash._buckets;
		}
	}

	//helper function to initialize the buckets
	//this time we use a void function to set the _buckets directly
	private void initBuckets(int number_of_elements)
	{
		_buckets = new ArrayList<>(number_of_elements);
		//and fill it
		for(int i = 0; i < number_of_elements; i ++)
		{
			//Note that, the ArrayList<> value in sub_item is null at this point.
			ArrayList<HashItem<K,V>> sub_item = new ArrayList<>();
			_buckets.add(sub_item);
		}
	}

	//define the constructors
	public BucketHashTable(HasherBase<K> hasher)
	{
		//constructor chaining:
		//we pass in the default value of 11 as number_of_elements to the constructor below
		this(hasher, 11);
	}

	public BucketHashTable(HasherBase<K> hasher, int number_of_elements)
	{
		initBuckets(number_of_elements);
		_hasher = hasher;
		_local_prime_index = 0;
		_number_of_elements = 0;

		while (_primes[_local_prime_index] < number_of_elements)
		{
			_local_prime_index++;
		}
	}

	//copy constructor
	public BucketHashTable(BucketHashTable<K, V> other)
	{
		_hasher = other._hasher;
		_items = new ArrayList<>(other._items); //shallow copy - so strictly speaking this is not good enough. We might come back to this later
		_local_prime_index = other._local_prime_index;
		_number_of_elements = other._number_of_elements;
	}

    //TODO: concrete implementation for parent's addElement method
	public void addElement(K key, V value)
	{
		// To Be Implemented
	}

	//TODO: performs lazy delete using the supplied key
	public void removeElement(K key)
	{
		// To Be Implemented

		// Note: use setIsEmpty() to perform lazy delete
	}

	// returns true if the key is contained in the hash table
	public boolean containsElement(K key) {
	    int index = getHash(key); // Check what index the key gets hashed into

	    // Get the bucket (ArrayList) at the calculated index
	    ArrayList<HashItem<K, V>> bucket = _buckets.get(index);

	    // For each item in the bucket, check if its key matches the given key
	    for (HashItem<K, V> item : bucket) {
	        if (item.isEmpty() == false && item.getKey().equals(key)) {
	            return true;
	        }
	    }

	    return false;
	}
		
return false;
	}

	//returns the value paired with the key
	public V getElement(K key) {
	    int index = getHash(key); // Check what index the key gets hashed into

	    // Get the bucket (ArrayList) at the calculated index
	    ArrayList<HashItem<K, V>> bucket = _buckets.get(index);

	    for(HashItem<K, V> item : bucket) {
	    	if(item.isEmpty() == false && item.getKey(.equals(key))) {
	    		return item.getItem(); // return the value paired with the key.
	    	}

	   return null;
	}

	public ArrayList<ArrayList<HashItem<K, V>>> getBuckets() {
		return this._buckets;
	}

}
