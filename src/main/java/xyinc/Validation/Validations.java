package xyinc.Validation;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import xyinc.Datatype.DataType;
import xyinc.Repository.ModelRepository;

@Component
public class Validations {

    @Autowired
    private DataType types;

    @Autowired
    private ModelRepository modelRepository;

    @SuppressWarnings("rawtypes")
    public void schema(Map<String, String> schema, Map data) {
    	
        if (schema == null || schema.size() == 0 || data == null    || data.size() == 0) {
            throw new IllegalArgumentException("Modelo Vazio");
        }
        
        int dataSize = (data.containsKey("_id"))? data.size()-1 : data.size();
        
        if (dataSize!=schema.size()){
            throw new IllegalArgumentException("Modelo inválido");
        }
        
        Class<?> javaType = null;
        String mapType;
        for (Object field : data.keySet()) {

            if ("_id".equalsIgnoreCase((String) field)){
                continue;
            }
            
            mapType = schema.get((String) field);
            if (mapType == null) {
                throw new IllegalArgumentException("Atributos " + field + " não existem no modelo.");
            }

            javaType = types.getType(mapType);
            if (javaType == null) {
                throw new IllegalArgumentException("Tipo de dado inválido" + field);
            }

            try {
                Object object = javaType.cast(data.get(field));
                if (object == null) {
                    throw new IllegalArgumentException("Tipo de dado inválido para o campo" + field);
                }
            } catch (ClassCastException e) {
                throw new IllegalArgumentException("Tipo de dado inválido para o campo"  + field);            }
        }
    }
    
    public void checkModelParam(String modelName) throws Exception{
        if (modelName==null || modelName.length()==0){
            throw new IllegalArgumentException("Defina um nome de modelo.");
        }
        if (modelRepository.find(modelName)==null){
            throw new IllegalArgumentException("Modelo " + modelName + " não foi encontrado.");
        }
    }
    
    
    @SuppressWarnings("rawtypes")
    public void checkNotFound(DBObject dbResult, Map result) throws SQLException{
        if (dbResult==null){
            throw new SQLException("Não encontrado.");
        }
        
        result = dbResult.toMap(); 
        if (result==null || result.size()==0){
            throw new SQLException("Não encontrado.");
        }
    }
    
    public void checkCursor(DBCursor cursor) throws SQLException{
        if (cursor==null){
            throw new SQLException("Não encontrado.");
        }
    }
}
