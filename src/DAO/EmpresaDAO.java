/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import POJOS.Empresas;
import java.util.List;
import modelo.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Rodrigo
 */
public class EmpresaDAO {
    
    SessionFactory sf = null;
    Session sesion = null;
    Transaction tx = null;

    public EmpresaDAO(){
        sf = HibernateUtil.getSessionFactory();
        sesion = sf.openSession();

}
    /**DEVUELVE LA EMPRESA DEL CIF PASADO. SI NO SE ENCUENTRA, DEVUELVE NULL**/
    public Empresas consultarEmpresaPorCIF(String CIF){
    
        Empresas emp1 = new Empresas();
        try{
        
            emp1.setCif(CIF);
            
            List<Empresas> listaEmpresas = null;
            String HQL="FROM Empresas e WHERE e.cif = :param1";
            Query query = sesion.createQuery(HQL);
            query.setParameter("param1", emp1.getCif());
            listaEmpresas = query.list();
            if (!listaEmpresas.isEmpty()) emp1 = listaEmpresas.get(0);
                                         else emp1 = null;
            System.out.println("Empresa " + emp1.getNombre() + "CIF: " + emp1.getCif());
            
            
        }
        catch(Exception e){
            System.out.println("Error al consultar la empresa en base de datos " + e.getMessage());
        }
        
        return(emp1);
    
    }
    
    
    public List<Empresas> empresasExceptoLaPasada(Empresas emp){
        
    
            List<Empresas> listaEmpresas;
            
            String HQL = "FROM Empresas e WHERE e.cif <> :param1";
            Query query = sesion.createQuery(HQL);
            query.setParameter("param1", emp.getCif());
            listaEmpresas = query.list();
    
            return(listaEmpresas);
    }
    
    public void actualizarEmpresas(List<Empresas> listaEmpresas){
    
            for (Empresas emp:listaEmpresas){
                emp.setNombre(emp.getNombre()+"2022");
            }
            
            tx=sesion.beginTransaction();
            
            for (Empresas emp:listaEmpresas){
                sesion.update(emp);
            }
            tx.commit();
    
    }
    
}
