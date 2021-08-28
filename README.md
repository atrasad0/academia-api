# academia-api
<h3> Tecnologias </h3>
Spring Boot, Java 11,
MySQL e Mybatis

Este é um sistema (API REST) para cadastro de alunos em uma academia.

<h1>Patches</h1><br>
  A estrutura do banco de dados está na pasta raiz do sistema em <b>Patches</b>. <br>
  
  - Aplicar o <b>patch patch_2021_06_27_estrutura_tabelas.sql</b> dentro deste arquivo estão as tabelas que o sistema irá utilizar para as operações de CRUD. <br>
  
  - Aplicar o <b>patch_2021_06_27_default_values.sql</b> dentro deste arquivo estão valores que serão utilizados para que seja possivel persistir uma entidade no banco. <br>
  
  - Dentro de Patches contêm um arquivo chamado <b>json_exemplo_teste.json</b> este é um exemplo do modelo para realizar um POST na API.
  
  
<h1>Configurando</h1><br>
  Para configurar o sistema de modo que seja possivel "rodar" localmente altere o arquivo <b>src.main.resources.application.properties</b>: <br>
  
    spring.datasource.url=jdbc:mysql://<b>URL_BANCO_DADOS_MYSQL:PORTA</b>/academia?useTimezone=true&serverTimezone=UTC <br>
    spring.datasource.username=<b>USUARIO_MYSQL</b> <br>
    spring.datasource.password=<b>SENHA_MYSQL</b> <br>
    
  O sistema irá subir na porta 8080. <br>
  
 <h1>EndPoints</h1><br>
 
 <h3>APIController</h3><br>
     - <b>"URL_API"/api/conexao</b> Esse endpoint apenas retorna uma mensagem de Ok se a API estiver ONLINE.<br>
  
  <h3>AcademiaController</h3><br>
    <b>"URL_API"/api/academia/buscaPlanos</b> Esse endpoint retorna uma lista no formato JSON com todos os planos disponiveis em uma academia, este metodo não precisa de parametros.<br>
  
   <b>"URL_API"/api/academia/buscaAulas</b> Esse endpoint retorna uma lista no formato JSON com todas as aulas disponiveis em uma academia, este metodo não precisa de parametros.<br>
  
 <h3>AlunoController</h3><br>
   <b>"URL_API"/api/aluno/salva</b> Esse endpoint salva um aluno no banco de dados, se espera um modelo JSON igual o arquivo arquivo json_exemplo_teste.json em pathes como parametro.
      Esse endpoint retorna o ID do aluno que foi salvo no banco. Atributos obrigatórios: nome, cpf, dataPagamento, aulas, plano.<br><br>
  
   <b>"URL_API"/api/api/aluno/academia/{id} </b> Esse endpoint retorna um aluno com todas as suas informações no formato JSON, se espera como parametro o ID do aluno..<br>
  
   <b>"URL_API"/api/api/aluno/academia/edita" </b> Esse endpoint edita um aluno, se espera o aluno com suas novas informações passado como parametro no formato JSON.
   Uma dica é realizar uma busca do aluno que deseja editar, copiar o JSON retornado e realizar as altreraçoes. 
   É obritatorio que o ID do aluno seja informado no JSON enviado na requisição.
   Todas as novas informações desta edição aluno serão tratadas como uma nova inserção. Exemplo: 
     No caso do atributo telefones conter apenas 1 registro, o sistema irá apagar todos os registros "antigos" da tabela "telefones" relacionado a este aluno, se for null, irá apagar todos os telefones deste aluno.<br>
     O atributo aulas funcionará da mesma forma, com a diferença de nunca poder ser null.<br>
     
   <b>"URL_API"/api/api/aluno/academia/alunos"</b> Esse endpoint retorna uma lista no formato JSON com todos os alunos salvos no sistema, este metodo não precisa de parametros<br>
   
   <b>"URL_API"/api/api/aluno/academia/remove/{id}"</b> Esse endpoint remove um aluno salvo no sistema, se espera como parametro o ID do aluno.<br>
   
   
    
