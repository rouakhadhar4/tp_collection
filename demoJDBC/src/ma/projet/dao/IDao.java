package ma.projet.dao;
import java.util.List;

import ma.projet.beans.client;

public interface IDao {
	

	
	    boolean create(client o);
	    boolean delete(client o);
	    boolean update(client o);
	    client findById(int id);
	    List<client> findAll();
	}


