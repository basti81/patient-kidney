//-- Meeting/time/new --
//Extrae e incorpora doctor al form
$(".btn_getDoctor").on("click", function() {
    console.log("entre al metodo")
    const idWorker =$(this).closest('tr').children()[0].textContent;
    const rut = $(this).closest('tr').children()[1].textContent;
    const name = $(this).closest('tr').children()[2].textContent;
    $("#inputNameWorker").val(name);
    $("#inputIdWorker").val(idWorker);
 });

//-- Meeting/running/new --
//Extrae e incorpora una consulta al form
$(".btn_viewConsulta").on("click", function() {
    const id_consulta = $(this).closest('tr').children()[0].textContent;
    const id_paciente = $(this).closest('tr').children()[1].textContent;
    const id_personal = $(this).closest('tr').children()[2].textContent;
    const nota = $(this).closest('tr').children()[3].textContent;
    const estado = $(this).closest('tr').children()[4].textContent.trim();
    const fecha_atencion = $(this).closest('tr').children()[5].textContent;
    $("#inputIdConsulta").val(id_consulta);
    $("#inputIdPersonal").val(id_personal);
    $("#inputIdPaciente").val(id_paciente);
    $("#inputFechaAtencion").val(fecha_atencion);
    if(estado == "Atendido"){
        //alert("entre atendido");
        $("#inputNota").val(nota);
        //$("#btn_addConsulta").hide();
    }else{
        //alert("entre a no atendido");
        $("#inputNota").val("");
       // $("#btn_addConsulta").show();
    }
});