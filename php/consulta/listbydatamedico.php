<?php

/**
 * author legates (Lucas)
 * email lucas.arantes55@gmail.com
 */

include '../conexao.php';

$conn = (new Conexao())->connect();

if(!isset($_POST['consulta_data'])
|| !isset($_POST['medico_id'])
|| ($_POST['consulta_data']) == ''
|| ($_POST['medico_id']) == ''){
    header('HTTP/1.1 500 FAIL');
    die();
}

$consultaData = $_POST['consulta_data'];
$medicoId = $_POST['medico_id'];

$query = "SELECT * FROM Consulta WHERE medico_id = '$medicoId' and consulta_data = '$consultaData'";

$result['consultas'] = [];

$queryResult = mysqli_query($conn, $query);

while($row = mysqli_fetch_assoc($queryResult)){
    $item = [
        'consulta_id' => $row['consulta_id'],
        'medico_nome' => $row['medico_nome'],
        'paciente_nome' => $row['paciente_nome'],        
        'especialidade_nome' => $row['especialidade_nome'],        
        'convenio_nome' => $row['convenio_nome'],        
        'consulta_status' => $row['consulta_status'],
        'consulta_data' => $row['consulta_data'],
    ];

    array_push($result['consultas'], $item);
}  

echo json_encode($result);