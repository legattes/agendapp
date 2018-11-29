<?php

/**
 * author legates (Lucas)
 * email lucas.arantes55@gmail.com
 */

class Medico {
    public function post(){          
        if(!isset($_POST['medico_nome'])
        || !isset($_POST['medico_cpf']) 
        || !isset($_POST['medico_crm'])
        || !isset($_POST['medico_telefone']) 
        || !isset($_POST['medico_email'])
        || ($_POST['medico_nome']) == ''
        || ($_POST['medico_cpf']) == ''
        || ($_POST['medico_crm']) == ''
        || ($_POST['medico_telefone']) == ''
        || ($_POST['medico_email']) == ''){
            header('HTTP/1.1 500 FAIL');
            header('Location: index.php');
            die();
        }
        
        $medicoNome = $_POST['medico_nome'];
        $medicoCpf = $_POST['medico_cpf'];
        $medicoCrm = $_POST['medico_crm'];
        $medicoTelefone = $_POST['medico_telefone'];
        $medicoEmail = $_POST['medico_email'];
                
        $conn = (new Connection())->connect();

        $query = "INSERT INTO Medico (medico_nome, medico_cpf, medico_crm, medico_telefone, medico_email)
        values ('{$medicoNome}','{$medicoCpf}','{$medicoCrm}','{$medicoTelefone}','{$medicoEmail}')";

        if(mysqli_query($conn, $query)){
            header('HTTP/1.1 200 OK');            
            header('Location: index.php');
        } else {
            header('HTTP/1.1 500 FAIL');
            header('Location: index.php');
        } 
    }

    public function get(){        
        $result['medicos'] = [];

        $query = "SELECT * FROM Medico";
        $conn = (new Connection())->connect();
        
        if(!($queryResult = mysqli_query($conn, $query))){
            header('HTTP/1.1 500 FAIL');
            die();
        }

        while($row = mysqli_fetch_assoc($queryResult)){
            $item = [
                'id' => $row['medico_id'],
                'nome' => $row['medico_nome'],        
                'crm' => $row['medico_crm'],
                'cpf' => $row['medico_cpf'],
                'telefone' => $row['medico_telefone'],
                'email' => $row['medico_email'],
            ];
        
            array_push($result['medicos'], $item);
        }   

        echo json_encode($result);
    }
}    