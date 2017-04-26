package xyinc;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;

import xyinc.Entity.Model;
import xyinc.Response.ResponseWrapper;

@RunWith(SpringJUnit4ClassRunner.class)
public class DinamicEndpointsTests {
    
    final String BASE_PATH = "http://localhost:8080/create";
    final String BASE_PATH_MODEL = "http://localhost:8080/create/model";
    
    private RestTemplate restTemplate;
    
    @SuppressWarnings("unchecked")
	@Before
    public void setUp() throws Exception {
        restTemplate = new RestTemplate();
        
        restTemplate.delete(BASE_PATH_MODEL + "/clients");
        
        Model model = new Model();
        Map<String, String> fields = new HashMap<String, String>();
        
        fields.put("document", "string");
        fields.put("name", "string");
        fields.put("dateCreate", "Date");
        fields.put("birthDate", "Date");
        fields.put("PostalCode", "string");
        fields.put("_id", "long");
        model.setFields(fields);
        model.setName("clients");
        
        //Insert a new model 
        @SuppressWarnings("unused")
		ResponseWrapper<Model> response = restTemplate.postForObject(BASE_PATH_MODEL, model, ResponseWrapper.class);
    }
    
    
    //POST
    @Test
    @SuppressWarnings("unchecked")
    public void testCreateModel() throws JsonProcessingException{
        Map<String, Object> fields = new HashMap<String, Object>();
        
        fields.put("document", "123456789");
        fields.put("name", "Túlio Martins");
        fields.put("dateCreate", "2017-04-25 00:00:00");
        fields.put("birthDate", "1982-06-26 00:00:00");
        fields.put("PostalCode", "38412130");
        fields.put("_id", "12347890");

        ResponseWrapper<Map<String, Object>> response = restTemplate.postForObject(BASE_PATH + "/clients", fields, ResponseWrapper.class);
        assertNotNull(response);
        Assert.assertEquals(HttpStatus.CREATED, response.getStatus());
    }
    
    //GET by ID
    @Test
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void testFindOne() throws JsonProcessingException{
        Map<String, Object> fields = new HashMap<String, Object>();
        

        fields.put("document", "987654321");
        fields.put("name", "José das Couves");
        fields.put("dateCreate", "2017-04-25 00:00:00");
        fields.put("birthDate", "1982-06-26 00:00:00");
        fields.put("PostalCode", "38412144");
        fields.put("_id", "09874321");

        ResponseWrapper<Map<String, Object>> response = restTemplate.postForObject(BASE_PATH + "/clients", fields, ResponseWrapper.class);
        
        Map result = (Map) response.getResult();
        String id = (String) result.get("_id");

        ResponseWrapper response2 = restTemplate.getForObject(BASE_PATH + "/albums/" + id , ResponseWrapper.class);
        assertNotNull(response);
        Assert.assertTrue(response2.getResult().toString().contains("gender=Progressive Rock, year=1971, artist=Jethro Tull, name=Aqualung"));
    }
    
    //PUT
    @Test
    @SuppressWarnings("unchecked")
    public void testCreateUnsuportedModel() throws JsonProcessingException{
        Map<String, Object> fields = new HashMap<String, Object>();
        
        fields.put("manufacturer", "SAMSUNG");
        fields.put("details", "Explosive");
        fields.put("model", "Galaxy Note 7 ");

        ResponseWrapper<Map<String, Object>> response = restTemplate.postForObject(BASE_PATH + "/telephones", fields, ResponseWrapper.class);
        assertNotNull(response);
        Assert.assertEquals(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE, response.getStatus());
    }
    
    //GET all
    @SuppressWarnings("rawtypes")
	@Test
    public void testFindAll() throws JsonProcessingException{
        ResponseWrapper response = restTemplate.getForObject(BASE_PATH + "/albums", ResponseWrapper.class);
        assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatus());
    }

}
