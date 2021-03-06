package impls;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.User;
import interfaces.IAccount;
import main.Main;
import utils.Helpers;
import utils.Model;
import utils.Securepass;

/**
 * Create a user object or a developer object
 * 
 * @author chamodya wimansha
 *
 */
public class Account extends UnicastRemoteObject implements IAccount{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Model model;
	
	public Account() throws RemoteException {
		model = Main.getMainModel();
	}

	@Override
	public boolean newParticipant(User user) throws RemoteException {
				
		String sql = "INSERT INTO participant_data (full_name, email, country, device_manufacturer, device_os, participated_on) VALUES("
				+ "'" + user.getFull_name() + "',"
				+ "'" + user.getEmail() + "',"
				+ "'" + user.getCountry() + "',"
				+ "'" + user.getDevice_manufacturer() + "',"
				+ "'" + user.getDevice_os() + "',"
				+ "'" + user.getParticipated_on() + "'"
				+ ")";

		return model.EXECUTE(sql);
			
	}

	@Override
	public boolean newDeveloper(User user) throws RemoteException {
		
		/**
		 * check for similer accounts
		 */
		String sql = "SELECT * FROM developers WHERE username='"+user.getEmail()+"';";
		
		ResultSet res = model.SELECT(sql);
		
		try {
			if(res.next()) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		sql = "INSERT INTO developers (username, password, created_on) VALUES("
				+ "'" + user.getEmail() + "',"
				+ "'" + user.getPassword() + "',"
				+ "'" + user.getParticipated_on() + "'"
				+ ")";
		
		return model.EXECUTE(sql);

	}

	@Override
	public boolean login(String username, String password) throws RemoteException {
		
		
		String sql = "SELECT * FROM developers WHERE username = '" + username +  "'";
						
		try {
			
			ResultSet res = model.SELECT(sql);
						
			return new Securepass(password).isSame(res.getString("password"));
			
		} catch (SQLException e) {
			Helpers.Debug("Error!! Can't check username or password. Server Error - " + e.toString());
		}
		
		return false;
	}

	@Override
	public boolean updateDeveloperPassword(String username, String newPassword) throws RemoteException {

		String sql = "UPDATE developers SET password='"+newPassword+"' WHERE username='"+username+"'";
		
		return model.EXECUTE(sql);

	}

}




