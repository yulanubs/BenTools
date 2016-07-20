package com.bentools.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
/**
 * 
	 * @ClassName:JSON工具类 <BR>
     * @Describe：<BR>
     * @Author: Jekshow
	 * @Extends：<BR>
     * @Version:1.0 
     * @date:2016-6-15 上午9:14:56
 */
public class JSONUtils {

	public static void resolveData(JsonObject data) {
		Gson gson = new Gson();
		JavaBean bean = gson.fromJson(data, JavaBean.class);
		System.out.println(bean.success);
	}



	// "{\"username\":\"arthinking\",\"userId\":001}";
	public static void main(String[] args) {
		Gson gson  = new Gson();
		String datatxt = getData();
		JsonParser parser = new JsonParser();
		JsonObject data = (JsonObject) parser.parse(datatxt);
		resolveData(data);
	}

	private static String getData() {
		try {
			String encoding = "UTF-8";
			File file = new File("E:\\data.txt");
			if (file.isFile() && file.exists()) {
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file),encoding);
				 BufferedReader bufferedReader = new BufferedReader(read);
				 String lineTxt = null;
				 StringBuffer sb = new StringBuffer();
				 while((lineTxt = bufferedReader.readLine()) != null){
					 sb.append(lineTxt);
				 }
				 read.close();
				 return sb.toString();
			}else{
				return null;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}
	
	class JavaBean{
		
		boolean success;
		
		Product product;
		
		Product msgood;
		
		Product tjgood;
		
		Adss ads;
		
		/**
		 * @return the ads
		 */
		public Adss getAds() {
			return ads;
		}

		/**
		 * @param ads the ads to set
		 */
		public void setAds(Adss ads) {
			this.ads = ads;
		}

		/**
		 * @return the success
		 */
		public boolean isSuccess() {
			return success;
		}

		/**
		 * @param success the success to set
		 */
		public void setSuccess(boolean success) {
			this.success = success;
		}

		/**
		 * @return the product
		 */
		public Product getProduct() {
			return product;
		}

		/**
		 * @param product the product to set
		 */
		public void setProduct(Product product) {
			this.product = product;
		}

		/**
		 * @return the msgood
		 */
		public Product getMsgood() {
			return msgood;
		}

		/**
		 * @param msgood the msgood to set
		 */
		public void setMsgood(Product msgood) {
			this.msgood = msgood;
		}

		/**
		 * @return the tjgood
		 */
		public Product getTjgood() {
			return tjgood;
		}

		/**
		 * @param tjgood the tjgood to set
		 */
		public void setTjgood(Product tjgood) {
			this.tjgood = tjgood;
		}


		class Adss{
			List<Ads> adslist;
			class Ads{
				String title;
				int order;
				String path;
				String url;
				/**
				 * @return the title
				 */
				public String getTitle() {
					return title;
				}
				/**
				 * @param title the title to set
				 */
				public void setTitle(String title) {
					this.title = title;
				}
				/**
				 * @return the order
				 */
				public int getOrder() {
					return order;
				}
				/**
				 * @param order the order to set
				 */
				public void setOrder(int order) {
					this.order = order;
				}
				/**
				 * @return the path
				 */
				public String getPath() {
					return path;
				}
				/**
				 * @param path the path to set
				 */
				public void setPath(String path) {
					this.path = path;
				}
				/**
				 * @return the url
				 */
				public String getUrl() {
					return url;
				}
				/**
				 * @param url the url to set
				 */
				public void setUrl(String url) {
					this.url = url;
				}
			}
		}
		
		
		
		class Product{
			String name;
			/**
			 * @return the name
			 */
			public String getName() {
				return name;
			}
			/**
			 * @param name the name to set
			 */
			public void setName(String name) {
				this.name = name;
			}
			/**
			 * @return the goodlist
			 */
			public List<Goods> getGoodlist() {
				return goodlist;
			}
			/**
			 * @param goodlist the goodlist to set
			 */
			public void setGoodlist(List<Goods> goodlist) {
				this.goodlist = goodlist;
			}
			List<Goods> goodlist;
			class Goods{
				int id;
				/**
				 * @return the id
				 */
				public int getId() {
					return id;
				}
				/**
				 * @param id the id to set
				 */
				public void setId(int id) {
					this.id = id;
				}
				/**
				 * @return the thumbnail
				 */
				public String getThumbnail() {
					return thumbnail;
				}
				/**
				 * @param thumbnail the thumbnail to set
				 */
				public void setThumbnail(String thumbnail) {
					this.thumbnail = thumbnail;
				}
				/**
				 * @return the price
				 */
				public int getPrice() {
					return price;
				}
				/**
				 * @param price the price to set
				 */
				public void setPrice(int price) {
					this.price = price;
				}
				/**
				 * @return the sales
				 */
				public int getSales() {
					return sales;
				}
				/**
				 * @param sales the sales to set
				 */
				public void setSales(int sales) {
					this.sales = sales;
				}
				/**
				 * @return the name
				 */
				public String getName() {
					return name;
				}
				/**
				 * @param name the name to set
				 */
				public void setName(String name) {
					this.name = name;
				}
				/**
				 * @return the path
				 */
				public String getPath() {
					return path;
				}
				/**
				 * @param path the path to set
				 */
				public void setPath(String path) {
					this.path = path;
				}
				/**
				 * @return the image
				 */
				public String getImage() {
					return image;
				}
				/**
				 * @param image the image to set
				 */
				public void setImage(String image) {
					this.image = image;
				}
				String thumbnail;
				int price;
				int sales;
				String name;
				String path;
				String image;
			}
		}
		
	}
	
}
