package com.vijava_HW.cmd;

import java.util.HashMap;

public class TrieCmdNode {
	private HashMap<String, TrieCmdNode> trieMapNode;
	private int endState; // -1:not end; 0:init, 1:exit; 2:help ...
	
	// create root node
	public TrieCmdNode()
	{
		this.trieMapNode = new HashMap<String, TrieCmdNode>();
		this.endState = 0;
		this.trieMapNode.put("",null);
	}
	
	/**
	 * create Trie Node
	 * @param str : the key;
	 * @param endState : the value; if endState == -1, not the leaf node.
	 */
	public TrieCmdNode(String str, int endState)
	{
		this.trieMapNode = new HashMap<String, TrieCmdNode>();
		this.endState = endState;
		this.trieMapNode.put(str, null);
	}
	
	// create not leaf node, do not change the endState;
	public TrieCmdNode(String str, TrieCmdNode trieNode)
	{
		this.trieMapNode = new HashMap<String, TrieCmdNode>();
		this.trieMapNode.put(str, trieNode);
	}
	
	public boolean containKey(String str)
	{
		if (this.trieMapNode.containsKey(str))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	// get the TrieNode whose key is str;
	public TrieCmdNode getTrieNode(String str)
	{
		if (this.trieMapNode.containsKey(str))
		{
			TrieCmdNode temp = this.trieMapNode.get(str);
			return temp;
		}	
		else
		{
			System.out.println("Can not find the arguement " + str);;
			return null;
		}
	}
	
	// set a new TrieNode;
	public void setTrieNode(String str, TrieCmdNode trieCmdNode)
	{
		this.trieMapNode.put(str,  trieCmdNode);
	}
	
	public int getEndState()
	{
		return this.endState;
	}
	
	public void setEndState(int endState)
	{
		this.endState = endState;
	}
}
