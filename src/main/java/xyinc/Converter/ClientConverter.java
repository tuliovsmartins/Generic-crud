package xyinc.Converter;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import xyinc.Entity.Client;
import xyinc.View.Model.ClientDataVM;

@Component
public class ClientConverter {
	
	public Client convert(ClientDataVM clientDataVM){
		
		Client client = new Client();
		
		client.setId(clientDataVM.getId());
		client.setName(clientDataVM.getName());
		client.setDocument(clientDataVM.getDocument());
		client.setCreateDate(clientDataVM.getCreateDate());
		
		return client;
	
	}
	
	public ClientDataVM convert(Client client){
		
		ClientDataVM clientDataVM = new ClientDataVM();
		
		clientDataVM.setId(client.getId());
		clientDataVM.setName(client.getName());
		clientDataVM.setDocument(client.getDocument());
		clientDataVM.setCreateDate(client.getCreateDate());
		
		return clientDataVM;
	
	}
	
	public List<ClientDataVM> convertList(List<Client> client){
		
		List<ClientDataVM> clientDataVM = new  ArrayList<ClientDataVM>();
		
		for(Client clients : client){
			clientDataVM.add(ClientDataVM.convertFrom(clients));
        }
		
		return clientDataVM;
		
	}

}
