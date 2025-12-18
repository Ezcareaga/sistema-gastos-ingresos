import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface TransaccionRapidaRequest {
  tipo: string;
  monto: number;
  categoria: string;
  cuenta: string;
  descripcion?: string;
  fecha: string;
}

export interface TransaccionRapidaResponse {
  id: number;
  tipo: string;
  monto: number;
  categoria: string;
  cuenta: string;
  descripcion?: string;
  fecha: string;
  mensaje: string;
}

export interface ResumenFinanciero {
  totalIngresos: number;
  totalGastos: number;
  balance: number;
}

@Injectable({ providedIn: 'root' })
export class TransaccionRapidaService {
  protected resourceUrl = 'api/transacciones-rapidas';

  constructor(protected http: HttpClient) {}

  registrar(request: TransaccionRapidaRequest): Observable<HttpResponse<TransaccionRapidaResponse>> {
    return this.http.post<TransaccionRapidaResponse>(this.resourceUrl, request, { observe: 'response' });
  }

  obtenerResumen(): Observable<HttpResponse<ResumenFinanciero>> {
    return this.http.get<ResumenFinanciero>(`${this.resourceUrl}/resumen`, { observe: 'response' });
  }
}
