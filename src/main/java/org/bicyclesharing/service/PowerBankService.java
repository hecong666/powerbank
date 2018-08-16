package org.bicyclesharing.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.bicyclesharing.entities.PowerBank;

public interface PowerBankService {
			/**
			 * 1.根据id查询充电宝
			 * */
	public PowerBank selectPowerBankById(@Param("pid")int i);
	
	//2.增加一台充电宝
	public boolean insertPowerBank(PowerBank p);
	
	//3.查询所有充电宝
	public List<PowerBank> selectAll();
	
	//4.根据ID删除充电宝
	public boolean deletePowerBankById(@Param("pid")int i);
	
	//5.修改单车信息
	public boolean updatePowerBanById(PowerBank p);
	
	//6.获取单车总数
	public int countPowerBank();
	
	//7.通过状态查询单车和数量
	public List<PowerBank> selectPowerBankByStatement(int s);
	public int selectCountPowerBankByStatement(int s);
	
	//8.修改充电宝状态
	public boolean updatePowerBankStateById(int pid);
	
}
