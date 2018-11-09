<?php

/**
 * author legates (Lucas)
 * email lucas.arantes55@gmail.com
 */

include '../conexao.php';

$conn = (new Conexao())->connect();

if(!isset($_POST['medico_nome'])
|| !isset($_POST['medico_cpf'])
|| !isset($_POST['medico_crm']) 
|| !isset($_POST['medico_telefone']) 
|| !isset($_POST['medico_email'])
|| ($_POST['medico_nome']) == ''
|| ($_POST['medico_cpf']) == ''
|| ($_POST['medico_crm']) == ''
|| ($_POST['medico_telefone']) == ''
|| ($_POST['medico_email']) == ''){
    header('Response-Code: 420');
    die();
}

$medicoNome = $_POST['medico_nome'];
$medicoCpf = $_POST['medico_cpf'];
$medicoCrm = $_POST['medico_crm'];
$medicoTelefone = $_POST['medico_telefone'];
$medicoEmail = $_POST['medico_email'];

$query = "INSERT INTO Medico (medico_nome, medico_cpf, medico_crm, medico_telefone, medico_email)
 values ('{$medicoNome}','{$medicoCpf}','{$medicoCrm}','{$medicoTelefone}','{$medicoEmail}')";

if(mysqli_query($conn, $query)){
    header('Response-Code: 200');
} else {
    header('Response-Code: 200');
}