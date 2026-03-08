import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {map, Observable} from 'rxjs';
import {Message} from './message';
import {MessageCreate} from './message-create';
import {User} from './user';
import {UserCreate} from './user-create';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  httpClient: HttpClient = inject(HttpClient);

  private readonly baseUrl: string = 'http://localhost:8080/api/users';

  login(user: UserCreate): Observable<User> {
    return this.httpClient.put<User>(this.baseUrl + "/login", user);
  }

  create(user: UserCreate): Observable<User> {
    return this.httpClient.post<User>(this.baseUrl + "/create", user);
  }

  constructor() { }
}
