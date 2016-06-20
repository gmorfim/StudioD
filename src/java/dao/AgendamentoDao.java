/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Agendamento;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Gustavo
 */
public class AgendamentoDao {
    
     public void salvar(Agendamento agendamento) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.saveOrUpdate(agendamento);
        session.getTransaction().commit();
    }

    public List<Agendamento> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        List<Agendamento> lista;
        Query query = session.createQuery("from entity.Agendamento");
        lista = query.list();
        session.getTransaction().commit();
        return lista;
    }
    
    public void excluir(Agendamento agendamento) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.delete(agendamento);
        session.getTransaction().commit();
    }
    
}
