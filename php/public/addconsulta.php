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
                    <form name="addconsulta" method="POST" action="http://agendapp.legates.com.br/api/consulta">
                        <div class="form-group">      
                            <label for="medico_id" class="control-label">Medico ID</label>      
                            <input name="medico_id" class="form-control" type="text">   
                        </div>                        

                        <div class="form-group">      
                            <label for="paciente_id" class="control-label">Paciente ID</label>      
                            <input name="paciente_id" class="form-control" type="text">   
                        </div>                        

                        <div class="form-group">      
                            <label for="especialidade_id" class="control-label">Especialidade ID</label>      
                            <input name="especialidade_id" class="form-control" type="text">   
                        </div>

                        <div class="form-group">      
                            <label for="convenio_id" class="control-label">Convenio ID</label>     
                            <input name="convenio_id" class="form-control" type="text">   
                        </div>

                        <div class="form-group">      
                            <label for="convenio_data" class="control-label">Data</label>     
                            <input name="convenio_data" class="form-control" type="text">   
                        </div>

                        <button type="submit" class="btn btn-primary">Enviar</button>
                        <a href="consulta.php"><button type="button" class="btn btn-danger">Voltar</button></a>

                    </form>
                </div>
            </div>   
        </div>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>