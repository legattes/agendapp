<?php

/**
 * author legates (Lucas)
 * email lucas.arantes55@gmail.com
 */

class Especialidade {
    public function post(){          
        if(!isset($_POST['especialidade_nome']) 
        || ($_POST['especialidade_nome']) == ''){
            header('HTTP/1.1 500 FAIL');
            header('Location: index.php');
            die();
        }
        
        $especialidadeNome = $_POST['especialidade_nome'];
                
        $conn = (new Connection())->connect();

        $query = "INSERT INTO Especialidade (especialidade_nome)
        values ('{$especialidadeNome}')";
        
        if(mysqli_query($conn, $query)){
            header('HTTP/1.1 200 OK');            
            header('Location: index.php');
        } else {
            header('HTTP/1.1 500 FAIL');
            header('Location: index.php');
        } 
    }

    public function get(){        
        $result['especialidades'] = [];

        $query = "SELECT * FROM Especialidade";
        $conn = (new Connection())->connect();
        
        if(!($queryResult = mysqli_query($conn, $query))){
            header('HTTP/1.1 500 FAIL');
            die();
        }

        while($row = mysqli_fetch_assoc($queryResult)){
            $item = [
                'id' => $row['especialidade_id'],
                'nome' => $row['especialidade_nome'],        
            ];
        
            array_push($result['especialidades'], $item);
        }  

        echo json_encode($result);
    }
}    