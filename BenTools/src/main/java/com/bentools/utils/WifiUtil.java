package com.bentools.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import android.content.Context;
import android.net.DhcpInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.WifiLock;
import android.util.Log;
public class WifiUtil {  
    //定义一个WifiManager对象  
    private WifiManager mWifiManager;  
    //定义一个WifiInfo对象  
    private WifiInfo mWifiInfo;  
    //扫描出的网络连接列表  
    private List<ScanResult> mWifiList;  
    //网络连接列表  
    private List<WifiConfiguration> mWifiConfigurations;  
    WifiLock mWifiLock;  
    private String wifiLockTag;
    public WifiUtil(Context context){  
        //取得WifiManager对象  
        mWifiManager=(WifiManager) context.getSystemService(Context.WIFI_SERVICE);  
        //取得WifiInfo对象  
        mWifiInfo=mWifiManager.getConnectionInfo();  
    }  
    //打开wifi  
    public void openWifi(){  
        if(!mWifiManager.isWifiEnabled()){  
            mWifiManager.setWifiEnabled(true);  
        }  
    }  
    //关闭wifi  
    public void closeWifi(){  
        if(!mWifiManager.isWifiEnabled()){  
            mWifiManager.setWifiEnabled(false);  
        }  
    }  
     // 检查当前wifi状态    
    public int checkState() {    
        return mWifiManager.getWifiState();    
    }
    public boolean isWifiEnable(){
    	return mWifiManager.isWifiEnabled();
    }
    //锁定wifiLock  
    public void acquireWifiLock(){  
    	if(mWifiLock==null){
    		return;
    	}
        mWifiLock.acquire();  
    }  
    //解锁wifiLock  
    public void releaseWifiLock(){  
    	if(mWifiLock==null){
    		return;
    	}
        //判断是否锁定  
        if(mWifiLock.isHeld()){  
            mWifiLock.acquire();  
        }  
    }  
    public void createWifiLock(){
    	createWifiLock(null);
    }
    //创建一个wifiLock  
    public void createWifiLock(String tag){  
    	wifiLockTag=tag;
    	if(wifiLockTag==null){
    		wifiLockTag=DateUtils.getCurrentDate();//随机字符
    	}
        mWifiLock=mWifiManager.createWifiLock(wifiLockTag);  
    }  
    //得到配置好的网络  
    public List<WifiConfiguration> getConfiguration(){  
        return mWifiManager.getConfiguredNetworks();
    }  
    public DhcpInfo getDhcpInfo(){
    	 return mWifiManager.getDhcpInfo();    	
    }
      
    public void startScan(){  
        mWifiManager.startScan(); 
    }  
    //得到网络列表  
    public List<ScanResult> getWifiList(){  
        return mWifiManager.getScanResults();  
    }  
    
