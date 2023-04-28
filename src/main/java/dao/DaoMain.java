package dao;

import java.sql.Statement;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dao.dao.*;
public class DaoMain {

	
	public static void main(String[] args) {
		// les utilisateurs :
		User user = new User();
		
		user.setLogin("dino");
		user.setFirstname("Alexandre");
		user.setLastname("Legrand");
		user.setPwd("root");
		
		User user2=new User();
		
	    user2.setFirstname("Jean-Bernard");
	    user2.setLastname("Buffalo");
	    user2.setLogin("bbuffalo");
	    user2.setPwd("root2");
	        
	    User user3=new User();
	        
	    user3.setFirstname("Jean-Yves");	        
	    user3.setLastname("Doeuf");
	    user3.setLogin("jdoeuf");
	    user3.setPwd("root3");
	    
	    User user4 = new User();
	    user4.setFirstname("Smith");	        
	    user4.setLastname("Alice");
	    user4.setLogin("asmith");
	    user4.setPwd("password2");
	    
	    User user5 = new User();
	    user5.setFirstname("Williams");	        
	    user5.setLastname("Robert");
	    user5.setLogin("rwilliams");
	    user5.setPwd("password3");
	    
	    User user6 = new User();
	    user6.setFirstname("Johnson");	        
	    user6.setLastname("Karen");
	    user6.setLogin("kjohnson");
	    user6.setPwd("password4");
		
	    IDAO<User> userDAO = UserDAO.getInstance();
//	    userDAO.create(user);
//	    userDAO.create(user2);
//	    userDAO.create(user3);
//	    userDAO.create(user4);
//	    userDAO.create(user5);
//	    userDAO.create(user6);
	    
	    
	    IDAO<User> userDAO2 = UserDAO.getInstance();
	    String log = "jdoeuf";
	    String pwd = "root3";
	    User userBy = userDAO2.readByLoginAndPwd(log,pwd );
	    
	    if(userBy != null) {
	    	System.out.println("Utilisateur trouvé");
	    	System.out.println("login : " + userBy.getLogin());
	    	System.out.println("pwd : " + userBy.getPwd()); 
	    	System.out.println("pwd : " + userBy.getFirstname()); 
	    	System.out.println("pwd : " + userBy.getLastname()); 
	    	
	    }else {
	    	System.out.println("Utilisateur Introuvable ");
	    }
	    
	    User u2 = userDAO2.read(4);
	    
	    if(u2 != null) {
	    	System.out.println("id trouvé");
	    	System.out.println("login : " + u2.getLogin());
	    	System.out.println("pwd : " + u2.getPwd()); 
	    	System.out.println("pwd : " + u2.getFirstname()); 
	    	System.out.println("pwd : " + u2.getLastname()); 
	    	
	    }else {
	    	System.out.println("id Introuvable ");
	    }
	    
	    
	    System.out.println("Tous les infos : ");
	    System.out.println(userDAO2.readAll().toString());
	    
	    User userDelete = userDAO2.read(34);
	    if(userDelete != null) {
		    System.out.println("Tous les infos apres le delete : ");
		    userDAO2.delete(userDelete);
	    }
	    System.out.println(userDAO2.readAll());
	    
		
		
	}

}
