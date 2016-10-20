package com.vijava_HW.cmd;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Scanner;

import com.vmware.vim25.mo.ServiceInstance;

public class Application {

	public static void main(String[] args)
	{
		ServiceInstance service = null;
		try {
			service = new ServiceInstance(new URL(args[0]), args[1], args[2], true);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("CMPE281 HW2 from HuiWan");
		Scanner scanner = new Scanner(System.in);
		boolean condition = true;
		
		if (args.length < 3)
		{
			Application.usage();
			return;
		}
		
		while (condition)
		{
			System.out.print("huiwan-716> ");
			String sentence = scanner.nextLine();
			String[] tokens = sentence.split("(\\t|\\s|\\n|,)+"); // at least once match the above reg
			if (tokens[0].equals("")) // skip enter;
			{
				continue;
			}
			//System.out.println(Arrays.toString(tokens));
			ExecuteCMD executeCmd;
			
			try {
				executeCmd = new ExecuteCMD(tokens, args, service);
				executeCmd.doMethod();
				condition = executeCmd.getWhileState();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}					
		}
	}
	
	public static void usage()
	{
		System.out.println("Usage: java [-options] Application IP Username Password(to execute the application)");
	}
}
