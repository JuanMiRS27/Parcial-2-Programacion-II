import { Component } from '@angular/core';
import { HeaderComponent } from '../../layout/header/header.component';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { CarrosService } from '../../core/services/carros.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { inject } from '@angular/core';
import { AuthService } from '../../core/services/auth.service';

@Component({
  standalone: true,
  imports: [HeaderComponent, ReactiveFormsModule, CommonModule],
  templateUrl: './carro-create.component.html'
})
export class CarroCreateComponent {
  private fb = inject(FormBuilder);
  private auth = inject(AuthService);
  estados = ['RECIBIDO', 'EN_REVISION', 'EN_REPARACION', 'REPARADO', 'ENTREGADO'];
  form = this.fb.group({
    placa: ['', Validators.required],
    modelo: ['', Validators.required],
    fechaIngreso: ['', Validators.required],
    estado: ['RECIBIDO', Validators.required],
    tipoDanio: ['', Validators.required]
  });
  constructor(private service: CarrosService, private router: Router) {}
  isAdmin() { return this.auth.isAdmin(); }
  save() {
    if (this.form.invalid) return;
    const payload: any = this.form.getRawValue();
    if (!this.isAdmin()) {
      payload.estado = 'EN_REVISION';
    }
    this.service.create(payload).subscribe(() => this.router.navigate(['/carros']));
  }
}
