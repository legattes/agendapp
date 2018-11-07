<?php

include '../conexao.php';

$query = "SELECT * FROM Paciente";

$conn = (new Conexao())->connect();
$sql = mysqli_query($conn, $query);

$result = [];

while($row = mysqli_fetch_assoc($sql)){
    $item = [];

    $item['name'] = $row['paciente_nome'];
    $item['doc'] = $row['paciente_doc'];

    array_push($result, $item);
}
echo '<pre>';
print_r($result);

$conn->mysqli_close();
    
?>