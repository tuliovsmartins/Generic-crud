package xyinc.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyinc.Converter.ClientConverter;
import xyinc.Dao.ClientDao;
import xyinc.View.Model.ClientDataVM;

@Service
public class ClientService {
	
	@Autowired
	ClientDao clientDao;
	
	@Autowired
	ClientConverter clientConverter;
		
	public void createClient(ClientDataVM clientDataVM){
		
		clientDao.create(clientConverter.convert(clientDataVM));
		
	}
	
	public List<ClientDataVM> retrieveClient(){
		
		List<ClientDataVM> clientDataVM = clientConverter.convertList(clientDao.getAll());
		
		return clientDataVM;
		
	}
	
	public ClientDataVM retrieveIndividualClient(long id){
		
		ClientDataVM clientDataVM = clientConverter.convert(clientDao.getById(id));
		
		return clientDataVM;
		
	}
	
	public void DeleteById(long id){
		ClientDataVM clientDataVM = clientConverter.convert(clientDao.getById(id));
		clientDao.deleteById(clientConverter.convert(clientDataVM));
		
		
	}

}
