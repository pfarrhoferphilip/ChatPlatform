import {Component, inject, Input, OnInit} from '@angular/core';
import {MessageComponent} from '../message/message.component';
import {ServerService} from '../server.service';
import {Message} from '../message';
import {FormsModule} from '@angular/forms';
import {User} from '../user';

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

  ngOnInit(): void {
    this.serverService.getAll().subscribe(messages => {
      this.messages = messages;
    })
  }

  messageInput: string = '';

  sendMessage() {
    console.log(this.messageInput);
    this.serverService.sendMessage(
      {
        content: this.messageInput,
        userId: 2
      }
    ).subscribe(message => {
      console.log(message);
      this.messageInput = '';
    })
  }

}
