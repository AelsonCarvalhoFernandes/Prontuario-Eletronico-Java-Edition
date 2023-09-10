import Style from "../Header/Header.module.css"
import menuicon from "../../assets/icons/menu.png"

import { useState } from "react"

function Header(){

    const [menu, setMenu] = useState(Style.menuVisibleFalse)

    const mudarMenu = () =>{
        if(menu == Style.menuVisibleFalse){
            setMenu(Style.menuVisibleTrue)
        }else{
            setMenu(Style.menuVisibleFalse)
        }
    }

    return(
        <header className={Style.cabecalho}>
            <div className={Style.top}>
                <h3>Titulo da pagina</h3>
                <img src={menuicon} alt="menu" onClick={mudarMenu}/>
            </div>
            <nav className={menu}>
                <ul>
                    <li><a href="#">Pagina Inicial</a></li>
                    <li><a href="#">Sobre</a></li>
                    <li><a href="#">Contato</a></li>
                </ul>

                <div>
                    <a href="#">Login</a>
                </div>
            </nav>
        </header>
    )
}

export default Header