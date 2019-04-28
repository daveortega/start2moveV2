/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.davido.ejb;

import com.davido.entities.DbhouseBuying;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author davidortega
 */
@Local
public interface DbhouseBuyingFacadeLocal {

    void create(DbhouseBuying dbhouseBuying);

    void edit(DbhouseBuying dbhouseBuying);

    void remove(DbhouseBuying dbhouseBuying);

    DbhouseBuying find(Object id);

    List<DbhouseBuying> findAll();

    List<DbhouseBuying> findRange(int[] range);

    int count();

    List<DbhouseBuying> findByName(String fieldName, String fieldValue);

    List<Object[]> findHouseBuyPostCode();

    List<Object[]> findHouseBuy(List<String> listOfPostCodes);

    List<Object[]> getAllHouseBuy(List<String> listOfPostCodes);

    List<DbhouseBuying> findByPostcode(int postcode);
    
    List<Object[]> findPostCodeByRange(String min, String max);
    
    List<Object[]> findByPostcodeAndLine(String postcode, String postCodeLine);

}
