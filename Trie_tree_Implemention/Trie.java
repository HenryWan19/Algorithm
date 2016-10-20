/**
 * Implement a trie with insert, search, and startsWith methods.
 *
 * Note:
 * You may assume that all inputs are consist of lowercase letters a-z.
 *
 * Subscribe to see which companies asked this question
 */

package Trie_tree_Implemention;

/**
 * Created by henrywan16 on 10/16/16.
 */
public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        int i = 0, n = word.length();
        char temp;
        TrieNode p = root;

        // go to the end of the word which already contains in the tree.
        for (i = 0; i < n; i++)
        {
            temp = word.charAt(i);
            // apple insert app; app insert apple
            if (!p.containKey(temp)) {
                TrieNode newNode = new TrieNode();
                p.setTrieNodeList(temp, newNode);
            }
            p = p.getTrieNode(temp);
        }
        p.setEnd();
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        if (this.startsWith(word))
        {
            int i;
            TrieNode temp = this.root;
            for (i = 0; i < word.length(); i++) {
                temp = temp.getTrieNode(word.charAt(i));
            }
            if (temp.isend()) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        int i;
        TrieNode temp = this.root;
        for (i = 0; i < prefix.length(); i++)
        {
            char c = prefix.charAt(i);
            if (temp.containKey(c)) {
                temp = temp.getTrieNode(c);
            }
            else
            {
                return false;
            }
        }
        return true;
    }
}
