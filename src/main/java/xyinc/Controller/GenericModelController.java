package xyinc.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import xyinc.Repository.GenericModelRepository;
import xyinc.Response.ResponseWrapper;

@RestController
@RequestMapping("/api")
public class GenericModelController {

    @Autowired
    private GenericModelRepository genericModelRepository;
    
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="/{modelName}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseWrapper<List<Map<String, Object>>>> getAllRecords(@PathVariable(value="modelName") String modelName) {
        List<Map<String, Object>> results = null;
        try {
            genericModelRepository.setModelName(modelName);
            results = genericModelRepository.findAll();
            if (results==null) {
                return new ResponseWrapper<List<Map<String, Object>>>(HttpStatus.NOT_FOUND, "Não encontrado", null).response();
            }    
            return new ResponseWrapper<List<Map<String, Object>>>(HttpStatus.OK, "Encontrado!", results).response();            
        } catch (Exception e) {
            return new ResponseWrapper<List<Map<String, Object>>>(HttpStatus.UNAUTHORIZED, e.getMessage(), results).response();
        }
    }
     
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="/{modelName}/{id}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseWrapper<Map<String, Object>>> getRecord(@PathVariable(value="modelName") String modelName, @PathVariable(value="id") String id) {
        
    	Map<String, Object> result = null;
        try {
    
            genericModelRepository.setModelName(modelName);
            result = (Map<String, Object>) genericModelRepository.find(id);
            if (result==null) {
                return new ResponseWrapper<Map<String, Object>>(HttpStatus.NOT_FOUND, "Não encontrado.", null).response();
            }        
            return new ResponseWrapper<Map<String, Object>>(HttpStatus.OK, "Encontrado!", result).response();
        } catch (Exception e) {
            return new ResponseWrapper<Map<String, Object>>(HttpStatus.UNAUTHORIZED, e.getMessage(), result).response();
        }
    }
    
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="/{modelName}",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseWrapper<Map<String, Object>>> insertRecord(@PathVariable(value="modelName") String modelName, @RequestBody Map<String, Object> data) {        
        Map<String, Object> result = null;
        try {        
            genericModelRepository.setModelName(modelName);
            result = genericModelRepository.insert((Map<String, Object>) data);
            return new ResponseWrapper<Map<String, Object>>(HttpStatus.CREATED, "Encontrado!", result).response();
        } catch (Exception e) {
            return new ResponseWrapper<Map<String, Object>>(HttpStatus.UNAUTHORIZED, e.getMessage(), result).response();
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="/{modelName}",method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseWrapper<Map<String, Object>>> updateRecord(@PathVariable(value="modelName") String modelName, @RequestBody Map<String, Object> data){
        Map<String, Object> result = null;
        try {        
            genericModelRepository.setModelName(modelName);
            if (genericModelRepository.find(data.get("_id").toString())==null) {
                return new ResponseWrapper<Map<String, Object>>(HttpStatus.NOT_FOUND, "Não encontrado.", null).response();
            }
            result = genericModelRepository.update(data);
            return new ResponseWrapper<Map<String, Object>>(HttpStatus.OK, "Encontrado!", result).response();
        } catch (Exception e) {
            return new ResponseWrapper<Map<String, Object>>(HttpStatus.UNAUTHORIZED, e.getMessage(), result).response();
        }        
    }
    
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="/{modelName}/{id}",method=RequestMethod.DELETE)
    public ResponseEntity<ResponseWrapper<Map<String, Object>>> deleteRecord(@PathVariable(value="modelName") String modelName, @PathVariable(value="id") String id) {        
        Map<String, Object> result = null;
        try {                        
            genericModelRepository.setModelName(modelName);
            if (genericModelRepository.find(id)==null) {
                return new ResponseWrapper<Map<String, Object>>(HttpStatus.NOT_FOUND, "Não encontrado.", null).response();
            }
            result = genericModelRepository.delete(id);
            return new ResponseWrapper<Map<String, Object>>(HttpStatus.OK, "Encontrado!", result).response();            
        } catch (Exception e) {
            return new ResponseWrapper<Map<String, Object>>(HttpStatus.UNAUTHORIZED, e.getMessage(), result).response();
        }        
    }
    

}
