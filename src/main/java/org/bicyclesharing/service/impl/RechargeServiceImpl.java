package org.bicyclesharing.service.impl;

import org.bicyclesharing.dao.RechargeDao;
import org.bicyclesharing.entities.Recharge;
import org.bicyclesharing.service.RechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 充值相关逻辑实现类
 * 
 */
@Service
public class RechargeServiceImpl implements RechargeService {
    @Autowired
    private RechargeDao rechargeDao;

    @Override
    public boolean addRecharge(Recharge recharge) {
        
       
           
            rechargeDao.insertRecharge(recharge);
            return true;
        
    }

    @Override
    public void removeRecharge(Integer id) {
        rechargeDao.deleteRecharge(id);
    }

    @Override
    public List<Recharge> getAllRecharge() {
        return rechargeDao.selectAllRecharge();
    }

    @Override
    public Recharge getRechargeById(Integer id) {
        return rechargeDao.selectRechargeById(id);
    }

    @Override
    public List<Recharge> getRechargeByUserId(Integer userId) {
        return rechargeDao.selectRechargeByUserId(userId);
    }

    @Override
    public BigDecimal getRechargeCount() {
        return rechargeDao.selectRechargeCount();
    }
}
