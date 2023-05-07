<?php 

include('./conexion.php');
$link=conectar();
$user=$_GET['user'];
 
$sql="select * from admin where usuario='$user'";
// $res=mysqli_query($link,$sql)or die ("Error en la consulta $sql ".mysqli_error($link));
$res=$link->query($sql);
// verificar si existen registros
if($link->affected_rows>0){
while($row=$res->fetch_assoc()){
$array=$row;


}
echo json_encode($array);

}else{
    echo "no existre el usuario con ese usuario $user";

}
$res->close();
$link->close();





?>