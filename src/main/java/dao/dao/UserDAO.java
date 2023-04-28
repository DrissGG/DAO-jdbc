package dao.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import dao.dao.*;

public class UserDAO implements IDAO<User>{
	
	private final static UserDAO INSTANCE = new UserDAO();
	public final static String CREATE_USER_SQL = "INSERT INTO user (login,pwd,lastname,firstname) VALUES(?,?,?,?)";
	public final static String FIND_USER_BY_LOG_AND_PWD = "SELECT login,pwd,lastname,firstname FROM user WHERE login = ? AND pwd = ?";
	public final static String FIND_USER_SQL = "SELECT login,pwd,lastname,firstname FROM user WHERE id = ?";
	public final static String FIND_USER_ALL_SQL = "SELECT login,pwd,lastname,firstname FROM user";
	public final static String DELETE_USER_SQL = "DELETE FROM user WHERE id=?";
	
	Statement st = null;
	PreparedStatement ps =null; 
	
	public static UserDAO getInstance() {
		return INSTANCE;
	}
	private UserDAO() {
		//Le constructeur de la classe est privé, 
		//ce qui signifie qu'il n'est pas possible de créer de nouvelles instances de la classe à l'extérieur de la classe elle-même.
	}

	public User create(User user) {
		// PreparedStatement pour exécuter la requête SQL INSERT_USER 
		
		try {
			ps = JdbcSingleton.getInstance().getConnexion().prepareStatement(CREATE_USER_SQL,PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getLogin());
			ps.setString(2, user.getPwd());
			ps.setString(3, user.getLastname());
			ps.setString(4, user.getFirstname());
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				user.setId(rs.getInt(1));
 			}
			if(rs!=null) {
				rs.close();
			}
			if(ps!=null) {
				ps.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	return user;
	}

	public User readByLoginAndPwd(String login, String pwd) {
		User u = null;
	
		try {
			ps = JdbcSingleton.getInstance().getConnexion().prepareStatement(FIND_USER_BY_LOG_AND_PWD);
			ps.setString(1, login);
			ps.setString(2, pwd);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				u = new User();
				u.setLogin(rs.getString("login"));
	            u.setPwd(rs.getString("pwd"));
	            u.setLastname(rs.getString("lastname"));
	            u.setFirstname(rs.getString("firstname"));
			
			}
			if(rs!=null) {
				rs.close();
			}
			if(ps!=null) {
				ps.close();
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}

	public User read(Integer id) {
		// TODO Auto-generated method stub
		User u = null;
		
		try {
			ps = JdbcSingleton.getInstance().getConnexion().prepareStatement(FIND_USER_SQL);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				u = new User();
				u.setId(id);
				u.setLogin(rs.getString("login"));
	            u.setPwd(rs.getString("pwd"));
	            u.setLastname(rs.getString("lastname"));
	            u.setFirstname(rs.getString("firstname"));
			}
			if(rs!=null) {
				rs.close();
			}
			if(ps!=null) {
				ps.close();
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}

	public void update(User t) {
		// TODO Auto-generated method stub
		
	}

	public void delete(User user) {
		
		try {
			ps = JdbcSingleton.getInstance().getConnexion().prepareStatement(DELETE_USER_SQL);
			ps.setInt(1, user.getId());
			
			int n = ps.executeUpdate();
			System.out.println("nombre de lignes supprimées : " +n);
			
			if(ps!=null) {
				ps.close();
			}		
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public List<User> readAll() {
		List<User> u = new ArrayList<>();
		
		try {
			ps = JdbcSingleton.getInstance().getConnexion().prepareStatement(FIND_USER_ALL_SQL);		
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {				
				User user = new User();
				user.setLogin(rs.getString("login"));
	            user.setPwd(rs.getString("pwd"));
	            user.setLastname(rs.getString("lastname"));
	            user.setFirstname(rs.getString("firstname"));
	            
	            u.add(user);
			}
			if(rs!=null) {
				rs.close();
			}
			if(ps!=null) {
				ps.close();
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}

}
