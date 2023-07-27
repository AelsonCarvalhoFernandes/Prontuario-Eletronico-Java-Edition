import Style from "./Header.module.css"

function Header(){
    return (
        <header>
            <nav>
                <ul>
                    <li>Pagina Inicial</li>
                    <li>Sobre</li>
                    <li>Ajuda</li>
                </ul>
            </nav>

            <div className={Style.desktopLoginConteiner}>

            </div>
        </header>
    )
}