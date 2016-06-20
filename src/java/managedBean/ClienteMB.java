package managedBean;

import dao.ClienteDao;
import entity.Cliente;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "clienteMB")
@SessionScoped
public class ClienteMB implements Serializable {

    private ClienteDao clienteDao = new ClienteDao();
    private Cliente cliente = new Cliente();

    public List<Cliente> getLista() {
        return clienteDao.getAll();
    }

    public String excluir() {
        clienteDao.excluir(cliente);
        cliente = null;
        return "listCliente.xhtml";
    }

    public String editar() {
        return "editCliente.xhtml";
    }

    public String salvar() {
        clienteDao.salvar(cliente);
        return "listCliente.xhtml";
    }

    public String novoCliente() {
        cliente = new Cliente();
        return "editCliente.xhtml";
    }

    public String cancelarEdicao() {
        this.cliente = null;
        return "listCliente.xhtml";
    }

    public String listar() {
        return "listCliente.xhtml";
    }
    
    public List<Integer> getListaBranco(){
        List<Integer> lista = new ArrayList();
        lista.add(0);
        lista.add(10);
        lista.add(20);
        lista.add(30);
        lista.add(40);
        lista.add(50);
        lista.add(60);
        lista.add(70);
        lista.add(80);
        lista.add(90);
        lista.add(100);
        return lista;
    }
    
    public List<String> getListaTipoCabelo(){
        List<String> lista = new ArrayList();
        lista.add("Liso");
        lista.add("Crespo");
        lista.add("Cacheado");               
        return lista;
    }
    public List<String> getListaAlisamento(){
        List<String> lista = new ArrayList();
        lista.add("Progressiva");
        lista.add("Plástica dos fios");
        lista.add("Definitiva");        
        return lista;
    }

    public ClienteDao getClienteDao() {
        return clienteDao;
    }

    public void setClienteDao(ClienteDao clienteDao) {
        this.clienteDao = clienteDao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}
