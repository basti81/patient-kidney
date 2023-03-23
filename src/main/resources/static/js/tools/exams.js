$('#btnCardDiagnosisExam').click(function() { 
    if( $('#state-card-diagnosis').is(':visible') ) { 
        $(".card-diagnosis").hide();
        console.log("Entre a visible") 
    }else{
        $(".card-diagnosis").show();
        console.log("Entre a invisible")
    } 
});
