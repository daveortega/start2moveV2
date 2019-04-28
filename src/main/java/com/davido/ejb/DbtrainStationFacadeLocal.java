/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.davido.ejb;

import com.davido.entities.DbtrainStation;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author davidortega
 */
@Local
public interface DbtrainStationFacadeLocal {

    void create(DbtrainStation dbtrainStation);

    void edit(DbtrainStation dbtrainStation);

    void remove(DbtrainStation dbtrainStation);

    DbtrainStation find(Object id);

    List<DbtrainStation> findAll();

    List<DbtrainStation> findRange(int[] range);

    int count();
    
    List<DbtrainStation> findByName(String fieldName, String fieldValue);
    
    List<Object[]> findTrainStationsPostCode();
    
    List<Object[]> findPostCodeByRange(String min, String max);
    
    List<DbtrainStation> findByPostcodeAndLine(int postcode, int postCodeLine);
    
}
