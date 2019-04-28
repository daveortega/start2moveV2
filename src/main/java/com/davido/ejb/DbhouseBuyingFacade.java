/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.davido.ejb;

import com.davido.entities.DbhouseBuying;
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
public class DbhouseBuyingFacade extends AbstractFacade<DbhouseBuying> implements DbhouseBuyingFacadeLocal {

    @PersistenceContext(unitName = "com.davido_LookAndFeel_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DbhouseBuyingFacade() {
        super(DbhouseBuying.class);
    }

    @Override
    public List<Object[]> findHouseBuyPostCode() {
        String querySTR;
        List<Object[]> resultantList = new ArrayList<>();
        try {
            querySTR = "SELECT DISTINCT ROUND(AVG(a.buyingPrice)) "
                    + "FROM db_houseBuying a, db_postCode b "
                    + "WHERE a.postCodeId = b.postCodeId "
                    + "AND a.postCodeLine = b.postCodeLine "
                    + "GROUP BY b.postCodeId, b.postCodeLine "
                    + "ORDER BY 1";
            Query query = em.createNativeQuery(querySTR);
            resultantList = query.getResultList();
        } catch (Exception e) {
            System.err.println(e);
        }
        return resultantList;
    }

    @Override
    public List<Object[]> findHouseBuy(List<String> listOfPostCodes) {
        String querySTR;
        List<Object[]> resultantList = new ArrayList<>();
        try {
            querySTR = "SELECT MIN(a.buyingPrice), MAX(a.buyingPrice) "
                    + "FROM db_houseBuying a "
                    + "WHERE a.postCodeId IN ?1";
            Query query = em.createNativeQuery(querySTR);
            query.setParameter(1, listOfPostCodes);
            resultantList = query.getResultList();
        } catch (Exception e) {
            System.err.println(e);
        }
        return resultantList;
    }

    @Override
    public List<Object[]> getAllHouseBuy(List<String> listOfPostCodes) {
        String querySTR;
        List<Object[]> resultantList = new ArrayList<>();
        try {
            querySTR = "SELECT DISTINCT a.postCodeId, b.postCodeName, buyingPrice "
                    + "FROM db_houseBuying a, db_postCode b "
                    + "WHERE a.postCodeId = b.postCodeId "
                    + "AND a.postCodeLine = b.postCodeLine "
                    + "AND a.postCodeId IN ?1";
            Query query = em.createNativeQuery(querySTR);
            query.setParameter(1, listOfPostCodes);
            resultantList = query.getResultList();
        } catch (Exception e) {
            System.err.println(e);
        }
        return resultantList;
    }

    @Override
    public List<DbhouseBuying> findByPostcode(int postcode) {
        String querySTR;
        List<DbhouseBuying> resultantList = new ArrayList<>();
        try {
            querySTR = "FROM DbhouseBuying hb WHERE hb.dbpostCode.dbpostCodePK.postCodeId = ?1";
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
            querySTR = "SELECT b.postCodeId, b.postCodeLine, b.postCodeName, ROUND(AVG(a.buyingPrice)) buyPrice "
                    + "FROM db_houseBuying a, db_postCode b "
                    + "WHERE a.postCodeId = b.postCodeId "
                    + "AND a.postCodeLine = b.postCodeLine "
                    + "GROUP BY b.postCodeId, b.postCodeLine, b.postCodeName "
                    + "HAVING buyPrice >= ?1 AND buyPrice <= ?2 "
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
            querySTR = "SELECT a.postCodeId, a.postCodeLine, a.year, ROUND(AVG(a.buyingPrice)) buyPrice "
                    + "FROM db_houseBuying a "
                    + "WHERE a.postCodeId = ?1 "
                    + "AND a.postCodeLine = ?2 "
                    + "GROUP BY a.postCodeId, a.postCodeLine, a.year "
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
