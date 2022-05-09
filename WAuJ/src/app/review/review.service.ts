import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, of, tap } from 'rxjs';
import { Hardware } from '../hardware/hardware';
import { Review } from './review';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  private reviewUrl = 'http://localhost:8080/review';

  httpOptions = {
    hearders: new HttpHeaders({ 'Content-Type': 'application/json' })
  };  

  constructor(
    private http: HttpClient
  ) { }

  getReviews(): Observable<Review[]> {
    return this.http.get<Review[]>(this.reviewUrl)
      .pipe(
        tap(_ => console.log('fetched Reviews')),
        catchError(this.handleError<Review[]>('getReviews', []))
      );
  }

  getReviewsByHardwareCode(code: String): Observable<Review[]> {
    const url = `${this.reviewUrl}/${code}`;

    return this.http.get<Review[]>(url)
      .pipe(
        tap(_ => console.log(`fetched reviews that references code=${code}`)),
        catchError(this.handleError<Review[]>(`getReviews code=${code}`, []))
      );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(operation);
      console.error(error);
      return of(result as T);
    };
  }
}
