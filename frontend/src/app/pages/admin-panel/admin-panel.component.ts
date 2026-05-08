import { Component, OnInit } from '@angular/core';
import { HeaderComponent } from '../../layout/header/header.component';
import { UsersService } from '../../core/services/users.service';
import { User } from '../../core/models/user.model';
import { CommonModule } from '@angular/common';

@Component({
  standalone: true,
  imports: [HeaderComponent, CommonModule],
  templateUrl: './admin-panel.component.html'
})
export class AdminPanelComponent implements OnInit {
  users: User[] = [];
  constructor(private usersService: UsersService) {}
  ngOnInit() { this.load(); }
  load() { this.usersService.findAll().subscribe((u) => this.users = u); }
  delete(id: number) { this.usersService.delete(id).subscribe(() => this.load()); }
}
