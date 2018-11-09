<?php

/**
 * author legates (Lucas)
 * email lucas.arantes55@gmail.com
 */

include '../conexao.php';

$conn = (new Conexao())->connect();

if(!isset($_POST['paciente_id']) 
|| ($_POST['paciente_id']) == ''){
    header('Response-Code: 420');
    die();
}

$pacienteId = $_POST['paciente_id'];

$query = "DELETE FROM Paciente where paciente_id = '{$pacienteId}'";

if(mysqli_query($conn, $query)){
    header('Response-Code: 200');
} else {
    header('Response-Code: 200');
}