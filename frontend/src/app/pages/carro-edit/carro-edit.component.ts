import { Component, OnInit } from '@angular/core';
import { HeaderComponent } from '../../layout/header/header.component';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { CarrosService } from '../../core/services/carros.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { inject } from '@angular/core';

@Component({
  standalone: true,
  imports: [HeaderComponent, ReactiveFormsModule, CommonModule],
  templateUrl: './carro-edit.component.html'
})
export class CarroEditComponent implements OnInit {
  private fb = inject(FormBuilder);
  id!: number;
  estados = ['RECIBIDO', 'EN_REVISION', 'EN_REPARACION', 'REPARADO', 'ENTREGADO'];
  form = this.fb.group({
    placa: ['', Validators.required],
    modelo: ['', Validators.required],
    fechaIngreso: ['', Validators.required],
    estado: ['RECIBIDO', Validators.required],
    tipoDanio: ['', Validators.required]
  });
  constructor(private service: CarrosService, private route: ActivatedRoute, private router: Router) {}
  ngOnInit() {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.service.findById(this.id).subscribe((c) => this.form.patchValue(c as any));
  }
  save() {
    if (this.form.invalid) return;
    this.service.update(this.id, this.form.getRawValue() as any).subscribe(() => this.router.navigate(['/carros']));
  }
}
