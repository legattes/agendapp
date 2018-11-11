<?php

/**
 * author legates (Lucas)
 * email lucas.arantes55@gmail.com
 */

include '../conexao.php';

$conn = (new Conexao())->connect();

if(!isset($_POST['especialidade_id']) 
|| ($_POST['especialidade_id']) == ''){
    header('Response-Code: 420');
    die();
}

$especialidadeId = $_POST['especialidade_id'];

$query = "DELETE FROM Especialidade where especialidade_id = '{$especialidadeId}'";

if(mysqli_query($conn, $query)){
    header('Response-Code: 200');
} else {
    header('Response-Code: 200');
}