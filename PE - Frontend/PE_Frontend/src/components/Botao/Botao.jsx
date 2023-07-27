import Style from "./Botao.module.css"
import { useState } from "react"

function Botao({name, estilo}){

    const [btn, setBtn] = useState(estilo)
    

    return (
        <input className={btn == "cancel" ? Style.botaoCancel : Style.botaoSuccess } type="submit" value={name}/>
    )
}

export default Botao