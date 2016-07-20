package com.bentools.utils;

public enum OrderStatus {
	TO_PAY,			/*3  待付款*/
	TO_PACK,		/*4  待配货*/
	DELIVERY,		/*5  已发货*/
	PACKED,			/*6  配货完成*/
	SIGNED,			/*7  已签收*/
	REFUSE_SIGN,	/*8  拒绝签收*/
	CANCEL,			/*9  已取消*/
	PAYED,			/*10 已付款*/
	PROCESSING; /*unknow 处理中*/
	
	public static String toString(OrderStatus status){
		switch (status) {
		case TO_PAY: 		return "待付款";
		case TO_PACK:		return "待配货";
		case DELIVERY:		return "已发货";
		case PACKED:		return "配货完成";
		case SIGNED:		return "已签收";
		case REFUSE_SIGN:	return "拒绝签收";
		case CANCEL:		return "已取消";
		case PAYED:			return "已付款";
		case PROCESSING:	return "处理中";
		default:			return "未知";
		}
	}
	
	public static OrderStatus value(String status){
		if ("3".equals(status)){
			return TO_PAY;
		}else if("4".equals(status)){
			return TO_PACK;
		}else if("5".equals(status)){
			return DELIVERY;
		}else if("6".equals(status)){
			return PACKED;
		}else if("7".equals(status)){
			return SIGNED;
		}else if("8".equals(status)){
			return REFUSE_SIGN;
		}else if("9".equals(status)){
			return CANCEL;
		}else if("10".equals(status)){
			return PAYED;
		}else{
			return PROCESSING;
		}
	}
}
