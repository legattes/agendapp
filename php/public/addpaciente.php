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
                        <h2 class="page-header">CADASTRAR PACIENTE</h2>
                    </div>   
                    <form name="addpaciente" method="POST" action="http://agendapp.legates.com.br/api/paciente">
                        <div class="form-group">      
                            <label for="paciente_nome" class="control-label">Nome</label>      
                            <input name="paciente_nome" class="form-control" placeholder="Digite o Nome..." type="text">   
                        </div>                        

                        <div class="form-group">      
                            <label for="paciente_cpf" class="control-label">CPF</label>      
                            <input name="paciente_cpf" class="form-control" placeholder="Digite o CPF..." type="text">   
                        </div>                        

                        <div class="form-group">      
                            <label for="paciente_telefone" class="control-label">Telefone</label>      
                            <input name="paciente_telefone" class="form-control" placeholder="Digite o telefone..." type="text">   
                        </div>

                        <div class="form-group">      
                            <label for="paciente_email" class="control-label">Email</label>     
                            <input name="paciente_email" class="form-control" placeholder="Digite o E-mail" type="email">   
                        </div>

                        <button type="submit" class="btn btn-primary">Enviar</button>
                        <a href="paciente.php"><button type="button" class="btn btn-danger">Voltar</button></a>

                    </form>
                </div>
            </div>   
        </div>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>