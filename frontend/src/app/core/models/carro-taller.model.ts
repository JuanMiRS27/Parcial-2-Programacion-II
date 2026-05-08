export interface CarroTaller {
  id?: number;
  placa: string;
  modelo: string;
  fechaIngreso: string;
  estado: 'RECIBIDO' | 'EN_REVISION' | 'EN_REPARACION' | 'REPARADO' | 'ENTREGADO';
  tipoDanio: string;
}
