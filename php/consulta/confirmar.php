<?php

/**
 * author legates (Lucas)
 * email lucas.arantes55@gmail.com
 */

include '../conexao.php';

$conn = (new Conexao())->connect();

if(!isset($_POST['consulta_id'])
|| ($_POST['consulta_id']) == ''){
    header('HTTP/1.1 500 FAIL');
    die();
}

$consultaId = $_POST['consulta_id'];

$query = "UPDATE Consulta SET consulta_status = '2' WHERE consulta_id = '$consultaId';";


if(mysqli_query($conn, $query)){
    header('HTTP/1.1 200 OK');
} else {
    header('HTTP/1.1 500 FAIL');
}