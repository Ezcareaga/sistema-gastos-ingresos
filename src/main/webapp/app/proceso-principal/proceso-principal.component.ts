import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { TransaccionRapidaService, TransaccionRapidaRequest, ResumenFinanciero } from '../entities/transaccion-rapida.service';

@Component({
  selector: 'jhi-proceso-principal',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './proceso-principal.component.html',
  styleUrls: ['./proceso-principal.component.scss'],
})
export class ProcesoPrincipalComponent implements OnInit {
  resumen: ResumenFinanciero = {
    totalIngresos: 0,
    totalGastos: 0,
    balance: 0,
  };

  transaccion: TransaccionRapidaRequest = {
    tipo: 'INCOME',
    monto: 0,
    categoria: 'Ventas',
    cuenta: 'Caja',
    descripcion: '',
    fecha: new Date().toISOString().split('T')[0],
  };

  mensaje: string = '';
  error: string = '';

  constructor(
    private transaccionService: TransaccionRapidaService,
    private router: Router,
  ) {}

  ngOnInit(): void {
    this.cargarResumen();
  }

  cargarResumen(): void {
    this.transaccionService.obtenerResumen().subscribe({
      next: res => {
        if (res.body) {
          this.resumen = res.body;
        }
      },
      error: err => {
        console.error('Error al cargar resumen:', err);
        this.error = 'Error al cargar el resumen financiero';
      },
    });
  }

  registrarTransaccion(): void {
    this.mensaje = '';
    this.error = '';

    this.transaccionService.registrar(this.transaccion).subscribe({
      next: res => {
        if (res.body) {
          this.mensaje = res.body.mensaje;
          this.limpiarFormulario();
          this.cargarResumen(); // Actualizar resumen
        }
      },
      error: err => {
        console.error('Error al registrar:', err);
        this.error = err.error?.detail || 'Error al registrar la transacción';
      },
    });
  }

  limpiarFormulario(): void {
    this.transaccion = {
      tipo: 'INCOME',
      monto: 0,
      categoria: 'Ventas',
      cuenta: 'Caja',
      descripcion: '',
      fecha: new Date().toISOString().split('T')[0],
    };
  }
}
