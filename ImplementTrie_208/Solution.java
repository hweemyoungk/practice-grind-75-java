package ImplementTrie_208;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/*A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.

Implement the Trie class:

Trie() Initializes the trie object.
void insert(String word) Inserts the string word into the trie.
boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.


Example 1:

Input
["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
Output
[null, null, true, false, true, null, true]

Explanation
Trie trie = new Trie();
trie.insert("apple");
trie.search("apple");   // return True
trie.search("app");     // return False
trie.startsWith("app"); // return True
trie.insert("app");
trie.search("app");     // return True


Constraints:

1 <= word.length, prefix.length <= 2000
word and prefix consist only of lowercase English letters.
At most 3 * 104 calls in total will be made to insert, search, and startsWith.

[4/2/2024]27m out of 30m
89%/96%
(What about Trie having a dummy TrieNode?)
*/
class Trie {
    private TrieNode[] roots = new TrieNode[26];

    public Trie() {

    }

    // O(len(word))/O(1)
    public void insert(String word) {
        if (roots[word.charAt(0) - 97] == null) {
            roots[word.charAt(0) - 97] = new TrieNode(word.charAt(0), word.length() == 1);
        }
        TrieNode node = roots[word.charAt(0) - 97];
        for (int i = 1; i < word.length(); i++) {
            node = node.insert(word, i);
        }
    }

    // O(len(word))/O(1)
    public boolean search(String word) {
        TrieNode node = searchNode(word);
        if (node == null) return false;
        return node.isEndOfWord;
    }

    // O(len(word))/O(1)
    private TrieNode searchNode(String word) {
        TrieNode root = roots[word.charAt(0) - 97];
        if (root == null) return null;
        for (int i = 1; i < word.length(); i++) {
            root = root.search(word, i);
            if (root == null) {
                return null;
            }
        }
        return root;
    }

    // O(len(word))/O(1)
    public boolean startsWith(String prefix) {
        TrieNode node = searchNode(prefix);
        return node != null;
    }
}

class TrieNode {
    char c;
    boolean isEndOfWord;
    TrieNode[] nexts = new TrieNode[26];

    public TrieNode(char c) {
        this.c = c;
    }

    public TrieNode(char c, boolean isEndOfWord) {
        this.c = c;
        this.isEndOfWord = isEndOfWord;
    }

    public TrieNode insert(String word, int iNext) {
        if (nexts[word.charAt(iNext) - 97] == null) {
            nexts[word.charAt(iNext) - 97] = new TrieNode(word.charAt(iNext), iNext == word.length() - 1);
        }
        TrieNode next = nexts[word.charAt(iNext) - 97];
        if (iNext == word.length() - 1) {
            next.isEndOfWord = true;
        }
        return next;
    }

    public TrieNode search(String word, int iNext) {
        return nexts[word.charAt(iNext) - 97];
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */

