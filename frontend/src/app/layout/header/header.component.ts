import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../core/services/auth.service';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './header.component.html'
})
export class HeaderComponent {
  constructor(public auth: AuthService, private router: Router) {}
  go(path: string) { this.router.navigate([path]); }
  logout() { this.auth.logout(); this.router.navigate(['/login']); }
}
