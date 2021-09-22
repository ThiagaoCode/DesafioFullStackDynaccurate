import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment.prod';
import { UsuarioDTO } from '../modelo/UsuarioDTO';
import { AlertasService } from '../service/alertas.service';

import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-entrar',
  templateUrl: './entrar.component.html',
  styleUrls: ['./entrar.component.css']
})
export class EntrarComponent implements OnInit {

  usuarioDTO: UsuarioDTO = new UsuarioDTO()

  constructor(
    private auth: AuthService,
    private router: Router,
    private alertas: AlertasService

  ) { }

  ngOnInit() {

    window.scroll(0, 0)

    if (environment.token == '') {
      this.alertas.showAlertInfo('Sua sessão expirou, faça login novamente!!!')
      this.router.navigate(['/entrar'])
    }


  }

  entrar() {
    this.auth.entrar(this.usuarioDTO).subscribe((resp: UsuarioDTO) => {
      console.log(JSON.stringify(this.usuarioDTO))

      this.usuarioDTO = resp
      console.log("~resp foto" + resp.foto)
      environment.token = this.usuarioDTO.token
      environment.nome = this.usuarioDTO.nome
      environment.foto = resp.foto
      environment.id = this.usuarioDTO.id
      environment.usuario = this.usuarioDTO.usuario
      environment.tipo = this.usuarioDTO.tipo

      console.log(environment.token)
      console.log(environment.nome)
      console.log(environment.foto)
      console.log(environment.id)
      console.log(environment.usuario)
      console.log(environment.tipo)

      this.router.navigate(["/home"])

    }, erro => {
      if (erro.status == 500) {
        this.alertas.showAlertDanger("Usuário ou senha estão incorretos!")
      }
      if (erro.status == 400) {
        this.alertas.showAlertDanger("Usuário ou senha estão incorretos!")
      }
    })
  }


}