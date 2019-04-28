/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.davido.ejb;

import com.davido.entities.DblandPrice;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author davidortega
 */
@Local
public interface DblandPriceFacadeLocal {

    void create(DblandPrice dblandPrice);

    void edit(DblandPrice dblandPrice);

    void remove(DblandPrice dblandPrice);

    DblandPrice find(Object id);

    List<DblandPrice> findAll();

    List<DblandPrice> findRange(int[] range);

    int count();
    
    List<DblandPrice> findByName(String fieldName, String fieldValue);
    
    List<Object[]> findBuyLandPostCode();
    
    List<Object[]> findLandBuy(List<String> listOfPostCodes);

    List<Object[]> getAllLandBuy(List<String> listOfPostCodes);
    
    List<DblandPrice> findByPostcode(int postcode);
    
    List<Object[]> findPostCodeByRange(String min, String max);
    
    List<Object[]> findByPostcodeAndLine(String postcode, String postCodeLine);
    
}
