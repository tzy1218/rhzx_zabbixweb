package com.rmyh.report.excel.bean;

public class ZbBean {

    public String sbName = "";//设备名称
	public String ipAddress = "";//IP地址
	public String jkObject = "";//监控对象
	public String jkxName = "";//监控项名
	public String gjdj = "";//告警等级
	public String yzcs = "";//压制次数
	public String gjfzbds = "";//告警表阈值达式
	public String state = "";//状态
	
    public String DOMAIN = "";//DOMAIN
    public String SERVER = "";//SERVER
    public String jkx = "";//监控项
    public String gjfz = "";//告警阀值
    public String gjjb = "";//告警级别
    
    public String groupName = "";
    
    public String getSbName() {
		return sbName;
    }
	public void setSbName(String sbName) {
		this.sbName = sbName;
    }

    public String getIpAddress() {
		return ipAddress;
    }
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
    }

    public String getJkObject() {
		return jkObject;
	}
	public void setJkObject(String jkObject) {
		this.jkObject = jkObject;
    }

    public String getJkxName() {
		return jkxName;
	}
	public void setJkxName(String jkxName) {
		this.jkxName = jkxName;
    }

    public String getGjdj() {
		return gjdj;
	}
	public void setGjdj(String gjdj) {
		this.gjdj = gjdj;
    }

    public String getYzcs() {
		return yzcs;
	}
	public void setYzcs(String yzcs) {
		this.yzcs = yzcs;
    }
    
    public String getGjfzbds() {
		return gjfzbds;
	}
	public void setGjfzbds(String gjfzbds) {
		this.gjfzbds = gjfzbds;
    }
    
    public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public String getDOMAIN() {
		return DOMAIN;
	}
	public void setDOMAIN(String DOMAIN) {
		this.DOMAIN = DOMAIN;
	}
	
	public String getSERVER() {
		return SERVER;
	}
	public void setSERVER(String SERVER) {
		this.SERVER = SERVER;
	}
	
	public String getJkx() {
		return jkx;
	}
	public void setJkx(String jkx) {
		this.jkx = jkx;
	}
	
	public String getGjfz() {
		return gjfz;
	}
	public void setGjfz(String gjfz) {
		this.gjfz = gjfz;
	}
	
	public String getGjjb() {
		return gjjb;
	}
	public void setGjjb(String gjjb) {
		this.gjjb = gjjb;
	}
	
    public String getGroupName() {
		return groupName;
    }
	public void setGroupName(String groupName) {
		this.groupName = groupName;
    }
	
	public String toString() {

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("{sbName:").append(sbName).append(",");
		stringBuilder.append("ipAddress:").append(ipAddress).append(",");
		stringBuilder.append("jkObject:").append(jkObject).append(",");
		stringBuilder.append("jkxName:").append(jkxName).append(",");
		stringBuilder.append("gjdj:").append(gjdj).append(",");
		stringBuilder.append("yzcs:").append(yzcs).append(",");
		stringBuilder.append("gjfzbds:").append(gjfzbds).append(",");
		stringBuilder.append("groupName:").append(groupName).append(",");
		stringBuilder.append("state:").append(state).append("}");

		return stringBuilder.toString();
	}
}