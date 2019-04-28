/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.davido.ejb;

import com.davido.entities.DbPreferences;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author davidortega
 */
@Local
public interface DbPreferencesFacadeLocal {

    void create(DbPreferences dbPreferences);

    void edit(DbPreferences dbPreferences);

    void remove(DbPreferences dbPreferences);

    DbPreferences find(Object id);

    List<DbPreferences> findAll();

    List<DbPreferences> findRange(int[] range);

    int count();
    
    List<DbPreferences> findByName(String fieldName, String fieldValue);
    
}
