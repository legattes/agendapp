<?php

/**
 * author legates (Lucas)
 * email lucas.arantes55@gmail.com
 */

include '../conexao.php';

$query = "SELECT * FROM Especialidade";

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