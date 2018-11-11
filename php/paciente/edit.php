<?php

/**
 * author legates (Lucas)
 * email lucas.arantes55@gmail.com
 */

include '../conexao.php';

$conn = (new Conexao())->connect();

if(!isset($_POST['paciente_id'])
|| !isset($_POST['paciente_nome']) 
|| !isset($_POST['paciente_telefone']) 
|| !isset($_POST['paciente_email'])
|| ($_POST['paciente_id']) == ''
|| ($_POST['paciente_nome']) == ''
|| ($_POST['paciente_telefone']) == ''
|| ($_POST['paciente_email']) == ''){
    header('Response-Code: 420');
    die();
}

$pacienteId = $_POST['paciente_id'];
$pacienteNome = $_POST['paciente_nome'];
$pacienteTelefone = $_POST['paciente_telefone'];
$pacienteEmail = $_POST['paciente_email'];



$query = "UPDATE Paciente SET paciente_nome = '$pacienteNome',paciente_telefone = '$pacienteTelefone',paciente_email = '$pacienteEmail' WHERE paciente_id = '$pacienteId';";


if(mysqli_query($conn, $query)){
    header('Response-Code: ' . $pacienteNome);
} else {
    header('Response-Code: 420');
}