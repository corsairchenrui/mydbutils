package MySQL.DAO;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class DAOFactory {
	
	
	
	protected static class Session{
		protected Connection conn = null;
		protected String driver;
		protected String URL;
		protected String user;
		protected String passwd;
		protected Statement createStatement(){
			try {
				return conn.createStatement();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
		private void load() throws ClassNotFoundException, SQLException {
			Class.forName(driver);
			conn = DriverManager.getConnection(URL, user, passwd);
		}
	}
	public static Statement load(String name){
		if(null != name){
			Properties p = new Properties();
			try {
				p.load(new FileReader(new File(name+".properties")));
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
			Session session = new Session();
			session.driver = p.getProperty("driver");
			session.user = p.getProperty("user");
			session.passwd = p.getProperty("password");
			session.URL = "jdbc:mysql://"+p.getProperty("host")+":"+
						p.getProperty("port")+"/"+p.getProperty("database")+
						"?useUnicode=true&characterEncoding=utf8";
			try {
				session.load();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				return null;
			}
			return session.createStatement();
		}
		return null;
	}
}