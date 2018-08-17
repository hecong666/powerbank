package org.bicyclesharing.entities;

import java.io.Serializable;
import java.util.Date;



public class PowerBank implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer pid;

    private Date lastTime;
    private int dumpEnergy;
    private Integer statement;

    public int getDumpEnergy() {
		return dumpEnergy;
	}

	public void setDumpEnergy(int dumpEnergy) {
		this.dumpEnergy = dumpEnergy;
	}

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