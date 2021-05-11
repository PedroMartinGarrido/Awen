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
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import awen.commons.to.PaisTO;
import awen.commons.to.PredTO;
import es.web.controllers.BeanAdmiPais;

public class ServiciosPais {

	ArrayList<PaisTO> paisAll = new ArrayList<PaisTO>();
//	List<PredTO> listpred;

	
//	public ServiciosPais() {
//		ServiciosPred serviciosPred = new ServiciosPred();
//		listpred = null;
//		listpred = serviciosPred.callSelectPred();
//	}

	public PaisTO callUpdatePais(PaisTO paisOne, boolean isUpdate) {
		BeanAdmiPais beanAdmiPais = new BeanAdmiPais();
		PaisTO paisTO = new PaisTO();

		Locale locale = new Locale("es", "ES");
		String pattern = "yyyy-MM-dd HH:mm:ss.SSS"; // 2021-01-08 09:56:06.445+00:00
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, locale);

		// Se preparan la solicitud para enviar al microservicio.
		HttpClient client = HttpClient.newHttpClient();

		/////
		System.out.println("Dentro callUpdatePais");
		paisTO.printPais(paisTO);

		String strUri = "";
		if (isUpdate) {
			strUri = "http://localhost:8101/pais/updateInpais?inpais=" + paisOne.getInpais() + "&nopais="
					+ paisOne.getNopais().replace(" ", "+") + "&contin=" + paisOne.getIncont() + "&unieur="
					+ paisOne.getUnieur();
		} else {
			strUri = "http://localhost:8101/pais/disableInpais?inpais=" + paisOne.getInpais();
		}
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(strUri)).GET().build();
//		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8101/pais/updateInpais?inpais=" + paisOne.getInpais() + "&nopais=" + paisOne.getNopais().replace(" ", "+") + "&contin=" + paisOne.getContin() + "&unieur=" + paisOne.getUnieur())).GET()
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
			LinkedHashMap<String, String> pais = (LinkedHashMap<String, String>) mapper.readValue(resultado, Map.class);

			Set set = pais.keySet();
			Iterator iterator = set.iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				Object value = pais.get(key);
				switch (key) {
				case "inpais":
					paisTO.setInpais(Long.valueOf(value.toString()));
					break;
				case "nopais":
					paisTO.setNopais(value.toString());
					break;
				case "contin":
					paisTO.setContin(value.toString());
					break;
				case "unieur":
					paisTO.setUnieur(value.toString() == "true");
					break;
				case "borlog":
					paisTO.setBorlog(value.toString());
					break;
				case "usupro":
					if (value != null) {
						paisTO.setUsupro(Long.valueOf(value.toString()));
					}
					break;
				case "usucre":
					if (value != null) {
						paisTO.setUsucre(Long.valueOf(value.toString()));
					}
					break;
				case "usuult":
					if (value != null) {
						paisTO.setUsuult(Long.valueOf(value.toString()));
					}
					break;
				case "feccre":
					try {
						DateFormat formatter = new SimpleDateFormat(pattern);
						Date date = formatter.parse(value.toString().substring(0, 23).replaceAll("T", " "));
						Timestamp timestamp = new Timestamp(date.getTime());
						paisTO.setFeccre(timestamp);

					} catch (ParseException e) {
						e.printStackTrace();
					}
					break;
				case "fecult":
					try {
						DateFormat formatter = new SimpleDateFormat(pattern);
						Date date = formatter.parse(value.toString().substring(0, 23).replaceAll("T", " "));
						Timestamp timestamp = new Timestamp(date.getTime());
						paisTO.setFecult(timestamp);

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

	public PaisTO callInsertPais(PaisTO paisOne) {
		BeanAdmiPais beanAdmiPais = new BeanAdmiPais();
		PaisTO paisTO = new PaisTO();

		Locale locale = new Locale("es", "ES");
		String pattern = "yyyy-MM-dd HH:mm:ss.SSS"; // 2021-01-08 09:56:06.445+00:00
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, locale);

		// Se preparan la solicitud para enviar al microservicio.
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("http://localhost:8101/pais/insertPais?nopais=" + paisOne.getNopais().replace(" ", "+")
						+ "&contin=" + paisOne.getIncont() + "&unieur=" + paisOne.getUnieur()))
				.GET().build();

		// System.out.println("<<<<<ERROR: " + paisOne.toString());
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
			LinkedHashMap<String, String> pais = (LinkedHashMap<String, String>) mapper.readValue(resultado, Map.class);

			Set set = pais.keySet();
			Iterator iterator = set.iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				Object value = pais.get(key);
				switch (key) {
				case "inpais":
					paisTO.setInpais(Long.valueOf(value.toString()));
					break;
				case "nopais":
					paisTO.setNopais(value.toString());
					break;
				case "contin":
					paisTO.setContin(value.toString());
					break;
				case "unieur":
					paisTO.setUnieur(value.toString() == "true");
					break;
				case "borlog":
					paisTO.setBorlog(value.toString());
					break;
				case "usupro":
					paisTO.setUsupro(Long.valueOf(value.toString()));
					break;
				case "usucre":
					paisTO.setUsucre(Long.valueOf(value.toString()));
					break;
				case "usuult":
					paisTO.setUsuult(Long.valueOf(value.toString()));
					break;
				case "feccre":
					try {
						DateFormat formatter = new SimpleDateFormat(pattern);
						Date date = formatter.parse(value.toString().substring(0, 23).replaceAll("T", " "));
						Timestamp timestamp = new Timestamp(date.getTime());
						paisTO.setFeccre(timestamp);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					break;
				case "fecult":
					try {
						DateFormat formatter = new SimpleDateFormat(pattern);
						Date date = formatter.parse(value.toString().substring(0, 23).replaceAll("T", " "));
						Timestamp timestamp = new Timestamp(date.getTime());
						paisTO.setFecult(timestamp);
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
		BeanAdmiPais beanAdmiPais = new BeanAdmiPais();
		Locale locale = new Locale("es", "ES");
		paisAll.clear();
		String pattern = "yyyy-MM-dd HH:mm:ss.SSS";// 2021-01-08 09:56:06.445+00:00
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, locale);

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8101/pais/selectPais")).GET().build();

		CompletableFuture<String> respuesta = client.sendAsync(request, BodyHandlers.ofString()).thenApply(HttpResponse::body);

		String resultado = Stream.of(respuesta, respuesta).map(CompletableFuture::join).collect(Collectors.joining(" "));

		ObjectMapper mapper = new ObjectMapper();

		// JSON string to Java Object
		try {
			ArrayList<LinkedHashMap<String, String>> anypais = (ArrayList<LinkedHashMap<String, String>>) mapper.readValue(resultado, List.class);

			for (LinkedHashMap pais : anypais) {
				Set set = pais.keySet();
				Iterator iterator = set.iterator();
				PaisTO paisTO = new PaisTO();
				while (iterator.hasNext()) {
					String key = (String) iterator.next();
					Object value = pais.get(key);
					
					switch (key) {
					case "inpais":
						/////
						System.out.println("\\\\\\\\\\\\ Continente inpais: [" + value + "]");
						
						paisTO.setInpais(Long.valueOf(value.toString()));
						break;
					case "nopais":
						/////
						System.out.println("\\\\\\\\\\\\ Continente nopais: [" + value + "]");
						
						paisTO.setNopais((String) value);
						break;
//					case "incont":
//						/////
//						System.out.println("\\\\\\\\\\\\ Continente incont_ [" + value + "]");
//						
//						paisTO.setContin(callSelectPred(Long.valueOf(value.toString())).get().getValorx());
//						break;
					case "contin":
						/////
						System.out.println("\\\\\\\\\\\\ Continente contin: [" + value + "]");
						
						paisTO.setContin(value.toString());
						break;
					case "unieur":
						paisTO.setUnieur(value.toString() == "true");
						break;
					case "borlog":
						paisTO.setBorlog((String) value);
						break;
					case "usupro":
						paisTO.setUsupro(Long.valueOf(value.toString()));
						break;
					case "usucre":
						paisTO.setUsucre(Long.valueOf(value.toString()));
						break;
					case "usuult":
						paisTO.setUsuult(Long.valueOf(value.toString()));
						break;
					case "feccre":
						try {
							DateFormat formatter = new SimpleDateFormat(pattern);
							Date date = formatter.parse(value.toString().substring(0, 23).replaceAll("T", " "));
							Timestamp timestamp = new Timestamp(date.getTime());
							paisTO.setFeccre(timestamp);

						} catch (ParseException e) {
							e.printStackTrace();
						}
						break;
					case "fecult":
						try {
							DateFormat formatter = new SimpleDateFormat(pattern);
							Date date = formatter.parse(value.toString().substring(0, 23).replaceAll("T", " "));
							Timestamp timestamp = new Timestamp(date.getTime());
							paisTO.setFecult(timestamp);

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

//	private Optional<PredTO> callSelectPred(Long inpred) {
//		return listpred.stream().filter(s -> inpred.equals(s.getInpred())).findFirst();
//	}
	
}
