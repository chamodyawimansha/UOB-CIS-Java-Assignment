package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * RMI interface to allow users add and get data from the Session variable
 * @author chamodya wimansha
 *
 */
public interface ISession extends Remote{
	/**
	 * Find objects in the Session Map and return the found
	 * @param txt
	 * @param autoDestroy
	 * @return
	 * @throws RemoteException
	 */
	public Object find(String txt) throws RemoteException;
	/**
	 * Add object to the Session Map
	 * @param obj
	 * @param txt
	 * @param autoDestroy
	 * @return
	 * @throws RemoteException
	 */
	public String add(Object obj, String txt,boolean autoDestroy) throws RemoteException;
	/**
	 * Remove object from the session map
	 * @param txt
	 * @return
	 * @throws RemoteException
	 */
	public boolean destroy(String txt) throws RemoteException;
	/**
	 * 
	 */
	public void ping() throws RemoteException;
	
}
