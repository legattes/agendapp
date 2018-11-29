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
                        <h3 class="page-header">CONSULTAS
                            <a href="addconsulta.php"><button type="button" class="btn btn-success">Adicionar</button></a>
                        </h3>
                    </div>   
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col">Paciente</th>
                                <th scope="col">Médico</th>
                                <th scope="col">Especialidade</th>
                                <th scope="col">Convênio</th>
                                <th scope="col">Dia</th>                                
                                <th scope="col">Hora</th>
                            </tr>
                        </thead>
                        <tbody>
                            <?php
                            $response = json_decode(file_get_contents("http://agendapp.legates.com.br/api/consulta"));
                            foreach ($response->consultas as $consulta) {
                                echo "<tr>";
                                echo "<td>" . $consulta->medico_nome . "</td>";
                                echo "<td>" . $consulta->paciente_nome . "</td>";
                                echo "<td>" . $consulta->especialidade_nome . "</td>";
                                echo "<td>" . $consulta->convenio_nome . "</td>";
                                echo "<td>" . date_format(date_create($consulta->consulta_data), "d/m/Y") . "</td>";
                                echo "<td>" . date_format(date_create($consulta->consulta_data), "H:i") . "</td>";
                                echo "</tr>";
                            }
                            ?>
                        </tbody>
                    </table>
                    <a href="index.php"><button type="button" class="btn btn-danger">Voltar</button></a>
                </div>
            </div>   
        </div>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>