/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Cliente;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Gustavo
 */
public class ClienteDao {

    public void salvar(Cliente cliente) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.saveOrUpdate(cliente);
        session.getTransaction().commit();
    }

    public List<Cliente> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        List<Cliente> lista;
        Query query = session.createQuery("from entity.Cliente");
        lista = query.list();
        session.getTransaction().commit();
        return lista;
    }
    
    public void excluir(Cliente cliente) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.delete(cliente);
        session.getTransaction().commit();
    }

}
