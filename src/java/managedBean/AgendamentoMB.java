package managedBean;

import dao.AgendamentoDao;
import dao.ServicoDao;
import entity.Agendamento;
import entity.Servico;
import java.io.Serializable;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.component.schedule.Schedule;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.DualListModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

@ManagedBean(name = "agendamentoMB")
@SessionScoped
public class AgendamentoMB implements Serializable {

    private Agendamento agendamento;
    private ScheduleModel agendamentoModel;
    private List<Agendamento> listaAgendamento;
    private AgendamentoDao agendamentoDao = new AgendamentoDao();
    private DualListModel<Servico> servicoDualList = new DualListModel();
    private List<String> listaServicoToView = new ArrayList();
    private ServicoDao servicoDao;

    @PostConstruct
    public void inicializar() {
        agendamentoDao = new AgendamentoDao();
        agendamento = new Agendamento();
        agendamentoModel = new DefaultScheduleModel();
        
        listaAgendamento = agendamentoDao.getAll();

        for (Agendamento ag : listaAgendamento) {
            DefaultScheduleEvent evt = new DefaultScheduleEvent();

            evt.setEndDate(ag.getDataFim());
            evt.setStartDate(ag.getDataInicio());
            evt.setTitle(ag.getTitulo());
            evt.setData(ag.getId());
//            evt.setDescription(ag.getDescricao());
            evt.setAllDay(false);
            evt.setEditable(true);

            if (ag.isStatus() == true) {
                evt.setStyleClass("emp1");
            } else if (ag.isStatus() == false) {
                evt.setStyleClass("emp2");
            }

            agendamentoModel.addEvent(evt);
        }
    }

    public String listar() {
        return "agendamento.xhtml";
    }

    public void quandoSelecionado(SelectEvent selectEvent) {

        ScheduleEvent event = (ScheduleEvent) selectEvent.getObject();

        for (Agendamento ag : listaAgendamento) {
            if (Objects.equals(ag.getId(), event.getData())) {/* 'if' compara 'getId' c/ 'getData()', 'getData' recebe valor do
                 'selectEvent.getObject();' que atribui em 'event', e compara c/ 'getId' que recebe valor temporário da 'listaAgendamento'. 
                 Após verificação da condição 'if', resultando em igualdade é executado a atribuição de 'ev' em 'event' */

                agendamento = ag;
                break; //'Stop' após verificar a primeira condição, para não dar continuidade no laço de repetição!
            }
        }
    }

    public void quandoNovo(SelectEvent selectEvent) {

        ScheduleEvent event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());

        agendamento = new Agendamento();
        agendamento.setDataInicio(new Timestamp(event.getStartDate().getTime()));
        agendamento.setDataFim(new Timestamp(event.getEndDate().getTime()));        
        System.out.println(new Timestamp(event.getEndDate().getTime()));
        //agendamento.setInicio(new java.sql.Date(event.getStartDate().getTime()));
        //agendamento.setFim(new java.sql.Date(event.getEndDate().getTime()));

    }

    public String salvar() {
//        agendamentoDao.salvar(agendamento);
//        return "agendamento.xhtml";
        if (agendamento.getId() == null) {
            if (agendamento.getDataInicio().getTime() <= agendamento.getDataFim().getTime()) {
                agendamentoDao = new AgendamentoDao();
                try {
                    
                    //zera as competencias da questão atual e salva
                    agendamento.getServico().clear();
                    agendamentoDao.salvar(agendamento);
                    //Adiciona as que foram selecionadas pelo usuario
                    List<Servico> escolhidos = servicoDualList.getTarget();
                    for (Servico umServicoEscolhido : escolhidos) {
                        agendamento.getServico().add(umServicoEscolhido);
                    }
                    agendamentoDao.salvar(agendamento);
                    inicializar();
                } catch (Exception ex) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar agendamento", "Erro: " + ex.getMessage()));
                }
                agendamento = new Agendamento();

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data inicial não pode ser maior que a inicial", ""));
            }
        } else {
            try {
                agendamentoDao.salvar(agendamento);
                inicializar();
            } catch (Exception ex) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar evento", "Erro: " + ex.getMessage()));
            }
        }
        return "agendamento.xhtml";
    }

    public DualListModel<Servico> getServicoDualList() {
        servicoDao = new ServicoDao();
        List<Servico> disponivel = new ArrayList();
        List<Servico> noAgendamento = new ArrayList();

        List<Servico> todosServicos = servicoDao.getAll();
        List<Servico> servicosDoAgendamento = agendamento.getServico();

        for (Servico s : todosServicos) {
            if (!servicosDoAgendamento.contains(s)) {
                disponivel.add(s);
            } else {
                noAgendamento.add(s);
            }
        servicoDualList.setSource(disponivel);
        servicoDualList.setTarget(noAgendamento);
        }
        return servicoDualList;
    }

    public void setServicoDualList(DualListModel<Servico> servicoDualList) {
        this.servicoDualList = servicoDualList;
    }

    public void onTransfer(TransferEvent event) {
        if (event.isAdd()) {
            for (Object item : event.getItems()) {
                listaServicoToView.add(((Servico) item).getNome());
            }
        }
        if (event.isRemove()) {
            for (Object item : event.getItems()) {
                listaServicoToView.remove(((Servico) item).getNome());
            }
        }
    }

    public void primeiraCargaListaServicoToView() {
        System.out.println("carregou #primeiraCargaListaCompetenciaToView");
        listaServicoToView.clear();
        for (Servico umServico : agendamento.getServico()) {
            listaServicoToView.add(umServico.getNome());
        }
    }

    public void quandoMovido(ScheduleEntryMoveEvent eventoMovido) {
        for (Agendamento ag : listaAgendamento) {
            if (ag.getId() == (Integer) eventoMovido.getScheduleEvent().getData()) {
                agendamento = ag;
                agendamentoDao = new AgendamentoDao();
                try {
                    agendamentoDao.salvar(agendamento);
                    inicializar();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar evento", "Erro: " + ex.getMessage()));
                }
                break;
            }
        }
    }

    public void quandoRedimencionar(ScheduleEntryMoveEvent eventoRedimencionar) {
        for (Agendamento ag : listaAgendamento) {
            if (ag.getId() == (Integer) eventoRedimencionar.getScheduleEvent().getData()) {
                agendamento = ag;
                agendamentoDao = new AgendamentoDao();
                try {
                    agendamentoDao.salvar(agendamento);
                    inicializar();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar evento", "Erro: " + ex.getMessage()));
                }
                break;
            }
        }
    }

    // GETTER E SETTERS
    public List<String> getListaServicoToView() {
        return listaServicoToView;
    }

    public void setListaServicoToView(List<String> listaServicoToView) {
        this.listaServicoToView = listaServicoToView;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }

    public ScheduleModel getAgendamentoModel() {
        return agendamentoModel;
    }

    public void setAgendamentoModel(ScheduleModel agendamentoModel) {
        this.agendamentoModel = agendamentoModel;
    }

    public List<Agendamento> getListaAgendamento() {
        return listaAgendamento;
    }

    public void setListaAgendamento(List<Agendamento> listaAgendamento) {
        this.listaAgendamento = listaAgendamento;
    }

    public AgendamentoDao getAgendamentoDao() {
        return agendamentoDao;
    }

    public void setAgendamentoDao(AgendamentoDao agendamentoDao) {
        this.agendamentoDao = agendamentoDao;
    }

}
