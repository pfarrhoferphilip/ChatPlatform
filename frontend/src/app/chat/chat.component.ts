import {Component, inject, Input, OnInit} from '@angular/core';
import {MessageComponent} from '../message/message.component';
import {ServerService} from '../server.service';
import {Message} from '../message';
import {FormsModule} from '@angular/forms';
import {User} from '../user';
import {ServerSocketService} from '../server-socket.service';
import {Router} from '@angular/router';
import {routes} from '../app.routes';

@Component({
  selector: 'app-chat',
  imports: [
    MessageComponent,
    FormsModule
  ],
  templateUrl: './chat.component.html',
  styleUrl: './chat.component.css'
})
export class ChatComponent implements OnInit {
  messages!: Message[];
  serverService: ServerService = inject(ServerService);
  serverSocketService: ServerSocketService = inject(ServerSocketService);
  router: Router = inject(Router);
  user:User = JSON.parse(<string>sessionStorage.getItem("user"));

  ngOnInit(): void {

    this.serverService.getAll().subscribe(messages => {
      this.messages = messages;
    })

    this.serverSocketService.connect().subscribe(message => {
      console.log(message);
      this.messages.push(message);
    })
  }

  messageInput: string = '';

  sendMessage() {
    this.serverService.sendMessage(
      {
        content: this.messageInput,
        userId: this.user.id
      }
    ).subscribe(message => {
      this.messageInput = '';
    })
  }

}
