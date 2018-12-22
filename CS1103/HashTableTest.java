/* Class for Hash Table Nodes
 * 
 * Output:
 *  
 *  Hash Table Test
 * 
 * Bucket 1 : thirst seeds 
 * Bucket 2 : 
 * Bucket 3 : 
 * Bucket 4 : juicy sweet 
 * Bucket 5 : 
 * 
 * Hash Table size: 4
 * 
 * 
 * HashTable["Water"]: thirst
 * 
 * Removing Apple..
 * 
 * Bucket 1 : thirst seeds 
 * Bucket 2 : 
 * Bucket 3 : 
 * Bucket 4 : sweet 
 * Bucket 5 : 
 * 
 * Guava exists: true
 * Apple exists: false
 * 
 * */

class LinkedHashEntry {
	String key;
	String value;
	LinkedHashEntry next;

	// Constructor
	LinkedHashEntry(String key, String value) {
		this.key = key;
		this.value = value;
		this.next = null;
	}
}

class HashTable {
	private int TABLE_SIZE;
	private int size;
	private LinkedHashEntry[] table;

	// Constructor
	public HashTable(int ts) {
		size = 0;
		TABLE_SIZE = ts;
		table = new LinkedHashEntry[TABLE_SIZE];
		for (int i = 0; i < TABLE_SIZE; i++) {
			table[i] = null;
		}
	}

	// Function to get value of a key
	public String get(String key) {
		int hash = (hash( key ) % TABLE_SIZE);
		if (table[hash] == null) {
			return null;
		} else {
			LinkedHashEntry entry = table[hash];
			while (entry != null && !entry.key.equals(key)) {
				entry = entry.next;
			}
			if (entry == null) {
				return null;
			} else {
				return entry.value;
			}
		}
	}

	// Function to put a key value pair
	public void put(String key, String value) {
		int hash = (hash( key ) % TABLE_SIZE);
		if (table[hash] == null) {
			table[hash] = new LinkedHashEntry(key, value);
		} else {
			LinkedHashEntry entry = table[hash];
			while (entry.next != null && !entry.key.equals(key)) {
				entry = entry.next;
			}

			if (entry.key.equals(key)) {

				entry.value = value;
			} else {
				entry.next = new LinkedHashEntry(key, value);
			}
		}
		size++;
	}

	// Function to remove a key-value pair
	public void remove(String key) {
		int hash = (hash( key ) % TABLE_SIZE);
		if (table[hash] != null) {
			LinkedHashEntry prevEntry = null;
			LinkedHashEntry entry = table[hash];
			while (entry.next != null && !entry.key.equals(key)) {
				prevEntry = entry;
				entry = entry.next;
			}

			if (entry.key.equals(key)) {
				if (prevEntry == null) {
					table[hash] = entry.next;
				} else {
					prevEntry.next = entry.next;
				}

				size--;
			}
		}
	}

	// Function to return if given key exists
	public boolean containsKey(String key) {
		int bucket = hash(key);
		LinkedHashEntry entry = table[bucket];

		while (entry != null) {
			if(entry.key.equals(key)) {
				return true;
			}
			entry = entry.next;
		}
		return false;
	}

	// Function to get number of key-value pairs
	public int size() {
		return size;
	}


	// Function hash which gives a hash value for a given string
	private int hash(String x) {
		int hashVal = x.hashCode();
		hashVal %= TABLE_SIZE;
		if (hashVal < 0) {
			hashVal += TABLE_SIZE;
		}

		return hashVal;
	}

	// Function to print hash table
	public void printHashTable() {
		for (int i = 0; i < TABLE_SIZE; i++) {
			System.out.print("\nBucket "+ (i + 1) +" : ");
			LinkedHashEntry entry = table[i];
			while (entry != null) {
				System.out.print(entry.value +" ");
				entry = entry.next;
			}
		}
	}
}

public class HashTableTest {
	public static void main(String[] args) {
		System.out.println("Hash Table Test");

		// Make object of HashTable
		HashTable ht = new HashTable(5);
		ht.put("Water", "thirst");
		ht.put("Apple", "juicy");
		ht.put("Mango", "sweet");
		ht.put("Guava", "seeds");

		// Print HashTable
		ht.printHashTable();

		// Print size
		System.out.printf("\n\nHash Table size: %d\n", ht.size());

		System.out.println("\n\nHashTable[\"Water\"]: " + ht.get("Water"));

		// Try to remove
		System.out.println("\nRemoving Apple..");
		ht.remove("Apple");

		// Print HashTable to make sure Mango has removed
		ht.printHashTable();

		// Try to check key exists
		System.out.println("\n\nGuava exists: "+ht.containsKey("Guava"));
		System.out.println("Apple exists: "+ht.containsKey("Apple"));
	}
}