<?php

include('./conexion.php');
$link=conectar();// llamo la funcion de conexion    
$idArea=$_REQUEST['id_area']; //traigo el codigo capturado del formulario android
$nomArea=$_REQUEST['nombre']; //traigo el nombre capturado del formulario android
$NumHab=$_REQUEST['num_habitaciones'];




//realizar la incersion de los datos en la tabla


$sql="insert into area values($idArea,'$nomArea',$NumHab)";
$res=mysqli_query($link, $sql) or die ("Error al insertar".mysqli_error($link));
mysqli_close($link);//cerrar conexion BD




?>
