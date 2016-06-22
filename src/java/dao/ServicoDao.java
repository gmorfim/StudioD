package dao;

import entity.Servico;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class ServicoDao {
     public void salvar(Servico servico) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.saveOrUpdate(servico);
        session.getTransaction().commit();
    }

    public List<Servico> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        List<Servico> lista;
        Query query = session.createQuery("from entity.Servico");
        lista = query.list();
        session.getTransaction().commit();
        return lista;
    }
    
    public void excluir(Servico servico) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.delete(servico);
        session.getTransaction().commit();
    }
}
