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

import awen.commons.to.PaisTO;

public class ServiciosPais {
	ArrayList<PaisTO> paisAll = new ArrayList<PaisTO>();
	
	public PaisTO callUpdatePais(PaisTO paisOne, boolean isUpdate) {
		System.out.println("-----------------------");
		System.out.println("Estoy en callUpdatePais y isUpdate es " + isUpdate);
		System.out.println("issele: " + paisOne.getIssele());
		System.out.println("inpais: " + paisOne.getInpais());
		System.out.println("nopais: " + paisOne.getNopais());
		System.out.println("contin: " + paisOne.getContin());
		System.out.println("comeur: " + paisOne.getComeur());
		System.out.println("borlog: " + paisOne.getBorlog());
		
		PaisTO paisTO = new PaisTO();
		
		Locale locale = new Locale("es", "ES");
		String pattern = "yyyy-MM-dd HH:mm:ss.SSS";  // 2021-01-08 09:56:06.445+00:00
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, locale);

		// Se preparan la solicitud para enviar al microservicio.
		HttpClient client = HttpClient.newHttpClient();
		
		String strUri = "";
		if (isUpdate) {
		    strUri = "http://localhost:8101/pais/updateInpais?inpais=" + paisOne.getInpais() + "&nopais=" + paisOne.getNopais().replace(" ", "+") + "&contin=" + paisOne.getContin() + "&comeur=" + paisOne.getComeur();
		}
		else {
			strUri = "http://localhost:8101/pais/disableInpais?inpais=" + paisOne.getInpais();
		}
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(strUri)).GET().build();
//		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8101/pais/updateInpais?inpais=" + paisOne.getInpais() + "&nopais=" + paisOne.getNopais().replace(" ", "+") + "&contin=" + paisOne.getContin() + "&comeur=" + paisOne.getComeur())).GET()
//				.build();

		// Se envia los datos al microservicio.
		CompletableFuture<String> respuesta = client.sendAsync(request, BodyHandlers.ofString()).thenApply(HttpResponse::body);

		// Se obtiene los resultados de los datos grabados por  el microservicio, en confirmación de exito/fracaso de la operación.
		String resultado = Stream.of(respuesta, respuesta).map(CompletableFuture::join).collect(Collectors.joining(" "));

		ObjectMapper mapper = new ObjectMapper();

		System.out.println("<<<< Resultado: " + resultado.toString());
		
		// JSON string to Java Object
		try {
			//System.out.println ("ANTES DE");
			
			LinkedHashMap<String, String> pais = (LinkedHashMap<String, String>) mapper.readValue(resultado, Map.class);
			
			//System.out.println ("DESPUES DE");
			
			Set set = pais.keySet();
			Iterator iterator = set.iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				//System.out.println("****** >> Key=" + key);
				Object value = pais.get(key);
				switch (key) {
				case "inpais":
					paisTO.setInpais(value.toString());
					break;
				case "nopais":
					paisTO.setNopais(value.toString());
					break;
				case "contin":
					paisTO.setContin(value.toString());
					break;
				case "comeur":
					paisTO.setComeur(value.toString());
					break;
				case "borlog":
					paisTO.setBorlog(value.toString());
					break;
				case "usupro":
					paisTO.setUsupro(value.toString());
					break;
				case "usucre":
					paisTO.setUsucre(value.toString());
					break;
				case "usuult":
					paisTO.setUsuult(value.toString());
					break;
				case "feccre":
					try {
						paisTO.setFeccre(dateFormat.parse(value.toString().substring(0, 23).replaceAll("T", " ")));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					break;
				case "fecult":
					try {
						paisTO.setFecult(dateFormat.parse(value.toString().substring(0, 23).replaceAll("T", " ")));
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

		return paisTO;
	}

	public PaisTO callInsertPais (PaisTO paisOne) {
		PaisTO paisTO = new PaisTO();

		Locale locale = new Locale("es", "ES");
		String pattern = "yyyy-MM-dd HH:mm:ss.SSS";  // 2021-01-08 09:56:06.445+00:00
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, locale);

		// Se preparan la solicitud para enviar al microservicio.
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8101/pais/insertPais?nopais=" + paisOne.getNopais().replace(" ", "+") + "&contin=" + paisOne.getContin() + "&comeur=" + paisOne.getComeur())).GET()
				.build();

		//System.out.println("<<<<<ERROR: " + paisOne.toString());
		// Se envia los datos al microservicio.
		CompletableFuture<String> respuesta = client.sendAsync(request, BodyHandlers.ofString())
				.thenApply(HttpResponse::body);

		// Se obtiene los resultados de los datos grabados por  el microservicio, en confirmación de exito/fracaso de la operación.
		String resultado = Stream.of(respuesta, respuesta).map(CompletableFuture::join).collect(Collectors.joining(" "));

		ObjectMapper mapper = new ObjectMapper();

		System.out.println("<<<< Resultado: " + resultado.toString());
		
		// JSON string to Java Object
		try {
			//System.out.println ("ANTES DE");
			
			LinkedHashMap<String, String> pais = (LinkedHashMap<String, String>) mapper.readValue(resultado, Map.class);
			
			//System.out.println ("DESPUES DE");
			
			Set set = pais.keySet();
			Iterator iterator = set.iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				//System.out.println("****** >> Key=" + key);
				Object value = pais.get(key);
				switch (key) {
				case "inpais":
					paisTO.setInpais(value.toString());
					break;
				case "nopais":
					paisTO.setNopais(value.toString());
					break;
				case "contin":
					paisTO.setContin(value.toString());
					break;
				case "comeur":
					paisTO.setComeur(value.toString());
					break;
				case "borlog":
					paisTO.setBorlog(value.toString());
					break;
				case "usupro":
					paisTO.setUsupro(value.toString());
					break;
				case "usucre":
					paisTO.setUsucre(value.toString());
					break;
				case "usuult":
					paisTO.setUsuult(value.toString());
					break;
				case "feccre":
					try {
						paisTO.setFeccre(dateFormat.parse(value.toString().substring(0, 23).replaceAll("T", " ")));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					break;
				case "fecult":
					try {
						paisTO.setFecult(dateFormat.parse(value.toString().substring(0, 23).replaceAll("T", " ")));
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
		return paisTO;
	}
	
	public List<PaisTO> callSelectPais() {
		Locale locale = new Locale("es", "ES");
		paisAll.clear();
		String pattern = "yyyy-MM-dd HH:mm:ss.SSS";// 2021-01-08 09:56:06.445+00:00
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, locale);
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8101/pais/selectPais")).GET().build();

		CompletableFuture<String> respuesta = client.sendAsync(request, BodyHandlers.ofString())
				.thenApply(HttpResponse::body);

		String resultado = Stream.of(respuesta, respuesta).map(CompletableFuture::join).collect(Collectors.joining(" "));

		ObjectMapper mapper = new ObjectMapper();

		// JSON string to Java Object
		try {
			ArrayList<LinkedHashMap<String, String>> datospaises = (ArrayList<LinkedHashMap<String, String>>) mapper
					.readValue(resultado, List.class);

			for (LinkedHashMap pais : datospaises) {
				Set set = pais.keySet();
				Iterator iterator = set.iterator();
				PaisTO paisTO = new PaisTO();
				while (iterator.hasNext()) {
					String key = (String) iterator.next();
					String value = (String) pais.get(key).toString();
					//System.out.println("Key = " + key + " values = " + value);
					switch (key) {
					case "inpais":
						paisTO.setInpais(value);
						break;
					case "nopais":
						paisTO.setNopais(value);
						break;
					case "contin":
						paisTO.setContin(value);
						break;
					case "comeur":
						paisTO.setComeur(value);
						break;
					case "borlog":
						paisTO.setBorlog(value);
						break;
					case "usupro":
						paisTO.setUsupro(value);
						break;
					case "usucre":
						paisTO.setUsucre(value);
						break;
					case "usuult":
						paisTO.setUsuult(value);
						break;
					case "feccre":
						try {
							paisTO.setFeccre(dateFormat.parse(value.substring(0, 23).replaceAll("T", " ")));
						} catch (ParseException e) {
							e.printStackTrace();
						}
						break;
					case "fecult":
						try {
							paisTO.setFecult(dateFormat.parse(value.substring(0, 23).replaceAll("T", " ")));
						} catch (ParseException e) {
							e.printStackTrace();
						}
						break;
					default:
						break;
					}
				}
				paisAll.add(paisTO);
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return paisAll;
	}

}
