package xyinc.Controller;

import java.util.List;

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

import xyinc.Entity.Model;
import xyinc.Repository.ModelRepository;
import xyinc.Response.ResponseWrapper;

@RestController
@RequestMapping("/create/model")
public class ModelController {

    @Autowired
    private ModelRepository modelRepository;
    
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseWrapper<List<Model>>> getAllRecords() {
        List<Model> models = null;
        try {
            models = modelRepository.findAll();
            if (models==null) {
                return new ResponseWrapper<List<Model>>(HttpStatus.NOT_FOUND, "Não encontrado.", null).response();
            }        
            return new ResponseWrapper<List<Model>>(HttpStatus.OK, "Encontrado!", models).response();    
        } catch (Exception e) {
            return new ResponseWrapper<List<Model>>(HttpStatus.UNAUTHORIZED, e.getMessage(), models).response();            
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="/{id}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseWrapper<Model>> getRecord(@PathVariable(value="id") String id) {
        Model model = null;
        
        try {
            model = modelRepository.find(id);
            if (model==null) {
                return new ResponseWrapper<Model>(HttpStatus.NOT_FOUND, "Não encontrado.", null).response();
            }    
            return new ResponseWrapper<Model>(HttpStatus.OK, "Encontrado!", model).response();                
        } catch (Exception e) {
            return new ResponseWrapper<Model>(HttpStatus.UNAUTHORIZED, e.getMessage(), model).response();
        }
    }
    
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseWrapper<Model>> insertRecord(@RequestBody Model model) {        
        Model result;
        try {                        
            if (modelRepository.find(model.getName())!=null) {
                return new ResponseWrapper<Model>(HttpStatus.CONFLICT, "Modelo já existente.", model).response();
            }
            result = modelRepository.insert(model);
            return new ResponseWrapper<Model>(HttpStatus.CREATED, "Inserido!", result).response();    
    
        } catch (Exception e) {
            return new ResponseWrapper<Model>(HttpStatus.UNAUTHORIZED, e.getMessage(), model).response();
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="/{id}",method=RequestMethod.PUT,consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseWrapper<Model>> updateRecord(@RequestBody Model model, @PathVariable(value="id") String id){
        Model result = null;
        model.setName(id);
        try {
            if (modelRepository.find(model.getName())==null) {
                return new ResponseWrapper<Model>(HttpStatus.NOT_FOUND, "Não encontrado.", null).response();
            }
            
            result = modelRepository.update(model);
            return new ResponseWrapper<Model>(HttpStatus.OK, "Atualizado!", result).response();
    
        } catch (Exception e) {
            return new ResponseWrapper<Model>(HttpStatus.UNAUTHORIZED, e.getMessage(), model).response();
        }        
    }
    
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public ResponseEntity<ResponseWrapper<Model>> deleteRecord(@PathVariable(value="id") String id) {        
        Model result = null;
        try {            
            if (modelRepository.find(id)==null) {
                return new ResponseWrapper<Model>(HttpStatus.NOT_FOUND, "Não encontrado", null).response();
            }
            result = modelRepository.delete(id);
            return new ResponseWrapper<Model>(HttpStatus.OK, "Deletado!", result).response();    
    
        } catch (Exception e) {
            return new ResponseWrapper<Model>(HttpStatus.UNAUTHORIZED, e.getMessage(), result).response();
        }        
    }
}
