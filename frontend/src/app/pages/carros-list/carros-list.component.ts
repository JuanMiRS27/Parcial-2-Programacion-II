import { Component, OnInit } from '@angular/core';
import { HeaderComponent } from '../../layout/header/header.component';
import { CarrosService } from '../../core/services/carros.service';
import { CarroTaller } from '../../core/models/carro-taller.model';
import { CommonModule } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../core/services/auth.service';

@Component({
  standalone: true,
  imports: [HeaderComponent, CommonModule, RouterLink],
  templateUrl: './carros-list.component.html'
})
export class CarrosListComponent implements OnInit {
  carros: CarroTaller[] = [];
  constructor(private service: CarrosService, private auth: AuthService, private router: Router) {}
  ngOnInit() { this.load(); }
  load() { this.service.findAll().subscribe((r) => this.carros = r); }
  isAdmin() { return this.auth.isAdmin(); }
  goCreate() { this.router.navigate(['/carros/crear']); }
  delete(id?: number) {
    if (!id) return;
    this.service.delete(id).subscribe(() => this.load());
  }
}
