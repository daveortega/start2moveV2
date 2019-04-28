/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.davido.ejb;

import com.davido.entities.DbHospital;
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
public class DbHospitalFacade extends AbstractFacade<DbHospital> implements DbHospitalFacadeLocal {

    @PersistenceContext(unitName = "com.davido_LookAndFeel_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DbHospitalFacade() {
        super(DbHospital.class);
    }

    @Override
    public List<Object[]> findHospitalsPostCode() {
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
    public List<Object[]> findHospitals(List<String> listOfPostCodes) {
        String querySTR;
        List<Object[]> resultantList = new ArrayList<>();
        try {
            querySTR = "SELECT DISTINCT COUNT(X) stopsNo "
                    + "FROM db_hospital a, db_postCode b "
                    + "WHERE a.postCode = b.postCodeId "
                    + "AND a.postLine = b.postCodeLine "
                    + "AND a.postCode IN ?1 "
                    + "GROUP BY b.postCodeName "
                    + "ORDER BY 1";
            Query query = em.createNativeQuery(querySTR);
            query.setParameter(1, listOfPostCodes);
            resultantList = query.getResultList();
        } catch (Exception e) {
            System.err.println(e);
        }
        return resultantList;
    }

    @Override
    public List<Object[]> getAllHospitals(List<String> listOfPostCodes) {
        String querySTR;
        List<Object[]> resultantList = new ArrayList<>();
        try {
            querySTR = "SELECT b.postCodeId, b.postCodeName, count(X) hospitalsNo "
                    + "FROM db_hospital a, db_postCode b "
                    + "WHERE a.postCode = b.postCodeId "
                    + "AND a.postLine = b.postCodeLine "
                    + "AND a.postCode IN ?1 "
                    + "GROUP BY a.postCode, b.postCodeName "
                    + "ORDER BY 3";
            Query query = em.createNativeQuery(querySTR);
            query.setParameter(1, listOfPostCodes);
            resultantList = query.getResultList();
        } catch (Exception e) {
            System.err.println(e);
        }
        return resultantList;
    }

    @Override
    public List<DbHospital> findByPostcode(int postcode) {
        String querySTR;
        List<DbHospital> resultantList = new ArrayList<>();
        try {
            querySTR = "FROM DbHospital h WHERE h.dbpostCode.dbpostCodePK.postCodeId = ?1";
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
            querySTR = "SELECT DISTINCT b.postCodeId, b.postCodeLine, b.postCodeName, count(X) hospitalsNo "
                    + "FROM db_hospital a, db_postCode b "
                    + "WHERE a.postCode = b.postCodeId "
                    + "AND a.postLine = b.postCodeLine "
                    + "GROUP BY b.postCodeId, b.postCodeLine, b.postCodeName "
                    + "HAVING hospitalsNo >= ?1 AND hospitalsNo <= ?2 "
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
    public List<DbHospital> findByPostcodeAndLine(int postcode, int postCodeLine) {
        String querySTR;
        List<DbHospital> resultantList = new ArrayList<>();
        try {
            querySTR = "FROM DbHospital s WHERE s.dbpostCode.dbpostCodePK.postCodeId = ?1 "
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
