package com.adailsilva.pfgrafica.controller;

import com.adailsilva.pfgrafica.dao.EntidadesDaoImp;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.adailsilva.pfgrafica.model.Entidade;

@ManagedBean
@SessionScoped
public class EntidadeMB {

    @ManagedProperty(value = "#{usuarioMB}")
    private UsuarioMB usuarioMB;

    private Entidade entidade;
    private List<Entidade> entidades;
    private EntidadesDaoImp dao;

    public EntidadeMB() throws Exception {
        entidade = new Entidade();
        entidades = new ArrayList<>();
    }

    public Entidade getEntidade() {
        return entidade;
    }

    public void setEntidade(Entidade entidade) {
        this.entidade = entidade;
    }

    public List<Entidade> getEntidades() {

        if (entidade != null) {
            dao = new EntidadesDaoImp();
            this.dao.usuarioLogado(getUsuarioMB().getUsuario().getNome(),
                    getUsuarioMB().getUsuario().getSenha());
            entidades = dao.listarTodos();
        }

        return entidades;
    }

    public void setEntidades(List<Entidade> entidades) {
        this.entidades = entidades;
    }

    public void add() {
        try {
            dao = new EntidadesDaoImp();
            
            entidade.setCriadoPor(usuarioMB.usuarioDaSessao()+usuarioMB.senhaUsuarioDaSessao());
            if (entidade.getId() != null) {
                dao.atualizar(entidade);
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                " Cliente  Atualizado " + getEntidade().getNome(), null));
            } else {
                dao.adicionar(entidade);
                entidades.add(entidade);
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                " Cliente  Salvo " + getEntidade().getNome(), null));
            }

            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            " Cliente  Salvo " + getEntidade().getNome(), null));
            entidade = getInstancia();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            " Não foi possível salvar o Cliente " + getEntidade().getNome(), null));
            entidade = getInstancia();
        }
    }

    public void remover() {
        try {
            dao = new EntidadesDaoImp();
            dao.remover(entidade);
            entidades.remove(entidade);

            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            " Cliente " + getEntidade().getNome() + " removido com sucesso!", null));
            entidade = getInstancia();

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            " Não foi possível remover o Cliente " + getEntidade().getNome(), null));
            entidade = getInstancia();
        }
    }

    private Entidade getInstancia() {
        return new Entidade();
    }
    
    /*
    public List<String> complete(String input) {
        return new EntidadesDaoImp().clientesQueContem(input);
    } */

    public void consultar() {
        entidades = getEntidades();
    }

    public void preCadastro() {
        entidades = getEntidades();
    }

    public void clear() {
        entidade = new Entidade();
    }

    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();

        if ("admin".equals(entidade.getNome().trim()) && "@123".equals(entidade.getSenha().trim())) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            " Bem-Vindo " + getEntidade().getNome() + " !!", null));
            return "/consultar-titulo?faces-redirect=true";

        } else {
            FacesMessage mensagem = new FacesMessage(
                    "Usuário/senha inválidos!");
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }
        return null;
    }

    public List<String> completeCidades(String input) {

        List<String> cidades = new ArrayList<>(); // Lista das Cidades 
        List<String> cidadesEncontradas = new ArrayList<>(); // Cidades encontradas na lista

        // Ceará
        cidades.add("Tianguá".toUpperCase());
        cidades.add("Viçosa do Ceará".toUpperCase());
        cidades.add("Ubajara".toUpperCase());
        cidades.add("Ibiapina".toUpperCase());
        cidades.add("São Benedito".toUpperCase());
        cidades.add("Guaraciaba do Norte".toUpperCase());
        cidades.add("Carnaubal".toUpperCase());
        cidades.add("Croatá".toUpperCase());
        cidades.add("Sobral".toUpperCase());

        // Piauí
        cidades.add("Altos".toUpperCase());
        cidades.add("Cocal da Estação".toUpperCase());
        cidades.add("Parnaíba".toUpperCase());
        cidades.add("Piripiri".toUpperCase());
        cidades.add("São João da Fronteira".toUpperCase());
        cidades.add("Teresina".toUpperCase());

        //cidades.stream().filter((listCidades)
          //      -> (listCidades.contains(input.toUpperCase()))).forEach(cidadesEncontradas::add);
        
        for(String c : cidades){
            if(c.contains(input.toUpperCase())){
                cidadesEncontradas.add(c);
            }
        }
        
        return cidadesEncontradas;
    }

    public String updateCliente() {
        return "/form-novo-cliente";
    }

    public UsuarioMB getUsuarioMB() {
        return usuarioMB;
    }

    public void setUsuarioMB(UsuarioMB usuarioMB) {
        this.usuarioMB = usuarioMB;
    }
}
