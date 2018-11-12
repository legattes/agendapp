<?php

/**
 * author legates (Lucas)
 * email lucas.arantes55@gmail.com
 */

include '../conexao.php';

$conn = (new Conexao())->connect();

if(!isset($_POST['consulta_id']) 
|| ($_POST['consulta_id']) == ''){
    header('Response-Code: 420');
    die();
}

$consultaId = $_POST['consulta_id'];

$query = "DELETE FROM Consulta where consulta_id = '{$consulta_id}'";

if(mysqli_query($conn, $query)){
    header('Response-Code: 200');
} else {
    header('Response-Code: 200');
}