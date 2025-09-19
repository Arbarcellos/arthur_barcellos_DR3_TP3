#API do Projeto
Este documento fornece instruções para configurar, executar e testar a API do projeto.

##1. Configuração e Execução do Projeto
Para começar, você precisará ter o Java e o Maven instalados em sua máquina.


##2. Configurar o Banco de Dados:
A API utiliza um banco de dados relacional. Crie um banco de dados e atualize as configurações de conexão no arquivo src/main/resources/application.properties:

		spring.datasource.url=jdbc:mysql://localhost:3306/[NOME_DO_BANCO]
		spring.datasource.username=[SEU_USUARIO]
		spring.datasource.password=[SUA_SENHA]
		spring.jpa.hibernate.ddl-auto=update
		
Se você não tiver um banco de dados, pode usar o H2 para facilitar:

		spring.h2.console.enabled=true
		spring.datasource.url=jdbc:h2:mem:testdb
		spring.datasource.driverClassName=org.h2.Driver
		spring.datasource.username=sa
		spring.datasource.password=
		spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
		
##3. Compilar e Executar:
Abra o terminal na raiz do projeto e execute o seguinte comando para compilar e iniciar a aplicação:

	mvn spring-boot:run
	A aplicação estará disponível em http://localhost:8080.

##4. Endpoints da API
A API expõe os seguintes endpoints para gerenciamento de recursos. Todos os dados são transferidos em formato JSON.

Recurso: /api/tasks
Representa um recurso genérico no sistema.

####GET /api/tasks
	Descrição: Lista todos os itens.
	Requisição:
			GET /api/tasks
			Resposta (Exemplo):
				[
				    {
				        "id": 1,
				        "description": "Task description 1",
				        "owner": "Alice",
				        "dueDate": "2025-09-21",
				        "duration": 1.0,
				        "status": "On Hold",
				        "pending": false,
				        "comment": "Follow up required",
				        "urgency": "MEDIUM"
				    },
				    {
				        "id": 2,
				        "description": "Task description 2",
				        "owner": "Alice",
				        "dueDate": "2025-10-13",
				        "duration": 1.5,
				        "status": "On Hold",
				        "pending": true,
				        "comment": "Needs review",
				        "urgency": "HIGH"
				    }
			]
####GET /api/items/{id}
	Descrição: Recupera um item específico pelo seu ID.
	Requisição:
			GET /api/tasks/1
			Resposta (Exemplo):
					{
					    "id": 1,
					    "description": "Task description 1",
					    "owner": "Alice",
					    "dueDate": "2025-09-21",
					    "duration": 1.0,
					    "status": "On Hold",
					    "pending": false,
					    "comment": "Follow up required",
					    "urgency": "MEDIUM"
					}
					
####POST /api/items
	Descrição: Cria um novo item.
	Requisição:
			POST /api/tasks
			Content-Type: application/json
			
					{
					    "description": "TASK POST",
					    "owner": "ARTHUR",
					    "dueDate": "2025-09-21",
					    "duration": 1.0,
					    "status": "On Hold",
					    "pending": false,
					    "comment": "Follow up required",
					    "urgency": "MEDIUM"
					}
			Resposta (Exemplo):
					{
						 "id": 16,
					    "description": "TASK POST",
					    "owner": "ARTHUR",
					    "dueDate": "2025-09-21",
					    "duration": 1.0,
					    "status": "On Hold",
					    "pending": false,
					    "comment": "Follow up required",
					    "urgency": "MEDIUM"
					}
####PUT /api/items/{id}
	Descrição: Atualiza um item existente pelo seu ID.
	Requisição:
			PUT /api/task/16
			Content-Type: application/json
			
					{
					    "description": "TASK PUT",
					    "owner": "ARTHUR",
					    "dueDate": "2025-09-21",
					    "duration": 1.0,
					    "status": "On Hold",
					    "pending": false,
					    "comment": "Follow up required",
					    "urgency": "MEDIUM"
					}
			Resposta (Exemplo):
					{
					    "description": "TASK PUT",
					    "owner": "ARTHUR",
					    "dueDate": "2025-09-21",
					    "duration": 1.0,
					    "status": "On Hold",
					    "pending": false,
					    "comment": "Follow up required",
					    "urgency": "MEDIUM"
					}
					
####DELETE /api/items/{id}
	Descrição: Exclui um item pelo seu ID.
	Requisição:
			DELETE /api/tasks/16
			Resposta: Status HTTP 204 No Content.
			
