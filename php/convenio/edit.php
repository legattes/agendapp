<?php

/**
 * author legates (Lucas)
 * email lucas.arantes55@gmail.com
 */

include '../conexao.php';

$conn = (new Conexao())->connect();

if(!isset($_POST['convenio_id'])
|| !isset($_POST['convenio_nome']) 
|| !isset($_POST['convenio_dia_mes_fatura'])
|| ($_POST['convenio_id']) == ''
|| ($_POST['convenio_nome']) == ''
|| ($_POST['convenio_dia_mes_fatura']) == ''){
    header('Response-Code: 420');
    die();
}

$convenioId = $_POST['convenio_id'];
$convenioNome = $_POST['convenio_nome'];
$convenioDiaMesFatura = $_POST['convenio_dia_mes_fatura'];

$query = "UPDATE Convenio SET convenio_nome = '$convenioNome',convenio_dia_mes_fatura = '$convenioDiaMesFatura' WHERE convenio_id = '$convenioId';";

if(mysqli_query($conn, $query)){
    header('Response-Code: 200');
} else {
    header('Response-Code: 420');
}