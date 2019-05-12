/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.davido.ejb;

import com.davido.entities.DbCrimerateTrend;
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
public class DbCrimerateTrendFacade extends AbstractFacade<DbCrimerateTrend> implements DbCrimerateTrendFacadeLocal {

    @PersistenceContext(unitName = "com.davido_LookAndFeel_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DbCrimerateTrendFacade() {
        super(DbCrimerateTrend.class);
    }

    @Override
    public List<Object[]> findTrends(String postcode, String postCodeLine) {
        String querySTR;
        List<Object[]> resultantList = new ArrayList<>();
        try {
            querySTR = "SELECT c.postCodeId, c.postCodeLine, a.year, "
                    + "CASE WHEN a.dataType = 'R' THEN ROUND(AVG(a.crimeRate),1) ELSE 'null' END, "
                    + "CASE WHEN a.dataType = 'P' THEN ROUND(AVG(a.crimeRate),1) ELSE 'null' END "
                    + "FROM db_crimerate_trend a, db_locality b, db_postCode c "
                    + "WHERE a.municipalityId = b.municipalityId "
                    + "AND b.postCodeId = c.postCodeId "
                    + "AND b.postCodeLine = c.postCodeLine "
                    + "AND b.postCodeId = ?1 "
                    + "AND b.postCodeLine = ?2 "
                    + "GROUP BY c.postCodeId, c.postCodeLine, a.year, a.dataType "
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
