<?php

/**
 * author legates (Lucas)
 * email lucas.arantes55@gmail.com
 */

include '../conexao.php';

$conn = (new Conexao())->connect();

if(!isset($_POST['medico_id'])
|| !isset($_POST['medico_nome'])
|| !isset($_POST['medico_telefone']) 
|| !isset($_POST['medico_email'])
|| ($_POST['medico_id']) == ''
|| ($_POST['medico_nome']) == ''
|| ($_POST['medico_telefone']) == ''
|| ($_POST['medico_email']) == ''){
    header('Response-Code: 420');
    die();
}

$medicoId = $_POST['medico_id'];
$medicoNome = $_POST['medico_nome'];
$medicoTelefone = $_POST['medico_telefone'];
$medicoEmail = $_POST['medico_email'];



$query = "UPDATE Medico SET medico_nome = '$medicoNome',medico_telefone = '$medicoTelefone',medico_email = '$medicoEmail' WHERE medico_id = '$medicoId';";


if(mysqli_query($conn, $query)){
    header('Response-Code: 200');
} else {
    header('Response-Code: 420');
}