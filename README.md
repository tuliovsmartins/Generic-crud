# xy-inc

Simple crud to manipulate products and clients

Exemplo de CRUD simples com 2 entidades e objetos usando boas práticas de acesso, Controller, Services, Converters, Configs, etc...

Exemplo criado apenas para demonstração....
O exemplo pode ser usado como uma API que pode ser consumido via interface ou via APP configurando os end-points necessários e os tipos de dados específicos.

O exemplo é constituido por dua entidades: Client, Product

Os end-points dos controllers serão respectivamente:

POST /createClient - deve ser passado um objeto com os dados do cliente
GET /retrieveIndividualClient/{id} - deve ser passado o id do cliente a ser recuperado
GET /retrieveClient - Recupera uma lista de todos os clientes cadastrados
DELETE /deleteClient - deve ser passado um objeto com os dados do cliente a ser deletado
PUT /editClient/{id} - deve ser passado um objeto com os dados do cliente a ser editado e um parametro com o id individual deste cliente.

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

A aplicação conta com algumas telas criadas para onde foram usados o bootstrap framework e o Jquery.
