<?php

/**
 * author legates (Lucas)
 * email lucas.arantes55@gmail.com
 */

include '../conexao.php';

$conn = (new Conexao())->connect();

$pacienteNome = $_POST['paciente_nome'];
$pacienteDoc = $_POST['paciente_doc'];

$query = "INSERT INTO Paciente (paciente_nome, paciente_doc)
 values ('{$pacienteNome}', '{$pacienteDoc}')";

if(mysqli_query($conn, $query)){
    $conn->mysqli_close();
    return true;
} else {
    $conn->mysqli_close();
    return false;
}
    
?>