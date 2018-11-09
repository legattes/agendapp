<?php

/**
 * author legates (Lucas)
 * email lucas.arantes55@gmail.com
 */

include '../conexao.php';

$query = "SELECT * FROM Medico";

$conn = (new Conexao())->connect();

$queryResult = mysqli_query($conn, $query);

$result['medicos'] = [];

while($row = mysqli_fetch_assoc($queryResult)){
    $item = [
        'id' => $row['medico_id'],
        'nome' => $row['medico_nome'],
        'crm' => $row['medico_crm'],
        'cpf' => $row['medico_cpf'],
        'telefone' => $row['medico_telefone'],
        'email' => $row['medico_email'],
    ];

    array_push($result['medicos'], $item);
}  

echo json_encode($result);