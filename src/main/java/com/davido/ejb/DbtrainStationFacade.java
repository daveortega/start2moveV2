/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.davido.ejb;

import com.davido.entities.DbtrainStation;
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
public class DbtrainStationFacade extends AbstractFacade<DbtrainStation> implements DbtrainStationFacadeLocal {

    @PersistenceContext(unitName = "com.davido_LookAndFeel_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DbtrainStationFacade() {
        super(DbtrainStation.class);
    }

    @Override
    public List<Object[]> findTrainStationsPostCode() {
        String querySTR;
        List<Object[]> resultantList = new ArrayList<>();
        try {
            querySTR = "SELECT DISTINCT COUNT(X) hospitalsNo "
                    + " FROM db_hospital a, db_postCode b "
                    + " WHERE a.postCode = b.postCodeId "
                    + " AND a.postLine = b.postCodeLine "
                    + " GROUP BY b.postCodeName, b.postCodeLine "
                    + " ORDER BY 1";
            Query query = em.createNativeQuery(querySTR);
            resultantList = query.getResultList();
        } catch (Exception e) {
            System.err.println(e);
        }
        return resultantList;
    }

    @Override
    public List<Object[]> findPostCodeByRange(String min, String max) {
        String querySTR;
        List<Object[]> resultantList = new ArrayList<>();
        try {
            querySTR = "SELECT b.postCodeId, b.postCodeLine, b.postCodeName, COUNT(stationName) TrainStopsNo "
                    + "FROM db_trainStation a, db_postCode b "
                    + "WHERE a.postCode = b.postCodeId "
                    + "AND a.postLine = b.postCodeLine "
                    + "GROUP BY b.postCodeId, b.postCodeLine, b.postCodeName "
                    + "HAVING TrainStopsNo >= ?1 AND TrainStopsNo <= ?2 "
                    + "ORDER BY 4";
            Query query = em.createNativeQuery(querySTR);
            query.setParameter(1, min);
            query.setParameter(2, max);
            resultantList = query.getResultList();
        } catch (Exception e) {
            System.err.println(e);
        }
        return resultantList;
    }
    
    
    @Override
    public List<DbtrainStation> findByPostcodeAndLine(int postcode, int postCodeLine) {
        String querySTR;
        List<DbtrainStation> resultantList = new ArrayList<>();
        try {
            querySTR = "FROM DbtrainStation s WHERE s.dbpostCode.dbpostCodePK.postCodeId = ?1 "
                    + "AND s.dbpostCode.dbpostCodePK.postCodeLine = ?2";
            Query query = em.createQuery(querySTR);
            query.setParameter(1, postcode);
            query.setParameter(2, postCodeLine);
            resultantList = query.getResultList();
        } catch (Exception e) {
            System.err.println(e);
        }
        return resultantList;
    }

}
