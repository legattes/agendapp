<?php

/**
 * author legates (Lucas)
 * email lucas.arantes55@gmail.com
 */

include '../conexao.php';

$conn = (new Conexao())->connect();

if(!isset($_POST['medico_id'])
|| !isset($_POST['especialidade_id'])
|| ($_POST['medico_id']) == ''
|| ($_POST['especialidade_id']) == ''){
    header("HTTP/1.1 500 FAIL");
    die();
}

$medicoId = $_POST['medico_id'];
$especialidadeId = $_POST['especialidade_id'];

$query = "INSERT INTO Medico_Especialidade (medico_id, especialidade_id)
 values ('{$medicoId}','{$especialidadeId}')";

if(mysqli_query($conn, $query)){
    header("HTTP/1.1 200 OK");
} else {
    header("HTTP/1.1 500 FAIL");
}
    
?>