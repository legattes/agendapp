<?php

/**
 * author legates (Lucas)
 * email lucas.arantes55@gmail.com
 */

include '../conexao.php';

$conn = (new Conexao())->connect();

if(!isset($_POST['convenio_nome'])
|| !isset($_POST['convenio_dia_mes_fatura']) 
|| ($_POST['convenio_nome']) == ''
|| ($_POST['convenio_dia_mes_fatura']) == ''){
    header('Response-Code: 420');
    die();
}

$convenioNome = $_POST['convenio_nome'];
$convenioDiaFatura = $_POST['convenio_dia_mes_fatura'];

$query = "INSERT INTO Convenio (convenio_nome, convenio_dia_mes_fatura)
 values ('{$convenioNome}','{$convenioDiaFatura}')";

if(mysqli_query($conn, $query)){
    header('Response-Code: 200');
} else {
    header('Response-Code: 200');
}