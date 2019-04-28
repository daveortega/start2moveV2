/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.davido.ejb;

import com.davido.entities.DbView;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author davidortega
 */
@Local
public interface DbViewFacadeLocal {

    void create(DbView dbView);

    void edit(DbView dbView);

    void remove(DbView dbView);

    DbView find(Object id);

    List<DbView> findAll();

    List<DbView> findRange(int[] range);

    int count();
    
    List<DbView> findByName(String fieldName, String fieldValue);
    
}
