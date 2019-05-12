/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.davido.ejb;

import com.davido.entities.DbpostCode;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author davidortega
 */
@Stateless
public class DbpostCodeFacade extends AbstractFacade<DbpostCode> implements DbpostCodeFacadeLocal {

    @PersistenceContext(unitName = "com.davido_LookAndFeel_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DbpostCodeFacade() {
        super(DbpostCode.class);
    }

    @Override
    public List<DbpostCode> findAllRegional() {
        String querySTR;
        List<DbpostCode> resultantList = new ArrayList<>();
        try {
            querySTR = "FROM DbpostCode p "
                    + "WHERE p.dbpostCodePK.postCodeId NOT BETWEEN '3000' AND '3207' "
                    + "ORDER BY p.postCodeName";
            Query query = em.createQuery(querySTR);
            resultantList = query.getResultList();
        } catch (Exception e) {
            System.err.println(e);
        }
        return resultantList;
    }

}
