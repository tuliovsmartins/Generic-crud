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
public class ModelEndpointsTests {
    
    final String BASE_PATH = "http://localhost:8080/create/model";
    private RestTemplate restTemplate;
    
    @Before
    public void setUp() throws Exception {
        restTemplate = new RestTemplate();
    }
    
    
    //POST
    @Test
    @SuppressWarnings("unchecked")
    public void testCreateModel() throws JsonProcessingException{
        restTemplate.delete(BASE_PATH + "/clients");

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
        ResponseWrapper<Model> response = restTemplate.postForObject(BASE_PATH, model, ResponseWrapper.class);
        assertNotNull(response);
        Assert.assertEquals(HttpStatus.CREATED, response.getStatus());
        
        //Insert a repeated model 
        ResponseWrapper<Model> response2 = restTemplate.postForObject(BASE_PATH, model, ResponseWrapper.class);
        assertNotNull(response2);
        Assert.assertEquals(HttpStatus.CONFLICT, response2.getStatus());
    }
    
    //GET by ID
    @Test
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void testFindOne() throws JsonProcessingException{
        restTemplate.delete(BASE_PATH + "/clients");
        
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
        ResponseWrapper<Model> response = restTemplate.postForObject(BASE_PATH, model, ResponseWrapper.class);
        
        //Check if was updated
        ResponseWrapper response2 = restTemplate.getForObject(BASE_PATH + "/products", ResponseWrapper.class);
        assertNotNull(response);
        Assert.assertTrue(response2.getResult().toString().contains("products"));
    }
    
    //PUT
    @Test
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void testUpdateModel() throws JsonProcessingException{
        restTemplate.delete(BASE_PATH + "/clients");
        
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
        ResponseWrapper<Model> response = restTemplate.postForObject(BASE_PATH, model, ResponseWrapper.class);
        assertNotNull(response);
        Assert.assertEquals(HttpStatus.CREATED, response.getStatus());
        
        //Update previously inserted model 
        fields.put("name", "string");
        model.setFields(fields);
        restTemplate.put(BASE_PATH, model);
        
        //Check if was updated
        ResponseWrapper response2 = restTemplate.getForObject(BASE_PATH + "/clients", ResponseWrapper.class);
        assertNotNull(response);
        Assert.assertTrue(response2.getResult().toString().contains("name"));
    }
    
    //GET all
    @SuppressWarnings("rawtypes")
	@Test
    public void testFindAll() throws JsonProcessingException{
        ResponseWrapper response = restTemplate.getForObject(BASE_PATH, ResponseWrapper.class);
        assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatus());
    }

}
