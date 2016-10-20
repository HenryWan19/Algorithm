package com.vijava_HW.cmd;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import com.vmware.vim25.InvalidProperty;
import com.vmware.vim25.RuntimeFault;
import com.vmware.vim25.TaskInfoState;
import com.vmware.vim25.VirtualMachinePowerState;
import com.vmware.vim25.mo.Datastore;
import com.vmware.vim25.mo.Folder;
import com.vmware.vim25.mo.HostSystem;
import com.vmware.vim25.mo.InventoryNavigator;
import com.vmware.vim25.mo.ManagedEntity;
import com.vmware.vim25.mo.Network;
import com.vmware.vim25.mo.ServiceInstance;
import com.vmware.vim25.mo.Task;
import com.vmware.vim25.mo.VirtualMachine;

public class ExecuteCMD {
	private ArrayList<String> wordsList;
	private TrieCmd trieCmd;
	private String ip;
	private String username;
	private String passwords;
	private boolean while_state;
	private ServiceInstance service;
	private Folder root;

	public ExecuteCMD() {
		this.wordsList = new ArrayList<String>();
		this.trieCmd = new TrieCmd();
		this.service = null;
		this.root = null;
	}

	// java Application ip username password
	public ExecuteCMD(String[] str, String[] args, ServiceInstance service)
			throws RemoteException, MalformedURLException {
		this.wordsList = new ArrayList<String>();
		int i;
		for (i = 0; i < str.length; i++) {
			this.wordsList.add(str[i]);
		}
		this.trieCmd = new TrieCmd();

		this.ip = args[0];
		this.username = args[1];
		this.passwords = args[2];
		this.while_state = true;
		this.service = service;
		this.root = this.service.getRootFolder();
	}

	public boolean getWhileState() {
		return this.while_state;
	}

	public void doMethod() throws InvalidProperty, RuntimeFault, RemoteException, InterruptedException {
		int state = trieCmd.searchCmd(wordsList);
		long start = System.currentTimeMillis();
		switch (state) {
		case 1:
			this.while_state = doExit();
			break;
		case 2:
			doHelp();
			break;
		case 3:
			doHost();
			break;
		case 4:
			doVm();
			break;
		case 5:
			doHostInfo();
			break;
		case 6:
			doHostDatastore();
			break;
		case 7:
			doHostNetwork();
			break;
		case 8:
			doVmInfo();
			break;
		case 9:
			doVmOn();
			break;
		case 10:
			doVmOff();
			break;
		case 11:
			doVmShutdown();
			break;
		default:
			System.out.println("Error command!");
			Application.usage();
		}
		long end = System.currentTimeMillis();
		System.out.println("This command execution time is " + (end - start) + " ms");
	}

	public boolean doExit() {
		return false;
	}

	public void doHelp() {
		System.out.println("Usage: command arguement1 arguement2");
		String space = "							";
		System.out.println("exit" + space + "	exit the program");
		System.out.println("host" + space + "	enumerate hosts");
		System.out.println("host hname info" + space + "show info for hname");
		System.out.println("host hname datastore" + "						" + "enumerate datastores for hname");
		System.out.println("host hname network" + "						" + "enumerate networks for hname");
		System.out.println("vm" + space + "	enumerate vms");
		System.out.println("vm vname info" + space + "show info for vname");
		System.out.println("vm vname shutdown" + "						" + "shutdown OS on vname");
		System.out.println("vm vname on" + space + "power on vname");
		System.out.println("vm vname off" + space + "power off vname");
	}

	public void doHost() throws InvalidProperty, RuntimeFault, RemoteException {
		ManagedEntity[] managedEntityArray = new InventoryNavigator(this.root).searchManagedEntities("HostSystem");
		int i;
		for (i = 0; i < managedEntityArray.length; i++) {
			System.out.println("host[" + i + "]:Name = " + managedEntityArray[i].getName());
		}
	}

	public void doVm() throws InvalidProperty, RuntimeFault, RemoteException {
		ManagedEntity[] managedEntityArray = new InventoryNavigator(this.root).searchManagedEntities("VirtualMachine");
		int i;
		for (i = 0; i < managedEntityArray.length; i++) {
			System.out.println("vm[" + i + "]:Name = " + managedEntityArray[i].getName());
		}
	}

	public void doHostInfo() throws InvalidProperty, RuntimeFault, RemoteException {
		HostSystem ssoHost = (HostSystem) new InventoryNavigator(this.root).searchManagedEntity("HostSystem",
				this.trieCmd.getName());
		System.out.println("Name = " + ssoHost.getName());
		System.out.println("ProductFullName = " + ssoHost.getConfig().product.getFullName());
		System.out.println("Cpu cores = " + ssoHost.getHardware().cpuInfo.numCpuCores);
		System.out.println("RAM = " + ssoHost.getHardware().memorySize / (1024 * 1024 * 1024) + " GB");
	}

	public void doHostDatastore() throws InvalidProperty, RuntimeFault, RemoteException {
		HostSystem ssoHost = (HostSystem) new InventoryNavigator(this.root).searchManagedEntity("HostSystem",
				this.trieCmd.getName());
		System.out.println("Name = " + ssoHost.getName());

		int i;
		Datastore[] multiDatastores = ssoHost.getDatastores();
		for (i = 0; i < multiDatastores.length; i++) {
			System.out.println("Datastore[" + i + "]: name = " + multiDatastores[i].getName() + " ,FreeSpace = "
					+ multiDatastores[i].getSummary().getFreeSpace() / (1024 * 1024 * 1024) + "GB, " + " ,capacity = "
					+ multiDatastores[i].getSummary().getCapacity() / (1024 * 1024 * 1024) + "GB");
		}
	}

