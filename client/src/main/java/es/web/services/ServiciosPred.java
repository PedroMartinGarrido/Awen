package es.web.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
//import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import awen.commons.to.PredTO;
import es.web.controllers.BeanAdmiPred;

public class ServiciosPred {

	ArrayList<PredTO> predAll = new ArrayList<PredTO>();

	public PredTO callUpdatePred(PredTO predOne, boolean isUpdate) {
		BeanAdmiPred beanAdmiPred = new BeanAdmiPred();
		PredTO predTO = new PredTO();

		Locale locale = new Locale("es", "ES");
		String pattern = "yyyy-MM-dd HH:mm:ss.SSS"; // 2021-01-08 09:56:06.445+00:00
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, locale);

		// Se preparan la solicitud para enviar al microservicio.
		HttpClient client = HttpClient.newHttpClient();

		/////
		System.out.println("Dentro callUpdatePred");
		predTO.printPred(predTO);

		String strUri = "";
		if (isUpdate) {
			strUri = "http://localhost:8101/pred/updateInpred?inpred=" + predOne.getInpred() + "&clavex="
					+ predOne.getClavex().replace(" ", "+") + "&valorx=" + predOne.getValorx();
		} else {
			strUri = "http://localhost:8101/pred/disableInpred?inpred=" + predOne.getInpred();
		}
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(strUri)).GET().build();
//		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8101/pred/updateInpred?inpred=" + predOne.getInpred() + "&nopred=" + predOne.getNopred().replace(" ", "+") + "&contin=" + predOne.getValorx() + "&unieur=" + predOne.getUnieur())).GET()
//				.build();

		// Se envia los datos al microservicio.
		CompletableFuture<String> respuesta = client.sendAsync(request, BodyHandlers.ofString())
				.thenApply(HttpResponse::body);

		// Se obtiene los resultados de los datos grabados por el microservicio, en
		// confirmación de exito/fracaso de la operación.
		String resultado = Stream.of(respuesta, respuesta).map(CompletableFuture::join)
				.collect(Collectors.joining(" "));

		ObjectMapper mapper = new ObjectMapper();

		// JSON string to Java Object
		try {
			LinkedHashMap<String, String> pred = (LinkedHashMap<String, String>) mapper.readValue(resultado, Map.class);

			Set set = pred.keySet();
			Iterator iterator = set.iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				Object value = pred.get(key);
				switch (key) {
				case "inpred":
					predTO.setInpred(Long.valueOf(value.toString()));
					break;
				case "clavex":
					predTO.setClavex(value.toString());
					break;
				case "valorx":
					predTO.setValorx(value.toString());
					break;
				case "borlog":
					predTO.setBorlog(value.toString());
					break;
				case "usupro":
					if (value != null) {
						predTO.setUsupro(Long.valueOf(value.toString()));
					}
					break;
				case "usucre":
					if (value != null) {
						predTO.setUsucre(Long.valueOf(value.toString()));
					}
					break;
				case "usuult":
					if (value != null) {
						predTO.setUsuult(Long.valueOf(value.toString()));
					}
					break;
				case "feccre":
					try {
						DateFormat formatter = new SimpleDateFormat(pattern);
						Date date = formatter.parse(value.toString().substring(0, 23).replaceAll("T", " "));
						Timestamp timestamp = new Timestamp(date.getTime());
						predTO.setFeccre(timestamp);

					} catch (ParseException e) {
						e.printStackTrace();
					}
					break;
				case "fecult":
					try {
						DateFormat formatter = new SimpleDateFormat(pattern);
						Date date = formatter.parse(value.toString().substring(0, 23).replaceAll("T", " "));
						Timestamp timestamp = new Timestamp(date.getTime());
						predTO.setFecult(timestamp);

					} catch (ParseException e) {
						e.printStackTrace();
					}
					break;
				default:
					break;
				}
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return predTO;
	}

	public PredTO callInsertPred(PredTO predOne) {
		BeanAdmiPred beanAdmiPred = new BeanAdmiPred();
		PredTO predTO = new PredTO();

		Locale locale = new Locale("es", "ES");
		String pattern = "yyyy-MM-dd HH:mm:ss.SSS"; // 2021-01-08 09:56:06.445+00:00
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, locale);

		// Se preparan la solicitud para enviar al microservicio.
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("http://localhost:8101/pred/insertPred?clavex=" + predOne.getClavex().replace(" ", "+")
						+ "&valorx=" + predOne.getValorx()))
				.GET().build();

		// System.out.println("<<<<<ERROR: " + predOne.toString());
		// Se envia los datos al microservicio.
		CompletableFuture<String> respuesta = client.sendAsync(request, BodyHandlers.ofString())
				.thenApply(HttpResponse::body);

		// Se obtiene los resultados de los datos grabados por el microservicio, en
		// confirmación de exito/fracaso de la operación.
		String resultado = Stream.of(respuesta, respuesta).map(CompletableFuture::join)
				.collect(Collectors.joining(" "));

		ObjectMapper mapper = new ObjectMapper();

		// JSON string to Java Object
		try {
			LinkedHashMap<String, String> pred = (LinkedHashMap<String, String>) mapper.readValue(resultado, Map.class);

			Set set = pred.keySet();
			Iterator iterator = set.iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				Object value = pred.get(key);
				switch (key) {
				case "inpred":
					predTO.setInpred(Long.valueOf(value.toString()));
					break;
				case "clavex":
					predTO.setClavex(value.toString());
					break;
				case "valorX":
					predTO.setValorx(value.toString());
					break;
				case "borlog":
					predTO.setBorlog(value.toString());
					break;
				case "usupro":
					predTO.setUsupro(Long.valueOf(value.toString()));
					break;
				case "usucre":
					predTO.setUsucre(Long.valueOf(value.toString()));
					break;
				case "usuult":
					predTO.setUsuult(Long.valueOf(value.toString()));
					break;
				case "feccre":
					try {
						DateFormat formatter = new SimpleDateFormat(pattern);
						Date date = formatter.parse(value.toString().substring(0, 23).replaceAll("T", " "));
						Timestamp timestamp = new Timestamp(date.getTime());
						predTO.setFeccre(timestamp);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					break;
				case "fecult":
					try {
						DateFormat formatter = new SimpleDateFormat(pattern);
						Date date = formatter.parse(value.toString().substring(0, 23).replaceAll("T", " "));
						Timestamp timestamp = new Timestamp(date.getTime());
						predTO.setFecult(timestamp);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					break;
				default:
					break;
				}
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return predTO;
	}

	public List<PredTO> callSelectPred() {
		BeanAdmiPred beanAdmiPred = new BeanAdmiPred();
		Locale locale = new Locale("es", "ES");
		predAll.clear();
		String pattern = "yyyy-MM-dd HH:mm:ss.SSS";// 2021-01-08 09:56:06.445+00:00
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, locale);
		HttpClient client = HttpClient.newHttpClient();
		String varstr = URI.create("http://localhost:8116/pred/selectPred").toString();
		
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8116/pred/selectPred")).GET().build();

		CompletableFuture<String> respuesta = client.sendAsync(request, BodyHandlers.ofString())
				.thenApply(HttpResponse::body);

		String resultado = Stream.of(respuesta, respuesta).map(CompletableFuture::join)
				.collect(Collectors.joining(" "));

		ObjectMapper mapper = new ObjectMapper();

		// JSON string to Java Object
		try {
			ArrayList<LinkedHashMap<String, String>> anypred = (ArrayList<LinkedHashMap<String, String>>) mapper
					.readValue(resultado, List.class);

			for (LinkedHashMap pred : anypred) {
				Set set = pred.keySet();
				Iterator iterator = set.iterator();
				PredTO predTO = new PredTO();
				while (iterator.hasNext()) {
					String key = (String) iterator.next();
					Object value = pred.get(key);
					switch (key) {
					case "inpred":
						predTO.setInpred(Long.valueOf(value.toString()));
						break;
					case "clavex":
						predTO.setClavex((String) value);
						break;
					case "valorx":
						predTO.setValorx((String) value);
						break;
					case "borlog":
						predTO.setBorlog((String) value);
						break;
					case "usupro":
						predTO.setUsupro(Long.valueOf(value.toString()));
						break;
					case "usucre":
						predTO.setUsucre(Long.valueOf(value.toString()));
						break;
					case "usuult":
						predTO.setUsuult(Long.valueOf(value.toString()));
						break;
					case "feccre":
						try {
							DateFormat formatter = new SimpleDateFormat(pattern);
							Date date = formatter.parse(value.toString().substring(0, 23).replaceAll("T", " "));
							Timestamp timestamp = new Timestamp(date.getTime());
							predTO.setFeccre(timestamp);

						} catch (ParseException e) {
							e.printStackTrace();
						}
						break;
					case "fecult":
						try {
							DateFormat formatter = new SimpleDateFormat(pattern);
							Date date = formatter.parse(value.toString().substring(0, 23).replaceAll("T", " "));
							Timestamp timestamp = new Timestamp(date.getTime());
							predTO.setFecult(timestamp);

						} catch (ParseException e) {
							e.printStackTrace();
						}
						break;
					default:
						break;
					}
				}

				predAll.add(predTO);
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return predAll;
	}

}
