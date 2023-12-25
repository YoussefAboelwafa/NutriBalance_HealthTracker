import { Component, OnInit } from '@angular/core';
import { ChatService } from '../_services/chat.service';
@Component({
  selector: 'app-user-chat',
  templateUrl: './user-chat.component.html',
  styleUrls: ['./user-chat.component.css']
})
export class UserChatComponent implements OnInit {

  constructor(private chatservice:ChatService) { }

  ngOnInit(): void {
  }

}
