package org.bicyclesharing.web.API;


import java.math.BigDecimal;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.bicyclesharing.entities.Borrow;
import org.bicyclesharing.entities.PowerBank;
import org.bicyclesharing.entities.User;
import org.bicyclesharing.service.BicycleService;
import org.bicyclesharing.service.BorrowService;
import org.bicyclesharing.service.PowerBankService;
import org.bicyclesharing.service.UserService;
import org.bicyclesharing.util.UpdateDumpEnergy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 借车相关api
 * Created by HuiJa on 2017/8/1.
 */
@Controller
public class BorrowApi {
    @Autowired
    BorrowService borrowService;
    @Autowired
    UserService userService;
    @Autowired
    BicycleService bicycleService;
    @Autowired
    private RedisTemplate<String, Object> redis;
    @Autowired
    private PowerBankService powerbankService;
    /**
     * 1.借车开始api,修改单车状况
     */
    @RequestMapping(value = "api-borrow-borrowBicycle")   
    public String borrowBicycle(@RequestParam("pid") Integer pid, @RequestParam("userName") String userName,HttpSession session) {
        User user = userService.getUserByName(userName);
        PowerBank pb = (PowerBank) redis.opsForValue().get(String.valueOf(pid));
        if (user == null || pb == null) {
            //-1表示找不到该车或者该用户不存在
            return "customer/error1";
        } else {
            if (pb.getStatement() == 0) {
            		Borrow borrow = new Borrow();
            		borrow.setPid(pid);
            		Date date = new Date();
            		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            		String strd = sd.format(date);
            		borrow.setBorrowStartTime(date);
            		borrow.setUser(userService.getUserByName(userName));
            		borrow.setUserId(userService.getUserByName(userName).getUserId());
                    //添加借充电宝记录(车id,用户名,当前时间,开始地址)
                
                    //修改充电宝状况
                   pb.setStatement(1);
                   redis.opsForValue().set(String.valueOf(pid), pb);
                   session.setAttribute("startTime", strd);
                   redis.opsForValue().set("borrow:" + userName , borrow);
                   int energy = pb.getDumpEnergy();
                   session.setAttribute("time",energy / 10);
                    return "customer/userShow";
                
            } else if(pb.getStatement() == 1){
            	 int energy = pb.getDumpEnergy();
                 session.setAttribute("time",energy / 10);
                 return "customer/userShow";
               //该充电宝正在使用中
            }else{
            return "customer/error1";
            }
        }
    }
    @RequestMapping("updateState")
    public String updateState(@RequestParam("pid")int pid,HttpSession session){
    	
    	PowerBank p = (PowerBank) redis.opsForValue().get(String.valueOf(pid));
    	if(p == null){
    		p = powerbankService.selectPowerBankById(pid);
    		redis.opsForValue().set(String.valueOf(pid), p);
    	}
    	int i = p.getStatement();
    	if(i == 0){//待使用
    	p.setStatement(-1);
    	redis.opsForValue().set(String.valueOf(pid), p);
    	
    	return "customer/error1";
    	}else if(i == -1){//未扫描
    		p.setStatement(0);
        	redis.opsForValue().set(String.valueOf(pid), p);
        	session.setAttribute("powerbank", p);
        	return "customer/index";
    	}else if(i == 1){//使用中
    		return "customer/error1";
    	}else if(i == -2){//电量为0
    		return "customer/error1";
    	}
    	return "customer/error1";
    }
    /**
     * 2.借车结束相关api,添加借车记录,修改用户余额,修改单车状况为1(还有地址)
     *
     * @return
     */
    @RequestMapping(value = "api-borrow-returnBicycle")
    @Transactional
    public String returnBicycle(@RequestParam("pid") Integer pid, @RequestParam("userName") String userName,HttpSession session) {
        User user = userService.getUserByName(userName);
        Date date = new Date();
        PowerBank pb = (PowerBank) redis.opsForValue().get(String.valueOf(pid));
        Borrow b = (Borrow) redis.opsForValue().get("borrow:" + userName);
        if (user == null || pb == null) {
            return "-1";//用户不存在
        } else {
        double times = 	UpdateDumpEnergy.update(b.getBorrowStartTime(), date);
        int energy = (int) Math.round(times * 10);
       
        	double money = Math.ceil(times * 2);
            //用户的余额减少
                BigDecimal remaining = user.getUserAccount();
         
                BigDecimal subMoney = new BigDecimal(-money);
                userService.addUserAccountByUserId(subMoney, userName);
                //完善租借记录
            b.setCost(new BigDecimal(money));
            b.setRemaining(user.getUserAccount());
               b.setBorrowEndTime(date);
               if(pb.getDumpEnergy() <= energy){
               	pb.setDumpEnergy(0);   
               	pb.setStatement(-2);
               }else{
               	pb.setDumpEnergy(pb.getDumpEnergy() - energy); 
               	pb.setStatement(-1);
               }
               pb.setLastTime(date);
               redis.opsForValue().set(String.valueOf(pid), pb);
               borrowService.addBorrow(b);
                redis.delete("borrow:" + userName);
                redis.delete("user:" + userName);
                session.invalidate();
                return "customer/success";
            
        }

    }

    /**
     * 3.查询当前(最后一条)借车记录api
     */
    @RequestMapping(value = "api-borrow-currentBorrow/{userName}")
    @ResponseBody
    public Borrow currentBorrow(@PathVariable("userName") String userName) {
        ArrayList<Borrow> borrows = (ArrayList<Borrow>) borrowService.getBorrowByUserId(userService.getUserByName(userName).getUserId());
        if (borrows.size() >= 1) {
            return borrows.get(borrows.size() - 1);
        } else return null;
    }
}
