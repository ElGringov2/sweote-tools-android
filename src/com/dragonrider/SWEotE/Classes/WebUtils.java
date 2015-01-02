package com.dragonrider.SWEotE.Classes;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class WebUtils {
	
	public static String sURL = "http://dragonrider.zz.mu/sweote/";
	
	public static JSONObject getData(String PHPFile, ArrayList<NameValuePair> Args) {
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(sURL + PHPFile);
		try {
			
			post.setEntity(new UrlEncodedFormEntity(Args));
			HttpResponse response = client.execute(post);
			HttpEntity entity = response.getEntity();
			InputStream is = entity.getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			String result = sb.toString();
			if (result.equals(""))  {
				
				return null;
			}
			JSONArray jArray = new JSONArray(result);
	
			JSONObject json_data = jArray.getJSONObject(0);
			return json_data;
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
	}
	public static JSONArray getArrayData(String PHPFile, ArrayList<NameValuePair> Args) {
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(sURL + PHPFile);
		try {
			
			post.setEntity(new UrlEncodedFormEntity(Args));
			HttpResponse response = client.execute(post);
			HttpEntity entity = response.getEntity();
			InputStream is = entity.getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			String result = sb.toString();
			if (result.equals(""))  {
				
				return null;
			}
			JSONArray jArray = new JSONArray(result);
	
			
			return jArray;
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static JSONArray CharacterSkillData(int CharacterID) {
		ArrayList<NameValuePair> Args = new ArrayList<NameValuePair>();
		Args.add(new BasicNameValuePair("id", String.valueOf(CharacterID)));
		
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(sURL + "skilllist.php");
		try {
			
			post.setEntity(new UrlEncodedFormEntity(Args));
			HttpResponse response = client.execute(post);
			HttpEntity entity = response.getEntity();
			InputStream is = entity.getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			String result = sb.toString();
			if (result.equals(""))  {
				
				return null;
			}
			JSONArray jArray = new JSONArray(result);
	
			
			return jArray;
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
	}
	
	public static String SetData(final String PHPFile, final ArrayList<NameValuePair> Args) {
		
		try {
			return new AsyncTask<String, String, String>() {

				@Override
				protected String doInBackground(String... params) {
					
					try {
						HttpClient client = new DefaultHttpClient();
						HttpPost post = new HttpPost(sURL + PHPFile);
						Log.i("log_tag", sURL + PHPFile);
						post.setEntity(new UrlEncodedFormEntity(Args));
						HttpResponse response = client.execute(post);
						HttpEntity entity = response.getEntity();
						InputStream is = entity.getContent();
						BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
						StringBuilder sb = new StringBuilder();
						String line = null;
						while ((line = reader.readLine()) != null) {
							sb.append(line + "\n");
						}
						is.close();
						return sb.toString();

					} catch (Exception e) {
						
						e.printStackTrace();

					}
					return "";
				}
			}.execute("","","").get();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		} catch (ExecutionException e) {
		
			e.printStackTrace();
		}

		return "";
	}
	
	public static String DelData(String TableName, int RecordID) {
		final ArrayList<NameValuePair> Args = new ArrayList<NameValuePair>();
		Args.add(new BasicNameValuePair("table", TableName));
		Args.add(new BasicNameValuePair("id", String.valueOf(RecordID)));
		
		try {
			return new AsyncTask<String, String, String>() {

				@Override
				protected String doInBackground(String... params) {
					
					try {
						HttpClient client = new DefaultHttpClient();
						HttpPost post = new HttpPost(sURL + "delrecord.php");
						Log.i("log_tag", sURL + "delrecord.php");
						post.setEntity(new UrlEncodedFormEntity(Args));
						HttpResponse response = client.execute(post);
						HttpEntity entity = response.getEntity();
						InputStream is = entity.getContent();
						BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
						StringBuilder sb = new StringBuilder();
						String line = null;
						while ((line = reader.readLine()) != null) {
							sb.append(line + "\n");
						}
						is.close();
						return sb.toString();

					} catch (Exception e) {
						
						e.printStackTrace();

					}
					return "";
				}
			}.execute("","","").get();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		} catch (ExecutionException e) {
			
			e.printStackTrace();
		}

		return "";
	}
	

	public static void Execute(final String PHPFile) {

		
		new AsyncTask<String, Integer, String> () {

			@Override
			protected String doInBackground(String... params) {
				try {
					HttpClient client = new DefaultHttpClient();
					HttpPost post = new HttpPost(sURL + PHPFile);
					client.execute(post);

				} catch (Exception e) {
					
					e.printStackTrace();

				}
				return "";
			}
			
		}.execute("");
	}
}
