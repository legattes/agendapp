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
                        <h2 class="page-header">CADASTRAR CONVÊNIO </h2>
                    </div>   
                    <form name="addconvenio" method="POST" action="http://agendapp.legates.com.br/api/convenio">
                        <div class="form-group">      
                            <label for="convenio_nome" class="control-label">Nome</label>      
                            <input name="convenio_nome" class="form-control" placeholder="Digite o nome..." type="text">   
                        </div>                        

                        <div class="form-group">      
                            <label for="convenio_dia_mes_fatura" class="control-label">CPF</label>      
                            <input name="convenio_dia_mes_fatura" class="form-control" placeholder="Digite o dia de faturar..." type="text">   
                        </div>   

                        <button type="submit" class="btn btn-primary">Enviar</button>
                        <a href="convenio.php"><button type="button" class="btn btn-danger">Voltar</button></a>

                    </form>
                </div>
            </div>   
        </div>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>