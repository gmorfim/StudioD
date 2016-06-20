package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.Type;

@Entity
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nome;
    private String email;
    private String endereco;
    private String bairro;
    private Integer numero;
    private String telefone;
    private String celular;
    @Type(type="true_false")
    private boolean alergia;
    @Type(type="true_false")
    private boolean cabelo;
    private String tipoCabelo;
    private Integer branco;
    private String alisamento;

    public Cliente() {
    }

    //Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoCabelo() {
        return tipoCabelo;
    }

    public void setTipoCabelo(String tipoCabelo) {
        this.tipoCabelo = tipoCabelo;
    }

    public Boolean getAlergia() {
        return alergia;
    }

    public void setAlergia(Boolean alergia) {
        this.alergia = alergia;
    }

    public Integer getBranco() {
        return branco;
    }

    public void setBranco(Integer branco) {
        this.branco = branco;
    }

    public Boolean getCabelo() {
        return cabelo;
    }

    public void setCabelo(Boolean cabelo) {
        this.cabelo = cabelo;
    }

    public String getAlisamento() {
        return alisamento;
    }

    public void setAlisamento(String alisamento) {
        this.alisamento = alisamento;
    }

}
