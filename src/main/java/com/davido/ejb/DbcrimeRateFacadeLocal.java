/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.davido.ejb;

import com.davido.entities.DbcrimeRate;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author davidortega
 */
@Local
public interface DbcrimeRateFacadeLocal {

    void create(DbcrimeRate dbcrimeRate);

    void edit(DbcrimeRate dbcrimeRate);

    void remove(DbcrimeRate dbcrimeRate);

    DbcrimeRate find(Object id);

    List<DbcrimeRate> findAll();

    List<DbcrimeRate> findRange(int[] range);

    int count();
    
    List<DbcrimeRate> findByName(String fieldName, String fieldValue);
    
    List<Object[]> findCrimePostCode();
    
    List<Object[]> findCrime(List<String> listOfPostCodes);

    List<Object[]> getAllCrime(List<String> listOfPostCodes);
    
    List<DbcrimeRate> findByPostcode(int postcode);
    
    List<Object[]> findPostCodeByRange(String min, String max);
    
    List<Object[]> findByPostcodeAndLine(String postcode, String postCodeLine);
    
}
