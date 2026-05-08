import { Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { HomeComponent } from './pages/home/home.component';
import { CarrosListComponent } from './pages/carros-list/carros-list.component';
import { CarroCreateComponent } from './pages/carro-create/carro-create.component';
import { CarroEditComponent } from './pages/carro-edit/carro-edit.component';
import { CarroDetailComponent } from './pages/carro-detail/carro-detail.component';
import { AdminPanelComponent } from './pages/admin-panel/admin-panel.component';
import { authGuard } from './core/guards/auth.guard';
import { adminGuard } from './core/guards/admin.guard';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'home', component: HomeComponent, canActivate: [authGuard] },
  { path: 'carros', component: CarrosListComponent, canActivate: [authGuard] },
  { path: 'carros/crear', component: CarroCreateComponent, canActivate: [authGuard] },
  { path: 'carros/editar/:id', component: CarroEditComponent, canActivate: [authGuard, adminGuard] },
  { path: 'carros/detalle/:id', component: CarroDetailComponent, canActivate: [authGuard] },
  { path: 'admin', component: AdminPanelComponent, canActivate: [authGuard, adminGuard] },
  { path: '', pathMatch: 'full', redirectTo: 'login' },
  { path: '**', redirectTo: 'login' }
];
