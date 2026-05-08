import { Component } from '@angular/core';
import { HeaderComponent } from '../../layout/header/header.component';

@Component({
  standalone: true,
  imports: [HeaderComponent],
  template: `
    <app-header></app-header>
    <main class="content">
      <h2>Panel principal</h2>
      <p>Bienvenido al sistema del Taller Mecánico. Desde aquí puedes navegar al módulo de carros y administración.</p>
    </main>
  `
})
export class HomeComponent {}
