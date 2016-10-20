package com.vijava_HW.cmd;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import org.junit.Test;

import com.vmware.vim25.mo.Folder;
import com.vmware.vim25.mo.HostSystem;
import com.vmware.vim25.mo.InventoryNavigator;
import com.vmware.vim25.mo.ServiceInstance;

public class TestVIjavaAPI {

	@Test
	public void test() throws RemoteException, MalformedURLException {
		//fail("Not yet implemented");
		ServiceInstance service = new ServiceInstance(new URL("https://130.65.159.14/sdk"), "cmpe281_sec3_student@vsphere.local", "cmpe-LXKN", true);
		Folder root = service.getRootFolder();
		HostSystem ssoHost = (HostSystem)new InventoryNavigator(root).searchManagedEntity("HostSystem", "cmpe281_sec3_student@vsphere.local");
		System.out.println("Name = " + ssoHost.getName());
	}
	
}
