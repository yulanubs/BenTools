package com.bentools.utils;

import java.util.regex.Pattern;

public class PackageUtil{

	//验证包裹单号规则
	public static boolean validPackageRule(Long countryId,String packageNo, Long operator){
		boolean flag=false;
		
		if(packageNo.length()!=13){
			return flag;
		}		
		String regCn = null;//中国邮政		
		String regJp="[Ee][e-zE-Z]\\d{9}[Jj][Pp]"; //日本邮政	
		String regNz = "[Ee][a-zA-Z]\\d{9}[Nn][Zz]";//新西兰邮政
		String regAu = "[Ee][a-zA-Z]\\d{9}[Aa][Uu]";//澳洲邮政
		String regDe = "[Cc][Ll]\\d{9}[Dd][Ee]";//德国邮政
		String reg;
				
		if(countryId==81l){//日本邮政
			reg=regJp;
		}else if(countryId==64l){//新西兰邮政
			reg=regNz;
		}else if(countryId==61l){//澳洲邮政
			reg=regAu;
		}else if(countryId==49l){//德国邮政
			reg=regDe;
		}else{//中国邮政
			if (operator == null) {
				regCn = "([Ee][a-zA-Z]|[Ff][Tt])\\d{9}([Cc][SsNn]|[Gg][Dd]|[Tt][Ww])";//中国邮政旧规则
			} else if (operator == 1L) {
				regCn = "(10|11)\\d{9}([0-9][0-9])";//中国邮政新规则
			} else if (operator == 2L) {
				regCn = "([Ee][a-zA-Z]|[Ff][Tt]|(10|11))\\d{9}([Cc][SsNn]|[Gg][Dd]|[Tt][Ww]|[0-9][0-9])";//中国邮政新与旧
			} else {
				return flag;
			}
			reg=regCn;
		}
		Pattern p = Pattern.compile(reg);
		if(p.matcher(packageNo).find()){
			String ss=packageNo.substring(2,11);
			char[] c=ss.toCharArray();			
			int v9=0;		
			int v=(Integer.parseInt(String.valueOf(c[0]))*8+
			Integer.parseInt(String.valueOf(c[1]))*6+
			Integer.parseInt(String.valueOf(c[2]))*4+
			Integer.parseInt(String.valueOf(c[3]))*2+
			Integer.parseInt(String.valueOf(c[4]))*3+
			Integer.parseInt(String.valueOf(c[5]))*5+
			Integer.parseInt(String.valueOf(c[6]))*9+
			Integer.parseInt(String.valueOf(c[7]))*7)%11;			
			if(v==0){
				v9=5;
			}else if(v==1){
				v9=0;
			}else{	
				v9=11-v;
			}
			//判断验证是否相等
			if(v9==Integer.parseInt(String.valueOf(c[8]))){
				flag=true;
			}
		}
		return flag;
	}
	
	/**
	 * 默认中国邮政包裹单验证
	 * @param countryId
	 * @param packageNo
	 * @return
	 */
	public static boolean defaultPackageRule(String packageNo){
		return validPackageRule(86L,packageNo, 2L);
	}	
}
