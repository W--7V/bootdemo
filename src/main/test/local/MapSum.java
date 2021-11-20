package local;

import java.util.HashMap;
import java.util.Map;

public class MapSum {
    TrieNode root;
    Map<String, Integer> map;

    class TrieNode {
        int val = 0;
        TrieNode[] sub = new TrieNode[26];
    }

    public MapSum() {
        root = new TrieNode();
        map = new HashMap<>();
    }

    public void insert(String key, int val) {
        Integer old = map.getOrDefault(key, 0);
        int newVal= val - old;
        map.put(key, val);

        TrieNode current = root;
        for (char c : key.toCharArray()) {
            if (current.sub[c - 'a'] == null) {
                current.sub[c - 'a'] = new TrieNode();
            }
            current.sub[c - 'a'].val += newVal;
            current = current.sub[c - 'a'];
        }
    }

    public int sum(String prefix) {
        TrieNode current = root;
        for (char c : prefix.toCharArray()) {
            if (current.sub[c - 'a'] == null) {
                return 0;
            }
            current = current.sub[c - 'a'];
        }
        return current.val;
    }

    public static void main(String[] args) {
        MapSum m = new MapSum();
        m.insert("apple", 3);
        System.out.println(m.sum("ap"));
        m.insert("app", 2);
        m.insert("apple", 2);
        System.out.println(m.sum("ap"));
    }

}
