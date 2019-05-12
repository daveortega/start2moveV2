/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.davido.ejb;

import com.davido.entities.DbHousebuyingTrend;
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
public class DbHousebuyingTrendFacade extends AbstractFacade<DbHousebuyingTrend> implements DbHousebuyingTrendFacadeLocal {

    @PersistenceContext(unitName = "com.davido_LookAndFeel_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DbHousebuyingTrendFacade() {
        super(DbHousebuyingTrend.class);
    }

    @Override
    public List<Object[]> findTrends(String postcode, String postCodeLine) {
        String querySTR;
        List<Object[]> resultantList = new ArrayList<>();
        try {
            querySTR = "SELECT a.postCode, a.postLine, a.year, "
                    + "CASE WHEN a.dataType = 'R' THEN ROUND(AVG(a.buyingPrice)) ELSE 'null' END, "
                    + "CASE WHEN a.dataType = 'P' THEN ROUND(AVG(a.buyingPrice)) ELSE 'null' END "
                    + "FROM db_housebuying_trend a "
                    + "WHERE a.postCode = ?1 "
                    + "AND a.postLine = ?2 "
                    + "GROUP BY a.postCode, a.postLine, a.year, a.dataType "
                    + "ORDER BY 3 ";
            Query query = em.createNativeQuery(querySTR);
            query.setParameter(1, postcode);
            query.setParameter(2, postCodeLine);
            resultantList = query.getResultList();
        } catch (Exception e) {
            System.err.println(e);
        }
        return resultantList;
    }

}
