/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;



import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

public class HibernateUtility {

    private static final SessionFactory factory;
    private static final ThreadLocal sessionThread = new ThreadLocal();
    private static final ThreadLocal transactionThread = new ThreadLocal();

    public static Session getSession() {
        Session session = (Session) sessionThread.get();
        if ((session == null) || (!(session.isOpen()))) {
            session = factory.openSession();
            sessionThread.set(session);
        }
        return ((Session) sessionThread.get());
    }

    public static void closeSession() {
        Session session = (Session) sessionThread.get();
        if ((session != null) && (session.isOpen())) {
            sessionThread.set(null);
            session.close();
        }
    }

    public static void beginTransaction() {
        Transaction transaction = getSession().beginTransaction();
        transactionThread.set(transaction);
    }

    public static void commitTransaction() {
        Transaction transaction = (Transaction) transactionThread.get();
        if ((transaction != null) && (!(transaction.wasCommitted())) && (!(transaction.wasRolledBack()))) {
            transaction.commit();
            transactionThread.set(null);
        }
    }

    public static void rollbackTransaction() {
        Transaction transaction = (Transaction) transactionThread.get();
        if ((transaction != null) && (!(transaction.wasCommitted())) && (!(transaction.wasRolledBack()))) {
            transaction.rollback();
            transactionThread.set(null);
        }
    }

    static {
        try {
            factory = new Configuration()
                    //                    /***MYSQL***/
                    //TODO Lembrar de parametrar de outro modo
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect") // tipo de dialeto do banco
                    .setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver") // driver do banco
                    .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/studiod") // endereço do banco de dados
                    .setProperty("hibernate.connection.username", "root")
                    .setProperty("hibernate.connection.password", "")
                    .setProperty("hibernate.hbm2ddl.auto", "update")
                    .setProperty("hibernate.c3p0.max_size", "10")
                    .setProperty("hibernate.c3p0.min_size", "2")
                    .setProperty("hibernate.c3p0.timeout", "5000")
                    .setProperty("hibernate.c3p0.max_statements", "10")
                    .setProperty("hibernate.c3p0.idle_test_period", "3000")
                    .setProperty("hibernate.c3p0.acquire_increment", "2")
                    .setProperty("show_sql", "true")
                    .setProperty("use_outer_join", "true")
                    .setProperty("hibernate.generate_statistics", "true")
                    .setProperty("hibernate.use_sql_comments", "true")
                    .setProperty("hibernate.format_sql", "true")
                    //CADASTROS abaixo coloque todas classes que deseja ser modelo para criação do banco de dados
                    .addAnnotatedClass(entity.Cliente.class)
                    .addAnnotatedClass(entity.Agendamento.class)
                    .addAnnotatedClass(entity.Servico.class)
                    //MOVIMENTOS
                    .buildSessionFactory();
        } catch (RuntimeException e) {
            System.out.println("Erro " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public static void main(String[] args) {
//TODO Usado para teste de conexao SOMENTE
//        try{
//            List lista = new ArrayList();
//        
//            Criteria crit = HibernateUtility.getSession().createCriteria(Obra.class)
//            .add(Restrictions.eq("clienteIdcliente.idcliente", 28));
//            lista = (List) crit.list();
//
//            List<Obra> obras = lista;
//            for (Obra obra : obras) {
//                System.out.println("Nome " +obra.getNome());
//            }
//            System.out.println("FIM");
//        }catch(Exception e){
//            System.out.println("Erro " + e.getMessage());
//        }
    }

}
