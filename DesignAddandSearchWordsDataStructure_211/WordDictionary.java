package DesignAddandSearchWordsDataStructure_211;

/*Design a data structure that supports adding new words and finding if a string matches any previously added string.

Implement the WordDictionary class:

WordDictionary() Initializes the object.
void addWord(word) Adds word to the data structure, it can be matched later.
bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.


Example:

Input
["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
Output
[null,null,null,null,false,true,true,true]

Explanation
WordDictionary wordDictionary = new WordDictionary();
wordDictionary.addWord("bad");
wordDictionary.addWord("dad");
wordDictionary.addWord("mad");
wordDictionary.search("pad"); // return False
wordDictionary.search("bad"); // return True
wordDictionary.search(".ad"); // return True
wordDictionary.search("b.."); // return True


Constraints:

1 <= word.length <= 25
word in addWord consists of lowercase English letters.
word in search consist of '.' or lowercase English letters.
There will be at most 2 dots in word for search queries.
At most 10^4 calls will be made to addWord and search.

[5/6/2024]27m out of 35m
58%/95%
*/
class WordDictionary {
    static class TrieNode {
        final char val;
        boolean isEnd;
        final TrieNode[] nexts = new TrieNode[26];

        TrieNode(char val, boolean isEnd) {
            this.val = val;
            this.isEnd = isEnd;
        }
    }

    TrieNode root = new TrieNode('.', false);

    public WordDictionary() {

    }

    // O(len(word))/O(1)
    public void addWord(String word) {
        TrieNode prevNode = root;
        for (int i = 0; i < word.length(); i++) {
            char currChar = word.charAt(i);
            TrieNode currNode = prevNode.nexts[currChar - 'a'];
            if (currNode == null) {
                // Create
                prevNode.nexts[currChar - 'a'] = (currNode = new TrieNode(currChar, false));
            }
            prevNode = currNode;
        }
        prevNode.isEnd = true;
    }

    public boolean search(String word) {
        return search(word, 0, root);
    }

    // O(len(word) + (~26)^#wildcards)/O(#wildcards)
    private boolean search(String word, int iStart, TrieNode prevNode) {
        for (int i = iStart; i < word.length(); i++) {
            char currChar = word.charAt(i);
            if (currChar == '.') {
                // Wildcard
                for (var currNode : prevNode.nexts) {
                    if (currNode == null) continue;
                    if (search(word, i + 1, currNode)) return true;
                }
                return false;
            }
            // Not wildcard
            TrieNode currNode = prevNode.nexts[currChar - 'a'];
            if (currNode == null) return false;
            prevNode = currNode;
        }
        return prevNode.isEnd;
    }

    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        wordDictionary.search("pad");
        wordDictionary.search("bad");
        wordDictionary.search(".ad");
        wordDictionary.search("b..");
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