	public void doHostNetwork() throws InvalidProperty, RuntimeFault, RemoteException {
		HostSystem ssoHost = (HostSystem) new InventoryNavigator(this.root).searchManagedEntity("HostSystem",
				this.trieCmd.getName());
		System.out.println("Name = " + ssoHost.getName());

		int i;
		Network[] multiNetworks = ssoHost.getNetworks();
		for (i = 0; i < multiNetworks.length; i++) {
			System.out.println("Network[" + i + "]: name = " + multiNetworks[i].getName());
		}
	}

	public void doVmInfo() throws InvalidProperty, RuntimeFault, RemoteException {
		VirtualMachine ssoVm = (VirtualMachine) new InventoryNavigator(this.root).searchManagedEntity("VirtualMachine",
				this.trieCmd.getName());
		System.out.println("Name = " + ssoVm.getName());
		System.out.println("Guest full name = " + ssoVm.getConfig().getGuestFullName());
		System.out.println("Guest state = " + ssoVm.getGuest().getGuestState());
		System.out.println("IP address = " + ssoVm.getGuest().getIpAddress());
		System.out.println("Tool running status = " + ssoVm.getGuest().getToolsRunningStatus());
		System.out.println("Power state = " + ssoVm.getSummary().getRuntime().getPowerState());
	}

	public void doVmOn() throws InvalidProperty, RuntimeFault, RemoteException, InterruptedException {
		VirtualMachine ssoVm = (VirtualMachine) new InventoryNavigator(this.root).searchManagedEntity("VirtualMachine",
				this.trieCmd.getName());
		// Task[] taskList = ssoVm.getRecentTasks();
		// TaskInfoState state = taskList[taskList.length -
		// 1].getTaskInfo().getState();
		VirtualMachinePowerState powerState = ssoVm.getSummary().getRuntime().getPowerState();
		System.out.println("Name = " + ssoVm.getName());
		if (powerState.toString().equals("poweredOn")) {

			System.out.println(
					"Power on VM: status = " + "The attempted operation cannot be performed in the current state ("
							+ powerState.toString() + ")" + ", completion time = " + getTime());
		} else {
			ssoVm.powerOnVM_Task(null);
			this.waitting();
			powerState = ssoVm.getSummary().getRuntime().getPowerState();
			System.out.println("Power on VM: status = " + powerState.toString() + ", completion time = " + getTime());
		}
	}

	public void doVmOff() throws InvalidProperty, RuntimeFault, RemoteException, InterruptedException {
		VirtualMachine ssoVm = (VirtualMachine) new InventoryNavigator(this.root).searchManagedEntity("VirtualMachine",
				this.trieCmd.getName());
		VirtualMachinePowerState powerState = ssoVm.getSummary().getRuntime().getPowerState();
		System.out.println("Name = " + ssoVm.getName());
		if (powerState.toString().equals("poweredOn")) {
			ssoVm.powerOffVM_Task();
			this.waitting();
			powerState = ssoVm.getSummary().getRuntime().getPowerState();
			System.out.println("Power off VM: status = " + powerState.toString() + ", completion time = " + getTime());

		} else {
			ssoVm.powerOffVM_Task();
			this.waitting();
			System.out.println(
					"Power off VM: status = " + "The attempted operation cannot be performed in the current state ("
							+ powerState.toString() + ")" + ", completion time = " + getTime());
		}
	}

	public void doVmShutdown() throws InterruptedException, InvalidProperty, RuntimeFault, RemoteException {
		VirtualMachine ssoVm;

		ssoVm = (VirtualMachine) new InventoryNavigator(this.root).searchManagedEntity("VirtualMachine",
				this.trieCmd.getName());
		VirtualMachinePowerState powerState = ssoVm.getSummary().getRuntime().getPowerState();
		long start = System.currentTimeMillis(), end = start;
		if (powerState.toString().equals("poweredOff")) {
			System.out.println("Name = " + ssoVm.getName());
			System.out.println("The virtual machine is poweroff now! Can not shutdown the guestOS!");
		} else {
			System.out.println("Name = " + ssoVm.getName());
			try {
				ssoVm.shutdownGuest();
			
				this.waitting();
				do {
					Thread.sleep(2000);
					end = System.currentTimeMillis();
					powerState = ssoVm.getSummary().getRuntime().getPowerState();
					if ((end - start) >= 180000) {
						System.out.println("Graceful shutdown failed. Now try a hard power off.");
						this.doVmOff();
						break;
					}
				} while (!powerState.toString().equals("poweredOff"));
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				System.out.println("Exception: The virtual machine is poweroff now! Can not shutdown the guestOS!");
				// e.printStackTrace();
			}
		}
		// catch (RemoteException e) {
		// TODO Auto-generated catch block
	}

	// System.out.println("Shutdown guest: completed, time = " + getTime());
	

	// Wait until the task completes
	public void waitting() throws InvalidProperty, RuntimeFault, RemoteException, InterruptedException {
		VirtualMachine ssoVm = (VirtualMachine) new InventoryNavigator(this.root).searchManagedEntity("VirtualMachine",
				this.trieCmd.getName());
		Task[] taskList = ssoVm.getRecentTasks();
		do {
			Thread.sleep(500);
		} while (taskList[taskList.length - 1].getTaskInfo().getState() == TaskInfoState.running);
	}

	// print the time now;
	public String getTime() {
		String result = "";
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Calendar calobj = Calendar.getInstance();
		result = df.format(calobj.getTime());
		return result;
	}
}
