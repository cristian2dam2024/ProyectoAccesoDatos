package utils;
import org.corella.AccesoDatos.utilsAcceso.Constantes;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.io.File;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    // Código estático. Sólo se ejecuta una vez, como un Singleton
    static {
        try {
            // Creamos es SessionFactory desde el fichero hibernate.cfg.xml 
        	
        	File configFile = new File(Constantes.hibernateConfigFile);
        	
        	if(configFile.exists()) {
        		System.out.println("Aplicando fichero de configuración");
        		sessionFactory = new Configuration().configure(configFile).buildSessionFactory();
        	} else {
        		sessionFactory = null;
        	}
                
        } catch (Throwable ex) {
            System.err.println("Error en la inicialización.  " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
