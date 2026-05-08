import { Component } from '@angular/core';
import { HeaderComponent } from '../../layout/header/header.component';

@Component({
  standalone: true,
  imports: [HeaderComponent],
  template: `<app-header></app-header><main class="content"><h2>Página principal</h2></main>`
})
export class HomeComponent {}
