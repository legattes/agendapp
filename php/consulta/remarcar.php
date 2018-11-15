<?php

/**
 * author legates (Lucas)
 * email lucas.arantes55@gmail.com
 */

include '../conexao.php';

$conn = (new Conexao())->connect();

if(!isset($_POST['consulta_id'])
|| !isset($_POST['consulta_data'])
|| ($_POST['consulta_id']) == ''
|| ($_POST['consulta_data']) == ''){
    header('HTTP/1.1 500 FAIL');
    die();
}

$consultaId = $_POST['consulta_id'];
$consultaData = $_POST['consulta_data'];

$query = "UPDATE Consulta SET consulta_data = '$consultaData' WHERE consulta_id = '$consultaId';";


if(mysqli_query($conn, $query)){
    header('HTTP/1.1 200 OK');
} else {
    header('HTTP/1.1 500 FAIL');
}