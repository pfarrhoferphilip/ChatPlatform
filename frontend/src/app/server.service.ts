import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Message} from './message';
import {map, Observable} from 'rxjs';
import {MessageCreate} from './message-create';

@Injectable({
  providedIn: 'root'
})
export class ServerService {
  httpClient: HttpClient = inject(HttpClient);

  private readonly baseUrl: string = 'http://localhost:8080/api/servers';

  getAll(): Observable<Message[]> {
    return this.httpClient.get<Message[]>(this.baseUrl + "/1/messages")
      .pipe(map(r => {
        return r;
      }))
  }

  sendMessage(message: MessageCreate): Observable<Message> {
    return this.httpClient.post<Message>(this.baseUrl + "/1/messages", message);
  }

  constructor() { }
}
