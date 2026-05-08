import { Component, OnInit } from '@angular/core';
import { HeaderComponent } from '../../layout/header/header.component';
import { CarrosService } from '../../core/services/carros.service';
import { ActivatedRoute } from '@angular/router';
import { CarroTaller } from '../../core/models/carro-taller.model';
import { CommonModule } from '@angular/common';

@Component({
  standalone: true,
  imports: [HeaderComponent, CommonModule],
  templateUrl: './carro-detail.component.html'
})
export class CarroDetailComponent implements OnInit {
  carro?: CarroTaller;
  constructor(private service: CarrosService, private route: ActivatedRoute) {}
  ngOnInit() {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.service.findById(id).subscribe((c) => this.carro = c);
  }
}
