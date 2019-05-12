/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.davido.ejb;

import com.davido.entities.DbHousebuyingTrend;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author davidortega
 */
@Local
public interface DbHousebuyingTrendFacadeLocal {

    void create(DbHousebuyingTrend dbHousebuyingTrend);

    void edit(DbHousebuyingTrend dbHousebuyingTrend);

    void remove(DbHousebuyingTrend dbHousebuyingTrend);

    DbHousebuyingTrend find(Object id);

    List<DbHousebuyingTrend> findAll();

    List<DbHousebuyingTrend> findRange(int[] range);

    int count();
    
    List<DbHousebuyingTrend> findByName(String fieldName, String fieldValue);
    
    List<Object[]> findTrends(String postcode, String postCodeLine);
    
}
