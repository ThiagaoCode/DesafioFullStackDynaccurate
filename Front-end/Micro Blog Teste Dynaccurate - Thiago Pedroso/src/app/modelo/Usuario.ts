import { Postagem } from "./Postagem"

export class Usuario{

    public id: number
    public usuario: string
    public foto: string
    public nome: string
    public tipo: string
    public senha:string
    public postagens: Postagem[]

}
