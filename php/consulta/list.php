<?php

/**
 * author legates (Lucas)
 * email lucas.arantes55@gmail.com
 */

include '../conexao.php';

$query = "SELECT C.consulta_id, M.medico_nome, P.paciente_nome, E.especialidade_nome, Con.convenio_nome, C.consulta_status, C.consulta_data
FROM Consulta C
LEFT JOIN Medico M
	ON C.medico_id = M.medico_id
LEFT JOIN Paciente P
	ON C.paciente_id = P.paciente_id
LEFT JOIN Especialidade E
	ON C.especialidade_id = E.especialidade_id
LEFT JOIN Convenio Con
    ON C.convenio_id = Con.convenio_id WHERE C.consulta_status <> '0'";

$conn = (new Conexao())->connect();

$queryResult = mysqli_query($conn, $query);

$result['consultas'] = [];

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