package com.adailsilva.pfgrafica.dao;

import java.util.List;

import com.adailsilva.pfgrafica.model.Entidade;


public interface EntidadesDao {

    public void adicionar(Entidade entidade);

    public void remover(Entidade entidade);

    public void atualizar(Entidade entidade);

    public Entidade pesquisarId(int id);

    public List<Entidade> listarTodos();
}
