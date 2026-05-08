import { Component, OnInit } from '@angular/core';
import { HeaderComponent } from '../../layout/header/header.component';
import { UsersService } from '../../core/services/users.service';
import { User } from '../../core/models/user.model';
import { CommonModule } from '@angular/common';
import { CarrosService } from '../../core/services/carros.service';
import { CarroTaller } from '../../core/models/carro-taller.model';

@Component({
  standalone: true,
  imports: [HeaderComponent, CommonModule],
  templateUrl: './admin-panel.component.html'
})
export class AdminPanelComponent implements OnInit {
  users: User[] = [];
  carros: CarroTaller[] = [];
  estados: CarroTaller['estado'][] = ['RECIBIDO', 'EN_REVISION', 'EN_REPARACION', 'REPARADO', 'ENTREGADO'];
  roles: User['role'][] = ['USER', 'ADMIN'];
  error = '';
  constructor(private usersService: UsersService, private carrosService: CarrosService) {}
  ngOnInit() { this.load(); this.loadCarros(); }
  load() {
    this.usersService.findAll().subscribe({
      next: (u) => this.users = u,
      error: () => this.error = 'No se pudieron cargar los usuarios'
    });
  }
  loadCarros() {
    this.carrosService.findAll().subscribe({
      next: (c) => this.carros = c,
      error: () => this.error = 'No se pudieron cargar los carros'
    });
  }
  delete(id: number) { this.usersService.delete(id).subscribe(() => this.load()); }
  updateRole(user: User, role: string) {
    if (!this.roles.includes(role as User['role'])) return;
    this.usersService.updateRole(user.id, role as User['role']).subscribe(() => this.load());
  }
  updateEstado(carro: CarroTaller, estado: CarroTaller['estado']) {
    if (!carro.id) return;
    this.carrosService.updateEstado(carro.id, estado).subscribe(() => this.loadCarros());
  }

  updateEstadoFromSelect(carro: CarroTaller, estado: string) {
    if (!this.estados.includes(estado as CarroTaller['estado'])) return;
    this.updateEstado(carro, estado as CarroTaller['estado']);
  }
}
