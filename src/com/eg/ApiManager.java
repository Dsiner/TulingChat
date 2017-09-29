package com.eg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

public class ApiManager {

	/**
	 * Post����
	 */
	public static String post(String url, String Params) throws IOException {
		OutputStreamWriter out = null;
		BufferedReader reader = null;
		String response = "";
		try {
			URL httpUrl = null; // HTTP URL�� �����������������
			// ����URL
			httpUrl = new URL(url);
			// ��������
			HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("connection", "keep-alive");
			conn.setUseCaches(false);// ���ò�Ҫ����
			conn.setInstanceFollowRedirects(true);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.connect();
			// POST����
			out = new OutputStreamWriter(conn.getOutputStream());
			out.write(Params);
			out.flush();
			// ��ȡ��Ӧ
			reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String lines;
			while ((lines = reader.readLine()) != null) {
				lines = new String(lines.getBytes(), "utf-8");
				response += lines;
			}
			reader.close();
			// �Ͽ�����
			conn.disconnect();
			ULog.d("response: " + response.toString());
		} catch (Exception e) {
			ULog.d("���� POST ��������쳣��");
			e.printStackTrace();
		}
		// ʹ��finally�����ر��������������
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (reader != null) {
					reader.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		return response;
	}

	// public String HttpClientPost(String url, String data) {
	// String response = null;
	// ULog.d("url: " + url);
	// ULog.d("request: " + data);
	// try {
	// CloseableHttpClient httpclient = null;
	// CloseableHttpResponse httpresponse = null;
	// try {
	// httpclient = HttpClients.createDefault();
	// HttpPost httppost = new HttpPost(url);
	// StringEntity stringentity = new StringEntity(data,
	// ContentType.create("text/json", "UTF-8"));
	// httppost.setEntity(stringentity);
	// httpresponse = httpclient.execute(httppost);
	// response = EntityUtils.toString(httpresponse.getEntity());
	// ULog.d("response: " + response);
	// } finally {
	// if (httpclient != null) {
	// httpclient.close();
	// }
	// if (httpresponse != null) {
	// httpresponse.close();
	// }
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return response;
	// }
}
