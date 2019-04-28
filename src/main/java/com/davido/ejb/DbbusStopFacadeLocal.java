/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.davido.ejb;

import com.davido.entities.DbbusStop;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author davidortega
 */
@Local
public interface DbbusStopFacadeLocal {

    void create(DbbusStop dbbusStop);

    void edit(DbbusStop dbbusStop);

    void remove(DbbusStop dbbusStop);

    DbbusStop find(Object id);

    List<DbbusStop> findAll();

    List<DbbusStop> findRange(int[] range);

    int count();
    
    List<DbbusStop> findByName(String fieldName, String fieldValue);
    
    List<Object[]> findStopsPostCode();

    List<Object[]> getAllBusStops();

    List<DbbusStop> findByPostcode(int postcode);
    
    List<Object[]> findPostCodeByRange(String min, String max);
    
    List<DbbusStop> findByPostcodeAndLine(int postcode, int postCodeLine);
    
}
