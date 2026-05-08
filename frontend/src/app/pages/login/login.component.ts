import { Component } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../../core/services/auth.service';
import { Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { inject } from '@angular/core';

@Component({
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
  templateUrl: './login.component.html'
})
export class LoginComponent {
  private fb = inject(FormBuilder);
  error = '';
  form = this.fb.group({ email: ['', [Validators.required, Validators.email]], password: ['', Validators.required] });
  constructor(private auth: AuthService, private router: Router) {}
  submit() {
    if (this.form.invalid) return;
    this.auth.login(this.form.getRawValue() as any).subscribe({
      next: () => this.router.navigate(['/home']),
      error: () => this.error = 'Credenciales inválidas'
    });
  }
}
