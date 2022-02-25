/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package priemrapractica;

import DAO.EmpresaDAO;
import DAO.NominaDAO;
import POJOS.Empresas;
import POJOS.Nomina;
import POJOS.Trabajadorbbdd;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import modelo.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;



/**
 *
 * @author Rodrigo
 */
public class PrimeraPractica {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Aplicacion en ejerucion");
        
        Empresas emp1;
        EmpresaDAO empDAO = new EmpresaDAO();
        List<Empresas> listaEmpresas;
        
        emp1 = empDAO.consultarEmpresaPorCIF("P2472621I");
        
            Set<Trabajadorbbdd> listaTrabajadores =  emp1.getTrabajadorbbdds();
            
            Set<Nomina> listaNominasCompleta = new HashSet<Nomina>();
            for (Trabajadorbbdd tbd:listaTrabajadores){
               System.out.println("Nombre del trabajador" + tbd.getNombre() + " " + tbd.getApellido1() + " " + tbd.getApellido2() + " " + tbd.getCategorias().getNombreCategoria());
               Set<Nomina> listaNominas = tbd.getNominas();
               listaNominasCompleta.addAll(listaNominas);
               System.out.println("Numero de nominas del trabajador: " + listaNominas.size());
            }
                       
            
            listaEmpresas = empDAO.empresasExceptoLaPasada(emp1);
            
            //Actualizamos el nombre de las empresas como pide la práctica
            empDAO.actualizarEmpresas(listaEmpresas);
            
   
   
        double total=0;
        for(Nomina n:listaNominasCompleta){
            total += n.getBrutoNomina();
        
        }
            double media = total/listaNominasCompleta.size();
            
            System.out.println("Van a ser eliminadas las nóminas cuyo valor sea inferior a " + media);
            
            NominaDAO nomina = new NominaDAO();
            nomina.eliminarNominasInferiores(media);
            

            HibernateUtil.shutdown();

            
    }
    
}
