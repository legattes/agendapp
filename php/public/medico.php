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
                        <h3 class="page-header">MEDICOS
                            <a href="addmedico.php"><button type="button" class="btn btn-success">Adicionar</button></a>
                        </h3>
                    </div>   
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col">Nome</th>
                                <th scope="col">CPF</th>
                                <th scope="col">CRM</th>
                                <th scope="col">Telefone</th>
                                <th scope="col">Email</th>
                            </tr>
                        </thead>
                        <tbody>
                            <?php
                            $response = json_decode(file_get_contents("http://agendapp.legates.com.br/api/medico"));
                            foreach ($response->medicos as $medico) {
                                echo "<tr>";
                                echo "<td>" . $medico->nome . "</td>";
                                echo "<td>" . $medico->cpf . "</td>";
                                echo "<td>" . $medico->crm . "</td>";
                                echo "<td>" . $medico->telefone . "</td>";
                                echo "<td>" . $medico->email . "</td>";
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