<?php

include '../conexao.php';

$conn = (new Conexao())->connect();

$pacienteNome = $_POST['paciente_nome'];
$pacienteDoc = $_POST['paciente_doc'];

$query = "INSERT INTO Paciente (paciente_nome, paciente_doc)
 values ('{$pacienteNome}', '{$pacienteDoc}')";

if(mysqli_query($conn, $query)){
    echo 'inserido com sucesso';
} else {
    echo 'erro na inserção';
}

$conn->mysqli_close();
    
?>