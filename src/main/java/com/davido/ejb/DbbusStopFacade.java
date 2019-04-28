/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.davido.ejb;

import com.davido.entities.DbbusStop;
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
public class DbbusStopFacade extends AbstractFacade<DbbusStop> implements DbbusStopFacadeLocal {

    @PersistenceContext(unitName = "com.davido_LookAndFeel_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DbbusStopFacade() {
        super(DbbusStop.class);
    }

    @Override
    public List<Object[]> findStopsPostCode() {
        String querySTR;
        List<Object[]> resultantList = new ArrayList<>();
        try {

            querySTR = "SELECT DISTINCT count(X) stopsNo "
                    + "FROM db_busStop a, db_postCode b "
                    + "WHERE a.postCode = b.postCodeId "
                    + "AND a.postLine = b.postCodeLine "
                    + "GROUP BY b.postCodeName, b.postCodeLine "
                    + "ORDER BY 1";
            Query query = em.createNativeQuery(querySTR);
            resultantList = query.getResultList();
        } catch (Exception e) {
            System.err.println(e);
        }
        return resultantList;
    }

    @Override
    public List<Object[]> getAllBusStops() {
        String querySTR;
        List<Object[]> resultantList = new ArrayList<>();
        try {

            querySTR = "SELECT DISTINCT b.postCodeId, b.postCodeName, count(X) stopsNo "
                    + "FROM db_busStop a, db_postCode b "
                    + "WHERE a.postCode = b.postCodeId "
                    + "AND a.postLine = b.postCodeLine "
                    + "GROUP BY a.postCode, b.postCodeName "
                    + "ORDER BY 3";
            Query query = em.createNativeQuery(querySTR);
            resultantList = query.getResultList();
        } catch (Exception e) {
            System.err.println(e);
        }
        return resultantList;
    }

    @Override
    public List<DbbusStop> findByPostcode(int postcode) {
        String querySTR;
        List<DbbusStop> resultantList = new ArrayList<>();
        try {
            querySTR = "FROM DbbusStop bs WHERE bs.dbpostCode.dbpostCodePK.postCodeId = ?1";
            Query query = em.createQuery(querySTR);
            query.setParameter(1, postcode);
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
            querySTR = "SELECT DISTINCT b.postCodeId, b.postCodeLine, b.postCodeName, count(X) stopsNo "
                    + "FROM db_busStop a, db_postCode b "
                    + "WHERE a.postCode = b.postCodeId "
                    + "AND a.postLine = b.postCodeLine "
                    + "GROUP BY b.postCodeId, b.postCodeLine, b.postCodeName "
                    + "HAVING stopsNo >= ?1 AND stopsNo <= ?2 "
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
    public List<DbbusStop> findByPostcodeAndLine(int postcode, int postCodeLine) {
        String querySTR;
        List<DbbusStop> resultantList = new ArrayList<>();
        try {
            querySTR = "FROM DbbusStop s WHERE s.dbpostCode.dbpostCodePK.postCodeId = ?1 "
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
