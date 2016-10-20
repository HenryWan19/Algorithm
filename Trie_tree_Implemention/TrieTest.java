package Trie_tree_Implemention;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by henrywan16 on 10/17/16.
 */
public class TrieTest {
    @Test
    public void search() throws Exception {
        Trie treeRoot = new Trie();
        treeRoot.insert("app");
        treeRoot.insert("apple");
        treeRoot.insert("beer");
        boolean result = treeRoot.search("apps");
        assertEquals(false, result);

        result = treeRoot.search("app");
        assertEquals(true, result);
    }

    @Test
    public void startsWith() throws Exception {
        Trie treeRoot = new Trie();
        treeRoot.insert("app");
        treeRoot.insert("apple");
        treeRoot.insert("beer");
        boolean result = treeRoot.startsWith("apps");
        assertEquals(false, result);

        result = treeRoot.startsWith("app");
        assertEquals(true, result);
    }

}