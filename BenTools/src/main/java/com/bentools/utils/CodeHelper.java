package com.bentools.utils;

import java.util.HashMap;

public  class CodeHelper {
	

	public static final String unkown = "未知";
	public static final HashMap<String, String> big_order_status = new HashMap<String, String>();
	static {
		big_order_status.put("1", "交易取消");
		big_order_status.put("2", "交易退款");
		big_order_status.put("3", "待付款");
		big_order_status.put("4", "待配货");
		big_order_status.put("5", "已发货");
		big_order_status.put("6", "订单终止");
		big_order_status.put("107", "部分支付");

	}

	public static String BigOrderStatus2Des(String order_status_code) {
		try {
			return big_order_status.get(order_status_code);
		} catch (Exception e) {
			return unkown;
		}

	}
	
	public static final HashMap<String, String> order_status = new HashMap<String, String>();
	static {
		order_status.put("0", "交易完成");
		order_status.put("1", "交易取消");
		order_status.put("2", "退换货");
		order_status.put("3", "待付款");
		order_status.put("4", "待配货");
		order_status.put("5", "已发货");
		order_status.put("6", "配货完成");
		order_status.put("7", "已签收");
		order_status.put("8", "未签收");
		order_status.put("9", "取消");

	}

	public static String OrderStatus2Des(String order_status_code) {
		try {
			return order_status.get(order_status_code);
		} catch (Exception e) {
			return unkown;
		}

	}

	public static final HashMap<String, String> order_sub_status = new HashMap<String, String>();
	static {
		order_sub_status.put("11", "接收");
		order_sub_status.put("3", "揽收成功");
		order_sub_status.put("5", "妥投");
		order_sub_status.put("6", "未妥投");
		order_sub_status.put("2", "拒收");
		order_sub_status.put("4", "揽收失败");

	}

	public static String OrderSubStatus2Des(String order_status_code) {
		try {
			return order_sub_status.get(order_status_code);
		} catch (Exception e) {
			return unkown;
		}

	}

	public static final HashMap<String, String> order_type = new HashMap<String, String>();
	static {
		order_type.put("1001", "易趣订单处理");
		order_type.put("1101", "美国货订单");
		order_type.put("1201", "站外店订单");
		order_type.put("1401", "邮政线上订单");
		order_type.put("1701", "邮政积分兑换物品");
		order_type.put("1702", "线上购买礼品券订单");
		order_type.put("1703", "线上兑换礼品券订单");
		order_type.put("1901", "邮政post机下单");
		order_type.put("1501", "11185订单");
		order_type.put("1601", "邮政线下订单");

	}

	public static String OrderType2Des(String order_status_code) {
		try {
			return order_sub_status.get(order_status_code);
		} catch (Exception e) {
			return unkown;
		}

	}

	public static final HashMap<String, String> tran_type = new HashMap<String, String>();
	static {
		tran_type.put("2", "邮乐递");
		tran_type.put("3", "邮乐快递");
		tran_type.put("4", "邮乐急递");

	}

	public static String TranType2Des(String order_status_code) {
		try {
			return tran_type.get(order_status_code);
		} catch (Exception e) {
			return unkown;
		}

	}

	public static final HashMap<String, String> buy_type = new HashMap<String, String>();
	static {
		buy_type.put("0", "尚未选择支付类型");
		buy_type.put("1", "安付通网上银行交易");
		buy_type.put("2", "安付通邮政汇款交易");
		buy_type.put("3", "安付通余额交易");
		buy_type.put("4", "COUPON全额交易");
		buy_type.put("50", "线下汇款");
		buy_type.put("60", "货到付款");
		buy_type.put("70", "即时付款");
		buy_type.put("80", "邮乐礼品券");
		buy_type.put("81", "礼品卡支付");
		buy_type.put("82", "邮乐卡支付");
		buy_type.put("85", "账户余额");
		buy_type.put("86", "邮乐现金卡");
		buy_type.put("90", "非现金支付");
	}

	public static String BuyType2Des(String order_status_code) {
		try {
			return buy_type.get(order_status_code);
		} catch (Exception e) {
			return unkown;
		}

	}

	public static final HashMap<String, String> need_invoice = new HashMap<String, String>();
	static {
		need_invoice.put("1", "需要");
		need_invoice.put("0", "不需要");

	}

	public static String NeedVoice2Des(String order_status_code) {
		try {
			return need_invoice.get(order_status_code);
		} catch (Exception e) {
			return unkown;
		}

	}

	
}
