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

  getReviewsByContent(clip: String): Observable<Review[]> {
    const url = `${this.reviewUrl}?clip=${clip}`;

    return this.http.get<Review[]>(url)
      .pipe(
        tap(_ => console.log(`fetched reviews that have clip=${clip}`)),
        catchError(this.handleError<Review[]>(`getReviewsByClip clip=${clip}`, []))
      );
  }

  getReviewsBetweenScore(min: number, max: number): Observable<Review[]> {
    const url = `${this.reviewUrl}?min=${min}&max=${max}`;

    return this.http.get<Review[]>(url)
      .pipe(
        tap(_ => console.log(`fetched reviews between scores ${min}-${max}`)),
        catchError(this.handleError<Review[]>(`getReviewsBetweenScores ${min}-${max}`, []))
      );
  }

  getReviewById(id: number): Observable<Review> {
    const url = `${this.reviewUrl}?id=${id}`;

    return this.http.get<Review>(url)
      .pipe(
        tap(_ => console.log(`fetched review that have id=${id}`)),
        catchError(this.handleError<Review>(`getReviewsById clip=${id}`))
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
