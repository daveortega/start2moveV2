/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.davido.ejb;

import com.davido.entities.DbSchool;
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
public class DbSchoolFacade extends AbstractFacade<DbSchool> implements DbSchoolFacadeLocal {

    @PersistenceContext(unitName = "com.davido_LookAndFeel_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DbSchoolFacade() {
        super(DbSchool.class);
    }

    @Override
    public List<Object[]> findSchoolTypePostCode() {
        String querySTR;
        List<Object[]> resultantList = new ArrayList<>();
        try {
            querySTR = "SELECT DISTINCT a.schoolType "
                    + "FROM db_school a";
            Query query = em.createNativeQuery(querySTR);
            resultantList = query.getResultList();
        } catch (Exception e) {
            System.err.println(e);
        }
        return resultantList;
    }

    @Override
    public List<Object[]> findSchools(List<String> listOfPostCodes) {
        String querySTR;
        List<Object[]> resultantList = new ArrayList<>();
        try {
            querySTR = "SELECT DISTINCT a.schoolType "
                    + "FROM db_school a "
                    + "WHERE a.postCode IN ?1";
            Query query = em.createNativeQuery(querySTR);
            query.setParameter(1, listOfPostCodes);
            resultantList = query.getResultList();
        } catch (Exception e) {
            System.err.println(e);
        }
        return resultantList;
    }

    @Override
    public List<Object[]> getAllSchools(List<String> listOfPostCodes) {
        String querySTR;
        List<Object[]> resultantList = new ArrayList<>();
        try {
            querySTR = "SELECT DISTINCT b.postCodeId, b.postCodeName, a.schoolType "
                    + "FROM db_school a, db_postCode b "
                    + "WHERE a.postCode = b.postCodeId "
                    + "AND a.postLine = b.postCodeLine "
                    + "AND a.postCode IN ?1";
            Query query = em.createNativeQuery(querySTR);
            query.setParameter(1, listOfPostCodes);
            resultantList = query.getResultList();
        } catch (Exception e) {
            System.err.println(e);
        }
        return resultantList;
    }

    @Override
    public List<DbSchool> findByPostcode(int postcode) {
        String querySTR;
        List<DbSchool> resultantList = new ArrayList<>();
        try {
            querySTR = "FROM DbSchool s WHERE s.dbpostCode.dbpostCodePK.postCodeId = ?1";
            Query query = em.createQuery(querySTR);
            query.setParameter(1, postcode);
            resultantList = query.getResultList();
        } catch (Exception e) {
            System.err.println(e);
        }
        return resultantList;
    }

    @Override
    public List<Object[]> findPostCodeByRange(List<String> type) {
        String querySTR;
        List<Object[]> resultantList = new ArrayList<>();
        try {
            querySTR = "SELECT a.postCode, a.postLine, b.postCodeName, COUNT(a.Address) NoSchools "
                    + "FROM db_school a, db_postCode b "
                    + "WHERE a.postCode = b.postCodeId "
                    + "AND a.postLine = b.postCodeLine "
                    + "AND a.schoolType IN ?1 "
                    + "GROUP BY a.postCode, a.postLine "
                    + "ORDER BY 4";
            Query query = em.createNativeQuery(querySTR);
            query.setParameter(1, type);
            resultantList = query.getResultList();
        } catch (Exception e) {
            System.err.println(e);
        }
        return resultantList;
    }

    @Override
    public List<DbSchool> findByPostcodeAndLine(int postcode, int postCodeLine) {
        String querySTR;
        List<DbSchool> resultantList = new ArrayList<>();
        try {
            querySTR = "FROM DbSchool s WHERE s.dbpostCode.dbpostCodePK.postCodeId = ?1 "
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
