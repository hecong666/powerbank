package org.bicyclesharing.web.Controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.bicyclesharing.entities.Bicycle;
import org.bicyclesharing.entities.PowerBank;
import org.bicyclesharing.service.BicycleService;
import org.bicyclesharing.service.PowerBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
//redisTemplate.opsForValue();//操作字符串
////redisTemplate.opsForHash();//操作hash
//redisTemplate.opsForList();//操作list
//redisTemplate.opsForSet();//操作set
//redisTemplate.opsForZSet();//操作有序set
/**
 * Created by HuiJa on 2017/7/28.
 */
@Controller
public class PowerBankController {
    @Autowired
    private BicycleService bicycleService;
    @Autowired
    private PowerBankService powerbankService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 1.单车列表显示
     *
     * @return bicycle/bicycle_list.jsp
     */
    @RequestMapping(value = "admin-bicycle-list-show", method = RequestMethod.GET)
    public String listShow(Map<String, Object> requestMap, @RequestParam("page") Integer page) {
        requestMap.put("nav", "bicycle");
        ArrayList<PowerBank> powerBanks = (ArrayList<PowerBank>) redisTemplate.opsForValue().get("powerBanks");
        	System.out.println((ArrayList<PowerBank>) redisTemplate.opsForValue().get("powerBanks"));
        if(powerBanks == null){
        powerBanks = (ArrayList<PowerBank>) powerbankService.selectAll();
       
        redisTemplate.opsForValue().set("powerBanks", powerBanks);
        	}
        	requestMap.put("powerBanks", powerBanks);
        int pageCount = powerBanks.size();  //数据条数
        int pageSize = 20;  //分页条数
        int pageMax = pageCount / pageSize;  //最大页数
        int pagePointer = 1;  //当前指向页
        if (pageMax != 0 && pageCount % pageSize != 0) {
            ++pageMax;
        }
        if (pageMax == 0) {
            pageMax = 1;
        }
        if (page < 1 || page > pageMax) {
            pagePointer = 1;
        } else {
            pagePointer = page;
        }
        ArrayList<PowerBank> pageBicycles = new ArrayList<>();
        if (pageMax == 1) {
            pageBicycles.addAll(powerBanks);
        } else if (pagePointer == pageMax) {
            int tmp = pageCount % pageSize;
            if (tmp == 0) {
                tmp = pageSize;
            }
            for (int i = pageSize * (pagePointer - 1); i < pageSize * (pagePointer - 1) + tmp; ++i) {
                pageBicycles.add(powerBanks.get(i));
            }
        } else {
            for (int i = pageSize * (pagePointer - 1); i < pageSize * pagePointer; ++i) {
                pageBicycles.add(powerBanks.get(i));
            }
        }
        requestMap.put("pageMax", pageMax);
        requestMap.put("pagePoint", pagePointer);
        requestMap.put("pageBicycles", pageBicycles);

        return "bicycle/bicycle_list";
    }

    /**
     * 2.搜索单车显示
     *
     * @return bicycle/bicycle_list.jsp
     */
    @RequestMapping(value = "admin-bicycle-searchbicycle-show", method = RequestMethod.GET)
    public String searchBicycleShow(Map<String, Object> requestMap, @RequestParam("statement") Integer statement) {
        requestMap.put("nav", "powerBank-list");
        
        ArrayList<Bicycle> bicycles = (ArrayList<Bicycle>) bicycleService.getBicycleByStatement(statement);
        requestMap.put("pageBicycles", bicycles);
        return "bicycle/bicycle_list";
    }

    /**
     * 3.新增单车显示
     *
     * @return bicycle/bicycle_add.jsp
     */
    @RequestMapping(value = "admin-bicycle-addbicycle-show", method = RequestMethod.GET)
    public String addBicycleShow() {
        return "bicycle/bicycle_add";
    }

    /**
     * 4.新增单车执行
     *
     * @return admin/bicycle_list.jsp
     */
    @RequestMapping(value = "admin-bicycle-addbicycle-execute", method = RequestMethod.POST)
    public String addBicycleExcute(@RequestParam("bicycleCurrentX") Double bicycleCurrentX, @RequestParam("bicycleCurrentY") Double bicycleCurrentY, @RequestParam("bicycleStatement") Integer bicycleStatement, @RequestParam("n") Integer n) {
       PowerBank p = new PowerBank();
       
      p.setStatement(0);
    	p.setDumpEnergy(100);
    	
    	
    	//p.setLastTime();
      powerbankService.insertPowerBank(p);
        String view = "redirect:/admin-bicycle-list-show?page=1";
        return view;
    }

    /**
     * 5.显示编辑单车信息
     *
     * @return
     */
    @RequestMapping(value = "admin-bicycle-editbicycle-show/{id}", method = RequestMethod.GET)
    public String editBicycleShow(@PathVariable("id") Integer id, Map<String, Object> requestMap) {
    	
        PowerBank powerBank = powerbankService.selectPowerBankById(id);
        requestMap.put("powerBank", powerBank);
        return "bicycle/bicycle_edit";
    }

    @RequestMapping(value = "admin-bicycle-editbicycle-execute", method = RequestMethod.POST)
    public String editBicycleExecute(@RequestParam("id") Integer id, @RequestParam("bicycleCurrentX") Double bicycleCurrentX, @RequestParam("bicycleCurrentY") Double bicycleCurrentY, @RequestParam("bicycleStatement") Integer bicycleStatement) {
        bicycleService.editBicycyle(id, bicycleCurrentX, bicycleCurrentY,new Date(), bicycleStatement);
        return "redirect:/admin-bicycle-list-show?page=1";
    }

    @RequestMapping(value = "admin-bicycle-delete-execute/{id}", method = RequestMethod.GET)
    public String deleteOneBicycle(@PathVariable("id") Integer id) {
    	powerbankService.deletePowerBankById(id);
        return "redirect:/admin-bicycle-list-show?page=1";
    }
    
}
