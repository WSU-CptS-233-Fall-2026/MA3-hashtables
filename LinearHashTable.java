class LinearHashTable<K, V> extends HashTableBase<K, V>
{
	//determines whether or not we need to resize
	//to turn off resize, just always return false
    protected boolean needsResize()
    {
    	//linear probing seems to get worse after a load factor of about 70%
		if (_number_of_elements > (0.7 * _primes[_local_prime_index]))
		{
			return true;
		}
		return false;
    }
    
    //called to check to see if we need to resize
    protected void resizeCheck()
    {
    	//Right now, resize when load factor > .7; it might be worth it to experiment with 
		//this value for different kinds of hashtables
		if (needsResize())
		{
			_local_prime_index++;

			HasherBase<K> hasher = _hasher;
			LinearHashTable<K, V> new_hash = new LinearHashTable<K, V>(hasher, _primes[_local_prime_index]);
			
			for (HashItem<K, V> item: _items)
			{
				if (item.isEmpty() == false)
				{
					//add to new hash table
					new_hash.addElement(item.getKey(), item.getValue());
				}
			}
			
			_items = new_hash._items;
		}
    }
    
    public LinearHashTable()
    {
    	super();
    }
    
    public LinearHashTable(HasherBase<K> hasher)
    {
    	super(hasher);
    }
    
    public LinearHashTable(HasherBase<K> hasher, int number_of_elements)
    {
    	super(hasher, number_of_elements);
    }
    
    //copy constructor
    public LinearHashTable(LinearHashTable<K, V> other)
    {
    	super(other);
    }
    
    //TODO: concrete implementation for parent's addElement method
    public void addElement(K key, V value)
    {
        // To Be Implemented
    }
    
    //TODO: removes supplied key from hash table
    public void removeElement(K key)
    {
    	// To Be Implemented
    }

    
    //TODO: returns true if the key is contained in the hash table
    public boolean containsElement(K key)
    {
		// To Be Implemented
    	
		//placeholder code to prevent errors
    	return false;
    }

    
    //TODO: returns the value paired with the key
    public V getElement(K key)
    {
		// To Be Implemented
    	
    	//placeholder code to prevent errors
    	return null;
    }
}