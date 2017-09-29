package com.eg;

import java.awt.Graphics;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Tuling {
	
	/**
	 * 正确返回示例：{"code":100000,"text":"???"}
	 */
	public static String BASE_URL = "http://www.tuling123.com/openapi/api";
	public static String API_KEY = "81e68e7109654a90bf9991f9c0bda98a";

	public static void chat(final String userId, final String message, final OnCallBack callback) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					String response = ApiManager.post(BASE_URL, paramJson(userId, message)).toString();
					ULog.d(response);
					// todo You can replace it with json
					String[] values = response.split("\"");
					if (values == null || values.length <= 1) {
						if (callback != null) {
							callback.onError();
						}
					} else {
						if (callback != null) {
							callback.onSuccess(values[values.length - 2]);
						}
					}

				} catch (IOException e) {
					e.printStackTrace();
					if (callback != null) {
						callback.onError();
					}
				}
			}
		}).start();
	}

	public static void chat(final String userId, final String message) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					String response = ApiManager.post(BASE_URL, paramJson(userId, message));
					ULog.d(response.toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	/**
	 * 拼接请求参数：json字符串
	 * 
	 */
	public static String paramJson(String userId, String message) {
		try {
			userId = URLEncoder.encode(userId, "utf-8");
			message = URLEncoder.encode(message, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String json = "{\"key\":\"" + API_KEY + "\",\"info\":\"" + message + "\",\"userid\":\"" + userId + "\"}";
		return json;
	}

	public interface OnCallBack {
		void onSuccess(String response);

		void onError();
	}
}
