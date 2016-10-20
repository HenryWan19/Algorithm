# Trie_Tree_implemention
## Link is as follows:

+ [LeetCode](https://leetcode.com/problems/implement-trie-prefix-tree/)

## Summarize
1. The structure of TrieNode do not contain "private char currentChar". 
   Because if we don't have this char the trieNodeList[currentChar - 'a'] will be null.
  
2. If we "new TrieNode()" inside the method setTrieNodeList, 
   we will overwrite the formal records, which may make the isEnd to be changed, 
   such as inserting "app" and then "apple". The formal isEnd will be reset to be false.
  
3. How to write insert method? We need to go through the TrieTree, 
   and insert the nodes following the sequence belowing the inserted nodes, then init a new one and insert into the tree.
  
4. If we don't insert any letters to the wordlist, our root is not null. 
   It has an array which contains 26 null. After we insert a new TrieNode, 
   the list is no longer all null. For example, inserting "app" and "apple". 
   Root is not null, go to the link 'a'. 
   if the word is 'a', we can find that the TrieNodeList is an array which contains 26 null elements 
   but the TrieNodeList[c-'a'] is not null, and we need to set its isEnd area in the word 'a'.

