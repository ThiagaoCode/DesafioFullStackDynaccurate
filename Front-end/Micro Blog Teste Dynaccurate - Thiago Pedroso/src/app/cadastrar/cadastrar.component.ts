import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Usuario } from '../modelo/Usuario';
import { AlertasService } from '../service/alertas.service';
import { AuthService } from '../service/auth.service';


@Component({
  selector: 'app-cadastrar',
  templateUrl: './cadastrar.component.html',
  styleUrls: ['./cadastrar.component.css']
})
export class CadastrarComponent implements OnInit {

  usuario: Usuario = new Usuario

  confirmarSenha: string

  tipo: string

  constructor(
    private authService: AuthService,
    private router: Router,
    private alertas: AlertasService


  ) { }

  ngOnInit() {
    window.scroll(0, 0)

  }

  selecionarRadio(select: string){
    this.tipo = select;
    this.alertas.showAlertInfo("Você selecionou " + this.tipo + " como tipo de Usuário");
  }

  confirmSenha(event: any) {
    this.confirmarSenha = event.target.value
  }


  tipoUser(event: any) {
    this.tipo = event.target.value
  }

  cadastrar() {
   
    this.usuario.tipo = this.tipo
    
    
    if (this.usuario.senha != this.confirmarSenha) {
      this.alertas.showAlertDanger("A senha está incorreta.")
    }
    else {
      this.authService.cadastrar(this.usuario).subscribe((resp: Usuario) => {
        this.usuario = resp
        console.log(JSON.stringify(resp))
        this.router.navigate(["/entrar"])
        this.alertas.showAlertSuccess("Usuário cadastrado com sucesso!")
      })
    }

  }

}

