package SkipList;
import java.util.*;
/*http://dsqiu.iteye.com/blog/1705530*/
public class SkipList {
	public class SkipNode {
		int key;
		int val;
		ArrayList<SkipNode> forward;
		SkipNode (int level, int key, int value) {
			this.key = key;
			this.val = value;
			forward = new ArrayList<SkipNode>(level);
		}
	}
	
	int level;
	final int MAX_LEVEL = 15;
	SkipNode head;
	SkipList () {
		this.level = 0;
		head = new SkipNode(MAX_LEVEL - 1, 0, 0);
		for (int i = 0; i < head.forward.size(); i++) {
			head.forward.set(i, null);
		}
	}
	
	/*Create random level*/
	public int getRandomLevel () {
		Random r = new Random();
		int num = r.nextInt(MAX_LEVEL + 1);
		return num;
	}
	
	/*Insert a SkipNode*/
	public boolean insert (int key, int val) {
		SkipNode[] update = new SkipNode[MAX_LEVEL];
		SkipNode cur = head;
		int i = this.level -1;
		for (; i >= 0; i--) {
			while ((cur.forward.get(i) != null) && (cur.forward.get(i).key < key)) {
				cur = cur.forward.get(i);
			}
			update[i] = cur;
		}
		
		/*cannot insert the same key*/
		if ((cur.forward.get(i) != null) && (cur.forward.get(i).key == key)) {
			return false;
		}
		
		/*Get RandomLevel*/
		int k = getRandomLevel();
		if (k > this.level) {
			for (int j = this.level; j < k; j++) {
				update[j] = this.head;
			}
			this.level = k;
		}
		
		SkipNode iNode = new SkipNode(k, key, val);
		for (int j = 0; j < k; j++) {
			iNode.forward.set(j, update[j].forward.get(j));
			update[j].forward.set(j, iNode);
		}
		return true;
	}
	
	/*Find the value of the key. If not found, return -1*/
	public int search (int key) {
		SkipNode cur = this.head;
		for (int i = this.level; i >= 0; i--) {
			while ((cur.forward.get(i) != null) && (cur.forward.get(i).key <= key)){
				if (cur.forward.get(i).key == key) {
					return cur.forward.get(i).val;
				}
				cur = cur.forward.get(i);
			}
		}
		return -1;
	}
	
	/*Delete a skipNode*/
	public boolean delete (int key) {
		SkipNode[] update = new SkipNode[MAX_LEVEL];
		SkipNode cur = this.head;
		int k = this.level;
		int i = k - 1;
		for (; i >= 0; i--) {
			while ((cur.forward.get(i) != null) && (cur.forward.get(i).key < key)) {
				cur = cur.forward.get(i);
			}
			update[i] = cur;
		}
		/*If the node has been found, delete it*/
		if ((cur.forward.get(i) != null) && (cur.forward.get(i).key == key)) {
			for (int j = 0; j < this.level; j++) {
				if (update[j].forward.get(j) == cur.forward.get(i)) {
					update[j].forward.set(j, cur.forward.get(i).forward.get(j));
				}
			}
			
			for (int j = this.level - 1; j >= 0; j--) {
				if (update[j].forward.get(j) == null) {
					this.level--;
				}
			}
			return true;
		} else {
			return false;
		}
	}
}
