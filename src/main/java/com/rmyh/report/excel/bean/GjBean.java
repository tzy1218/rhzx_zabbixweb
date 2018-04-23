package com.rmyh.report.excel.bean;

public class GjBean {

    public String gjTime = null;//时间
	public String gjLevel = null;//告警级别
	public String hostName = null;//主机名
	public String ipAddress = null;//IP地址
	public String gjFrequency = null;//告警次数
	public String gjContent = null;//告警内容
    public String state = null;//状态
    public String confirmState = null;//确认状态
  
    public String getGjTime() {
		return gjTime;
    }
	public void setGjTime(String gjTime) {
		this.gjTime = gjTime;
    }

    public String getGjLevel() {
		return gjLevel;
    }
	public void setGjLevel(String gjLevel) {
		this.gjLevel = gjLevel;
    }

    public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
    }

    public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
    }

    public String getGjFrequency() {
		return gjFrequency;
	}
	public void setGjFrequency(String gjFrequency) {
		this.gjFrequency = gjFrequency;
    }

    public String getGjContent() {
		return gjContent;
	}
	public void setGjContent(String gjContent) {
		this.gjContent = gjContent;
    }
    
    public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
    }
    
    public String getConfirmState() {
		return confirmState;
	}
	public void setConfirmState(String confirmState) {
		this.confirmState = confirmState;
	}
}