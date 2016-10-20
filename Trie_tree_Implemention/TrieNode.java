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
 * When we design, why we don't need to put
 * char current as an attribute?
 */
public class TrieNode {
    // Initialize your data structure here.
    // private char current;
    private TrieNode[] trieNodeList;
    private final int R;
    private boolean isEnd;

    public TrieNode() {
        this.isEnd = false;
        this.R = 26;
        this.trieNodeList = new TrieNode[R];
    }

    public boolean isend()
    {
        return this.isEnd;
    }

    public void setEnd()
    {
        this.isEnd = true;
    }

    /**
     * change the list inside the TrieNode
     * Why we need to make a parameter TrieNode temp?
     * Because if we do this, we will make a new node each time.
     * It means that if we want to insert "app" and "apple" the second words will cover the first record, which will
     * make the "app" end to be false!
     * Solution: using
     * public void setTrieNodeList(char c, TrieNode t); instead of public void setTrieNodeList(char c);
     *
     * @param c
     */
    public void setTrieNodeList(char c, TrieNode t)
    {
        int index = (c - 'a');
        if (index >= 0)
        {
            this.trieNodeList[index] = t;
        }
        else
        {
            System.out.println("Index error!");
        }
    }

    public boolean containKey(char c)
    {
        int index = c - 'a';
        if (this.trieNodeList[index] != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public TrieNode getTrieNode(char c)
    {
        int index = c - 'a';
        return this.trieNodeList[index];
    }
}
