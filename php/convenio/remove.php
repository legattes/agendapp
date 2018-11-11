<?php

/**
 * author legates (Lucas)
 * email lucas.arantes55@gmail.com
 */

include '../conexao.php';

$conn = (new Conexao())->connect();

if(!isset($_POST['convenio_id']) 
|| ($_POST['convenio_id']) == ''){
    header('Response-Code: 420');
    die();
}

$convenioId = $_POST['convenio_id'];

$query = "DELETE FROM Convenio where convenio_id = '{$convenioId}'";

if(mysqli_query($conn, $query)){
    header('Response-Code: 200');
} else {
    header('Response-Code: 200');
}