package dao;

import entity.Servico;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class ServicoDao {

    public void salvar(Servico servico) {
        HibernateUtility.beginTransaction();
        HibernateUtility.getSession().saveOrUpdate(servico);
        HibernateUtility.commitTransaction();
    }

    public List<Servico> getAll() {
        //        Session session = HibernateUtil.getSessionFactory().openSession();
//        session.getTransaction().begin();
        List<Servico> lista;
        // Query query = session.createQuery("from entity.Cliente");
        Query query = HibernateUtility.getSession().createQuery("from entity.Servico");
        lista = query.list();
        return lista;
    }

    public void excluir(Servico servico) {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        session.getTransaction().begin();
//        session.delete(servico);
//        session.getTransaction().commit();
        HibernateUtility.beginTransaction();
        HibernateUtility.getSession().delete(servico);
        HibernateUtility.commitTransaction();
    }
}
