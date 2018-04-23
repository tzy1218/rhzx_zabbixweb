package com.rmyh.report.excel.bean;

public class XnBean {

	public String sbName = "";// 设备名称
	public String ipAddress = "";// IP地址
	public String jkObject = "";// 监控对象
	public String jkxName = "";// 监控项名
	public String jkInstance = "";// 监控实例
	public String max = "";// 最大值
	public String avg = "";// 平均值
	public String min = "";// 最小值
	public String yzjg = "";// 严重告警次数
	public String zyjg = "";// 主要告警次数
	public String cyjg = "";// 次要告警次数
	public String DOMAIN = "";// DOMAIN
	public String SERVER = "";// SERVER

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

	public String getJkInstance() {
		return jkInstance;
	}

	public void setJkInstance(String jkInstance) {
		this.jkInstance = jkInstance;
	}

	public String getJkxName() {
		return jkxName;
	}

	public void setJkxName(String jkxName) {
		this.jkxName = jkxName;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}

	public String getAvg() {
		return avg;
	}

	public void setAvg(String avg) {
		this.avg = avg;
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	public String getYzjg() {
		return yzjg;
	}

	public void setYzjg(String yzjg) {
		this.yzjg = yzjg;
	}

	public String getZyjg() {
		return zyjg;
	}

	public void setZyjgState(String zyjg) {
		this.zyjg = zyjg;
	}

	public String getCyjg() {
		return cyjg;
	}

	public void setCyjg(String cyjg) {
		this.cyjg = cyjg;
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

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String toString() {

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("{groupName:").append(groupName).append(",");
		stringBuilder.append("sbName:").append(sbName).append(",");
		stringBuilder.append("ipAddress:").append(ipAddress).append("}");

		return stringBuilder.toString();
	}
	}