import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class ModalPopServiceService {
  constructor() {}
  error_message: any = '';
  title: any = '';
  message: any = '';

  name_course: any = '';
  discription: any = '';

  public open_error(message:any) {
    this.error_message =message;
    this.title = 'Error Message !';
    const errorMessageElement = document.getElementById('error-message');
    if (errorMessageElement) {
      errorMessageElement.textContent = this.error_message;
    }
    const title_message = document.getElementById('error-title');
    if (title_message) {
      title_message.textContent = this.title;
    }
    const modal = document.getElementById('ERROR_login');
    if (modal) {
      modal.classList.add('show');
      modal.style.display = 'block';
    }
  }
  
  public pop_up(message:any,title:any) {
    this.message =message;
    this.title = title;
    const errorMessageElement = document.getElementById('message');
    if (errorMessageElement) {
      errorMessageElement.textContent = this.message;
    }
    const title_message = document.getElementById('title');
    if (title_message) {
      title_message.textContent = this.title;
    }
    const modal = document.getElementById('popup');
    if (modal) {
      modal.classList.add('show');
      modal.style.display = 'block';
    }
  }
  public close() {
    const modal = document.getElementById('popup');
    if (modal) {
      modal.classList.remove('show');
      modal.style.display = 'none';
    }
  }

  public close_error() {
    const modal = document.getElementById('ERROR_login');
    if (modal) {
      modal.classList.remove('show');
      modal.style.display = 'none';
    }
  }

}