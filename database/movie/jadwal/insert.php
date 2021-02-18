<?php

include_once('koneksi.php');

if (!empty($_POST['jadwal_nama']) && !empty($_POST['jadwal_tgl'])
    && !empty($_POST['jadwal_jenis'])) {

    $jadwal_nama = $_POST['jadwal_nama'];
    $jadwal_tgl = $_POST['jadwal_tgl'];
    $jadwal_jenis = $_POST['jadwal_jenis'];

    $query = "INSERT INTO jadwal(jadwal_nama, jadwal_tgl, jadwal_jenis) 
        VALUES ('$jadwal_nama','$jadwal_tgl','$jadwal_jenis')";

    $insert = mysqli_query($connect, $query);

    if($insert) {
        set_response(true, "Success insert data");
    } else {
        set_response(false, "Failed insert data");
    } 
} else {
        set_response(false, "jadwal_nama & jadwal_tgl harus diisi");
    }

function set_response($isSuccess, $message) {
    $result = array(
        'isSuccess' => $isSuccess,
        'message' => $message
    );

    echo json_encode($result);
}
