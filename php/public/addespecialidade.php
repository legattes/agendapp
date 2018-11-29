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
                        <h2 class="page-header">CADASTRAR ESPECIALIDADE</h2>
                    </div>   
                    <form name="addespecialidade" method="POST" action="http://agendapp.legates.com.br/api/especialidade">
                        <div class="form-group">      
                            <label for="especialidade_nome" class="control-label">Nome</label>      
                            <input name="especialidade_nome" class="form-control" placeholder="Digite o nome..." type="text">   
                        </div>         

                        <button type="submit" class="btn btn-primary">Enviar</button>
                        <a href="especialidade.php"><button type="button" class="btn btn-danger">Voltar</button></a>

                    </form>
                </div>
            </div>   
        </div>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>