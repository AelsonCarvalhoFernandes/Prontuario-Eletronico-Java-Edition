import { createContext, useState, useEffect } from "react";
import api from "../resources/Api";

export const AuthContext = createContext({})

export const AuthProvider = ({children}) => {

    const [auth, setAuth] = useState(sessionStorage.getItem("auth"))

    const authenticate = (email, password) => {
        api.post("/auth", {"email": email, "password": password})
        .then((e) =>{
            sessionStorage.setItem("auth", e)
        })
    }

    return (
        <AuthContext.Provider value={{auth, authenticate}}>
            {children}
        </AuthContext.Provider>
    )
}