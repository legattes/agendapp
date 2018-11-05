<?php
class Conexao{

    public function connect(){    
        $server = 'sql110.epizy.com';
        $user = 'epiz_22943201';
        $pass = 'M35ljXEk';
        $db = 'epiz_22943201_agenda';

        return $conn = mysqli_connect($server, $user, $pass, $db);
    }
}

?>