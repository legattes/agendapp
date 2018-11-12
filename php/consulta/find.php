<?php

/**
 * author legates (Lucas)
 * email lucas.arantes55@gmail.com
 */

include '../conexao.php';

if(!isset($_POST['paciente_id']) 
|| ($_POST['paciente_id']) == ''){
    header('Response-Code: 420');
    die();
}

$pacienteId = $_POST['paciente_id'];

$query = "SELECT C.consulta_id, M.medico_nome, P.paciente_nome, E.especialidade_nome, Con.convenio_nome, C.consulta_status, C.consulta_data
FROM Consulta C
LEFT JOIN Medico M
	ON C.medico_id = M.medico_id
LEFT JOIN Paciente P
	ON C.paciente_id = P.paciente_id
LEFT JOIN Especialidade E
	ON C.especialidade_id = E.especialidade_id
LEFT JOIN Convenio Con
    ON C.convenio_id = Con.convenio_id
WHERE P.paciente_id = '$pacienteId'";

$conn = (new Conexao())->connect();

$queryResult = mysqli_query($conn, $query);

$result['consulta'] = [];

while($row = mysqli_fetch_assoc($queryResult)){
    $item = [
        'consulta_id' => $row['consulta_id'],
        'medico_id' => $row['medico_id'],
        'paciente_id' => $row['paciente_id'],        
        'especialidade_id' => $row['especialidade_id'],        
        'convenio_id' => $row['convenio_id'],        
        'consulta_status' => $row['consulta_status'],
        'consulta_data' => $row['consulta_data'],
    ];

    array_push($result['consulta'], $item);
}  

echo json_encode($result);