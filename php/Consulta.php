<?php

/**
 * author legates (Lucas)
 * email lucas.arantes55@gmail.com
 */

class Consulta {
    public function post($args = []){          
        if(empty($args)){
            $this->add();
        } else {
            $this->edit($args);
        }
    }

    public function get($args = []){        
        $result['consultas'] = [];

        $query = "SELECT C.consulta_id, M.medico_nome, P.paciente_nome, E.especialidade_nome, Con.convenio_nome, C.consulta_status, C.consulta_data
        FROM Consulta C
        LEFT JOIN Medico M
            ON C.medico_id = M.medico_id
        LEFT JOIN Paciente P
            ON C.paciente_id = P.paciente_id
        LEFT JOIN Especialidade E
            ON C.especialidade_id = E.especialidade_id
        LEFT JOIN Convenio Con
            ON C.convenio_id = Con.convenio_id WHERE C.consulta_status <> '0'";
        
        $conn = (new Connection())->connect();
        
        if(!($queryResult = mysqli_query($conn, $query))){
            header('HTTP/1.1 500 FAIL');
            die();
        }

        while($row = mysqli_fetch_assoc($queryResult)){
            $item = [
                'consulta_id' => $row['consulta_id'],
                'medico_nome' => $row['medico_nome'],
                'paciente_nome' => $row['paciente_nome'],        
                'especialidade_nome' => $row['especialidade_nome'],        
                'convenio_nome' => $row['convenio_nome'],        
                'consulta_status' => $row['consulta_status'],
                'consulta_data' => $row['consulta_data'],
            ];
        
            array_push($result['consultas'], $item);
        }   

        echo json_encode($result);
    }

    protected function add(){
        if(!isset($_POST['medico_id']) 
        || !isset($_POST['paciente_id']) 
        || !isset($_POST['especialidade_id'])
        || !isset($_POST['convenio_id'])
        || !isset($_POST['consulta_data'])
        || ($_POST['medico_id']) == ''
        || ($_POST['paciente_id']) == ''
        || ($_POST['especialidade_id']) == ''
        || ($_POST['convenio_id']) == ''
        || ($_POST['consulta_data']) == ''){
            header('HTTP/1.1 500 FAIL');
            header('Location: index.php');
            die();
        }
        
        $medicoId = $_POST['medico_id'];
        $pacienteId = $_POST['paciente_id'];
        $especialidadeId = $_POST['especialidade_id'];
        $convenioId= $_POST['convenio_id'];
        $consultaData = $_POST['consulta_data'];
                
        $conn = (new Connection())->connect();

        $query = "INSERT INTO Consulta (medico_id, paciente_id, especialidade_id, convenio_id, consulta_data)
        values ('{$medicoId}','{$pacienteId}','{$especialidadeId}','{$convenioId}','{$consultaData}')";

        if(mysqli_query($conn, $query)){
            header('HTTP/1.1 200 OK');            
            header('Location: index.php');
        } else {
            header('HTTP/1.1 500 FAIL');
            header('Location: index.php');
        } 
    }

    protected function edit($args){
        if(!isset($_POST['medico_id']) 
        || !isset($_POST['paciente_id']) 
        || !isset($_POST['especialidade_id'])
        || !isset($_POST['convenio_id'])
        || !isset($_POST['consulta_data'])
        || ($_POST['medico_id']) == ''
        || ($_POST['paciente_id']) == ''
        || ($_POST['especialidade_id']) == ''
        || ($_POST['convenio_id']) == ''
        || ($_POST['consulta_data']) == ''){
            header('HTTP/1.1 500 FAIL');
            header('Location: index.php');
            die();
        }
        
        $medicoId = $_POST['medico_id'];
        $pacienteId = $_POST['paciente_id'];
        $especialidadeId = $_POST['especialidade_id'];
        $convenioId= $_POST['convenio_id'];
        $consultaData = $_POST['consulta_data'];
                
        $conn = (new Connection())->connect();

        $query = "INSERT INTO Consulta (medico_id, paciente_id, especialidade_id, convenio_id, consulta_data)
        values ('{$medicoId}','{$pacienteId}','{$especialidadeId}','{$convenioId}','{$consultaData}')";

        if(mysqli_query($conn, $query)){
            header('HTTP/1.1 200 OK');            
            header('Location: index.php');
        } else {
            header('HTTP/1.1 500 FAIL');
            header('Location: index.php');
        } 
    }
}    