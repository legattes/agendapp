<?php

$server = 'sql110.epizy.com';
$user = 'epiz_22943201';
$pass = 'M35ljXEk';
$db = 'epiz_22943201_agenda';

$conn = mysqli_connect($server, $user, $pass, $db);

$pacienteNome = $_POST['paciente_nome'];
$pacienteDoc = $_POST['paciente_doc'];

$query = "INSERT INTO Paciente (paciente_nome, paciente_doc) values ('{$pacienteNome}', '{$pacienteDoc}')";

if(mysqli_query($conn, $query)){
    echo 'inserido com sucesso';
} else {
    echo 'erro na inserção';
}

$conn->mysqli_close();

?>