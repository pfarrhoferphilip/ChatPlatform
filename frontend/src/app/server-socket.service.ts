import { Injectable } from '@angular/core';
import {Observable, Subject} from 'rxjs';
import {MessageCreate} from './message-create';
import {Message} from './message';

@Injectable({
  providedIn: 'root'
})
export class ServerSocketService {

  private socket?: WebSocket;
  private messageSubject = new Subject<Message>();
  private readonly url: string = 'ws://localhost:8080/api/ws/servers';

  public connect(): Observable<Message> {
    this.socket = new WebSocket(this.url);

    this.socket.onmessage = (event) => {
      this.messageSubject.next(JSON.parse(event.data));
    };

    this.socket.onerror = (error) => {
      console.error("WebSocket error:", error);
    };

    return this.messageSubject.asObservable();
  }
}
