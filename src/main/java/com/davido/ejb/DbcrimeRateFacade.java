/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.davido.ejb;

import com.davido.entities.DbcrimeRate;
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
public class DbcrimeRateFacade extends AbstractFacade<DbcrimeRate> implements DbcrimeRateFacadeLocal {

    @PersistenceContext(unitName = "com.davido_LookAndFeel_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DbcrimeRateFacade() {
        super(DbcrimeRate.class);
    }

    @Override
    public List<Object[]> findCrimePostCode() {
        String querySTR;
        List<Object[]> resultantList = new ArrayList<>();
        try {
            querySTR = "SELECT DISTINCT ROUND(AVG(a.crimeRate),1) "
                    + "FROM db_crimeRate a, db_locality b, db_postCode c "
                    + "WHERE a.municipalityId = b.municipalityId "
                    + "AND b.postCodeId = c.postCodeId "
                    + "AND b.postCodeLine = c.postCodeLine "
                    + "GROUP BY b.postCodeId, c.postCodeName "
                    + "ORDER BY 1";
            Query query = em.createNativeQuery(querySTR);
            resultantList = query.getResultList();
        } catch (Exception e) {
            System.err.println(e);
        }
        return resultantList;
    }

    @Override
    public List<Object[]> findCrime(List<String> listOfPostCodes) {
        String querySTR;
        List<Object[]> resultantList = new ArrayList<>();
        try {
            querySTR = "SELECT MIN(a.crimeRate), MAX(crimeRate) "
                    + "FROM (SELECT ROUND(AVG(a.crimeRate),1) crimeRate "
                    + "FROM db_crimeRate a, db_locality b "
                    + "WHERE a.municipalityId = b.municipalityId "
                    + "AND b.postCodeId IN ?1 "
                    + "GROUP BY b.postCodeId) a";
            Query query = em.createNativeQuery(querySTR);
            query.setParameter(1, listOfPostCodes);
            resultantList = query.getResultList();
        } catch (Exception e) {
            System.err.println(e);
        }
        return resultantList;
    }

    @Override
    public List<Object[]> getAllCrime(List<String> listOfPostCodes) {
        String querySTR;
        List<Object[]> resultantList = new ArrayList<>();
        try {
            querySTR = "SELECT b.postCodeId, c.postCodeName, ROUND(AVG(a.crimeRate),1) "
                    + "FROM db_crimeRate a, db_locality b, db_postCode c "
                    + "WHERE a.municipalityId = b.municipalityId "
                    + "AND b.postCodeId = c.postCodeId "
                    + "AND b.postCodeLine = c.postCodeLine "
                    + "AND b.postCodeId IN ?1 "
                    + "GROUP BY b.postCodeId, c.postCodeName "
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
    public List<DbcrimeRate> findByPostcode(int postcode) {
        String querySTR;
        List<DbcrimeRate> resultantList = new ArrayList<>();
        try {
            querySTR = "FROM DbcrimeRate cr WHERE EXISTS ("
                    + "SELECT 'x' FROM DbLocality l "
                    + "WHERE l.dbpostCode.dbpostCodePK.postCodeId = ?1 "
                    + "AND l.municipalityId = cr.municipalityId)";
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
            querySTR = "SELECT c.postCodeId, c.postCodeLine, c.postCodeName, ROUND(AVG(a.crimeRate),1) crimeRate "
                    + "FROM db_crimeRate a, db_locality b, db_postCode c "
                    + "WHERE a.municipalityId = b.municipalityId "
                    + "AND b.postCodeId = c.postCodeId "
                    + "AND b.postCodeLine = c.postCodeLine "
                    + "GROUP BY c.postCodeId, c.postCodeLine, c.postCodeName "
                    + "HAVING crimeRate >= ?1 AND crimeRate <= ?2 "
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
    public List<Object[]> findByPostcodeAndLine(String postcode, String postCodeLine) {
        String querySTR;
        List<Object[]> resultantList = new ArrayList<>();
        try {
            querySTR = "SELECT c.postCodeId, c.postCodeLine, a.year, ROUND(AVG(a.crimeRate),1) crimeRate "
                    + "FROM db_crimeRate a, db_locality b, db_postCode c "
                    + "WHERE a.municipalityId = b.municipalityId "
                    + "AND b.postCodeId = c.postCodeId "
                    + "AND b.postCodeLine = c.postCodeLine "
                    + "AND b.postCodeId = ?1 "
                    + "AND b.postCodeLine = ?2 "
                    + "GROUP BY c.postCodeId, c.postCodeLine, a.year "
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
