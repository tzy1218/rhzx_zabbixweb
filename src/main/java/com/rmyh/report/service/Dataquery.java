package com.rmyh.report.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.rmyh.report.bean.AlertBean;
import com.rmyh.report.bean.ItemBean;
import com.rmyh.report.bean.TriggerBean;
import com.rmyh.report.bean.XNDataBean;
import com.rmyh.report.dao.HbaseConnectionFactory;

public class Dataquery {
	public static final String hbaseXNTableName = "zabbix_data";
	public static final String hbaseItemTableName = "zabbix_items";
	public static final String hbaseTriggerTableName = "zabbix_trigger";
	public static final String hbaseAlertTableName = "zabbix_alert";
	public static final String url = "127.0.0.1";

	// public static void main(String[] args) throws ZabbixApiException,
	// ParseException {
	// getByKeyWTime(0, 9531119873000l);
	// }

	// public static XNDataBean getByKey(String key) {
	// GetHistoryBeanList historyBean = new GetHistoryBeanList();
	// List<ItemBean> itemBeans = new GetItems().getAllItems();
	// HbaseConnectionFactory.init(url);
	// HbaseTools hbtquery = new HbaseTools();
	// XNDataBean dataqueryresult =
	// hbtquery.getValueByTransDetails(hbaseXNTableName, key);
	// System.out.println(dataqueryresult);
	// return dataqueryresult;
	// // logg4j. getTime +""+data insert hbase success
	// }

	public static List<XNDataBean> getXNDataByWTime(long startTime, long stopTime) throws ParseException {
		// GetHistoryBeanList historyBean = new GetHistoryBeanList();
		// List<ItemBean> itemBeans = new GetItems().getAllItems_App();
		HbaseConnectionFactory.init(url);
		HbaseTools hbtitemquery = new HbaseTools();
		List<ItemBean> itemBeanList = hbtitemquery.getItemBeans(hbaseItemTableName, stopTime);

		HbaseConnectionFactory.init(url);
		HbaseTools hbtquery = new HbaseTools();
		List<XNDataBean> result = new ArrayList();
		for (ItemBean bean : itemBeanList) {
			int itemId = bean.getItemId();
			XNDataBean dataqueryresult = hbtquery.getXNValueByTime(hbaseXNTableName, startTime, stopTime, itemId);
			dataqueryresult.setApplicationId(bean.getApplicationId());
			dataqueryresult.setApplicationName(bean.getApplicationName());
			dataqueryresult.setGroupId(bean.getGroupId());
			dataqueryresult.setGroupName(bean.getGroupName());
			dataqueryresult.setHostId(bean.getHostId());
			dataqueryresult.setHostName(bean.getHostName());
			dataqueryresult.setItemName(bean.getItemName());
			dataqueryresult.setHostIp(bean.getIp());
			result.add(dataqueryresult);
		}
		// System.out.println(result);
		return result;
		// logg4j. getTime +""+data insert hbase success
	}

	public static List<AlertBean> getAlertDataByWTime(long startTime, long stopTime) throws ParseException {

		HbaseConnectionFactory.init(url);
		HbaseTools hbtquery = new HbaseTools();
		List<AlertBean> alertBeans = hbtquery.getAlertBeans(hbaseAlertTableName, startTime, stopTime);
		List<AlertBean> alertCountBeans = new ArrayList();
		OUT: for (AlertBean alertbean : alertBeans) {
			IN: for (AlertBean countbean : alertCountBeans) {
				if (alertbean.getHostId() == countbean.getHostId()
						&& alertbean.getAlertTitle().equals(countbean.getAlertTitle())) {
					countbean.setAlertTimes((Integer) (countbean.getALertTimes() + 1));
					if (Long.parseLong(alertbean.getClock()) > Long.parseLong(countbean.getClock())) {
						countbean.setConfirmstatus(alertbean.getConfirmstatus());
						countbean.setClock(alertbean.getClock());
					}
					continue OUT;
				}
			}
			alertCountBeans.add(alertbean);
		}

		return alertCountBeans;
	}

	public static List<TriggerBean> getAllTriggers_App(long startTime, long stopTime) throws ParseException {

		HbaseConnectionFactory.init(url);
		HbaseTools hbtquery = new HbaseTools();
		List<TriggerBean> result = hbtquery.getTriggerBeans(hbaseTriggerTableName, stopTime);
		return result;
	}
}
