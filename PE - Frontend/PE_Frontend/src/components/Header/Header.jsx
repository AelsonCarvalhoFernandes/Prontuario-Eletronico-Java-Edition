import Style from "./Header.module.css"
import { useContext } from "react"

export function Header(){
    return (
        <header>
            <div className={Style.hamburger}>
                <img src="" alt="" />
            </div>
            <nav className={Style.nagecacao}>
                <ul>
                    <li>Pagina Inicial</li>
                    <li>Sobre</li>
                    <li>Ajuda</li>
                </ul>

                <div className={Style.cellLoginConteiner}>

                </div>
            </nav>

            <div className={Style.desktopLoginConteiner}>

            </div>
        </header>
    )
}