package com.client;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import com.project.dao.IphoneRemote;
import com.project.dao.IpositionRemote;
import com.project.dao.IuserRemote;


public class EjbConnection {
	private static IpositionRemote rposition;
	private static IphoneRemote rSphone;
	private static IuserRemote rUser;
	
	private static void connect(){
		Hashtable<Object, Object> jndiProperties = new Hashtable<Object, Object>();

		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		try {
		final Context context = new InitialContext(jndiProperties);

		rUser = (IuserRemote) context.lookup("ejb:webEar/serveurEjb/USER!com.project.dao.IuserRemote");
		rSphone = (IphoneRemote) context.lookup("ejb:webEar/serveurEjb/SPHONE!com.project.dao.IphoneRemote");
		rposition = (IpositionRemote) context.lookup("ejb:webEar/serveurEjb/POSITION!com.project.dao.IpositionRemote");
		}catch (Exception e) {
			System.out.println("probleme");
		}

	}

	public static IpositionRemote getRposition() {
		connect();
		return rposition;
	}

	public static IphoneRemote getrSphone() {
		connect();
		return rSphone;
	}

	public static IuserRemote getrUser() {
		connect();
		return rUser;
	}
	
	
	

	public static void main(String[] args) throws Exception {
		
	/*	User u = new User("hafid", "abbassi", "0606066", "hafid8live@hotmail.fr");
		Smartphone s = new Smartphone("qewq23232");
		Position p = new Position("we3223233", "ew323233", new Date());

		s.setPositions(Arrays.asList(p));
		s.setUser(u);
		
		u.setSmartphones(Arrays.asList(s));
		p.setSmartphone(s);*/
	//	connect();
		/*
		 * User u = new User("hafid", "abbassi", "0606066", "hafid8live@hotmail.fr");
		 * rUser.create(u)
		 */;
		
		
		//  rUser.findAll().forEach(x->{System.out.println(x);});
		 
	}
}
