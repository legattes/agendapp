<?php

/**
 * author legates (Lucas)
 * email lucas.arantes55@gmail.com
 */
class Paciente {
    public function post(){          
        if(!isset($_POST['paciente_nome'])
        || !isset($_POST['paciente_cpf']) 
        || !isset($_POST['paciente_telefone']) 
        || !isset($_POST['paciente_email'])
        || ($_POST['paciente_nome']) == ''
        || ($_POST['paciente_cpf']) == ''
        || ($_POST['paciente_telefone']) == ''
        || ($_POST['paciente_email']) == ''){
            header('HTTP/1.1 500 FAIL');
            header('Location: index.php');
            die();
        }
        
        $pacienteNome = $_POST['paciente_nome'];
        $pacienteCpf = $_POST['paciente_cpf'];
        $pacienteTelefone = $_POST['paciente_telefone'];
        $pacienteEmail = $_POST['paciente_email'];
        
        $conn = (new Connection())->connect();

        $query = "INSERT INTO Paciente (paciente_nome, paciente_cpf, paciente_telefone, paciente_email)
        values ('{$pacienteNome}','{$pacienteCpf}','{$pacienteTelefone}','{$pacienteEmail}')";

        if(mysqli_query($conn, $query)){
            header('HTTP/1.1 200 OK');            
            header('Location: index.php');
        } else {
            header('HTTP/1.1 500 FAIL');
            header('Location: index.php');
        } 
    }

    public function get(){        
        $result['pacientes'] = [];

        $query = "SELECT * FROM Paciente";
        $conn = (new Connection())->connect();
        
        if(!($queryResult = mysqli_query($conn, $query))){
            header('HTTP/1.1 500 FAIL');
            die();
        }

        while($row = mysqli_fetch_assoc($queryResult)){
            $item = [
                'id' => $row['paciente_id'],
                'nome' => $row['paciente_nome'],
                'cpf' => $row['paciente_cpf'],
                'telefone' => $row['paciente_telefone'],
                'email' => $row['paciente_email'],
            ];

            array_push($result['pacientes'], $item);
        }  

        echo json_encode($result);
    }
}    