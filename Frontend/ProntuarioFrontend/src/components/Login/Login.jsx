import { useState } from "react"
import ButtonLogin from "../Buttons/ButtonLogin.jsx"
import { useContext } from "react"

import Style from "../Login/Login.module.css"
import { authContext } from "../../context/AuthContext.jsx"

function Login(){

    const {authentication, setEmail, setPassword} = useContext(authContext)

    return (
        <div className={Style.conteiner}>

            <form className={Style.loginForm}>
                <div>
                    <label htmlFor="">E-mail:</label>
                    <input type="email" onChange={(e)=>{setEmail(e.target.value)}}/>
                </div>

                <div>
                    <label htmlFor="">Senha:</label>
                    <input type="password" onChange={(e)=>{setPassword(e.target.value)}}/>
                </div>
                <div>
                    <button onClick={authentication}>Entrar</button>
                </div>
            </form>
        </div>
    )
}

export default Login