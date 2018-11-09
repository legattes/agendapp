<?php

/**
 * author legates (Lucas)
 * email lucas.arantes55@gmail.com
 */

include '../conexao.php';

$query = "SELECT * FROM Consulta";

$conn = (new Conexao())->connect();

$queryResult = mysqli_query($conn, $query);

$result['consultas'] = [];

while($row = mysqli_fetch_assoc($queryResult)){
    $item = [
        'consulta_id' => $row['consulta_id'],
        'medico_id' => $row['medico_id'],
        'paciente_id' => $row['paciente_id'],
        'especialidade_id' => $row['especialidade_id'],
        'convenio_id' => $row['convenio_id'],        
        'consulta_status' => $row['consulta_status'],
        'consulta_data' => $row['consulta_data']
    ];

    array_push($result['consultas'], $item);
}  

echo json_encode($result);