<?php

/**
 * author legates (Lucas)
 * email lucas.arantes55@gmail.com
 */

include '../conexao.php';

$query = "SELECT * FROM Paciente";

$conn = (new Conexao())->connect();

$queryResult = mysqli_query($conn, $query);

$result['pacientes'] = [];

while($row = mysqli_fetch_assoc($queryResult)){
    $item = [
        'name' => $row['paciente_nome'],
        'doc' => $row['paciente_doc']
    ];

    array_push($result['pacientes'], $item);
}  

/*$result = [
    'pacientes' => [[
        'nome' => 'Lucas',
        'doc' => '41465465'
    ],
    [
        'nome' => 'Lucas',
        'doc' => '41465465'
    ],
    ]
];*/

echo json_encode($result);
?>