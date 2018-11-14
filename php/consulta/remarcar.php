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
    header('Response-Code: 420');
    die();
}

$consultaId = $_POST['consulta_id'];
$consultaData = $_POST['consulta_data'];



$query = "UPDATE Consulta SET consulta_data = $consultaData WHERE consulta_id = '$consultaId';";


if(mysqli_query($conn, $query)){
    header('Response-Code: 200');
} else {
    header('Response-Code: 420');
}