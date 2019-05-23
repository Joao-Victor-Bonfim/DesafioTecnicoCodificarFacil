package desafio.controller.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author João Victor Bonfim
 */
public interface DAOConstants {

    public static final String NOME_PU = "Oficina_2.0PU";
    public static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory(NOME_PU);
}
