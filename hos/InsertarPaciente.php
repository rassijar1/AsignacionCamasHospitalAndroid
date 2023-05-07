<?php

include('./conexion.php');
$link=conectar();// llamo la funcion de conexion    
$idpac=$_REQUEST['id_paciente']; //traigo el codigo capturado del formulario android
$nom=$_REQUEST['nombre']; //traigo el nombre capturado del formulario android
$ape=$_REQUEST['apellido'];
$dir=$_REQUEST['direccion'];
$usu=$_REQUEST['usuario'];
$cla=$_REQUEST['clave'];
$fec=$_REQUEST['fecha_nac'];
$tel=$_REQUEST['telefono'];
$idadminfk=$_REQUEST['id_admin_fk'];
$id_diag_fk=$_REQUEST['id_diagnostico_fk'];
$id_ser_fk=$_REQUEST['id_servicio_adicional_fk'];



//realizar la incersion de los datos en la tabla


$sql="insert into paciente values($idpac,'$nom','$ape','$dir','$usu','$cla','$fec',$tel,$idadminfk,$id_diag_fk,$id_ser_fk)";
$res=mysqli_query($link, $sql) or die ("Error al insertar".mysqli_error($link));
mysqli_close($link);//cerrar conexion BD




?>
