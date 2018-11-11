<?php

/**
 * author legates (Lucas)
 * email lucas.arantes55@gmail.com
 */

include '../conexao.php';

$query = "SELECT * FROM Paciente";

$conn = (new Conexao())->connect();

$queryResult = mysqli_query($conn, $query);

$result['pacientes'] = [];

while($row = mysqli_fetch_assoc($queryResult)){
    $item = [
        'id' => $row['paciente_id'],
        'nome' => $row['paciente_nome'],
        'cpf' => $row['paciente_cpf'],
        'telefone' => $row['paciente_telefone'],
        'email' => $row['paciente_email'],
    ];

    array_push($result['pacientes'], $item);
}  

echo json_encode($result);