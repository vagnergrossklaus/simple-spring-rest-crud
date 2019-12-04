import { Component, OnInit } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { map, tap, catchError, retry } from 'rxjs/operators';
import { HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import { Person } from './person';

const apiUrl = 'http://localhost:9091/api/person';
const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'app';

  persons: Person[] = [];
  person: Person = undefined;

  constructor(private http: HttpClient) {
    this.getPersons().subscribe(persons => this.persons = persons);
  }

  getPersons(): Observable<Person[]> {
     return this.http.get<Person[]>(apiUrl);
  }

  onSelect(person : Person){
    this.person = person;
  }

  onNewPerson(){
    this.person = new Person(undefined, "");
  }

  save(): void {
    this.addPerson(this.person)
      .subscribe(person => {
        this.person = undefined;
        this.getPersons().subscribe(persons => this.persons = persons);
      });
  }

  /** POST: add a new hero to the server */
  addPerson (person: Person): Observable<Person> {
    return this.http.post<Person>(apiUrl, person, httpOptions)
  }

  ngOnInit(): void {
    // this.onNewPerson();
  }

//   private handleError(error: HttpErrorResponse) {
//     if (error.error instanceof ErrorEvent) {
//       // A client-side or network error occurred. Handle it accordingly.
//       console.error('An error occurred:', error.error.message);
//     } else {
//       // The backend returned an unsuccessful response code.
//       // The response body may contain clues as to what went wrong,
//       console.error(
//         'Backend returned code ${error.status}, ' +
//         'body was: ${error.error}');
//     }
//     // return an observable with a user-facing error message
//     return throwError(
//       'Something bad happened; please try again later.');
//   };

}
