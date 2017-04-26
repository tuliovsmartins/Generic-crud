# xy-inc

<<<<<<< HEAD
Simple generic aplication to create dinamic models

# Tecnologias Usadas

1. Spring-boot
2. Maven
3. MongoDB (Para o funcionamento da aplicação, é necessário instalar o MongoDB que pode ser encontrado em http://www.mongodb.org/downloads) 
4. Tomcat (Embarcado junto ao spring-boot)
5. JUnit

# Para clonar o projeto:

~#: mkdir xy-inc
~#: cd xy-inc
~#: git clone https://github.com/tuliovsmartins/xy-inc.git
~#: cd xy-inc

# Executando a aplicação:

~#: mvn spring-boot:run

Após a inicialização `http://localhost:8080/create/model` ou pelo comando ~#: `curl http://localhost:8080/create/model`

# Criando um novo model

Para criar um model é necessário enviar uma mensagem do tipo JSON para o endpoint `http://localhost:8080/create/model` no seguinte formato:

Exemplo: 

`{
	"name": "clients",
	"fields" : {
		"name": "String",
		"document": "String",
		"dateCreate": "Date",
		"birthDate": "Date",
		"PostalCode": "String",
		"_id": "long"
	}
}`

Após isto será criado um modelo Genérico com nome clients e com os campos:  
name - do tipo String  
document: do tipo String  
dateCreate: do tipo Date  
birthDate: do tipo Date
_id: do tipo long

Após a criação de um modelo, podemos ter uma lista de modelos criados acessando o endereço: 
`http://localhost:8080/create/model`

A resposta deve ser parecida com a resposta abaixo:
`{"status":"OK","description":"Success!","result":[{"name":"clients","fields":{"document":"string","name":"string","dateCreate":"date"}}]}`

**Deletando um modelo**
Para deletar um modelo é necessário fazer uma requisição do tipo delete passando o nome do modelo a ser excluído:

`http://localhost:8080/create/model/clients`

O modelo clients será apagado.


**Editando um modelo**
Para atualizar um modelo, deve ser enviada uma requisição do tipo PUT com o nome do modelo passando os dados a serem alterados no modelo:

`http://localhost:8080/create/model/clients`

Exemplo de JSON a ser enviado: 

`{
	"fields":{
		"document": "12347890",
		"name": "Túlio martins",
		"dateCreate": "",
		"birthDate": "",
		"PostalCode": "38412130"
	}
}`


**Listando ítens de um modelo**
Enviar uma requisição do tipo GET com o nome do modelo

`http://localhost:8080/create/model/clients`


**Retornando um ítem pelo id**
Enviar uma requisição do tipo GET com o nome do modelo seguido do id que deseja visualizar o ítem

`http://localhost:8080/create/model/clients/78901234`
=======
Simple crud to manipulate products and clients

Exemplo de CRUD simples com 2 entidades e objetos usando boas práticas de acesso, Controller, Services, Converters, Configs, etc...

Exemplo criado apenas para demonstração....
O exemplo pode ser usado como uma API que pode ser consumido via interface ou via APP configurando os end-points necessários e os tipos de dados específicos.

O exemplo é constituido por dua entidades: Client, Product

Os end-points dos controllers serão respectivamente:

1 - POST /createClient - deve ser passado um objeto com os dados do cliente  
2 - GET /retrieveIndividualClient/{id} - deve ser passado o id do cliente a ser recuperado  
3 - GET /retrieveClient - Recupera uma lista de todos os clientes cadastrados  
4 - DELETE /deleteClient - deve ser passado um objeto com os dados do cliente a ser deletado  
5 - PUT /editClient/{id} - deve ser passado um objeto com os dados do cliente a ser editado e um parametro com o id individual deste cliente.  

*Os mesmos end-points podem também ser usados para manipular a entidade Products apenas mudando os sufixos de Client para Product.*

**Para clonar o projeto:**  
~#: mkdir xy-inc  
~#: cd xy-inc  
~#: git clone https://github.com/tuliovsmartins/xy-inc.git  
~#: cd xy-inc  

**Executando a aplicação:**  
~#: mvn spring-boot:run  

*É necessário ter o gerenciador de dependencias Maven instalado.*
*No linux é possível instalar maven via repositório: apt-get ou Yum*



Após inicializar a aplicação abra o web browser e acesse o endereço:
http://localhost:8080
