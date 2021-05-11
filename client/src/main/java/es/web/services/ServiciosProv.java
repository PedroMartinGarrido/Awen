package es.web.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;
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

import awen.commons.to.ProvTO;

public class ServiciosProv {

	ArrayList<ProvTO> provAll = new ArrayList<ProvTO>();
	Hashtable htContin = new Hashtable();

	public ProvTO callUpdateProv(ProvTO provOne, boolean isUpdate) {
		ProvTO provTO = new ProvTO();

		Locale locale = new Locale("es", "ES");
		String pattern = "yyyy-MM-dd HH:mm:ss.SSS"; // 2021-01-08 09:56:06.445+00:00
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, locale);

		// Se preparan la solicitud para enviar al microservicio.
		HttpClient client = HttpClient.newHttpClient();

		String strUri = "";
		if (isUpdate) {
			strUri = "http://localhost:8101/prov/updateInprov?inprov=" + provOne.getInprov() + "&provin=" 
					+ provOne.getProvin().replace(" ", "+") + "&inpais=" + provOne.getInpais();
		} else {
			strUri = "http://localhost:8101/prov/disableInprov?inprov=" + provOne.getInprov();
		}
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(strUri)).GET().build();
//		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8101/prov/updateInprov?inprov=" + provOne.getInprov() + "&provin=" + provOne.getProvin().replace(" ", "+") + "&inpais=" + provOne.getInpais())).GET()
//				.build();

		// Se envia los datos al microservicio.
		CompletableFuture<String> respuesta = client.sendAsync(request, BodyHandlers.ofString())
				.thenApply(HttpResponse::body);

		// Se obtiene los resultados de los datos grabados por el microservicio, en
		// confirmación de exito/fracaso de la operación.
		String resultado = Stream.of(respuesta, respuesta).map(CompletableFuture::join)
				.collect(Collectors.joining(" "));

		ObjectMapper mapper = new ObjectMapper();

		System.out.println("<<<< Resultado: " + resultado.toString());

		// JSON string to Java Object
		try {
			// System.out.println ("ANTES DE");

			LinkedHashMap<String, String> prov = (LinkedHashMap<String, String>) mapper.readValue(resultado, Map.class);

			// System.out.println ("DESPUES DE");

			Set set = prov.keySet();
			Iterator iterator = set.iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				// System.out.println("****** >> Key=" + key);
				Object value = prov.get(key);
				switch (key) {
				case "inprov":
					provTO.setInprov(value.toString());
					break;
				case "provin":
					provTO.setProvin(value.toString());
					break;
				case "inpais":
					provTO.setInpais(value.toString());
					break;
				case "borlog":
					provTO.setBorlog(value.toString());
					break;
				case "usupro":
					provTO.setUsupro(value.toString());
					break;
				case "usucre":
					provTO.setUsucre(value.toString());
					break;
				case "usuult":
					provTO.setUsuult(value.toString());
					break;
				case "feccre":
					try {
						provTO.setFeccre(dateFormat.parse(value.toString().substring(0, 23).replaceAll("T", " ")));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					break;
				case "fecult":
					try {
						provTO.setFecult(dateFormat.parse(value.toString().substring(0, 23).replaceAll("T", " ")));
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

		return provTO;
	}

	public ProvTO callInsertProv(ProvTO provOne) {
		ProvTO provTO = new ProvTO();

		Locale locale = new Locale("es", "ES");
		String pattern = "yyyy-MM-dd HH:mm:ss.SSS"; // 2021-01-08 09:56:06.445+00:00
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, locale);

		// Se preparan la solicitud para enviar al microservicio.
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("http://localhost:8101/prov/insertProv?provin=" + provOne.getProvin().replace(" ", "+")
						+ "&inpais=" + provOne.getInpais()))
				.GET().build();

		// System.out.println("<<<<<ERROR: " + provOne.toString());
		// Se envia los datos al microservicio.
		CompletableFuture<String> respuesta = client.sendAsync(request, BodyHandlers.ofString())
				.thenApply(HttpResponse::body);

		// Se obtiene los resultados de los datos grabados por el microservicio, en
		// confirmación de exito/fracaso de la operación.
		String resultado = Stream.of(respuesta, respuesta).map(CompletableFuture::join)
				.collect(Collectors.joining(" "));

		ObjectMapper mapper = new ObjectMapper();

		System.out.println("<<<< Resultado: " + resultado.toString());

		// JSON string to Java Object
		try {
			// System.out.println ("ANTES DE");

			LinkedHashMap<String, String> prov = (LinkedHashMap<String, String>) mapper.readValue(resultado, Map.class);

			// System.out.println ("DESPUES DE");

			Set set = prov.keySet();
			Iterator iterator = set.iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				// System.out.println("****** >> Key=" + key);
				Object value = prov.get(key);
				switch (key) {
				case "inprov":
					provTO.setInprov(value.toString());
					break;
				case "provin":
					provTO.setProvin(value.toString());
					break;
				case "inpais":
					provTO.setInpais(value.toString());
					break;
				case "borlog":
					provTO.setBorlog(value.toString());
					break;
				case "usupro":
					provTO.setUsupro(value.toString());
					break;
				case "usucre":
					provTO.setUsucre(value.toString());
					break;
				case "usuult":
					provTO.setUsuult(value.toString());
					break;
				case "feccre":
					try {
						provTO.setFeccre(dateFormat.parse(value.toString().substring(0, 23).replaceAll("T", " ")));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					break;
				case "fecult":
					try {
						provTO.setFecult(dateFormat.parse(value.toString().substring(0, 23).replaceAll("T", " ")));
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
		return provTO;
	}

	public List<ProvTO> callSelectProv() {
		Locale locale = new Locale("es", "ES");
		provAll.clear();
		String pattern = "yyyy-MM-dd HH:mm:ss.SSS";// 2021-01-08 09:56:06.445+00:00
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, locale);
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8101/prov/selectProv")).GET()
				.build();

		CompletableFuture<String> respuesta = client.sendAsync(request, BodyHandlers.ofString())
				.thenApply(HttpResponse::body);

		String resultado = Stream.of(respuesta, respuesta).map(CompletableFuture::join)
				.collect(Collectors.joining(" "));

		ObjectMapper mapper = new ObjectMapper();

		// JSON string to Java Object
		try {
			ArrayList<LinkedHashMap<String, String>> anyprov = (ArrayList<LinkedHashMap<String, String>>) mapper
					.readValue(resultado, List.class);

			for (LinkedHashMap prov : anyprov) {
				Set set = prov.keySet();
				Iterator iterator = set.iterator();
				ProvTO provTO = new ProvTO();
				while (iterator.hasNext()) {
					String key = (String) iterator.next();
					String value = (String) prov.get(key).toString();
					// System.out.println("Key = " + key + " values = " + value);
					switch (key) {
					case "inprov":
						provTO.setInprov(value);
						break;
					case "provin":
						provTO.setProvin(value);
						break;
					case "inpais":
						provTO.setInpais(value.toString());
						break;
					case "borlog":
						provTO.setBorlog(value);
						break;
					case "usupro":
						provTO.setUsupro(value);
						break;
					case "usucre":
						provTO.setUsucre(value);
						break;
					case "usuult":
						provTO.setUsuult(value);
						break;
					case "feccre":
						try {
							provTO.setFeccre(dateFormat.parse(value.substring(0, 23).replaceAll("T", " ")));
						} catch (ParseException e) {
							e.printStackTrace();
						}
						break;
					case "fecult":
						try {
							provTO.setFecult(dateFormat.parse(value.substring(0, 23).replaceAll("T", " ")));
						} catch (ParseException e) {
							e.printStackTrace();
						}
						break;
					default:
						break;
					}
				}
				provAll.add(provTO);
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return provAll;
	}

}
