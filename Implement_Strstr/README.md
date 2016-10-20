# search-word

## link
+ [search_word](https://leetcode.com/problems/add-and-search-word-data-structure-design)

## Summarize
1. How to solve the "."? by Recursion
   
   ```
   temp = word.charAt(i);
   if (temp == '.') {
       tempArrayList = p.getAllTrieNodeIm();
       for (TrieNodeIm item : tempArrayList) {
          String sub = word.substring(i + 1, word.length());
   	      if (search(sub, item)) {
             return true;
          }
       }
       return false; // having found all the subString
   }
   ```

