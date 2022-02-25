/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import modelo.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Rodrigo
 */
public class NominaDAO {
    
    SessionFactory sf = null;
    Session sesion = null;
    Transaction tx = null;

    public NominaDAO(){
        sf = HibernateUtil.getSessionFactory();
        sesion = sf.openSession();

}
    
    public void eliminarNominasInferiores(double valor){
            tx = sesion.beginTransaction();
            String HQLborrado = "DELETE Nomina n WHERE n.brutoNomina<:param1";
            sesion.createQuery(HQLborrado).setParameter("param1", valor).executeUpdate();

            tx.commit();
    }
    
}
