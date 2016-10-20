package com.vijava_HW.cmd;

import java.util.ArrayList;
import java.util.HashMap;

// Trie Node
public class TrieCmd {
	private TrieCmdNode root;
	private String name; // vname or hname;
	
	public TrieCmd()
	{
		this.root = new TrieCmdNode();
		TrieCmdNode temp1, temp2, temp3, temp4; // first level;
		temp1 = new TrieCmdNode("exit", 1);
		this.root.setTrieNode("exit", temp1);
		temp2 = new TrieCmdNode("help", 2);
		this.root.setTrieNode("help", temp2);
		temp3 = new TrieCmdNode("host", 3);
		this.root.setTrieNode("host", temp3);
		temp4 = new TrieCmdNode("vm", 4);
		this.root.setTrieNode("vm", temp4);
		
		TrieCmdNode middleHost, middleVm; // middle node, can accept any String
		middleHost = new TrieCmdNode("anyString", -1);
		middleVm = new TrieCmdNode("anyString", -1);
		temp3.setTrieNode("anyString", middleHost);
		temp4.setTrieNode("anyString", middleVm);
		
		TrieCmdNode temp5, temp6, temp7; // second lever command host
		temp5 = new TrieCmdNode("info", 5);
		temp6 = new TrieCmdNode("datastore", 6);
		temp7 = new TrieCmdNode("network", 7);
		middleHost.setTrieNode("info", temp5);
		middleHost.setTrieNode("datastore", temp6);
		middleHost.setTrieNode("network", temp7);
		
		TrieCmdNode temp8, temp9, temp10, temp11; // second lever command vm
		temp8 = new TrieCmdNode("info", 8);
		temp9 = new TrieCmdNode("on", 9);
		temp10 = new TrieCmdNode("off", 10);
		temp11 = new TrieCmdNode("shutdown", 11);
		middleVm.setTrieNode("info", temp8);
		middleVm.setTrieNode("on", temp9);
		middleVm.setTrieNode("off", temp10);
		middleVm.setTrieNode("shutdown", temp11);
	}
	
	/**
	 * 
	 * @param cmd
	 * @return 	1: exit
	 * 			2: help
	 * 			3: host
	 * 			4: vm
	 * 			5: host hname info
	 * 			6: host hname datastore
	 * 			7: host hname network
	 * 			8: vm vname info
	 * 			9: vm vname on
	 * 			10: vm vname off
	 * 			11: vm vname shutdown
	 */
	public int searchCmd(ArrayList<String> cmd)
	{
		String currentString = "";
		int i;
		TrieCmdNode node = this.root;
		for (i = 0; i < cmd.size(); i++)
		{
			currentString = cmd.get(i);
			if (i == 1)
			{
				this.name = currentString;
				node = node.getTrieNode("anyString");
				continue;
			}
			if (node != null && node.containKey(currentString))
			{
				node = node.getTrieNode(currentString);
			}
			else
			{
				return -1;
			}
		}
		if (node.getEndState() > 0) // leaf node of the Trie tree and end of the cmd line;
		{
			return node.getEndState();
		}
		else
		{
			return -1;
		}
	}
	
	public String getName()
	{
		return this.name;
	}
}
