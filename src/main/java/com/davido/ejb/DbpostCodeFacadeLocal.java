/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.davido.ejb;

import com.davido.entities.DbpostCode;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author davidortega
 */
@Local
public interface DbpostCodeFacadeLocal {

    void create(DbpostCode dbpostCode);

    void edit(DbpostCode dbpostCode);

    void remove(DbpostCode dbpostCode);

    DbpostCode find(Object id);

    List<DbpostCode> findAll();

    List<DbpostCode> findRange(int[] range);

    int count();
    
    List<DbpostCode> findByName(String fieldName, String fieldValue);
    
    List<DbpostCode> findAllRegional();
    
}
