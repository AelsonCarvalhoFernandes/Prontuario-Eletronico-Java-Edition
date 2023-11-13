
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

// Função para filtrar a tabela com base no valor digitado na barra de pesquisa
function filterTable() {
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("textSearch");
    filter = input.value.toUpperCase();
    table = document.getElementById("myTable");
    tr = table.getElementsByTagName("tr");

    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[2];
        if (td) {
            txtValue = td.textContent || td.innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}
