package com.adailsilva.pfgrafica.tests;

import com.adailsilva.pfgrafica.conexao.PersistenceManager;
import com.adailsilva.pfgrafica.dao.EventosDaoImp;
import com.adailsilva.pfgrafica.dao.TitulosDaoImp;
import com.adailsilva.pfgrafica.dao.UsuarioDaoImp;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.adailsilva.pfgrafica.model.Evento;
import com.adailsilva.pfgrafica.model.Titulo;
import com.adailsilva.pfgrafica.model.Usuario;


public class TestConexao {

    public static void main(String[] args) throws Exception {
        EntityManager em = PersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        
        TitulosDaoImp dao = new TitulosDaoImp();
        
        em.getTransaction().commit();
        em.close();
    }

    public static <T> Object getObjectSession(String attribute) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession(true);
        return session.getAttribute(attribute);
    }
}
