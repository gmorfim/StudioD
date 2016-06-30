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
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        session.getTransaction().begin();
//        session.saveOrUpdate(cliente);
//        session.getTransaction().commit();
        HibernateUtility.beginTransaction();
        HibernateUtility.getSession().saveOrUpdate(cliente);
        HibernateUtility.commitTransaction();
    }

    public List<Cliente> getAll() {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        session.getTransaction().begin();
        List<Cliente> lista;
        // Query query = session.createQuery("from entity.Cliente");
        Query query = HibernateUtility.getSession().createQuery("from entity.Cliente");
        lista = query.list();

        return lista;
    }

    public void excluir(Cliente cliente) {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        session.getTransaction().begin();
//        session.delete(cliente);
//        session.getTransaction().commit();
        HibernateUtility.beginTransaction();
        HibernateUtility.getSession().delete(cliente);
        HibernateUtility.commitTransaction();
    }

}
