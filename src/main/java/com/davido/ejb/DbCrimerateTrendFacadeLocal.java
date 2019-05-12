/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.davido.ejb;

import com.davido.entities.DbCrimerateTrend;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author davidortega
 */
@Local
public interface DbCrimerateTrendFacadeLocal {

    void create(DbCrimerateTrend dbCrimerateTrend);

    void edit(DbCrimerateTrend dbCrimerateTrend);

    void remove(DbCrimerateTrend dbCrimerateTrend);

    DbCrimerateTrend find(Object id);

    List<DbCrimerateTrend> findAll();

    List<DbCrimerateTrend> findRange(int[] range);

    int count();
    
    List<DbCrimerateTrend> findByName(String fieldName, String fieldValue);
    
    List<Object[]> findTrends(String postcode, String postCodeLine);
    
}
