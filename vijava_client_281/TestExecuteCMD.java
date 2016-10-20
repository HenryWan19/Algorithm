package com.vijava_HW.cmd;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;

public class TestExecuteCMD {

	@Test
	public void test() {
		//fail("Not yet implemented");
	}
	
	@Test
	public void testExit()
	{
		String s = "exit";
		ArrayList<String> tempList = new ArrayList<String>();
		tempList.add(s);
		TrieCmd trieCmd = new TrieCmd();
		int result = trieCmd.searchCmd(tempList);
		assertEquals(1,result);
		
		// error cmd: vms
		int i;
		String[] s1 = {"host", "myhost"};
		tempList.clear();
		for (i = 0; i < s1.length; i++)
		{
			tempList.add(s1[i]);
		}		
		result = trieCmd.searchCmd(tempList);
		assertEquals(-1,result);
	}
	
	@Test
	public void testHelp()
	{
		String s = "help";
		ArrayList<String> tempList = new ArrayList<String>();
		tempList.add(s);
		TrieCmd trieCmd = new TrieCmd();
		int result = trieCmd.searchCmd(tempList);
		assertEquals(2,result);
	}
	
	@Test
	public void testHost()
	{
		String s = "host";
		ArrayList<String> tempList = new ArrayList<String>();
		tempList.add(s);
		TrieCmd trieCmd = new TrieCmd();
		int result = trieCmd.searchCmd(tempList);
		assertEquals(3,result);
	}
	
	@Test
	public void testVm()
	{
		String s = "vm";
		ArrayList<String> tempList = new ArrayList<String>();
		tempList.add(s);
		TrieCmd trieCmd = new TrieCmd();
		int result = trieCmd.searchCmd(tempList);
		assertEquals(4,result);
	}
	
	@Test
	public void testVmCommands()
	{
		int i;
		String[] s = {"vm", "myhost", "info"};
		ArrayList<String> tempList = new ArrayList<String>();
		for (i = 0; i < s.length; i++)
		{
			tempList.add(s[i]);
		}
		TrieCmd trieCmd = new TrieCmd();
		int result = trieCmd.searchCmd(tempList);
		assertEquals(8,result);
		
		String[] s1 = {"vm", "myhost", "shutdown"};
		tempList.clear();
		for (i = 0; i < s1.length; i++)
		{
			tempList.add(s1[i]);
		}		
		result = trieCmd.searchCmd(tempList);
		assertEquals(11,result);
		
		String[] s2 = {"vm", "myhost", "on"};
		tempList.clear();
		for (i = 0; i < s2.length; i++)
		{
			tempList.add(s2[i]);
		}		
		result = trieCmd.searchCmd(tempList);
		assertEquals(9,result);
		
		String[] s3 = {"vm", "myhost", "off"};
		tempList.clear();
		for (i = 0; i < s3.length; i++)
		{
			tempList.add(s3[i]);
		}		
		result = trieCmd.searchCmd(tempList);
		assertEquals(10,result);
	}
	
	@Test
	public void testHostCommands()
	{
		int i;
		String[] s = {"host", "myhost", "info"};
		ArrayList<String> tempList = new ArrayList<String>();
		for (i = 0; i < s.length; i++)
		{
			tempList.add(s[i]);
		}
		TrieCmd trieCmd = new TrieCmd();
		int result = trieCmd.searchCmd(tempList);
		assertEquals(5,result);
		
		String[] s1 = {"host", "myhost", "datastore"};
		tempList.clear();
		for (i = 0; i < s1.length; i++)
		{
			tempList.add(s1[i]);
		}		
		result = trieCmd.searchCmd(tempList);
		assertEquals(6,result);
		
		String[] s2 = {"host", "myhost", "network"};
		tempList.clear();
		for (i = 0; i < s2.length; i++)
		{
			tempList.add(s2[i]);
		}		
		result = trieCmd.searchCmd(tempList);
		assertEquals(7,result);
	}
	
	@Test
	public void testHostCommandsError()
	{
		// error cmd : vm myhost nifo
		int i;
		String[] s = {"host", "myhost", "nifo"};
		ArrayList<String> tempList = new ArrayList<String>();
		for (i = 0; i < s.length; i++)
		{
			tempList.add(s[i]);
		}
		TrieCmd trieCmd = new TrieCmd();
		int result = trieCmd.searchCmd(tempList);
		assertEquals(-1,result);
		
		// error cmd: vm myhost
		String[] s1 = {"host", "myhost"};
		tempList.clear();
		for (i = 0; i < s1.length; i++)
		{
			tempList.add(s1[i]);
		}		
		result = trieCmd.searchCmd(tempList);
		assertEquals(-1,result);
		
		// error cmd: vm myhost info more
		String[] s2 = {"host", "myhost", "info", "more"};
		tempList.clear();
		for (i = 0; i < s1.length; i++)
		{
			tempList.add(s1[i]);
		}		
		result = trieCmd.searchCmd(tempList);
		assertEquals(-1,result);
	}
	
	@Test
	public void testVmCommandsError()
	{
		// error cmd : vm myhost nifo
		int i;
		String[] s = {"vm", "HuiWan-ub1404-716", "nifo"};
		ArrayList<String> tempList = new ArrayList<String>();
		for (i = 0; i < s.length; i++)
		{
			tempList.add(s[i]);
		}
		TrieCmd trieCmd = new TrieCmd();
		int result = trieCmd.searchCmd(tempList);
		assertEquals(-1,result);
		
		// error cmd: vm myhost
		String[] s1 = {"vm", "myhost"};
		tempList.clear();
		for (i = 0; i < s1.length; i++)
		{
			tempList.add(s1[i]);
		}		
		result = trieCmd.searchCmd(tempList);
		assertEquals(-1,result);
	}
}
