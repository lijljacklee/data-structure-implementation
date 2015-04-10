package HashTable;

public class HashTable <K, V> {
	private K[] keySet;
	private V[] valueSet;
	private int size;
	
	@SuppressWarnings("unchecked")
	public HashTable() {
		this.keySet =  (K[]) new Object[10];
		this.valueSet = (V[]) new Object[10];
		this.size = 0;
	}
	
	public int hashfun1 (String key) {
		int sum = 0;
		for (int i = 0; i < key.length(); i++) {
			sum = sum * 33 + (int)key.charAt(i);
			sum = sum % this.size;
		}
		return sum;
	}
	
	public int hash1 (K key) {
		return Math.abs(key.hashCode()) % this.keySet.length;
	}
	
	public int hash2 (K key) {
		int num = Math.abs(key.hashCode()) % (this.keySet.length -1);
		if (num % 2 == 0)
			num++;
		return num;
	}
	
	public boolean contains (K key) {
		int start = hash1(key);
		int i = start;
		while (keySet[i] != null) {
			if (keySet[i].equals(key)) {
				return true;
			}
			i = (i + hash2(key)) % this.keySet.length;
			if (i == start) {
				return false;
			}
		}
		return false;
	}
	
	public V get (K key) {
		int start = hash1(key);
		int i = start;
		while (keySet[i] != null) {
			if (keySet[i].equals(key)) {
				return valueSet[i];
			}
			i = (i + hash2(key)) % this.keySet.length;
			if (i == start) {
				return null;
			}
		}
		return null;
	}
	
	public void add (K key, V value) {
		if (this.size >= (this.keySet.length/2)){
			this.rehash();
		}
		int start = hash1(key);
		int i = start;
		while (this.keySet[i] != null) {
			if (this.keySet[i].equals(key)) {
				if (this.valueSet[i].equals(value)) {
					return;
				} else {
					break;
				}
			}
			i = (i + hash2(key)) % this.keySet.length;
			if (i == start) {
				return;
			}
		}
		this.keySet[i] = key;
		this.valueSet[i] = value;
		this.size++;
	}
	
	@SuppressWarnings("unchecked")
	public void rehash() {
		HashTable<K, V> newHT = new HashTable<K, V>();
		newHT.keySet = (K[]) new Object[this.keySet.length * 2];
		newHT.valueSet = (V[]) new Object[this.valueSet.length * 2];
		for (int i = 0; i < this.valueSet.length; i++) {
			if (this.valueSet[i] != null) {
				newHT.add(this.keySet[i], this.valueSet[i]);
			}
		}
		this.keySet = (K[])newHT.keySet;
		this.valueSet = (V[])newHT.valueSet;
		this.size = newHT.size;
	}
	
	public void remove (K key) {
		if (this.contains(key)) {
			int i = this.getIndex(key);
			if (i >= 0) {
				this.valueSet[i] = null;
				this.keySet[i] = null;	
			}
		}
	}
	
	public int getIndex (K key) {
		int start = this.hash1(key);
		int i = start;
		while (this.keySet[i] != null) {
			if (this.keySet[i].equals(key)) {
				return i;
			}
			i = (i + this.hash2(key)) % (this.keySet.length);
			if (i == start) {
				return -1;
			}
		}
		return -1;
	}
	
	public void print() {
		for (int i = 0; i < this.keySet.length; i++) {
			if (this.keySet[i] != null) {
				System.out.println(this.keySet[i] + ": " + this.valueSet[i]);
			}
		}
	}
	
	public int size() {
		return this.size;
	}
	
	public int length() {
		return this.keySet.length;
	}
	
	public static void main (String[] argv) {
		HashTable<Integer, String> ht = new HashTable<Integer, String>();
		ht.print();
		System.out.println();
		ht.add(1,"Jack");
		ht.add(2, "Jerry");
		ht.add(2, "Potato");
		ht.add(3, "Jennifer");
		ht.add(4, "Peter");
		ht.add(5, "Kevin");
		ht.add(6, "Susan");
		ht.add(7, "Tom");
		ht.print();
		System.out.println("size: "+ ht.size() + " length: " + ht.length());
		System.out.println(ht.get(1));
		ht.remove(2);
		ht.print();
		
		HashTable<String, String> ht2 = new HashTable<String, String>();
		ht2.add("Red", "Apple");
		ht2.add("White", "Snow");
		ht2.print();
	}
}
