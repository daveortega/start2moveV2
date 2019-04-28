/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.davido.ejb;

import com.davido.entities.DbSchool;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author davidortega
 */
@Local
public interface DbSchoolFacadeLocal {

    void create(DbSchool dbSchool);

    void edit(DbSchool dbSchool);

    void remove(DbSchool dbSchool);

    DbSchool find(Object id);

    List<DbSchool> findAll();

    List<DbSchool> findRange(int[] range);

    int count();
    
    List<DbSchool> findByName(String fieldName, String fieldValue);
    
    List<Object[]> findSchoolTypePostCode();

    List<Object[]> findSchools(List<String> listOfPostCodes);

    List<Object[]> getAllSchools(List<String> listOfPostCodes);

    List<DbSchool> findByPostcode(int postcode);
    
    List<Object[]> findPostCodeByRange(List<String> type);
    
    List<DbSchool> findByPostcodeAndLine(int postcode, int postCodeLine);

}
