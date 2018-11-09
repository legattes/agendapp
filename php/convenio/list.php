<?php

/**
 * author legates (Lucas)
 * email lucas.arantes55@gmail.com
 */

include '../conexao.php';

$query = "SELECT * FROM Convenio";

$conn = (new Conexao())->connect();

$queryResult = mysqli_query($conn, $query);

$result['convenios'] = [];

while($row = mysqli_fetch_assoc($queryResult)){
    $item = [
        'id' => $row['convenio_id'],
        'nome' => $row['convenio_nome'],
        'dia_mes_fatura' => $row['convenio_dia_mes_fatura'],
    ];

    array_push($result['convenios'], $item);
}  

echo json_encode($result);