<?php

include_once('koneksi.php');

if (!empty($_POST['jadwal_id'])) {

    $jadwal_id = $_POST['jadwal_id'];
    
    $query = "DELETE FROM jadwal WHERE jadwal_id = '$jadwal_id'";

    $delete = mysqli_query($connect, $query);

    if($delete) {
        set_response(true, "Success delete data");
    } else {
        set_response(false, "Failed delete data");
    }
} else {
    set_response(false, "jadwal_id harus diisi");
}

function set_response($isSuccess, $message) {
    $result = array(
        'isSuccess' => $isSuccess,
        'message' => $message
    );

    echo json_encode($result);
}