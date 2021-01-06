package com.sbs.example.mysqlTextBoard;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbs.example.mysqlTextBoard.Util.Util;

public class TestRunner {
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class TestDataType1 {
		public int age;
		public String name;
	}

	public void run() {
		testApi2();

	}

	private void testJackson() {
		String jsonString = "{\"age\":22, \"name\":\"홍길동\"}";

		ObjectMapper ob = new ObjectMapper();
		Map rs = null;

		try {
			rs = ob.readValue(jsonString, Map.class);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return;
		}
		System.out.println(rs);
	}

	private void testJackson2() {
		String jsonString = "1";

		ObjectMapper ob = new ObjectMapper();
		Integer rs = null;

		try {
			rs = ob.readValue(jsonString, Integer.class);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return;
		}
		System.out.println(rs);

	}

	private void testJackson3() {
		String jsonString = "[1,2,3]";

		ObjectMapper ob = new ObjectMapper();
		List<Integer> rs = null;

		try {
			rs = ob.readValue(jsonString, List.class);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return;
		}
		System.out.println(rs.get(1));

	}

	private void testJackson4() {
		String jsonString = "[{\"age\":22, \"name\":\"홍길동\"}, {\"age\":23, \"name\":\"홍길순\"},{\"age\":24, \"name\":\"홍길근\"}]";

		ObjectMapper ob = new ObjectMapper();
		List<Map<String, Object>> rs = null;

		try {
			rs = ob.readValue(jsonString, List.class);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return;
		}
		System.out.println(rs.get(1).get("age"));

	}

	private void testJackson5() {
		String jsonString = "[{\"age\":22, \"name\":\"홍길동\"}, {\"age\":23, \"name\":\"홍길순\"},{\"age\":24, \"name\":\"홍길근\"}]";

		ObjectMapper ob = new ObjectMapper();
		List<TestDataType1> rs = null;

		try {
			rs = ob.readValue(jsonString, new TypeReference<List<TestDataType1>>() {
			});

		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return;
		}
		System.out.println(rs.get(0).age);

	}

	private static void testApi() {
		String url = "https://disqus.com/api/3.0/forums/listThreads.json";
		String rs = Util.callApi(url, "api_key=mQsMAh9IqQeSH1TzMd20w7eaZRPkoaoAc1NHrdOTBCaI2XgLn0ZlHH6RlLXxzVZW",
				"forum=enkung", "thread:ident=article_detail_2.html");

		System.out.println(rs);
	}

	private void testApi2() {
		String url = "https://disqus.com/api/3.0/forums/listThreads.json";
		Map<String,Object> rs = Util.callApiResponseToMap(url, "api_key=mQsMAh9IqQeSH1TzMd20w7eaZRPkoaoAc1NHrdOTBCaI2XgLn0ZlHH6RlLXxzVZW",
				"forum=enkung", "thread:ident=article_detail_2.html");
		List<Map<String,Object>> response = (List<Map<String, Object>>) rs.get("response");
		Map<String,Object> thread = response.get(0);
		
		
		System.out.println((int)thread.get("likes"));
	}

}
