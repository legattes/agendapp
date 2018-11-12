<?php

/**
 * author legates (Lucas)
 * email lucas.arantes55@gmail.com
 */

include '../conexao.php';

if(!isset($_POST['medico_id'])
|| ($_POST['medico_id']) == ''){
    header('Response-Code: 420');
    die();
}

$medicoId = $_POST['medico_id'];

$query = "SELECT E.* FROM Especialidade E 
LEFT JOIN Medico_Especialidade ME
ON E.especialidade_id = ME.especialidade_id
WHERE ME.medico_id = '$medicoId'";

$conn = (new Conexao())->connect();

$queryResult = mysqli_query($conn, $query);

$result['especialidades'] = [];

while($row = mysqli_fetch_assoc($queryResult)){
    $item = [
        'id' => $row['especialidade_id'],
        'nome' => $row['especialidade_nome'],        
    ];

    array_push($result['especialidades'], $item);
}  

echo json_encode($result);