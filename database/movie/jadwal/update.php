<?php

include_once('koneksi.php');

if (!empty($_POST['jadwal_id']) && !empty($_POST['jadwal_nama']) && !empty($_POST['jadwal_tgl'])
    && !empty($_POST['jadwal_jenis'])) {

    $jadwal_id = $_POST['jadwal_id'];
    $jadwal_nama = $_POST['jadwal_nama'];
    $jadwal_tgl = $_POST['jadwal_tgl'];
    $jadwal_jenis = $_POST['jadwal_jenis'];

    $query = "UPDATE jadwal set jadwal_nama = '$jadwal_nama', jadwal_tgl = '$jadwal_tgl',
             jadwal_jenis = '$jadwal_jenis' WHERE jadwal_id = '$jadwal_id'";

    $update = mysqli_query($connect, $query);

    if($update) {
        set_response(true, "Success update data");
    } else {
        set_response(false, "False update data");
    }
} else {
    set_response(false, "jadwal_id, jadwal_nama & jadwal_tgl harus diisi");
}

function set_response($isSuccess, $message) {
    $result = array(
        'isSuccess' => $isSuccess,
        'message' => $message
    );
    
    echo json_encode($result);
}