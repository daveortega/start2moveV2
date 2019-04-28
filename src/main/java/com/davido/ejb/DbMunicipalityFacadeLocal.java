/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.davido.ejb;

import com.davido.entities.DbMunicipality;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author davidortega
 */
@Local
public interface DbMunicipalityFacadeLocal {

    void create(DbMunicipality dbMunicipality);

    void edit(DbMunicipality dbMunicipality);

    void remove(DbMunicipality dbMunicipality);

    DbMunicipality find(Object id);

    List<DbMunicipality> findAll();

    List<DbMunicipality> findRange(int[] range);

    int count();
    
    List<DbMunicipality> findByName(String fieldName, String fieldValue);
    
}
