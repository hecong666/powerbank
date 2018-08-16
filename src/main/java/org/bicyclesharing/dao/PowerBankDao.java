package org.bicyclesharing.dao;

import org.bicyclesharing.entities.PowerBank;
import org.bicyclesharing.entities.PowerBankExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PowerBankDao {
  

  

    int deleteByPrimaryKey(Integer pid);

    int insert(PowerBank record);

 

   

    PowerBank selectByPrimaryKey(Integer pid);

   

    int updateByPrimaryKey(PowerBank record);
    
    List<PowerBank> selectAllPowerBank();
}