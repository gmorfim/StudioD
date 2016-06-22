package managedBean;

import dao.ServicoDao;
import entity.Servico;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "servicoMB")
@SessionScoped
public class ServicoMB implements Serializable{
    
     private ServicoDao servicoDao = new ServicoDao();
    private Servico servico = new Servico();

    public List<Servico> getLista() {
        return servicoDao.getAll();
    }

    public String excluir() {
        servicoDao.excluir(servico);
        servico = null;
        return "listServico.xhtml";
    }

    public String editar() {
        return "editServico.xhtml";
    }

    public String salvar() {
        servicoDao.salvar(servico);
        return "listServico.xhtml";
    }

    public String novoServico() {
        servico = new Servico();
        return "editServico.xhtml";
    }

    public String cancelarEdicao() {
        this.servico = null;
        return "listServico.xhtml";
    }

    public String listar() {
        return "listServico.xhtml";
    }

    
    //GETTERS E SETTERS
    public ServicoDao getServicoDao() {
        return servicoDao;
    }

    public void setServicoDao(ServicoDao servicoDao) {
        this.servicoDao = servicoDao;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }
    
}
