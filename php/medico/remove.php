<?php

/**
 * author legates (Lucas)
 * email lucas.arantes55@gmail.com
 */

include '../conexao.php';

$conn = (new Conexao())->connect();

if(!isset($_POST['medico_id']) 
|| ($_POST['medico_id']) == ''){
    header('Response-Code: 420');
    die();
}

$medicoId = $_POST['medico_id'];

$query = "DELETE FROM Medico where medico_id = '{$medicoId}'";

if(mysqli_query($conn, $query)){
    header('Response-Code: 200');
} else {
    header('Response-Code: 200');
}