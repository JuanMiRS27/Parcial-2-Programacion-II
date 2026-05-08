export interface User {
  id: number;
  nombre: string;
  email: string;
  role: 'ADMIN' | 'USER';
}
