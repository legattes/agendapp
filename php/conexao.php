<?php

/**
 * author legates (Lucas)
 * email lucas.arantes55@gmail.com
 */

class Conexao{
    public function connect(){    
        $server = 'fdb22.awardspace.net';
        $user = '2874449_agenda';
        $pass = '3Uj7zy98@';
        $db = '2874449_agenda';

        return $conn = mysqli_connect($server, $user, $pass, $db);
    }
}

?>