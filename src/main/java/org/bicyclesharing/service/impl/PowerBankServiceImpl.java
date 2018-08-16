package org.bicyclesharing.service.impl;

import java.util.List;

import org.bicyclesharing.dao.PowerBankDao;
import org.bicyclesharing.entities.PowerBank;
import org.bicyclesharing.service.PowerBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class PowerBankServiceImpl implements PowerBankService {
		@Autowired
		private PowerBankDao pdao;
	@Override
	public PowerBank selectPowerBankById(int i) {
		// TODO Auto-generated method stub
		return pdao.selectByPrimaryKey(i);
		
	}

	@Override
	public boolean insertPowerBank(PowerBank p) {
		// TODO Auto-generated method stub
		int i = pdao.insert(p);
		if(i <= 0){
		return false;
		}else{
			return true;
		}
	}

	@Override
	public List<PowerBank> selectAll() {
		// TODO Auto-generated method stub
		return pdao.selectAllPowerBank();
	}

	@Override
	public boolean deletePowerBankById(int i) {
		// TODO Auto-generated method stub
		int x = pdao.deleteByPrimaryKey(i);
		if(x <= 0)
		return false;
		
		return true;
	}

	@Override
	public boolean updatePowerBanById(PowerBank p) {
		// TODO Auto-generated method stub
		int i = pdao.updateByPrimaryKey(p);
		if(i <= 0)
		return false;
		return true;
	}

	@Override
	public int countPowerBank() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<PowerBank> selectPowerBankByStatement(int s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectCountPowerBankByStatement(int s) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean updatePowerBankStateById(int pid) {
		// TODO Auto-generated method stub
		
		return false;
	}

}
