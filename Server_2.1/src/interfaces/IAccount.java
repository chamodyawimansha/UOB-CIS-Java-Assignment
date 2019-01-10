package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import application.User;

/**
 * RMI interface to enable retrieve account data from the database
 * and to add account data to the database 
 * @author Chamodya Wimansha
 *
 */
public interface IAccount extends Remote{
	/**
	 * Create new Participant and save in the database
	 * returns true if created successfully
	 * @param user
	 * @return
	 * @throws RemoteException
	 */
	public boolean newParticipant(User user) throws RemoteException;
	/**
	 * create a new developer account. this account can login to the system and view survey results
	 * @param user
	 * @return
	 * @throws RemoteException
	 */
	public boolean newDeveloper(User user) throws RemoteException;
	/**
	 * Login to the system.
	 * returns true if the username and password correct
	 * @param username
	 * @param password
	 * @return
	 * @throws RemoteException
	 */
	public boolean login(String username, String password) throws RemoteException;
	/**
	 * update developer password
	 * @param newPassword
	 * @return
	 * @throws RemoteException
	 */
	public boolean updateDeveloperPassword(String username,String newPassword) throws RemoteException;
	
}
