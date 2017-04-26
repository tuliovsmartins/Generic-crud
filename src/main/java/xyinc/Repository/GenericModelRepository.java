package xyinc.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import xyinc.Converter.Converter;
import xyinc.Entity.Model;
import xyinc.Interface.Repository;
import xyinc.Validation.Validations;

@Component
public class GenericModelRepository implements Repository<Map<String,Object>> {

    @Autowired
    private MongoTemplate template;
    
    @Autowired
    private ModelRepository modelRepository;
    
    @Autowired
    private Validations validation;
    
    @Autowired
    private Converter converter;
    
    private Model model;    
    private String modelName;

    public void setModelName(String modelName) throws SQLException {
    	
        this.modelName = modelName;
        this.model = modelRepository.find(this.modelName);        
    }
    
    public Map<String, Object> insert(Map<String, Object> model) throws Exception {
    	
        this.validation.checkModelParam(modelName);        
        this.validation.schema(this.model.getFields(), model);
        DBObject dbObject = BasicDBObjectBuilder.start(model).get();
        template.getDb().getCollection(modelName).save(dbObject);
        ObjectId id = (ObjectId) dbObject.get("_id");
        return find(id.toString());
    }

    public Map<String, Object> update(Map<String, Object> model) throws Exception {
    	
        this.validation.checkModelParam(modelName);
        this.validation.schema(this.model.getFields(), model);
        DBObject data = BasicDBObjectBuilder.start(model).get();        
        ObjectId objectId = new ObjectId(data.get("_id").toString());
        DBObject filter = new BasicDBObject("_id", objectId);
        data.removeField("_id");
        template.getDb().getCollection(modelName).update(filter, data);
        
        return find(objectId.toString());    
    }

    public Map<String, Object> delete(String id) throws Exception {
    	
        this.validation.checkModelParam(modelName);
        Map<String, Object> resultDeleted = find(id);
        ObjectId objectId = new ObjectId(id);        
        DBObject filter = new BasicDBObject("_id", objectId);
        template.getDb().getCollection(modelName).remove(filter);
        return resultDeleted;
        
    }
    
    @SuppressWarnings({"unchecked","rawtypes"})
    public Map<String, Object> find(String id) throws Exception {
    	
        this.validation.checkModelParam(modelName);
        Map result = null;
        DBObject filter = new BasicDBObject("_id", new ObjectId(id));
        DBObject dbResult = template.getDb().getCollection(modelName).findOne(filter);        
        this.validation.checkNotFound(dbResult, result);        
        result = dbResult.toMap();
        converter.convertObjectIdToString(result);        
        return result;
    }
    
    @SuppressWarnings({"unchecked","rawtypes"})
    public List<Map<String, Object>> findAll() throws Exception {
    	
        this.validation.checkModelParam(modelName);        
        List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();        
        DBCursor cursor = template.getDb().getCollection(modelName).find();
        this.validation.checkCursor(cursor);
        
        Map map = null;
        while (cursor.hasNext()) {
            map = cursor.next().toMap();
            converter.convertObjectIdToString(map);            
            results.add(map);
        }
        cursor.close();
        return results;    
    }

}
