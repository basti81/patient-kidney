

const btnVisibleDiagnosisExam = () => {
    if( $('#extends-form-diagnosis').is(':visible') ) { 
        $(".card-extends-form-diagnosis").hide();
    }else{
        $(".card-extends-form-diagnosis").show();
    } 
}

const btnVisibleExendsExam = () => {
    if( $('#extends-form-exam').is(':visible') ) { 
        $(".card-extends-from-exam").hide();
        console.log("Entre a visible") 
    }else{
        $(".card-extends-from-exam").show();
    }
}
