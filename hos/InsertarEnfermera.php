<?php

include('./conexion.php');
$link=conectar();// llamo la funcion de conexion    
$idEnfer=$_REQUEST['id_enfermera']; //traigo el codigo capturado del formulario android
$nom=$_REQUEST['nombre']; //traigo el nombre capturado del formulario android
$ape=$_REQUEST['apellido'];
$fechaNac=$_REQUEST['fecha_nac'];
$cc=$_REQUEST['cc'];
$idAdminFk=$_REQUEST['id_admin_fk'];
$idAreaFk=$_REQUEST['id_area_fk'];



//realizar la incersion de los datos en la tabla


$sql="insert into enfermera values($idEnfer,'$nom','$ape','$fechaNac',$cc,$idAdminFk,$idAreaFk)";
$res=mysqli_query($link, $sql) or die ("Error al insertar".mysqli_error($link));
mysqli_close($link);//cerrar conexion BD




?>
