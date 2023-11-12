
/* Verifica se os campos se senhas são iguais */
function validationForm() {
    var senha = document.getElementById('password').value;
    var confirmarSenha = document.getElementById('confirmPassword').value;
    var errorMessage = document.getElementById('errorMessage');
    var form = document.getElementById('formPatient');

    if (senha !== confirmarSenha) {
        errorMessage.textContent = 'As senhas não coincidem.';
    } else {
        errorMessage.textContent = '';
        if (form.checkValidity()) {
            form.submit();
        } else {
            form.reportValidity();
        }
    }
}

function updateForm() {
    var form = document.getElementById('updateFormPatient');

    if (form.checkValidity()) {
        form.submit();
    } else {
        form.reportValidity();
    }
}

function validationFormDoctor() {
    var senha = document.getElementById('password').value;
    var confirmarSenha = document.getElementById('confirmPassword').value;
    var errorMessage = document.getElementById('errorMessage');
    var form = document.getElementById('createDoctorForm');

    if (senha !== confirmarSenha) {
        errorMessage.textContent = 'As senhas não coincidem.';
    } else {
        errorMessage.textContent = '';
        if (form.checkValidity()) {
            form.submit();
        } else {
            form.reportValidity();
        }
    }
}