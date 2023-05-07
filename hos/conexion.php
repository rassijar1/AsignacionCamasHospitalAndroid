<?php
function conectar(){
// datos del hosting

/*$host="localhost";
$user="id17543360_distri";
$pass="qnM|cHyAMJiWvg8[";
$dbname="id17543360_compdistri";*/

// datos locales

$host="localhost"; //el nombre del servidor
$user="root"; // el usuario de mysql 
$pass="";// password
$dbname="hos";// el nombre de la BD
/// relizamos la conexion a la BD
$link=mysqli_connect($host,$user,$pass) or die ("Error al conectar la base de datos".mysqli_error($link));
mysqli_select_db($link,$dbname) or die ("Error al seleccionar la base de datos".mysqli_error($link));
return $link;

}







?>