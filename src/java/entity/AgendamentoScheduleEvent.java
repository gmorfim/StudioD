/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import org.primefaces.model.ScheduleEvent;

/**
 *
 * @author Gustavo
 */
public class AgendamentoScheduleEvent implements ScheduleEvent, Serializable{

    private Agendamento agendamento;

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }
    
    @Override
    public String getId() {
       return agendamento.getId().toString();
    }

    @Override
    public void setId(String string) {
        //nada
    }

    @Override
    public Object getData() {
        return null;
    }

    @Override
    public String getTitle() {
        return agendamento.getTitulo();
    }

    @Override
    public Date getStartDate() {
        return agendamento.getDataInicio();
    }

    @Override
    public Date getEndDate() {
        return agendamento.getDataFim();
    }

    @Override
    public boolean isAllDay() {
        return false;
    }

    @Override
    public String getStyleClass() {
        return "";
    }

    @Override
    public boolean isEditable() {
        return false;
    }

    @Override
    public String getDescription() {
        return agendamento.getDescricao();
    }
    
}


