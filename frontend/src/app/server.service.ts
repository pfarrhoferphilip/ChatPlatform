import {inject, Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Message} from './message';
import {map, Observable} from 'rxjs';
import {MessageCreate} from './message-create';
import {User} from './user';
import {Router} from '@angular/router';
import {routes} from './app.routes';

@Injectable({
  providedIn: 'root'
})
export class ServerService {
  httpClient: HttpClient = inject(HttpClient);
  router: Router = inject(Router);

  private readonly baseUrl: string = 'http://localhost:8080/api/servers';

  user:User = JSON.parse(<string>sessionStorage.getItem("user"));

  headers!:HttpHeaders;

  getAll(): Observable<Message[]> {
    return this.httpClient.get<Message[]>(this.baseUrl + '/1/messages', { headers: this.headers })
      .pipe(map(r => {
        return r;
      }))
  }

  sendMessage(message: MessageCreate): Observable<Message> {
    return this.httpClient.post<Message>(this.baseUrl + "/1/messages", message, { headers: this.headers });
  }

  constructor() {
    if (!sessionStorage.getItem('user')) {
      this.router.navigate(['login']);
    }
    this.headers = new HttpHeaders({
      'Authorization': `${this.user.id}:${sessionStorage.getItem("password")}`
    });
  }
}
