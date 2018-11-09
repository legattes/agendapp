<?php

/**
 * author legates (Lucas)
 * email lucas.arantes55@gmail.com
 */

include '../conexao.php';

$conn = (new Conexao())->connect();

if(!isset($_POST['especialidade_nome'])
|| ($_POST['especialidade_nome']) == ''){
    header('Response-Code: 420');
    die();
}

$especialidadeNome = $_POST['especialidade_nome'];

$query = "INSERT INTO Especialidade (especialidade_nome)
 values ('{$especialidadeNome}')";

if(mysqli_query($conn, $query)){
    header('Response-Code: 200');
} else {
    header('Response-Code: 200');
}