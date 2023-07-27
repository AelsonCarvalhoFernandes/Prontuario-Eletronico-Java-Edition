import Botao from "../components/Botao/Botao"

function Login(){
    return (
        <>
            <form>
                <input type="email" placeholder="email@email.com"/>
                <input type="password" placeholder="Senha"/>

                <Botao name="Entrar"/>

            </form>
        </>
    )
}