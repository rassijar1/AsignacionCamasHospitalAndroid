<?php 

include('./conexion.php');
$link=conectar();
$id=$_GET['id'];
 
$sql="select * from paciente where id_paciente=$id";
// $res=mysqli_query($link,$sql)or die ("Error en la consulta $sql ".mysqli_error($link));
$res=$link->query($sql);
// verificar si existen registros
if($link->affected_rows>0){
while($row=$res->fetch_assoc()){
$array=$row;


}
echo json_encode($array);

}else{
    echo "no existre el curso con ese id $id";

}
$res->close();
$link->close();





?>