    public String getMacAddress(){  
    	mWifiInfo=mWifiManager.getConnectionInfo();
        return mWifiInfo.getMacAddress();  
    }  
    public int getRssi(){  
    	mWifiInfo=mWifiManager.getConnectionInfo();
        return mWifiInfo.getRssi();  
    }
    public String getBSSID(){  
    	mWifiInfo=mWifiManager.getConnectionInfo();
        return mWifiInfo.getBSSID();  
    }  
    public String getSSID(){  
    	mWifiInfo=mWifiManager.getConnectionInfo();
        return mWifiInfo.getSSID();  
    }
    public int getIpAddress(){  
    	mWifiInfo=mWifiManager.getConnectionInfo();
        return mWifiInfo.getIpAddress();  
    }  
    /**
     *      case AUTHENTICATING:
            case ASSOCIATING:
            case ASSOCIATED:
            case FOUR_WAY_HANDSHAKE:
            case GROUP_HANDSHAKE:
                return true;
            case COMPLETED:
            case DISCONNECTED:
            case INTERFACE_DISABLED:
            case INACTIVE:
            case SCANNING:
            case DORMANT:
            case UNINITIALIZED:
            case INVALID:
     * @return
     */
    public SupplicantState getSupplicantState(){  
    	mWifiInfo=mWifiManager.getConnectionInfo();mWifiInfo.getSupplicantState();
        return mWifiInfo.getSupplicantState();  
    }
    //得到连接的ID  
    public int getNetWordId(){  
    	mWifiInfo=mWifiManager.getConnectionInfo();
        return mWifiInfo.getNetworkId();  
    }  
    //得到wifiInfo的所有信息  
    public String getWifiInfo(){  
    	mWifiInfo=mWifiManager.getConnectionInfo();
        return mWifiInfo.toString();  
    }  
    public boolean addWifi(String ssid,String pwd){
    	return addWifi(ssid, pwd, false);
    }
    public boolean addWifi(String ssid){
    	return addWifi(ssid, null, true);
    }
    public boolean addWifi(String ssid,boolean hidden){
    	return addWifi(ssid, null, false);
    }    
    //添加网络并连接  
    public boolean addWifi(String ssid,String pwd,boolean hide){  
        int netId=-1; 
    	WifiConfiguration wificonf = new WifiConfiguration();
    	wificonf.SSID = "\""+ssid+"\"";//\"转义字符，代表"
    	wificonf.hiddenSSID = hide;
    	if(pwd!=null){
    		wificonf.preSharedKey = "\""+pwd+"\"";//WPA-PSK密码
    		wificonf.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
    		wificonf.status = WifiConfiguration.Status.ENABLED;
    	}else{
    		wificonf.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
    	}
    	Method method=null;
    	Object isvalid=null;;
    	try {
			 method=wificonf.getClass().getMethod("isValid", new Class[]{});
			 isvalid=method.invoke(wificonf,new Object[]{});			 
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			Log.e("wifiUtil",e.getMessage());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	boolean isv= ((Boolean)isvalid).booleanValue();
    	if(!isv){
    		Log.e("wifiUtil", "configuration is valid");
    		return false;
    	}
    	netId=mWifiManager.addNetwork(wificonf);
    	
    	if(netId<0){
    		Log.w("wifiUtil", "netId="+netId+",isconn=false");
    		return false;
    	}
    	isv=mWifiManager.enableNetwork(netId, true);
    	Log.w("wifiUtil", "netId="+netId+",isconn="+isv);
        return isv;  
    }
    public boolean removeWifi(int ssid){
    	return mWifiManager.removeNetwork(ssid);
    }
    public boolean removeWifi(String ssid){
    	return removeWifi(Integer.valueOf(ssid));
    }
  //指定配置好的网络进行连接  
    public boolean connSsidWifi(String ssid){  
    	mWifiConfigurations=mWifiManager.getConfiguredNetworks(); 
       int networkId=-1;
        for (WifiConfiguration wifiConfiguration : mWifiConfigurations) {
			if(wifiConfiguration.SSID.equals(ssid)){
				networkId=wifiConfiguration.networkId;
				Log.e("conn","networkId"+ networkId);
				break;
			}
		}
        if(networkId<=0){
        	return false;
        }
        //连接配置好指定ID的网络  
        boolean isconn=mWifiManager.enableNetwork(networkId, true); 
        Log.e("conn", "conn="+isconn);
        return  isconn;
    }
    public boolean connNetworkWifi(String Id){      	
        //连接配置好指定ID的网络  
    	int networkId=Integer.valueOf(Id);
        return  connNetworkWifi(networkId); 
    }
    public boolean connNetworkWifi(int networkId){      	
        //连接配置好指定ID的网络
        boolean isconn=mWifiManager.enableNetwork(networkId, true); 
        Log.e("conn", "conn="+isconn);
        return  isconn;
    }
    //断开指定ID的网络  
    public void disConnectionWifi(int netId){  
        mWifiManager.disableNetwork(netId);  
        mWifiManager.disconnect();  
    }  
} 