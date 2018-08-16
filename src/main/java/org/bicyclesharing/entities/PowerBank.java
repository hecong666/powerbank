package org.bicyclesharing.entities;

import java.util.Date;

public class PowerBank {
    private Integer pid;

    private Date lastTime;

    private Integer statement;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public Integer getStatement() {
        return statement;
    }

    public void setStatement(Integer statement) {
        this.statement = statement;
    }
}