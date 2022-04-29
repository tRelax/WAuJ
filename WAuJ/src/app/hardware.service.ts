import { Injectable } from '@angular/core';
import { Observable, of, tap, catchError } from 'rxjs';
import { Hardware } from './hardware';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class HardwareService {

  private hardwareUrl = 'http://localhost:8080/hardware';

  httpOptions = {
    hearders: new HttpHeaders({ 'Content-Type': 'application/json' })
  };  

  constructor(
    private http: HttpClient
  ) { }

  getHardwares(): Observable<Hardware[]> {
    return this.http.get<Hardware[]>(this.hardwareUrl)
    .pipe(
      tap(_ => console.log('fetched hardwares')),
      catchError(this.handleError<Hardware[]>('findAllHardware', []))
    );
  }

  getHardware(code: string): Observable<Hardware> {
    const url = `${this.hardwareUrl}/${code}`;
    return this.http.get<Hardware>(url)
    .pipe(
      tap(_ => console.log(`fetched hardware code=${code}`)),
      catchError(this.handleError<Hardware>(`getHardware code=${code}`))
    );
  }

  updateHardware(hardware: Hardware): Observable<any> {
    const url = `${this.hardwareUrl}/${hardware.id}`;
    return this.http.put(url, hardware)
    .pipe(
      tap(_ => console.log(`updated hardware with id=${hardware.id}`)),
      catchError(this.handleError<any>('updateHardware'))
    )
  }
  
  addHardware(hardware: Hardware): Observable<Hardware> {
    return this.http.post<Hardware>(this.hardwareUrl, hardware)
    .pipe(
      tap((newHardware: Hardware) => console.log(`added hardware w/ code=${newHardware.code}`)),
      catchError(this.handleError<Hardware>('addHardware'))
    );
  }

  deleteHardware(hardware: Hardware | string): Observable<Hardware> {
    const code = typeof hardware === 'string' ? hardware : hardware.code;
    const url = `${this.hardwareUrl}/${code}`;

    return this.http.delete<Hardware>(url)
    .pipe(
      tap(_ => console.log(`deleted hardware w/ code=${code}`)),
      catchError(this.handleError<Hardware>('deleteHardware'))
    );
  }

  private handleError<T>(operation = 'operation', result?: T){
    return (error: any): Observable<T> => {
      console.error(operation);
      console.error(error);
      return of(result as T);
    }
  }
}
