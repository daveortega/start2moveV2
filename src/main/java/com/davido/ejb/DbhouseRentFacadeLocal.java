/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.davido.ejb;

import com.davido.entities.DbhouseRent;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author davidortega
 */
@Local
public interface DbhouseRentFacadeLocal {

    void create(DbhouseRent dbhouseRent);

    void edit(DbhouseRent dbhouseRent);

    void remove(DbhouseRent dbhouseRent);

    DbhouseRent find(Object id);

    List<DbhouseRent> findAll();

    List<DbhouseRent> findRange(int[] range);

    int count();
    
    List<DbhouseRent> findByName(String fieldName, String fieldValue);
    
    List<Object[]> findHouseRentPostCode();
    
    List<Object[]> findHouseRent(List<String> listOfPostCodes);

    List<Object[]> getAllHouseRent(List<String> listOfPostCodes);
    
    List<DbhouseRent> findByPostcode(int postcode);
    
    List<Object[]> findPostCodeByRange(String min, String max);
    
    List<Object[]> findByPostcodeAndLine(String postcode, String postCodeLine);
    
}
