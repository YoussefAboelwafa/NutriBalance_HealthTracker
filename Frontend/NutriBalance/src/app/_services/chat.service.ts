import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
const baseUrl = 'http://localhost:8080/chat';
@Injectable({
  providedIn: 'root',
})
export class ChatService {
  constructor(private http: HttpClient) {}
  saveChat(user_id: any, coach_id: any, message: any): Observable<any> {
    return this.http.post<any>(`${baseUrl}/save`, {
      user_id: user_id,
      coach_id: coach_id,
      message: message,
      sent_by: 'user',
    });
  
  }
  saveChatByCoach(user_id: any, coach_id: any, message: any): Observable<any> {
    return this.http.post<any>(`${baseUrl}/save`, {
      user_id: user_id,
      coach_id: coach_id,
      message: message,
      sent_by: 'coach',
    });
  }

  get_user_chat(user_id: any): Observable<any> {
    return this.http.get<any>(`${baseUrl}/get_ordered_user_chats`, {
      params: { user_id: user_id },
    });
  }
  get_coach_chat(coach_id: any): Observable<any> {
    return this.http.get<any>(`${baseUrl}/get_ordered_coach_chats`, {
      params: { coach_id: coach_id },
    });
  }
  deletechatByUser(user_id: any): Observable<any> {
    return this.http.delete<any>(`${baseUrl}/delete`, {
      params: { user_id: user_id },
    });
  }
}
