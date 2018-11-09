<?php

/**
 * author legates (Lucas)
 * email lucas.arantes55@gmail.com
 */

include '../conexao.php';

$conn = (new Conexao())->connect();

if(!isset($_POST['medico_id'])
|| !isset($_POST['paciente_id']) 
|| !isset($_POST['especialidade_id']) 
|| !isset($_POST['convenio_id'])        
|| !isset($_POST['consulta_status'])
|| !isset($_POST['consulta_data'])        
|| ($_POST['medico_id']) == ''
|| ($_POST['paciente_id']) == ''
|| ($_POST['especialidade_id']) == ''
|| ($_POST['convenio_id']) == ''
|| ($_POST['consulta_status']) == ''
|| ($_POST['consulta_data']) == ''){
    header('Response-Code: 420');
    die();
}

$medicoId = $_POST['medico_id'];
$pacienteId = $_POST['paciente_id'];
$especialidadeId = $_POST['especialidade_id'];
$convenioId = $_POST['convenio_id'];
$consultaStatus = $_POST['consulta_status'];
$consultaData = $_POST['consulta_data'];

$query = "INSERT INTO Consulta (medico_id, paciente_id, especialidade_id, convenio_id, consulta_status, consulta_data)
 values ('{$medicoId}','{$pacienteId}','{$especialidadeId}','{$convenioId}','{$consultaStatus}','{$consultaData}')";

if(mysqli_query($conn, $query)){
    header('Response-Code: 200');
} else {
    header('Response-Code: 200');
}