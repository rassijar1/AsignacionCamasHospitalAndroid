<?php 

include('./conexion.php');
$link=conectar();
$id=$_GET['id'];
 
$sql="select * from area where id_area=$id";
// $res=mysqli_query($link,$sql)or die ("Error en la consulta $sql ".mysqli_error($link));
$res=$link->query($sql);
// verificar si existen registros
if($link->affected_rows>0){
while($row=$res->fetch_assoc()){
$array=$row;


}
echo json_encode($array);

}else{
    echo "no existre el area con ese id $id";

}
$res->close();
$link->close();





?>