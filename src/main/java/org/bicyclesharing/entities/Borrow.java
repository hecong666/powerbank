package org.bicyclesharing.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 租借记录实体类
 * Created by HuiJa on 2017/7/26.
 */
public class Borrow implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer borrowId;
    private Integer pid;
    private Integer userId;
    //借车时间段
    private Date borrowStartTime;
    private Date borrowEndTime;
   
    //消费
    private BigDecimal cost;
    //余额
    private BigDecimal remaining;

   
    private User user;

  

   
   

	public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Borrow() {
    }

    public Borrow(Integer pid, Integer userId, Date borrowStartTime, Date borrowEndTime, Double borrowStartX, Double borrowStartY, Double borrowEndX, Double borrowEndY, BigDecimal cost, BigDecimal remaining) {
        this.pid = pid;
        this.userId = userId;
        this.borrowStartTime = borrowStartTime;
        this.borrowEndTime = borrowEndTime;
       
        this.cost = cost;
        this.remaining = remaining;
    }

  
    public Integer getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(Integer borrowId) {
        this.borrowId = borrowId;
    }

  
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getBorrowStartTime() {
        return borrowStartTime;
    }

    public void setBorrowStartTime(Date borrowStartTime) {
        this.borrowStartTime = borrowStartTime;
    }

    public Date getBorrowEndTime() {
        return borrowEndTime;
    }

    public void setBorrowEndTime(Date borrowEndTime) {
        this.borrowEndTime = borrowEndTime;
    }

   

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getRemaining() {
        return remaining;
    }

    public void setRemaining(BigDecimal remaining) {
        this.remaining = remaining;
    }

    public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	@Override
    public String toString() {
        return "Borrow{" +
                "borrowId=" + borrowId +
                ", pid=" + pid +
                ", userId=" + userId +
                ", borrowStartTime=" + borrowStartTime +
                ", borrowEndTime=" + borrowEndTime +
                
                ", cost=" + cost +
                ", remaining=" + remaining +
                '}';
    }
}
