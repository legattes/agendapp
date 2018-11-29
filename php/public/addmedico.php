<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>AgendApp</title>

        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <header class="row">
                <h2>AgendApp</h2>
            </header>
            <div class="row">
                <div role="main">
                    <div id="main" class="container-fluid">
                        <h2 class="page-header">CADASTRAR MEDICO</h2>
                    </div>   
                    <form name="addmedico" method="POST" action="http://agendapp.legates.com.br/api/medico">
                        <div class="form-group">      
                            <label for="medico_nome" class="control-label">Nome</label>      
                            <input name="medico_nome" class="form-control" placeholder="Digite o Nome..." type="text">   
                        </div>                        

                        <div class="form-group">      
                            <label for="medico_cpf" class="control-label">CPF</label>      
                            <input name="medico_cpf" class="form-control" placeholder="Digite o CPF..." type="text">   
                        </div>   
                        
                        <div class="form-group">      
                            <label for="medico_crm" class="control-label">CRM</label>      
                            <input name="medico_crm" class="form-control" placeholder="Digite o CRM..." type="text">   
                        </div> 

                        <div class="form-group">      
                            <label for="medico_telefone" class="control-label">Telefone</label>      
                            <input name="medico_telefone" class="form-control" placeholder="Digite o telefone..." type="text">   
                        </div>

                        <div class="form-group">      
                            <label for="medico_email" class="control-label">Email</label>     
                            <input name="medico_email" class="form-control" placeholder="Digite o E-mail" type="email">   
                        </div>

                        <button type="submit" class="btn btn-primary">Enviar</button>
                        <a href="medico.php"><button type="button" class="btn btn-danger">Voltar</button></a>

                    </form>
                </div>
            </div>   
        </div>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>