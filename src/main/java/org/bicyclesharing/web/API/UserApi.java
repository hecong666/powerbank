package org.bicyclesharing.web.API;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bicyclesharing.entities.Borrow;
import org.bicyclesharing.entities.Recharge;
import org.bicyclesharing.entities.User;
import org.bicyclesharing.service.BorrowService;
import org.bicyclesharing.service.RechargeService;
import org.bicyclesharing.service.UserService;
import org.bicyclesharing.util.MsgUtil;
import org.bicyclesharing.util.WzwResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户相关API
 * Created by HuiJa on 2017/7/31.
 */
@Controller
public class UserApi {
    @Autowired
    private UserService userService;
    @Autowired
    private BorrowService borrowService;
    @Autowired
    private RechargeService rechargeService;
    @Autowired
    private RedisTemplate<String, Object> redis;

    /**
     * 1.用户登录接口
     *
     * @param userName
     * @param session
     * @return
     */
    
    @RequestMapping(value = "api-user-login")
    @ResponseBody
    public WzwResultUtil login(@RequestParam("userName") String userName,@RequestParam("Scode")String sCode, HttpSession session,HttpServletResponse response,HttpServletRequest request) {
   		String _Scode = (String) redis.opsForValue().get(userName);
   		if(_Scode == null){
   			return WzwResultUtil.build(-1, "验证码已过期", null); 			
    		}else{
    		if(_Scode.equals(sCode) ){
    			User user = userService.getUserByName(userName);
    			if(user == null){
    				user = new User();
    				user.setUserName(userName);
    				user.setUserCredit(0);
    				user.setUserCash(0);
    				user.setUserAccount(new BigDecimal(0));
    				userService.insertUser(user);
    			}
    			redis.opsForValue().set("user:"+userName, user);
    			return WzwResultUtil.build(1, "验证码成功", null);
    		    	
    		}
    			return WzwResultUtil.build(-2, "验证码错误", null);
    		}
    		
    }
    @RequestMapping("forWard")
    public String forWard(@RequestParam("userName")String userName,HttpSession session){
    	Borrow b  = (Borrow) redis.opsForValue().get("borrow:" + userName);
    	if(b == null){
    	User user = (User) redis.opsForValue().get("user:"+userName);
    	session.setAttribute("user", user);
    	System.out.println(user);
    	return "customer/index";
    	}else{
    		
    		return "customer/userShow";
    	}
    }
    @RequestMapping(value="sendMsg")
    @ResponseBody
    public WzwResultUtil sendMsg(@RequestParam("phoneNumber")String phoneNumber){	
    String Scode = MsgUtil.roundMsg();
    MsgUtil.sendMsg(phoneNumber, Scode);
    System.out.println(phoneNumber);
    System.out.println(phoneNumber + ":" + Scode);
    redis.opsForValue().set(phoneNumber, Scode, 60 * 2, TimeUnit.SECONDS);
    return WzwResultUtil.build(1, "验证码已发送", null);
    }
   

    /**
     * 3.退出登录接口
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "api-user-exit")
    @ResponseBody
    public String exit(HttpSession session) {
        session.removeAttribute("user");
        return "1";
    }

    /**
     * 4.用户租借记录api
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "api-user-queryBorrow/{userName}")
    @ResponseBody
    public ArrayList<Borrow> getBorrowByUserId(@PathVariable("userName") String userName) {
        User user = userService.getUserByName(userName);
        ArrayList<Borrow> borrows = (ArrayList<Borrow>) borrowService.getBorrowByUserId(user.getUserId());
        return borrows;
    }

    /**
     * 6.用户查询充值记录api
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "api-user-queryRecharge/{userName}")
    @ResponseBody
    public ArrayList<Recharge> getRechargeByUserId(@PathVariable("userName") String userName) {
        User user = userService.getUserByName(userName);
        if (user == null) {
            return null;
        } else {
            ArrayList<Recharge> recharges = (ArrayList<Recharge>) rechargeService.getRechargeByUserId(user.getUserId());
            return recharges;
        }

    }

    /**
     * 7.用户充值api,修改余额和充值记录
     */
    @RequestMapping(value = "api-user-recharge")
    public String Recharge(@RequestParam("rechargeAmount") BigDecimal rechargeAmount, @RequestParam("userName") String userName,HttpSession session) {
       userService.addUserAccountByUserId(rechargeAmount, userName);
       Recharge r = new Recharge();
       r.setRechargeTime(new Date());
     
       
    	User   user = userService.getUserByName(userName);
       
       r.setUser(user);
       r.setUserId(user.getUserId());
       r.setRechargeAmount(rechargeAmount);
        redis.opsForValue().set("user:" + userName,user);
        r.setRemaining(user.getUserAccount());
       rechargeService.addRecharge(r);
       
      session.setAttribute("user", user);
     return "customer/index";
    }

    /**
     * 8.用户信息api
     */
    @RequestMapping(value = "api-user-userInfo/{userName}")
    @ResponseBody
    public User getUserInfo(@PathVariable("userName") String userName) {
        return userService.getUserByName(userName);
    }

    /**
     * 9.查看用户押金
     */
    @RequestMapping(value = "api-user-getUserCash/{userName}")
    @ResponseBody
    public Integer getUserCash(@PathVariable("userName") String userName) {
        User user = userService.getUserByName(userName);
        if (user == null) {
            return -1;
        } else {
            return user.getUserCash();
        }

    }

    /**
     * 10.用户提交押金
     */
    @RequestMapping(value = "api-user-submitUserCash/{userName}")
    @ResponseBody
    public String submitUserCash(@PathVariable("userName") String userName) {
        User user = userService.getUserByName(userName);
        if (user == null) {
            return "-1";
        } else {
            if (user.getUserCash() == 0) {
                user.setUserCash(199);
                userService.editUser(user.getUserName(), user.getUserAccount(), user.getUserCredit(), user.getUserCash());
                return "1";
            } else {
                return "0";
            }
        }
    }

    /**
     * 11.用户退押金
     */
    @RequestMapping(value = "api-user-returnUserCash/{userName}")
    @ResponseBody
    public String returnUserCash(@PathVariable("userName") String userName) {
        User user = userService.getUserByName(userName);
        if (user == null) {
            return "-1";
        } else {
            if (user.getUserCash() == 199) {
                user.setUserCash(0);
                userService.editUser(user.getUserName(), user.getUserAccount(), user.getUserCredit(), user.getUserCash());
                return "1";
            } else {
                return "0";
            }
        }
    }

    /**
     * 12.修改用户信用的api
     * @param x
     * @param userName
     * @return
     */
    @RequestMapping(value = "api-user-changeUserCredit/{x}/{userName}")
    @ResponseBody
    public String changeUserCredit(@PathVariable("x") Integer x, @PathVariable("userName") String userName) {
        User user = userService.getUserByName(userName);
        if (user == null) {
            return "-1";
        } else {
            userService.editUser(user.getUserName(), user.getUserAccount(), user.getUserCredit(), user.getUserCash());
            return "1";
        }
    }
}
