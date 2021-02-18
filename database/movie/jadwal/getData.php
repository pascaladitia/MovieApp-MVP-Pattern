<?php

include_once('koneksi.php');
if (!empty($_GET['jadwal_id'])){
    
    $jadwal_id = $_GET['jadwal_id'];
    $query = "SELECT * FROM jadwal WHERE jadwal_id * '$jadwal_id'";

} else {
    $query = "SELECT * FROM jadwal";
}
$get = mysqli_query($connect, $query);

$data = array();
if (mysqli_num_rows($get) > 0) {
    
    while ($row = mysqli_fetch_assoc($get)) {
        $data[] = $row;
    }
    set_response(true, "Data ditemukan", $data);
} else {
    set_response(false, "Data tjadwal_idak ditemukan", $data);
}

function set_response($isSuccess, $message, $data) {

    $result = array(
        'isSuccess' => $isSuccess,
        'message' => $message,
        'data' => $data
    );
    echo json_encode($result);
}
