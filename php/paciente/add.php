<?php

/**
 * author legates (Lucas)
 * email lucas.arantes55@gmail.com
 */

include '../conexao.php';

$conn = (new Conexao())->connect();

if(!isset($_POST['paciente_nome'])
|| !isset($_POST['paciente_cpf']) 
|| !isset($_POST['paciente_telefone']) 
|| !isset($_POST['paciente_email'])
|| ($_POST['paciente_nome']) == ''
|| ($_POST['paciente_cpf']) == ''
|| ($_POST['paciente_telefone']) == ''
|| ($_POST['paciente_email']) == ''){
    header('Response-Code: 420');
    die();
}

$pacienteNome = $_POST['paciente_nome'];
$pacienteCpf = $_POST['paciente_cpf'];
$pacienteTelefone = $_POST['paciente_telefone'];
$pacienteEmail = $_POST['paciente_email'];

$query = "INSERT INTO Paciente (paciente_nome, paciente_cpf, paciente_telefone, paciente_email)
 values ('{$pacienteNome}','{$pacienteCpf}','{$pacienteTelefone}','{$pacienteEmail}')";

if(mysqli_query($conn, $query)){
    header('Response-Code: 200');
} else {
    header('Response-Code: 200');
}
    
?>