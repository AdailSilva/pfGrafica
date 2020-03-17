package com.adailsilva.pfgrafica.dao;

import java.util.List;

import com.adailsilva.pfgrafica.model.Titulo;


public interface TitulosDao {

    public void adicionar(Titulo titulo);

    public void remover(Titulo titulo);

    public void atualizar(Titulo titulo);

    public Titulo pesquisarId(int id);

    public List<Titulo> listarTodos();

}
