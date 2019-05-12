/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.davido.ejb;

import com.davido.entities.DbLandpriceTrend;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author davidortega
 */
@Local
public interface DbLandpriceTrendFacadeLocal {

    void create(DbLandpriceTrend dbLandpriceTrend);

    void edit(DbLandpriceTrend dbLandpriceTrend);

    void remove(DbLandpriceTrend dbLandpriceTrend);

    DbLandpriceTrend find(Object id);

    List<DbLandpriceTrend> findAll();

    List<DbLandpriceTrend> findRange(int[] range);

    int count();
    
    List<DbLandpriceTrend> findByName(String fieldName, String fieldValue);
    
    List<Object[]> findTrends(String postcode, String postCodeLine);
    
}
