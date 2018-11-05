<?php

include '../conexao.php';

$sql = "INSERT INTO Paciente (paciente_nome, paciente_doc) values('{}', '{}')";

$conn = (new Conexao())->connect();

if(1 == 1){
    echo json_encode(['nome' => 'Joao']);
} else {
    echo 'falhou';
}
    
?>