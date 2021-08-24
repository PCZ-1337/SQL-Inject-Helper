package cn.PCZ.Utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


public class WebUtils {
	/*
	 * WebUtils.get(url)
	 * @author PCZ
	 */
	public static String get(String url) throws IOException {
		HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();

		con.setDoOutput(true);
		con.setDoInput(true);
		con.setUseCaches(false);
		con.setInstanceFollowRedirects(true);

		con.setRequestMethod("GET");

		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuilder response = new StringBuilder();
		
		while((inputLine = in.readLine())!= null) {
			response.append(inputLine);
			response.append("\n");
		}
		
		in.close();
		
		return response.toString();
	}
	public static String Post(String targeturl, String param) {
		String result = "";
		BufferedReader in = null;
		try {

			URL url = new URL(targeturl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("User-Agent", "Mozilla/5.0");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
			connection.connect();
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(),"UTF-8"));
			out.write(param);
			out.flush();
			out.close();
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("Error");
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
}
