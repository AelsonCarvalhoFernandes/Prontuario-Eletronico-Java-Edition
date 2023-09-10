import { createContext, useState } from "react";
import axios, { Axios } from "axios";
import api from "./api";

export const authContext = createContext();

export function AuthProvider(props){

    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")

    const [data, setData] = useState()

    const authentication = (e)=>{
        e.preventDefault();

        console.log(email)
        console.log(password)

        axios.post("http://localhost:8080/auth/login", {"email": email, "password":password}).then(response => {
            console.log(response.data)
        }

        )
    } 

    return(
        <authContext.Provider value={{authentication, setEmail, setPassword}}>
            {props.children}
        </authContext.Provider>
    )
}
