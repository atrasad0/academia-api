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
  Para executar o projeto adicione as seguintes variaveis de ambiente no seu sistema: <br>
  
    DB_USER_NAME = 'nome do usuario do banco de dados'
    DB_PASSWORD  = 'senha do banco de dados'<br>
    
  O sistema irá subir na porta 8080. <br>
  
 <h1>EndPoints</h1><br>
 
 <h3>APIController</h3><br>
  GET <b>"URL_API"/api/teste-conexao</b> Esse endpoint apenas retorna uma mensagem de Ok se a API estiver ONLINE.<br>
  
  <h3>AcademiaController</h3><br>
  GET <b>"URL_API"/academia/busca-planos</b> Esse endpoint retorna uma lista no formato JSON com todos os planos disponiveis em uma academia, este metodo não precisa de parametros.<br>

  GET <b>"URL_API"/academia/busca-aulas</b> Esse endpoint retorna uma lista no formato JSON com todas as aulas disponiveis em uma academia, este metodo não precisa de parametros.<br>
  
 <h3>AlunoController</h3><br>
   POST <b>"URL_API"/alunos</b> Esse endpoint salva um aluno no banco de dados, se espera um modelo JSON igual o arquivo arquivo json_exemplo_teste.json em pathes como parametro.
      Esse endpoint retorna o aluno salvo salvo no banco de dados no formato json no corpo da requisição. Retorna também a "location" deste recurso no header da requisição. Atributos obrigatórios: nome, cpf, dataPagamento, aulas, plano.<br><br>
  
   GET <b>"URL_API"/alunos/{id} </b> Esse endpoint retorna um aluno com todas as suas informações no formato JSON, se espera como parametro o ID do aluno.<br>
  
   PUT <b>"URL_API"/alunos" </b> Esse endpoint edita um aluno, se espera o aluno com suas novas informações passado como parametro no formato JSON.
   Uma dica é realizar uma busca do aluno que deseja editar, copiar o JSON retornado e realizar as altreraçoes. 
   É obritatorio que o ID do aluno seja informado no JSON enviado na requisição.
   Todas as novas informações desta edição aluno serão tratadas como uma nova inserção. Exemplo: 
     No caso do atributo telefones conter apenas 1 registro, o sistema irá apagar todos os registros "antigos" da tabela "telefones" relacionado a este aluno, se for null, irá apagar todos os telefones deste aluno.<br>
     O atributo aulas funcionará da mesma forma, com a diferença de nunca poder ser null.<br>
     
   GET <b>"URL_API"/alunos"</b> Esse endpoint retorna uma lista paginada no formato JSON com todos os alunos salvos no sistema.<br>
    Se não informado os parametros de paginação, por padrão este metodo retorna a pagina: 1 com o tamanho de 25 itens.
    Para realizar uma busca paginada espera-se uma requisição como no exemplo a seguir: GET <b>"URL_API"/alunos?page=1&size=10"</b>
   
   DELETE <b>"URL_API"/alunos/{id}"</b> Esse endpoint remove um aluno salvo no sistema, se espera como parametro o ID do aluno.<br>
   
   
    
