const showPass = () =>{
    console.log("entre al metodo");
    const check = document.getElementById("inputPassword");
    if(check.type == "password") check.type = "text";
    else check.type = "password";
}

